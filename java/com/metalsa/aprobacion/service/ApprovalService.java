package com.metalsa.aprobacion.service;

import com.google.common.collect.Lists;
import com.metalsa.aprobacion.model.*;
import com.metalsa.aprobacion.repository.*;
import com.metalsa.aprobacion.service.MailNotificationService.MailData;
import com.metalsa.core.model.NvcTblProvSitesH;
import com.metalsa.core.model.OaUens;
import com.metalsa.error.NotFoundException;
import com.metalsa.utils.Constants;
import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.mapping;
import org.apache.commons.collections4.map.HashedMap;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
@Service
@Log4j
public class ApprovalService {

    @Autowired
    private ReqPorAprobarRepository db;

    @Autowired
    private DetalleRequisicionRepository detalles;

    @Autowired
    private OaCombinacionRepository contables;

    @Autowired
    private ProjectBudgetRepository projectos;

    @Autowired
    private CategoryConfigurationRepository configCategorias;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private ApprovalValidation approval_validation;

    @Qualifier("application")
    @Autowired
    private MessageSource messages;

    @Transactional(timeout = 360)
    public List<ApprovalResponse> preaprobacion(ApprovalRequest items, String modulo) {
        return preaprobarReq(items,modulo);
    }
    
    @Transactional(timeout = 360)
    public List<ApprovalResponse> aprobacion(ApprovalRequest items, boolean isCritical, String modulo) {
        if (isCritical) {
            return aprobarCriticas(items, modulo);
        }
        return aprobar(items, modulo);
    }

    public CheckBudgetResponse verifyBudget(List<CheckBudgetRequest> items, String idioma) {
        items.forEach(log::debug);
        List<ApprovedItem> result = new ArrayList<>();
        List<Presupuesto> presupuestos = new ArrayList<>();

        verifyCcBudget(items, idioma, result, presupuestos);
        verifyProjectBudget(items, result, presupuestos);
        verifyMultiCcBudget(items, idioma, result, presupuestos);//<MDA_MULTICC/>

        return new CheckBudgetResponse(result, presupuestos);
    }

    private void verifyProjectBudget(List<CheckBudgetRequest> items, List<ApprovedItem> result, List<Presupuesto> presupuestos) {
        log.debug(" **** verifyProjectBudget *** ");
        //<T428940>
        ArrayList<String> listProyectos = new ArrayList<>();
        String sWhereRequis = "";
        for (CheckBudgetRequest i : items) {
            sWhereRequis = sWhereRequis + " (ID_REQUISICION = " + i.getRequisicion() + " AND ID_PARTIDA = " + i.getLinea() + ") OR";
        }
        sWhereRequis = sWhereRequis.substring(0, sWhereRequis.length() - 2);
        List<CheckBudgetRequest> requisList = db.getBudgetRequiProyecto(sWhereRequis);
        for (CheckBudgetRequest i : requisList) {
            if (i.getControlLevel() != null && i.getControlLevel().equals("P")) {
                listProyectos.add(
                        i.getControlLevel() + "|"
                        + String.valueOf(i.getIdUen()) + "|"
                        + String.valueOf(i.getProyecto()) + "|"
                        + String.valueOf(i.getCuenta())
                );
            } else {
                listProyectos.add(
                        i.getControlLevel()+"|"
                        + String.valueOf(i.getIdUen()) + "|"
                        + String.valueOf(i.getProyecto()) + "|"
                        + String.valueOf(i.getTarea()) + "|"
                        + String.valueOf(i.getTipoGasto())
                );
            }
        }
        Set<String> hashSet = new HashSet<String>(listProyectos);
        listProyectos.clear();
        listProyectos.addAll(hashSet);

        for (String proyecto : listProyectos) {
            String[] proyectValues = proyecto.split("\\|");

            if (proyectValues[0].equals("P")) {
                Double monto = new Double(0);
                for (CheckBudgetRequest i : requisList) {
                    if (Objects.equals(i.getProyecto(), Long.valueOf(proyectValues[2])) && Objects.equals(i.getCuenta(), Long.valueOf(proyectValues[3]))) {
                        monto = monto + i.getMonto();
                    }
                }
                Integer p_result = db.verifyBudgetProyecto(Long.valueOf(proyectValues[1]), Long.valueOf(proyectValues[2]), monto, Long.valueOf(proyectValues[3]));
                log.debug(p_result);
                if (p_result == 1 || p_result == 100 || p_result == -200 || p_result == -300 || p_result == -400 || p_result == -500 || p_result == -600 || p_result == -700
                        || p_result == -1 || p_result == 1 || p_result == -2 || p_result == -3 || p_result == -4 || p_result == -5 || p_result == -6 || p_result == -7) {
                    for (CheckBudgetRequest i : items) {
                        if (Objects.equals(i.getProyecto(), Long.valueOf(proyectValues[2])) && Objects.equals(i.getCuenta(), Long.valueOf(proyectValues[3]))) {
                            if (i != null && i.isPreAprobacion()) {
                                result.add(new ApprovedItem(i.getCuenta(), true, false));
                            } else {
                                result.add(new ApprovedItem(i.getCuenta(), false, false));
                            }
                        }
                    }
                } else {
                    for (CheckBudgetRequest i : items) {
                        if (Objects.equals(i.getProyecto(), Long.valueOf(proyectValues[2])) && Objects.equals(i.getCuenta(), Long.valueOf(proyectValues[3]))) {
                            result.add(new ApprovedItem(i.getCuenta(), true, false));
                        }
                    }
                }
            } else {
                Double monto = new Double(0);
                for (CheckBudgetRequest i : requisList) {
                    if (Objects.equals(i.getProyecto(), Long.valueOf(proyectValues[2]))
                            && Objects.equals(i.getTarea(), Long.valueOf(proyectValues[3]))
                            && Objects.equals(i.getTipoGasto(), proyectValues[4])) {
                        monto = monto + i.getMonto();
                    }
                }
                monto = new BigDecimal(monto).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
                Integer p_result = db.validaPresupProySpx(Long.valueOf(proyectValues[1]), Long.valueOf(proyectValues[2]), Long.valueOf(proyectValues[3]), proyectValues[4], monto);

                log.debug(p_result);
                if (p_result == 1 || p_result == 100 || p_result == -200 || p_result == -300 || p_result == -400 || p_result == -500 || p_result == -600 || p_result == -700
                        || p_result == -1 || p_result == 1 || p_result == -2 || p_result == -3 || p_result == -4 || p_result == -5 || p_result == -6 || p_result == -7) {
                    for (CheckBudgetRequest i : items) {
                        if (Objects.equals(i.getProyecto(), Long.valueOf(proyectValues[2])) && Objects.equals(i.getTarea(), Long.valueOf(proyectValues[3]))) {
                            if (i != null && i.isPreAprobacion()) {
                                result.add(new ApprovedItem(i.getCuenta(), true, false));
                            } else {
                                result.add(new ApprovedItem(i.getCuenta(), false, false));
                            }
                        }
                    }
                } else {
                    for (CheckBudgetRequest i : items) {
                        if (Objects.equals(i.getProyecto(), Long.valueOf(proyectValues[2])) && Objects.equals(i.getTarea(), Long.valueOf(proyectValues[3]))) {
                            result.add(new ApprovedItem(i.getCuenta(), true, false));
                        }
                    }
                }
            }
        }
        //</T428940>
        /* for (CheckBudgetRequest i : items) {//<T420188>
            if (i.getProyecto() != null && !i.getProyecto().equals(0L)) {
                Integer p_result = db.verifyBudgetProyecto(i.getRequisicion(), i.getLinea(), i.getAprobador());
                log.debug(p_result);
                
                if (p_result == 1 || p_result == 100 || p_result == -200 || p_result == -300 || p_result == -400 || p_result == -500 || p_result == -600 || p_result == -700)
                {
                    result.add(new ApprovedItem(i.getCuenta(), false, false));
                }
                else
                {
                    result.add(new ApprovedItem(i.getCuenta(), true, false));
                }                
            }
        }//</T420188>*/

    }

