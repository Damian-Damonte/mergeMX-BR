package com.metalsa.requisicion.repository;

import com.metalsa.core.model.NvcTblOaProveedoresH;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jose.espindola03
 */
@Repository
public class ProveedorRepositoryImpl implements ProveedorRepository {
    @PersistenceContext
    EntityManager em;
    
    @Override
    public NvcTblOaProveedoresH findOneById(Integer id) {
        return (NvcTblOaProveedoresH) em.createQuery(
                "SELECT p " +
                "FROM NvcTblOaProveedoresH p " +
                "WHERE p.idProveedor = :id")
                .setParameter("id", id)
                .getSingleResult();
       
    }
}
