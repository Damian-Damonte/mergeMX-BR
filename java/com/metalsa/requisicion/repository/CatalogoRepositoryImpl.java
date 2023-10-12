package com.metalsa.requisicion.repository;

import com.metalsa.catalogo.model.NvcTblCatalogoLocalizacion;
import com.metalsa.catalogo.model.NvcTblCatalogoUenSite;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gamaliel Espinoza M.
 */
@Repository
public class CatalogoRepositoryImpl implements CatalogoRepository {
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public List<NvcTblCatalogoUenSite> findSitesByLocalizacion(Integer idLocalizacion) {
        return (List<NvcTblCatalogoUenSite>) em.createQuery(
                "SELECT n FROM NvcTblCatalogoUenSite n " +
                "WHERE n.idCatalogoLocalizacion = :idCatalogoLocalizacion")
                .setParameter("idCatalogoLocalizacion", idLocalizacion)
                .getResultList();
    }
    
    @Override
    public NvcTblCatalogoLocalizacion findByCatUenLocalizacion(Integer idUen, Integer idLocalizacion) {
        return (NvcTblCatalogoLocalizacion) em.createQuery(
                        "SELECT n FROM NvcTblCatalogoLocalizacion n " +
                                "Where n.idCatalogoUen.idCatalogoUen = :idCatalogoUen " +
                                "AND n.idLocalizacion = :idLocalizacion")
                .setParameter("idCatalogoUen", idUen)
                .setParameter("idLocalizacion", idLocalizacion)
                .getSingleResult();
//        return null;
    }
}
