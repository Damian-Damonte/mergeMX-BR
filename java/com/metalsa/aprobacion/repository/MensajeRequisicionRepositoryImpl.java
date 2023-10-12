package com.metalsa.aprobacion.repository;


import com.metalsa.aprobacion.model.NotificacionHeader;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruben.bresler on 06/07/2017.
 */
public class MensajeRequisicionRepositoryImpl implements MensajesProcedures {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<NotificacionHeader> getProcNuevoHeader(String usuario, String idioma) {
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("Notificaciones.procNuevoHeader")
                .setParameter("p_usuario", usuario)
                .setParameter("p_idioma", idioma);

        List<NotificacionHeader> list = new ArrayList<>();
        if (proc.execute()) {
            list.addAll((List<NotificacionHeader>) proc.getResultList());
        }
        return list;
    }
}
