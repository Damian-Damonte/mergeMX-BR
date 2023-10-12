package com.metalsa.requisicion.repository;

import com.metalsa.aprobacion.model.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gamaliel Espinoza M.
 */
@Repository
public class UserRepositoryImpl implements UserRepository {
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public Usuario getOneById(String id) {
        return (Usuario) em.createQuery(
                "SELECT u FROM com.metalsa.aprobacion.model.Usuario u WHERE u.id = :id ")
                .setParameter("id", id)
                .getSingleResult();
    }
}
