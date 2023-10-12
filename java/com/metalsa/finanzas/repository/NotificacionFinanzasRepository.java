package com.metalsa.finanzas.repository;

import com.metalsa.finanzas.model.NotificacionFinanzas;
import com.metalsa.finanzas.model.RespuestaPojo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author JL
 */
public interface NotificacionFinanzasRepository extends JpaRepository<NotificacionFinanzas, Long>{
    
    RespuestaPojo enviarNotificacion(String idsSolicitud, String idioma, String tipoCorreo, String etapa);
    
}
