/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.contralor.service;

import com.metalsa.aprobacion.model.UsuarioUen;
import com.metalsa.aprobacion.repository.UsuarioUenRepository;
import com.metalsa.compradorCC.pojo.CompradorCcPojo;
import com.metalsa.contralor.model.CentroCostoProceso;
import com.metalsa.contralor.model.CentroCostoProcesoDetalle;
import com.metalsa.contralor.model.RequestBodyContralor;
import com.metalsa.contralor.repository.ProcesoRepository;
import com.metalsa.finanzas.model.AdjuntoPojo;
import com.metalsa.utils.TemplateSpxUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

/**
 *
 * @author juanangelmunozolvera
 * @param <T>
 * @param <ID>
 */
@Log4j
@Service
public class ConfiguracionContralorServiceImpl<T, ID extends Serializable> implements ConfiguracionContralorService  {
    @PersistenceContext
    private EntityManager em;
    
    private static Locale LOCALE;
    @Qualifier("application")
    @Autowired
    private MessageSource messages;
    
    @Autowired
    private ProcesoRepository procesoRepository;
    
    @Autowired
    private UsuarioUenRepository usuariosPorUen;
    
    @Override
    public ByteArrayInputStream getExcelConfiguracionResponsableDelegado(RequestBodyContralor request){
        LOCALE = new Locale(request.getLang());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            Workbook workbook = new XSSFWorkbook();
            // compradores sin repeticiones
            List<CompradorCcPojo> listUsersSin = new ArrayList();
            //

            List<Object[]> datos = this.getDatos(request);
            String[] columns = {messages.getMessage("ORGANIZATION_NAME", null, LOCALE),
                messages.getMessage("NOMBRE_REPONSABLE_PROCESO", null, LOCALE),
                messages.getMessage("NOMBRE_PROCESO", null, LOCALE),
                messages.getMessage("NOMBRE_REPONSABLE_SUBPROCESO", null, LOCALE),
                messages.getMessage("NOMBRE_SUBPROCESO", null, LOCALE),
                messages.getMessage("lbl_centro_costo", null, LOCALE),
                messages.getMessage("lbl_usuario", null, LOCALE),
                messages.getMessage("lbl_email", null, LOCALE),
                messages.getMessage("TIPO_DE_RELACION", null, LOCALE),
                messages.getMessage("TIPO_RESPONSABILIDAD", null, LOCALE),
                messages.getMessage("lbl_tipo_requisicion", null, LOCALE),
                messages.getMessage("MONTO_INICIAL", null, LOCALE),
                messages.getMessage("MONTO_FINAL", null, LOCALE),
                messages.getMessage("lbl_tipo_requisicion", null, LOCALE),
                messages.getMessage("MONTO_INICIAL", null, LOCALE),
                messages.getMessage("MONTO_FINAL", null, LOCALE)};
            Sheet sheetDatosTabla = workbook.createSheet(messages.getMessage("lbl_hoja_1", null, LOCALE));
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 14);
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
            int i= 1;
            for (Object[] rec: datos) {
                Row rowExel = sheetDatosTabla.createRow(i++);
                rowExel.createCell(0)
                        .setCellValue(rec[0]!=null?String.valueOf(rec[0]):"");
                rowExel.createCell(1)
                        .setCellValue(rec[1]!=null?String.valueOf(rec[1]):"");
                rowExel.createCell(2)
                        .setCellValue(rec[2]!=null?String.valueOf(rec[2]):"");
                rowExel.createCell(3)
                        .setCellValue(rec[3]!=null?String.valueOf(rec[3]):"");
                rowExel.createCell(4)
                        .setCellValue(rec[4]!=null?String.valueOf(rec[4]):"");
                rowExel.createCell(5)
                        .setCellValue(rec[5]!=null?String.valueOf(rec[5]):"");
                rowExel.createCell(6)
                        .setCellValue(rec[6]!=null?String.valueOf(rec[6]):"");
                rowExel.createCell(7)
                        .setCellValue(rec[7]!=null?String.valueOf(rec[7]):"");
                rowExel.createCell(8)
                        .setCellValue(rec[8]!=null?String.valueOf(rec[8]):"");
                rowExel.createCell(9)
                        .setCellValue(rec[9]!=null?String.valueOf(rec[9]):"");
                rowExel.createCell(10)
                        .setCellValue(rec[10]!=null?String.valueOf(rec[10]):"");
                rowExel.createCell(11)
                        .setCellValue(rec[11]!=null?String.valueOf(rec[11]):"");
                rowExel.createCell(12)
                        .setCellValue(rec[12]!=null?String.valueOf(rec[12]):"");
                rowExel.createCell(13)
                        .setCellValue(rec[13]!=null?String.valueOf(rec[13]):"");
                rowExel.createCell(14)
                        .setCellValue(rec[14]!=null?String.valueOf(rec[14]):"");
                rowExel.createCell(15)
                        .setCellValue(rec[15]!=null?String.valueOf(rec[15]):"");
            }
            for (int j = 0; j < columns.length; j++) {
                sheetDatosTabla.autoSizeColumn(j);
            }

