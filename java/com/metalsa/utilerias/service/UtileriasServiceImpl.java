/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.utilerias.service;

import com.metalsa.utilerias.model.ClasificacionArbol;
import com.metalsa.utilerias.pojo.AprobarExamenPojo;
import com.metalsa.utilerias.pojo.MyHDRowPojo;
import com.metalsa.utilerias.pojo.MyHDRowSetPojo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

/**
 *
 * @author jose.jimenez07
 */
@Service
@Log4j
public class UtileriasServiceImpl implements UtileriasService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<ClasificacionArbol> getReclasificacion(Long idSubFamila) {
        StoredProcedureQuery proc = em.createStoredProcedureQuery("NVC_PKG_FAMILIES_SPX.GET_ARBOL_BY_ID_SUB", ClasificacionArbol.class);
        proc.registerStoredProcedureParameter("P_ID_SUB_FAMILIA", Long.class, ParameterMode.IN);
        proc.registerStoredProcedureParameter("OUT_CURSOR", void.class, ParameterMode.REF_CURSOR);

        proc.setParameter("P_ID_SUB_FAMILIA", idSubFamila);

        List<ClasificacionArbol> list = new ArrayList<>();
        try {
            if (proc.execute()) {
                list = (List<ClasificacionArbol>) proc.getResultList();
            }
        } catch (Exception e) {
            log.error("erro getReclasificacion: ", e);

        }
        return list;
    }

<<<<<<< HEAD
=======
    @Override
    public void guardarCalificacion(MyHDRowSetPojo myHDRowSetPojo) {
        if (myHDRowSetPojo.getMyHDRow() == null) {
            return;
        }
        for (MyHDRowPojo item : myHDRowSetPojo.getMyHDRow()) {
            try {
                StoredProcedureQuery procedure = em.createStoredProcedureQuery("NVC_PKG_ELEARNING.GUARDA_CALIFICACION");
                procedure.registerStoredProcedureParameter("v_id_empleado", String.class, ParameterMode.IN);
                procedure.registerStoredProcedureParameter("v_id_modulo", String.class, ParameterMode.IN);
                procedure.registerStoredProcedureParameter("v_calificacion", Double.class, ParameterMode.IN);
                procedure.registerStoredProcedureParameter("v_country", String.class, ParameterMode.IN);
                procedure.registerStoredProcedureParameter("v_in_date", Date.class, ParameterMode.IN);
                procedure.registerStoredProcedureParameter("v_intento_id", Long.class, ParameterMode.IN);
                procedure.registerStoredProcedureParameter("v_value_out", String.class, ParameterMode.OUT);
                procedure.setParameter("v_id_empleado", item.getEmpNo());
                procedure.setParameter("v_id_modulo", item.getObjectiveId());
                procedure.setParameter("v_calificacion", item.getRawScore() == null ? 0 : Double.parseDouble(item.getRawScore()));
                procedure.setParameter("v_country", item.getCountry());
                procedure.setParameter("v_in_date", item.getInDate());
                procedure.setParameter("v_intento_id", item.getId());
                procedure.execute();
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }

    }

    @Override
    public AprobarExamenPojo aproboExamen(String url, String idUsuario, Integer idRol) {
        Integer respuesta;
        Integer isIgnorar;
        AprobarExamenPojo response = new AprobarExamenPojo();
        String moduloReporte;
        try {

            if (url != null && url.contains("mainMenu.xhtml")) {
                response.setResult(-1);
                return response;
            }
            try {
                moduloReporte = (String) em.createNativeQuery("select descripcion from NVC_TBL_ELEARNING_ROLES er\n"
                        + "inner join NVC_TBL_CAT_ELEARNING r on er.cat_elearning = r.id\n"
                        + "where rol_id = ?1")
                        .setParameter(1, idRol)
                        .getSingleResult();
            } catch (Exception e) {
                moduloReporte = null;
            }
            if (moduloReporte != null) {
                StoredProcedureQuery procedure = em.createStoredProcedureQuery("NVC_PKG_ELEARNING.APROBO_EXAMEN");
                procedure.registerStoredProcedureParameter("v_url", String.class, ParameterMode.IN);
                procedure.registerStoredProcedureParameter("v_id_usuario", String.class, ParameterMode.IN);
                procedure.registerStoredProcedureParameter("v_id_rol", Integer.class, ParameterMode.IN);
                procedure.registerStoredProcedureParameter("v_value_out", String.class, ParameterMode.OUT);
                procedure.registerStoredProcedureParameter("v_ignorar", String.class, ParameterMode.OUT);
                procedure.setParameter("v_url", url);
                procedure.setParameter("v_id_usuario", idUsuario);
                procedure.setParameter("v_id_rol", idRol);
                procedure.execute();

                respuesta = Integer.parseInt(procedure.getOutputParameterValue("v_value_out").toString());
                isIgnorar = Integer.parseInt(procedure.getOutputParameterValue("v_ignorar").toString());
                response.setResult(respuesta);
                response.setIgnorar((isIgnorar != 0));
            } else {
                response.setResult(-1);
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return response;
    }
>>>>>>> mexico
}
