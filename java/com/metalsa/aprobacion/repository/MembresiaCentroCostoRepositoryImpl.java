package com.metalsa.aprobacion.repository;

import com.metalsa.aprobacion.model.CcPorUsuario;
import com.metalsa.aprobacion.model.CentroCosto;
import com.metalsa.aprobacion.model.Usuario;
import com.metalsa.core.model.CentroCostoUsuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import lombok.extern.log4j.Log4j;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author APOOD9272
 */
@Log4j
public class MembresiaCentroCostoRepositoryImpl implements MembresiaCentroCostoProcedures,CentroCostoInterface {

    @PersistenceContext
    private EntityManager em;
    Integer intentos = 0;

    /**
     * Obtener los usuarios que pueden aprobar la requisicion en base a su monto
     * de aprobacion
     *
     * @param idUen uen
     * @param idioma idioma
     * @param codigo codigo del centro de costo
     * @param requisicion requisicion para verificar el monto
     *
     * @return lista de Usuarios
     */
    @Override
    public List<Usuario> getAprobadoresPorRango(String idUen, String idioma, String codigo, String requisicion) {
        List<Usuario> aprobadores;
        try {
            StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("CcPorUsuario.getAprobadoresPorRango");

            proc.setParameter("p_iduen", idUen);
            proc.setParameter("p_idioma", idioma);
            proc.setParameter("p_codigocc", codigo);
            proc.setParameter("p_requi", requisicion);
            proc.execute();
            aprobadores = proc.getResultList();

        } catch (Throwable e) {
            log.debug(e.getLocalizedMessage(), e);
            aprobadores = new ArrayList<>();
        }

        return aprobadores;
    }

    //MDA_REPORTES_FINANZAS	
    @Override
    public CcPorUsuario getRespccOrDel(Long idUen, Long idCentroCosto, String usuario, String tipoRelacion) {

        CcPorUsuario ccPorUsuario = null;
        try {
            ccPorUsuario = (CcPorUsuario) em.createNamedQuery("CcPorUsuario.get_RespccOrDel")
                    .setParameter("idUen", idUen)
                    .setParameter("idCentroCosto", idCentroCosto)
                    .setParameter("tipoRelacion", tipoRelacion)
                    .setParameter("idUsuario", usuario)
                    .getSingleResult();

        } catch (Exception e) {
            intentos = intentos + 1;
            if (intentos <= 1) {
                return getRespccOrDel(idUen, idCentroCosto, usuario, "Del");
            }
            log.error("Sin responsable o delegados");
        }
        intentos = 0;
        return ccPorUsuario;
    }

    @Override
    public List<CentroCostoUsuario> getByUserUen(String lang, String user, Integer uen) {
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("CentroCostoUsuario.get_by_user_uen");
        proc.setParameter("p_lang", lang);
        proc.setParameter("p_user", user);
        proc.setParameter("p_sbu", uen);
        return proc.getResultList();
    }

    @Override
    public List<CentroCostoUsuario> getByUserUenRelation(String lang, String user, String relation, Integer uen) {
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("CentroCostoUsuario.get_by_user_relation");
        proc.setParameter("p_lang", lang);
        proc.setParameter("p_user", user);
        proc.setParameter("p_relation", relation);
        proc.setParameter("p_sbu", uen);
        return proc.getResultList();
    }

    @Override
    public List<CentroCostoUsuario> getByUserUenRelations(String lang, String user, String relations, Integer uen) {
        em.clear();
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("CentroCostoUsuario.get_by_user_uen_relations");
        proc.setParameter("p_lang", lang);
        proc.setParameter("p_user", user);
        proc.setParameter("p_relations", relations);
        proc.setParameter("p_sbu", uen);
        return proc.getResultList();
    }
    
    @Override
    public List<CentroCostoUsuario> getByUserUenCC(String lang, String user,Integer uen,Long cc) {
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("CentroCostoUsuario.get_by_user_uen_cc");
        proc.setParameter("p_lang", lang);
        proc.setParameter("p_user", user);
        proc.setParameter("p_sbu", uen);
        proc.setParameter("p_id_cc", cc);
        return proc.getResultList();
    }

    @Override
    public List<CentroCosto> getAllDistinctCCS(String idioma) {
        em.clear();
        return em.createNamedQuery("CentroCosto.getAllDistinctCCS").setParameter(1, idioma).getResultList();
    }
    
    @Override
    public List<CentroCosto> getAllByIdUenAndIdioma(Long uen, String idioma) {
        em.clear();
        return em.createNamedQuery("CentroCosto.getAllByIdUenAndIdioma").setParameter(1, idioma).getResultList();
    }

    @Override
    public List<CentroCosto> getAllByRespAndDel(Long uen, String usuario, String idioma) {
        em.clear();
        return em.createNamedQuery("CentroCosto.getAllByRespAndDel").setParameter(1, uen).setParameter(2, usuario).setParameter(3,idioma).getResultList();
    }
    
}
