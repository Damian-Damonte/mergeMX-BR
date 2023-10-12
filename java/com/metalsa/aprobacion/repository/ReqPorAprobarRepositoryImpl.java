/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.aprobacion.repository;

import com.google.common.collect.Maps;
import com.metalsa.aprobacion.model.GastoAdicional;
import com.metalsa.aprobacion.model.PreaprobacionCC;//</RELEASE_BRASIL>
import com.metalsa.aprobacion.model.RedicionRequisicionVO;
import com.metalsa.aprobacion.model.ReqPorAprobar;
import com.metalsa.aprobacion.service.CheckBudgetRequest;
import com.metalsa.core.model.NvcTblProvSitesH;
import com.metalsa.core.model.Uens;
import java.math.BigDecimal;
import lombok.extern.log4j.Log4j;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.ParameterMode;//</RELEASE_BRASIL>

/**
 * @author jose.rivera02
 */
@Log4j
public class ReqPorAprobarRepositoryImpl implements ReqPorAprobarProcedures {

    @PersistenceContext
    private EntityManager em;

    private List<ReqPorAprobar> getRequisByProcedure2(ReqPorAprobarRepository.APPROVAL_TYPE type, Map<String, Object> params) {
        StoredProcedureQuery proc;
        switch (type) {
            case POR_CRITICAS:
                proc = em.createNamedStoredProcedureQuery("getRequisCriticas");
                proc.setParameter("p_uen", params.get("p_uen"));
                break;
            case POR_FUERARANGO:
                proc = em.createNamedStoredProcedureQuery("getRequisFueraRango");
                break;
            case POR_PREAPROBAR:
                proc = em.createNamedStoredProcedureQuery("getRequisPorPreAprobar");
                break;
            case POR_SUPERAPROBAR:
                //<ERM#14135>
                proc = em.createNamedStoredProcedureQuery("getRequisSuperAprobacion");
                proc.setParameter("p_id_uens", params.get("p_id_uens"));
                proc.setParameter("p_id_requisicion", params.get("p_id_requisicion"));
                //</ERM#14135>
                break;
            default:
                proc = em.createNamedStoredProcedureQuery("getRequisPorAprobar");
                break;
        }

        proc.setParameter("p_aprobador", params.get("p_aprobador"));

        List<ReqPorAprobar> list = new ArrayList<>();
        try {
            if (proc.execute()) {
                list.addAll((List<ReqPorAprobar>) proc.getResultList());
            }
        } catch (Exception e) {
            log.error("erro al invocar: " + type.name(), e);
        }
        return list;
    }

