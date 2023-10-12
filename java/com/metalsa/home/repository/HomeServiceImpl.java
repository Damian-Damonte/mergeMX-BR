package com.metalsa.home.repository;

import com.metalsa.home.model.NoticiaHome;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

/**
 *
 * @author miguel.rdz
 * @param <T>
 * @param <ID>
 */
@Log4j
@Service
public class HomeServiceImpl<T, ID extends Serializable> implements HomeService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Iterable<NoticiaHome> getNoticias(String idIdioma) {
        Iterable<NoticiaHome> noticias = null;
        try {
            noticias = em.createNamedQuery("NoticiaHome.getNoticias").setParameter("idIdioma", idIdioma).getResultList();
        } catch (Exception e) {
            log.error("error en : NoticiaHome.getNoticias :" + e.getMessage());
        }
        return noticias;
    }
}
