package com.metalsa.catalogo.service;

import com.metalsa.aprobacion.repository.UsuarioRepository;
import com.metalsa.catalogo.pojo.CatalogoFiltro;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

/**
 *
 * @author miguel.rdz
 * @param <T>
 * @param <ID>
 */
@Log4j
@Service
public class CatalogoServiceImpl<T, ID extends Serializable> implements CatalogoService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Qualifier("application")
    @Autowired
    private MessageSource messages;

    @Override
    public List<CatalogoFiltro> getItemEstatus(String idIdioma) {
        List<CatalogoFiltro> listEstatus = new ArrayList();
        try {
            String locale;
            switch (idIdioma) {
                case "ESA":
                    locale = "ES";
                    break;
                case "US":
                    locale = "EN";
                    break;
                default:
                    locale = "ES";
                    break;
            }
            List<Object[]> results = em.createNativeQuery("select ie.id_estatus, ei.desc_estatus from "
                    + "(select distinct id_estatus from nvc_tbl_catalogo_item) ie, dc_estatus_idioma ei "
                    + "where ie.id_estatus = ei.id_estatus and ie.id_estatus != 1 and ei.id_idioma = :idIdioma ")
                    .setParameter("idIdioma", idIdioma)
                    .getResultList();
            results.stream().map((result) -> {
                CatalogoFiltro estatus = new CatalogoFiltro();
                estatus.setIntKey(Integer.parseInt(result[0].toString()));
                estatus.setStrKey(result[0].toString());
                estatus.setDescripcion(result[1].toString());
                estatus.setDescripcion(messages.getMessage("cat.estatus_" + result[0].toString(), null, new Locale(locale)));
                return estatus;
            }).forEachOrdered((estatus) -> {
                listEstatus.add(estatus);
            });
            CatalogoFiltro publicado = new CatalogoFiltro();
            CatalogoFiltro noPublicado = new CatalogoFiltro();
            publicado.setIntKey(1);
            publicado.setStrKey("45");
            publicado.setDescripcion(messages.getMessage("cat.estatus_" + publicado.getStrKey(), null, new Locale(locale)));
            listEstatus.add(publicado);
            noPublicado.setIntKey(1);
            noPublicado.setStrKey("46");
            noPublicado.setDescripcion(messages.getMessage("cat.estatus_" + noPublicado.getStrKey(), null, new Locale(locale)));
            listEstatus.add(noPublicado);
        } catch (Exception e) {
            log.error("error en : getItemEstatus :" + e.getMessage());
        }
        return listEstatus;
    }

}