    private boolean isProyecto(CheckBudgetRequest req) {
        return Constants.TIPO_CARGO.PROYECTO.getIdTipoCargo().equals(req.getIdTipoCargo());//<MDA_MULTICC/>
    }

    private boolean isCc(CheckBudgetRequest req) {
        return Constants.TIPO_CARGO.CENTRO_COSTO.getIdTipoCargo().equals(req.getIdTipoCargo());//<MDA_MULTICC/>
    }

    //<MDA_MULTICC>
    private boolean isMultiCc(CheckBudgetRequest req) {
        return Constants.TIPO_CARGO.MULTI_CC.getIdTipoCargo().equals(req.getIdTipoCargo());
    }
    //</MDA_MULTICC>

    private void verifyCcBudget(List<CheckBudgetRequest> items, String idioma, List<ApprovedItem> result, List<Presupuesto> presupuestos) {
        List<CategoryCentroCosto> presupuestosAux = new ArrayList<>(); //<RELEASE_ETOWN>
               
        Map<Long, Double> sumaMontos = items.stream()
                .filter(this::isCc)
                //.filter( (f) -> (f != null && !f.isPreAprobacion())) 
                .collect(Collectors.groupingBy(CheckBudgetRequest::getCuenta,
                        Collectors.summingDouble(i -> currencyService.getRateByPartida(i.getRequisicion(), i.getLinea()))));

        for (Map.Entry<Long, Double> entry : sumaMontos.entrySet()) {
            CategoryCentroCosto presupuesto = contables.findPresupuestoCcByCuentaAndIdioma(entry.getKey(), idioma);
            if (Objects.nonNull(presupuesto)) {
                //
                CheckBudgetRequest item = items.stream().filter(x -> x.getCuenta()!=null && x.getCuenta().equals(entry.getKey())).findAny().orElse(null);
                if (item != null && item.isPreAprobacion()) {
                    //ERM 66822 - Aprobacion marca falta de presuuesto pero si cuenta con dinero el CC
                    BigDecimal presupDispo = new BigDecimal(presupuesto.getDisponible()).setScale(4,RoundingMode.UP);
                    presupuesto.setActual(presupDispo.doubleValue());
                    //presupuesto.setActual(1D);
                    //entry.setValue(0D);
                } else {
                    BigDecimal presupDispo = new BigDecimal(presupuesto.getDisponible()).setScale(4,RoundingMode.UP);
                    BigDecimal entryValue = new BigDecimal(entry.getValue()).setScale(4,RoundingMode.UP);
                    presupuesto.setActual(presupDispo.doubleValue() - entryValue.doubleValue());
                }
                //

                if (presupuesto.getActual() < 0 && 63L != presupuesto.getCategoria() && item!=null && !item.isPreAprobacion()) {
                    CategoryConfiguration conf = configCategorias.findOne(
                            new CategoryConfiguration.Pk(presupuesto.getUen(), presupuesto.getCategoria()));
                    if (conf.isAprobacionExceso()) {
                        result.add(new ApprovedItem(entry.getKey(), false, true));
                    } else {
                        result.add(new ApprovedItem(entry.getKey(), false, false));
                    }
                } else {
                    result.add(new ApprovedItem(entry.getKey(), true, false));
                }
                presupuestos.add(presupuesto);
                presupuestosAux.add(presupuesto); //<RELEASE_ETOWN>
            } else {
                result.add(new ApprovedItem(entry.getKey(), false, false));
            }
        }
        
        //<RELEASE_ETOWN>
        int i;
        int j;
        int k;
        for(i=0; i < presupuestosAux.size(); i++){
            for(j=i+1; j < presupuestosAux.size(); j++){
                if(presupuestosAux.get(i).getUen().equals(presupuestosAux.get(j).getUen()) 
                        && presupuestosAux.get(i).getCodigoCC().equals(presupuestosAux.get(j).getCodigoCC())
                        && presupuestosAux.get(i).getCategoria().equals(presupuestosAux.get(j).getCategoria())
                        && !(presupuestosAux.get(i).getCuenta().equals(presupuestosAux.get(j).getCuenta())) ){
                   

                        if(0 == i){
                            Double presActual = presupuestosAux.get(i).getActual() - sumaMontos.get(presupuestosAux.get(j).getCuenta());
                            log.debug("i-j: " + i + "-" + j);
                            log.debug("presupuestos.get(i).getActual(): " + presupuestosAux.get(0).getActual());
                            log.debug("sumaMontos.get(presupuestosAux.get(j).getCuenta()): " + sumaMontos.get(presupuestosAux.get(j).getCuenta()));
                            log.debug("presActual: " + presActual);
                            for(k=0; k < presupuestos.size(); k++){
                                if(presupuestosAux.get(i).getUen().equals(presupuestosAux.get(k).getUen()) 
                                    && presupuestosAux.get(i).getCodigoCC().equals(presupuestosAux.get(k).getCodigoCC())
                                    && presupuestosAux.get(i).getCategoria().equals(presupuestosAux.get(k).getCategoria()) ){

                                    presupuestos.get(k).setActual(presActual); 
                                }

                            }
                        }
                        
                        if (presupuestos.get(i).getActual() < 0 && 63L != presupuestosAux.get(i).getCategoria()) {
                            CategoryConfiguration conf = configCategorias.findOne(
                                    new CategoryConfiguration.Pk(presupuestosAux.get(i).getUen(), presupuestosAux.get(i).getCategoria()));
                            if (conf.isAprobacionExceso()) {
                                result.get(i).setAprobado(false);
                                result.get(i).setExceso(true);
                                result.get(j).setAprobado(false);
                                result.get(j).setExceso(true);
                            } else {
                                result.get(i).setAprobado(false);
                                result.get(i).setExceso(false);
                                result.get(j).setAprobado(false);
                                result.get(j).setExceso(false);
                            }
                        } else {
                            result.get(i).setAprobado(true);
                            result.get(i).setExceso(false);
                            result.get(j).setAprobado(true);
                            result.get(j).setExceso(false);
                        }
                                              
                }
            
            }
        }
        //</RELEASE_ETOWN>
    }
    
