package com.metalsa.reportes.repository;

import com.metalsa.reportes.model.Balanza;
import com.metalsa.reportes.model.BalanzaLinea;
import com.metalsa.reportes.model.EstadoCuenta;
import com.metalsa.reportes.model.EstadoCuentaProject;
import com.metalsa.reportes.model.ExportModel;
import com.metalsa.reportes.model.GastosProject;
import com.metalsa.reportes.model.Incrementos;
import com.metalsa.reportes.model.Meses;
import com.metalsa.reportes.model.Presupuesto;
import com.metalsa.reportes.model.PresupuestoAnual;
import com.metalsa.reportes.model.PresupuestoAnualLinea;
import com.metalsa.reportes.model.PresupuestoUen;
import com.metalsa.reportes.model.Transferencias;
import com.metalsa.reportes.model.Variacion;
import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import lombok.extern.log4j.Log4j;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Drawing;
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
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFName;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Oscar del Angel
 */
@Service
@Log4j
public class ExportReportServiceImpl implements ExportReportService {

    private enum COLORS {

        LIGHT_BLUE(217, 237, 247),
        BLUE(16, 146, 209),
        DARK_BLUE(32, 66, 148),
        LIGHT_GRAY(220, 220, 220),
        DARK_GRAY(86, 93, 94),
        GRAY(16, 146, 209),
        BORDER_DARK_BLUE(1, 53, 103);

        private final Color color;
        private final XSSFColor xssfcolor;

        COLORS(int red, int green, int blue) {
            this.color = new Color(red, green, blue);
            this.xssfcolor = new XSSFColor(this.color);
        }

        public Color getColor() {
            return color;
        }

        public XSSFColor getXssfcolor() {
            return xssfcolor;
        }

    };

    private enum FORMATS {
        CURRENCY((short) 8),
        PERCENT("0.00%");

        private short INDEX_FORMAT;
        private String STRING_FORMAT;

//        private final int format;
        FORMATS(short format) {
            this.INDEX_FORMAT = format;
        }

        FORMATS(String format) {
            this.STRING_FORMAT = format;
        }

    };

    private static XSSFCellStyle TEXT_STYLE;
    private static XSSFCellStyle NUMBER_STYLE;
    private static XSSFCellStyle PERCENT_STYLE;
    private static XSSFCellStyle HEADER_STYLE;
    private static XSSFCellStyle SUB_HEADER_STYLE;
    private static Locale LOCALE;

    @Qualifier("application")
    @Autowired
    private MessageSource messages;

