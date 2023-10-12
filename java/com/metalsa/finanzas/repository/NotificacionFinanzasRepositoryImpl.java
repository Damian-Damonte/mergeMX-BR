package com.metalsa.finanzas.repository;

import com.metalsa.finanzas.model.RespuestaPojo;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import lombok.extern.log4j.Log4j;

/**
 *
 * @author JL
 */
@Log4j
public class NotificacionFinanzasRepositoryImpl {

    @PersistenceContext
    private EntityManager em;

    RespuestaPojo enviarNotificacion(String idsSolicitud, String idioma, String tipoCorreo, String etapa) {

        RespuestaPojo resp = new RespuestaPojo();
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("enviar_notificacion");
        proc.setParameter("P_IDS", idsSolicitud);
        proc.setParameter("P_IDIOMA", idioma);
        proc.setParameter("P_TIPO_CORREO", tipoCorreo);
        proc.setParameter("P_ETAPA", etapa);

        log.debug("P_IDS->" + idsSolicitud);
        log.debug("P_IDIOMA->" + idioma);
        log.debug("P_TIPO_CORREO->" + tipoCorreo);
        log.debug("P_ETAPA->" + etapa);

        try {
            proc.execute();
            resp.setMensaje((String) proc.getOutputParameterValue("P_MESSAGE_OUT"));
            resp.setValor((Integer) proc.getOutputParameterValue("P_VALUE_OUT"));

        } catch (Exception e) {
            resp.setMensaje(e.getLocalizedMessage());
            throw new RuntimeException("Error ejecutando procedimiento: enviar_notificacion", e);
        }

        return resp;
    }

}