    //<MDA_MULTICC>
    private Map<Long, Double> getMontosMultiCC(List<CheckBudgetRequest> items){
        Map<Long, Double> sumaMontos = new HashMap<>();
        items.stream().forEach(i->{
            Map<Long, Double> montos = i.getDistribucionCC().stream().collect(Collectors.groupingBy(DistribucionRequi::getIdCuenta,
                    Collectors.summingDouble(d -> currencyService.getRateByDistribucion(i.getRequisicion(), i.getLinea(), d.getIdCuenta()))));
            Consumer<Map.Entry<Long, Double>> action = entry -> 
            { 
               System.out.println("Cuenta : " + entry.getKey() + " Monto : " + entry.getValue());
            }; 
            montos.entrySet().forEach(action);
            if(sumaMontos.isEmpty())
                sumaMontos.putAll(montos);
            else
                montos.forEach((k,v)->{
                    if(sumaMontos.containsKey(k))
                        sumaMontos.replace(k, sumaMontos.get(k)+v);
                    else
                        sumaMontos.put(k, v);
                });
        });         
        return sumaMontos;
    }
    
    private void verifyMultiCcBudget(List<CheckBudgetRequest> items, String idioma, List<ApprovedItem> result, List<Presupuesto> presupuestos) {
        List<CategoryCentroCosto> presupuestosAux = new ArrayList<>(); //<RELEASE_ETOWN>
        Map<Long, Double> sumaMontos = getMontosMultiCC(items.stream().filter(this::isMultiCc).collect(Collectors.toList()));

        for (Map.Entry<Long, Double> entry : sumaMontos.entrySet()) {
            CategoryCentroCosto presupuesto = contables.findPresupuestoCcByCuentaAndIdioma(entry.getKey(), idioma);
            if (Objects.nonNull(presupuesto)) {
                //
                //CheckBudgetRequest item = items.stream().filter(x -> x.getCuenta().equals(entry.getKey())).findAny().orElse(null);
                CheckBudgetRequest item = items.stream().filter(this::isMultiCc).collect(Collectors.toList()).stream().filter(x ->{ 
                        for(DistribucionRequi dcc: x.getDistribucionCC()){
                            if(dcc.getIdCuenta().equals(entry.getKey()))
                                return true;
                        }
                        return false;
                    }
                ).findAny().orElse(null);
                if (item != null && item.isPreAprobacion()) {
                    //ERM 66822 - Aprobacion marca falta de presuuesto pero si cuenta con dinero el CC
                    presupuesto.setActual(presupuesto.getDisponible());
                    //presupuesto.setActual(1D);
                    //entry.setValue(0D);
                } else {
                    presupuesto.setActual(presupuesto.getDisponible() - entry.getValue());
                }
                //
                
                if (presupuesto.getActual() < 0 && 63L != presupuesto.getCategoria() && !item.isPreAprobacion()) {
                    CategoryConfiguration conf = configCategorias.findOne(
                            new CategoryConfiguration.Pk(presupuesto.getUen(), presupuesto.getCategoria()));
                    if (conf.isAprobacionExceso()) {
                        result.add(new ApprovedItem(entry.getKey(), false, true));
                    } else {
                        result.add(new ApprovedItem(entry.getKey(), false, false));
                    }
                } else {
                    result.add(new ApprovedItem(entry.getKey(), true, false));
                }
                presupuestos.add(presupuesto);
                presupuestosAux.add(presupuesto); //<RELEASE_ETOWN>
            } else {
                result.add(new ApprovedItem(entry.getKey(), false, false));
            }
        }
        
        //<RELEASE_ETOWN>
//        int i;
//        int j;
//        int k;
//        for(i=0; i < presupuestosAux.size(); i++){
//            for(j=i+1; j < presupuestosAux.size(); j++){
//                if(presupuestosAux.get(i).getUen().equals(presupuestosAux.get(j).getUen()) 
//                        && presupuestosAux.get(i).getCodigoCC().equals(presupuestosAux.get(j).getCodigoCC())
//                        && presupuestosAux.get(i).getCategoria().equals(presupuestosAux.get(j).getCategoria())
//                        && !(presupuestosAux.get(i).getCuenta().equals(presupuestosAux.get(j).getCuenta())) ){
//                   
//
//                        if(0 == i){
//                            Double presActual = presupuestosAux.get(i).getActual() - sumaMontos.get(presupuestosAux.get(j).getCuenta());
//                            log.debug("i-j: " + i + "-" + j);
//                            log.debug("presupuestos.get(i).getActual(): " + presupuestosAux.get(0).getActual());
//                            log.debug("sumaMontos.get(presupuestosAux.get(j).getCuenta()): " + sumaMontos.get(presupuestosAux.get(j).getCuenta()));
//                            log.debug("presActual: " + presActual);
//                            for(k=0; k < presupuestos.size(); k++){
//                                if(presupuestosAux.get(i).getUen().equals(presupuestosAux.get(k).getUen()) 
//                                    && presupuestosAux.get(i).getCodigoCC().equals(presupuestosAux.get(k).getCodigoCC())
//                                    && presupuestosAux.get(i).getCategoria().equals(presupuestosAux.get(k).getCategoria()) ){
//
//                                    presupuestos.get(k).setActual(presActual); 
//                                }
//
//                            }
//                        }
//                        
//                        if (presupuestos.get(i).getActual() < 0 && 63L != presupuestosAux.get(i).getCategoria()) {
//                            CategoryConfiguration conf = configCategorias.findOne(
//                                    new CategoryConfiguration.Pk(presupuestosAux.get(i).getUen(), presupuestosAux.get(i).getCategoria()));
//                            if (conf.isAprobacionExceso()) {
//                                result.get(i).setAprobado(false);
//                                result.get(i).setExceso(true);
//                                result.get(j).setAprobado(false);
//                                result.get(j).setExceso(true);
//                            } else {
//                                result.get(i).setAprobado(false);
//                                result.get(i).setExceso(false);
//                                result.get(j).setAprobado(false);
//                                result.get(j).setExceso(false);
//                            }
//                        } else {
//                            result.get(i).setAprobado(true);
//                            result.get(i).setExceso(false);
//                            result.get(j).setAprobado(true);
//                            result.get(j).setExceso(false);
//                        }
//                                              
//                }
//            
//            }
//        }
        //</RELEASE_ETOWN>
    }
    //</MDA_MULTICC>

