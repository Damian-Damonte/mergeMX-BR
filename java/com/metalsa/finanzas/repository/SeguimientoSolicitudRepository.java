package com.metalsa.finanzas.repository;

import com.metalsa.finanzas.model.SeguimientoSolicitud;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author JL
 */
public interface SeguimientoSolicitudRepository extends JpaRepository<SeguimientoSolicitud, Long>{
    
    List<SeguimientoSolicitud> getSolicitudesUsuario(String user);
    
    List<SeguimientoSolicitud> getReporteUsuario(String user, Date fechaInicial, Date fechaFin);
    
}
