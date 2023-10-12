package com.metalsa.requisicion.repository;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.ParameterMode;
import com.metalsa.requisicion.model.Requisicion;

/**
 *
 * @author Gamaliel Espinoza M.
 */
public class RequisicionRepositoryImpl implements RequisicionRepository {
    private final EntityManager em;
    
    private static final String AUTO_APRUEBA_SP = "nvc_pkg_spx_approvals_internal.auto_approve_requisition";
    
    public RequisicionRepositoryImpl(EntityManager em) {
        this.em = em;
    }
    
    @Override
    public boolean validarAutoaprobacion(Integer idRequisicion) {
        try {
            return ((java.math.BigDecimal) em
                .createNativeQuery("SELECT fn_tiene_autoaprobacion(?1) FROM DUAL")
                .setParameter("1", idRequisicion)
                .getSingleResult()).intValue() > 0;
        } catch (Exception ex) {
            System.out.println("(validarAutoaprobacion) " + ex.getMessage());
            return false;
        }
    }
    
    @Override
    public Integer autoAprueba(Integer idRequisicion, Integer idPartida) {
        Integer valueOut = -1;
        
        try {
            StoredProcedureQuery sp = em.createStoredProcedureQuery(AUTO_APRUEBA_SP);
            sp.registerStoredProcedureParameter("p_id_requisicion", Integer.class, ParameterMode.IN);
            sp.registerStoredProcedureParameter("p_id_partida", Integer.class, ParameterMode.IN);
            sp.registerStoredProcedureParameter("value_out", Integer.class, ParameterMode.OUT);
            sp.registerStoredProcedureParameter("message_out", String.class, ParameterMode.OUT);

            sp.setParameter("p_id_requisicion", idRequisicion);
            sp.setParameter("p_id_partida", idPartida);
            sp.execute();

            valueOut = (Integer) sp.getOutputParameterValue("value_out");

            // String message = (String) sp.getOutputParameterValue("message_out");
        } catch (Exception ex) {
            System.out.println("(RequisicionRepositoryImpl.autoAprueba) " + ex.getMessage());
        }
            
        return valueOut;
    }
    
    @Override
    public Requisicion findById(Long idRequisicion) {
        return em.find(Requisicion.class, idRequisicion);
    }
    
    @Override
    public boolean exist(Long idRequisicion) {
        return findById(idRequisicion) != null;
    }
}
