package com.metalsa.contralor.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.metalsa.contralor.model.CentroCostoProceso;
import com.metalsa.contralor.model.CentroCostoProcesoDetalle;
import com.metalsa.contralor.model.CentroCostoProcesoUpdate;
import com.metalsa.contralor.model.ContralorResponse;
import com.metalsa.contralor.model.Procesos;
import com.metalsa.contralor.model.ProcesosUpdate;
import com.metalsa.contralor.model.ProcessUen;
import com.metalsa.contralor.model.ProcessUenCC;
import com.metalsa.contralor.model.RequestBodyContralor;
import com.metalsa.contralor.model.ResponsableProceso;
import com.metalsa.utils.Constants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import lombok.extern.log4j.Log4j;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Log4j
public class ProcesoRepositoryImpl implements ProcesosProcedure {

    @PersistenceContext
    private EntityManager em;

    private StoredProcedureQuery executeMainProcedure(RequestBodyContralor request, String procedure) throws Exception {
        em.clear();
        StoredProcedureQuery store = em.createNamedStoredProcedureQuery(procedure);
        store.setParameter("p_id_uen", request.getUen());
        store.setParameter("p_id_ccs", request.getCcs());
        store.setParameter("p_id_process", request.getProcess());
        store.setParameter("p_id_resp_ccs", request.getResponsiblescc());
        store.setParameter("p_id_resp_grs", request.getResponsiblesgroup());
        store.setParameter("p_id_dels", request.getDelegates());
        store.setParameter("p_witout_resp", request.isWithoutresponsible() + "");
        store.setParameter("p_last_update_i", request.getFistUpdate());
        store.setParameter("p_last_update_e", request.getLastUpdate());
        store.setParameter("p_id_idioma", request.getLang());
        return store;
    }

    @Override
    public Long findCostsCenterProcessCount(RequestBodyContralor request) {
        Long total;
        try {
            StoredProcedureQuery store = this.executeMainProcedure(request, "CentroCostoProceso.findCostsCenterCount");
            store.execute();
            total = (Long) store.getOutputParameterValue("p_out_result");
        } catch (Exception e) {
            log.debug(e.getLocalizedMessage(), e);
            total = 0L;
        }
        return total;
    }

    @Override
    public List<CentroCostoProceso> findCostsCenterProcess(RequestBodyContralor request) {
        List<CentroCostoProceso> result;
        try {

            StoredProcedureQuery store = this.executeMainProcedure(request, "CentroCostoProceso.findCostsCenter");
            store.setParameter("p_page_start", request.getPagestart());
            store.setParameter("p_page_end", request.getPageend());
            store.execute();
            result = store.getResultList();
        } catch (Exception e) {
            log.debug(e.getLocalizedMessage(), e);
            result = new ArrayList<>();
        }
        return result;
    }

    @Override
    public List<CentroCostoProcesoDetalle> findDetailCC_UEN(Integer uen) {
//        log.debug("findDetailCC{uen:" + uen + ",cc:" + cc + "}");
        StoredProcedureQuery store = em.createNamedStoredProcedureQuery("CentroCostoProcesoDetalle.findDetailCC_UEN");
        store.setParameter("p_id_uen", uen);
        store.execute();
        List<CentroCostoProcesoDetalle> result = store.getResultList();
        return result;
    }