    /**
     * request
     */
    @Data
    @AllArgsConstructor
    public static class ApprovalItemRequest {

        private Long requisicion;
        private Long linea;
        private String aprobador;
        private Integer tipoAprobacion;
        private String razon;
        private String solucion;
        private Integer idCuenta;
    }

    private List<ApprovalItemRequest> getItems(ApprovalRequest request) {
        if (request.getRequisiciones().isEmpty()) {
            return new ArrayList<>();
        }
        //<MDA_MULTICC>
        List<ApprovalItemRequest> it = Lists.newArrayList();//<MDA_MULTICC>
        it.addAll(request.getRequisiciones().stream()
                .flatMap(r -> {
                    List<ApprovalItemRequest> lineas = r.getLineas().stream()
                            .filter(t -> (null == t.getIdTipoCargo() || !Constants.TIPO_CARGO.MULTI_CC.getIdTipoCargo().equals(t.getIdTipoCargo())))
                            .map(l -> new ApprovalItemRequest(r.getIdRequisicion(),
                            l.getIdPartida(), request.getAprobador(),
                            0,
                            l.getRazon(),
                            l.getSolucion(),
                            0))
                            .collect(Collectors.toList());
                    return lineas.stream();
                }).collect(Collectors.toList()));

        for (RequisicionVO r : request.getRequisiciones()) {
            for (LineaVO l : r.getLineas()) {
                if (null != l.getIdTipoCargo() && l.getIdTipoCargo().equals(Constants.TIPO_CARGO.MULTI_CC.getIdTipoCargo())) {
                    for (DistribucionVO d : l.getDistribuciones()) {
                        it.add(new ApprovalItemRequest(r.getIdRequisicion(),
                            l.getIdPartida(), request.getAprobador(),
                            0,
                            l.getRazon(),
                            l.getSolucion(),
                            d.getIdCuenta()));
                    }
                }
            }
        }
        return it;
        //<MDA_MULTICC>
    }
    protected List<ApprovalResponse> preaprobarReq(ApprovalRequest request, String modulo) {
        validateRequest(request, false);
        List<ApprovalItemRequest> items = getItems(request);
        List<ApprovalResponse> response = Lists.newArrayList();
        if (!items.isEmpty()) {
            // aprobar
            response.addAll(preaprobarRequisicion(items, request.getAprobador(), modulo));
            items.removeIf(item -> response.stream()
                    .filter(r -> item.getRequisicion().equals(r.getRequisicion()) && item.getLinea().equals(r.getLinea()))
                    .count() > 0
            );
        }
        return response;
    }
    protected List<ApprovalResponse> aprobar(ApprovalRequest request,String modulo) {
        validateRequest(request, false);

        List<ApprovalItemRequest> items = getItems(request);
        List<ApprovalResponse> response = Lists.newArrayList();
        
//        blockItems(items, true); //Aprobacion sin presupuesto
        try {
            /***********************************************************************
             * Blqoue de codigo deprecado, comentarizado por JAMO autorizado por Mike Salazar
             * Fecha: 30/Oct/2019
             ***********************************************************************
            
            // Verificar si es ISO para aprobar directamente
            response.addAll(verificarISO(items));
            items.removeIf(item -> response.stream()
                    .filter(r -> item.getRequisicion().equals(r.getRequisicion()) && item.getLinea().equals(r.getLinea()))
                    .count() > 0
            );
            **************************************************************************/

            if (!items.isEmpty()) {
                // validar presupuesto
                response.addAll(validarCuentas(items, request.getAprobador()));
                items.removeIf(item -> response.stream()
                        .filter(r -> item.getRequisicion().equals(r.getRequisicion()) && item.getLinea().equals(r.getLinea()))
                        .count() > 0
                );
            }

            if (!items.isEmpty()) {
                // aprobar
                response.addAll(aprobarRequisicion(items, request.getAprobador(),modulo));
                items.removeIf(item -> response.stream()
                        .filter(r -> item.getRequisicion().equals(r.getRequisicion()) && item.getLinea().equals(r.getLinea()))
                        .count() > 0
                );
            }
        } finally {
            blockItems(getItems(request), false);
        }

        return response;
    }

