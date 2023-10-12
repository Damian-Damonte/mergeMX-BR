package com.metalsa.correos.repository;

import com.metalsa.aprobacion.service.MailNotificationService;
import com.metalsa.correos.pojo.NotificacionRequest;
import java.io.Serializable;
import java.sql.Clob;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author miguel.rdz
 * @param <T>
 * @param <ID>
 */
@Log4j
@Service
public class NotificacionServiceImpl<T, ID extends Serializable> implements NotificacionService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private MailNotificationService mailService;

    @Override
    public boolean insert(NotificacionRequest req) {
        //TODO
        return false;
    }

    @Override
    public boolean update(NotificacionRequest req) {
        String out = "OK";
        try {
            Clob body = new javax.sql.rowset.serial.SerialClob(req.getCorreo().getCuerpo().toCharArray());
            StoredProcedureQuery procedure = em.createStoredProcedureQuery("NVC_PKG_ADM_NOTIFICACIONES.UPDATE_NOTIFICACION");
            procedure.registerStoredProcedureParameter("P_ID_CORREO", Integer.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("P_ASUNTO", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("P_CUERPO", Clob.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("P_OUT", String.class, ParameterMode.OUT);
            procedure.setParameter("P_ID_CORREO", req.getCorreo().getIdCorreo());
            procedure.setParameter("P_ASUNTO", req.getCorreo().getAsunto());
            procedure.setParameter("P_CUERPO", body);
            procedure.execute();
            out = procedure.getOutputParameterValue("P_OUT").toString();
            log.debug("P_OUT->" + out);
            return "OK".equals(out);
        } catch (Exception e) {
            log.debug(e.getLocalizedMessage(), e);
            log.error("error en : update :" + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(NotificacionRequest req) {
        //TODO
        return false;
    }
}
