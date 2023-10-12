package com.metalsa.catalogo.service;

import com.metalsa.catalogo.pojo.CatalogoFiltro;
import java.util.List;

/**
 *
 * @author miguel.rdz
 */
public interface CatalogoService {

    List<CatalogoFiltro> getItemEstatus(String idIdioma);

}
