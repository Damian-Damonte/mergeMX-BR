package com.metalsa.aprobacion.repository;

import com.metalsa.aprobacion.model.NvcTblFad;
import lombok.extern.log4j.Log4j;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * @author miguel.rdz
 */
@Log4j
public class NvcTblFadRepositoryImpl implements NvcTblFadProcedures {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<NvcTblFad> getFadByIdReq(Integer idReq) {
        System.out.println("- - - - - - - - - - - - getFadByIdReq {idReq=>" + idReq + "}- - - - - - - - - - - - - - - - - -");
        try {
            StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("getFadByIdReq");
            proc.setParameter("P_ID_REQ", idReq);
            proc.execute();
            List<NvcTblFad> list = new ArrayList<>();
            try {
                if (proc.execute()) {
                    list.addAll((List<NvcTblFad>) proc.getResultList());
                }
            } catch (Exception e) {
                log.error(e);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            System.out.println(e);
            return null;
        }
    }
}
