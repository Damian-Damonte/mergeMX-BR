package com.metalsa.home.repository;

import com.metalsa.home.model.ItemsSugeridos;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

/**
 *
 * @author APOMR10051
 */
public class ItemsSugeridosRepositoryImpl implements ItemsSugeridosProcedures {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<ItemsSugeridos> GET_ITEMS_SUGERIDOS_BY_USER(String P_ID_USUARIO) {
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("GET_ITEMS_SUGERIDOS_BY_USER");
        proc.setParameter("P_ID_USUARIO", P_ID_USUARIO);
        List<ItemsSugeridos> list = new ArrayList<>();
        if (proc.execute()) {
            try {
                list.addAll((List<ItemsSugeridos>) proc.getResultList());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return list;
    }
}