    @Override
    public List<ReqPorAprobar> getRequisPorAprobar(String user) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("p_aprobador", user);
        return getRequisByProcedure2(ReqPorAprobarRepository.APPROVAL_TYPE.POR_APROBAR, params);
    }

    @Override
    public List<ReqPorAprobar> getRequisPorPreAprobar(String user) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("p_aprobador", user);
        return getRequisByProcedure2(ReqPorAprobarRepository.APPROVAL_TYPE.POR_PREAPROBAR, params);
    }

    @Override
    public List<ReqPorAprobar> getRequisFueraRango(String user) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("p_aprobador", user);
        return getRequisByProcedure2(ReqPorAprobarRepository.APPROVAL_TYPE.POR_FUERARANGO, params);
    }

    @Override
    public List<ReqPorAprobar> getRequisCriticas(String user, Long uen) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("p_aprobador", user);
        params.put("p_uen", uen);
        return getRequisByProcedure2(ReqPorAprobarRepository.APPROVAL_TYPE.POR_CRITICAS, params);
    }

    //<ERM#14135>
    @Override
    public List<ReqPorAprobar> getRequisSuperAprobacion(String user, String idUens, String requisicion) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("p_aprobador", user);
        params.put("p_id_uens", idUens);
        params.put("p_id_requisicion", requisicion);
        return getRequisByProcedure2(ReqPorAprobarRepository.APPROVAL_TYPE.POR_SUPERAPROBAR, params);
    }
    //</ERM#14135>

    @Override
    public List<GastoAdicional> getGastosAdicionales(Integer idRequisicion, Integer idPartida, String idIdioma, String aprobador) {
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("getGastosAdicionales");
        proc.setParameter("p_id_requisicion", idRequisicion);
        proc.setParameter("p_id_partida", idPartida);
        proc.setParameter("p_id_idioma", idIdioma);
        proc.setParameter("p_aprobador", aprobador);

        List<GastoAdicional> list;
        try {
            proc.execute();
            list = new ArrayList<>((List<GastoAdicional>) proc.getResultList());
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            list = new ArrayList<>();
        }

        return list;
    }

    @Override
    public Integer cancelarRequisicion(Long requisicion, Long linea, String razonRechazo, String idAprobador, String modulo) {
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("cancelByLine");
        proc.setParameter("p_requisicion", requisicion);
        proc.setParameter("p_partida", linea);
        proc.setParameter("p_reason", razonRechazo);
        proc.setParameter("p_aprobador", idAprobador);
        proc.setParameter("p_modulo", modulo);

        try {
            return executeProcedureWithResult(proc, "p_result", "cancelarRequisicion");
        } catch (Exception e) {
            log.error("Error cancelando requisicion", e);
        }
        return -1;
    }
    
    @Override
    public Integer preaprobarRequisicion(Long requisicion, Long linea, String aprobador, String modulo) {
        log.debug("[INICIO] preaprobarRequisicion() -> requisicion: " + requisicion + ", linea: " + linea + ", aprobador: " + aprobador + ", modulo: " + modulo);
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("preaprobar_requisicion");
        proc.setParameter("p_requisicion", requisicion);
        proc.setParameter("p_partida", linea);
        proc.setParameter("p_aprobador", aprobador);
        proc.setParameter("p_modulo", modulo);

        Integer response = executeProcedureWithResult(proc, "p_result", "preaprobarRequisicion");
        log.debug("[FIN] preaprobarRequisicion() -> requisicion: " + requisicion + ", linea: " + linea + ", aprobador: " + aprobador + ", modulo: " + modulo
                + ". Response: " + response);
        return response;
    }

    @Override
    public Integer aprobarRequisicion(Long requisicion, Long linea, String aprobador, String modulo, Integer idCuenta) {
        log.debug("[INICIO] aprobarRequisicion() -> requisicion: " + requisicion + ", linea: " + linea + ", aprobador: " + aprobador + ", modulo: " + modulo);
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("aprobar_requisicion");
        proc.setParameter("p_requisicion", requisicion);
        proc.setParameter("p_partida", linea);
        proc.setParameter("p_aprobador", aprobador);
        proc.setParameter("p_modulo", modulo);
        proc.setParameter("p_id_cuenta", idCuenta);
        proc.setParameter("p_log", "Y");

        Integer response = executeProcedureWithResult(proc, "p_result", "aprobarRequisicion");
        log.debug("[FIN] aprobarRequisicion() -> requisicion: " + requisicion + ", linea: " + linea + ", aprobador: " + aprobador + ", modulo: " + modulo
                + ". Response: " + response);
        return response;
    }
    
    @Override
    public Integer iniAprobarRequisicion(Long requisicion, Long linea, String aprobador, String modulo, Integer idCuenta) {
        log.debug("[INICIO] iniAprobarRequisicion() -> requisicion: " + requisicion + ", linea: " + linea + ", aprobador: " + aprobador + ", modulo: " + modulo);
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("ini_aprobar_requisicion");
        proc.setParameter("p_requisicion", requisicion);
        proc.setParameter("p_partida", linea);
        proc.setParameter("p_aprobador", aprobador);
        proc.setParameter("p_id_cuenta", idCuenta);
        proc.setParameter("p_modulo", modulo);

        Integer response = executeProcedureWithResult(proc, "p_result", "iniAprobarRequisicion");
        log.debug("[FIN] aprobarRequisicion() -> requisicion: " + requisicion + ", linea: " + linea + ", aprobador: " + aprobador + ", modulo: " + modulo
                + ". Response: " + response);
        return response;
    }

    @Override
    public Integer aprobarRequisicionCritica(Long requisicion, Long linea, String aprobador, String solicitante, String razon, String modulo) {//<R17424>
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("aprobar_requisicion_critica");
        proc.setParameter("p_requisicion", requisicion);
        proc.setParameter("p_partida", linea);
        proc.setParameter("p_aprobador", aprobador);
        proc.setParameter("p_solicitante", solicitante);
        proc.setParameter("p_razon", razon);
        proc.setParameter("p_modulo", modulo);

        return executeProcedureWithResult(proc, "p_result", "aprobarRequisicionCritica");
    }

    private Integer executeProcedureWithResult(StoredProcedureQuery proc, String resultName, String callerMethod) {
        try {
            proc.execute();
            return (Integer) proc.getOutputParameterValue(resultName);
        } catch (Exception e) {
            throw new RuntimeException("Error ejecutando procedimiento: " + callerMethod, e);
        }
    }

    public List<Uens> getUensSuperAprobador(String idUsuario) {
        System.out.println("- - - - - -  - - - - - getUensSuperAprobador{idUsuario=> " + idUsuario + "} - - - - - -  - -- -  - - -");
        List<Uens> uens = this.em.createNamedQuery("getUensSuperAprobador", Uens.class)
                .setParameter("id_usuario", idUsuario)
                .getResultList();
        return uens;
    }

    public List<Integer> getUensPreAprobador(String idUsuario) {
        System.out.println("- - - - - -  - - - - - getUensSuperAprobador{idUsuario=> " + idUsuario + "} - - - - - -  - -- -  - - -");
        List<Integer> uens = this.em.createNamedQuery("getUensPreAprobador")
                .setParameter("id_usuario", idUsuario)
                .getResultList();
        return uens;
    }

    public Integer editarRequisicionPreAprobacion(RedicionRequisicionVO partida) {
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("ReqPorAprobar.updatePreaprobacion");
        proc.setParameter("p_idRequisicion", partida.getIdRequisicion());
        proc.setParameter("p_idProyecto", partida.getIdProyecto());
        proc.setParameter("p_idTarea", partida.getIdTarea());
        proc.setParameter("p_tipoGasto", partida.getTipoGasto());
        proc.setParameter("p_idCuenta", partida.getIdCuenta());
        proc.setParameter("p_siguienteAprobador", partida.getSiguienteAprobador());
        proc.setParameter("p_codProyecto", partida.getCodProyecto());
        proc.setParameter("p_codTarea", partida.getCodTarea());
        proc.setParameter("p_segmento3", partida.getSegmento3());
        proc.setParameter("p_segmento5", partida.getSegmento5());
        return executeProcedureWithResult(proc, "p_result", "editarRequisicionPreAprobacion");
    }

    //<ERM#17422>
    @Override
    public Integer preAprobarRequisciones(String stringRequis, String modulo, String aprobador) {
        System.out.println("- - - - - - - - - - - - preAprobarRequisciones {stringRequis=>" + stringRequis + "}- - - - - - - - - - - - - - - - - -");
        try {
            StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("preaprobarRequisiciones");
            proc.setParameter("P_REQUISICIONES", stringRequis);
            proc.setParameter("P_APROBADOR", aprobador);
            proc.setParameter("P_MODULO", modulo);
            proc.execute();
            return (Integer) proc.getOutputParameterValue("P_OUT_ESTATUS");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            System.out.println(e);
            return 0;
        }
    }
    //</ERM#17422>

    @Override
    public Integer verificarPresupuesto(Long requisicion, Long linea, String aprobador, Integer idCuenta) {
        log.debug("[INICIO] verificarPresupuesto() -> requisicion: " + requisicion + ", linea: " + linea + ", aprobador: " + aprobador);
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("verifyBudget");
        proc.setParameter("p_requisicion", requisicion);
        proc.setParameter("p_partida", linea);
        proc.setParameter("p_aprobador", aprobador);
        proc.setParameter("p_cuenta", idCuenta);

        try {
            proc.execute();
            log.debug("[FIN] verificarPresupuesto() -> requisicion: " + requisicion + ", linea: " + linea + ", aprobador: " + aprobador);
            return (Integer) proc.getOutputParameterValue("p_result");
        } catch (Exception e) {
            throw new RuntimeException("Error ejecutando procedimiento: verificarPresupuesto", e);
        }
    }

    @Override
    public void bloquearDetalle(Long requisicion, Long linea, Integer idCuenta, String estado) {
        log.debug("[INICIO] bloquearDetalle() -> requisicion: " + requisicion + ", linea: " + linea + ", estado: " + estado);
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("blockRequisitionDetail");
        proc.setParameter("p_requisicion", requisicion);
        proc.setParameter("p_partida", linea);
        proc.setParameter("p_estado", estado);
        proc.setParameter("p_id_cuenta", idCuenta);

        try {
            proc.execute();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            throw new RuntimeException("Error ejecutando procedimiento: bloquearDetalle", e);
        }
        log.debug("[FIN] bloquearDetalle() -> requisicion: " + requisicion + ", linea: " + linea + ", estado: " + estado);
    }

    @Override
    public Long countRequisiciones(ReqPorAprobarRepository.APPROVAL_TYPE tipo, Map<String, Object> params) {
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("countRequisiciones");
        proc.setParameter("p_type", tipo.ordinal());
        proc.setParameter("p_aprobador", params.get("p_aprobador"));
        proc.setParameter("p_uen", params.get("p_uen"));
        proc.setParameter("p_id_uens", params.get("p_id_uens"));

        try {
            proc.execute();
            return Long.valueOf((Integer) proc.getOutputParameterValue("p_counter"));
        } catch (Exception e) {
            throw new RuntimeException("Error ejecutando procedimiento: countRequisiciones", e);
        }
    }

    @Override
    public Integer updateExceso(Long requisicion, Long partida, String razonExceso, String solucionExceso) {
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("updateRazonExceso");
        proc.setParameter("p_requisicion", requisicion);
        proc.setParameter("p_partida", partida);
        proc.setParameter("p_razon", razonExceso);
        proc.setParameter("p_solucion", solucionExceso);

        try {
            proc.execute();
            return (Integer) proc.getOutputParameterValue("p_result");
        } catch (Exception e) {
            throw new RuntimeException("Error ejecutando procedimiento: updateRazonExceso", e);
        }
    }

    @Override
    public Integer guardaComentarioFad(Integer idFad, String aprobacionEspecial, String comentario) {
        System.out.println("- - - - - - - - - - - - guardaComentarioFad {idFad=>" + idFad + "}- - - - - - - - - - - - - - - - - -");
        try {
            StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("guardaComentarioFad");
            proc.setParameter("P_ID_FAD", idFad);
            proc.setParameter("P_APROBACION_ESPECIAL", aprobacionEspecial);
            proc.setParameter("P_COMENTARIO", comentario);
            proc.execute();
            return (Integer) proc.getOutputParameterValue("VALUE_OUT");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            System.out.println(e);
            return 0;
        }
    }

    @Override
    public List<String> getSiguienteAprobador(Long idRequisicion, Long idPartida, String aprobador) {
        System.out.println("- - - - - - - - - - - - getSiguienteAprobador {idRequisicion=>" + idRequisicion + "}- - - - - - - - - - - - - - - - - -");
        try {
            StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("getSiguienteAprobador");
            proc.setParameter("P_ID_REQUISICION", idRequisicion);
            proc.setParameter("P_ID_PARTIDA", idPartida);
            proc.setParameter("P_APROBADOR", aprobador);
            proc.execute();
            List<String> out = new ArrayList();
            out.add(proc.getOutputParameterValue("OUT_APPROVER") != null ? proc.getOutputParameterValue("OUT_APPROVER").toString() : null);
            out.add(proc.getOutputParameterValue("OUT_SPECIAL_APPROVAL") != null ? proc.getOutputParameterValue("OUT_SPECIAL_APPROVAL").toString() : null);
            return out;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            System.out.println(e);
            return null;
        }
    }

    @Override
    public String guardaMailAprobacion(String mailBody, String destino, String asunto) {
        log.debug("Notificacion para:" + destino);

        //<T395493>
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("guardaMailAprobacion");
        proc.setParameter("P_DESTINO", destino);
        proc.setParameter("P_DESTINO_CC", "");
        proc.setParameter("P_ASUNTO", asunto);
        proc.setParameter("P_MAIL_CUERPO", mailBody);

        try {
            proc.execute();
            return (String) proc.getOutputParameterValue("P_MESSAGE_OUT");
        } catch (Exception e) {
            throw new RuntimeException("Error ejecutando procedimiento: guardaMailAprobacion", e);
        }
        //</T395493>
    }

    @Override
    public Integer verifyBudgetProyecto(Long requisicion, Long linea, String aprobador) {
        //<T420188>
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("ReqPorAprobar.verifyBudgetProyecto");
        proc.setParameter("p_requisicion", requisicion);
        proc.setParameter("p_partida", linea);
        proc.setParameter("p_aprobador", aprobador);
        try {
            proc.execute();
            return (Integer) proc.getOutputParameterValue("p_result");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            throw new RuntimeException("Error ejecutando procedimiento: bloquearDetalle", e);
        }
    }//</T420188>
    
    @Override
    public List<CheckBudgetRequest> getBudgetRequiProyecto(String sWhere) {

        List<CheckBudgetRequest> BudgetRequiProyectoList = new ArrayList<>();

        List<Object[]> outObject = this.em.createNativeQuery("SELECT DISTINCT\n"
                + "       D.ID_REQUISICION,\n"
                + "       D.ID_PARTIDA,\n"
                + "       D.ID_UEN,\n"
                + "       D.ID_PROYECTO,\n"
                + "       D.ID_TAREA,\n"
                + "       D.TIPO_GASTO,\n"
                + "       (  GET_MONTO_CON_GASTOS_REQ (D.ID_REQUISICION, D.ID_PARTIDA)\n"
                + "        * ICOM_DCOMPRAS_PKG.FN_GET_TIPO_CAMBIO_BY_UEN (D.ID_MONEDA, D.ID_UEN))\n"
                + "          MONTO_EXTENDIDO,\n"
                + "        nvc_pkg_spx_approvals.GET_CONTROL_LEVEL_PRESUP_PROY\n"
                + "        (\n"
                + "        D.ID_UEN,\n"
                + "      D.ID_PROYECTO,\n"
                + "      D.ID_TAREA,\n"
                + "      D.TIPO_GASTO,\n"
                + "      (  GET_MONTO_CON_GASTOS_REQ (D.ID_REQUISICION, D.ID_PARTIDA)\n"
                + "        * ICOM_DCOMPRAS_PKG.FN_GET_TIPO_CAMBIO_BY_UEN (D.ID_MONEDA, D.ID_UEN))\n"
                + "        ) CONTROL_LEVEL,\n"
                + "        D.ID_CUENTA\n"
                + "  FROM DETALLE_DE_REQUISICION D\n"
                + "WHERE (D.ID_PROYECTO is not null and NVL (D.ID_PROYECTO, 0) <> 0 ) and (" + sWhere + ")")
                .getResultList();

        for (Object[] objects : outObject) 
        {
            CheckBudgetRequest CheckBudget= new CheckBudgetRequest();
            CheckBudget.setRequisicion(((BigDecimal)(objects[0])).longValue());
            CheckBudget.setLinea(((BigDecimal)(objects[1])).longValue());
            CheckBudget.setIdUen(((BigDecimal)(objects[2])).longValue());
            CheckBudget.setProyecto(((BigDecimal)(objects[3])).longValue());
            CheckBudget.setTarea(((BigDecimal)(objects[4])).longValue());
            CheckBudget.setTipoGasto((String) objects[5]);
            CheckBudget.setMonto(((BigDecimal)(objects[6])).doubleValue());
            CheckBudget.setControlLevel((String) objects[7]);
            CheckBudget.setCuenta(((BigDecimal)(objects[8])).longValue());
            BudgetRequiProyectoList.add(CheckBudget);
        }
        return BudgetRequiProyectoList;
    }
    @Override
    public Integer verifyBudgetProyecto(Long idUen,Long idProyecto, Double monto, Long idCuenta) {
        //<T428940>
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("ReqPorAprobar.verifyBudgetProyectoCheck");
        try {
        proc.setParameter("P_ID_UEN", idUen);
        proc.setParameter("P_ID_PROYECTO", idProyecto);
        proc.setParameter("P_MONTO_MXP", monto);
        proc.setParameter("P_ID_CUENTA", idCuenta);
        
            proc.execute();
            return (Integer) proc.getOutputParameterValue("P_RETURN");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            throw new RuntimeException("Error ejecutando procedimiento: bloquearDetalle", e);
        }
    }//</T428940>
    
    @Override
    public Integer validaPresupProySpx(Long idUen,Long idProyecto, Long idTarea, String TipoGasto,Double monto) {
        //<T428940>
        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("ReqPorAprobar.validaPresupProySpx");
        try {
            proc.setParameter("P_ID_UEN", idUen);
            proc.setParameter("P_ID_PROYECTO", idProyecto);
            proc.setParameter("P_ID_TAREA", idTarea);
            proc.setParameter("P_TIPO_GASTO", TipoGasto);
            proc.setParameter("P_MONTO_MXP", monto);
            proc.setParameter("P_ACUMULAR", "N");
            proc.setParameter("P_ID_VAL", 0L);
            proc.setParameter("P_REFERENC", "");
            proc.execute();
            return (Integer) proc.getOutputParameterValue("P_RETURN");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            throw new RuntimeException("Error ejecutando procedimiento: bloquearDetalle", e);
        }
    }//</T428940>
    
    @Override
    public NvcTblProvSitesH getProveedorSite(Long requisicion) {
        NvcTblProvSitesH site = null;
        try {
            site = (NvcTblProvSitesH) em.createNativeQuery("SELECT p.* from NVC_TBL_PROV_SITES_H p\n"
                    + "INNER JOIN REQUISICION r ON p.ID_PROVEEDOR=r.ID_PROVEEDOR AND p.ID_SUCURSAL_PROVEEDOR=r.ID_SUCURSAL_PROVEEDOR\n"
                    + "WHERE r.id_requisicion = ?1", NvcTblProvSitesH.class)
                    .setParameter(1, requisicion)
                    .getSingleResult();

        } catch (Exception e) {
            log.debug(e.getMessage());
            e.printStackTrace();
        }
        return site;
    }
