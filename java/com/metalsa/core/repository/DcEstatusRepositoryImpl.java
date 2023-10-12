package com.metalsa.core.repository;

import javax.persistence.EntityManager;
import com.metalsa.core.model.DcEstatus;

/**
 *
 * @author Gamaliel Espinoza M.
 */
public class DcEstatusRepositoryImpl implements DcEstatusRepository {
    private final EntityManager em;

    public DcEstatusRepositoryImpl(EntityManager em) {
        this.em = em;
    }
    
    @Override
    public DcEstatus findActiveByDesc(String desc) {
        return (DcEstatus) em.createQuery(
                "SELECT d FROM DcEstatus d "
                    + "WHERE d.descEstatus = :description "
                    + "AND d.active = :active")
                .setParameter("description", desc)
                .setParameter("active", "SI")
                .getSingleResult();
    }
}