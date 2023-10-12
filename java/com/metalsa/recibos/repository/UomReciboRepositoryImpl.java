/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.repository;

import com.metalsa.recibos.model.Uom;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

/**
 *
 * @author yair.nunez
 */
public class UomReciboRepositoryImpl implements UomProcedure{
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public List<Uom> obtenerListaUom(String unidad) {
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("obtenerListaUom");
        
        proc.setParameter("p_unit_of_measure_sel", unidad);
        List<Uom> list = new ArrayList<>();
        if (proc.execute()) {
            //System.out.println("SP UTILERIAS_PKG.p_getUoMList ejecutado");
            try {
                list.addAll((List<Uom>) proc.getResultList());
            } catch (Exception e) {
                System.out.println("Trono UTILERIAS_PKG.p_getUoMList");
                System.out.println(e.getMessage());
            }
        }
        return list;
    }
    
}
