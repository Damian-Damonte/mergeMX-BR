package com.metalsa.finanzas.repository;

import com.metalsa.finanzas.model.AdjuntoSolicitud;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author JL
 */
public interface AdjuntoSolicitudRepository extends JpaRepository<AdjuntoSolicitud, Long>{

     Iterable<AdjuntoSolicitud> findAllByIdSolicitudPresupuesto(Long id);
    
}