    private List<ApprovalResponse> aprobarCriticas(ApprovalRequest request, String modulo) {
        validateRequest(request, true);

        List<ApprovalItemRequest> items = getItems(request);
        List<ApprovalResponse> response = Lists.newArrayList();

        blockItems(items, true);
        try {
            if (!items.isEmpty()) {
                // aprobar
                response.addAll(aprobarRequisicionCriticas(items, request.getAprobador(), request.getSolicitante(), modulo));
                items.removeIf(item -> response.stream()
                        .filter(r -> item.getRequisicion().equals(r.getRequisicion()) && item.getLinea().equals(r.getLinea()))
                        .count() > 0
                );
            }
        } finally {
            blockItems(getItems(request), false);
        }

        return response;
    }

    private void validateRequest(ApprovalRequest request, boolean isCritical) {
        if (request == null) {
            throw new IllegalArgumentException(messages.getMessage("approvals.request.error_null", null,
                    LocaleContextHolder.getLocale()));
        }
        if (StringUtils.isBlank(request.getAprobador())) {
            throw new IllegalArgumentException(messages.getMessage("approvals.request.approver_null", null,
                    LocaleContextHolder.getLocale()));
        }
        if (isCritical && request.getSolicitante() == null) {
            throw new IllegalArgumentException(messages.getMessage("approvals.request.requester_null", null,
                    LocaleContextHolder.getLocale()));
        }
        if (request.getRequisiciones() == null || request.getRequisiciones().isEmpty()) {
            throw new IllegalArgumentException(messages.getMessage("approvals.request.requis_empty", null,
                    LocaleContextHolder.getLocale()));
        }

    }
	
