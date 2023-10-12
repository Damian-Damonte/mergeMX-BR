package com.metalsa.home.repository;

import com.metalsa.home.model.NoticiaHome;

/**
 *
 * @author miguel.rdz
 */
public interface HomeService {

    Iterable<NoticiaHome> getNoticias(String idIdioma);

}
