/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.repository;

import com.metalsa.recibos.model.ReporteEstatus;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

/**
 *
 * @author yair.nunez
 */
public class ReporteResumenRepositoryImpl implements ReporteResumenProcedures{
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<ReporteEstatus> obtenerResumenReportes(String idUsuario) {
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("obtenerResumenReportes");
        proc.setParameter("p_id_usuario",idUsuario);
        List<ReporteEstatus> list = new ArrayList<>();
        if (proc.execute()) {
            System.out.println("NVC_RECIBOS_SPX_PKG.getReporteMaestro EJECUTADO");
            try {
                list.addAll((List<ReporteEstatus>) proc.getResultList());
            } catch (Exception e) {
                System.out.println("ERROR NVC_RECIBOS_SPX_PKG.getReporteMaestro");
                System.out.println(e.getMessage());
            }
        }
        return list;
    }
}
