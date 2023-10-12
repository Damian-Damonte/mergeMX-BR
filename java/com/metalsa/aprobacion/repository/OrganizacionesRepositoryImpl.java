package com.metalsa.aprobacion.repository;

import com.metalsa.aprobacion.model.NvcTblOrganizacionesH;
import com.metalsa.aprobacion.model.UenWithDefault;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruben.bresler on 05/07/2017.
 */
@Slf4j
public class OrganizacionesRepositoryImpl implements OrganizationProcedures {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<UenWithDefault> findUenByUser(String user) {
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("NvcTblOrganizacionesH.findUenByUser");
        proc.setParameter("p_id_user", user);

        List<UenWithDefault> list = new ArrayList<>();
        if (proc.execute()) {
            list.addAll((List<UenWithDefault>) proc.getResultList());
        }
        return list;
    }

    List<NvcTblOrganizacionesH> getUensActivasByUser2( String userId){

        List<NvcTblOrganizacionesH> uens = new ArrayList<>();

        try {    
            StoredProcedureQuery store = em.createStoredProcedureQuery("NVC_PKG_WEB_COT.GET_ACTIVE_UENS_BY_USER");
            store.registerStoredProcedureParameter("P_ID_USER", String.class, ParameterMode.IN);
            store.registerStoredProcedureParameter("MSG_OUT", String.class, ParameterMode.OUT);
            store.registerStoredProcedureParameter("CODE_OUT", Integer.class, ParameterMode.OUT);
            store.registerStoredProcedureParameter("CURSOR_OUT", Void.class, ParameterMode.REF_CURSOR);
            
            store.setParameter("P_ID_USER", userId);
                        

            boolean isOK = store.execute();

            if (isOK) {
                List<Object[]> cursor = (List<Object[]>) store.getResultList();                 
                
                for (Object[] row : cursor) {
                    NvcTblOrganizacionesH uen = new NvcTblOrganizacionesH();
                    uen.setId(((BigDecimal) row[0]).longValue());
                    uen.setNombre(row[1] == null ? null : (String) row[1]);
                    uen.setCurrency(row[11] == null ? null : (String) row[11]);
                    
                    uens.add(uen);                    
                }                
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        
        return uens;                    
    }
}
