package com.metalsa.admin.repository;

import com.metalsa.admin.model.NvcTblDocumento;
import com.metalsa.admin.model.ProveedorDirigido;
import com.metalsa.admin.model.ProveedorDirigidoPorTipo;
import com.metalsa.admin.pojo.ExcelProvDirigidosPojo;
import com.metalsa.admin.pojo.ProveedorDirigidoRequest;
import com.metalsa.finanzas.model.AdjuntoPojo;
import com.metalsa.finanzas.repository.UploadFileRepository;
import com.metalsa.matriz.pagos.dto.MatrizPagosDTO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import lombok.Getter;
import lombok.Setter;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author miguel.rdz
 * @param <T>
 * @param <ID>
 */
@Log4j
@Service
public class ProvDirigidosServiceImpl<T, ID extends Serializable> implements ProvDirigidosService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private UploadFileRepository UploadFileRepository;

    @Autowired
    private NvcTblDocumentoRepository nvcTblDocumentoRepository;

    @Autowired
    private TipoProvDirigidosRepository tipoProvDirigidosRepository;

    @Getter
    @Setter
    @Value("${ftp.host}")
    private String host;

    @Getter
    @Setter
    @Value("${ftp.port}")
    private Integer port;

    @Getter
    @Setter
    @Value("${ftp.user}")
    private String user;

    @Getter
    @Setter
    @Value("${ftp.password}")
    private String pass;

    @Getter
    @Setter
    @Value("${pathFtp}")
    private String path;

    @Override
    public boolean insertProvDirigido(ProveedorDirigidoRequest req) {
        ProveedorDirigido p = req.getProveedor();
        String out = "OK";
        Integer provDirigidoId;
        List<NvcTblDocumento> doc_;
        try {
            String tiposProveedor = p.getTiposProvDirigido().stream()
                    .map(a -> String.valueOf(a.getIdTipoProveedor()))
                    .collect(Collectors.joining(","));
            StoredProcedureQuery procedure = em.createStoredProcedureQuery("NVC_PKG_PROVEEDORES_DIRIGIDOS.INSERT_PROV_DIRIGDO");
            procedure.registerStoredProcedureParameter("P_ID_PROVEEDOR", Integer.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("P_ID_UEN", Integer.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("P_MARCA", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("P_TIPOS_PROV", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("P_ID_USUARIO", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("P_OUT", String.class, ParameterMode.OUT);
            procedure.registerStoredProcedureParameter("V_ID_PROV_DIRIGIDO", Integer.class, ParameterMode.OUT);
            procedure.setParameter("P_ID_PROVEEDOR", p.getIdProveedor());
            procedure.setParameter("P_ID_UEN", p.getIdUen());
            procedure.setParameter("P_MARCA", p.getMarca());
            procedure.setParameter("P_TIPOS_PROV", tiposProveedor);
            procedure.setParameter("P_ID_USUARIO", req.getIdUsuario());
            procedure.execute();
            out = procedure.getOutputParameterValue("P_OUT").toString();
            provDirigidoId = (Integer) procedure.getOutputParameterValue("V_ID_PROV_DIRIGIDO");
            log.debug("P_OUT->" + out);
            persisteAdjuntos(req, provDirigidoId, p.getIdUen());
            return "OK".equals(out);
        } catch (Exception e) {
            log.debug(e.getLocalizedMessage(), e);
            log.error("error en : insertProvDirigido :" + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateProvDirigido(ProveedorDirigidoRequest req) {
        ProveedorDirigido p = req.getProveedor();
        String out = "OK";
        try {
            String tiposProveedor = p.getTiposProvDirigido().stream()
                    .map(a -> String.valueOf(a.getIdTipoProveedor()))
                    .collect(Collectors.joining(","));
            StoredProcedureQuery procedure = em.createStoredProcedureQuery("NVC_PKG_PROVEEDORES_DIRIGIDOS.UPDATE_PROV_DIRIGDO");
            procedure.registerStoredProcedureParameter("P_ID_PROV_DIRIGIDO", Integer.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("P_MARCA", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("P_TIPOS_PROV", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("P_ID_USUARIO", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("P_FROM_INSERT", Integer.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("P_OUT", String.class, ParameterMode.OUT);
            procedure.setParameter("P_ID_PROV_DIRIGIDO", p.getIdProvDirigido());
            procedure.setParameter("P_MARCA", p.getMarca());
            procedure.setParameter("P_TIPOS_PROV", tiposProveedor);
            procedure.setParameter("P_ID_USUARIO", req.getIdUsuario());
            procedure.setParameter("P_FROM_INSERT", 0);
            procedure.execute();
            out = procedure.getOutputParameterValue("P_OUT").toString();
            log.debug("P_OUT->" + out);
            persisteAdjuntos(req, p.getIdProvDirigido(), p.getIdUen());
            return "OK".equals(out);
        } catch (Exception e) {
            log.debug(e.getLocalizedMessage(), e);
            log.error("error en : updateProvDirigido :" + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteProvDirigido(ProveedorDirigidoRequest req) {
        ProveedorDirigido p = req.getProveedor();
        String out = "OK";
        try {
            StoredProcedureQuery procedure = em.createStoredProcedureQuery("NVC_PKG_PROVEEDORES_DIRIGIDOS.DELETE_PROV_DIRIGDO");
            procedure.registerStoredProcedureParameter("P_ID_PROV_DIRIGIDO", Integer.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("P_ID_USUARIO", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("P_OUT", String.class, ParameterMode.OUT);
            procedure.setParameter("P_ID_PROV_DIRIGIDO", p.getIdProvDirigido());
            procedure.setParameter("P_ID_USUARIO", req.getIdUsuario());
            procedure.execute();
            out = procedure.getOutputParameterValue("P_OUT").toString();
            log.debug("P_OUT->" + out);
            return "OK".equals(out);
        } catch (Exception e) {
            log.debug(e.getLocalizedMessage(), e);
            log.error("error en : deleteProvDirigido :" + e.getMessage());
            return false;
        }
    }

    private String getNombreFtp(String nombre) {
        String extension = "";

        try {
            int i = nombre.lastIndexOf('.');
            if (i > 0) {
                extension = nombre.substring(i + 1);
            }
            nombre = nombre.substring(0, i);
        } catch (Exception e) {
            nombre = "";
            log.error("getNombreFtp", e);
        }
        return nombre + "-" + (new Date().getTime() + "." + extension);
    }

    @Override
    public List<NvcTblDocumento> getAdjuntosProvDirigidos(Long provDirId, Integer idUen) {
        return nvcTblDocumentoRepository.findByProvDir(provDirId, idUen);
    }

    private String persisteAdjuntos(ProveedorDirigidoRequest req, Integer provDirigidoId, Integer idUen) {
        try {
            for (AdjuntoPojo file : req.getDataFile()) {
                NvcTblDocumento doc = new NvcTblDocumento();
                String path = this.path;
                String nombreArchivoFtp = getNombreFtp(file.getNombreArchivo());
                if (path != null) {
                    path = path.replace("[id_uen]", idUen.toString());
                    path = path + provDirigidoId + "/";
                    UploadFileRepository.upload(new ByteArrayInputStream(file.getFile()), path, nombreArchivoFtp, host, port, user, pass);
                    doc.setNombreArchivo(file.getNombreArchivo());
                    doc.setNombreArchivoFtp(nombreArchivoFtp);
                    //doc.setExtensionArchivo();
                    doc.setFechaCreacion(new Date());
                    doc.setUsuarioCreacion(req.getIdUsuario());
                    doc.setActivo('1');
                    doc.setUbicacionFtp(path);
                    doc.setProvDirId(provDirigidoId.longValue());
                    doc.setIdUen(idUen);
                    nvcTblDocumentoRepository.save(doc);
                }
            }
            return "OK";
        } catch (Exception e) {
            log.error("error", e);
            return "!OK";
        }
    }

    @Override
    public ByteArrayInputStream getReporteExcel(List<ProveedorDirigido> allProvs, ExcelProvDirigidosPojo excelProvDirigidosPojo) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Workbook workbook = new XSSFWorkbook();
        String nombreHoja;
        if (excelProvDirigidosPojo.getIdioma() != null && excelProvDirigidosPojo.getIdioma().equals("ESA")) {
            nombreHoja = "Proveedor Dirigido_";
        } else {
            nombreHoja = "Directed Suppliers_";
        }
        Sheet sheetDatosTabla = workbook.createSheet(nombreHoja + excelProvDirigidosPojo.getIdUen());
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.BLUE.getIndex());
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        // Create a Row
        Row headerRow = sheetDatosTabla.createRow(0);
        List<ProveedorDirigidoPorTipo> allTipos = tipoProvDirigidosRepository.getAllTipos(excelProvDirigidosPojo.getIdioma());
        int index = creaCell(headerCellStyle, headerRow, excelProvDirigidosPojo.getColumnas(), allTipos, excelProvDirigidosPojo.getColumnasPart2());
        int contador = 0;
        DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");

        for (ProveedorDirigido item : allProvs) {
            int row = -1;
            contador++;
            Row rowExel = sheetDatosTabla.createRow(contador);
            rowExel.createCell(++row).setCellValue(item.getNombreProveedor());
            rowExel.createCell(++row).setCellValue(item.getMarca());
            List<ProveedorDirigidoPorTipo> tipos = tipoProvDirigidosRepository.getByIdProvDirigido(item.getIdProvDirigido(), excelProvDirigidosPojo.getIdioma());

            for (ProveedorDirigidoPorTipo tipo : tipos) {
                int cont = 0;
                boolean flag = false;
                while (headerRow.getCell(cont) != null) {
                    if (headerRow.getCell(cont).toString().equals(tipo.getDescripcion())) {
                        flag = true;
                        break;
                    }
                    cont++;
                }
                if (flag) {
                    rowExel.createCell(cont).setCellValue("x");
                }
            }
            row = excelProvDirigidosPojo.getColumnas().size() + allTipos.size();
            String fecha = item.getFechaCreacion() != null ? formatter.format(item.getFechaCreacion()) : "";
            rowExel.createCell(row).setCellValue(fecha);
        }
        for (int i = 0; i < index; i++) {
            sheetDatosTabla.autoSizeColumn(i);
        }
        try {
            workbook.write(out);
            workbook.close();
        } catch (IOException ex) {
            log.error("Error al generar el archivo", ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    private Integer creaCell(CellStyle headerCellStyle, Row headerRow, List<String> columns, List<ProveedorDirigidoPorTipo> allTipos, List<String> columnsPart2) {
        for (int i = 0; i < columns.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns.get(i));
            cell.setCellStyle(headerCellStyle);
        }
        int index = columns.size();
        for (int i = 0; i < allTipos.size(); i++) {
            Cell cell = headerRow.createCell(index);
            cell.setCellValue(allTipos.get(i).getDescripcion());
            cell.setCellStyle(headerCellStyle);
            index++;
        }

        for (int i = 0; i < columnsPart2.size(); i++) {
            Cell cell = headerRow.createCell(index);
            cell.setCellValue(columnsPart2.get(i));
            cell.setCellStyle(headerCellStyle);
        }
        index++;
        return index;

    }
}