    @Override
    public ByteArrayInputStream getReporteVariacion(ExportModel model) {
        log.debug("entramos en ExportReportServiceImpl.getReporteVariacion");
        LOCALE = new Locale(model.getIdioma());

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(messages.getMessage("lbl_variacion", null, LOCALE));

        HEADER_STYLE = getHeaderStyle(workbook);
        SUB_HEADER_STYLE = getSubHeaderStyle(workbook);
        SUB_HEADER_STYLE.setDataFormat(FORMATS.CURRENCY.INDEX_FORMAT);
        TEXT_STYLE = getDataStyle(workbook);
        NUMBER_STYLE = getDataStyle(workbook);
        NUMBER_STYLE.setDataFormat(FORMATS.CURRENCY.INDEX_FORMAT);
        PERCENT_STYLE = getDataStyle(workbook);
        PERCENT_STYLE.setDataFormat(workbook.createDataFormat().getFormat(FORMATS.PERCENT.STRING_FORMAT));

        Index idx = new Index(1, 0);
        final List<Variacion> variaciones = model.getVariaciones();
        XSSFRow rccheader;
        CellRangeAddress ccregion, respregion;
        Drawing drw;
        try {
            this.createFilterHeaders(model, workbook, sheet, idx);
            drw = sheet.createDrawingPatriarch();
            idx.getNextRow();

            Collections.reverse(variaciones);

            for (Variacion data : variaciones) {

                rccheader = sheet.createRow(idx.getNextRow());
                this.addTextCell(rccheader, idx.getNextCol(), messages.getMessage("lbl_centro_costo", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rccheader, idx.getNextCol(), data.getCodigoCC() + " - " + data.getNombreCC(), TEXT_STYLE);
                ccregion = new CellRangeAddress(idx.getRow(), idx.getRow(), idx.getCol(), idx.getNextCol(3));
                addRegionBorder(sheet, ccregion, COLORS.BORDER_DARK_BLUE.getXssfcolor());
                sheet.addMergedRegion(ccregion);

                this.addTextCell(rccheader, idx.getNextCol(), messages.getMessage("lbl_responsable", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rccheader, idx.getNextCol(), data.getResponsable(), TEXT_STYLE);
                respregion = new CellRangeAddress(idx.getRow(), idx.getRow(), idx.getCol(), idx.getNextCol(3));
                addRegionBorder(sheet, respregion, COLORS.BORDER_DARK_BLUE.getXssfcolor());
                sheet.addMergedRegion(respregion);

                this.mapBudgetHeader(sheet, messages.getMessage("lbl_categoria", null, LOCALE), idx);
                this.mapBudgetValue(sheet, data.getTotal(), messages.getMessage("lbl_total", null, LOCALE), idx, SUB_HEADER_STYLE);

                if (data.getChartImages() != null) {
                    data.getChartImages().forEach(image -> {
                        int pictureureIdx = workbook.addPicture(image, XSSFWorkbook.PICTURE_TYPE_DIB);
                        ClientAnchor anchor = workbook.getCreationHelper().createClientAnchor();
                        anchor.setCol1(idx.getNextCol(2));
                        anchor.setRow1(idx.getRow() - 2);
                        drw.createPicture(anchor, pictureureIdx).resize();
                        idx.getNextCol(8);
                    });
                }

                data.getLineas().forEach(linea -> this.mapBudgetValue(sheet, linea, linea.getNombreCategoria(), idx, NUMBER_STYLE));
                idx.getNextRow();
            }
            System.gc();
            sheet.setDisplayGridlines(false);

            idx.row = 0;
            idx.col = 0;
            XSSFSheet sheet2 = workbook.createSheet(messages.getMessage("lbl_variacion", null, LOCALE) + "__");
            this.mapBudgetHeader(sheet2, messages.getMessage("lbl_categoria", null, LOCALE), idx);
            this.addTextCell(sheet2.getRow(idx.getRow()), 0, messages.getMessage("lbl_centro_costo", null, LOCALE), HEADER_STYLE);
            
            log.debug("inicia segundo ciclo");
            for (Variacion data : variaciones) {
                log.debug("Ciclo 2 CodigoCC :" + data.getCodigoCC() + " NombreCC :" + data.getNombreCC() + " Responsable :" + data.getResponsable() 
                        + " Total :" + data.getTotal() + " Lineas :" + data.getLineas().size());
                data.getLineas().forEach(linea -> {
                    this.mapBudgetValue(sheet2, linea, linea.getNombreCategoria(), idx, NUMBER_STYLE);
                    this.addTextCell(sheet2.getRow(idx.getRow()), 0, linea.getCodigoCC() + " " + linea.getNombreCC(), TEXT_STYLE);
                });
            }
            log.debug("termino segundo ciclo");

            workbook.write(out);
            workbook.close();
        } catch (IOException | NoSuchMessageException e) {
            log.debug(e.getLocalizedMessage(), e);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    @Override
    public ByteArrayInputStream getReporteEstadoCuentaCC(ExportModel model) {

        LOCALE = new Locale(model.getIdioma());

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XSSFWorkbook workbook = new XSSFWorkbook();

        HEADER_STYLE = getHeaderStyle(workbook);
        SUB_HEADER_STYLE = getSubHeaderStyle(workbook);
        SUB_HEADER_STYLE.setDataFormat(FORMATS.CURRENCY.INDEX_FORMAT);
        TEXT_STYLE = getDataStyle(workbook);
        NUMBER_STYLE = getDataStyle(workbook);
        NUMBER_STYLE.setDataFormat(FORMATS.CURRENCY.INDEX_FORMAT);
        PERCENT_STYLE = getDataStyle(workbook);
        PERCENT_STYLE.setDataFormat(workbook.createDataFormat().getFormat(FORMATS.PERCENT.STRING_FORMAT));

        try {

            Index idx = new Index(1, 0);
            XSSFSheet sheetvariacion = workbook.createSheet(messages.getMessage("lbl_estado_cuenta_cc", null, LOCALE));
            sheetvariacion.setDisplayGridlines(false);
            this.createFilterHeaders(model, workbook, sheetvariacion, idx);
            idx.getNextRow();

            try {
                if (model.getVariaciones() != null && false == model.getVariaciones().isEmpty()) {

                    int temprow1 = idx.row;

                    Variacion data = model.getVariaciones().get(0);
                    this.mapBudgetHeader(sheetvariacion, messages.getMessage("lbl_categoria", null, LOCALE), idx);
                    this.mapBudgetValue(sheetvariacion, data.getTotal(), messages.getMessage("lbl_total", null, LOCALE), idx, SUB_HEADER_STYLE);
                    data.getLineas().forEach(linea -> this.mapBudgetValue(sheetvariacion, linea, linea.getNombreCategoria(), idx, NUMBER_STYLE));
                    int temprow2 = idx.row;

                    idx.row = temprow1;
                    int col = 12;

                    XSSFRow r1 = sheetvariacion.getRow(idx.getNextRow());
                    this.addTextCell(r1, col, messages.getMessage("lbl_gasto_real", null, LOCALE), (XSSFCellStyle) HEADER_STYLE.clone()).getCellStyle().setAlignment(HorizontalAlignment.CENTER);;
                    CellRangeAddress lblgastoreal = new CellRangeAddress(idx.getRow(), idx.getRow(), col, col + 1);
                    addRegionBorder(sheetvariacion, lblgastoreal, COLORS.BORDER_DARK_BLUE.getXssfcolor());
                    sheetvariacion.addMergedRegion(lblgastoreal);

                    Row r2 = sheetvariacion.getRow(idx.getNextRow());
                    this.addNumberCell(r2, col, data.getTotal().getErogado(), (XSSFCellStyle) NUMBER_STYLE.clone()).getCellStyle().setAlignment(HorizontalAlignment.CENTER);;
                    CellRangeAddress gastoreal = new CellRangeAddress(idx.getRow(), idx.getRow(), col, col + 1);
                    addRegionBorder(sheetvariacion, gastoreal, COLORS.BORDER_DARK_BLUE.getXssfcolor());
                    sheetvariacion.addMergedRegion(gastoreal);

                    r1 = sheetvariacion.getRow(idx.getNextRow());
                    this.addTextCell(r1, col, messages.getMessage("lbl_presupuesto_original", null, LOCALE), SUB_HEADER_STYLE);
                    this.addTextCell(r1, col + 1, messages.getMessage("lbl_presupuesto_ajustado", null, LOCALE), SUB_HEADER_STYLE);

                    r2 = sheetvariacion.getRow(idx.getNextRow());
                    this.addNumberCell(r2, col, data.getTotal().getOriginal(), NUMBER_STYLE);
                    this.addNumberCell(r2, col + 1, data.getTotal().getOperativo(), NUMBER_STYLE);

                    r1 = sheetvariacion.getRow(idx.getNextRow());
                    this.addTextCell(r1, col, messages.getMessage("lbl_dif_original_real", null, LOCALE), SUB_HEADER_STYLE);
                    this.addTextCell(r1, col + 1, messages.getMessage("lbl_dif_ajustado_real", null, LOCALE), SUB_HEADER_STYLE);

                    double dif1 = data.getTotal().getOriginal() - data.getTotal().getErogado();
                    double dif2 = data.getTotal().getOperativo() - data.getTotal().getErogado();
                    r2 = sheetvariacion.getRow(idx.getNextRow());
                    this.addNumberCell(r2, col, dif1, NUMBER_STYLE);
                    this.addNumberCell(r2, col + 1, dif2, NUMBER_STYLE);

                    r1 = sheetvariacion.getRow(idx.getNextRow());
                    this.addTextCell(r1, col, "% " + messages.getMessage("lbl_diferencia", null, LOCALE), SUB_HEADER_STYLE);
                    this.addTextCell(r1, col + 1, "% " + messages.getMessage("lbl_diferencia", null, LOCALE), SUB_HEADER_STYLE);

                    r2 = sheetvariacion.getRow(idx.getNextRow());

                    this.addNumberCell(r2, col,
                            data.getTotal().getOriginal() != 0
                            ? dif1 / data.getTotal().getOriginal()
                            : 0.0,
                            NUMBER_STYLE);

                    this.addNumberCell(r2, col + 1,
                            data.getTotal().getOperativo() != 0
                            ? dif2 / data.getTotal().getOperativo()
                            : 0.0,
                            NUMBER_STYLE);

                    sheetvariacion.setColumnWidth(col, 5000);
                    sheetvariacion.setColumnWidth(col + 1, 5000);
                    idx.row = temprow2;
                    idx.getNextRow();

                    if (data.getChartImages() != null) {
                        data.getChartImages().forEach(image -> {
                            int pictureureIdx = workbook.addPicture(image, Workbook.PICTURE_TYPE_PNG);
                            ClientAnchor anchor = workbook.getCreationHelper().createClientAnchor();
                            anchor.setCol1(idx.getNextCol());
                            anchor.setRow1(idx.getRow());
                            sheetvariacion.createDrawingPatriarch().createPicture(anchor, pictureureIdx).resize();
                            idx.row += 22;
                            idx.col = 0;
                        });
                        data.setChartImages(null);
                    }
                    System.gc();
                }
            } catch (Exception e) {
                log.debug(e.getMessage(), e);
            }

            try {
                idx.row = 0;
                idx.col = 0;
                //transferencias
                Sheet transferencias = workbook.createSheet(messages.getMessage("lbl_transferencias", null, LOCALE));
                transferencias.setDisplayGridlines(false);

                Row rheader = transferencias.createRow(idx.getNextRow());
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_uen_origen", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_uen_destino", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_categoria_cuenta_origen", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_categoria_cuenta_destino", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_periodo_origen", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_periodo_destino", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_centro_costo_origen", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_centro_costo_destino", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_numero_solicitud", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_transaccion", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_tipo_transaccion", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_fecha_solicitud", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_fecha_aprobacion", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_motivo", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_usuario", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_requisitor", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_aprobador_cc", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_aprobador_finanzas", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_responsable_cc", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_monto", null, LOCALE), HEADER_STYLE);

                if (model.getTransferencias() != null) {
                    for (Transferencias transferencia : model.getTransferencias()) {
                        Row row = transferencias.createRow(idx.getNextRow());
                        this.addTextCell(row, idx.getNextCol(), transferencia.getNombreUenOrigen(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), transferencia.getNombreUenDestino(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), transferencia.getCategoriaOrigen(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), transferencia.getCategoriaDestino(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), transferencia.getPeriodoOrigen(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), transferencia.getPeriodoDestino(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), transferencia.getCodigoCCOrigen() + "-" + transferencia.getNombreCCOrigen(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), transferencia.getCodigoCCDestino() + "-" + transferencia.getNombreCCDestino(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), transferencia.getNumSolicitud() == null
                                ? transferencia.getTrxIdDestino() == null ? "" : transferencia.getTrxIdDestino().toString()
                                : transferencia.getNumSolicitud().toString(), TEXT_STYLE);

                        this.addTextCell(row, idx.getNextCol(),
                                Objects.equals("I", transferencia.getTipoTransaccion())
                                ? messages.getMessage("lbl_incremento", null, LOCALE)
                                : messages.getMessage("lbl_decremento", null, LOCALE),
                                TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(),
                                Objects.equals(transferencia.getCodigoCCOrigen(), transferencia.getCodigoCCDestino())
                                ? messages.getMessage("lbl_interna", null, LOCALE)
                                : messages.getMessage("lbl_externa", null, LOCALE),
                                TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), transferencia.getFechaSolicitud(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), transferencia.getFechaAprobacion(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), transferencia.getRazon(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), transferencia.getCreador(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), transferencia.getSolicitante(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), transferencia.getAprobadorCC(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), transferencia.getAprobadorFinanzas(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), transferencia.getResponsableCC(), TEXT_STYLE);
                        this.addNumberCell(row, idx.getNextCol(), transferencia.getMonto(), NUMBER_STYLE);
                    }
                }
                for (int i = 1; i < 20; i++) {
                    transferencias.setColumnWidth(i, 5000);
                }
            } catch (Exception e) {
                log.debug(e.getMessage(), e);
            }

            try {
                idx.row = 0;
                idx.col = 0;

                //incrementos y decrementos
                Sheet movimientos = workbook.createSheet(messages.getMessage("lbl_inc_dec", null, LOCALE));
                Row rheader = movimientos.createRow(idx.getNextRow());
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_categoria_cuenta", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_periodo", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_num_solicitud", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_tipo_transaccion", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_fecha_solicitud", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_fecha_aprobacion", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_motivo", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_usuario", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_requisitor", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_aprobador_cc", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_aprobador_finanzas", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_responsable_cc", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_monto", null, LOCALE), HEADER_STYLE);

                if (model.getMovimientos() != null) {
                    for (Incrementos movimiento : model.getMovimientos()) {
                        Row row = movimientos.createRow(idx.getNextRow());
                        this.addTextCell(row, idx.getNextCol(), movimiento.getCategoria(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), movimiento.getPeriodo(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), movimiento.getNumSolicitud() == null ? movimiento.getTrxIdDestino() == null ? "" : movimiento.getTrxIdDestino().toString() : movimiento.getNumSolicitud().toString(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), messages.getMessage("lbl_" + movimiento.getTipoTransaccion(), null, LOCALE), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), movimiento.getFechaSolicitud(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), movimiento.getFechaAprobacion(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), movimiento.getRazon(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), movimiento.getCreador(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), movimiento.getSolicitante(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), movimiento.getAprobadorCC(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), movimiento.getAprobadorFinanzas(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), movimiento.getResponsableCC(), TEXT_STYLE);
                        this.addNumberCell(row, idx.getNextCol(), movimiento.getMonto(), NUMBER_STYLE);
                    }
                }
                for (int i = 1; i < 15; i++) {
                    movimientos.setColumnWidth(i, 5000);
                }
                movimientos.setDisplayGridlines(false);
            } catch (Exception e) {
                log.debug(e.getMessage(), e);
            }

            try {
                idx.row = 0;
                idx.col = 0;
                //gastos
                Sheet gastos = workbook.createSheet(messages.getMessage("lbl_gastos", null, LOCALE));
                Row rheader = gastos.createRow(idx.getNextRow());
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_categoria_cuenta_origen", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_fecha_solicitud", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_fecha_pre_aprobacion", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_fecha_aprobacion", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_folio", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_tipo_requisicion", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_requisicion", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_linea_requisicion", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_linea_proveedor", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_estatus_requisicion", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_orden_compra", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_Descripcion", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_cantidad", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_precio_unitario", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_monto", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_moneda", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_moneda_local", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_requisitor", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_pre_aprobador", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_aprobador_cc", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_cuenta_contable", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_nombre_cuenta", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_concatenado", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_categoria", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_familia", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_sub_familia", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_comprometido", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_erogado_recibido", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_erogado_facturado", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_total", null, LOCALE), HEADER_STYLE);

                if (model.getGastos() != null) {
                    for (EstadoCuenta gasto : model.getGastos()) {
                        Row row = gastos.createRow(idx.getNextRow());
                        this.addTextCell(row, idx.getNextCol(), gasto.getCategoriaCuenta(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), gasto.getFechaSolicitud(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), gasto.getFechaPreAprobacion(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), gasto.getFechaAprobacion(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), gasto.getFolio(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), gasto.getTipoRequisicion(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), gasto.getRequisicion() == null ? "" : gasto.getRequisicion().toString(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), gasto.getPartida() == null ? "" : gasto.getPartida().toString(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), gasto.getProveedor() == null ? "" : gasto.getProveedor(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), gasto.getEstatus() == null ? "" : gasto.getEstatus(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), gasto.getOrdenCompra() == null ? "" : gasto.getOrdenCompra().toString(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), gasto.getDescripcion(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), gasto.getCantidad() == null ? "" : gasto.getCantidad().toString(), TEXT_STYLE);
                        this.addNumberCell(row, idx.getNextCol(), gasto.getPrecio(), NUMBER_STYLE);
                        this.addNumberCell(row, idx.getNextCol(), gasto.getMonto(), NUMBER_STYLE);
                        this.addTextCell(row, idx.getNextCol(), gasto.getMoneda(), TEXT_STYLE);
                        this.addNumberCell(row, idx.getNextCol(),gasto.getMoneda_local()== null ? 0 : Double.valueOf(gasto.getMoneda_local()), NUMBER_STYLE);
                        this.addTextCell(row, idx.getNextCol(), gasto.getSolicitante(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), gasto.getPreAprobador(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), gasto.getAprobador(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), gasto.getCuentaContable(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), gasto.getNombreCuenta(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), gasto.getConcaten(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), gasto.getCategoria(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), gasto.getFamilia(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), gasto.getSubfamilia(), TEXT_STYLE);
                        this.addNumberCell(row, idx.getNextCol(), gasto.getComprometido(), NUMBER_STYLE);
                        this.addNumberCell(row, idx.getNextCol(), gasto.getErogadoRecibido(), NUMBER_STYLE);
                        this.addNumberCell(row, idx.getNextCol(), gasto.getErogadoFacturado(), NUMBER_STYLE);
                        this.addNumberCell(row, idx.getNextCol(), gasto.getTotal(), NUMBER_STYLE);
                    }
                }
                for (int i = 1; i < 28; i++) {
                    gastos.setColumnWidth(i, 5000);
                }
                gastos.setDisplayGridlines(false);
            } catch (Exception e) {
                log.debug(e.getMessage(), e);
            }

