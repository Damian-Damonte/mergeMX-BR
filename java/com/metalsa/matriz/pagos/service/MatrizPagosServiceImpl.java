/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.matriz.pagos.service;

import com.metalsa.aprobacion.model.NvcTblOrganizacionesH;
import com.metalsa.core.model.Periodos;
import com.metalsa.core.repository.PeriodosRepository;
import com.metalsa.matriz.pagos.dto.FiltrosMatrizPagosDTO;
import com.metalsa.matriz.pagos.dto.MatrizPagosDTO;
import com.metalsa.matriz.pagos.dto.OaProyectosDTO;
import com.metalsa.matriz.pagos.dto.ResponseMatrizDTO;
import com.metalsa.matriz.pagos.model.MatrizPagos;
import com.metalsa.utils.Constants;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
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
import org.springframework.stereotype.Service;

/**
 *
 * @author jose.jimenez07
 */
@Log4j
@Service
public class MatrizPagosServiceImpl implements MatrizPagosService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private PeriodosRepository periodosRepository;
    private final Integer anioDefault = 2003;
    private final Integer mesDefault = 1;

    @Override
    public ResponseMatrizDTO getDatosMatrizPagos(FiltrosMatrizPagosDTO filtros) {
        log.debug("getDatosMatrizPagos");
        String condiciones = "";
        String idProyecto = "";
        ResponseMatrizDTO responseMatrizDTO = new ResponseMatrizDTO();
        List<Periodos> listComboPeriodos = new ArrayList<>();
        List<MatrizPagos> list = new ArrayList<>();
        MatrizPagos fechaMin = null;
        MatrizPagos fechaMax = null;
        boolean flag=true;
        if (filtros.getModelListaProyecto() != null && !filtros.getModelListaProyecto().isEmpty()) {
            for (OaProyectosDTO proyecto : filtros.getModelListaProyecto()) {
                idProyecto += proyecto.getId_proyecto() + ",";
            }
            idProyecto = idProyecto.substring(0, idProyecto.length() - 1);
            condiciones += " AND PO_FACTS.PROJECT_ID IN (" + idProyecto + ")\n";
        }
        if (filtros.getModelListaUen() != null && !filtros.getModelListaUen().isEmpty()
                && filtros.getModelListaPeriodo() != null && !filtros.getModelListaPeriodo().isEmpty()) {
            String fechaInicio = filtros.getModelListaPeriodo().get(0).getFechInicioFormat();
            String fechaFin = filtros.getModelListaPeriodo().get(0).getFechFinFormat();
            if (idProyecto.equals("") && !filtros.getModelListaUen().isEmpty()) {
                Long idUen = filtros.getModelListaUen().get(0).getId();
                condiciones += " AND PO_FACTS.org_id=" + idUen + "\n";
            }
            condiciones += " AND PO_FACTS.INVOICE_CREATION_DATE >=TO_DATE('" + fechaInicio + "','dd/mm/yyyy') and PO_FACTS.INVOICE_CREATION_DATE <=TO_DATE('" + fechaFin + "','dd/mm/yyyy')\n";
        }
        StoredProcedureQuery proc = em.createStoredProcedureQuery("NVC_PKG_MATRIZ_PAGOS.GASTO_PROYECTOS_CC", MatrizPagos.class);
        proc.registerStoredProcedureParameter("P_FILTROS", String.class, ParameterMode.IN);
        proc.registerStoredProcedureParameter("P_LENGUAGE", String.class, ParameterMode.IN);
        proc.registerStoredProcedureParameter("V_CURSOR_OUT", void.class, ParameterMode.REF_CURSOR);
        proc.setParameter("P_FILTROS", condiciones);
        proc.setParameter("P_LENGUAGE", filtros.getIdioma());
        try {
            if (!condiciones.equals("") && proc.execute()) {
                list = (List<MatrizPagos>) proc.getResultList();

                fechaMin = list
                        .stream()
                        .min(Comparator.comparing(MatrizPagos::getInvoiceCreationDate, Comparator.nullsLast(Comparator.naturalOrder())))
                        .orElseThrow(NoSuchElementException::new);
                fechaMax = list
                        .stream()
                        .max(Comparator.comparing(MatrizPagos::getInvoiceCreationDate, Comparator.nullsFirst(Comparator.naturalOrder())))
                        .orElseThrow(NoSuchElementException::new);
            }
        } catch (Exception e) {
            log.debug("Sin registros");
            flag = false;
        }
        if (!idProyecto.equals("")) {
            listComboPeriodos = getComboPeriodos(fechaMin, fechaMax, filtros.getIdioma(), flag);
        } else if (filtros.getModelListaUen() != null && !filtros.getModelListaUen().isEmpty() && listComboPeriodos.isEmpty()) {
            listComboPeriodos = getComboPeriodos(null, null, filtros.getIdioma(), true);
        }
        responseMatrizDTO.setMatrizPagos(list);
        responseMatrizDTO.setPeriodos(listComboPeriodos);
        return responseMatrizDTO;
    }

    @Override
    public Iterable<NvcTblOrganizacionesH> getUens(String idUsuario) {
        Iterable<NvcTblOrganizacionesH> listUenCc = null;
        try {
            listUenCc = em.
                    createNamedQuery("NvcTblOrganizacionesH.getUensByUser")
                    .setParameter(1, idUsuario)
                    .getResultList();
        } catch (Exception e) {
            log.error("error en : NvcTblOrganizacionesH.getUenCc :" + e.getMessage());
        }
        return listUenCc;
    }

    @Override
    public ByteArrayInputStream getReporteExcel(FiltrosMatrizPagosDTO filtrosMatrizPagosDTO) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        List<MatrizPagosDTO> matrizPagosDTO = filtrosMatrizPagosDTO.getMatrizPagosDTO();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheetDatosTabla = workbook.createSheet("Payment_Matrix");
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.BLUE.getIndex());
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        // Create a Row
        Row headerRow = sheetDatosTabla.createRow(0);
        creaCell(headerCellStyle, headerRow, filtrosMatrizPagosDTO.getColumnas());
        int contador = 0;
        for (MatrizPagosDTO item : matrizPagosDTO) {
            contador++;
            Row rowExel = sheetDatosTabla.createRow(contador);
            rowExel.createCell(0).setCellValue(item.getNombreUen());
            rowExel.createCell(1).setCellValue(item.getProjectName());
            rowExel.createCell(2).setCellValue(item.getNombreSite());
            rowExel.createCell(3).setCellValue(item.getRequistionNum());
            rowExel.createCell(4).setCellValue(item.getVendorName());
            rowExel.createCell(5).setCellValue(item.getNombreComprador());
            rowExel.createCell(6).setCellValue(item.getPo());
            rowExel.createCell(7).setCellValue(item.getInvoiceAmount());
            rowExel.createCell(8).setCellValue(item.getFunctionalCurrencyCode());
            rowExel.createCell(9).setCellValue(item.getInvoiceNum());
            rowExel.createCell(10).setCellValue(item.getPayments());
            rowExel.createCell(11).setCellValue(item.getReceiptNum());
            rowExel.createCell(12).setCellValue(item.getInvoiceDate());
            rowExel.createCell(13).setCellValue(item.getFechaVencimiento());
            rowExel.createCell(14).setCellValue(item.getFechaProgramada());
            rowExel.createCell(15).setCellValue(item.getFechaPagoReal());
            rowExel.createCell(16).setCellValue(item.getStatus());
            rowExel.createCell(17).setCellValue(item.getWarning());

        }
        for (int i = 0; i < filtrosMatrizPagosDTO.getColumnas().size(); i++) {
            sheetDatosTabla.autoSizeColumn(i);
        }
        try {
            //FileOutputStream fileOut = new FileOutputStream("poi-generated-file.xlsx");
            workbook.write(out);
            workbook.close();
        } catch (IOException ex) {
            log.error("Error al generar el archivo", ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    private void creaCell(CellStyle headerCellStyle, Row headerRow, List<String> columns) {
        for (int i = 0; i < columns.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns.get(i));
            cell.setCellStyle(headerCellStyle);
        }

    }

    public List<Periodos> getComboPeriodos(MatrizPagos fechaMin, MatrizPagos fechaMax, String idioma, boolean flag) {
        Calendar calInicial = Calendar.getInstance();
        Calendar calFinal = Calendar.getInstance();
        if (fechaMin != null && fechaMax != null) {
            calInicial.setTime(fechaMin.getInvoiceCreationDate());
            calFinal.setTime(fechaMax.getInvoiceCreationDate());
            log.debug("a�o inicial" + calInicial.get(Calendar.YEAR));
            log.debug("men inicial" + calInicial.get(Calendar.MONTH));
            log.debug("a�o final" + calFinal.get(Calendar.YEAR));
            log.debug("mes final" + calFinal.get(Calendar.MONTH));
            return periodosRepository.findByPorRangoAnioMes(calInicial.get(Calendar.YEAR), calFinal.get(Calendar.YEAR), calInicial.get(Calendar.MONTH) + 1, calFinal.get(Calendar.MONTH) + 1, Constants.getIdioma(idioma));
        } else if (flag) {
            Date date = new Date();
            calFinal.setTime(date);
            return periodosRepository.findByPorRangoAnioMes(anioDefault, calFinal.get(Calendar.YEAR), mesDefault, calFinal.get(Calendar.MONTH) + 1, Constants.getIdioma(idioma));
        }
        return null;
    }

}
