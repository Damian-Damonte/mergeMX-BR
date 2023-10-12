/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.utilerias.service;

import com.metalsa.utilerias.model.ClasificacionArbol;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

/**
 *
 * @author jose.jimenez07
 */
@Service
@Log4j
public class UtileriasServiceImpl implements UtileriasService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<ClasificacionArbol> getReclasificacion(Long idSubFamila) {
        StoredProcedureQuery proc = em.createStoredProcedureQuery("NVC_PKG_FAMILIES_SPX.GET_ARBOL_BY_ID_SUB", ClasificacionArbol.class);
        proc.registerStoredProcedureParameter("P_ID_SUB_FAMILIA", Long.class, ParameterMode.IN);
        proc.registerStoredProcedureParameter("OUT_CURSOR", void.class, ParameterMode.REF_CURSOR);

        proc.setParameter("P_ID_SUB_FAMILIA", idSubFamila);

        List<ClasificacionArbol> list = new ArrayList<>();
        try {
            if (proc.execute()) {
                list = (List<ClasificacionArbol>) proc.getResultList();
            }
        } catch (Exception e) {
            log.error("erro getReclasificacion: ", e);

        }
        return list;
    }

}
