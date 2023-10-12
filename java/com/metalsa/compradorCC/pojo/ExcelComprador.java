/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.compradorCC.pojo;

import com.metalsa.finanzas.model.AdjuntoPojo;
import com.metalsa.utils.Constants;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.DecoderException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.context.annotation.ComponentScan;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jose.jimenez07
 */
@ComponentScan
public class ExcelComprador {

    public ByteArrayInputStream creaPlantillaCc(List<DatosTabla> datosTabla, List<CompradorCcPojo> listComprador, List<CompradorCcPojo> listUser, String idioma, RequestFiltros requestFiltros, List<CategoriaPojo> listCategoria) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            Workbook workbook = new XSSFWorkbook();
            // compradores sin repeticiones
            List<CompradorCcPojo> listUsersSin = new ArrayList();
            //
            int flag = 0;
            for (int i = 0; i < listUser.size(); i++) { // quitamos las comas y los repetidos si es que hay 
                listUser.get(i).setNombre_comprador(listUser.get(i).getNombre_comprador().replace(",", "").replace(".", "").replace("-", ""));
                for (int j = 0; j < listUsersSin.size(); j++) {
                    if (listUser.get(i).getId().equals(listUsersSin.get(j).getId())) {
                        flag = 1;
                        break;
                    }
                }

                if (flag == 1) {
                    flag = 0;
                } else {
                    listUsersSin.add(listUser.get(i));
                }
            }

            if (requestFiltros.getTipoExcel().equalsIgnoreCase(Constants.PLANTILLA)) {
                sheetDatosTablaPlantilla(workbook, datosTabla, listUsersSin, requestFiltros);// que crea todo el pinche desmadre 
            }

            if (requestFiltros.getTipoExcel().equalsIgnoreCase(Constants.REPORTE)) {
                sheetDatosTablaReporte(workbook, datosTabla, requestFiltros);// que crea todo el pinche desmadre 
            }

            if (requestFiltros.getTipoExcel().equals(Constants.PLANTILLATOBB)) {
                sheetDatosTablaPlantillaToBB(workbook, datosTabla.get(0).getId_uen(), listComprador, listUser, requestFiltros);
            }

            if (requestFiltros.getTipoExcel().equals(Constants.REPORTEFAM)) {
                sheetDatosTablaPlantillaFam(workbook, listCategoria, requestFiltros);
            }

