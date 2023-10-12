package com.metalsa.contralor.repository;

import com.metalsa.aprobacion.model.CentroCosto;
import com.metalsa.aprobacion.model.UsuarioUen;
import com.metalsa.contralor.model.AdjuntoPojoP;
import com.metalsa.contralor.model.Procesos;
import com.metalsa.contralor.model.ProcessUenCC;
import com.metalsa.contralor.model.ReponseProcesos;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import lombok.extern.log4j.Log4j;
import org.apache.commons.codec.DecoderException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Lorena
 */
@Log4j
public class CargaMasivaCRepository {

    public CargaMasivaCRepository() {
    }

    public ReponseProcesos procesarExcelTransaccion(AdjuntoPojoP data) {
        ReponseProcesos result = new ReponseProcesos();
        String message = "Exitoso";
        String type = "";
        List procesosList = new ArrayList<Procesos>();
        Map<String, Procesos> mapProcesos = new HashMap<>();
        try {
            InputStream excelFile = new ByteArrayInputStream(data.getFile());
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            Row headerUen = iterator.next();  // saltamos las cabeceras uen
            iterator.next();  // saltamos las cabeceras
            Procesos subprocesos = new Procesos();
            Procesos procesosGral = new Procesos();
            Date fechaNow = new Date();
            /*Obtener el tipo de plantilla Nueva o editar*/
            if (headerUen.getCell(80).getNumericCellValue() == data.getUen()) {
                type = headerUen.getCell(81).getStringCellValue();
                if (type.equals("Editar")) {
                    while (iterator.hasNext()) {
                        String cc = "";
                        String ccCodigo = "";
                        String proceso = "";
                        int procesoId = 0;
                        String responsableP = "";
                        String responsablePId = "";
                        String subproceso = "";
                        int subprocesoId = 0;
                        String responsableS = "";
                        String responsableSId = "";
                        List<Procesos> subprocesosList = new ArrayList<Procesos>();
                        List<String> ccGral = new ArrayList<String>();
                        Row currentRow = iterator.next();
                        try {
                            if ((currentRow.getCell(0) != null
                                    || currentRow.getCell(0).getCellTypeEnum() != CellType.BLANK) &&
                                            (currentRow.getCell(1) != null
                                    || currentRow.getCell(1).getCellTypeEnum() != CellType.BLANK)) {
                                
                           
                            if (currentRow.getCell(0) == null) {
                                proceso = "";
                            } else if (currentRow.getCell(0).getCellTypeEnum() == CellType.STRING
                                    || currentRow.getCell(0).getCellTypeEnum() != CellType.BLANK) {
                                proceso =currentRow.getCell(0).getStringCellValue().contains("|") ? currentRow.getCell(0).getStringCellValue().split("\\|")[1] : "";
                                procesoId=Integer.valueOf(currentRow.getCell(0).getStringCellValue().contains("|") ? currentRow.getCell(0).getStringCellValue().split("\\|")[0] : "0");
                            }
                            if (currentRow.getCell(1) == null) {
                                responsableP = "";
                                responsablePId = "";
                            } else if (currentRow.getCell(1).getCellTypeEnum() == CellType.STRING
                                    || currentRow.getCell(1).getCellTypeEnum() != CellType.BLANK) {
                                responsableP = currentRow.getCell(1).getStringCellValue().contains("|") ? currentRow.getCell(1).getStringCellValue().split("\\|")[1] : "";
                                responsablePId = currentRow.getCell(1).getStringCellValue().contains("|") ? currentRow.getCell(1).getStringCellValue().split("\\|")[0] : "";
                            }

                            if (currentRow.getCell(2) == null) {
                                subproceso = "";
                            } else if (currentRow.getCell(2).getCellTypeEnum() == CellType.STRING
                                    || currentRow.getCell(2).getCellTypeEnum() != CellType.BLANK) {
                                subproceso =currentRow.getCell(2).getStringCellValue().contains("|") ? currentRow.getCell(2).getStringCellValue().split("\\|")[1] : "";
                                subprocesoId=Integer.valueOf(currentRow.getCell(2).getStringCellValue().contains("|") ? currentRow.getCell(2).getStringCellValue().split("\\|")[0] : "0");
                            }

                            if (currentRow.getCell(3) == null) {
                                responsableS = "";
                                responsableSId = "";
                            } else if (currentRow.getCell(3).getCellTypeEnum() == CellType.STRING
                                    || currentRow.getCell(3).getCellTypeEnum() != CellType.BLANK) {
                                responsableS = currentRow.getCell(3).getStringCellValue().contains("|") ? currentRow.getCell(3).getStringCellValue().split("\\|")[1] : "";
                                responsableSId = currentRow.getCell(3).getStringCellValue().contains("|") ? currentRow.getCell(3).getStringCellValue().split("\\|")[0] : "";
                            }

                            if (currentRow.getCell(4) == null) {
                                cc = "";
                                ccCodigo = "";
                            } else if (currentRow.getCell(4).getCellTypeEnum() == CellType.STRING
                                    || currentRow.getCell(4).getCellTypeEnum() != CellType.BLANK) {
                                cc = currentRow.getCell(4).getStringCellValue().contains("|") ? currentRow.getCell(4).getStringCellValue().split("\\|")[1] : "";
                                ccCodigo = currentRow.getCell(4).getStringCellValue().contains("|") ? currentRow.getCell(4).getStringCellValue().split("\\|")[0] : "";
                            }
                            boolean verificar = false;
                            if (mapProcesos.containsKey(proceso)) {
                                procesosGral = (Procesos) mapProcesos.get(proceso);
                                if(procesosGral.getSubprocesos().size()>0){
                                    subprocesosList = procesosGral.getSubprocesos();
                                }else{
                                    subprocesosList = new ArrayList<Procesos>();
                                }
                                for (int i = 0; i < subprocesosList.size(); i++) {
                                    subprocesos = subprocesosList.get(i);
                                    if (subprocesos.getNombreProceso().equals(subproceso.trim())) {
                                        if (!ccCodigo.equals("") && !cc.equals("")) {
                                            ccGral = subprocesos.getCc();
                                            ccGral.add(ccCodigo);
                                            subprocesos.setCc(ccGral);
                                        }
                                        verificar = true;
                                        break;
                                    }
                                }
                                if (verificar == false) {
                                    subprocesos = new Procesos();
                                    ccGral = new ArrayList<String>();
                                    /*Subprocesos*/
                                    if (!subproceso.equals("")) {
                                        subprocesos.setNombreProceso(subproceso);
                                        subprocesos.setNombreProcesoEsa(subproceso);
                                        subprocesos.setNombreProcesoPtb(subproceso);
                                        subprocesos.setNombreProcesoUs(subproceso);
                                    }
                                    if (subprocesoId!=0) {
                                        subprocesos.setIdProceso(subprocesoId);
                                    }
                                    if (!responsableSId.equals("")) {
                                        subprocesos.setIdResponsable(responsableSId);
                                    }
                                    if (!responsableS.equals("")) {
                                        subprocesos.setNombreResponsable(responsableS);
                                    }
                                    subprocesos.setFechaCreacionPro(fechaNow);
                                    if (!ccCodigo.equals("") && !cc.equals("")) {
                                        ccGral.add(ccCodigo);
                                        subprocesos.setCc(ccGral);
                                    }
                                    if (!subproceso.equals("")) {
                                        subprocesosList.add(subprocesos);
                                        procesosGral.setSubprocesos(subprocesosList);
                                        mapProcesos.put(proceso, procesosGral);
                                    }
                                }

                            } else {
                                procesosGral = new Procesos();
                                subprocesos = new Procesos();
                                subprocesosList = new ArrayList<Procesos>();
                                ccGral = new ArrayList<String>();
                                /*Procesos*/
                                if (!proceso.equals("")) {
                                    procesosGral.setNombreProceso(proceso);
                                    procesosGral.setNombreProcesoEsa(proceso);
                                    procesosGral.setNombreProcesoPtb(proceso);
                                    procesosGral.setNombreProcesoUs(proceso);
                                }
                                if (procesoId!=0) {
                                    procesosGral.setIdProceso(procesoId);
                                }
                                if (!responsablePId.equals("")) {
                                    procesosGral.setIdResponsable(responsablePId);
                                }
                                if (!responsableP.equals("")) {
                                    procesosGral.setNombreResponsable(responsableP);
                                }
                                procesosGral.setFechaCreacionPro(fechaNow);
                                /*Subprocesos*/
                                if (!subproceso.equals("")) {
                                    subprocesos.setIdProceso(Integer.BYTES);
                                    subprocesos.setNombreProceso(subproceso);
                                    subprocesos.setNombreProcesoEsa(subproceso);
                                    subprocesos.setNombreProcesoPtb(subproceso);
                                    subprocesos.setNombreProcesoUs(subproceso);
                                }
                                if (subprocesoId!=0) {
                                    subprocesos.setIdProceso(subprocesoId);
                                }
                                
                                if (!responsableSId.equals("")) {
                                    subprocesos.setIdResponsable(responsableSId);
                                }
                                if (!responsableS.equals("")) {
                                    subprocesos.setNombreResponsable(responsableS);
                                }
                                subprocesos.setFechaCreacionPro(fechaNow);
                                if (!ccCodigo.equals("") && !cc.equals("")) {
                                    ccGral.add(ccCodigo);
                                    subprocesos.setCc(ccGral);
                                }
                                if ((!proceso.equals("")) && (!subproceso.equals("")  || !responsableSId.equals(""))) {
                                    subprocesosList.add(subprocesos);
                                    procesosGral.setSubprocesos(subprocesosList);
                                    mapProcesos.put(proceso, procesosGral);
                                }
                            }
                            }
                        } catch (Exception ex) {
                            log.debug(ex.getMessage(), ex);
                        }
                    }
                } else if (type.equals("Nuevo")) {
                    while (iterator.hasNext()) {
                        String cc = "";
                        String ccCodigo = "";
                        String proceso = "";
                        int procesoId = 0;
                        String responsableP = "";
                        String responsablePId = "";
                        String subproceso = "";
                        int subprocesoId = 0;
                        String responsableS = "";
                        String responsableSId = "";
                        List<Procesos> subprocesosList = new ArrayList<Procesos>();
                        List<String> ccGral = new ArrayList<String>();
                        Row currentRow = iterator.next();
                        try {
                            if ((currentRow.getCell(0) != null
                                    || currentRow.getCell(0).getCellTypeEnum() != CellType.BLANK) &&
                                            (currentRow.getCell(1) != null
                                    || currentRow.getCell(1).getCellTypeEnum() != CellType.BLANK)) {
                                
                           
                            if (currentRow.getCell(0) == null) {
                                proceso = "";
                            } else if (currentRow.getCell(0).getCellTypeEnum() == CellType.STRING
                                    || currentRow.getCell(0).getCellTypeEnum() != CellType.BLANK) {
                                proceso = currentRow.getCell(0).getStringCellValue();
                            }
                            if (currentRow.getCell(1) == null) {
                                responsableP = "";
                                responsablePId = "";
                            } else if (currentRow.getCell(1).getCellTypeEnum() == CellType.STRING
                                    || currentRow.getCell(1).getCellTypeEnum() != CellType.BLANK) {
                                responsableP = currentRow.getCell(1).getStringCellValue().contains("|") ? currentRow.getCell(1).getStringCellValue().split("\\|")[1] : "";
                                responsablePId = currentRow.getCell(1).getStringCellValue().contains("|") ? currentRow.getCell(1).getStringCellValue().split("\\|")[0] : "";
                            }

                            if (currentRow.getCell(2) == null) {
                                subproceso = "";
                            } else if (currentRow.getCell(2).getCellTypeEnum() == CellType.STRING
                                    || currentRow.getCell(2).getCellTypeEnum() != CellType.BLANK) {
                                subproceso = currentRow.getCell(2).getStringCellValue();
                            }

                            if (currentRow.getCell(3) == null) {
                                responsableS = "";
                                responsableSId = "";
                            } else if (currentRow.getCell(3).getCellTypeEnum() == CellType.STRING
                                    || currentRow.getCell(3).getCellTypeEnum() != CellType.BLANK) {
                                responsableS = currentRow.getCell(3).getStringCellValue().contains("|") ? currentRow.getCell(3).getStringCellValue().split("\\|")[1] : "";
                                responsableSId = currentRow.getCell(3).getStringCellValue().contains("|") ? currentRow.getCell(3).getStringCellValue().split("\\|")[0] : "";
                            }

                            if (currentRow.getCell(4) == null) {
                                cc = "";
                                ccCodigo = "";
                            } else if (currentRow.getCell(4).getCellTypeEnum() == CellType.STRING
                                    || currentRow.getCell(4).getCellTypeEnum() != CellType.BLANK) {
                                cc = currentRow.getCell(4).getStringCellValue().contains("|") ? currentRow.getCell(4).getStringCellValue().split("\\|")[1] : "";
                                ccCodigo = currentRow.getCell(4).getStringCellValue().contains("|") ? currentRow.getCell(4).getStringCellValue().split("\\|")[0] : "";
                            }
                            boolean verificar = false;
                            if (mapProcesos.containsKey(proceso)) {
                                procesosGral = (Procesos) mapProcesos.get(proceso);
                                if(procesosGral.getSubprocesos().size()>0){
                                    subprocesosList = procesosGral.getSubprocesos();
                                }else{
                                    subprocesosList = new ArrayList<Procesos>();
                                }
                                for (int i = 0; i < subprocesosList.size(); i++) {
                                    subprocesos = subprocesosList.get(i);
                                    if (subprocesos.getNombreProceso().equals(subproceso.trim())) {
                                        if (!ccCodigo.equals("") && !cc.equals("")) {
                                            ccGral = subprocesos.getCc();
                                            ccGral.add(ccCodigo);
                                            subprocesos.setCc(ccGral);
                                        }
                                        verificar = true;
                                    }
                                }
                                if (verificar == false) {
                                    subprocesos = new Procesos();
                                    ccGral = new ArrayList<String>();
                                    /*Subprocesos*/
                                    if (!subproceso.equals("")) {
                                        subprocesos.setNombreProceso(subproceso);
                                        subprocesos.setNombreProcesoEsa(subproceso);
                                        subprocesos.setNombreProcesoPtb(subproceso);
                                        subprocesos.setNombreProcesoUs(subproceso);
                                    }
                                    if (!responsableSId.equals("")) {
                                        subprocesos.setIdResponsable(responsableSId);
                                    }
                                    if (!responsableS.equals("")) {
                                        subprocesos.setNombreResponsable(responsableS);
                                    }
                                    subprocesos.setFechaCreacionPro(fechaNow);
                                    if (!ccCodigo.equals("") && !cc.equals("")) {
                                        ccGral.add(ccCodigo);
                                        subprocesos.setCc(ccGral);
                                    }
                                    if (!subproceso.equals("") || !responsableSId.equals("")) {
                                        subprocesosList.add(subprocesos);
                                        procesosGral.setSubprocesos(subprocesosList);
                                        mapProcesos.put(proceso, procesosGral);
                                    }
                                }

                            } else {
                                procesosGral = new Procesos();
                                subprocesos = new Procesos();
                                subprocesosList = new ArrayList<Procesos>();
                                ccGral = new ArrayList<String>();
                                /*Procesos*/
                                if (!proceso.equals("")) {
                                    procesosGral.setNombreProceso(proceso);
                                    procesosGral.setNombreProcesoEsa(proceso);
                                    procesosGral.setNombreProcesoPtb(proceso);
                                    procesosGral.setNombreProcesoUs(proceso);
                                }
                                if (!responsablePId.equals("")) {
                                    procesosGral.setIdResponsable(responsablePId);
                                }
                                if (!responsableP.equals("")) {
                                    procesosGral.setNombreResponsable(responsableP);
                                }
                                procesosGral.setFechaCreacionPro(fechaNow);
                                /*Subprocesos*/
                                if (!subproceso.equals("")) {
                                    subprocesos.setNombreProceso(subproceso);
                                    subprocesos.setNombreProcesoEsa(subproceso);
                                    subprocesos.setNombreProcesoPtb(subproceso);
                                    subprocesos.setNombreProcesoUs(subproceso);
                                }
                                if (!responsableSId.equals("")) {
                                    subprocesos.setIdResponsable(responsableSId);
                                }
                                if (!responsableS.equals("")) {
                                    subprocesos.setNombreResponsable(responsableS);
                                }
                                subprocesos.setFechaCreacionPro(fechaNow);
                                if (!ccCodigo.equals("") && !cc.equals("")) {
                                    ccGral.add(ccCodigo);
                                    subprocesos.setCc(ccGral);
                                }
                                if ((!proceso.equals("") &&  !responsablePId.equals("")) && (!subproceso.equals("")  || !responsableSId.equals(""))) {
                                    subprocesosList.add(subprocesos);
                                    procesosGral.setSubprocesos(subprocesosList);
                                    mapProcesos.put(proceso, procesosGral);
                                }
                            }
                            }
                        } catch (Exception ex) {
                            log.debug(ex.getMessage(), ex);
                        }
                    }
                    
                }

            } else {
                message = "UEN no iguales";
            }

        } catch (IOException e) {
            log.debug(e.getMessage());
        } catch (Throwable e) {
            log.debug(e.getMessage(), e);
        }
        for (Entry<String, Procesos> entry : mapProcesos.entrySet()) {
            procesosList.add(entry.getValue());
        }
        result.setData(procesosList);
        result.setType(type);
        result.setMessage(message);
        return result;
    }

