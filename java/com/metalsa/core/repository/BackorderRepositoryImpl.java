package com.metalsa.core.repository;

import com.metalsa.core.model.Backorder;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

/**
 * @author APOMR10051
 * @param <T>
 * @param <ID>
 */
@Log4j
@Service
public class BackorderRepositoryImpl<T, ID extends Serializable> implements BackorderRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Backorder> getBackorders(Integer idRequisicion, Integer idPartida) {
        List<Backorder> backorders = new ArrayList();
        List<Object[]> resultset;
        String out = "OK";
        try {
            StoredProcedureQuery procedure = em.createStoredProcedureQuery("NVC_PKG_SPX_PUNCHOUT.GET_BACKORDERS");
            procedure.registerStoredProcedureParameter("p_id_requisicion", Integer.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_id_partida", Integer.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("out_message", String.class, ParameterMode.OUT);
            procedure.registerStoredProcedureParameter("cursor_out", void.class, ParameterMode.REF_CURSOR);
            procedure.setParameter("p_id_requisicion", idRequisicion);
            procedure.setParameter("p_id_partida", idPartida);
            if (procedure.execute()) {
                resultset = (List<Object[]>) procedure.getResultList();
                out = (String) procedure.getOutputParameterValue("out_message");
                resultset.stream().map((row) -> {
                    Backorder b = new Backorder();
                    int index = -1;
                    b.setIdBackorder(Integer.parseInt(row[++index].toString()));
                    b.setIdRequisicion(Integer.parseInt(row[++index].toString()));
                    b.setIdPartida(Integer.parseInt(row[++index].toString()));
                    b.setCantidad(Integer.parseInt(row[++index].toString()));
                    b.setTiempoEntrega(Integer.parseInt(row[++index].toString()));
                    return b;
                }).forEachOrdered((b) -> {
                    backorders.add(b);
                });
            }
            log.debug(out);
        } catch (Exception e) {
            log.error("error en : getBackorders :" + e.getMessage());
        }
        return backorders;
    }

}
