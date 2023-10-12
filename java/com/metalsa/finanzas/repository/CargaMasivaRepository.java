package com.metalsa.finanzas.repository;

import com.metalsa.core.model.Periodos;
import com.metalsa.finanzas.model.AdjuntoPojo;
import com.metalsa.finanzas.model.CategoriaIdioma;
import com.metalsa.finanzas.model.SeguimientoSolicitud;
import com.metalsa.finanzas.model.SeguimientoTransferencia;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
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
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author JL
 */
@Log4j
public class CargaMasivaRepository {

    public CargaMasivaRepository() {
    }

    public List<SeguimientoSolicitud> procesarExcelTransaccion(AdjuntoPojo data) {
        log.debug(" **** procesarExcelTransaccion *** ");
        List<SeguimientoSolicitud> respuesta = new ArrayList<>();
        try {
            
            InputStream excelFile = new ByteArrayInputStream(data.getFile());
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            iterator.next();  // saltamos las cabeceras
            SeguimientoSolicitud registro;
            Double porcentaje=0.0;
            Double monto=0.0;
            String aprobador="";
            String solicitador="";
            String razon="";
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                try {
                    if (currentRow.getCell(0) != null 
                            && currentRow.getCell(1) != null
                            && currentRow.getCell(2) != null
                            && currentRow.getCell(3) != null)
                    {
                        /*Validar los montos y porcentaje*/
                        if ( currentRow.getCell(4)==null){
                            porcentaje=0.0;
                        }else if(currentRow.getCell(4).getCellTypeEnum() == CellType.NUMERIC
                            || currentRow.getCell(4).getCellTypeEnum() != CellType.BLANK) {
                            porcentaje = currentRow.getCell(4).getNumericCellValue();
                        }
                        if ( currentRow.getCell(5)==null){
                            monto=0.0;
                        }else if (currentRow.getCell(5).getCellTypeEnum() == CellType.NUMERIC
                                || currentRow.getCell(5).getCellTypeEnum() != CellType.BLANK) {
                            monto = currentRow.getCell(5).getNumericCellValue();
                        }
                        
                        if ( currentRow.getCell(6)==null){
                           solicitador="";
                        }else if (currentRow.getCell(6).getCellTypeEnum() == CellType.STRING
                                || currentRow.getCell(6).getCellTypeEnum() != CellType.BLANK) {
                            solicitador = currentRow.getCell(6).getStringCellValue().trim();
                        }
                        
                        if ( currentRow.getCell(7)==null){
                           aprobador="";
                        }else if (currentRow.getCell(7).getCellTypeEnum() == CellType.STRING
                                || currentRow.getCell(7).getCellTypeEnum() != CellType.BLANK) {
                            aprobador = currentRow.getCell(7).getStringCellValue().trim();
                        }
                        
                        if ( currentRow.getCell(8)==null){
                           solicitador="";
                        }else if (currentRow.getCell(8).getCellTypeEnum() == CellType.STRING
                                || currentRow.getCell(8).getCellTypeEnum() != CellType.BLANK) {
                            razon = currentRow.getCell(8).getStringCellValue().trim();
                        }
                        
                        registro = new SeguimientoSolicitud();
                        registro.setPeriodoDestino(currentRow.getCell(0).getStringCellValue());
                        registro.setNombreUen(currentRow.getCell(1).getStringCellValue().split("-")[0]);
                        //registro.setCcDestino(currentRow.getCell(2).getStringCellValue().equals("Todos")?"Todos":currentRow.getCell(2).getStringCellValue().split("-")[1]);
                        registro.setCategoriaDestino(currentRow.getCell(2).getStringCellValue().equals("Todos") ? "Todos" : currentRow.getCell(2).getStringCellValue().split("-")[1]);
                        registro.setTransaccion(currentRow.getCell(3).getStringCellValue());
                        registro.setPorcentaje(porcentaje);
                        registro.setMonto(monto);
                        registro.setUsuarioCreacion(solicitador);
                        registro.setAprobadorCC(aprobador);
                        registro.setRazon(razon);
                        //int idUen=Integer.valueOf(currentRow.getCell(1).getStringCellValue().split("-")[0]);
                        //registro.setIdUen(new Long(idUen));
                        registro.setIdCategoria(currentRow.getCell(2).getStringCellValue().equals("Todos") ? 0 : Integer.valueOf(currentRow.getCell(2).getStringCellValue().split("-")[0]));
                        registro.setCc_id(currentRow.getCell(1).getStringCellValue().equals("Todos") ? "Todos" : currentRow.getCell(1).getStringCellValue().split("-")[1]);
                        respuesta.add(registro);
                    }
                } catch (Exception ex) {
                    log.debug(ex.getMessage(), ex);
                }
            }
        } catch (IOException e) {
            log.debug(e.getMessage());
        } catch (Throwable e) {
            log.debug(e.getMessage(), e);
        }

