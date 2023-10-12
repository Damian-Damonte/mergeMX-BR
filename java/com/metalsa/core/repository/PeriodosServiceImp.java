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
@Log4j
@Service
public class PeriodosServiceImp implements PeriodosService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Periodos> findByPorRangoAnioMes(Integer anioinicial, Integer aniofinal, Integer mesinicial, Integer mesfinal, String idioma) {
        log.debug("findByPorRangoAnioMes{anioinicial:" + anioinicial + ",aniofinal:" + aniofinal + "}");
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
}
