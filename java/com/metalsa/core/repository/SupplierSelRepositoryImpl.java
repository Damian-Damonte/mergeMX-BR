//<R43143>
package com.metalsa.core.repository;

import com.metalsa.core.model.RespDelRequisicion;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import lombok.extern.log4j.Log4j;

/**
 *
 * @author Oscar del Angel
 */
@Log4j
public class SupplierSelRepositoryImpl implements SupplierSelProcedures {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List getAprobadoresCC(Integer uen, String codigocc, String idioma) {
        log.debug("getAprobadoresCC{uen:"+uen+" ,codigocc:"+codigocc+" ,idioma:"+idioma);
        List<RespDelRequisicion> list = new ArrayList<>();

        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("SupplierSelSPX.getAprobadoresCC");
        proc.setParameter("p_id_uen", uen);
        proc.setParameter("p_codigo_cc", codigocc);
        proc.setParameter("p_idioma", idioma);
        proc.execute();

        List<Object[]> response = proc.getResultList();
        for (Object[] row : response) {
            int col = -1;
            RespDelRequisicion usuario = new RespDelRequisicion();
            usuario.setCodigoCC((String) row[++col]);
            usuario.setNombreCC((String) row[++col]);
            usuario.setAprobador((String) row[++col]);
            usuario.setNivelAprobacionRi((String) row[++col]);
            usuario.setNivelAprobacionRe((String) row[++col]);
            list.add(usuario);
        }
        return list;
    }
    
    @Override
    public boolean isMultiCc(String idRequisicion) {
        List<BigDecimal> resp
                = (List<BigDecimal>) em.createNativeQuery("select id_tipo_cargo from detalle_de_requisicion where id_Requisicion =:idRequisicion")
                        .setParameter("idRequisicion", idRequisicion).getResultList();
        Integer value = 0;
        if (!resp.isEmpty()) {
            value = resp.get(0) == null ? 0 : resp.get(0).intValue();
        }

        if (value.equals(3)) {
            return true;
        } else {
            return false;
        }

    }
    
    @Override
    public List<String> getAllDist(String idRequisicion) {
        List<String> listCc = new ArrayList<>();
        try {
            List<String> resp
                    = em.createNativeQuery("select  distinct  codigo_cc from NVC_TBL_DISTRIBUCION_REQUI dist \n"
                            + "inner join NVC_VM_OA_CC cc on dist.id_cc = cc.id_cc\n"
                            + "where id_requisicion =:idRequisicion\n"
                            + "and CC.LENGUAJE = 'ESA'")
                            .setParameter("idRequisicion", idRequisicion).getResultList();
            for (String tem : resp) {
                listCc.add(tem);
            }
        } catch (Exception e) {
            log.error("-------- Error -------------", e);
        }
        return listCc;
    }    
}