        return respuesta;
    }

    
     public List<SeguimientoTransferencia> procesarExcelTransferencia(AdjuntoPojo data) {

        List<SeguimientoTransferencia> respuesta = new ArrayList<>();
        try {
            
            InputStream excelFile = new ByteArrayInputStream(data.getFile());
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            iterator.next();  // saltamos las cabeceras
            iterator.next();  // saltamos las cabeceras
            SeguimientoTransferencia registro;
            Double monto=0.0;
            String aprobador="";
            String solicitador="";
            String razon="";
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                try {
                    if (currentRow.getCell(0) != null 
                            && currentRow.getCell(1) != null
                            && currentRow.getCell(2) != null
                            && currentRow.getCell(3) != null
                            && currentRow.getCell(4) != null
                            && currentRow.getCell(5) != null)
                    {
                        if ( currentRow.getCell(6)==null){
                            monto=0.0;
                        }else if (currentRow.getCell(6).getCellTypeEnum() == CellType.NUMERIC
                                || currentRow.getCell(6).getCellTypeEnum() != CellType.BLANK) {
                            monto = currentRow.getCell(6).getNumericCellValue();
                        }
                        
                        if ( currentRow.getCell(7)==null){
                           solicitador="";
                        }else if (currentRow.getCell(7).getCellTypeEnum() == CellType.STRING
                                || currentRow.getCell(7).getCellTypeEnum() != CellType.BLANK) {
                            solicitador = currentRow.getCell(7).getStringCellValue().trim();
                        }
                        
                        if ( currentRow.getCell(8)==null){
                           aprobador="";
                        }else if (currentRow.getCell(8).getCellTypeEnum() == CellType.STRING
                                || currentRow.getCell(8).getCellTypeEnum() != CellType.BLANK) {
                            aprobador = currentRow.getCell(8).getStringCellValue().trim();
                        }
                        
                        if ( currentRow.getCell(9)==null){
                           solicitador="";
                        }else if (currentRow.getCell(9).getCellTypeEnum() == CellType.STRING
                                || currentRow.getCell(9).getCellTypeEnum() != CellType.BLANK) {
                            razon = currentRow.getCell(9).getStringCellValue().trim();
                        }
                        
                        registro = new SeguimientoTransferencia();
                        /*Origen*/
                        registro.setPeriodoD(currentRow.getCell(0).getStringCellValue());
                        registro.setUenD(currentRow.getCell(1).getStringCellValue().split("-")[0]);
                        registro.setCcDCodigo(currentRow.getCell(1).getStringCellValue().split("-")[1]);
                        registro.setCategoriaDId(Integer.valueOf(currentRow.getCell(2).getStringCellValue().split("-")[0]));
                        registro.setCategoriaD(currentRow.getCell(2).getStringCellValue().split("-")[1]);
                        /*Destino*/
                        registro.setPeriodoO(currentRow.getCell(3).getStringCellValue());
                        registro.setUenO(currentRow.getCell(4).getStringCellValue().split("-")[0]);
                        registro.setCcOCodigo(currentRow.getCell(4).getStringCellValue().split("-")[1]);
                        registro.setCategoriaOId(Integer.valueOf(currentRow.getCell(5).getStringCellValue().split("-")[0]));
                        registro.setCategoriaO(currentRow.getCell(5).getStringCellValue().split("-")[1]);
                        registro.setMonto(monto);
                        registro.setSolicitador(solicitador);
                        registro.setAprobador(aprobador);
                        registro.setRazon(razon);
                        respuesta.add(registro);
                    }
                } catch (Exception ex) {
                    log.debug(ex.getMessage(), ex);
                }
            }
        } catch (IOException e) {
            log.debug(e.getMessage());
        } catch (Throwable e) {
            log.debug(e.getMessage(), e);
        }

        return respuesta;
    }

     
    public ByteArrayInputStream creaPlantillaTransaccion(Iterable<CategoriaIdioma> listaCategoria, List<Periodos> listaPeriodos,
            String idioma, List <String>listaTransaccion, List<String> listaColumnas,  List<String>uen_cc, String plantilla) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            Workbook workbook = new XSSFWorkbook();
            if (plantilla.equals("Incremento")) {
                sheetDatosTablaPlantilla(workbook, listaCategoria, listaPeriodos, listaTransaccion, listaColumnas, uen_cc, plantilla);
            } else if (plantilla.equals("Transferencia")) {
                sheetDatosTablaPlantillaT(workbook, listaCategoria, listaPeriodos, listaTransaccion, listaColumnas, uen_cc, plantilla, idioma);
            }
            workbook.write(out);
            workbook.close();
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
        }
        return new ByteArrayInputStream(out.toByteArray());

    }

    private Workbook sheetDatosTablaPlantilla(Workbook workbook, Iterable<CategoriaIdioma> listaCategoria, List<Periodos> listaPeriodos, 
            List <String>listaTransaccion, List<String> listaColumnas, List<String>uen_cc, String plantilla) throws DecoderException { 
        String[] columns = listaColumnas.toArray(new String[0]);
        Sheet sheetDatosTabla = workbook.createSheet("Plantilla_Transaccion");
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.BLUE.getIndex());
        int tamTabla = 1000;
        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Create a Row
        Row headerRow = sheetDatosTabla.createRow(0);
        creaCell(headerCellStyle, headerRow, columns);
        Row rowExel = sheetDatosTabla.createRow(1);
        rowExel.createCell(0).setCellValue("Seleccionar");
        rowExel.createCell(1).setCellValue("Seleccionar");
        rowExel.createCell(2).setCellValue("Seleccionar");
        rowExel.createCell(3).setCellValue("Seleccionar");
        addValidacionCombo(workbook, sheetDatosTabla, listaPeriodos, listaCategoria, listaTransaccion, uen_cc, 0, 0, tamTabla,"PERIODOS",1, plantilla);
        addValidacionCombo(workbook, sheetDatosTabla, listaPeriodos, listaCategoria, listaTransaccion, uen_cc, 1, 1, tamTabla,"UEN_CC",2, plantilla);
        addValidacionCombo(workbook, sheetDatosTabla, listaPeriodos, listaCategoria, listaTransaccion, uen_cc, 2, 2, tamTabla,"CATEGORIA",3, plantilla);
        addValidacionCombo(workbook, sheetDatosTabla, listaPeriodos, listaCategoria, listaTransaccion, uen_cc, 3, 3, tamTabla,"TRANSACCION",4, plantilla);

        for (int i = 0; i < columns.length; i++) {
            sheetDatosTabla.autoSizeColumn(i);
        }
        XSSFWorkbook workbook1q = ((XSSFWorkbook) workbook);
        System.out.println(sheetDatosTabla.getSheetName());
        workbook1q.lockStructure();
        return workbook;
    }

    private void addValidacionCombo(Workbook workbook, Sheet sheetDatosTabla, List<Periodos> listaPeriodos,
            Iterable<CategoriaIdioma> listaCategoria, List <String>listaTransaccion, List<String>uen_cc,
            int colStart, int coluEnd, int tamTabla, String tipo, int datoExtra, String plantilla) {
        DataValidationHelper dvHelper = sheetDatosTabla.getDataValidationHelper();
        String[] validacion = null;
        Sheet sheetHidden = workbook.createSheet("hidden"+datoExtra);
        if(tipo.equals("PERIODOS")){
            validacion=new String[listaPeriodos.size()];
            for (int i = 0; i < listaPeriodos.size(); i++) {
                validacion[i] = listaPeriodos.get(i).getMesAbreviado()+"-"+listaPeriodos.get(i).getAnio();
                Row rowExel = sheetHidden.createRow(i);
                rowExel.createCell(0)
                        .setCellValue(validacion[i]);
            }
        }else if(tipo.equals("UEN_CC")){
            validacion=new String[uen_cc.size()];
            for (int i = 0; i < uen_cc.size(); i++) {
                validacion[i] = uen_cc.get(i);
                Row rowExel = sheetHidden.createRow(i);
                rowExel.createCell(0)
                        .setCellValue(validacion[i]);
            }
        } else if (tipo.equals("CATEGORIA")) {
            ArrayList list = new ArrayList<>((Collection<? extends CategoriaIdioma>) listaCategoria);
            validacion = new String[list.size()];
            int i = 0;
            for (CategoriaIdioma categoria : listaCategoria) {
                validacion[i] = categoria.getAccCatId() +"-"+categoria.getNombreCategoria();
                Row rowExel = sheetHidden.createRow(i);
                rowExel.createCell(0)
                        .setCellValue(validacion[i]);
                i++;
            }
        }else if(tipo.equals("TRANSACCION")){
            validacion=new String[listaTransaccion.size()];
            for (int i = 0; i < listaTransaccion.size(); i++) {

                validacion[i] = listaTransaccion.get(i);
                Row rowExel = sheetHidden.createRow(i);
                rowExel.createCell(0)
                        .setCellValue(validacion[i]);
            }
        }
        int size;
        if (validacion.length == 0) {
            size = 2;
        } else {
            size = validacion.length;
        }
        Name namedCell = workbook.createName();
        namedCell.setNameName("hidden" + datoExtra);
        namedCell.setRefersToFormula("hidden" + datoExtra + "!$A$1:$A$" + size);
        DataValidationConstraint dvConstraint = dvHelper.createFormulaListConstraint("hidden" + datoExtra);
        int rango = 1;
        if (plantilla.equals("Transferencia")) {
            rango=2;
        }
        CellRangeAddressList addressList = new CellRangeAddressList(rango, tamTabla, colStart, coluEnd);
        DataValidation validation = dvHelper.createValidation(dvConstraint, addressList);
        validation.setSuppressDropDownArrow(true);
        validation.setShowErrorBox(true);
        sheetHidden.protectSheet("metalsa");
        workbook.setSheetHidden(datoExtra, true);
        sheetDatosTabla.addValidationData(validation);
    }

    private Workbook sheetDatosTablaPlantillaT(Workbook workbook, Iterable<CategoriaIdioma> listaCategoria, List<Periodos> listaPeriodos,
            List<String> listaColumnas1, List<String> listaColumnas2, List<String> uen_cc, String plantilla, String idioma) throws DecoderException {
        String[] columns = listaColumnas2.toArray(new String[0]);
        String[] columnsH1 = listaColumnas1.toArray(new String[0]);
        String nameSheet = idioma.equals("ESA") ? "Plantilla_Transferencia" : "Template_Transfer";
        Sheet sheetDatosTabla = workbook.createSheet(nameSheet);
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.BLUE.getIndex());
        int tamTabla = 1000;
        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
        
        /*color origen*/
        CellStyle headerCellStyle1 = workbook.createCellStyle();
        headerCellStyle1.setFont(headerFont);
        headerCellStyle1.setAlignment(HorizontalAlignment.CENTER);
        headerCellStyle1.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
        headerCellStyle1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        
        // Color destino
        CellStyle headerCellStyle2 = workbook.createCellStyle();
        headerCellStyle2.setFont(headerFont);
        headerCellStyle2.setAlignment(HorizontalAlignment.CENTER);
        headerCellStyle2.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerCellStyle2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        
        // Create a Row
        Row headerRow1 = sheetDatosTabla.createRow(0);  
        creaCellDinamica(headerCellStyle2, headerRow1, columnsH1[0],0);
        creaCellDinamica(headerCellStyle1, headerRow1, columnsH1[1],3);
        sheetDatosTabla.addMergedRegion(new CellRangeAddress(
        0, //first row (0-based)
        0, //last row  (0-based)
        0, //first column (0-based)
        2  //last column  (0-based)
        ));
        sheetDatosTabla.addMergedRegion(new CellRangeAddress(
        0, //first row (0-based)
        0, //last row  (0-based)
        3, //first column (0-based)
        9  //last column  (0-based)
        ));
       
        Row headerRow = sheetDatosTabla.createRow(1);
        creaCell(headerCellStyle, headerRow, columns);
        Row rowExel = sheetDatosTabla.createRow(2);
        //origen
        rowExel.createCell(0).setCellValue("Seleccionar");
        rowExel.createCell(1).setCellValue("Seleccionar");
        rowExel.createCell(2).setCellValue("Seleccionar");
        //destino
        rowExel.createCell(3).setCellValue("Seleccionar");
        rowExel.createCell(4).setCellValue("Seleccionar");
        rowExel.createCell(5).setCellValue("Seleccionar");
        addValidacionCombo(workbook, sheetDatosTabla, listaPeriodos, listaCategoria, listaColumnas1, uen_cc, 0, 0, tamTabla,"PERIODOS",1, plantilla);
        addValidacionCombo(workbook, sheetDatosTabla, listaPeriodos, listaCategoria, listaColumnas1, uen_cc, 1, 1, tamTabla,"UEN_CC",2, plantilla);
        addValidacionCombo(workbook, sheetDatosTabla, listaPeriodos, listaCategoria, listaColumnas1, uen_cc, 2, 2, tamTabla,"CATEGORIA",3, plantilla);
        addValidacionCombo(workbook, sheetDatosTabla, listaPeriodos, listaCategoria, listaColumnas1, uen_cc, 3, 3, tamTabla,"PERIODOS",4, plantilla);
        addValidacionCombo(workbook, sheetDatosTabla, listaPeriodos, listaCategoria, listaColumnas1, uen_cc, 4, 4, tamTabla,"UEN_CC",5, plantilla);
        //addValidacionComboPrueba(workbook, sheetDatosTabla, listaCategoria, 5, 5, tamTabla,nombre);
        addValidacionCombo(workbook, sheetDatosTabla, listaPeriodos, listaCategoria, listaColumnas1, uen_cc, 5, 5, tamTabla,"CATEGORIA",6, plantilla);
        for (int i = 0; i < columns.length; i++) {
            sheetDatosTabla.autoSizeColumn(i);
        }
        XSSFWorkbook workbook1q = ((XSSFWorkbook) workbook);
        System.out.println(sheetDatosTabla.getSheetName());
        workbook1q.lockStructure();
        return workbook;
    }
        
    private void creaCell(CellStyle headerCellStyle, Row headerRow, String[] columns) {
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }
    }
    
    private void creaCellDinamica(CellStyle headerCellStyle, Row headerRow, String name, int star) {
        Cell cell = headerRow.createCell(star);
        cell.setCellValue(name);
        cell.setCellStyle(headerCellStyle);
    }
}
