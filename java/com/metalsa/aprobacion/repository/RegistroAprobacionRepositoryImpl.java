/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.aprobacion.repository;

import com.metalsa.aprobacion.model.LogRequisicion;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

/**
 * Created by Oscar
 *
 * @author Oscar del Angel
 */
public class RegistroAprobacionRepositoryImpl implements RegistroAprobacionProcedure {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void agregarRegistroAprobacion(LogRequisicion registro) {
        
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("agregarRegistroAprobacion");
        proc.setParameter("p_id_requisicion", registro.getIdRequisicion());
        proc.setParameter("p_id_partida", registro.getIdPartida());
        proc.setParameter("p_id_usuario", registro.getIdUsuario());
        proc.setParameter("p_accion", registro.getAccion());
        proc.setParameter("p_mensaje", registro.getMensaje());
        proc.setParameter("p_ora_error", registro.getOraError());
        proc.setParameter("p_app_origen", registro.getAppOrigen());
        proc.setParameter("p_modulo", registro.getModulo());
        proc.execute();
    
    }
    
}
