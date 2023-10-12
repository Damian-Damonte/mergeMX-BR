package com.metalsa.finanzas.repository;

import com.metalsa.finanzas.model.RespuestaPojo;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

/**
 *
 * @author JL
 */
public class SolicitudIncrementoRepositoryImpl {
    
    @PersistenceContext
    private EntityManager em;

    public RespuestaPojo crearSolicitud(Long idUen, Long idCcDestino, Long idCcOrigen, 
            String periodoDestino, String periodoOrigen, Date fechaNecesidad, 
            String razon, String usuario, String categorias, String tipo) {
        
        RespuestaPojo resp = new RespuestaPojo();
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("crear_solicitud");
        proc.setParameter("p_id_uen", idUen);
        proc.setParameter("p_id_cc_destino", idCcDestino);
        proc.setParameter("p_id_cc_origen", idCcOrigen);
        proc.setParameter("p_periodo_destino", periodoDestino);
        proc.setParameter("p_periodo_origen", periodoOrigen);
        proc.setParameter("p_fecha_necesidad", fechaNecesidad);
        proc.setParameter("p_razon", razon);
        proc.setParameter("p_usuario_creacion", usuario);
        proc.setParameter("p_categorias", categorias);
        proc.setParameter("p_tipo_solicitud", tipo);

        try {
            proc.execute();
            resp.setMensaje((String) proc.getOutputParameterValue("out_message"));
            resp.setValor((Integer) proc.getOutputParameterValue("out_value"));
            
        } catch (Exception e) {
            throw new RuntimeException("Error ejecutando procedimiento: crear_solicitud", e);
        }
        
        
        return resp;
    }
    
    

    RespuestaPojo actualizarSolicitud(String ids, String usuario, String razon, String accion){
        RespuestaPojo resp = new RespuestaPojo();
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("actualizar_solicitud");
        proc.setParameter("p_ids", ids);
        proc.setParameter("p_usuario", usuario);
        proc.setParameter("p_razon", razon);
        proc.setParameter("p_accion", accion);

        try {
            proc.execute();
            resp.setMensaje((String) proc.getOutputParameterValue("out_message"));
            resp.setValor((Integer) proc.getOutputParameterValue("out_value"));
            
        } catch (Exception e) {
            throw new RuntimeException("Error ejecutando procedimiento: actualizar_solicitud", e);
        }
        
        
        return resp;
    }
    
        RespuestaPojo setSolicitudEnVisto(Long id){
        RespuestaPojo resp = new RespuestaPojo();
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("solicitud_en_visto");
        proc.setParameter("p_id", id);

        try {
            proc.execute();
            resp.setMensaje((String) proc.getOutputParameterValue("out_message"));
            resp.setValor((Integer) proc.getOutputParameterValue("out_value"));
            
        } catch (Exception e) {
            throw new RuntimeException("Error ejecutando procedimiento: solicitud_en_visto", e);
        }
        
        
        return resp;
    }
        
    Integer getCountAprobacionCC(String usuario){

        Integer respuesta = 0;
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("get_count_aprob_cc");
        proc.setParameter("P_USUARIO", usuario);
        try {
            proc.execute();
            respuesta = (Integer) proc.getOutputParameterValue("TOTAL");
            
        } catch (Exception e) {
            throw new RuntimeException("Error ejecutando procedimiento: get_count_aprob_cc", e);
        }
        
        return respuesta;
    }
    
    Integer getCountAprobacionFinanzas(String usuario){

        Integer respuesta = 0;
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("get_count_aprob_finanzas");
        proc.setParameter("P_USUARIO", usuario);
        try {
            proc.execute();
            respuesta = (Integer) proc.getOutputParameterValue("TOTAL");
            
        } catch (Exception e) {
            throw new RuntimeException("Error ejecutando procedimiento: get_count_aprob_finanzas", e);
        }
        
        return respuesta;
    }
    
    Integer getEsPreaprobador(String usuario){

        Integer respuesta = 0;
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("get_es_preaprobador");
        proc.setParameter("P_USUARIO", usuario);
        try {
            proc.execute();
            respuesta = (Integer) proc.getOutputParameterValue("TOTAL");
            
        } catch (Exception e) {
            throw new RuntimeException("Error ejecutando procedimiento: get_es_preaprobador", e);
        }
        
        return respuesta;
    }
    
    RespuestaPojo cancelarSolicitud(Long id, String usuario){
        RespuestaPojo resp = new RespuestaPojo();
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("cancelar_solicitud");
        proc.setParameter("p_id", id);
        proc.setParameter("p_usuario", usuario);
        try {
            proc.execute();
            resp.setMensaje((String) proc.getOutputParameterValue("out_message"));
            resp.setValor((Integer) proc.getOutputParameterValue("out_value"));
            
        } catch (Exception e) {
            throw new RuntimeException("Error ejecutando procedimiento: cancelar_solicitud", e);
        }
        return resp;
    }
}