    private Collection<? extends ApprovalResponse> preaprobarRequisicion(List<ApprovalItemRequest> items, String aprobador,String modulo) {
        List<ApprovalResponse> response = Lists.newArrayList();
        items.forEach(item -> {
            blockItem(item, true); //Aprobacion sin presupuesto
            Integer result = db.preaprobarRequisicion(item.getRequisicion(), item.getLinea(), aprobador, modulo);
            log.debug(MessageFormat.format("aprobacion: {0,number,#}-{1} --> {2}", item.getRequisicion(),
                    item.getLinea(), result));
            if (result == -1) {
                result = 1;
            }
            if (result == 70) {
                result = 9;
            }

            if (result == 0 || result == 4 || result == 200 || result == 204) {
                response.add(new ApprovalResponse(item.getRequisicion(),
                        item.getLinea(),
                        true,
                        messages.getMessage("approvals.code." + result, null, LocaleContextHolder.getLocale()),
                        result));
            } else if (result == -200) {//<T403785>
                response.add(new ApprovalResponse(item.getRequisicion(),
                        item.getLinea(),
                        false,
                        messages.getMessage("approvals.valid.project", null, LocaleContextHolder.getLocale())+
                        messages.getMessage("approvals.valid.contact", null, LocaleContextHolder.getLocale()),
                        result));
            } else if (result == -300) {
                response.add(new ApprovalResponse(item.getRequisicion(),
                        item.getLinea(),
                        false,
                        messages.getMessage("approvals.valid.project_period", null, LocaleContextHolder.getLocale())+
                        messages.getMessage("approvals.valid.contact", null, LocaleContextHolder.getLocale()),
                        result));
            } else if (result == -400) {
                response.add(new ApprovalResponse(item.getRequisicion(),
                        item.getLinea(),
                        false,
                        messages.getMessage("approvals.valid.task", null, LocaleContextHolder.getLocale())+
                        messages.getMessage("approvals.valid.contact", null, LocaleContextHolder.getLocale()),
                        result));
            } else if (result == -500) {
                response.add(new ApprovalResponse(item.getRequisicion(),
                        item.getLinea(),
                        false,
                        messages.getMessage("approvals.valid.task.charges", null, LocaleContextHolder.getLocale())+
                        messages.getMessage("approvals.valid.contact", null, LocaleContextHolder.getLocale()),
                        result));
            } else if (result == -600) {
                response.add(new ApprovalResponse(item.getRequisicion(),
                        item.getLinea(),
                        false,
                        creaMensajeErrorAprobar(messages.getMessage("approvals.valid.project_budget_update", null, LocaleContextHolder.getLocale()),item.getRequisicion(), item.getLinea())+
                        messages.getMessage("approvals.valid.contact", null, LocaleContextHolder.getLocale()),
                        result));
            }//</T403785>
            else {
                if (result == 100 || result == 3) {
                    Optional<DetalleRequisicion> detail = detalles.getByRequisicionAndLinea(item.getRequisicion(), item.getLinea());
                    if (detail.isPresent()) {
                        CategoryConfiguration conf = configCategorias.findOne(
                                new CategoryConfiguration.Pk(detail.get().getUen(), detail.get().getCategoryId()));
                        if (conf != null && conf.isAprobacionExceso())
                            result = 101;
                    }
                }
                response.add(new ApprovalResponse(item.getRequisicion(),
                        item.getLinea(),
                        false,
                        messages.getMessage("approvals.code." + result, null, LocaleContextHolder.getLocale()),
                        result));
            }
        });
        return response;
    }

