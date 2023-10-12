/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.repository;

import com.metalsa.recibos.model.Requisitor;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

/**
 *
 * @author yair.nunez
 */
public class RequisitorRepositoryImpl implements RequisitorProcedure{
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Requisitor> obtenerRequisitores(String idUsuario) {
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("obtenerRequisitores");
        
        proc.setParameter("p_id_usuario", idUsuario);
        proc.setParameter("p_id_uen", null);
        List<Requisitor> list = new ArrayList<>();
        if (proc.execute()) {
            System.out.println("SP ejecutado");
            try {
                list.addAll((List<Requisitor>) proc.getResultList());
            } catch (Exception e) {
                System.out.println("Trono");
                System.out.println(e.getMessage());
            }
        }
        return list;
    }
    
}
