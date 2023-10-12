/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.repository;

import com.metalsa.recibos.model.ReporteRecibos;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

/**
 *
 * @author yair.nunez
 */
public class ReporteRecibosRepositoryImpl implements ReporteRecibosProcedures {
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public boolean generarReporteRecibos(String parametros,String parametros2,String idUsuario,String nombreReporte) {
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("generarReporteRecibos");
        proc.setParameter("EL_WHERE_ARMADO",parametros);
        proc.setParameter("EL_WHERE_ARMADO2",parametros2);
        proc.setParameter("P_ID_USUARIO",idUsuario);
        proc.setParameter("P_NOMBRE_REPORTE",nombreReporte);
        try {
            proc.execute();
            return true;
        } catch (Exception e) {
            System.out.println("TRONO SP");
            return false;
        }
        
    }

    @Override
    public String obtenerTimestamp() {
       StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("obtenerTimestamp");
       try {
            proc.execute();
            return (String) proc.getOutputParameterValue("eltiempoestampa");
        } catch (Exception e) {
            throw new RuntimeException("SP NO EJECUTADO", e);
        }
    }

    @Override
    public String insertaClave(String llave, String valor, String timestamp, String tipoDato,String proposito,Integer orden) {
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("insertaClave");
        proc.setParameter("pllave",llave);
        proc.setParameter("pvalor",valor);
        proc.setParameter("randomTimeStampa",timestamp);
        proc.setParameter("ptipodato",tipoDato);
        proc.setParameter("p_proposito", proposito);
        proc.setParameter("p_orden", orden);
        try{
            proc.execute();
            System.out.println("UTILERIAS_PKG.insertaClave EJECUTADO");
            return "Ejecutado";
        } catch (Exception e) {
            throw new RuntimeException("SP NO EJECUTADO", e);
        }
    }
    
    @Override
    public String enviarCorreoProveedor(String timestamp) {
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("enviarCorreoProveedor");
        proc.setParameter("p_id_session_tmp",timestamp);
        try{
            proc.execute();
            System.out.println("UTILERIAS_PKG.email_proveedor_occlose EJECUTADO");
            return "Ejecutado";
        } catch (Exception e) {
            throw new RuntimeException("SP NO EJECUTADO", e);
        }
    }

    @Override
    public String obtenerWhereQuery(String timestamp) {
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("obtenerWhereQuery");
        proc.setParameter("randomTimeStampa", timestamp);
       try {
            proc.execute();
            return (String) proc.getOutputParameterValue("consulta");
        } catch (Exception e) {
            throw new RuntimeException("SP NO EJECUTADO", e);
        }
    }

    @Override
    public List<ReporteRecibos> obtenerReporte(Integer idReporte) {
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("obtenerReporte");
        proc.setParameter("p_id_reporte",idReporte);
        List<ReporteRecibos> list = new ArrayList<>();
        if (proc.execute()) {
            System.out.println("NVC_RECIBOS_SPX_PKG.getReporteDetalle EJECUTADO");
            try {
                list.addAll((List<ReporteRecibos>) proc.getResultList());
            } catch (Exception e) {
                System.out.println("ERROR NVC_RECIBOS_SPX_PKG.getReporteDetalle");
                System.out.println(e.getMessage());
            }
        }
        return list;
    }

    @Override
    public boolean eliminarReporte(Integer idReporte) {
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("eliminarReporte");
        proc.setParameter("p_id_reporte",idReporte);
       try {
            proc.execute();
            return true;
        } catch (Exception e) {
            System.out.println("TRONO SP NVC_RECIBOS_SPX_PKG.deleteReporteMaestro");
            return false;
        }
    }

    
    
}