<<<<<<< HEAD
    
    //<RELEASE_BRASIL>
    @Override
    public Integer actualizarPreaprobacionCC(PreaprobacionCC requisicion) {

        log.debug(requisicion.toString());
        
        Integer result = 0;
        StoredProcedureQuery proc = em.createStoredProcedureQuery("NVC_PKG_APROBACION_SPX.UPDATE_PREAPROBACION_CC");
        proc.registerStoredProcedureParameter("p_id_requisicion", Integer.class, ParameterMode.IN);
        proc.registerStoredProcedureParameter("p_id_partida", Integer.class, ParameterMode.IN);
        proc.registerStoredProcedureParameter("p_id_familia", Integer.class, ParameterMode.IN);
        proc.registerStoredProcedureParameter("p_id_cuenta", Integer.class, ParameterMode.IN);
        proc.registerStoredProcedureParameter("p_usuario", String.class, ParameterMode.IN);
        proc.registerStoredProcedureParameter("p_result", Integer.class, ParameterMode.OUT);

        proc.setParameter("p_id_requisicion", requisicion.getIdRequisicion());
        proc.setParameter("p_id_partida", requisicion.getIdPartida());
        proc.setParameter("p_id_familia", requisicion.getIdFamilia());
        proc.setParameter("p_id_cuenta", requisicion.getIdCuenta());
        proc.setParameter("p_usuario", requisicion.getUsuario());

        try {
            proc.execute();
            result = (Integer) proc.getOutputParameterValue("p_result");
        } catch (Exception e) {
            log.debug(e.getLocalizedMessage(),e);
        }
        return result;
    }
    //</RELEASE_BRASIL>
=======
>>>>>>> mexico
}