            workbook.write(out);
            workbook.close();
        } catch (IOException | NoSuchMessageException e) {
            log.debug(e.getLocalizedMessage(), e);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    @Override
    public ByteArrayInputStream getReporteMovimientos(ExportModel model) {
        LOCALE = new Locale(model.getIdioma());

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XSSFWorkbook workbook = new XSSFWorkbook();

        HEADER_STYLE = getHeaderStyle(workbook);
        SUB_HEADER_STYLE = getSubHeaderStyle(workbook);
        SUB_HEADER_STYLE.setDataFormat(FORMATS.CURRENCY.INDEX_FORMAT);
        TEXT_STYLE = getDataStyle(workbook);
        NUMBER_STYLE = getDataStyle(workbook);
        NUMBER_STYLE.setDataFormat(FORMATS.CURRENCY.INDEX_FORMAT);
        PERCENT_STYLE = getDataStyle(workbook);
        PERCENT_STYLE.setDataFormat(workbook.createDataFormat().getFormat(FORMATS.PERCENT.STRING_FORMAT));

        try {

            Sheet movimientos = workbook.createSheet(messages.getMessage("lbl_inc_dec", null, LOCALE));
            movimientos.setDisplayGridlines(false);

            Index idx = new Index(1, 0);
            this.createFilterHeaders(model, workbook, movimientos, idx);
            idx.getNextRow();

            //incrementos y decrementos
            Row rheader = movimientos.createRow(idx.getNextRow());
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_uen", null, LOCALE), HEADER_STYLE);
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_categoria_cuenta", null, LOCALE), HEADER_STYLE);
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_periodo", null, LOCALE), HEADER_STYLE);
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_centro_costo", null, LOCALE), HEADER_STYLE);
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_num_solicitud", null, LOCALE), HEADER_STYLE);
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_transaccion", null, LOCALE), HEADER_STYLE);
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_fecha_solicitud", null, LOCALE), HEADER_STYLE);
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_fecha_necesidad", null, LOCALE), HEADER_STYLE);
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_motivo", null, LOCALE), HEADER_STYLE);
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_usuario", null, LOCALE), HEADER_STYLE);
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_requisitor", null, LOCALE), HEADER_STYLE);
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_fecha_pre_aprobacion", null, LOCALE), HEADER_STYLE);
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_aprobador_cc", null, LOCALE), HEADER_STYLE);
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_fecha_aprobacion", null, LOCALE), HEADER_STYLE);
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_aprobador_finanzas", null, LOCALE), HEADER_STYLE);
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_responsable_cc", null, LOCALE), HEADER_STYLE);
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_presupuesto_per", null, LOCALE), HEADER_STYLE);
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_monto", null, LOCALE), HEADER_STYLE);
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_moneda", null, LOCALE), HEADER_STYLE);
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_monto_inicial_cat_cuenta", null, LOCALE), HEADER_STYLE);
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_monto_final_cat_cuenta", null, LOCALE), HEADER_STYLE);

            for (Incrementos movimiento : model.getMovimientos()) {
                Row row = movimientos.createRow(idx.getNextRow());
                this.addTextCell(row, idx.getNextCol(), movimiento.getNombreUenDestino(), TEXT_STYLE);
                this.addTextCell(row, idx.getNextCol(), movimiento.getCategoria(), TEXT_STYLE);
                this.addTextCell(row, idx.getNextCol(), movimiento.getPeriodo(), TEXT_STYLE);
                this.addTextCell(row, idx.getNextCol(), movimiento.getCodigoCC() + "-" + movimiento.getNombre_cc(), TEXT_STYLE);
                this.addTextCell(row, idx.getNextCol(), movimiento.getNumSolicitud() == null ? "" : movimiento.getNumSolicitud().toString(), TEXT_STYLE);
                this.addTextCell(row, idx.getNextCol(), getTransactionType(movimiento.getTipoTransaccion()), TEXT_STYLE);
                this.addTextCell(row, idx.getNextCol(), movimiento.getFechaSolicitud(), TEXT_STYLE);
                this.addTextCell(row, idx.getNextCol(), movimiento.getFechaNecesidad(), TEXT_STYLE);
                this.addTextCell(row, idx.getNextCol(), movimiento.getRazon(), TEXT_STYLE);
                this.addTextCell(row, idx.getNextCol(), movimiento.getCreador(), TEXT_STYLE);
                this.addTextCell(row, idx.getNextCol(), movimiento.getSolicitante(), TEXT_STYLE);
                this.addTextCell(row, idx.getNextCol(), movimiento.getFechaPreaprobacion(), TEXT_STYLE);
                this.addTextCell(row, idx.getNextCol(), movimiento.getAprobadorCC(), TEXT_STYLE);
                this.addTextCell(row, idx.getNextCol(), movimiento.getFechaAprobacion(), TEXT_STYLE);
                this.addTextCell(row, idx.getNextCol(), movimiento.getAprobadorFinanzas(), TEXT_STYLE);
                this.addTextCell(row, idx.getNextCol(), movimiento.getResponsableCC(), TEXT_STYLE);
                this.addNumberCell(row, idx.getNextCol(), movimiento.getPorcentajeDestino() == null ? 0.0 : movimiento.getPorcentajeDestino(), PERCENT_STYLE);
                this.addNumberCell(row, idx.getNextCol(), movimiento.getMonto(), NUMBER_STYLE);
                this.addTextCell(row, idx.getNextCol(), movimiento.getMoneda(), TEXT_STYLE);
                this.addNumberCell(row, idx.getNextCol(), movimiento.getSaldoDestino() == null ? 0.0 : movimiento.getSaldoDestino(), NUMBER_STYLE);
                this.addNumberCell(row, idx.getNextCol(), movimiento.getSaldoFinalDestino() == null ? 0.0 : movimiento.getSaldoFinalDestino(), NUMBER_STYLE);
            }

            for (int i = 1; i < 23; i++) {
                movimientos.setColumnWidth(i, 5000);
            }

            workbook.write(out);
            workbook.close();
        } catch (IOException | NoSuchMessageException e) {
            log.debug(e.getLocalizedMessage(), e);
        } catch (Throwable ex) {
            log.debug(ex.getLocalizedMessage(), ex);
        }
        return new ByteArrayInputStream(out.toByteArray());
    }

    @Override
    public ByteArrayInputStream getReporteTransferencias(ExportModel model) {
        LOCALE = new Locale(model.getIdioma());

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XSSFWorkbook workbook = new XSSFWorkbook();

        HEADER_STYLE = getHeaderStyle(workbook);
        SUB_HEADER_STYLE = getSubHeaderStyle(workbook);
        SUB_HEADER_STYLE.setDataFormat(FORMATS.CURRENCY.INDEX_FORMAT);
        TEXT_STYLE = getDataStyle(workbook);
        NUMBER_STYLE = getDataStyle(workbook);
        NUMBER_STYLE.setDataFormat(FORMATS.CURRENCY.INDEX_FORMAT);
        PERCENT_STYLE = getDataStyle(workbook);
        PERCENT_STYLE.setDataFormat(workbook.createDataFormat().getFormat(FORMATS.PERCENT.STRING_FORMAT));

        try {
            //transferencias
            Sheet transferencias = workbook.createSheet(messages.getMessage("lbl_transferencias", null, LOCALE));
            transferencias.setDisplayGridlines(false);

            Index idx = new Index(1, 0);
            this.createFilterHeaders(model, workbook, transferencias, idx);
            idx.getNextRow();

            Row rheader = transferencias.createRow(idx.getNextRow());
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_uen_origen", null, LOCALE), HEADER_STYLE);
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_uen_destino", null, LOCALE), HEADER_STYLE);
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_categoria_cuenta_origen", null, LOCALE), HEADER_STYLE);
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_categoria_cuenta_destino", null, LOCALE), HEADER_STYLE);
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_periodo_origen", null, LOCALE), HEADER_STYLE);
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_periodo_destino", null, LOCALE), HEADER_STYLE);
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_centro_costo_origen", null, LOCALE), HEADER_STYLE);
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_centro_costo_destino", null, LOCALE), HEADER_STYLE);
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_numero_solicitud", null, LOCALE), HEADER_STYLE);
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_estatus_solicitud", null, LOCALE), HEADER_STYLE);
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_transaccion", null, LOCALE), HEADER_STYLE);
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_tipo_transaccion", null, LOCALE), HEADER_STYLE);
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_fecha_solicitud", null, LOCALE), HEADER_STYLE);
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_fecha_necesidad", null, LOCALE), HEADER_STYLE);
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_monto_solicitado", null, LOCALE), HEADER_STYLE);
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_motivo", null, LOCALE), HEADER_STYLE);
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_usuario", null, LOCALE), HEADER_STYLE);
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_requisitor", null, LOCALE), HEADER_STYLE);
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_fecha_pre_aprobacion", null, LOCALE), HEADER_STYLE);
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_aprobador_cc", null, LOCALE), HEADER_STYLE);
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_fecha_aprobacion", null, LOCALE), HEADER_STYLE);
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_aprobador_finanzas", null, LOCALE), HEADER_STYLE);
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_responsable_cc", null, LOCALE), HEADER_STYLE);
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_presupuesto_per", null, LOCALE), HEADER_STYLE);
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_monto", null, LOCALE), HEADER_STYLE);
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_moneda", null, LOCALE), HEADER_STYLE);
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_monto_inicial_cat_cuenta_origen", null, LOCALE), HEADER_STYLE);
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_monto_final_cat_cuenta_origen", null, LOCALE), HEADER_STYLE);
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_monto_inicial_cat_cuenta_destino", null, LOCALE), HEADER_STYLE);
            this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_monto_final_cat_cuenta_destino", null, LOCALE), HEADER_STYLE);

            for (Transferencias transferencia : model.getTransferencias()) {
                Row row = transferencias.createRow(idx.getNextRow());
                this.addTextCell(row, idx.getNextCol(), transferencia.getNombreUenOrigen(), TEXT_STYLE);
                this.addTextCell(row, idx.getNextCol(), transferencia.getNombreUenDestino(), TEXT_STYLE);
                this.addTextCell(row, idx.getNextCol(), transferencia.getCategoriaOrigen(), TEXT_STYLE);
                this.addTextCell(row, idx.getNextCol(), transferencia.getCategoriaDestino(), TEXT_STYLE);
                this.addTextCell(row, idx.getNextCol(), transferencia.getPeriodoOrigen(), TEXT_STYLE);
                this.addTextCell(row, idx.getNextCol(), transferencia.getPeriodoDestino(), TEXT_STYLE);
                this.addTextCell(row, idx.getNextCol(), transferencia.getCodigoCCOrigen() + "-" + transferencia.getNombreCCOrigen(), TEXT_STYLE);
                this.addTextCell(row, idx.getNextCol(), transferencia.getCodigoCCDestino() + "-" + transferencia.getNombreCCDestino(), TEXT_STYLE);

                this.addTextCell(row, idx.getNextCol(), transferencia.getNumSolicitud() == null
                        ? transferencia.getTrxIdDestino() == null ? "" : transferencia.getTrxIdDestino().toString()
                        : transferencia.getNumSolicitud().toString(), TEXT_STYLE);
                this.addTextCell(row, idx.getNextCol(), transferencia.getEstatus(), TEXT_STYLE);
                this.addTextCell(row, idx.getNextCol(), getTransactionType(transferencia.getTipoTransaccion()), TEXT_STYLE);
                this.addTextCell(row, idx.getNextCol(),
                        Objects.equals(transferencia.getCodigoCCOrigen(), transferencia.getCodigoCCDestino())
                        ? messages.getMessage("lbl_interna", null, LOCALE)
                        : messages.getMessage("lbl_externa", null, LOCALE),
                        TEXT_STYLE);
                this.addTextCell(row, idx.getNextCol(), transferencia.getFechaSolicitud(), TEXT_STYLE);
                this.addTextCell(row, idx.getNextCol(), transferencia.getFechaNecesidad(), TEXT_STYLE);
                this.addNumberCell(row, idx.getNextCol(), transferencia.getMonto(), NUMBER_STYLE);
                this.addTextCell(row, idx.getNextCol(), transferencia.getRazon(), TEXT_STYLE);
                this.addTextCell(row, idx.getNextCol(), transferencia.getCreador(), TEXT_STYLE);
                this.addTextCell(row, idx.getNextCol(), transferencia.getSolicitante(), TEXT_STYLE);
                this.addTextCell(row, idx.getNextCol(), transferencia.getFechaPreaprobacion(), TEXT_STYLE);
                this.addTextCell(row, idx.getNextCol(), transferencia.getAprobadorCC(), TEXT_STYLE);
                this.addTextCell(row, idx.getNextCol(), transferencia.getFechaAprobacion(), TEXT_STYLE);
                this.addTextCell(row, idx.getNextCol(), transferencia.getAprobadorFinanzas(), TEXT_STYLE);
                this.addTextCell(row, idx.getNextCol(), transferencia.getResponsableCC(), TEXT_STYLE);
                this.addNumberCell(row, idx.getNextCol(), transferencia.getPorcentajeDestino() == null ? 0.0 : transferencia.getPorcentajeDestino(), PERCENT_STYLE);
                this.addNumberCell(row, idx.getNextCol(), transferencia.getMonto(), NUMBER_STYLE);
                this.addTextCell(row, idx.getNextCol(), transferencia.getMoneda(), TEXT_STYLE);
                this.addNumberCell(row, idx.getNextCol(), transferencia.getSaldoOrigen() == null ? 0.0 : transferencia.getSaldoOrigen(), NUMBER_STYLE);
                this.addNumberCell(row, idx.getNextCol(), transferencia.getSaldoFinalOrigen() == null ? 0.0 : transferencia.getSaldoFinalOrigen(), NUMBER_STYLE);
                this.addNumberCell(row, idx.getNextCol(), transferencia.getSaldoDestino() == null ? 0.0 : transferencia.getSaldoDestino(), NUMBER_STYLE);
                this.addNumberCell(row, idx.getNextCol(), transferencia.getSaldoFinalDestino() == null ? 0.0 : transferencia.getSaldoFinalDestino(), NUMBER_STYLE);
            }
            for (int i = 1; i < 28; i++) {
                transferencias.setColumnWidth(i, 5000);
            }
            workbook.write(out);
            workbook.close();
        } catch (IOException | NoSuchMessageException e) {
            log.debug(e.getLocalizedMessage(), e);
        }
        return new ByteArrayInputStream(out.toByteArray());
    }

    @Override
    public ByteArrayInputStream getReporteProject(ExportModel model) {
        log.debug(" **** getReporteProject *** ");

        com.google.gson.Gson gson = new com.google.gson.Gson();
        String strModel = gson.toJson(model);
        log.debug(" **** getReporteProject *** getGastosProject: " + strModel);

        LOCALE = new Locale(model.getIdioma());

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XSSFWorkbook workbook = new XSSFWorkbook();

        HEADER_STYLE = getHeaderStyle(workbook);
        SUB_HEADER_STYLE = getSubHeaderStyle(workbook);
        SUB_HEADER_STYLE.setDataFormat(FORMATS.CURRENCY.INDEX_FORMAT);
        TEXT_STYLE = getDataStyle(workbook);
        NUMBER_STYLE = getDataStyle(workbook);
        NUMBER_STYLE.setDataFormat(FORMATS.CURRENCY.INDEX_FORMAT);
        PERCENT_STYLE = getDataStyle(workbook);
        PERCENT_STYLE.setDataFormat(workbook.createDataFormat().getFormat(FORMATS.PERCENT.STRING_FORMAT));

        try {

            Index idx = new Index(1, 0);
            Sheet sheetProject = workbook.createSheet(messages.getMessage("lbl_estado_cuenta_proyecto", null, LOCALE));
            sheetProject.setDisplayGridlines(false);
            this.createFilterHeadersProject(model, workbook, sheetProject, idx);
            idx.getNextRow();

            try {
                if (model.getEdoCuentaProject() != null && false == model.getEdoCuentaProject().isEmpty()) {
                    int temprow1 = idx.row;
                    EstadoCuentaProject data = model.getEdoCuentaProject().get(0);
                    this.mapBudgetHeaderProject(sheetProject, messages.getMessage("lbl_proy_tarea", null, LOCALE), idx);
                    this.mapBudgetValueProject(sheetProject, model.getTotalesProy(), messages.getMessage("lbl_total", null, LOCALE), "", idx, SUB_HEADER_STYLE);
                    model.getEdoCuentaProject().forEach(linea -> this.mapBudgetValueProject(sheetProject, linea.getTotal(), linea.getTarea(), linea.getTipoGasto(), idx, NUMBER_STYLE));
                    int temprow2 = idx.row;

                    idx.row = temprow1;
                    int col = 12;

                    sheetProject.setColumnWidth(col, 5000);
                    sheetProject.setColumnWidth(col + 1, 5000);
                    idx.row = temprow2;
                    idx.getNextRow();

                    System.gc();
                }
            } catch (Exception e) {
                log.debug(e.getMessage(), e);
            }

            try {
                idx.row = 0;
                idx.col = 0;
                //gastos
                Sheet gastos = workbook.createSheet(messages.getMessage("lbl_gastos", null, LOCALE));
                Row rheader = gastos.createRow(idx.getNextRow());
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_proy_tarea", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_fecha_solicitud", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_fecha_pre_aprobacion", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_fecha_aprobacion", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_folio", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_fuente", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_tipo_requisicion", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_ora_requisicion", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_requisicion", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_linea_requisicion", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_linea_proveedor", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_orden_compra", null, LOCALE), HEADER_STYLE);
                
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_linea_oc", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_factura", null, LOCALE), HEADER_STYLE);
                
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_Descripcion", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_cantidad", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_precio_unitario", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_monto", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_moneda", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_moneda_local", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_pre_aprobador", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_aprobador", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_requisitor", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_proy_tipo_gasto", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_concatenado", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_estatus_requisicion", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_categoria", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_familia", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_sub_familia", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_comprometido", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_erogado_recibido", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_erogado_facturado", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rheader, idx.getNextCol(), messages.getMessage("lbl_total", null, LOCALE), HEADER_STYLE);

                if (model.getGastosProject() != null) {
                    for (GastosProject gasto : model.getGastosProject()) {
                        log.debug(" **** getReporteProject *** " + gasto);
                        Row row = gastos.createRow(idx.getNextRow());
                        this.addTextCell(row, idx.getNextCol(), gasto.getProyectoTarea(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), gasto.getFechaSolicitud(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), gasto.getFechaPreaprobacion(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), gasto.getFechaAprobacion(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), gasto.getFolio() == null ? "" : gasto.getFolio().toString(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), gasto.getFuente() == null ? "" : gasto.getFuente(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), gasto.getTipoRequisicion(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), gasto.getOraRequisicion() == null ? "" : gasto.getOraRequisicion(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), gasto.getIdRequisicion() == null ? "" : gasto.getIdRequisicion().toString(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), gasto.getId_partida() == null ? "" : gasto.getId_partida().toString(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), gasto.getProveedor() == null ? "" : gasto.getProveedor(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), gasto.getOrden_compra() == null ? "" : gasto.getOrden_compra().toString(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), gasto.getOrdenCompraLinea()== null ? "" : gasto.getOrdenCompraLinea().toString(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), gasto.getFactura() == null ? "" : gasto.getFactura().toString(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), gasto.getDescripcion(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), gasto.getCantidad() == null ? "" : gasto.getCantidad().toString(), TEXT_STYLE);
                        this.addNumberCell(row, idx.getNextCol(), gasto.getPrecio(), NUMBER_STYLE);
                        this.addNumberCell(row, idx.getNextCol(), gasto.getMonto(), NUMBER_STYLE);
                        this.addTextCell(row, idx.getNextCol(), gasto.getMoneda(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), gasto.getMonedaLocal(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), gasto.getPreAprobador(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), gasto.getAprobador(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), gasto.getSolicitante(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), gasto.getTipoGasto(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), gasto.getConcaten(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), gasto.getEstatus() == null ? "" : gasto.getEstatus(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), gasto.getCategoria(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), gasto.getFamilia(), TEXT_STYLE);
                        this.addTextCell(row, idx.getNextCol(), gasto.getSubfamilia(), TEXT_STYLE);
                        this.addNumberCell(row, idx.getNextCol(), gasto.getComprometido(), NUMBER_STYLE);
                        this.addNumberCell(row, idx.getNextCol(), gasto.getErogadoRecibido(), NUMBER_STYLE);
                        this.addNumberCell(row, idx.getNextCol(), gasto.getErogadoFacturado(), NUMBER_STYLE);
                        this.addNumberCell(row, idx.getNextCol(), gasto.getTotal(), NUMBER_STYLE);
                    }
                }
                for (int i = 1; i < 28; i++) {
                    gastos.setColumnWidth(i, 5000);
                }
                gastos.setDisplayGridlines(false);
            } catch (Exception e) {
                log.debug(e.getMessage(), e);
            }

            workbook.write(out);
            workbook.close();
        } catch (IOException | NoSuchMessageException e) {
            log.debug(e.getLocalizedMessage(), e);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    @Override
    public ByteArrayInputStream getReporteEdoCuentaUen(ExportModel model) {

        LOCALE = new Locale(model.getIdioma());

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XSSFWorkbook workbook = new XSSFWorkbook();

        HEADER_STYLE = getHeaderStyle(workbook);
        SUB_HEADER_STYLE = getSubHeaderStyle(workbook);
        SUB_HEADER_STYLE.setDataFormat(FORMATS.CURRENCY.INDEX_FORMAT);
        TEXT_STYLE = getDataStyle(workbook);
        NUMBER_STYLE = getDataStyle(workbook);
        NUMBER_STYLE.setDataFormat(FORMATS.CURRENCY.INDEX_FORMAT);
        PERCENT_STYLE = getDataStyle(workbook);
        PERCENT_STYLE.setDataFormat(workbook.createDataFormat().getFormat(FORMATS.PERCENT.STRING_FORMAT));

        try {

            this.PresupuestoUen(workbook, model, model.getPresupuestoUenCC(), this.messages.getMessage("lbl_centro_costo", null, LOCALE), "lbl_edo_cuenta_por_cc");
            this.PresupuestoUen(workbook, model, model.getPresupuestoUenCat(), this.messages.getMessage("lbl_categoria", null, LOCALE), "lbl_edo_cuenta_por_cat");

            this.PresupuestoUen2(workbook, model, model.getPresupuestoUenCC(), this.messages.getMessage("lbl_centro_costo", null, LOCALE), "lbl_por_cc");
            this.PresupuestoUen2(workbook, model, model.getPresupuestoUenCat(), this.messages.getMessage("lbl_categoria", null, LOCALE), "lbl_por_cat");

            workbook.write(out);
            workbook.close();
        } catch (IOException | NoSuchMessageException ex) {
            log.debug(ex.getLocalizedMessage(), ex);
        }
        return new ByteArrayInputStream(out.toByteArray());
    }

    private void PresupuestoUen(XSSFWorkbook workbook, ExportModel model, PresupuestoUen budget, String group, String sheetname) {
        if (budget == null) {
            return;
        }

        Sheet sheet = workbook.createSheet(this.messages.getMessage(sheetname, null, LOCALE));
        sheet.setDisplayGridlines(false);

        Index index = new Index();
        this.createFilterHeaders(model, workbook, sheet, index);

        //esto para los totales de las uen
        if (budget.getLineas() != null) {
            index.getNextRow();
            budget.setNombreUen(messages.getMessage("lbl_all", null, LOCALE));
            this.buildUenHeader(budget, sheet, index);
            this.buildUenBody(budget, sheet, group, index);
        }

        index.setRow(index.row + 2);
        for (PresupuestoUen presupuesto : budget.getPresupuestos()) {
            this.buildUenHeader(presupuesto, sheet, index);
            this.buildUenBody(presupuesto, sheet, group, index);
        }

        int cidx = 0;
        sheet.setColumnWidth(++cidx, 6000);
        while (++cidx < 11) {
            sheet.setColumnWidth(cidx, 5000);
        }
    }

    private void PresupuestoUen2(XSSFWorkbook workbook, ExportModel model, PresupuestoUen budget, String group, String sheetname) {
        if (budget == null) {
            return;
        }

        Sheet sheet = workbook.createSheet(this.messages.getMessage(sheetname, null, LOCALE));
        sheet.setDisplayGridlines(false);

        Index index = new Index(0, -1);
        this.createFilterHeaders(model, workbook, sheet, index);
        index.getNextRow();
        index.getNextRow();

        this.mapBudgetHeader(sheet, group, index);
        this.addTextCell(sheet.getRow(index.getRow()), 0, messages.getMessage("lbl_uen", null, LOCALE), HEADER_STYLE);

        //esto para los totales de las uen
        if (budget.getLineas() != null) {
            budget.getPresupuestos().add(0, new PresupuestoUen(messages.getMessage("lbl_all", null, LOCALE), budget.getLineas()));
        }

        budget.getPresupuestos().forEach(presupuesto -> {
            presupuesto.getLineas().forEach(linea -> {
                this.mapBudgetValue(sheet, linea, linea.getCustomName(), index, NUMBER_STYLE);
                this.addTextCell(sheet.getRow(index.getRow()), 0, presupuesto.getNombreUen(), TEXT_STYLE);
            });
        });

        int cidx = 0;
        sheet.setColumnWidth(++cidx, 6000);
        while (++cidx < 11) {
            sheet.setColumnWidth(cidx, 5000);
        }
    }

    @Override
    public ByteArrayInputStream getReporteBalanza(ExportModel model) {

        LOCALE = new Locale(model.getIdioma());

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(messages.getMessage("lbl_balanza", null, LOCALE));

        HEADER_STYLE = getHeaderStyle(workbook);
        SUB_HEADER_STYLE = getSubHeaderStyle(workbook);
        SUB_HEADER_STYLE.setDataFormat(FORMATS.CURRENCY.INDEX_FORMAT);
        TEXT_STYLE = getDataStyle(workbook);
        NUMBER_STYLE = getDataStyle(workbook);
        NUMBER_STYLE.setDataFormat(FORMATS.CURRENCY.INDEX_FORMAT);

        try {
            Index idx = new Index(1, 0);
            this.createFilterHeaders(model, workbook, sheet, idx);
            idx.getNextRow();

            for (Balanza data : model.getBalanza()) {

                Row rccheader = sheet.createRow(idx.getNextRow());
                this.addTextCell(rccheader, idx.getNextCol(), messages.getMessage("lbl_centro_costo", null, LOCALE), HEADER_STYLE);
                this.addTextCell(rccheader, idx.getNextCol(), data.getCodigoCC() + " - " + data.getNombreCC(), TEXT_STYLE);
                CellRangeAddress ccregion = new CellRangeAddress(idx.getRow(), idx.getRow(), idx.getCol(), idx.getNextCol(4));
                addRegionBorder(sheet, ccregion, COLORS.BORDER_DARK_BLUE.getXssfcolor());
                sheet.addMergedRegion(ccregion);

                this.addTextCell(rccheader, idx.getNextCol(), messages.getMessage("lbl_responsable", null, LOCALE), HEADER_STYLE);
                CellRangeAddress lblresppregion = new CellRangeAddress(idx.getRow(), idx.getRow(), idx.getCol(), idx.getNextCol());
                addRegionBorder(sheet, lblresppregion, COLORS.BORDER_DARK_BLUE.getXssfcolor());
                sheet.addMergedRegion(lblresppregion);

                this.addTextCell(rccheader, idx.getNextCol(), data.getResponsable(), TEXT_STYLE);
                CellRangeAddress respregion = new CellRangeAddress(idx.getRow(), idx.getRow(), idx.getCol(), idx.getNextCol(4));
                addRegionBorder(sheet, respregion, COLORS.BORDER_DARK_BLUE.getXssfcolor());
                sheet.addMergedRegion(respregion);

                Row rth = sheet.createRow(idx.getNextRow());
                this.mapearHeaderMeses(rth, idx, HEADER_STYLE);

                Row rowthd = sheet.createRow(idx.getNextRow());
                this.addTextCell(rowthd, idx.getNextCol(), messages.getMessage("lbl_total", null, LOCALE), SUB_HEADER_STYLE);
                this.mapearMeses(data.getTotales(), rowthd, null, idx, SUB_HEADER_STYLE);

                for (BalanzaLinea linea : data.getLineas()) {
                    Row tr = sheet.createRow(idx.getNextRow());
                    this.addTextCell(tr, idx.getNextCol(), linea.getNombreCategoria(), TEXT_STYLE);
                    this.mapearMeses(linea, tr, null, idx, NUMBER_STYLE);
                }
                idx.getNextRow();
            }

            sheet.setDisplayGridlines(false);
            idx.row = 0;
            idx.col = 0;
            Sheet sheet2 = workbook.createSheet(messages.getMessage("lbl_balanza", null, LOCALE) + "__");
            Row rthx = sheet2.createRow(idx.getNextRow());
            this.mapearHeaderMeses(rthx, idx, HEADER_STYLE);
            this.addTextCell(sheet2.getRow(idx.getRow()), 0, messages.getMessage("lbl_centro_costo", null, LOCALE), HEADER_STYLE);

            for (Balanza data : model.getBalanza()) {
                Row rowthd = sheet2.createRow(idx.getNextRow());
                this.addTextCell(rowthd, idx.getNextCol(), messages.getMessage("lbl_total", null, LOCALE), SUB_HEADER_STYLE);
                this.mapearMeses(data.getTotales(), rowthd, null, idx, SUB_HEADER_STYLE);
                data.getLineas().forEach(line -> {
                    Row trx = sheet2.createRow(idx.getNextRow());
                    this.mapearMeses(line, trx, line.getNombreCategoria(), idx, NUMBER_STYLE);
                    this.addTextCell(sheet2.getRow(idx.getRow()), 0, line.getCodigoCC() + " " + line.getNombreCC(), TEXT_STYLE);
                });

            }
            workbook.write(out);
            workbook.close();
        } catch (IOException | NoSuchMessageException e) {
            log.debug(e.getLocalizedMessage(), e);
        }
        return new ByteArrayInputStream(out.toByteArray());
    }

    @Override
    public ByteArrayInputStream getReportePresupuestoAnual(ExportModel model) {
        byte output[] = new byte[]{};
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(messages.getMessage("lbl_presupuesto_anual", null, LOCALE));
        sheet.setDisplayGridlines(false);

        Index idx = new Index(2, 0);

        LOCALE = new Locale(model.getIdioma());
        HEADER_STYLE = getHeaderStyle(workbook);
        SUB_HEADER_STYLE = getSubHeaderStyle(workbook);
        TEXT_STYLE = getDataStyle(workbook);
        NUMBER_STYLE = getDataStyle(workbook);
        NUMBER_STYLE.setDataFormat(FORMATS.CURRENCY.INDEX_FORMAT);

        this.createFilterHeaders(model, workbook, sheet, idx);
        idx.setRow(idx.getRow() + 2);
        final List<PresupuestoAnual> presupuesto = model.getPresupuestoAnual();
        Collections.reverse(presupuesto);
        for (PresupuestoAnual data : presupuesto) {

            Row rowccheader = sheet.createRow(idx.getNextRow());
            this.addTextCell(rowccheader, idx.getNextCol(), messages.getMessage("lbl_centro_costo", null, LOCALE), HEADER_STYLE);
            CellRangeAddress ccregiontext = new CellRangeAddress(idx.getRow(), idx.getRow(), idx.getCol(), idx.getNextCol());
            addRegionBorder(sheet, ccregiontext, COLORS.BORDER_DARK_BLUE.getXssfcolor());
            sheet.addMergedRegion(ccregiontext);

            this.addTextCell(rowccheader, idx.getNextCol(), data.getCodigoCC() + "-" + data.getNombreCC(), TEXT_STYLE);
            CellRangeAddress ccregion = new CellRangeAddress(idx.getRow(), idx.getRow(), idx.getCol(), idx.getNextCol(4));
            addRegionBorder(sheet, ccregion, COLORS.BORDER_DARK_BLUE.getXssfcolor());
            sheet.addMergedRegion(ccregion);

            this.addTextCell(rowccheader, idx.getNextCol(), messages.getMessage("lbl_responsable", null, LOCALE), HEADER_STYLE);
            CellRangeAddress respregiontext = new CellRangeAddress(idx.getRow(), idx.getRow(), idx.getCol(), idx.getNextCol());
            addRegionBorder(sheet, respregiontext, COLORS.BORDER_DARK_BLUE.getXssfcolor());
            sheet.addMergedRegion(respregiontext);

            this.addTextCell(rowccheader, idx.getNextCol(), data.getResponsable(), TEXT_STYLE);
            CellRangeAddress respregion = new CellRangeAddress(idx.getRow(), idx.getRow(), idx.getCol(), idx.getNextCol(4));
            addRegionBorder(sheet, respregion, COLORS.BORDER_DARK_BLUE.getXssfcolor());
            sheet.addMergedRegion(respregion);

            Row rsubheader = sheet.createRow(idx.getNextRow());
            mapearHeaderMeses(rsubheader, idx, SUB_HEADER_STYLE);
            this.addTextCell(rsubheader, idx.getNextCol(), messages.getMessage("lbl_total", null, LOCALE), SUB_HEADER_STYLE);

            if (data.getChartImages() != null) {
                data.getChartImages().forEach(image -> {
                    int pictureureIdx = workbook.addPicture(image, Workbook.PICTURE_TYPE_PNG);
                    ClientAnchor anchor = workbook.getCreationHelper().createClientAnchor();
                    anchor.setCol1(idx.getNextCol(2));
                    anchor.setRow1(idx.getRow() - 4);
                    sheet.createDrawingPatriarch().createPicture(anchor, pictureureIdx).resize();
                });
                data.setChartImages(null);
            }

            for (PresupuestoAnualLinea linea : data.getLineas()) {
                Row row = sheet.createRow(idx.getNextRow());
                this.addTextCell(row, idx.getNextCol(), linea.getTipo(), TEXT_STYLE);
                this.mapearMeses(linea, row, null, idx, NUMBER_STYLE);
                this.addNumberCell(row, idx.getNextCol(), linea.getTotal(), NUMBER_STYLE);
            }

            idx.getNextRow(4);
        }
        idx.row = 0;
        idx.col = 0;
        Sheet sheet2 = workbook.createSheet(messages.getMessage("lbl_presupuesto_anual", null, LOCALE) + "__");
        Row rthx = sheet2.createRow(idx.getNextRow());
        this.mapearHeaderMeses(rthx, idx, HEADER_STYLE);
        this.addTextCell(sheet2.getRow(idx.getRow()), 0, messages.getMessage("lbl_centro_costo", null, LOCALE), HEADER_STYLE);
        this.addTextCell(sheet2.getRow(idx.getRow()), idx.getNextCol(), messages.getMessage("lbl_total", null, LOCALE), HEADER_STYLE);

        for (PresupuestoAnual data : presupuesto) {
            data.getLineas().forEach(linea -> {
                Row trx = sheet2.createRow(idx.getNextRow());
                this.mapearMeses(linea, trx, linea.getTipo(), idx, NUMBER_STYLE);
                this.addTextCell(sheet2.getRow(idx.getRow()), 0, data.getCodigoCC() + " " + data.getNombreCC(), TEXT_STYLE);
                this.addNumberCell(trx, idx.getNextCol(), linea.getTotal(), NUMBER_STYLE);
            });
        }

        try {
            workbook.write(out);
            workbook.close();
            output = out.toByteArray();
        } catch (IOException e) {
            log.debug(e.getLocalizedMessage(), e);
        } catch (Throwable ex) {
            log.debug(ex.getLocalizedMessage(), ex);
        }

        return new ByteArrayInputStream(output);
    }

    public static XSSFCellStyle getBorderStyle(XSSFWorkbook workbook) {
        XSSFCellStyle borderStyle = workbook.createCellStyle();
        borderStyle.setBorderTop(BorderStyle.THIN);
        borderStyle.setBorderRight(BorderStyle.THIN);
        borderStyle.setBorderBottom(BorderStyle.THIN);
        borderStyle.setBorderLeft(BorderStyle.THIN);
        borderStyle.setBorderColor(XSSFCellBorder.BorderSide.TOP, COLORS.BORDER_DARK_BLUE.getXssfcolor());
        borderStyle.setBorderColor(XSSFCellBorder.BorderSide.RIGHT, COLORS.BORDER_DARK_BLUE.getXssfcolor());
        borderStyle.setBorderColor(XSSFCellBorder.BorderSide.BOTTOM, COLORS.BORDER_DARK_BLUE.getXssfcolor());
        borderStyle.setBorderColor(XSSFCellBorder.BorderSide.LEFT, COLORS.BORDER_DARK_BLUE.getXssfcolor());
        return borderStyle;
    }

    public static XSSFCellStyle getHeaderStyle(XSSFWorkbook workbook) {
        XSSFCellStyle headerstyle = getBorderStyle(workbook);
        headerstyle.setFillBackgroundColor(COLORS.DARK_BLUE.getXssfcolor());
        headerstyle.setFillForegroundColor(COLORS.DARK_BLUE.getXssfcolor());
        headerstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font font = workbook.createFont();
        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());
        headerstyle.setFont(font);
        return headerstyle;
    }

    public static XSSFCellStyle getSubHeaderStyle(XSSFWorkbook workbook) {
        XSSFCellStyle subheaderstyle = getBorderStyle(workbook);
        subheaderstyle.setFillBackgroundColor(COLORS.LIGHT_BLUE.getXssfcolor());
        subheaderstyle.setFillForegroundColor(COLORS.LIGHT_BLUE.getXssfcolor());
        subheaderstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font font = workbook.createFont();
        font.setBold(true);
        subheaderstyle.setFont(font);
        return subheaderstyle;
    }

    public static XSSFCellStyle getDataStyle(XSSFWorkbook workbook) {
        XSSFCellStyle datastyle = getBorderStyle(workbook);
        return datastyle;
    }

    private Cell addNumberCell(Row row, int index, double value, XSSFCellStyle style) {
        Cell cell = row.createCell(index, CellType.NUMERIC);
        cell.setCellValue(value);
        cell.setCellStyle(style);
        return cell;
    }

    private Cell addTextCell(Row row, int index, String value, XSSFCellStyle style) {
        Cell cell = row.createCell(index, CellType.STRING);
        cell.setCellValue(value);
        cell.setCellStyle(style);
        return cell;
    }

    public static void addRegionBorder(Sheet sheet, CellRangeAddress rangeAddress, XSSFColor color) {

        RegionUtil.setBorderTop(BorderStyle.THIN, rangeAddress, sheet);
        RegionUtil.setBorderLeft(BorderStyle.THIN, rangeAddress, sheet);
        RegionUtil.setBorderRight(BorderStyle.THIN, rangeAddress, sheet);
        RegionUtil.setBorderBottom(BorderStyle.THIN, rangeAddress, sheet);

        RegionUtil.setTopBorderColor(color.getIndex(), rangeAddress, sheet);
        RegionUtil.setRightBorderColor(color.getIndex(), rangeAddress, sheet);
        RegionUtil.setBottomBorderColor(color.getIndex(), rangeAddress, sheet);
        RegionUtil.setLeftBorderColor(color.getIndex(), rangeAddress, sheet);

    }

    private void buildUenHeader(PresupuestoUen budget, Sheet sheet, Index index) {
        Row rccheader = sheet.createRow(index.getNextRow());
        this.addTextCell(rccheader, index.getNextCol(), messages.getMessage("lbl_uen", null, LOCALE), HEADER_STYLE);
        this.addTextCell(rccheader, index.getNextCol(), budget.getNombreUen(), TEXT_STYLE);
        CellRangeAddress uenregion = new CellRangeAddress(index.row, index.row, index.getCol(), index.getNextCol(8));
        addRegionBorder(sheet, uenregion, COLORS.BORDER_DARK_BLUE.getXssfcolor());
        sheet.addMergedRegion(uenregion);
    }

    private void buildUenBody(PresupuestoUen budget, Sheet sheet, String grupo, Index index) {
        this.mapBudgetHeader(sheet, grupo, index);
        this.mapBudgetValue(sheet, budget.getTotales(), messages.getMessage("lbl_total", null, LOCALE), index, SUB_HEADER_STYLE);
        budget.getLineas().forEach(linea -> this.mapBudgetValue(sheet, linea, linea.getCustomName(), index, NUMBER_STYLE));
        index.getNextRow();
    }

    private class Index {

        private int row = -1;
        private int col = -1;

        public Index() {
        }

        public Index(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getNextRow() {
            this.col = 0;
            return ++this.row;
        }

        public int getNextRow(int sum) {
            this.col = 0;
            return this.row += sum;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public int getNextCol() {
            return ++this.col;
        }

        public int getNextCol(int sum) {
            return this.col += sum;
        }

    }

    private void mapBudgetHeaderProject(Sheet sheet, String grupo, Index idx) {
        Row rth = sheet.createRow(idx.getNextRow());
        this.addTextCell(rth, idx.getNextCol(), grupo, HEADER_STYLE);
        this.addTextCell(rth, idx.getNextCol(), messages.getMessage("lbl_proy_tipo_gasto", null, LOCALE), HEADER_STYLE);
        this.addTextCell(rth, idx.getNextCol(), messages.getMessage("lbl_proy_presupuesto", null, LOCALE), HEADER_STYLE);
        this.addTextCell(rth, idx.getNextCol(), messages.getMessage("lbl_proy_actual", null, LOCALE), HEADER_STYLE);
        this.addTextCell(rth, idx.getNextCol(), messages.getMessage("lbl_proy_comprometido", null, LOCALE), HEADER_STYLE);
        this.addTextCell(rth, idx.getNextCol(), messages.getMessage("lbl_proy_disponible", null, LOCALE), HEADER_STYLE);
    }

    private void mapBudgetHeader(Sheet sheet, String grupo, Index idx) {
        Row rth = sheet.createRow(idx.getNextRow());
        this.addTextCell(rth, idx.getNextCol(), grupo, HEADER_STYLE);
        this.addTextCell(rth, idx.getNextCol(), messages.getMessage("lbl_original", null, LOCALE), HEADER_STYLE);
        this.addTextCell(rth, idx.getNextCol(), messages.getMessage("lbl_transferido", null, LOCALE), HEADER_STYLE);
        this.addTextCell(rth, idx.getNextCol(), messages.getMessage("lbl_inc_dec", null, LOCALE), HEADER_STYLE);
        this.addTextCell(rth, idx.getNextCol(), messages.getMessage("lbl_presupuesto_ajustado", null, LOCALE), HEADER_STYLE);
        this.addTextCell(rth, idx.getNextCol(), messages.getMessage("lbl_variacion", null, LOCALE), HEADER_STYLE);
        this.addTextCell(rth, idx.getNextCol(), "% " + messages.getMessage("lbl_variacion", null, LOCALE), HEADER_STYLE);
        this.addTextCell(rth, idx.getNextCol(), messages.getMessage("lbl_comprometido", null, LOCALE), HEADER_STYLE);
        this.addTextCell(rth, idx.getNextCol(), messages.getMessage("lbl_erogado", null, LOCALE), HEADER_STYLE);
        this.addTextCell(rth, idx.getNextCol(), messages.getMessage("lbl_disponible", null, LOCALE), HEADER_STYLE);
    }

    private void mapBudgetValue(Sheet sheet, Presupuesto data, String categoria, Index idx, XSSFCellStyle style) {
        Row row = sheet.createRow(idx.getNextRow());
        this.mapBudgetValue(row, data, categoria, idx, style);
    }

    private Row mapBudgetValue(Row row, Presupuesto data, String categoria, Index idx, XSSFCellStyle style) {
        this.addTextCell(row, idx.getNextCol(), categoria, style);
        this.addNumberCell(row, idx.getNextCol(), data.getOriginal(), style);
        this.addNumberCell(row, idx.getNextCol(), data.getTransferencias(), style);
        this.addNumberCell(row, idx.getNextCol(), data.getIncrementosDecrementos(), style);
        this.addNumberCell(row, idx.getNextCol(), data.getOperativo(), style);
        this.addNumberCell(row, idx.getNextCol(), data.getDiferencia(), style);
        this.addNumberCell(row, idx.getNextCol(), data.getDiferenciaPorcentual(), (XSSFCellStyle) style.clone()).getCellStyle().setDataFormat(PERCENT_STYLE.getDataFormat());
        this.addNumberCell(row, idx.getNextCol(), data.getComprometido(), style);
        this.addNumberCell(row, idx.getNextCol(), data.getErogado(), style);
        this.addNumberCell(row, idx.getNextCol(), data.getDisponible(), style);
        return row;
    }

    private void mapBudgetValueProject(Sheet sheet, Presupuesto data, String task, String expenditureType, Index idx, XSSFCellStyle style) {
        Row row = sheet.createRow(idx.getNextRow());
        this.mapBudgetValueProject(row, data, task, expenditureType, idx, style);
    }

    private Row mapBudgetValueProject(Row row, Presupuesto data, String task, String expenditureType, Index idx, XSSFCellStyle style) {
        log.debug(" **** mapBudgetValueProject *** " + data);

        this.addTextCell(row, idx.getNextCol(), task, style);
        this.addTextCell(row, idx.getNextCol(), expenditureType, style);
        this.addNumberCell(row, idx.getNextCol(), data.getOriginal(), style);
//        this.addNumberCell(row, idx.getNextCol(), data.getTransferencias(), style);
//        this.addNumberCell(row, idx.getNextCol(), data.getIncrementosDecrementos(), style);
//        this.addNumberCell(row, idx.getNextCol(), data.getOperativo(), style);
//        this.addNumberCell(row, idx.getNextCol(), data.getDiferencia(), style);
//        this.addNumberCell(row, idx.getNextCol(), data.getDiferenciaPorcentual(), (XSSFCellStyle) style.clone()).getCellStyle().setDataFormat(PERCENT_STYLE.getDataFormat());
        this.addNumberCell(row, idx.getNextCol(), data.getErogado(), style);
        this.addNumberCell(row, idx.getNextCol(), data.getComprometido(), style);
        this.addNumberCell(row, idx.getNextCol(), data.getDisponible(), style);
        return row;
    }

    private void mapearHeaderMeses(Row row, Index idx, XSSFCellStyle style) {
        this.addTextCell(row, idx.getNextCol(), messages.getMessage("lbl_categoria", null, LOCALE), style);
        this.addTextCell(row, idx.getNextCol(), messages.getMessage("lbl_enero", null, LOCALE), style);
        this.addTextCell(row, idx.getNextCol(), messages.getMessage("lbl_febrero", null, LOCALE), style);
        this.addTextCell(row, idx.getNextCol(), messages.getMessage("lbl_marzo", null, LOCALE), style);
        this.addTextCell(row, idx.getNextCol(), messages.getMessage("lbl_abril", null, LOCALE), style);
        this.addTextCell(row, idx.getNextCol(), messages.getMessage("lbl_mayo", null, LOCALE), style);
        this.addTextCell(row, idx.getNextCol(), messages.getMessage("lbl_junio", null, LOCALE), style);
        this.addTextCell(row, idx.getNextCol(), messages.getMessage("lbl_julio", null, LOCALE), style);
        this.addTextCell(row, idx.getNextCol(), messages.getMessage("lbl_agosto", null, LOCALE), style);
        this.addTextCell(row, idx.getNextCol(), messages.getMessage("lbl_septiembre", null, LOCALE), style);
        this.addTextCell(row, idx.getNextCol(), messages.getMessage("lbl_octubre", null, LOCALE), style);
        this.addTextCell(row, idx.getNextCol(), messages.getMessage("lbl_noviembre", null, LOCALE), style);
        this.addTextCell(row, idx.getNextCol(), messages.getMessage("lbl_diciembre", null, LOCALE), style);
    }

    private <T extends Meses> void mapearMeses(T linea, Row tr, String categoria, Index idx, XSSFCellStyle style) {
        if (categoria != null) {
            this.addTextCell(tr, idx.getNextCol(), categoria, style);
        }
        this.addNumberCell(tr, idx.getNextCol(), linea.getEnero(), style);
        this.addNumberCell(tr, idx.getNextCol(), linea.getFebrero(), style);
        this.addNumberCell(tr, idx.getNextCol(), linea.getMarzo(), style);
        this.addNumberCell(tr, idx.getNextCol(), linea.getAbril(), style);
        this.addNumberCell(tr, idx.getNextCol(), linea.getMayo(), style);
        this.addNumberCell(tr, idx.getNextCol(), linea.getJunio(), style);
        this.addNumberCell(tr, idx.getNextCol(), linea.getJulio(), style);
        this.addNumberCell(tr, idx.getNextCol(), linea.getAgosto(), style);
        this.addNumberCell(tr, idx.getNextCol(), linea.getSeptiembre(), style);
        this.addNumberCell(tr, idx.getNextCol(), linea.getOctubre(), style);
        this.addNumberCell(tr, idx.getNextCol(), linea.getNoviembre(), style);
        this.addNumberCell(tr, idx.getNextCol(), linea.getDiciembre(), style);
    }

    private void createFilterHeadersProject(ExportModel model, XSSFWorkbook workbook, Sheet sheet, Index idx) {
        log.debug(" **** createFilterHeadersProject *** ");
        Row rowuen = sheet.createRow(idx.getNextRow());
        this.addTextCell(rowuen, idx.getNextCol(), messages.getMessage("lbl_uen", null, LOCALE), HEADER_STYLE);
        Cell uencell = this.addTextCell(rowuen, idx.getNextCol(), "", TEXT_STYLE);
        CellRangeAddress uenregion = new CellRangeAddress(idx.getRow(), idx.getRow(), idx.getCol(), idx.getCol() + 2);
        addRegionBorder(sheet, uenregion, COLORS.BORDER_DARK_BLUE.getXssfcolor());
        sheet.addMergedRegion(uenregion);
        if (model.getUens().size() == 1) {
            uencell.setCellValue(model.getUens().get(0));
        }

        Row rowproject = sheet.createRow(idx.getNextRow());
        this.addTextCell(rowproject, idx.getNextCol(), messages.getMessage("lbl_proyecto", null, LOCALE), HEADER_STYLE);
        Cell ccscell = this.addTextCell(rowproject, idx.getNextCol(), "", TEXT_STYLE);
        CellRangeAddress regionprojs = new CellRangeAddress(idx.getRow(), idx.getRow(), idx.getCol(), idx.getCol() + 2);
        addRegionBorder(sheet, regionprojs, COLORS.BORDER_DARK_BLUE.getXssfcolor());
        sheet.addMergedRegion(regionprojs);

        if (model.getCcs() != null && false == model.getCcs().isEmpty()) {
            if (model.getCcs().size() == 1) {
                ccscell.setCellValue(model.getCcs().get(0));
            } else {

                if (workbook.getSheet("catalogccs") == null) {
                    Sheet sheetccs = workbook.createSheet("catalogccs");
                    sheetccs.protectSheet("metalsa");
                    workbook.setSheetHidden(workbook.getSheetIndex(sheetccs), true);

                    for (int i = 0; i < model.getCcs().size(); i++) {
                        sheetccs.createRow(i).createCell(0, CellType.STRING).setCellValue(model.getCcs().get(i));
                    }

                    Name namedCell = workbook.createName();
                    namedCell.setNameName("ccs");
                    namedCell.setRefersToFormula("'catalogccs'!$A$1:$A$" + model.getCcs().size());
                }

                DataValidationHelper dvHelper = sheet.getDataValidationHelper();
                DataValidationConstraint dvConstraint = dvHelper.createFormulaListConstraint("ccs");
                CellRangeAddressList addressList = new CellRangeAddressList(idx.getRow(), idx.getRow(), idx.getCol(), idx.getCol());
                DataValidation validation = dvHelper.createValidation(dvConstraint, addressList);
                validation.setSuppressDropDownArrow(true);
                validation.setShowErrorBox(true);
                sheet.addValidationData(validation);
            }
        }

        Row rowperiodo = sheet.createRow(idx.getNextRow());
        this.addTextCell(rowperiodo, idx.getNextCol(), messages.getMessage("lbl_rango_periodo", null, LOCALE), HEADER_STYLE);
        this.addTextCell(rowperiodo, idx.getNextCol(), model.getPeriodoInicial(), TEXT_STYLE);
        this.addTextCell(rowperiodo, idx.getNextCol(), "-", (XSSFCellStyle) TEXT_STYLE.clone()).getCellStyle().setAlignment(HorizontalAlignment.CENTER);
        this.addTextCell(rowperiodo, idx.getNextCol(), model.getPeriodoFinal(), TEXT_STYLE);

    }

    private void createFilterHeaders(ExportModel model, XSSFWorkbook workbook, Sheet sheet, Index idx) {

        Row rowuen = sheet.createRow(idx.getNextRow());
        this.addTextCell(rowuen, idx.getNextCol(), messages.getMessage("lbl_uen", null, LOCALE), HEADER_STYLE);
        Cell uencell = this.addTextCell(rowuen, idx.getNextCol(), "", TEXT_STYLE);
        CellRangeAddress uenregion = new CellRangeAddress(idx.getRow(), idx.getRow(), idx.getCol(), idx.getCol() + 2);
        addRegionBorder(sheet, uenregion, COLORS.BORDER_DARK_BLUE.getXssfcolor());
        sheet.addMergedRegion(uenregion);

        if (model.getUens() != null && false == model.getUens().isEmpty()) {
            if (model.getUens().size() == 1) {
                uencell.setCellValue(model.getUens().get(0));
            } else {

                if (workbook.getSheet("cataloguens") == null) {
                    Sheet sheetuens = workbook.createSheet("cataloguens");
                    sheetuens.protectSheet("metalsa");
                    workbook.setSheetHidden(workbook.getSheetIndex(sheetuens), true);

                    for (int i = 0; i < model.getUens().size(); i++) {
                        sheetuens.createRow(i).createCell(0, CellType.STRING).setCellValue(model.getUens().get(i));
                    }

                    Name namedCell = workbook.createName();
                    namedCell.setNameName("uens");
                    namedCell.setRefersToFormula("'cataloguens'!$A$1:$A$" + model.getUens().size());

                }

                DataValidationHelper dvHelper = sheet.getDataValidationHelper();
                DataValidationConstraint dvConstraint = dvHelper.createFormulaListConstraint("uens");
                CellRangeAddressList addressList = new CellRangeAddressList(idx.getRow(), idx.getRow(), idx.getCol(), idx.getCol());
                DataValidation validation = dvHelper.createValidation(dvConstraint, addressList);
                validation.setSuppressDropDownArrow(true);
                validation.setShowErrorBox(true);
                sheet.addValidationData(validation);
            }
        }

        Row rowccs = sheet.createRow(idx.getNextRow());
        this.addTextCell(rowccs, idx.getNextCol(), messages.getMessage("lbl_centro_costo", null, LOCALE), HEADER_STYLE);
        Cell ccscell = this.addTextCell(rowccs, idx.getNextCol(), "", TEXT_STYLE);
        CellRangeAddress regionccs = new CellRangeAddress(idx.getRow(), idx.getRow(), idx.getCol(), idx.getCol() + 2);
        addRegionBorder(sheet, regionccs, COLORS.BORDER_DARK_BLUE.getXssfcolor());
        sheet.addMergedRegion(regionccs);

        if (model.getCcs() != null && false == model.getCcs().isEmpty()) {
            if (model.getCcs().size() == 1) {
                ccscell.setCellValue(model.getCcs().get(0));
            } else {

                if (workbook.getSheet("catalogccs") == null) {
                    Sheet sheetccs = workbook.createSheet("catalogccs");
                    sheetccs.protectSheet("metalsa");
                    workbook.setSheetHidden(workbook.getSheetIndex(sheetccs), true);

                    for (int i = 0; i < model.getCcs().size(); i++) {
                        sheetccs.createRow(i).createCell(0, CellType.STRING).setCellValue(model.getCcs().get(i));
                    }

                    Name namedCell = workbook.createName();
                    namedCell.setNameName("ccs");
                    namedCell.setRefersToFormula("'catalogccs'!$A$1:$A$" + model.getCcs().size());
                }

                DataValidationHelper dvHelper = sheet.getDataValidationHelper();
                DataValidationConstraint dvConstraint = dvHelper.createFormulaListConstraint("ccs");
                CellRangeAddressList addressList = new CellRangeAddressList(idx.getRow(), idx.getRow(), idx.getCol(), idx.getCol());
                DataValidation validation = dvHelper.createValidation(dvConstraint, addressList);
                validation.setSuppressDropDownArrow(true);
                validation.setShowErrorBox(true);
                sheet.addValidationData(validation);
            }
        }

        Row rowperiodo = sheet.createRow(idx.getNextRow());
        this.addTextCell(rowperiodo, idx.getNextCol(), messages.getMessage("lbl_rango_periodo", null, LOCALE), HEADER_STYLE);
        this.addTextCell(rowperiodo, idx.getNextCol(), model.getPeriodoInicial(), TEXT_STYLE);
        this.addTextCell(rowperiodo, idx.getNextCol(), "-", (XSSFCellStyle) TEXT_STYLE.clone()).getCellStyle().setAlignment(HorizontalAlignment.CENTER);
        this.addTextCell(rowperiodo, idx.getNextCol(), model.getPeriodoFinal(), TEXT_STYLE);
    }

    private String getTransactionType(String type) {
        switch (type) {
        case "INCREASE":
        case "ADD BUDGET AMOUNT":
        case "TRANSFER IN":
            return this.messages.getMessage("lbl_incremento", null, LOCALE);
        case "DECREASE":
        case "DECREASE BUDGET AMOUNT":
        case "TRANSFER OUT":
        case "TRANSFER":
            return this.messages.getMessage("lbl_decremento", null, LOCALE);
        default:
            return type;
        }
    }

}
