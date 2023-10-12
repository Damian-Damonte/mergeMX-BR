/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.repository;

import com.metalsa.recibos.model.ResultadoCambioResponsable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

/**
 *
 * @author yair.nunez
 */
public class CambioResponsableRepositoryImpl implements CambioResponsableProcedures {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<ResultadoCambioResponsable> consultaCambioResponsable(Integer idRequisicion,String idUsuario ) {
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("consultaCambioResponsable");
        
        proc.setParameter("p_id_requisicion", idRequisicion);
        proc.setParameter("p_id_usuario", idUsuario);
        List<ResultadoCambioResponsable> list = new ArrayList<>();
        if (proc.execute()) {
            System.out.println("SP ejecutado");
            try {
                list.addAll((List<ResultadoCambioResponsable>) proc.getResultList());
            } catch (Exception e) {
                System.out.println("Trono");
                System.out.println(e.getMessage());
            }
        }
        return list;
    }

    @Override
    public boolean actualizarRecibe(Integer idRequisicion, String idUsuario, String tipoUsuario) {
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("actualizarRecibe");
        proc.setParameter("p_id_requisicion",idRequisicion);
        proc.setParameter("p_id_usuario",idUsuario);
        proc.setParameter("p_tipo_usuario",tipoUsuario);
        try {
            proc.execute();
            return true;
        } catch (Exception e) {
            System.out.println("TRONO SP");
            return false;
        }
    }
    
}