    @Override
    public List<CentroCostoProcesoDetalle> findDetailCC(Integer uen, Long cc) {
//        log.debug("findDetailCC{uen:" + uen + ",cc:" + cc + "}");
        em.clear();
        StoredProcedureQuery store = em.createNamedStoredProcedureQuery("CentroCostoProcesoDetalle.findDetailCC");
        store.setParameter("p_id_uen", uen);
        store.setParameter("p_id_cc", cc);
        store.execute();
        List<CentroCostoProcesoDetalle> result = store.getResultList();
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ContralorResponse updateUsersCCS(CentroCostoProcesoUpdate ccs) {

        ContralorResponse response = new ContralorResponse(null, Constants.RESPONSE.SUCCESS.name());

        if (ccs == null) {
            log.debug("-----------ccs null-------------");
            return response;
        }
        if (ccs.getLines() == null) {
            log.debug("---------ccs lines null--------");
            return response;
        }

        try {

            List<String> inserts = new ArrayList<>();
            List<String> deletes = new ArrayList<>();
            List<String> updates = new ArrayList<>();

            for (CentroCostoProceso cc : ccs.getLines()) {

                //se compara que tipoRelacion sea diferente de 'Resp' ya que esta se encuentra a nivel cc
                //y solo se puede tener un ResponsableProceso del cc y tambien todos los 'Resp'
                //deben tener el nivel 'SPX_ILIMITADO', por ahora se supone que tipo de relacion solo es 
                //'Del' pero de igual manera se agrupa por tipoRelacion por si en algun momento agregan otros tipos
                //<R39943>
                //lines_to_delete
                if (cc.getLinesToDelete() != null) {

                    Map<String, Map<String, String>> groups
                            = cc.getLinesToDelete().stream()
                                    .filter((e) -> !e.getTipoDeRelacion().equalsIgnoreCase("Resp"))
                                    .collect(Collectors.groupingBy(
                                            CentroCostoProcesoDetalle::getRelacion,
                                            Collectors.groupingBy(
                                                    CentroCostoProcesoDetalle::getNivelAprobacion,
                                                    Collectors.mapping(CentroCostoProcesoDetalle::getIdUsuario, Collectors.joining(","))
                                            )
                                    ));

                    groups.forEach((relacion, relaciones) -> {
                        relaciones.forEach((nivel, ids) -> {
                            //<R39943>
                            deletes.add(this.updateUsersCCS(ccs.getUen(), null, cc.getIdCC(), ids, relacion, nivel, ccs.getUser(), Constants.ACTIONS.DELETE.name()));
                        });
                    });
                }

                //lines_to_update
                if (cc.getLineas() != null) {

                    Map<String, Map<String, String>> groups
                            = cc.getLineas().stream()
                                    .filter((e) -> !e.getTipoDeRelacion().equalsIgnoreCase("Resp"))
                                    .collect(Collectors.groupingBy(
                                            CentroCostoProcesoDetalle::getRelacion,
                                            Collectors.groupingBy(
                                                    CentroCostoProcesoDetalle::getNivelAprobacion,
                                                    Collectors.mapping(CentroCostoProcesoDetalle::getIdUsuario, Collectors.joining(","))
                                            )
                                    ));

                    groups.forEach((relacion, relaciones) -> {
                        relaciones.forEach((nivel, ids) -> {
                            //<R39943>
                            updates.add(this.updateUsersCCS(ccs.getUen(), null, cc.getIdCC(), ids, relacion, nivel, ccs.getUser(), Constants.ACTIONS.UPDATE.name()));
                        });
                    });
                }

                //lines_to_add
                if (cc.getLineasToAdd() != null) {

                    Map<String, Map<String, String>> groups
                            = cc.getLineasToAdd().stream()
                                    .filter((e) -> !e.getTipoDeRelacion().equalsIgnoreCase("Resp"))
                                    .collect(Collectors.groupingBy(
                                            CentroCostoProcesoDetalle::getRelacion,
                                            Collectors.groupingBy(
                                                    CentroCostoProcesoDetalle::getNivelAprobacion,
                                                    Collectors.mapping(CentroCostoProcesoDetalle::getIdUsuario, Collectors.joining(","))
                                            )
                                    ));

                    groups.forEach((relacion, relaciones) -> {
                        relaciones.forEach((nivel, ids) -> {
                            //<R39943>
                            inserts.add(this.updateUsersCCS(ccs.getUen(), null, cc.getIdCC(), ids, relacion, nivel, ccs.getUser(), Constants.ACTIONS.INSERT.name()));
                        });
                    });
                }
                //<R39943>
                updates.add(this.updateUsersCCS(ccs.getUen(), null, cc.getIdCC(), cc.getIdResponsableCC(), "Resp", "SPX_ILIMITADO", ccs.getUser(), Constants.ACTIONS.UPDATE.name()));
            }

            Optional<String> error = inserts.stream().filter(insert -> !insert.equals(Constants.RESPONSE.SUCCESS.name())).findFirst();
            if (error.isPresent()) {
                throw new Exception(error.get());
            }

            error = deletes.stream().filter(delete -> !delete.equals(Constants.RESPONSE.SUCCESS.name())).findFirst();
            if (error.isPresent()) {
                throw new Exception(error.get());
            }

            error = updates.stream().filter(update -> !update.equals(Constants.RESPONSE.SUCCESS.name())).findFirst();
            if (error.isPresent()) {
                throw new Exception(error.get());
            }

            //R39943    
            //agrupar por procesos para poder obtener solo un responsable de proceso
            ccs.getLines()
                    .stream()
                    .filter(cc -> cc.getIdProcesoUen() != null)
                    .collect(Collectors.groupingBy(CentroCostoProceso::getIdProcesoUen))
                    .entrySet()
                    .forEach(proceso -> {

                        //actualizar el responsable_proceso
                        CentroCostoProceso process = proceso.getValue().get(0);
                        log.debug("proceso:" + proceso.getKey() + " : " + process.getNombreProceso() + "- " + process.getIdResponsableProceso());
                        updates.add(this.updateUsersCCS(ccs.getUen(), proceso.getKey(), null, process.getIdResponsableProceso(), "resp_proceso", "SPX_ILIMITADO", ccs.getUser(), Constants.ACTIONS.UPDATE.name()));

                        //agrupar por subprocesos
                        proceso
                                .getValue()
                                .stream()
                                .filter(cc -> cc.getIdSubProcesoUen() != null)
                                .collect(Collectors.groupingBy(CentroCostoProceso::getIdSubProcesoUen))
                                .entrySet()
                                .forEach(subproceso -> {

                                    //actualizar el responsable del subproceso
                                    CentroCostoProceso subprocess = subproceso.getValue().get(0);
                                    log.debug("\tsubprocess:" + subproceso.getKey() + " :" + subprocess.getNombreProceso() + "- " + subprocess.getIdResponsableSubProceso());
                                    updates.add(this.updateUsersCCS(ccs.getUen(), subproceso.getKey(), null, subprocess.getIdResponsableSubProceso(), "resp_grupo", "SPX_ILIMITADO", ccs.getUser(), Constants.ACTIONS.UPDATE.name()));
                                });
                    });
        } catch (Exception e) {
            response.setStatus(Constants.RESPONSE.ERROR.name());
            response.setMessage(e.getMessage());
            log.debug(e.getMessage(), e);
        }
        return response;
        //</R39943>

    }

    //<R39943>
    @Transactional(propagation = Propagation.REQUIRED)
    public String updateUsersCCS(Integer uen, Long procesoUen, Long cc, String users, String relacion, String nivel, String user, String action) {

        log.debug("updateUsersCCS{\n"
                + "\tuen:" + uen
                + ",\n\tproceso_uen:" + procesoUen//<R39943>
                + ",\n\tcc:" + cc
                + ",\n\tusers:" + users
                + ",\n\trelacion:" + relacion
                + ",\n\tnivel:" + nivel
                + ",\n\tuser:" + user
                + ",\n\taction:" + action
                + "\n}"
        );

        StoredProcedureQuery store = em.createNamedStoredProcedureQuery("CentroCostoProceso.updateCCPorUsuario");
        store.setParameter("p_id_cc", cc);
        store.setParameter("p_id_proceso_uen", procesoUen);//<R39943>
        store.setParameter("p_id_uen", uen);
        store.setParameter("p_nivel", nivel);
        store.setParameter("p_relacion", relacion);
        store.setParameter("p_id_usuario", user);
        store.setParameter("p_id_usuarios", users);
        store.setParameter("p_tipo_accion", action);
        store.execute();
        String response = (String) store.getOutputParameterValue("p_out_result");
        log.debug("\t\t response:" + response);
        return response;

    }

    //<R39943>
    @Override
    public List<Procesos> findAllProcess() {
        List<Procesos> result;
        try {
            StoredProcedureQuery store = em.createNamedStoredProcedureQuery("Procesos.findAllProcessLang");
            store.execute();
            result = store.getResultList();
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
            result = new ArrayList();
        }
        return result;
    }
    //<R39943>

    @Override
    public List<Procesos> findAllProcessUen(Integer uen, Integer parent) {
//        log.debug("findAllProcessUen{uen:" + uen + " ,parent:" + parent + "}");

        List<Procesos> result;
        try {
            StoredProcedureQuery store = em.createNamedStoredProcedureQuery("Procesos.findAllProcessUen");
            store.setParameter("p_id_uen", uen);
            store.setParameter("p_id_process_parent", parent);
            store.execute();
            result = store.getResultList();
//            log.debug("is null result:"+(result == null));
            if (result != null) {
                for (Procesos proceso : result) {
                    proceso.setSubprocesos(this.findAllProcessUen(uen, proceso.getIdProcesoUen()));
                }
            }
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
            result = new ArrayList();
        }
        return result;
    }

    @Override
    public List<Procesos> findAllProcessUenLang(Integer uen, Integer level) {
        List<Procesos> result;
        try {
            StoredProcedureQuery store = em.createNamedStoredProcedureQuery("Procesos.findAllProcessUenLangLevel");
            store.setParameter("p_id_uen", uen);
            store.setParameter("p_level", level);
            store.execute();
            result = store.getResultList();
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
            result = new ArrayList();
        }
        return result;

    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ContralorResponse UpdateProcess(ProcesosUpdate request) {
        ContralorResponse response = new ContralorResponse(null, Constants.RESPONSE.SUCCESS.getMSG());
        try {

            String status;
                if (request.getLinesToRemove() != null) {
                    for (Procesos process : request.getLinesToRemove()) {
                        status = this.updateProcess(request.getUen(), request.getUser(), process, Constants.ACTIONS.DELETE.name());
                        if (false == this.isSucess(status)) {
                            throw new Throwable(status);
                        }
                    }
                }

                if (request.getLinesToAdd() != null) {
                    for (Procesos process : request.getLinesToAdd()) {
                        status = this.updateProcess(request.getUen(), request.getUser(), process, Constants.ACTIONS.INSERT.name());
                        if (false == this.isSucess(status)) {
                            throw new Throwable(status);
                        }
                    }
                }

                if (request.getLinesToUpdate() != null) {
                    for (Procesos process : request.getLinesToUpdate()) {
                        status = this.updateProcess(request.getUen(), request.getUser(), process, Constants.ACTIONS.UPDATE.name());
                        if (false == this.isSucess(status)) {
                            throw new Throwable(status);
                        }
                    }
                }

        } catch (Throwable e) {
            log.debug(e.getMessage(), e);
            response.setStatus(Constants.RESPONSE.ERROR.getMSG());
            try {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode node = mapper.readTree(e.getMessage());
                response.setStatusCode(node.get("SQLCODE").asText());
                response.setMessage(node.get("SQLERRM").asText());
            } catch (IOException ioe) {
                response.setStatusCode("unknown");
                response.setMessage(e.getMessage());
            }
        }
        return response;
    }

    @Transactional(rollbackFor = Throwable.class)
    private String updateProcess(Integer uen, String user, Procesos process, String action) throws Throwable {
        log.debug("updateProcess{action:" + action + ",uen:" + uen + ",user:" + user + ",idProcess:" + process.getIdProceso() + ",process:" + process.getNombreProceso() + "}");
        StoredProcedureQuery store = em.createNamedStoredProcedureQuery("Procesos.addProcess");
        store.setParameter("p_id_process", process.getIdProceso());
        store.setParameter("p_id_proceso_uen", process.getIdProcesoUen());
        store.setParameter("p_id_process_uen_parent", process.getIdProcesoUenPadre());
        store.setParameter("p_id_responsible", process.getIdResponsable());
        store.setParameter("p_name", process.getNombreProceso());
        store.setParameter("p_name_esa", process.getNombreProcesoEsa());
        store.setParameter("p_name_us", process.getNombreProcesoUs());
        store.setParameter("p_name_ptb", process.getNombreProcesoPtb());
        store.setParameter("p_id_uen", uen);
        store.setParameter("p_id_user", user);
        store.setParameter("p_action", action);
        store.execute();
        String response = (String) store.getOutputParameterValue("p_out_result");
        log.debug("response:" + response);
        return response;
    }

// APOMR10051 Comentado por que no se usa y pmd no deja desplegar jenkins, descomentar cuando se vaya a usar
//    private String removeProcess(Integer uen, String user, String processids) throws Exception {
//        log.debug("removeProcess{uen:" + uen + ",user:" + user + ",processids:" + processids + "}");
//        StoredProcedureQuery store = em.createNamedStoredProcedureQuery("Procesos.removeProcess");
//        store.setParameter("p_process_ids", processids);
//        store.setParameter("p_id_uen", uen);
//        store.setParameter("p_id_user", user);
//        store.execute();
//        String response = (String) store.getOutputParameterValue("p_out_result");
//        log.debug("response:" + response);
//        return response;
//    }
    private boolean isSucess(String status) {
        return Constants.RESPONSE.SUCCESS.getMSG().equals(status)
                || Constants.RESPONSE.NOT_EXECUTED.getMSG().equals(status);
    }

    @Override
    public ContralorResponse addUpdateProcessUenCC(ProcesosUpdate procesosUpdate) {
        ContralorResponse response;
        try {
            response = this.addUpdateProcessUenCC2(procesosUpdate);
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
            response = new ContralorResponse(e.getMessage(), Constants.RESPONSE.ERROR.getMSG());
        }
        return response;
    }

    @Transactional
    public ContralorResponse addUpdateProcessUenCC2(ProcesosUpdate procesosUpdate) throws Exception {
        ContralorResponse response = new ContralorResponse(null, Constants.RESPONSE.SUCCESS.getMSG());
        log.debug("addUpdateProcessUenCC{uen:" + procesosUpdate.getUen() + ",user:" + procesosUpdate.getUser() + "}");

        if (procesosUpdate.getListProcessUen() != null) {
            for (ProcessUen processUen : procesosUpdate.getListProcessUen()) {
                StoredProcedureQuery store = em.createNamedStoredProcedureQuery("ProcessUenCC.addUpdateProcessUenCc");
                store.setParameter("p_id_uen", procesosUpdate.getUen());
                store.setParameter("p_id_user", procesosUpdate.getUser());
                store.setParameter("p_id_process_uen", processUen.getIdProcessUen());
                store.setParameter("p_id_ccs", processUen.getIdccs());
                store.execute();
                String status = (String) store.getOutputParameterValue("p_out_result");
                response.setMessage(status);
                if (false == this.isSucess(status)) {
                    throw new Exception(status);
                }
                response.setMessage(status);
                log.debug("\t{idProcessUen:" + processUen.getIdProcessUen() + ",ccs:" + processUen.getIdccs() + ",status:" + status + "}");
            }
        }
        return response;
    }

    @Override
    public List<ProcessUenCC> findAllProcessUenCC(Integer uen, String lang) {
        List<ProcessUenCC> result;
        try {
            StoredProcedureQuery store = em.createNamedStoredProcedureQuery("ProcessUenCC.findAllProcessUenCC");
            store.setParameter("p_id_uen", uen);
            store.setParameter("p_idioma", lang);
            store.execute();
            result = store.getResultList();
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
            result = new ArrayList<>();
        }
        return result;
    }

    @Override
    public List<ResponsableProceso> getResponsiblesByLevel(Integer uen, Integer level) {
        List<ResponsableProceso> result;
        try {
            StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("ProcessUenCC.getResponsiblesByLevel");
            proc.setParameter("p_id_uen", uen);
            proc.setParameter("p_level", level);
            proc.execute();
            result = proc.getResultList();
        } catch (Exception e) {
            log.debug(e.getLocalizedMessage(), e);
            result = new ArrayList<>();
        }

        return result;
    }

    @Override
    public List<Procesos> findProcessByName(String nombreProceso) {
        List<Procesos> resultProceso = null;
        try {
            StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("Procesos.findProcessByName");
            proc.setParameter("p_process_name", nombreProceso);
            proc.execute();
            List<Procesos> result = proc.getResultList();
            if (result != null && !result.isEmpty()) {
                resultProceso = result;
            }
        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);
        }
        return resultProceso;
    }

}
