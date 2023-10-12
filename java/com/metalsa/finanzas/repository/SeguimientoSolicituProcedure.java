package com.metalsa.finanzas.repository;

import com.metalsa.finanzas.model.SeguimientoSolicitud;
import java.util.Date;
import java.util.List;

/**
 *
 * @author JL
 */
public interface SeguimientoSolicituProcedure {
    
    List<SeguimientoSolicitud> getSolicitudesUsuario(String user);
    
    List<SeguimientoSolicitud> getReporteUsuario(String user, Date fechaInicial, Date fechaFin);
}
