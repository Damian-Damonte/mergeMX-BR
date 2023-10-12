/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.requisicion.service;

import com.metalsa.aprobacion.model.Usuario;
import com.metalsa.aprobacion.repository.UsuarioRepository;
import com.metalsa.core.model.NvcTblOaProveedoresH;
import com.metalsa.requisicion.pojo.RequisicionFad;
import com.metalsa.requisicion.pojo.RequisicionFadRequest;
import com.metalsa.requisicion.pojo.UenWithDefault;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;

import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import lombok.extern.log4j.Log4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

/**
 *
 * @author miguel.rdz
 * @param <T>
 * @param <ID>
 */
@Log4j
@Service
public class ConsRequisicionServiceImpl<T, ID extends Serializable> implements ConsRequisicionService {

    private static Locale LOCALE;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private MessageSource messages;

    public List<RequisicionFad> getRequisFad2(RequisicionFadRequest request) {
        List<RequisicionFad> listaRequis = new ArrayList();
        String out = "OK";
        try {
            List<Object[]> results = em.createNativeQuery("select d.id_uen, \n"
                    + "            u.organization_name,\n"
                    + "            c.id_fad,\n"
                    + "            to_char(r.fecha_requisicion, 'DD-Mon-YYYY'),\n"
                    + "            f.descripcion,\n"
                    + "            p.nombre_proveedor,\n"
                    + "            f.monto,\n"
                    + "            f.id_moneda,\n"
                    + "            d.id_requisicion,\n"
                    + "            d.id_partida,\n"
                    + "            d.descripcion_producto,\n"
                    + "            (select r.descripcion from nvc_tbl_razon_fad r where r.id_razon_fad = f.id_razon_fad) razon_fad,\n"
                    + "            f.otra_razon,\n"
                    + "            f.comentario_requisitor,\n"
                    + "            '-' servicio_realizado,\n"
                    + "            d.id_orden_de_compra,\n"
                    + "            (select distinct nombre_usuario from usuario where id_usuario = r.id_usuario) requisitor,\n"
                    + "            (select distinct nombre_usuario from usuario where id_usuario = d.comprador) comprador,\n"
                    + "            (select distinct nombre_usuario from usuario where id_usuario = r.id_usuario) autor\n"
                    + "            from nvc_tbl_fad f \n"
                    + "                    join nvc_tbl_carro_compra c on f.id_formato_asignacion_directa = c.id_fad join requisicion r on c.id_requisicion = r.id_requisicion \n"
                    + "                    join detalle_de_requisicion d on c.id_requisicion = d.id_requisicion \n"
                    + "                    join oa_uens u on d.id_uen = u.organization_id and c.id_partida = d.id_partida\n"
                    + "                    join nvc_tbl_oa_proveedores_h p on f.id_prov = p.id_proveedor\n"
                    + "                    left join nvc_tbl_prov_sites_h s on f.id_prov = s.id_proveedor and f.id_site = s.id_sucursal_proveedor\n"
                    + "                    where 1 = 1\n"
                    + "                    --filtros\n"
                    + "                    and (0 = (select nvl2('" + listToString(request.getUen()) + "', 1, 0) from dual) or (d.id_uen in ('" + listToString(request.getUen()) + "')))\n"
                    + "                    and (0 = (select nvl2('', 1, 0) from dual) or (f.id_formato_asignacion_directa in (''))) --id_fad\n"
                    + "                    --/filtros\n"
                    + "                    order by d.id_requisicion, d.id_partida ")
                    .getResultList();
            results.stream().map((row) -> {
                RequisicionFad rf = new RequisicionFad();
                int index = -1;
                rf.setIdUen(Integer.parseInt(row[++index].toString()));
                rf.setNombreUen(row[++index] != null ? row[index].toString() : "");
                rf.setIdFad(Integer.parseInt(row[++index].toString()));
                rf.setFechaCreacion(row[++index] != null ? row[index].toString() : "");
                rf.setProyMatServ(row[++index] != null ? row[index].toString() : "");
                rf.setNombreProveedor(row[++index] != null ? row[index].toString() : "");
                rf.setMonto(Double.parseDouble(row[++index].toString()));
                rf.setMoneda(row[++index] != null ? row[index].toString() : "");
                rf.setIdRequisicion(Integer.parseInt(row[++index].toString()));
                rf.setIdPartida(Integer.parseInt(row[++index].toString()));
                rf.setDescripcionLinea(row[++index] != null ? row[index].toString() : "");
                rf.setRazonFad(row[++index] != null ? row[index].toString() : "");
                rf.setDetalleFad(row[++index] != null ? row[index].toString() : "");
                rf.setComentariosAdicionales(row[++index] != null ? row[index].toString() : "");
                rf.setServicioRealizado(row[++index].toString() != null);
                rf.setIdOrdenCompra(row[++index] != null ? Integer.parseInt(row[index].toString()) : null);
                rf.setNombreRequisitor(row[++index] != null ? row[index].toString() : "");
                rf.setNombreComprador(row[++index] != null ? row[index].toString() : "");
                rf.setNombreAutor(row[++index] != null ? row[index].toString() : "");
                return rf;
            }).forEachOrdered((rf) -> {
                listaRequis.add(rf);
            });
        } catch (Exception e) {
            log.debug(e.getLocalizedMessage(), e);
            log.error("error en : saveNotificaciones :" + e.getMessage());
        }
        return listaRequis;
    }

