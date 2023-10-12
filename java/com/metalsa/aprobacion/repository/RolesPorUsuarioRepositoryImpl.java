//MDA_CONTRALOR
package com.metalsa.aprobacion.repository;

import com.metalsa.recibos.model.Requisitor;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

/**
 * 
 * @author Oscar del Angel
 */
public class RolesPorUsuarioRepositoryImpl implements RolesPorUsuarioProcedures {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Requisitor> getUsersByRol(Integer idUen, String clave) {
        List<Requisitor> result;
        try {
            StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("RolesPorUsuario.getUsersByRol");
            proc.setParameter("p_id_uen", idUen);
            proc.setParameter("p_clave_rol", clave);
            proc.execute();
            result = proc.getResultList();
        } catch (Exception e) {
            result = new ArrayList<>();
        }

        return result;
    }

}
