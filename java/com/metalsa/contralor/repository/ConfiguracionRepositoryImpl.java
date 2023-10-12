package com.metalsa.contralor.repository;

import com.metalsa.contralor.controller.UenContralor;
import com.metalsa.contralor.model.Configuracion;
import com.metalsa.contralor.model.RequestBodyConfiguration;
import com.metalsa.contralor.model.Valores;
import com.metalsa.finanzas.model.RespuestaPojo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import lombok.extern.log4j.Log4j;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Oscar del Angel
 */

@Log4j
public class ConfiguracionRepositoryImpl implements ConfiguracionProcedures {

    @PersistenceContext
    private EntityManager em;

    /**
     * 
     * @param request
     * @return 
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public RespuestaPojo updateConfiguration(RequestBodyConfiguration request) {
        RespuestaPojo respuesta = new RespuestaPojo();
        try {
            for (Configuracion configuracion : request.getConfigurations()) {
                if (configuracion.getIdParameter() == 67) {
                    for (Valores valores : configuracion.getValues()) {
                        this.updateAprover(valores.getValue(), 5);
                    }
                }
                respuesta = this.updateConfiguration(request.getUen(), request.getUser(), configuracion);
            }
        } catch (Exception e) {
            respuesta.setMensaje("ERROR");
            log.debug(e.getLocalizedMessage(), e);
        }
        return respuesta;
    }
    public void updateAprover(String user, Integer id_process) {
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("Configuracion.INSERTA_ROL");
        proc.setParameter("p_id_usuario", user);
        proc.setParameter("p_id_proceso_aprobacion", id_process);
        proc.execute();
        String response = null;
        response = proc.getOutputParameterValue("out_message").toString();
    }
    /**
     * 
     * @param uen
     * @param usuario
     * @param configuracion
     * @return
     * @throws Exception 
     */
    public RespuestaPojo updateConfiguration(Integer uen, String usuario, Configuracion configuracion) throws Exception {

        boolean isValid = true;
        StringBuilder valores = new StringBuilder();
        try {
            for (Valores valor : configuracion.getValues()) {
                if(valor.getValueCc()!= null && valor.getValueCc().length()<=100){
                    valores.append(valor.getValue()).append(",")
                           .append(valor.getProperty()).append(",")                      
                           .append(valor.getCondition()).append(",")
                           .append(valor.getValueCc()).append("|");

                }else{
                     valores.append(valor.getValue()).append(",")
                            .append(valor.getProperty()).append(",")                      
                            .append(valor.getCondition()).append(",")
                            .append("").append("|");
                }
                 
            }
        } catch (Exception e) {
            isValid = false;
        }
        RespuestaPojo respuesta = new RespuestaPojo();
        if (isValid) {

            StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("Configuracion.update_configuration");
            proc.setParameter("p_uen", uen);
            proc.setParameter("p_user", usuario);
            proc.setParameter("p_key", configuracion.getParameterName());
            proc.setParameter("p_condition", configuracion.getCondition());
            proc.setParameter("p_values", valores.toString());
            proc.setParameter("p_remplazable", configuracion.getRemplazable());
            proc.execute();
            respuesta.setMensaje(proc.getOutputParameterValue("p_out_result").toString());
        }
        return respuesta;
    }

    @Override
    public List<UenContralor> getUensByApprovalProcess(String user, String approvProcess) {
        List<UenContralor> result;
        try {
            StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("Configuracion.getUensByApprovalProcess");
            proc.setParameter("p_user", user);
            proc.setParameter("p_process", approvProcess);
            proc.execute();
            result = proc.getResultList();
        } catch (Exception e) {
            log.debug(e.getLocalizedMessage(),e);
            result = new ArrayList<>();
        }

        return result;
    }

    @Override
    public List<UenContralor> getUensByParamValue(String paramName, String paramValue) {
        List<UenContralor> result;
        try {
            StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("Configuracion.getUensByParamValue");
            proc.setParameter("p_parameter_name", paramName);
            proc.setParameter("p_parameter_value", paramValue);
            proc.execute();
            result = proc.getResultList();
        } catch (Exception e) {
            log.debug(e.getLocalizedMessage(),e);
            result = new ArrayList<>();
        }

        return result;
    }
    
    @Override
    public Configuracion findByUenName(Integer uen, String parameterName) {
        em.clear();
        return em.createNamedQuery("Configuracion.findByUenName", Configuracion.class)
                .setParameter(1, uen)
                .setParameter(2, parameterName)
                .getSingleResult();
    }

    @Override
    public Valores findSingleValue(Integer uen, String parameterName) {
        em.clear();
        return em.createNamedQuery("Valores.findSingleValue", Valores.class).setParameter(1, uen).setParameter(2, parameterName).getSingleResult();
    }
    
}
