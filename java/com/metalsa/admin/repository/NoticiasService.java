package com.metalsa.admin.repository;

import com.metalsa.admin.model.NoticiaRequest;
import com.metalsa.admin.model.NoticiaResponse;
import com.metalsa.admin.pojo.FiltrosNoticias;
import com.metalsa.core.model.Rol;
import com.metalsa.home.model.NoticiaHome;
import com.metalsa.perfil.pojo.Uen;

/**
 *
 * @author miguel.rdz
 */
public interface NoticiasService {

    Iterable<NoticiaHome> getActuales();

    Iterable<NoticiaHome> getHistorico();
    
    FiltrosNoticias getFiltrosHist();

    Iterable<Uen> getUens();

    Iterable<Uen> getUensById(String uens);

    boolean updateUens(NoticiaRequest req);

    Iterable<Rol> getRoles();

    boolean updateRoles(NoticiaRequest req);

    Iterable<Rol> getRolesById(String roles);

    boolean orderNoticia(NoticiaRequest req);

    NoticiaResponse insertNoticia(NoticiaRequest req);

    NoticiaResponse updateNoticia(NoticiaRequest req);

    boolean deleteNoticia(String idUsuario, Integer idNoticia);
    
    boolean pauseNoticia(Integer idNoticia, Integer status);

}
