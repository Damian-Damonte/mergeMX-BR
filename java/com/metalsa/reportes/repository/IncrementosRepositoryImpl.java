/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.reportes.repository;

import com.metalsa.reportes.model.Incrementos;
import com.metalsa.reportes.model.Parametros;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import lombok.extern.log4j.Log4j;

@Log4j
public class IncrementosRepositoryImpl implements IncrementosProcedure {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Incrementos> getReporte(Parametros parametros) {
        log.debug("----------------------------- getReporte -------------------------------");
        log.debug(parametros.toString());

        List<Incrementos> result;
        try {

            StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("getReporte");
            proc.setParameter("p_num_solicitud", parametros.getNumeroSolicitud());
            proc.setParameter("p_idioma", parametros.getIdioma());
            proc.setParameter("p_id_uens", parametros.getIduens());
            proc.setParameter("p_id_ccs", parametros.getIdccs());
            proc.setParameter("p_requisitores", parametros.getIdrequisitores());
            proc.setParameter("p_usuarios", parametros.getIdaprobadores());
            proc.setParameter("p_show_all", ""+parametros.isShowAll());
            proc.setParameter("p_categorias", parametros.getIdcategorias());
            proc.setParameter("p_periodo_inicial", parametros.getPeriodoInicial());
            proc.setParameter("p_periodo_final", parametros.getPeriodoFinal());
            proc.setParameter("p_fecha_inicial", parametros.getFechaInicial());
            proc.setParameter("p_fecha_final", parametros.getFechaFinal());

            proc.execute();
            result = proc.getResultList();
        } catch (Exception e) {
            log.debug(e.getLocalizedMessage(),e);
            result = new ArrayList<>();
        }

        return result;
    }

}
