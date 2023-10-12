/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.repository;

import com.metalsa.recibos.model.AlmacenRecibos;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

/**
 *
 * @author yair.nunez
 */
public class AlmacenReciboRepositoryImpl implements AlmacenReciboProcedure{
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<AlmacenRecibos> getAlmacenRecibos(String idUsuario) {
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("getAlmacenRecibos");
        
        proc.setParameter("P_USUARIO", idUsuario);
        List<AlmacenRecibos> list = new ArrayList<>();
        if (proc.execute()) {
            System.out.println("SP ejecutado");
            try {
                list.addAll((List<AlmacenRecibos>) proc.getResultList());
            } catch (Exception e) {
                System.out.println("Trono");
                System.out.println(e.getMessage());
            }
        }
        return list;
        
    }
    
}
