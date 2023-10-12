package com.metalsa.aprobacion.repository;

import com.metalsa.aprobacion.model.UenWithDefault;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
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
}
