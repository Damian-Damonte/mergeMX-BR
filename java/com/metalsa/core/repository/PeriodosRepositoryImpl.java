/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.repository;

import com.metalsa.core.model.Periodos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

/**
 *
 * @author jose.jimenez07
 */
//R41223
@Log4j
@Service
public class PeriodosRepositoryImpl implements PeriodosRepositoryProcedure {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Periodos> findPeriodByProyectFrom(String idioma, Integer idProyecto) {
        log.debug(" **** findPeriodByProyectFrom *** idioma:" + idioma + " idProyecto:" + idProyecto);
        StoredProcedureQuery store = em.createNamedStoredProcedureQuery("Periodos.findPeriodByProyectFrom");
        store.setParameter("p_id_idioma", idioma);
        store.setParameter("p_id_proyecto", idProyecto);
        store.execute();
        List<Periodos> result = store.getResultList();
        return result;
    }
    
    @Override
    public List<Periodos> findByPorRangoAnioMes(Integer anioinicial, Integer aniofinal, Integer mesinicial, Integer mesfinal, String idioma) {
        log.debug("findByPorRangoAnioMes{anioinicial:" + anioinicial + ",aniofinal:" + aniofinal + "}");
        em.clear();
        StoredProcedureQuery store = em.createNamedStoredProcedureQuery("Periodos.findByPorRangoAnioMes");
        store.setParameter("p_anioinicial", anioinicial);
        store.setParameter("p_aniofinal", aniofinal);
        store.setParameter("p_mesinicial", mesinicial);
        store.setParameter("p_mesfinal", mesfinal);
        store.setParameter("p_idioma", idioma);
        store.execute();
        List<Periodos> result = store.getResultList();
        return result;
    }

    @Override
    public Periodos getPeriodoActual(String idioma) {
        em.clear();
        return em.createNamedQuery("Periodos.getPeriodoActual",Periodos.class)
                .setParameter("idioma", idioma).getSingleResult();
    }
    
    //RDM54073
    @Override
    public List<Periodos> getPeriodosContablesFuturos(Integer id_uen, String idioma){
        StoredProcedureQuery store = em.createNamedStoredProcedureQuery("Periodos.getPeriodosContablesFuturos");
        store.setParameter("p_id_uen", id_uen);
        store.setParameter("p_idioma", idioma);
        store.execute();
        List<Periodos> result = store.getResultList();
        return result;
    }
    @Override
    public List<Periodos> getPeriodosContablesPasado(Integer id_uen, String idioma){
        StoredProcedureQuery store = em.createNamedStoredProcedureQuery("Periodos.getPeriodosContablesPasado");
        store.setParameter("p_id_uen", id_uen);
        store.setParameter("p_idioma", idioma);
        store.execute();
        List<Periodos> result = store.getResultList();
        return result;
    }
     //RDM54073
}