            //FileOutputStream fileOut = new FileOutputStream("poi-generated-file.xlsx");
            XSSFWorkbook workbook1q = ((XSSFWorkbook) workbook);
            workbook1q.lockStructure();
            workbook.write(out);
            workbook.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
    
    private List<Object[]> getDatos(RequestBodyContralor request){
        List<Object[]> results = new ArrayList<>();
        try {
            results = em.
                    createNativeQuery("select \n" +
                    "        o.organization_name,\n" +
                    "        resp.nombre_usuario nombre_reponsable_proceso, \n" +
                    "        pp.nombre_proceso nombre_proceso , --pup.id_uen uen_proceso,\n" +
                    "        sub.nombre_usuario nombre_reponsable_subproceso,\n" +
                    "        ph.nombre_proceso nombre_subproceso --pu.id_uen uen_subproceso\n" +
                    "    , cch.codigo_cc || ' - ' || cch.nombre_cc centro_costo\n" +
                    "    ,ucc.nombre_usuario nombre_usuario, ucc.email \n" +
                    "    ,ccu.tipo_de_relacion, \n" +
                    "    case when ccu.id_proceso_uen is not null then 'Por Proceso' end tipo_responsabilidad\n" +
                    "    , mi.tipo_requisicion tipo_requisicion_interno, mi.monto_inicial monto_inicial_interno, mi.monto_final monto_final_interno\n" +
                    "    , me.tipo_requisicion tipo_requisicion_externo, me.monto_inicial monto_inicial_externo, me.monto_final monto_final_externo\n" +
                    " from compras_as.nvc_tbl_proceso_uen pup\n" +
                    "    left join compras_as.nvc_tbl_proceso_uen pu on pu.id_proceso_padre = pup.id_proceso_uen\n" +
                    "    join compras_as.nvc_tbl_proceso pp on pp.id_proceso = pup.id_proceso\n" +
                    "    join compras_as.nvc_tbl_proceso ph on ph.id_proceso = pu.id_proceso\n" +
                    "    join compras_as.nvc_tbl_proceso_uen_cc puch on puch.id_proceso_uen = pu.id_proceso_uen\n" +
                    "    join compras_as.nvc_vm_oa_cc cch on cch.id_cc = puch.id_cc\n" +
                    "        and cch.lenguaje = 'ESA' and cch.id_uen = pu.id_uen\n" +
                    "    join compras_as.usuario resp on resp.id_usuario = pup.responsable\n" +
                    "    join compras_as.usuario sub on sub.id_usuario = pu.responsable\n" +
                    "    join compras_as.cc_por_usuario ccu on ccu.id_cc = cch.id_cc \n" +
                    "        and ccu.id_uen = pu.id_uen\n" +
                    "        and ccu.tipo_de_relacion in ('Resp', 'Del')\n" +
                    "   join compras_as.montos_por_nivel mi on mi.id_nivel = ccu.nivel_rqi\n" +
                    "        and mi.id_uen = ccu.id_uen\n" +
                    "       and mi.tipo_requisicion = 'INTERNA'\n" +
                    "    join compras_as.montos_por_nivel me on me.id_nivel = ccu.nivel_rqe\n" +
                    "        and me.id_uen = ccu.id_uen\n" +
                    "        and me.tipo_requisicion = 'EXTERNA' \n" +
                    "    join compras_as.oa_uens o on o.organization_id = pu.id_uen\n" +
                    "    join usuario ucc on ucc.id_usuario = ccu.id_usuario\n" +
                    " where 1 = 1\n" +
                    " and pu.id_uen = " + request.getUen() +
                    " order by o.organization_id, pp.nombre_proceso, ph.nombre_proceso, cch.codigo_cc || ' - ' || cch.nombre_cc")
                    .getResultList();
        } catch (Exception e) {
            log.error("error en : getBuyerCc :" + e.getMessage());
        }
        return results;
    }
    