    private Collection<? extends ApprovalResponse> aprobarRequisicion(List<ApprovalItemRequest> items, String aprobador, String modulo) {
        log.debug(" **** aprobarRequisicion *** ");
        try {
            for (ApprovalItemRequest item : items) {
                log.debug(" **** aprobarRequisicion.Requisicion *** " + item.getRequisicion());
                log.debug(" **** aprobarRequisicion.Linea *** " + item.getLinea());
                log.debug(" **** aprobarRequisicion.Razon *** " + item.getRazon());
                log.debug(" **** aprobarRequisicion.TipoAprobacion *** " + item.getTipoAprobacion());
                log.debug(" **** aprobarRequisicion.MessageFormat *** " + MessageFormat.format("aprobacion: {0,number,#}-{1} --> {2}", item.getRequisicion(), item.getLinea(), 0));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        List<ApprovalResponse> response = Lists.newArrayList();
        items.forEach(item -> {
            blockItem(item, true); //Aprobacion sin presupuesto
            Integer iResult = db.iniAprobarRequisicion(item.getRequisicion(), item.getLinea(), aprobador, modulo, item.getIdCuenta());
            log.debug(MessageFormat.format("aprobacion: {0,number,#}-{1} --> {2}", item.getRequisicion(),
                    item.getLinea(), iResult));
            if (iResult == -1) {
                iResult = 1;
            }
            if (iResult == 70) {
                iResult = 9;
            }
            Object[] oResult = approval_validation.EvalMessageApproval(iResult, item);
                response.add(new ApprovalResponse(item.getRequisicion(),
                        item.getLinea(),
                        (boolean)oResult[1],
                        (String)oResult[0],
                        iResult));
        });
        Map<Long, List<ApprovalResponse>> externas = response.stream()
                .filter(r -> r.getResult() == 2)
                .collect(Collectors.groupingBy(ApprovalResponse::getRequisicion));

        for (Map.Entry<Long, List<ApprovalResponse>> e : externas.entrySet()) {
            ApprovalResponse first = response.stream()
                    .filter(r -> e.getKey().equals(r.getRequisicion()) && r.getResult() != 2)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("condicion no esperada"));

            e.getValue().forEach(r -> {
                r.setAprobado(first.isAprobado());
                r.setMensaje(first.getMensaje());
            });
        }
        return response;
    }

    private Collection<? extends ApprovalResponse> aprobarRequisicionCriticas(List<ApprovalItemRequest> items,
            String aprobador,
            String solicitante, //<R17424>
            String modulo) {
        List<ApprovalResponse> response = Lists.newArrayList();
        items.forEach(item -> {
            Integer result = db.aprobarRequisicionCritica(
                    item.getRequisicion(),
                    item.getLinea(),
                    aprobador,
                    solicitante,
                    item.getRazon(),
                    modulo);
            log.debug(MessageFormat.format("aprobacion critica: {0,number,#}-{1} --> {2}", item.getRequisicion(),
                    item.getLinea(), result));
            if (result == 0 || result == 4) {
                response.add(new ApprovalResponse(item.getRequisicion(),
                        item.getLinea(),
                        true,
                        messages.getMessage("approvals.code.0", null, LocaleContextHolder.getLocale()),
                        result));
            } else {
                if (result != 1 && result != 100 && result != 50) {
                    result = 2;
                }
                response.add(new ApprovalResponse(item.getRequisicion(),
                        item.getLinea(),
                        false,
                        messages.getMessage("approvals.critical." + result, null, LocaleContextHolder.getLocale()),
                        result));
            }
        });
        return response;
    }

    private Collection<? extends ApprovalResponse> validarCuentas(List<ApprovalItemRequest> items, String aprobador) {
        /*
          Esto es para aprobacion por exceso: las lineas que vengan con razon y solucion
          son de aprobacion por exceso y en ese caso no se hace chequeo de presupuesto
          sino que se actualizan esos valores en la BD.
         */
        Map<Long, Set<Long>> porExceso = items.stream()
                .filter(i -> StringUtils.isNotBlank(i.getRazon()) && StringUtils.isNotBlank(i.getSolucion()))
                .collect(Collectors.groupingBy(ApprovalItemRequest::getRequisicion,
                        mapping(ApprovalItemRequest::getLinea, Collectors.toSet())));
        List<ApprovalResponse> response = items.stream()
                .filter(i -> porExceso.containsKey(i.getRequisicion())
                && !porExceso.get(i.getRequisicion()).contains(i.getLinea()))
                .filter(i -> Integer.valueOf(-1).equals(db.verificarPresupuesto(i.getRequisicion(), i.getLinea(), aprobador, i.getIdCuenta())))
                .map(i -> new ApprovalResponse(i.getRequisicion(),
                i.getLinea(),
                false,
                messages.getMessage("approvals.reject.byBudget", null, LocaleContextHolder.getLocale()),
                -1))
                .collect(Collectors.toList());
        items.stream()
                .filter(i -> porExceso.containsKey(i.getRequisicion())
                && porExceso.get(i.getRequisicion()).contains(i.getLinea()))
                .forEach(updateExceededLine());
        return response;
    }

    public void validaLineasFad(ApprovalRequest request) {//<T567460>
        /*
          Esto es para aprobacion por exceso: las lineas que vengan con razon y solucion
          son de aprobacion por exceso y en ese caso no se hace chequeo de presupuesto
          sino que se actualizan esos valores en la BD este fix aplica para requis fad.
         */        
        List<ApprovalItemRequest> items = getItems(request);        
        
        Map<Long, Set<Long>> porExceso = items.stream()
                .filter(i -> StringUtils.isNotBlank(i.getRazon()) && StringUtils.isNotBlank(i.getSolucion()))
                .collect(Collectors.groupingBy(ApprovalItemRequest::getRequisicion,
                        mapping(ApprovalItemRequest::getLinea, Collectors.toSet())));        
        items.stream()
                .filter(i -> porExceso.containsKey(i.getRequisicion())
                && porExceso.get(i.getRequisicion()).contains(i.getLinea()))
                .forEach(updateExceededLine());
    }
    
    protected Consumer<ApprovalItemRequest> updateExceededLine() {
        return i -> {
            try {
                detalles.getByRequisicionAndLinea(i.getRequisicion(), i.getLinea())
                        .orElseThrow(() -> new NotFoundException("Requi-Linea no encontrada"));
                db.updateExceso(i.getRequisicion(), i.getLinea(), i.getRazon(), i.getSolucion());
            } catch (Exception e) {
                log.debug("No se actualizo detalle con exceso...." + e);
            }
        };
    }

    private Collection<? extends ApprovalResponse> verificarISO(List<ApprovalItemRequest> items) {
        /*
          1- verificar si aplica encuesta para la requisicion pkg_iso.sl_uen_encuesta(req)
          2- si aplica llamar pkg_iso.up_status_req(req,linea) pone estatus a POR APROBAR EMS
         */
        List<ApprovalResponse> response = items.stream()
                .filter(i -> StringUtils.isNotBlank(db.verificarISO(i.getRequisicion())))
                .map(i -> {
                    db.aprobarISO(i.getRequisicion(), i.getLinea());
                    return new ApprovalResponse(i.getRequisicion(),
                            i.getLinea(),
                            true,
                            messages.getMessage("approvals.accept.byISO", null, LocaleContextHolder.getLocale()),
                            0);
                })
                .collect(Collectors.toList());
        return response;
    }

    private void blockItem(ApprovalItemRequest item, boolean blocked) {
        db.bloquearDetalle(item.getRequisicion(), item.getLinea(), item.getIdCuenta(), blocked ? "1" : ""); //Aprobacion sin presupuesto
    }
    
    private void blockItems(List<ApprovalItemRequest> items, boolean blocked) {
        items.forEach(i -> db.bloquearDetalle(i.getRequisicion(), i.getLinea(), i.getIdCuenta(), blocked ? "1" : ""));
    }

    public List<ApprovalResponse> cancel(RegresoRequisicionVO data,String modulo) {
        @Getter
        @AllArgsConstructor
        class CancelRequest {

            private Long requisicion;
            private Long linea;
            private String aprobador;
            private String razon;
        }

        return data.getRequisiciones().stream()
                .flatMap(r -> r.getLineas().stream()
                .map(l -> new CancelRequest(
                r.getIdRequisicion(),
                l.getIdPartida(),
                data.getIdAprobador(),
                data.getRazonRegreso())))
                .map(i -> {
                    int result = db.cancelarRequisicion(i.getRequisicion(), i.getLinea(), i.getRazon(), i.getAprobador(),modulo);
                    log.debug(MessageFormat.format("cancelar: {0,number,#}-{1} --> {2}", i.getRequisicion(),
                            i.getLinea(), result));
                    return new ApprovalResponse(i.getRequisicion(),
                            i.getLinea(),
                            result == 0,
                            messages.getMessage("approvals.cancel." + result, null, LocaleContextHolder.getLocale()),
                            0);
                })
                .collect(Collectors.toList());
    }

    public List<ApprovalResponse> toReturn(RegresoRequisicionVO data,String modulo) {
        @Getter
        @AllArgsConstructor
        class ReturnRequest {

            private Long requisicion;
            private Long linea;
            private String aprobador;
            private String comentario;
        }

        return data.getRequisiciones().stream()
                .flatMap(r -> r.getLineas().stream()
                .map(l -> new ReturnRequest(
                r.getIdRequisicion(),
                l.getIdPartida(),
                data.getIdAprobador(),
                data.getRazonRegreso())))
                .map(i -> {
                    int result = db.regresarRequisicion(i.getRequisicion(), i.getLinea(), i.getComentario(), i.getAprobador(),modulo);
                    log.debug(MessageFormat.format("cancelar: {0,number,#}-{1} --> {2}", i.getRequisicion(),
                            i.getLinea(), result));
                    return new ApprovalResponse(i.getRequisicion(),
                            i.getLinea(),
                            result == 0,
                            messages.getMessage("approvals.return." + result, null, LocaleContextHolder.getLocale()),
                            0);
                })
                .collect(Collectors.toList());
    }

    public boolean verificarPerfil(String idUsuario, Integer idNotificacion, String esperado) {
        String valorPerfil = obtenerPerfil(idUsuario, idNotificacion);
        return Objects.equals(valorPerfil, esperado);
    }

    public String obtenerPerfil(String idUsuario, Integer idNotificacion) {
        return this.db.obtenerValorPerfil(idUsuario, idNotificacion);
    }

    public void guardaMailAprobacion(ApprovalRequest data, MailNotificationService mailservice) {
		//<T395493>
        List<MailData> listMail=mailservice.getMailApproval(data,MailNotificationService.APPROVED);
        for (MailData mail : listMail) {
            log.debug(this.db.guardaMailAprobacion(mail.getBody(), mail.getReceipts().get(0), mail.getSubject()));
        }        
    }//</T395493>    
    
    public String creaMensajeErrorAprobar(String msj, Long requi, Long linea) {//<T426293>

        Optional<DetalleRequisicion> detail = detalles.getByRequisicionAndLinea(requi, linea);        
        OaUens uenEntity = db.findMainValuesById(detail.get().getUen());

        return messages.getMessage(msj, null, LocaleContextHolder.getLocale()).
                replace("[proyecto]", detail.get().getCodigoProyecto()).
                replace("[uen]", uenEntity.getOrganizationName());
        
    }   
}
