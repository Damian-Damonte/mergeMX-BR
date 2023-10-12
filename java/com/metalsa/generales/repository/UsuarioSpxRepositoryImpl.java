/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.generales.repository;

import com.metalsa.generales.model.Usuario;
import com.metalsa.home.model.ItemsSugeridos;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author APOJM20015
 */

@Repository
public class UsuarioSpxRepositoryImpl implements UsuarioSpxRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Iterable<Usuario> findByNombre(String query) {
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("Usuario.findByNombre");
        proc.setParameter("p_in_value", query);
        proc.execute();
        List<Usuario> list = proc.getResultList();
        return list;
    }
}
