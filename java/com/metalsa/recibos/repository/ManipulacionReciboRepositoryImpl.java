/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.repository;

import com.metalsa.recibos.model.ImprimeRecibo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import org.apache.log4j.Logger;

/**
 *
 * @author edgar.leal
 */
public class ManipulacionReciboRepositoryImpl implements ManipulacionReciboProcedure {
    private static final Logger LOG = Logger.getLogger(ManipulacionReciboRepositoryImpl.class);
    @PersistenceContext
    private EntityManager em;

    //<R17486>
    @Override
    public List<ImprimeRecibo> imprimeRecibo(String P_USUARIO, String P_FORMAT_DATE, 
            String P_REQUICISION, String P_ORDEN_COMPRA, String P_FINI, String P_FFIN, 
            String P_FOLIO,String P_ID_UENS, String P_MOSTRAR_RECIBOS, Integer P_PAGINA, Integer P_PAGSIZE ) {
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("imprimeRecibo");
        
        proc.setParameter("P_USUARIO", P_USUARIO);
        proc.setParameter("P_FORMAT_DATE", P_FORMAT_DATE);
        proc.setParameter("P_REQUICISION", P_REQUICISION);
        proc.setParameter("P_ORDEN_COMPRA", P_ORDEN_COMPRA);
        proc.setParameter("P_FINI", P_FINI);
        proc.setParameter("P_FFIN", P_FFIN);
        proc.setParameter("P_FOLIO", P_FOLIO);
        proc.setParameter("P_IDUENS", P_ID_UENS);
        proc.setParameter("P_MOSTRARRECIBOS", P_MOSTRAR_RECIBOS);
        proc.setParameter("P_PAGINA", P_PAGINA);
        proc.setParameter("P_PAGESIZE", P_PAGSIZE);
        
        

        List<ImprimeRecibo> list = new ArrayList<>();
        if (proc.execute()) {
            System.out.println("SP ejecutado > " + getClass().getName());
            try {
                list.addAll((List<ImprimeRecibo>) proc.getResultList());
            } catch (Exception e) {
                LOG.error("Trono > " + e.getMessage());
            }
        }
        return list;
        
        
    }
    
    @Override
    public Integer cuentaRecibo(String P_USUARIO, String P_FORMAT_DATE, 
            String P_REQUICISION, String P_ORDEN_COMPRA, String P_FINI, String P_FFIN, 
            String P_FOLIO,String P_ID_UENS, String P_MOSTRAR_RECIBOS) {
        
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("cuentaRecibos");
        Integer total = 0;
        proc.setParameter("P_USUARIO", P_USUARIO);
        proc.setParameter("P_FORMAT_DATE", P_FORMAT_DATE);
        proc.setParameter("P_REQUICISION", P_REQUICISION);
        proc.setParameter("P_ORDEN_COMPRA", P_ORDEN_COMPRA);
        proc.setParameter("P_FINI", P_FINI);
        proc.setParameter("P_FFIN", P_FFIN);
        proc.setParameter("P_FOLIO", P_FOLIO);
        proc.setParameter("P_IDUENS", P_ID_UENS);
        proc.setParameter("P_MOSTRARRECIBOS", P_MOSTRAR_RECIBOS);
        

        proc.execute();
            System.out.println("SP ejecutado > NVC_RECIBOS_SPX_PKG.CUENTA_RECIBOS");
            try {
                total = (Integer) proc.getOutputParameterValue("TOTAL");
            } catch (Exception e) {
                LOG.error("Trono > " + e.getMessage());
            }
        
        return total;
        
        
    }
    //</R17486>
    
    @Override
    public List<ImprimeRecibo> detalleRecibo(String P_REQUICISION, String P_FOLIO, String P_FORMAT_DATE) {
       StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("detalleRecibo");
        
        proc.setParameter("P_FORMAT_DATE", P_FORMAT_DATE);
        proc.setParameter("P_REQUICISION", P_REQUICISION);
        proc.setParameter("P_FOLIO", P_FOLIO);

        //ImprimeRecibo imprimeRecibo = new ImprimeRecibo();
        List<ImprimeRecibo> list = new ArrayList<>();
        
        if (proc.execute()) {
            LOG.debug("SP ejecutado");
            try {
                list.addAll((List<ImprimeRecibo>) proc.getResultList()); //getSingleResult()
            } catch (Exception e) {
                LOG.debug("Trono");
                LOG.debug(e.getMessage());
            }
        }
        return list;
    }
    
    
}