            //FileOutputStream fileOut = new FileOutputStream("poi-generated-file.xlsx");
            workbook.write(out);
            workbook.close();

        } catch (IOException | DecoderException e) {
            System.out.println(e.getMessage());
        }

        return new ByteArrayInputStream(out.toByteArray());

    }

    private Workbook sheetDatosTablaPlantilla(Workbook workbook, List<DatosTabla> datosTabla, List<CompradorCcPojo> listUsers, RequestFiltros requestFiltros) throws DecoderException { //datosTabla === plantilla
        String[] columns = requestFiltros.getColumnasExcel().toArray(new String[0]);
        //Sheet sheetDatosTabla = workbook.createSheet("" + requestFiltros.getModelListaUensCc().get(0).getNombre());
        Sheet sheetDatosTabla = workbook.createSheet("Plantilla_" + datosTabla.get(0).getId_uen());
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.BLUE.getIndex());
        int tamTabla = datosTabla.size();
        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Create a Row
        Row headerRow = sheetDatosTabla.createRow(0);
        creaCell(headerCellStyle, headerRow, columns);

        String[] campo = new String[1];
        for (int i = 0; i < datosTabla.size(); i++) {
            Row rowExel = sheetDatosTabla.createRow(i + 1);
            datosTabla.get(i).setNombre_cc(datosTabla.get(i).getNombre_cc().replace(",", ""));   // quitamos las mugrosas comas
            datosTabla.get(i).getComprador().get(0).setNombre_comprador(datosTabla.get(i).getComprador().get(0).getNombre_comprador().replace(",", ""));

            campo[0] = "[" + datosTabla.get(i).getId_cc() + "] - " + datosTabla.get(i).getNombre_cc();
            rowExel.createCell(0)
                    .setCellValue(campo[0]);
            addValidacionCombo(sheetDatosTabla, campo, i + 1, i + 1, 0, 0);

            rowExel.createCell(1)
                    .setCellValue("[" + datosTabla.get(i).getComprador().get(0).getId() + "] - " + datosTabla.get(i).getComprador().get(0).getNombre_comprador());

        }

        addValidacionCombo(workbook, sheetDatosTabla, listUsers, 1, 1, tamTabla);

        for (int i = 0; i < columns.length; i++) {
            sheetDatosTabla.autoSizeColumn(i);
        }

        XSSFWorkbook workbook1q = ((XSSFWorkbook) workbook);
        System.out.println(sheetDatosTabla.getSheetName());
        workbook1q.lockStructure();

        return workbook;
    }

    private void addValidacionCombo(Workbook workbook, Sheet sheetDatosTabla, List<CompradorCcPojo> listUsers, int colStart, int coluEnd, int tamTabla) {
        DataValidationHelper dvHelper = sheetDatosTabla.getDataValidationHelper();
        String[] compradorValidacion = new String[listUsers.size()];
        Sheet sheetHidden = workbook.createSheet("hidden");
        for (int i = 0; i < listUsers.size(); i++) {

            compradorValidacion[i] = "[" + listUsers.get(i).getId() + "] - " + listUsers.get(i).getNombre_comprador();
            Row rowExel = sheetHidden.createRow(i);
            rowExel.createCell(0)
                    .setCellValue(compradorValidacion[i]);
        }
        Name namedCell = workbook.createName();
        namedCell.setNameName("hidden");
        namedCell.setRefersToFormula("hidden!$A$1:$A$" + compradorValidacion.length);
        DataValidationConstraint dvConstraint = dvHelper.createFormulaListConstraint("hidden");
        CellRangeAddressList addressList = new CellRangeAddressList(1, tamTabla, colStart, coluEnd);
        DataValidation validation = dvHelper.createValidation(
                dvConstraint, addressList);

        validation.setSuppressDropDownArrow(true);
        validation.setShowErrorBox(true);
        sheetHidden.protectSheet("metalsa");
        workbook.setSheetHidden(1, true);
        sheetDatosTabla.addValidationData(validation);

    }

    private void addValidacionCombo(Sheet sheetDatosTabla, String[] campo, int rowX, int rowY, int colX, int colY) {

        DataValidationHelper dvHelper = sheetDatosTabla.getDataValidationHelper();

        DataValidationConstraint dvConstraint = dvHelper.createExplicitListConstraint(campo);
        CellRangeAddressList addressList = new CellRangeAddressList(rowX, rowY, colX, colY);
        DataValidation validation = dvHelper.createValidation(
                dvConstraint, addressList);

        validation.setSuppressDropDownArrow(true);
        validation.setShowErrorBox(true);
        sheetDatosTabla.addValidationData(validation);

    }

    private void creaCell(CellStyle headerCellStyle, Row headerRow, String[] columns) {
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

    }

    /// Leer excel 
    public List<DatosTabla> leerExcelComprador(AdjuntoPojo data) {
        InputStream excelFile;
        List<DatosTabla> datosTabla = new ArrayList();
        try {
            excelFile = new ByteArrayInputStream(data.getFile());
            Workbook workbook;
            workbook = new XSSFWorkbook(excelFile);
            Sheet sheetDatosTabla = workbook.getSheetAt(0);

            String sheetname = sheetDatosTabla.getSheetName();
            String[] sheetUen = cadenaOriginal(sheetname, "_");
            System.out.println(sheetname);
            System.out.println("uen:" + sheetUen[1]);
            Long idUen = Long.parseLong(sheetUen[1]);

            if (data.getNombreArchivo().equalsIgnoreCase(Constants.PLANTILLA)) {
                datosTabla = leePlantillaCompradorCc(sheetDatosTabla, datosTabla, idUen);
            }
            if (data.getNombreArchivo().equalsIgnoreCase(Constants.PLANTILLATOBB)) {
                datosTabla = leePlantillaCompradorToBB(sheetDatosTabla, datosTabla, idUen);
            }

        } catch (IOException ex) {
            Logger.getLogger(ExcelComprador.class.getName()).log(Level.SEVERE, null, ex);
        }

        return datosTabla;

    }

    private List<DatosTabla> leePlantillaCompradorCc(Sheet sheetDatosTabla, List<DatosTabla> datosTabla, Long idUen) {
        Iterator<Row> rowIterator = sheetDatosTabla.rowIterator();
        rowIterator.next();  // saltamos las cabeceras
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            DatosTabla rowDatosTabla = new DatosTabla();
            List<String[]> campos = new ArrayList();
            String[] campo;

            if (row.getCell(0) == null || row.getCell(1) == null || row.getCell(0).getStringCellValue().trim().equals("") || row.getCell(1).getStringCellValue().trim().equals("")) {// que pasa maldito? ,por si el usuario hizo copy paste y se fue un vacio 
                break;                                                  // para que continuamos
            }
            for (int i = 0; i < 2; i++) {    //4 por el numero de columnas
                campos.add(cadenaOriginal(row.getCell(i).getStringCellValue(), "-"));
            }

            if (!campos.isEmpty()) {
                CompradorCcPojo compradorCcPojo = new CompradorCcPojo();
                campo = campos.get(0);
                rowDatosTabla.setId_uen(idUen);
                rowDatosTabla.setId_cc(Long.parseLong(campo[0]));
                rowDatosTabla.setNombre_cc(campo[1]);
                campo = campos.get(1);
                //asignacion del comprador
                compradorCcPojo.setId(campo[0]);
                compradorCcPojo.setNombre_comprador(campo[1]);
                rowDatosTabla.getComprador().add(compradorCcPojo);
                rowDatosTabla.setCompradorFilter(campo[1]);  // que no se nos olvide el usuario para poder usar los filtros 
                datosTabla.add(rowDatosTabla);
            }

        }
        return datosTabla;
    }

    private String[] cadenaOriginal(String cadena, String pivote) {
        String[] campo = cadena.split(pivote);
        campo[0] = campo[0].replace("[", "").replace("]", "").trim();

        return campo;
    }

    private Workbook sheetDatosTablaReporte(Workbook workbook, List<DatosTabla> datosTabla, RequestFiltros requestFiltros) throws DecoderException { //datosTabla === plantilla
        //Sheet sheetDatosTabla = workbook.createSheet("Plantilla_" + datosTabla.get(0).getId_uen());
        String[] columns = requestFiltros.getColumnasExcel().toArray(new String[0]);
        Sheet sheetDatosTabla = workbook.createSheet("Plantilla_" + datosTabla.get(0).getId_uen());
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.BLUE.getIndex());
        //int tamTabla = datosTabla.size();
        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        // Create a Row
        Row headerRow = sheetDatosTabla.createRow(0);
        creaCell(headerCellStyle, headerRow, columns);
        for (int i = 0; i < datosTabla.size(); i++) {
            Row rowExel = sheetDatosTabla.createRow(i + 1);
            rowExel.createCell(0)
                    .setCellValue(datosTabla.get(i).getNombre_cc());
            rowExel.createCell(1)
                    .setCellValue(datosTabla.get(i).getComprador().get(0).getNombre_comprador());
            rowExel.createCell(2)
                    .setCellValue(datosTabla.get(i).getCreation_date());
            rowExel.createCell(3)
                    .setCellValue(datosTabla.get(i).getAdministrador().getNombre_comprador());
            rowExel.createCell(4)
                    .setCellValue(datosTabla.get(i).getPrevComprador().getNombre_comprador());
        }
        for (int i = 0; i < columns.length; i++) {
            sheetDatosTabla.autoSizeColumn(i);
        }
        XSSFWorkbook workbook1q = ((XSSFWorkbook) workbook);
        System.out.println(sheetDatosTabla.getSheetName());
        workbook1q.lockStructure();
        return workbook;
    }

    //// to bb 
    private Workbook sheetDatosTablaPlantillaToBB(Workbook workbook, Long idUen, List<CompradorCcPojo> listCompradores, List<CompradorCcPojo> listUsers, RequestFiltros requestFiltros) throws DecoderException { //listCompradores === todos los compradores configurados con una cc en esa uen
        String[] columns = requestFiltros.getColumnasExcel().toArray(new String[0]);
        //Sheet sheetDatosTabla = workbook.createSheet("" + requestFiltros.getModelListaUensCc().get(0).getNombre());
        Sheet sheetDatosTabla = workbook.createSheet("Plantilla_" + idUen);
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.BLUE.getIndex());
        int tamTabla = listCompradores.size();
        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        // Create a Row
        Row headerRow = sheetDatosTabla.createRow(0);
        creaCell(headerCellStyle, headerRow, columns);
        String[] campo = new String[1];
        for (int i = 0; i < listCompradores.size(); i++) {
            Row rowExel = sheetDatosTabla.createRow(i + 1);
            campo[0] = "[" + listCompradores.get(i).getId() + "] - " + listCompradores.get(i).getNombre_comprador();
            rowExel.createCell(0)
                    .setCellValue(campo[0]);
            addValidacionCombo(sheetDatosTabla, campo, i + 1, i + 1, 0, 0);

        }
        addValidacionCombo(workbook, sheetDatosTabla, listUsers, 1, 1, tamTabla);
        for (int i = 0; i < columns.length; i++) {
            sheetDatosTabla.autoSizeColumn(i);
        }
        sheetDatosTabla.setColumnWidth(1, 15000);
        XSSFWorkbook workbook1q = ((XSSFWorkbook) workbook);
        System.out.println(sheetDatosTabla.getSheetName());
        workbook1q.lockStructure();
        return workbook;
    }

    /// Leer excel comprador 
    private List<DatosTabla> leePlantillaCompradorToBB(Sheet sheetDatosTabla, List<DatosTabla> datosTabla, Long idUen) {
        Iterator<Row> rowIterator = sheetDatosTabla.rowIterator();
        rowIterator.next();  // saltamos las cabeceras
        int flagContinuar = 0;

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            DatosTabla rowDatosTabla = new DatosTabla();
            List<String[]> campos = new ArrayList();
            String[] campo;
            for (int i = 0; i < 2; i++) {    //2 por el numero de columnas
                if (row.getCell(i) == null || row.getCell(i).getStringCellValue().trim().equals("")) {// que pasa maldito? ,por si el usuario hizo copy paste y se fue un vacio 
                    flagContinuar = 1;
                    break;                                                  // para que continuamos
                }
                campos.add(cadenaOriginal(row.getCell(i).getStringCellValue(), "-"));
            }
            if (flagContinuar == 1) {  //salimos del while
                break;
            }
            if (!campos.isEmpty()) {
                CompradorCcPojo compradorCcPojo = new CompradorCcPojo();
                rowDatosTabla.setId_uen(idUen);
                campo = campos.get(1);                          //la columna dos pasa a ser el comprador y viceversa 
                compradorCcPojo.setId(campo[0]);
                compradorCcPojo.setNombre_comprador(campo[1]);
                rowDatosTabla.setComprador(new ArrayList());
                rowDatosTabla.getComprador().add(compradorCcPojo);
                rowDatosTabla.setCompradorFilter(compradorCcPojo.getNombre_comprador());
                campo = campos.get(0);   // previus buyer
                compradorCcPojo = new CompradorCcPojo();
                compradorCcPojo.setId(campo[0]);
                compradorCcPojo.setNombre_comprador(campo[1]);
                rowDatosTabla.setPrevComprador(compradorCcPojo);
                rowDatosTabla.setPrevCompradorFilter(compradorCcPojo.getNombre_comprador());

                datosTabla.add(rowDatosTabla);
            }

        }

        return datosTabla;

    }

    /**
     * **************************************************************************
     */
    private Workbook sheetDatosTablaPlantillaFam(Workbook workbook, List<CategoriaPojo> listCategoria, RequestFiltros requestFiltros) throws DecoderException {
        //Sheet sheetDatosTabla = workbook.createSheet("Plantilla_" + datosTabla.get(0).getId_uen());
        String[] columns = requestFiltros.getColumnasExcel().toArray(new String[0]);
        Sheet sheetDatosTabla = workbook.createSheet("Plantilla_" + requestFiltros.getModelListaUensCc().get(0).getId()); //id de la uen
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.BLUE.getIndex());
        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        // Create a Row
        Row headerRow = sheetDatosTabla.createRow(0);
        int indexRow = 1;
        List<Integer> categoria = new ArrayList();
        List<Integer> familia = new ArrayList();
        creaCell(headerCellStyle, headerRow, columns);
        for (int i = 0; i < listCategoria.size(); i++) {
            for (int j = 0; j < listCategoria.get(i).getNodo().size(); j++) {
                for (int k = 0; k < listCategoria.get(i).getNodo().get(j).getNodo().size(); k++) {
                    Row rowExel = sheetDatosTabla.createRow(indexRow);
                    if (categoria.isEmpty()) {
                        rowExel.createCell(0).setCellValue(listCategoria.get(i).getNombre_familia());
                        categoria.add(listCategoria.get(i).getId_familia());
                    } else {
                        int idFamilia = listCategoria.get(i).getId_familia();
                        if (categoria.stream().anyMatch(x -> x.equals(idFamilia))) {
                            rowExel.createCell(0).setCellValue("");
                        } else {
                            rowExel.createCell(0).setCellValue(listCategoria.get(i).getNombre_familia());
                        }
                        categoria.add(listCategoria.get(i).getId_familia());
                    }

                    if (familia.isEmpty()) {
                        rowExel.createCell(1).setCellValue(listCategoria.get(i).getNodo().get(j).getNombre_familia());
                        familia.add(listCategoria.get(i).getNodo().get(j).getId_familia());
                    } else {
                        int idFamilia = listCategoria.get(i).getNodo().get(j).getId_familia();
                        if (familia.stream().anyMatch(x -> x.equals(idFamilia))) {
                            rowExel.createCell(1).setCellValue("");
                        } else {
                            rowExel.createCell(1).setCellValue(listCategoria.get(i).getNodo().get(j).getNombre_familia());
                        }
                        familia.add(listCategoria.get(i).getNodo().get(j).getId_familia());
                    }
                    rowExel.createCell(2).setCellValue(listCategoria.get(i).getNodo().get(j).getNodo().get(k).getNombre_familia());
                    rowExel.createCell(3).setCellValue(listCategoria.get(i).getNodo().get(j).getNodo().get(k).getComprador().get(0).getNombre_comprador());
                    rowExel.createCell(4).setCellValue(listCategoria.get(i).getNodo().get(j).getNodo().get(k).getCreation_date());
                    rowExel.createCell(5).setCellValue(listCategoria.get(i).getNodo().get(j).getNodo().get(k).getAdministrador().getNombre_comprador());
                    rowExel.createCell(6).setCellValue(listCategoria.get(i).getNodo().get(j).getNodo().get(k).getPrevComprador().getNombre_comprador());
                    indexRow++;
                }
            }
        }

        for (int i = 0; i < columns.length; i++) {
            sheetDatosTabla.autoSizeColumn(i);
        }
        XSSFWorkbook workbook1q = ((XSSFWorkbook) workbook);
        System.out.println(sheetDatosTabla.getSheetName());
        workbook1q.lockStructure();
        return workbook;
    }

}