    /**
     *
     * @param request
     * @return
     */
    @Override
    public ByteArrayInputStream creaPlantillaCc(RequestBodyContralor request){
        LOCALE = new Locale(request.getIdioma());
        ByteArrayInputStream in = null;
        Map<String, List> infoCombosDTO = new HashMap<>();
        try {
            System.out.println("Antes de buscar procesos: " + new Date());
            List<CentroCostoProceso> ccProcesoList = this.procesoRepository.findCostsCenterProcess(request);
            System.out.println("Antes de buscar detalle: " + new Date());
            List<CentroCostoProcesoDetalle> listCCDet = this.procesoRepository.findDetailCC_UEN(request.getUen());
            System.out.println("armando mapa: " + new Date());
            Map<Long, List<CentroCostoProcesoDetalle>> mapResult = listCCDet.stream().collect(Collectors.groupingBy(CentroCostoProcesoDetalle::getIdCC));
            System.out.println("asignando las listas del mapa a procesos: " + new Date());
            for (CentroCostoProceso cc : ccProcesoList) {
                cc.setLineas(mapResult.get(cc.getIdCC()));
            }
            System.out.println("buscando los usuarios: " + new Date());
            List<UsuarioUen> usuarios = usuariosPorUen.findAllByIdUenOrderByIdUsuario(request.getUen().longValue());
            infoCombosDTO.put("usuarios", usuarios);
            System.out.println("armando el excel: " + new Date());
            in = TemplateSpxUtil.loadInfoTemplateConfRespDelCC(ccProcesoList, messages, LOCALE, infoCombosDTO);

        } catch (Throwable t) {
            t.printStackTrace();
        }

        return in;
    }

    @Override
    public List<CentroCostoProceso> uploadPlantilla(AdjuntoPojo data) throws Throwable{
        log.debug("Entro uploadPlantilla");
        InputStream excelFile;
        List<CentroCostoProceso> datosTabla = new ArrayList<>();
        try {
            excelFile = new ByteArrayInputStream(data.getFile());
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet sheetDatosTabla = workbook.getSheetAt(0);
            String sheetname = sheetDatosTabla.getSheetName();
            log.debug("nombre gato"+sheetname);
            Iterator<Row> rowIterator = sheetDatosTabla.rowIterator();
            rowIterator.next();  // saltamos las cabeceras
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                CentroCostoProceso rowDatosTabla = new CentroCostoProceso();
                List<String[]> campos = new ArrayList();
                String[] responsableCC = cadenaOriginal(row.getCell(1)!=null?row.getCell(1).getStringCellValue():null, "-");
                String[] responsableProceso = cadenaOriginal(row.getCell(3)!=null?row.getCell(3).getStringCellValue():null, "-");
                String[] responsableSubproceso = cadenaOriginal(row.getCell(5)!=null?row.getCell(5).getStringCellValue():null, "-");
                rowDatosTabla.setIdCC(Double.valueOf(row.getCell(TemplateSpxUtil.CONTROLLER_COLUMN_HIDE).getNumericCellValue()).longValue());
                if(responsableCC != null){
                    rowDatosTabla.setIdResponsableCC(responsableCC[0]);
                    if(responsableCC.length>1){
                        rowDatosTabla.setResponsableCC(responsableCC[1].trim());
                        if(responsableCC.length>2){
                            rowDatosTabla.setResponsableCC(responsableCC[1].trim().concat(" ").concat(responsableCC[2].trim()));
                        }
                    }
                }else{
                    rowDatosTabla.setIdResponsableCC(null);
                    rowDatosTabla.setResponsableCC(null);
                }
                if(responsableProceso != null){
                    rowDatosTabla.setIdResponsableProceso(responsableProceso[0]);
                    if(responsableProceso.length>1){
                        rowDatosTabla.setResponsableProceso(responsableProceso[1].trim());
                        if(responsableProceso.length>2){
                            rowDatosTabla.setResponsableProceso(responsableProceso[1].trim().concat(" ").concat(responsableProceso[2].trim()));
                        }
                    }
                }else{
                    rowDatosTabla.setIdResponsableProceso(null);
                    rowDatosTabla.setResponsableProceso(null);
                }
                if(responsableSubproceso != null){
                    rowDatosTabla.setIdResponsableSubProceso(responsableSubproceso[0]);
                    if(responsableSubproceso.length>1){
                        rowDatosTabla.setResponsableSubProceso(responsableSubproceso[1].trim());
                        if(responsableSubproceso.length>2){
                            rowDatosTabla.setResponsableSubProceso(responsableSubproceso[1].trim().concat(" ").concat(responsableSubproceso[2].trim()));
                        }
                    }
                }else{
                    rowDatosTabla.setIdResponsableSubProceso(null);
                    rowDatosTabla.setResponsableSubProceso(null);
                }
                datosTabla.add(rowDatosTabla);
            }
        } catch (Throwable ex) {
            throw ex;
        }
        return datosTabla;
    }
    
    private String[] cadenaOriginal(String cadena, String pivote) {
        String[] campo = null;
        if(cadena!=null){
            campo = cadena.split(pivote);
            campo[0] = campo[0].replace("[", "").replace("]", "").trim();
        }
        return campo;
    }

}
