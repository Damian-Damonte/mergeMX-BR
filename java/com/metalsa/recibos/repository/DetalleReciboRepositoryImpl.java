/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.repository;

import com.metalsa.recibos.model.DetalleRecibo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

/**
 *
 * @author yair.nunez
 */
public class DetalleReciboRepositoryImpl implements DetalleReciboProcedure{
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<DetalleRecibo> getDetalleRecibo(Integer idRequisicion, Integer idPartida) {
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("getDetalleRecibo");
        
        proc.setParameter("p_id_requisicion", idRequisicion);
        proc.setParameter("p_id_partida", idPartida);
        List<DetalleRecibo> list = new ArrayList<>();
        if (proc.execute()) {
            System.out.println("SP ejecutado");
            try {
                list.addAll((List<DetalleRecibo>) proc.getResultList());
            } catch (Exception e) {
                System.out.println("Trono");
                System.out.println(e.getMessage());
            }
        }
        return list;
    }
    
}
