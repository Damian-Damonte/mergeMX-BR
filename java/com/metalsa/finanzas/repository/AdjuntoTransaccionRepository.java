package com.metalsa.finanzas.repository;

import com.metalsa.finanzas.model.AdjuntoTransaccion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author JL
 */
public interface AdjuntoTransaccionRepository  extends JpaRepository<AdjuntoTransaccion, Long>{
    
}
