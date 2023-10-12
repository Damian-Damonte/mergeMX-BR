package com.metalsa.aprobacion.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.lowagie.text.pdf.PdfWriter;
import com.metalsa.aprobacion.model.ConversacionRequisicion;
import com.metalsa.utils.Constants;
import lombok.extern.log4j.Log4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
@Service
@Log4j
public class ReportService {

    private LoadingCache<String, JasperReport> reports = null;

    public ReportService() {
        this.reports = CacheBuilder.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .softValues()
                .build(new CacheLoader<String, JasperReport>() {
                    public JasperReport load(String key) throws Exception {
                        return loadReport(key);
                    }
                });
    }

    private JasperReport loadReport(String reportPath) {
        try {
            return (JasperReport) JRLoader.loadObject(this.getClass().getResource(reportPath));
        } catch (JRException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public boolean printMensajesRequisicion(ConversacionRequisicion chat, OutputStream outputStream) {

        JRDataSource ds = new JRBeanCollectionDataSource(chat.getMensajes(), false);
        Map<String, Object> params = new HashMap<>();
        params.put("requisicion", chat.getRequisicion());
        params.put("REPORT_RESOURCE_BUNDLE", ResourceBundle.getBundle("i18n/messages", LocaleContextHolder.getLocale()));

        try {
            JasperReport jasperReport = reports.getUnchecked(Constants.REPORT_CHAT);

            JasperPrint print = JasperFillManager.fillReport(jasperReport, params, ds /*new JREmptyDataSource()*/);

            JRPdfExporter exporter = new JRPdfExporter();

            exporter.setExporterInput(new SimpleExporterInput(print));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));

            SimplePdfReportConfiguration reportConfig
                    = new SimplePdfReportConfiguration();
            reportConfig.setSizePageToContent(true);
            reportConfig.setForceLineBreakPolicy(false);

            SimplePdfExporterConfiguration exportConfig
                    = new SimplePdfExporterConfiguration();
            exportConfig.setMetadataAuthor("SPX");
            exportConfig.setEncrypted(true);
//            exportConfig.setUserPassword("jasper");
            exportConfig.setOwnerPassword("reports");
            exportConfig.setPermissions(PdfWriter.ALLOW_COPY | PdfWriter.ALLOW_PRINTING);
            exporter.setConfiguration(exportConfig);

//            exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
            exporter.exportReport();
            return true;

        } catch (JRException e) {
            e.printStackTrace();
        }

        return false;
    }
}
