package com.metalsa.requisicion.repository;

import java.util.List;
import com.metalsa.catalogo.model.NvcTblCatalogoLocalizacion;
import com.metalsa.catalogo.model.NvcTblCatalogoUenSite;

/**
 *
 * @author Gamaliel Espinoza M.
 */
public interface CatalogoRepository {
    NvcTblCatalogoLocalizacion findByCatUenLocalizacion(Integer idUen, Integer idLocalizacion);
    List<NvcTblCatalogoUenSite> findSitesByLocalizacion(Integer idLocalizacion);
}