    public ByteArrayInputStream creaPlantillaProcesos( String idioma, List<String> listaColumnas, List<CentroCosto> cc, 
            String nombreUen, List<UsuarioUen>listaUsuario, String infoUen, String plantilla, List<ProcessUenCC> proceso, List  procesosData,
            int uenId, List<Procesos>subprocesosGeneralesUen, List<Procesos>procesosGeneralesUen ) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            Workbook workbook = new XSSFWorkbook();
            if(plantilla.equals("Empty")){
                sheetDatosTablaPlantilla(workbook, listaColumnas, cc, nombreUen, listaUsuario, infoUen, uenId,
                        subprocesosGeneralesUen, procesosGeneralesUen);  
            }else if(plantilla.equals("Edit")){
                sheetDatosTablaPlantillaEdit(workbook, listaColumnas, nombreUen, listaUsuario, infoUen, procesosData, proceso, cc, uenId,
                        subprocesosGeneralesUen, procesosGeneralesUen );
            }
            workbook.write(out);
            workbook.close();
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
        }
        return new ByteArrayInputStream(out.toByteArray());

    }

    private Workbook sheetDatosTablaPlantilla(Workbook workbook, List<String> listaColumnas, List<CentroCosto> cc,
            String nombreUen, List<UsuarioUen>listaUsuario, String infoUen, int uenId,
            List<Procesos>subprocesosGeneralesUen, List<Procesos>procesosGeneralesUen) throws DecoderException { 
        String[] columns = listaColumnas.toArray(new String[0]);
        Sheet sheetDatosTabla = workbook.createSheet("Proceso_SubProceso");
        int tamTabla = 500;
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
        headerCellStyle.setFillForegroundColor(IndexedColors.DARK_BLUE.index);
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        /*Create */
        Row headerPrincipal = sheetDatosTabla.createRow(0);
        headerPrincipal.createCell(0).setCellValue(infoUen); 
        headerPrincipal.createCell(1).setCellValue(nombreUen); 
        headerPrincipal.createCell(80).setCellValue(uenId); 
        headerPrincipal.createCell(81).setCellValue("Nuevo"); 
        // Create a Row
        Row headerRow = sheetDatosTabla.createRow(1);
        creaCell(headerCellStyle, headerRow, columns);
        Row rowExel = sheetDatosTabla.createRow(2);
        rowExel.createCell(1).setCellValue("Seleccionar");
        rowExel.createCell(3).setCellValue("Seleccionar");
        rowExel.createCell(4).setCellValue("Seleccionar");
        addValidacionCombo(workbook, sheetDatosTabla, listaUsuario, cc, 1, 1, tamTabla,"R_PROCESO",1, subprocesosGeneralesUen, procesosGeneralesUen);
        addValidacionCombo(workbook, sheetDatosTabla,listaUsuario, cc, 3, 3, tamTabla,"R_PROCESO",2, subprocesosGeneralesUen, procesosGeneralesUen);
        addValidacionCombo(workbook, sheetDatosTabla, listaUsuario, cc, 4, 4, tamTabla,"CCOSTO",3, subprocesosGeneralesUen, procesosGeneralesUen);
        for (int i = 0; i < columns.length; i++) {
            sheetDatosTabla.autoSizeColumn(i);
        }
        XSSFWorkbook workbook1q = ((XSSFWorkbook) workbook);
        System.out.println(sheetDatosTabla.getSheetName());
        workbook1q.lockStructure();
        return workbook;
    }
    
    
    private Workbook sheetDatosTablaPlantillaEdit(Workbook workbook, List<String> listaColumnas, String nombreUen, 
            List<UsuarioUen>listaUsuario, String infoUen, List datosTabla, List<ProcessUenCC>procesoCC,  List<CentroCosto> cc,
            int uenIdP,List<Procesos>subprocesosGeneralesUen, List<Procesos>procesosGeneralesUen ) throws DecoderException {
        String[] columns =  listaColumnas.toArray(new String[0]);
        int tamTabla=500;
        Sheet sheetDatosTabla = workbook.createSheet("Proceso_SubProceso");
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
        headerCellStyle.setFillForegroundColor(IndexedColors.DARK_BLUE.index);
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        /*Create */
        Row headerPrincipal = sheetDatosTabla.createRow(0);
        headerPrincipal.createCell(0).setCellValue(infoUen); 
        headerPrincipal.createCell(1).setCellValue(nombreUen); 
        headerPrincipal.createCell(80).setCellValue(uenIdP); 
        headerPrincipal.createCell(81).setCellValue("Editar"); 
        // Create a Row
        Row headerRow = sheetDatosTabla.createRow(1);
        creaCell(headerCellStyle, headerRow, columns);
        int index=0;
        int identificador=1;
        Row rowExel =null;
        String[] campo = new String[1];
        for (int i = 0; i < datosTabla.size(); i++) {
            Map<String, Object>informacionProceso= (Map<String, Object>) datosTabla.get(i);
            List subprocesos = (List) informacionProceso.get("subprocesos");
            for(int x=0;x<subprocesos.size();x++){
                index=0;
                Map<String, Object> informacionSubproceso= (Map<String, Object>) subprocesos.get(x);
                for(int w=0;w<procesoCC.size();w++){
                    ProcessUenCC ccProceso= procesoCC.get(w);
                    int uenId=Integer.valueOf(informacionSubproceso.get("id_proceso_uen")==null?"0":informacionSubproceso.get("id_proceso_uen").toString());
                    int uenIdCC=ccProceso.getIdProcesoUen()==null?0:ccProceso.getIdProcesoUen();
                    if(uenId ==uenIdCC){
                        index++;
                        if(index ==1){
                            rowExel = sheetDatosTabla.createRow(identificador + 1);
                            rowExel.createCell(4).setCellValue(ccProceso.getCodigoCC() +"|"+ccProceso.getNombreCC());
                        }else{
                            identificador++;
                            rowExel = sheetDatosTabla.createRow(identificador + 1);
                            rowExel.createCell(4).setCellValue(ccProceso.getCodigoCC() +"|"+ccProceso.getNombreCC());
                        }
                        rowExel.createCell(0).setCellValue(informacionProceso.get("id_proceso").toString() +"|"+informacionProceso.get("nombre_proceso").toString());
                        if(informacionProceso.get("nombre_responsable")==null){
                            rowExel.createCell(1).setCellValue("Seleccionar");
                        }else{
                            rowExel.createCell(1).setCellValue(informacionProceso.get("id_responsable").toString() +"|"+informacionProceso.get("nombre_responsable").toString());
                        }
                        rowExel.createCell(2).setCellValue(informacionSubproceso.get("id_proceso").toString() +"|" +informacionProceso.get("nombre_proceso").toString()  +" - "+informacionSubproceso.get("nombre_proceso").toString());
                        if(informacionSubproceso.get("nombre_responsable")==null){
                            rowExel.createCell(3).setCellValue("Seleccionar");
                        }else{
                            rowExel.createCell(3).setCellValue(informacionSubproceso.get("id_responsable").toString() +"|"+informacionSubproceso.get("nombre_responsable").toString());
                        }
                    }
                }
                if(index ==0){
                    rowExel = sheetDatosTabla.createRow(identificador + 1);
                    rowExel.createCell(0).setCellValue(informacionProceso.get("id_proceso").toString() +"|"+informacionProceso.get("nombre_proceso").toString());
                    if(informacionProceso.get("nombre_responsable")==null){
                        rowExel.createCell(1).setCellValue("Seleccionar");
                    }else{
                        rowExel.createCell(1).setCellValue(informacionProceso.get("id_responsable").toString() +"|"+informacionProceso.get("nombre_responsable").toString());
                    }
                    rowExel.createCell(2).setCellValue(informacionSubproceso.get("id_proceso").toString() +"|" +informacionProceso.get("nombre_proceso").toString()  +" - "+informacionSubproceso.get("nombre_proceso").toString());
                    if(informacionSubproceso.get("nombre_responsable")==null){
                        rowExel.createCell(3).setCellValue("Seleccionar");
                    }else{
                        rowExel.createCell(3).setCellValue(informacionSubproceso.get("id_responsable").toString() +"|"+informacionSubproceso.get("nombre_responsable").toString());
                    }
                    rowExel.createCell(4).setCellValue("Seleccionar");
                }
                
                identificador++;
            }
        }
        addValidacionCombo(workbook, sheetDatosTabla, listaUsuario, cc, 0, 0, tamTabla,"PROCESO",0, subprocesosGeneralesUen, procesosGeneralesUen);
        addValidacionCombo(workbook, sheetDatosTabla, listaUsuario, cc, 1, 1, tamTabla,"R_PROCESO",1, subprocesosGeneralesUen, procesosGeneralesUen);
        addValidacionCombo(workbook, sheetDatosTabla, listaUsuario, cc, 2, 2, tamTabla,"SUBPROCESO",2, subprocesosGeneralesUen, procesosGeneralesUen);
        addValidacionCombo(workbook, sheetDatosTabla, listaUsuario, cc, 3, 3, tamTabla,"R_PROCESO",3, subprocesosGeneralesUen, procesosGeneralesUen);
        addValidacionCombo(workbook, sheetDatosTabla, listaUsuario, cc, 4, 4, tamTabla,"CCOSTO",4, subprocesosGeneralesUen, procesosGeneralesUen);
        for (int i = 0; i < columns.length; i++) {
            sheetDatosTabla.autoSizeColumn(i);
        }
        XSSFWorkbook workbook1q = ((XSSFWorkbook) workbook);
        System.out.println(sheetDatosTabla.getSheetName());
        workbook1q.lockStructure();

        return workbook;
    }

    private void addValidacionCombo(Workbook workbook, Sheet sheetDatosTabla,  List<UsuarioUen>listaUsuario,
            List<CentroCosto> cc, int colStart, int coluEnd, int tamTabla, String tipo, int datoExtra,
            List<Procesos>subprocesosGeneralesUen, List<Procesos>procesosGeneralesUen) {
        DataValidationHelper dvHelper = sheetDatosTabla.getDataValidationHelper();
        String[] validacion = null;
        Sheet sheetHidden = workbook.createSheet("hidden"+datoExtra);
        if(tipo.equals("CCOSTO")){
            validacion=new String[cc.size()];
            for (int i = 0; i < cc.size(); i++) {
                validacion[i] =cc.get(i).getCodigo()+"|" +cc.get(i).getNombre();
                Row rowExel = sheetHidden.createRow(i);
                rowExel.createCell(0)
                        .setCellValue(validacion[i]);
            }
        }else if(tipo.equals("R_PROCESO")){
            validacion=new String[listaUsuario.size()];
            for (int i = 0; i < listaUsuario.size(); i++) {
                validacion[i] = listaUsuario.get(i).getIdUsuario()+"|"+listaUsuario.get(i).getNombreUsuario();
                Row rowExel = sheetHidden.createRow(i);
                rowExel.createCell(0)
                        .setCellValue(validacion[i]);
            }
        }else if(tipo.equals("PROCESO")){
            validacion=new String[procesosGeneralesUen.size()];
            for (int i = 0; i < procesosGeneralesUen.size(); i++) {
                validacion[i] = procesosGeneralesUen.get(i).getIdProceso()+"|"+procesosGeneralesUen.get(i).getNombreProceso();
                Row rowExel = sheetHidden.createRow(i);
                rowExel.createCell(0)
                        .setCellValue(validacion[i]);
            }
        }else if(tipo.equals("SUBPROCESO")){
            validacion=new String[subprocesosGeneralesUen.size()];
            for (int i = 0; i < subprocesosGeneralesUen.size(); i++) {
                validacion[i] = subprocesosGeneralesUen.get(i).getIdProceso()+"|"+subprocesosGeneralesUen.get(i).getNombreProceso();
                Row rowExel = sheetHidden.createRow(i);
                rowExel.createCell(0)
                        .setCellValue(validacion[i]);
            }
        }
        Name namedCell = workbook.createName();
        namedCell.setNameName("hidden"+datoExtra);
        namedCell.setRefersToFormula("hidden"+datoExtra+"!$A$1:$A$" + validacion.length);
        DataValidationConstraint dvConstraint = dvHelper.createFormulaListConstraint("hidden"+datoExtra);
        int rango=1;
        CellRangeAddressList addressList = new CellRangeAddressList(rango, tamTabla, colStart, coluEnd);
        DataValidation validation = dvHelper.createValidation(dvConstraint, addressList);
        validation.setSuppressDropDownArrow(true);
        validation.setShowErrorBox(true);
        sheetHidden.protectSheet("metalsa");
        workbook.setSheetHidden(datoExtra, true);
        sheetDatosTabla.addValidationData(validation);
    }

    private void creaCell(CellStyle headerCellStyle, Row headerRow, String[] columns) {
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }
    }

}
