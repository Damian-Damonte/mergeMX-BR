package com.metalsa.reportes.repository;

import com.metalsa.reportes.model.ExportModel;
import java.io.ByteArrayInputStream;

/**
 *
 * @author Oscar del Angel
 */
public interface ExportReportService {

    public ByteArrayInputStream getReporteVariacion(ExportModel model);

    public ByteArrayInputStream getReporteEstadoCuentaCC(ExportModel model);

    public ByteArrayInputStream getReporteMovimientos(ExportModel model);
    
    public ByteArrayInputStream getReporteTransferencias(ExportModel model);

    public ByteArrayInputStream getReporteBalanza(ExportModel model);

    public ByteArrayInputStream getReporteEdoCuentaUen(ExportModel model);

    public ByteArrayInputStream getReportePresupuestoAnual(ExportModel model);
    
    public ByteArrayInputStream getReporteProject(ExportModel model);
}