    @Override
    public List<RequisicionFad> getRequisFad(RequisicionFadRequest request) {
        List<RequisicionFad> listaRequis = new ArrayList();
        List<Object[]> resultset;
        String out = "OK";
        try {
            StoredProcedureQuery procedure = em.createStoredProcedureQuery("NVC_PKG_SPX_FAD.consulta_requis_fad");
            procedure.registerStoredProcedureParameter("p_uens", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_fecha_ini", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_fecha_fin", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_fads", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_id_idioma", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("message_out", String.class, ParameterMode.OUT);
            procedure.registerStoredProcedureParameter("out_cursor", void.class, ParameterMode.REF_CURSOR);
            procedure.setParameter("p_uens", listToString(request.getUen()));
            procedure.setParameter("p_fecha_ini", request.getFechaIni());
            procedure.setParameter("p_fecha_fin", request.getFechaFin());
            procedure.setParameter("p_fads", request.getFoliosFad());
            procedure.setParameter("p_id_idioma", request.getIdIdioma());
            if (procedure.execute()) {
                resultset = (List<Object[]>) procedure.getResultList();
                out = (String) procedure.getOutputParameterValue("message_out");
                for (Object[] row : resultset) {
                    RequisicionFad rf = new RequisicionFad();
                    int index = -1;
                    rf.setIdUen(Integer.parseInt(row[++index].toString()));
                    rf.setNombreUen(row[++index] != null ? row[index].toString() : "");
                    rf.setIdFad(Integer.parseInt(row[++index].toString()));
                    rf.setFechaCreacion(row[++index] != null ? row[index].toString() : "");
                    rf.setProyMatServ(row[++index] != null ? row[index].toString() : "");
                    rf.setNombreProveedor(row[++index] != null ? row[index].toString() : "");
                    rf.setMonto(Double.parseDouble(row[++index].toString()));
                    rf.setMoneda(row[++index] != null ? row[index].toString() : "");
                    rf.setIdRequisicion(Integer.parseInt(row[++index].toString()));
                    rf.setIdPartida(Integer.parseInt(row[++index].toString()));
                    rf.setDescripcionLinea(row[++index] != null ? row[index].toString() : "");
                    rf.setRazonFad(row[++index] != null ? row[index].toString() : "");
                    rf.setDetalleFad(row[++index] != null ? row[index].toString() : "");
                    rf.setComentariosAdicionales(row[++index] != null ? row[index].toString() : "");
                    rf.setServicioRealizado(row[++index] != null ? ("1".equals(row[index].toString())) : false);
                    rf.setIdOrdenCompra(row[++index] != null ? Integer.parseInt(row[index].toString()) : null);
                    rf.setNombreRequisitor(row[++index] != null ? row[index].toString() : "");
                    rf.setNombreComprador(row[++index] != null ? row[index].toString() : "");
                    rf.setNombreAutor(row[++index] != null ? row[index].toString() : "");
                    listaRequis.add(rf);
                }
            }
            log.debug(out);
        } catch (NumberFormatException e) {
            log.debug(e.getLocalizedMessage(), e);
            log.error("error en : getRequisFad :" + e.getMessage());
        }
        return listaRequis;
    }

    @Override
    public List<NvcTblOaProveedoresH> getLikeByUenWithFad(String uens, String query) {
        List<NvcTblOaProveedoresH> proveedores = new ArrayList();
        List<Object[]> resultset;
        String out = "OK";
        try {
            StoredProcedureQuery procedure = em.createStoredProcedureQuery("NVC_PKG_SPX_FAD.get_proveedores_fad");
            procedure.registerStoredProcedureParameter("p_uens", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_query", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("message_out", String.class, ParameterMode.OUT);
            procedure.registerStoredProcedureParameter("out_cursor", void.class, ParameterMode.REF_CURSOR);
            procedure.setParameter("p_uens", uens);
            procedure.setParameter("p_query", query);
            if (procedure.execute()) {
                resultset = (List<Object[]>) procedure.getResultList();
                out = (String) procedure.getOutputParameterValue("message_out");
                for (Object[] row : resultset) {
                    NvcTblOaProveedoresH p = new NvcTblOaProveedoresH();
                    int index = -1;
                    p.setIdProveedor(new BigDecimal(row[++index].toString()));
                    p.setNombreProveedor(row[++index] != null ? row[index].toString() : "");
                    proveedores.add(p);
                }
            }
            log.debug(out);
        } catch (NumberFormatException e) {
            log.debug(e.getLocalizedMessage(), e);
            log.error("error en : getRequisFad :" + e.getMessage());
        }
        return proveedores;
    }

    @Override
    public List<Usuario> getUserFadByTipo(String uens, String tipo) {
        List<Usuario> usuarios = new ArrayList();
        List<Object[]> resultset;
        String out = "OK";
        try {
            StoredProcedureQuery procedure = em.createStoredProcedureQuery("NVC_PKG_SPX_FAD.get_user_fad_tipo");
            procedure.registerStoredProcedureParameter("p_uens", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_tipo", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("message_out", String.class, ParameterMode.OUT);
            procedure.registerStoredProcedureParameter("out_cursor", void.class, ParameterMode.REF_CURSOR);
            procedure.setParameter("p_uens", uens);
            procedure.setParameter("p_tipo", tipo);
            if (procedure.execute()) {
                resultset = (List<Object[]>) procedure.getResultList();
                out = (String) procedure.getOutputParameterValue("message_out");
                for (Object[] row : resultset) {
                    Usuario u = new Usuario();
                    int index = -1;
                    u.setId(row[++index] != null ? row[index].toString() : "");
                    u.setNombreUsuario(row[++index] != null ? row[index].toString() : "");
                    usuarios.add(u);
                }
            }
            log.debug(out);
        } catch (NumberFormatException e) {
            log.debug(e.getLocalizedMessage(), e);
            log.error("error en : getUserFadByTipo :" + e.getMessage());
        }
        return usuarios;
    }

    @Override
    public List<RequisicionFad> findFadByFilters(RequisicionFadRequest request) {
        List<RequisicionFad> listaRequis = new ArrayList();
        List<Object[]> resultset;
        String out = "OK";
        try {
            StoredProcedureQuery procedure = em.createStoredProcedureQuery("NVC_PKG_SPX_FAD.consulta_requis_fad_filtros");
            procedure.registerStoredProcedureParameter("p_uens", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_fecha_ini", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_fecha_fin", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_fads", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_id_idioma", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_id_proveedor", Integer.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_id_requisicion", Integer.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_id_oc", Integer.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_requisitores", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_compradores", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_autores", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("message_out", String.class, ParameterMode.OUT);
            procedure.registerStoredProcedureParameter("out_cursor", void.class, ParameterMode.REF_CURSOR);
            procedure.setParameter("p_uens", listToString(request.getUen()));
            procedure.setParameter("p_fecha_ini", request.getFechaIni());
            procedure.setParameter("p_fecha_fin", request.getFechaFin());
            procedure.setParameter("p_fads", request.getFoliosFad());
            procedure.setParameter("p_id_idioma", request.getIdIdioma());
            procedure.setParameter("p_id_proveedor", request.getProveedor() != null ? request.getProveedor().getIdProveedor().intValue() : null);
            procedure.setParameter("p_id_requisicion", request.getIdRequisicion());
            procedure.setParameter("p_id_oc", request.getIdOc());
            procedure.setParameter("p_requisitores", userToListString(request.getRequisitores()));
            procedure.setParameter("p_compradores", userToListString(request.getCompradores()));
            procedure.setParameter("p_autores", userToListString(request.getAutores()));
            if (procedure.execute()) {
                resultset = (List<Object[]>) procedure.getResultList();
                out = (String) procedure.getOutputParameterValue("message_out");
                for (Object[] row : resultset) {
                    RequisicionFad rf = new RequisicionFad();
                    int index = -1;
                    rf.setIdUen(Integer.parseInt(row[++index].toString()));
                    rf.setNombreUen(row[++index] != null ? row[index].toString() : "");
                    rf.setIdFad(Integer.parseInt(row[++index].toString()));
                    rf.setFechaCreacion(row[++index] != null ? row[index].toString() : "");
                    rf.setProyMatServ(row[++index] != null ? row[index].toString() : "");
                    rf.setNombreProveedor(row[++index] != null ? row[index].toString() : "");
                    rf.setMonto(Double.parseDouble(row[++index].toString()));
                    rf.setMoneda(row[++index] != null ? row[index].toString() : "");
                    rf.setIdRequisicion(Integer.parseInt(row[++index].toString()));
                    rf.setIdPartida(Integer.parseInt(row[++index].toString()));
                    rf.setDescripcionLinea(row[++index] != null ? row[index].toString() : "");
                    rf.setRazonFad(row[++index] != null ? row[index].toString() : "");
                    rf.setDetalleFad(row[++index] != null ? row[index].toString() : "");
                    rf.setComentariosAdicionales(row[++index] != null ? row[index].toString() : "");
                    rf.setServicioRealizado(row[++index] != null ? ("1".equals(row[index].toString())) : false);
                    rf.setIdOrdenCompra(row[++index] != null ? Integer.parseInt(row[index].toString()) : null);
                    rf.setNombreRequisitor(row[++index] != null ? row[index].toString() : "");
                    rf.setNombreComprador(row[++index] != null ? row[index].toString() : "");
                    rf.setNombreAutor(row[++index] != null ? row[index].toString() : "");
                    listaRequis.add(rf);
                }
            }
            log.debug(out);
        } catch (NumberFormatException e) {
            log.debug(e.getLocalizedMessage(), e);
            log.error("error en : findFadByFilters :" + e.getMessage());
        }
        return listaRequis;
    }

    @Override
    public ByteArrayInputStream exportToExcel(RequisicionFadRequest request, Locale locale) {
        LOCALE = locale;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            Workbook workbook = new XSSFWorkbook();
            List<RequisicionFad> datos = this.findFadByFilters(request);
            String[] columns = {messages.getMessage("uen", null, LOCALE),
                messages.getMessage("folio_fad", null, LOCALE),
                messages.getMessage("fecha_creacion", null, LOCALE),
                messages.getMessage("proy_mat_serv", null, LOCALE),
                messages.getMessage("lbl_linea_proveedor", null, LOCALE),
                messages.getMessage("monto", null, LOCALE),
                messages.getMessage("lbl_moneda", null, LOCALE),
                messages.getMessage("requisicion", null, LOCALE),
                messages.getMessage("linea", null, LOCALE),
                messages.getMessage("desc_linea", null, LOCALE),
                messages.getMessage("razon_fad", null, LOCALE),
                messages.getMessage("detalle_fad", null, LOCALE),
                messages.getMessage("com_adicional", null, LOCALE),
                messages.getMessage("serv_realizado", null, LOCALE),
                messages.getMessage("orden_compra", null, LOCALE),
                messages.getMessage("requisitor", null, LOCALE),
                messages.getMessage("comprador", null, LOCALE),
                messages.getMessage("autor", null, LOCALE)};
            Sheet sheetDatosTabla = workbook.createSheet(messages.getMessage("lbl_hoja_1", null, LOCALE));
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 12);
            headerFont.setColor(IndexedColors.BLUE.getIndex());
            // Create a CellStyle with the font
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);
            // Create a Row
            Row headerRow = sheetDatosTabla.createRow(0);
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(headerCellStyle);
            }
            int i = 1;
            for (RequisicionFad rec : datos) {
                Row rowExel = sheetDatosTabla.createRow(i++);
                rowExel.createCell(0).setCellValue(rec.getNombreUen());
                rowExel.createCell(1).setCellValue(rec.getIdFad().toString());
                rowExel.createCell(2).setCellValue(rec.getFechaCreacion());
                rowExel.createCell(3).setCellValue(rec.getProyMatServ());
                rowExel.createCell(4).setCellValue(rec.getNombreProveedor());
                rowExel.createCell(5).setCellValue(String.valueOf(rec.getMonto()));
                rowExel.createCell(6).setCellValue(rec.getMoneda());
                rowExel.createCell(7).setCellValue(rec.getIdRequisicion().toString());
                rowExel.createCell(8).setCellValue(rec.getIdPartida().toString());
                rowExel.createCell(9).setCellValue(rec.getDescripcionLinea());
                rowExel.createCell(10).setCellValue(rec.getRazonFad());
                rowExel.createCell(11).setCellValue(rec.getDetalleFad());
                rowExel.createCell(12).setCellValue(rec.getComentariosAdicionales());
                rowExel.createCell(13).setCellValue(rec.isServicioRealizado() ? messages.getMessage("si", null, LOCALE) : messages.getMessage("no", null, LOCALE));
                rowExel.createCell(14).setCellValue(rec.getIdOrdenCompra() != null ? rec.getIdOrdenCompra().toString() : "");
                rowExel.createCell(15).setCellValue(rec.getNombreRequisitor());
                rowExel.createCell(16).setCellValue(rec.getNombreComprador());
                rowExel.createCell(17).setCellValue(rec.getNombreAutor());
            }
            for (int j = 0; j < columns.length; j++) {
                sheetDatosTabla.autoSizeColumn(j);
            }
            XSSFWorkbook workbook1q = ((XSSFWorkbook) workbook);
            workbook1q.lockStructure();
            workbook.write(out);
            workbook.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return new ByteArrayInputStream(out.toByteArray());
    }

    private String listToString(List list) {
        StringBuilder sb = new StringBuilder();
        String coma = ",";
        int c = -1;
        if (list != null && list.size() > 0) {
            for (Object o : list) {
                ++c;
                if ((c + 1) == list.size()) {
                    coma = "";
                }
                if (o instanceof UenWithDefault) {
                    sb.append(((UenWithDefault) o).getId()).append(coma);
                } else {
                    sb.append(o).append(coma);
                }
            }
        }
        return sb.toString();
    }

    private String userToListString(List<Usuario> list) {
        return list.stream().map(a -> String.valueOf(a.getId())).collect(Collectors.joining(","));
    }
}
