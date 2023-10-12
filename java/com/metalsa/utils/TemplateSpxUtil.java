package com.metalsa.utils;

import com.metalsa.aprobacion.model.UsuarioUen;
import com.metalsa.contralor.model.CentroCostoProceso;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
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
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author juanangelmunozolvera
 * @date 21/Mayo/2020
 */
@ComponentScan
public class TemplateSpxUtil {
    private static final String VALUE_NUMERIC_TRUE = "1";
    private static final String VALUE_NUMERIC_FALSE = "0";
    private static final String VALUE_STRING_SI = "SI";
    private static final String VALUE_STRING_TRUE = "true";
    private static final String VALUE_STRING_FALSE = "false";
    private static final String PASSWORD_XLS = "metalsa";
    private static final String EMPTY_VALUE = "";

    private static final int ROW_START_POSITION = 1;
    private static final int ROW_END_POSITION = 500;
    public static final int CONTROLLER_COLUMN_HIDE = 20;
    
    
    public static String getStringValue(Cell cell) {
        cell.setCellType(Cell.CELL_TYPE_STRING);
        return cell.getStringCellValue();
    }

    public static Double getNumericDoubleValueCell(Cell cell) {
        Double result = 0d;
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            result = cell.getNumericCellValue();
        } else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
            result = Double.valueOf(cell.getStringCellValue());
        }

        return result;
    }

    public static Integer getNumericIntegerValueCell(Cell cell) {
        int result = 0;
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            result = new BigDecimal(cell.getNumericCellValue()).intValue();
        } else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
            result = Integer.parseInt(cell.getStringCellValue());
        }

        return result;
    }

    public static String validateStringValueCell(Cell cell) {
        String result = "";
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            result = String.valueOf(cell.getNumericCellValue());

        } else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
            result = cell.getStringCellValue();
        }

        return result;
    }

    public static Integer validateBooleanToStringValueCell(Cell cell) {
        String result = "";
        Integer descuento;
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            result = String.valueOf(cell.getNumericCellValue());

        } else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
            result = cell.getStringCellValue();
        }

        if (result.equals(VALUE_NUMERIC_TRUE)) {
            descuento = 1;
            result = VALUE_STRING_TRUE;
        } else if (result.equals(VALUE_STRING_SI)) {
            result = VALUE_STRING_TRUE;
            descuento = 1;
        } else {
            descuento = 0;
            result = VALUE_STRING_FALSE;
        }

        return descuento;
    }

    public static Boolean getBooleanValueCell(Cell cell) {
        Boolean result = Boolean.FALSE;
        String value = "";

        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            value = String.valueOf(cell.getNumericCellValue());
            if (VALUE_NUMERIC_TRUE.equals(value)) {
                result = Boolean.TRUE;
            } else {
                result = Boolean.FALSE;
            }

        } else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
            value = cell.getStringCellValue();
            if (VALUE_STRING_SI.equals(value)) {
                result = Boolean.TRUE;
            } else {
                result = Boolean.FALSE;
            }
        }

        return result;
    }
    
    public static ByteArrayInputStream loadInfoTemplateConfRespDelCC(List<CentroCostoProceso> listInfo, MessageSource messages, Locale locale, Map<String, List> infoCombosDTO) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            Workbook workbook = new XSSFWorkbook();
            String hoja = messages.getMessage("lbl_hoja_1", null, locale);
            String hojaUsuarios = messages.getMessage("plantilla_header_usuarios", null, locale);
            Sheet sheet = workbook.createSheet(hoja);
            int tamTabla = 0;
            if (listInfo != null && !listInfo.isEmpty()) {
                fillInfoTemplateConfRespDelCC(workbook, sheet, listInfo, messages, locale);
                tamTabla = listInfo.size();
            }
            createPageUsuarios(workbook, infoCombosDTO.get("usuarios"), messages, locale);
            createHeaderConfRespDelCC(workbook, workbook.getSheet(hoja), messages, locale, infoCombosDTO);
            addValidacionCombo(workbook, sheet, infoCombosDTO.get("usuarios").size(), 1, 1, tamTabla, hojaUsuarios);
            addValidacionCombo(workbook, sheet, infoCombosDTO.get("usuarios").size(), 3, 3, tamTabla, hojaUsuarios);
            addValidacionCombo(workbook, sheet, infoCombosDTO.get("usuarios").size(), 5, 5, tamTabla, hojaUsuarios);
            workbook.write(baos);
            baos.close();
           
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ByteArrayInputStream(baos.toByteArray());
    }
    
    private static void fillInfoTemplateConfRespDelCC(Workbook workbook, Sheet sheet, List<CentroCostoProceso> listInfo, MessageSource messages, Locale locale) {

        int index = 1;
        int column;
        Row row = null;
        try {
            for (CentroCostoProceso info : listInfo) {
                column = 0;
                row = sheet.createRow((short) index++);
                row.createCell(column++).setCellValue(info.getCodigoCC().concat(" - ").concat(info.getNombreCC()));
                row.createCell(column++).setCellValue(info.getResponsableCC() != null ? "[" + info.getIdResponsableCC()+ "] - " + info.getResponsableCC() : EMPTY_VALUE);
                row.createCell(column++).setCellValue(info.getNombreProceso() != null ? info.getNombreProceso() : EMPTY_VALUE);
                row.createCell(column++).setCellValue(info.getResponsableProceso() != null ? "[" + info.getIdResponsableProceso()+ "] - " + info.getResponsableProceso() : EMPTY_VALUE);
                row.createCell(column++).setCellValue(info.getNombreSubProceso()!= null ? info.getNombreSubProceso() : EMPTY_VALUE);
                row.createCell(column++).setCellValue(info.getResponsableSubProceso()!= null ? "[" + info.getIdResponsableSubProceso()+ "] - " + info.getResponsableSubProceso() : EMPTY_VALUE);
                row.createCell(column++).setCellValue(info.getUsuarioActualizacion()!= null ? info.getUsuarioActualizacion() : EMPTY_VALUE);
                row.createCell(CONTROLLER_COLUMN_HIDE).setCellValue(info.getIdCC());
            }
            for (int i = 0; i < row.getLastCellNum(); i++) {                
                sheet.autoSizeColumn(i);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    
    private static void createHeaderConfRespDelCC(Workbook workbook, Sheet sheet, MessageSource messages, Locale locale, Map<String, List> infoCombosDTO) {

        int index = 0;
        int column = 0;
        try {

            CellStyle styleHeader = loadStyleHeader(workbook);
            CellStyle styleRow = workbook.createCellStyle();
            styleRow.setWrapText(true);
//            String[] usersArray = transformUsuariosToArray(infoCombosDTO.get("usuarios"));
            Row rowhead = sheet.createRow((short) index);
            rowhead.createCell(column++).setCellValue(messages.getMessage("lbl_centro_costo", null, locale));
            rowhead.createCell(column++).setCellValue(messages.getMessage("lbl_responsable_cc", null, locale));
            rowhead.createCell(column++).setCellValue(messages.getMessage("lbl_proceso", null, locale));
            rowhead.createCell(column++).setCellValue(messages.getMessage("lbl_responsable_proceso_2", null, locale));
            rowhead.createCell(column++).setCellValue(messages.getMessage("lbl_subproceso", null, locale));
            rowhead.createCell(column++).setCellValue(messages.getMessage("lbl_responsable_subproceso_2", null, locale));
            rowhead.createCell(column++).setCellValue(messages.getMessage("lbl_administrador", null, locale));
            rowhead.createCell(CONTROLLER_COLUMN_HIDE).setCellValue("idCc");
            sheet.setColumnHidden(CONTROLLER_COLUMN_HIDE, true);
            for (int i = 0; i < column; i++) {
                rowhead.getCell(i).setCellStyle(styleHeader);
                sheet.autoSizeColumn(i);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    
    private static void createPageUsuarios(Workbook workbook, List<UsuarioUen> usersList, MessageSource messages, Locale locale) {

        int index = 0;
        int column = 0;
        Row row;
        try {

            Sheet sheet = workbook.createSheet(messages.getMessage("plantilla_header_usuarios", null, locale));

//            CellStyle styleHeader = loadStyleHeader(workbook);
            CellStyle styleRow = workbook.createCellStyle();
            styleRow.setWrapText(true);

//            Row rowhead = sheet.createRow((short) index++);

//            rowhead.createCell(column++).setCellValue(messages.getMessage("label_gral_id", null, locale));
//            rowhead.createCell(column++).setCellValue(messages.getMessage("label_gral_descripcion", null, locale));

            for (UsuarioUen info : usersList) {
                row = sheet.createRow(index);
                row.createCell(0).setCellValue( "[" + info.getIdUsuario()+ "] - " + info.getNombreUsuario());
//                row.createCell(1).setCellValue(info.getNombreUsuario());
                index++;
            }

//            for (int i = 0; i < rowhead.getLastCellNum(); i++) {
//                rowhead.getCell(i).setCellStyle(styleHeader);
//                sheet.autoSizeColumn(i);
//            }

            sheet.protectSheet(PASSWORD_XLS);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    
    private static CellStyle loadStyleHeader(Workbook workbook) {

        CellStyle styleHeader = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setColor(IndexedColors.WHITE.getIndex());
        styleHeader.setFont(font);
        styleHeader.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        styleHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        styleHeader.setAlignment(HorizontalAlignment.CENTER);
        styleHeader.setWrapText(true);

        return styleHeader;
    }
    
    public static void setCombosInfo(HSSFSheet sheet, int columnPosition, String[] infoCombo) {

        //CellRangeAddressList(index_of_starting_row, index_of_ending_row, index_of_starting_column,index_of_ending_column);
        CellRangeAddressList addressList = new CellRangeAddressList(ROW_START_POSITION, ROW_END_POSITION, columnPosition - 1, columnPosition - 1);
        DVConstraint dvConstraint = DVConstraint.createExplicitListConstraint(infoCombo);
        DataValidation dataValidation = new HSSFDataValidation(addressList, dvConstraint);

        dataValidation.setSuppressDropDownArrow(false);

        sheet.addValidationData(dataValidation);

    }
    
    private static void addValidacionCombo(Workbook workbook, Sheet sheetDatosTabla, Integer listUserSize, int colStart, int coluEnd, int tamTabla, String userSheetName) {
        DataValidationHelper dvHelper = sheetDatosTabla.getDataValidationHelper();
        if(workbook.getName(userSheetName)==null){
            Name namedCell = workbook.createName();
            namedCell.setNameName(userSheetName);
            namedCell.setRefersToFormula(userSheetName + "!$A$1:$A$" + listUserSize);
        }
        DataValidationConstraint dvConstraint = dvHelper.createFormulaListConstraint(userSheetName);
        CellRangeAddressList addressList = new CellRangeAddressList(1, tamTabla, colStart, coluEnd);
        DataValidation validation = dvHelper.createValidation(
                dvConstraint, addressList);
        validation.setSuppressDropDownArrow(true);
        validation.setShowErrorBox(true);
        sheetDatosTabla.addValidationData(validation);
    }
}
