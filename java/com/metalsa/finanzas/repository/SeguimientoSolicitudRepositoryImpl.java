package com.metalsa.finanzas.repository;

import com.metalsa.finanzas.model.SeguimientoSolicitud;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import lombok.extern.log4j.Log4j;

/**
 *
 * @author JL
 */
@Log4j
public class SeguimientoSolicitudRepositoryImpl implements SeguimientoSolicituProcedure{

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public List<SeguimientoSolicitud> getSolicitudesUsuario(String user) {
        StoredProcedureQuery proc;
        proc = em.createNamedStoredProcedureQuery("get_solicitudes_usuario");

        proc.setParameter("P_USUARIO", user);

        List<SeguimientoSolicitud> list = new ArrayList<>();
        try {
            if (proc.execute()) {
                list.addAll((List<SeguimientoSolicitud>) proc.getResultList());
            }
        } catch (Exception e) {
            log.error(e);
        }
        return list;

    }
    
    
    @Override
    public List<SeguimientoSolicitud> getReporteUsuario(String user, Date fechaInicial, Date fechaFin) {
        StoredProcedureQuery proc;
        proc = em.createNamedStoredProcedureQuery("get_reporte_usuario");

        proc.setParameter("P_USUARIO", user);
        if(null == fechaInicial){
           proc.setParameter("P_FECHA_INICIO", null); 
        }else{
            proc.setParameter("P_FECHA_INICIO", fechaInicial);
        }
        
        if(null == fechaFin){
           proc.setParameter("P_FECHA_FIN", null); 
        }else{
            proc.setParameter("P_FECHA_FIN", fechaFin);
        }
        

        List<SeguimientoSolicitud> list = new ArrayList<>();
        try {
            if (proc.execute()) {
                list.addAll((List<SeguimientoSolicitud>) proc.getResultList());
            }
        } catch (Exception e) {
            log.error(e);
        }
        return list;

    }
    
}
