package com.metalsa.aprobacion.repository;

import com.metalsa.aprobacion.model.OaProyectoCuenta;
import com.metalsa.aprobacion.model.OaProyectoGastoCuenta;
import com.metalsa.aprobacion.model.OaProyectoTarea;
import com.metalsa.aprobacion.model.OaProyectos;
import com.metalsa.aprobacion.model.PresupuestoProyecto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import org.apache.log4j.Logger;

/**
 *
 * @author APOOD9272
 */
public class ProyectoRepositoryImpl implements ProyectoProcedures {

    private static final Logger LOG = Logger.getLogger(ProyectoRepositoryImpl.class);
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<OaProyectos> getProyectosUenUsuario(String idUsuario, Integer idUen) {

        List<OaProyectos> proyectos = new ArrayList<>();
        try {

            StoredProcedureQuery procedure = getEm().createStoredProcedureQuery("NVC_PKG_CUENTAS_SPX.SP_GET_PROYECTOS");
            procedure.registerStoredProcedureParameter("p_uen", Integer.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_idusuario", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("cursor_out", void.class, ParameterMode.REF_CURSOR);

            procedure.setParameter("p_uen", idUen);
            procedure.setParameter("p_idusuario", idUsuario);

            if (procedure.execute()) {

                List<Object[]> cursor = (List<Object[]>) procedure.getResultList();
                for (Object[] row : cursor) {
                    OaProyectos proyecto = new OaProyectos();
                    int index = -1;
                    proyecto.setIdProyecto(getLongValue(row[++index]));
                    proyecto.setCodProyecto((String) row[++index]);
                    proyecto.setNombreProyecto((String) row[++index]);
                    proyecto.setStartDate((String) row[++index]);
                    proyecto.setCompletionDate((String) row[++index]); 
                    proyectos.add(proyecto);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            System.out.println(e);
        }

        return proyectos;
    }

    @Override
    public List<OaProyectoTarea> getTareasProyecto(Long idProyecto) {

        List<OaProyectoTarea> tareas = new ArrayList<>();
        try {
            StoredProcedureQuery procedure = em.createStoredProcedureQuery("NVC_PKG_CUENTAS_SPX.SP_GET_TAREAS");
            procedure.registerStoredProcedureParameter("p_idproyecto", Long.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("cursor_out", void.class, ParameterMode.REF_CURSOR);
            procedure.setParameter("p_idproyecto", idProyecto);

            if (procedure.execute()) {
                List<Object[]> cursor = (List<Object[]>) procedure.getResultList();
                for (Object[] row : cursor) {
                    OaProyectoTarea tarea = new OaProyectoTarea();

                    int index = -1;

                    tarea.setIdProyecto(idProyecto);
                    tarea.setIdTarea(getLongValue(row[++index]));
                    tarea.setCodTarea((String) row[++index]);
                    tarea.setNombreTarea((String) row[++index]);
                    tarea.setDescTarea((String) row[++index]);
                    tareas.add(tarea);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            System.out.println(e);
        }
        return tareas;
    }

    @Override
    public List<OaProyectoGastoCuenta> getErogacionesProyecto(Long idProyecto, Long idTarea, String tipo, String reqAlm) {
        List<OaProyectoGastoCuenta> gastosCuenta = new ArrayList<>();

        StoredProcedureQuery procedure = em.createStoredProcedureQuery("NVC_PKG_CUENTAS_SPX.SP_GET_EROGACION");
        procedure.registerStoredProcedureParameter("p_idproyecto", Long.class, ParameterMode.IN);
        procedure.registerStoredProcedureParameter("p_idtarea", Long.class, ParameterMode.IN);
        procedure.registerStoredProcedureParameter("p_tipo", String.class, ParameterMode.IN);
        procedure.registerStoredProcedureParameter("p_reqalm", String.class, ParameterMode.IN);
        procedure.registerStoredProcedureParameter("cursor_out", void.class, ParameterMode.REF_CURSOR);

        procedure.setParameter("p_idproyecto", idProyecto);
        procedure.setParameter("p_idtarea", idTarea);
        procedure.setParameter("p_tipo", tipo);
        procedure.setParameter("p_reqalm", reqAlm);

        if (procedure.execute()) {
            List<Object[]> cursor = (List<Object[]>) procedure.getResultList();
            for (Object[] row : cursor) {
                int index = -1;

                OaProyectoGastoCuenta gastoCuenta = new OaProyectoGastoCuenta();
                gastoCuenta.setExpType((String) row[++index]);
                gastoCuenta.setTipoGasto((String) row[++index]);
                gastoCuenta.setReqAlm((String) row[++index]);
                gastoCuenta.setTipo((String) row[++index]);

                gastosCuenta.add(gastoCuenta);

            }
        }
        return gastosCuenta;
    }

    @Override
    public List<OaProyectoCuenta> getCuentasProyecto(Long idProyecto, Long idTarea, String tipoErogacion, String lenguaje) {
        List<OaProyectoCuenta> cuentas = new ArrayList<>();

        StoredProcedureQuery procedure = em.createStoredProcedureQuery("NVC_PKG_CUENTAS_SPX.SP_GET_CUENTAS_PROYECTO");

        procedure.registerStoredProcedureParameter("p_idproyecto", Long.class, ParameterMode.IN);
        procedure.registerStoredProcedureParameter("p_idtarea", Long.class, ParameterMode.IN);
        procedure.registerStoredProcedureParameter("p_erogacion", String.class, ParameterMode.IN);
        procedure.registerStoredProcedureParameter("p_lenguaje", String.class, ParameterMode.IN);
        procedure.registerStoredProcedureParameter("cursor_out", void.class, ParameterMode.REF_CURSOR);

        procedure.setParameter("p_idproyecto", idProyecto);
        procedure.setParameter("p_idtarea", idTarea);
        procedure.setParameter("p_erogacion", tipoErogacion);
        procedure.setParameter("p_lenguaje", lenguaje);

        if (procedure.execute()) {
            List<Object[]> cursor = (List<Object[]>) procedure.getResultList();
            for (Object[] row : cursor) {
                int index = -1;

                OaProyectoCuenta combinacion = new OaProyectoCuenta();
                combinacion.setIdProyecto(idProyecto);
                combinacion.setIdTarea(idTarea);
                combinacion.setIdCuenta(getLongValue(row[++index]));
                combinacion.setSegmento1((String) row[++index]);
                combinacion.setSegmento2((String) row[++index]);
                combinacion.setSegmento3((String) row[++index]);
                combinacion.setSegmento4((String) row[++index]);
                combinacion.setSegmento5((String) row[++index]);
                combinacion.setSegmento6((String) row[++index]);
                combinacion.setSegmento7((String) row[++index]);
                combinacion.setSegmento8((String) row[++index]);

                cuentas.add(combinacion);
            }
        }

        for (OaProyectoCuenta cuenta : cuentas) {
            System.out.println(cuenta.toString());
        }
        return cuentas;
    }

    public String getAprobadorProyecto(Long idProyecto) {
        System.out.println("- - - - - - - - - getAprobadorProyecto{idProyecto=>" + idProyecto + "} - - - - - - - - -");
        String aprobador = (String) getEm().createNativeQuery("SELECT get_proyectowner(?1) FROM DUAL")
                .setParameter("1", idProyecto)
                .getSingleResult();
        return aprobador;
    }

    public EntityManager getEm() {
        return em;
    }

    private Long getLongValue(Object val) {
        if (null == val) {
            return null;
        }
        if (val instanceof BigDecimal) {
            return ((BigDecimal) val).longValue();
        }
        return (Long) val;
    }

    @Override
    public List<String> getAprobadoresProyecto(Long idrequisicion) {

        try {
            StoredProcedureQuery procedure = em.createStoredProcedureQuery("PKG_COMPRAS.CONSULTA_APROB_PROY");
            procedure.registerStoredProcedureParameter("p_requi", Long.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("cursor_out", void.class, ParameterMode.REF_CURSOR);
            procedure.setParameter("p_requi", idrequisicion);
            procedure.execute();
            List<String> result = procedure.getResultList();
            return result;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            System.out.println(e);
        }
        return new ArrayList<>();
    }

    @Override
    public List<PresupuestoProyecto> getProyectosByIdUsuario(String idUsuario) {
        LOG.debug(" **** getProyectosByIdUsuario *** ");
        List<PresupuestoProyecto> proyectos = new ArrayList<>();
        try {
            StoredProcedureQuery procedure = em.createStoredProcedureQuery("get_proy_by_user");
            procedure.registerStoredProcedureParameter("p_id_usuario", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("out_cursor", void.class, ParameterMode.REF_CURSOR);
            procedure.setParameter("p_id_usuario", idUsuario);
            procedure.execute();

            List<Object[]> cursor = (List<Object[]>) procedure.getResultList();
            if (cursor != null && !cursor.isEmpty()) {
                PresupuestoProyecto proyecto;
                for (Object[] row : cursor) {
                    proyecto = new PresupuestoProyecto();
                    int index = -1;
                    proyecto.setNombreUen(row[++index] == null ? "" : (String) row[index]);
                    proyecto.setNombreProyecto(row[++index] == null ? "" : (String) row[index]);
                    proyecto.setProyecto(row[++index] == null ? 0 : Long.valueOf(row[index].toString()));
                    proyectos.add(proyecto);
                }
            }

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        LOG.debug(" **** getProyectosByIdUsuario *** " + proyectos);
        return proyectos;
    }

    @Override
    public List<PresupuestoProyecto> getProyectBudgetById(Integer idProyecto) {
        LOG.debug(" **** getProyectBudgetById *** ");
        List<PresupuestoProyecto> proyectos = new ArrayList<>();
        try {
            StoredProcedureQuery procedure = em.createStoredProcedureQuery("get_ppto_proy");
            procedure.registerStoredProcedureParameter("p_id_proyecto", Integer.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("out_cursor", void.class, ParameterMode.REF_CURSOR);
            procedure.setParameter("p_id_proyecto", idProyecto);
            procedure.execute();

            List<Object[]> cursor = (List<Object[]>) procedure.getResultList();

            if (cursor != null && !cursor.isEmpty()) {
                PresupuestoProyecto proyecto;
                for (Object[] row : cursor) {
                    proyecto = new PresupuestoProyecto();
                    int index = -1;
                    proyecto.setTarea(row[++index] == null ? 0 : Long.valueOf(row[index].toString()));
                    proyecto.setNombreTarea(row[++index] == null ? "" : (String) row[index]);
                    proyecto.setTipoGasto(row[++index] == null ? "" : (String) row[index]);
                    proyecto.setInicial(row[++index] == null ? 0 : Double.valueOf(row[index].toString()));
                    proyecto.setComprometido(row[++index] == null ? 0 : Double.valueOf(row[index].toString()));
                    proyecto.setActual(row[++index] == null ? 0 : Double.valueOf(row[index].toString()));
                    proyecto.setDisponible(row[++index] == null ? 0 : Double.valueOf(row[index].toString()));
                    proyectos.add(proyecto);
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return proyectos;
    }
}
