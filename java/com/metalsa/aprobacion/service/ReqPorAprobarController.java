package com.metalsa.aprobacion.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.metalsa.analytic.performance.PerformaceMeasure;
import com.metalsa.aprobacion.model.*;
import com.metalsa.aprobacion.repository.*;
//<R16788>
//<R16788>
import com.metalsa.core.model.Uens;
import com.metalsa.error.NotFoundException;
import com.metalsa.utils.Constants;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
//<R16788>
import com.metalsa.contralor.model.Configuracion;
import com.metalsa.contralor.model.Valores;
import com.metalsa.contralor.repository.ConfiguracionRepository;
import com.metalsa.core.model.CentroCostoUsuario;
import com.metalsa.core.repository.PeriodosRepository;
import com.metalsa.core.model.Periodos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author jose.rivera02
 */
@RestController
@RequestMapping(Constants.URI_API_APROBACIONES)
@CrossOrigin
@Api(value = "ReqPorAprobarController", description = "servicios de aprobacion")
@Log4j
public class ReqPorAprobarController {

    @Autowired
    private ReqPorAprobarRepository reqPorAprobarRepository;

    @Autowired
    private CategoryBudgetRepository categorias;

    @Autowired
    private MailNotificationService mailService;

    @Autowired
    private RolesPorUsuarioRepository rolRepository;

    @Autowired
    private OrganizacionesRepository uens;

    @Autowired
    private CentroCostoRepository centros;

    //<MDA_CONTRALOR>
    @Autowired
    private MembresiaCentroCostoRepository miembros;

    @Autowired
    private ConfiguracionRepository configuracionRepository;

    //<R16788>
    @Autowired
    private PeriodosRepository periodosRepository;
    //<R16788>

    // <FAD>
    @Autowired
    private NvcTblFadRepository fadService;
    // <FAD/>	

    @Qualifier("application")
    @Autowired
    private MessageSource messages;

    @Autowired
    private DetalleRequisicionRepository detalleRequisicionRepository;

    @RequestMapping(params = {"idUsuario"}, method = RequestMethod.GET)
    public @ResponseBody
    Iterable<ReqPorAprobar> getRequisPorAprobar(@RequestParam("idUsuario") String idUsuario,
            @RequestParam(value = "filtro", required = false) String filter,
            @RequestParam(value = "uen", required = false) Long uen,
            @RequestParam(value = "requisicion", required = false) String requisicion
    ) {

        if ("criticas".equalsIgnoreCase(filter)) {
            if (uen == null) {
                throw new IllegalArgumentException(messages.getMessage("approvals.critical.missing_uen", null,
                        LocaleContextHolder.getLocale()));
            }
            return reqPorAprobarRepository.getRequisCriticas(idUsuario, uen);
        } else if ("preaprobacion".equalsIgnoreCase(filter)) {
            return reqPorAprobarRepository.getRequisPorPreAprobar(idUsuario);
        } else if ("fuera_rango".equalsIgnoreCase(filter)) {
            return reqPorAprobarRepository.getRequisFueraRango(idUsuario);
        } else if ("superaprobacion".equalsIgnoreCase(filter)) {
            if (uen == null) {
                throw new IllegalArgumentException(messages.getMessage("approvals.super.missing_uen", null, LocaleContextHolder.getLocale()));
            }
            //<ERM#14135>
            return reqPorAprobarRepository.getRequisSuperAprobacion(idUsuario, uen.toString(), requisicion);
            //</ERM#14135>
        }

        return reqPorAprobarRepository.getRequisPorAprobar(idUsuario);
    }

    //<ERM#14135>
    @RequestMapping(value = "getRequisSuperAprobacion", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<ReqPorAprobar> getRequisSuperAprobacion(
            @RequestParam("idUsuario") String idUsuario,
            @RequestParam("uens") String uens,
            @RequestParam("requisicion") String requisicion
    ) {

        if (requisicion == null || requisicion.isEmpty()) {
            log.debug("sin criterio de busqueda");
            return new ArrayList<>();
        }
        return reqPorAprobarRepository.getRequisSuperAprobacion(idUsuario, uens, requisicion);
    }
    //</ERM#14135>

    @RequestMapping(value = "/counter", params = {"idUsuario"}, method = RequestMethod.GET)
    @ResponseBody
    @PerformaceMeasure
    public Long contadores(@RequestParam("idUsuario") String idUsuario,
            @RequestParam(value = "filtro", required = false) String filter,
            @RequestParam(value = "uen", required = false) Long uen) {

        Map<String, Object> params = Maps.newHashMap();
        params.put("p_aprobador", idUsuario);
        ReqPorAprobarRepository.APPROVAL_TYPE tipo = ReqPorAprobarRepository.APPROVAL_TYPE.POR_APROBAR;
        if ("criticas".equalsIgnoreCase(filter)) {
            if (uen == null) {
                throw new IllegalArgumentException(messages.getMessage("approvals.critical.missing_uen", null,
                        LocaleContextHolder.getLocale()));
            }
            tipo = ReqPorAprobarRepository.APPROVAL_TYPE.POR_CRITICAS;
            params.put("p_uen", uen);
        } else if ("preaprobacion".equalsIgnoreCase(filter)) {
            tipo = ReqPorAprobarRepository.APPROVAL_TYPE.POR_PREAPROBAR;
        } else if ("fuera_rango".equalsIgnoreCase(filter)) {
            tipo = ReqPorAprobarRepository.APPROVAL_TYPE.POR_FUERARANGO;
        } else if ("superaprobacion".equalsIgnoreCase(filter)) {
            if (uen == null) {
                throw new IllegalArgumentException(messages.getMessage("approvals.super.missing_uen", null, LocaleContextHolder.getLocale()));
            }
            tipo = ReqPorAprobarRepository.APPROVAL_TYPE.POR_SUPERAPROBAR;
            params.put("p_id_uens", uen.toString());
        }

        return reqPorAprobarRepository.countRequisiciones(tipo, params);
    }

    @RequestMapping(value = "/gastoAd", params = {"idRequisicion", "idPartida", "idIdioma", "aprobador"}, method = RequestMethod.GET)
    public @ResponseBody
    Iterable<GastoAdicional> getGastosAdicionales(
            @RequestParam("idRequisicion") Integer idRequisicion,
            @RequestParam("idPartida") Integer idPartida,
            @RequestParam("idIdioma") String idIdioma,
            @RequestParam("aprobador") String aprobador
    ) {

        return reqPorAprobarRepository.getGastosAdicionales(idRequisicion, idPartida, idIdioma, aprobador);
    }

    @RequestMapping(value = "/regresarRequisiciones", params = {"lang"}, method = RequestMethod.POST)
    @ResponseBody
    public Iterable<ApprovalResponse> regresarRequisiciones(
            @RequestHeader(value = "modulo") String modulo,
            @RequestBody RegresoRequisicionVO data
    ) {
        List<ApprovalResponse> responses = service.toReturn(data, modulo);

        filterApproved(data, responses);

        if (!data.getRequisiciones().isEmpty()) {
            try {
                mailService.notificarRegresadas(data);
            } catch (Exception e) {
                System.out.println("no se fue posible enviar la notificacion=> " + e.getLocalizedMessage());
                System.out.println(e);
            }
        }
        return responses;
    }

    @RequestMapping(value = "/cancelarRequisiciones", params = {"lang"}, method = RequestMethod.POST)
    @ResponseBody
    public Iterable<ApprovalResponse> cancelarRequisiciones(
            @RequestHeader(value = "modulo") String modulo,
            @RequestBody RegresoRequisicionVO data
    ) {
        List<ApprovalResponse> responses = service.cancel(data, modulo);

        filterApproved(data, responses);

        if (!data.getRequisiciones().isEmpty()) {
            try {
                mailService.notificarRechazadas(data);
            } catch (Exception e) {
                System.out.println("no se fue posible enviar la notificacion=> " + e.getLocalizedMessage());
                System.out.println(e);
            }
        }
        return responses;
    }

    private void filterApproved(@RequestBody RegresoRequisicionVO data, List<ApprovalResponse> responses) {
        Map<Long, Set<Long>> aprobadas = responses.stream()
                .filter(ApprovalResponse::isAprobado)
                .collect(Collectors.groupingBy(ApprovalResponse::getRequisicion,
                        Collectors.mapping(ApprovalResponse::getLinea, Collectors.toSet())));

        data.getRequisiciones().removeIf(r -> !aprobadas.keySet().contains(r.getIdRequisicion()));
        data.getRequisiciones()
                .forEach(r -> r.getLineas().removeIf(l -> !aprobadas.get(r.getIdRequisicion()).contains(l.getIdPartida())));
    }

    @RequestMapping(value = "/checkBudget", method = RequestMethod.POST)
    @ResponseBody
    public CheckBudgetResponse verificarPresupuestoPorGrupo(@RequestBody List<CheckBudgetRequest> items) {
        String idUsuario = items == null ? "" : items.isEmpty() ? "" : items.get(0).getIdUsuario();   //Contralor
        for (CheckBudgetRequest item : items) {
            List<String> sigAprobacion = reqPorAprobarRepository.getSiguienteAprobador(item.getRequisicion(), item.getLinea(), idUsuario);
            if (sigAprobacion!=null && !sigAprobacion.isEmpty() && sigAprobacion.get(0) != null) {
                item.setPreAprobacion(true);
            }
            Optional<DetalleRequisicion> detalle=detalleRequisicionRepository.getByRequisicionAndLinea(item.getRequisicion(), item.getLinea());
            if (detalle.isPresent()) {
                item.setIdTipoCargo(detalle.get().getIdTipoCargo());
            }
            
        }

        CheckBudgetResponse response = service.verifyBudget(items, Constants.getIdiomaFromRequest());
        List<Presupuesto> presupuestos = response.getPresupuestos();

        Map<Integer, Boolean[]> maps = new HashMap<>();
        for (Presupuesto presupuesto : presupuestos) {

            CategoryCentroCosto cc = (CategoryCentroCosto) presupuesto;
            Integer uen = cc.getUen().intValue();

            boolean delegado;
            boolean responsable;
            boolean otrosccs = false;
            if (maps.containsKey(uen) == false) {

                Configuracion conf = this.configuracionRepository.findByUenName(uen, "PERMITIR_TRANSFERENCIAS_RESPONSABLE");
                responsable = "Y".equals(conf.getCondition());

                conf = this.configuracionRepository.findByUenName(uen, "PERMITIR_TRANSFERENCIAS_DELEGADO");
                delegado = "Y".equals(conf.getCondition());
//                

                Valores temp = this.configuracionRepository.findSingleValue(uen, "PERMITIR_TRANSFERENCIAS_CCS");
                if (temp != null && "Y".equals(temp.getValue())) {
                    conf = this.configuracionRepository.findByUenName(uen, "PERMITIR_TRANSFERENCIAS_OTROS_CCS");
                    otrosccs = conf != null && "Y".equals(conf.getCondition());
                }

                maps.put(uen, new Boolean[]{responsable, delegado, otrosccs});

            } else {
                responsable = maps.get(uen)[0];
                delegado = maps.get(uen)[1];
                otrosccs = maps.get(uen)[2];
            }

            if (true == otrosccs) {
                cc.setPermitido(true);
            } else {
                List<CentroCostoUsuario> relaciones = this.miembros.getByUserUenCC("ESA", idUsuario, uen, cc.getCentroCosto());
                for (CentroCostoUsuario relacion : relaciones) {
                    log.debug(relacion.getCc() + ":" + relacion.getRelacion());
                    if ("Resp".equals(relacion.getRelacion()) && responsable) {
                        cc.setPermitido(true);
                    } else if ("Del".equals(relacion.getRelacion()) && delegado) {
                        cc.setPermitido(true);
                    }
                }
            }
        }  //end Contralor
        return response;
    }

    @Autowired
    private ProjectBudgetRepository projectBudget;

    //R41223: INICIO    
    @RequestMapping(value = "/balance", method = RequestMethod.GET)
    public Double presupuestoDisponible(
            @RequestParam("uen") Long uen,
            @RequestParam(value = "cc", required = false) Long centroCosto,
            @RequestParam(value = "project", required = false) Long proyecto
    ) {

        if (proyecto != null) {

            return projectBudget.findAllByUenAndProyectoOrderByTareaAscTipoGastoAsc(uen, proyecto)
                    .stream()
                    .mapToDouble(ProjectBudget::getSaldoDisponible)
                    .sum();

        } else if (centroCosto != null) {

            return getCategoriasByUenAndCcAndPeriodo(uen, centroCosto, null);
        }
        throw new IllegalArgumentException();
    }

    public Double getCategoriasByUenAndCcAndPeriodo(Long uen, Long centroCosto, String periodo) {
        return categorias.getCategoriasByUenAndCcAndPeriodo(
                uen,
                centroCosto,
                periodo == null ? this.periodosRepository.getPeriodoActual("ESA").getNombreOriginal() : periodo,
                Constants.getIdiomaFromRequest()
        ).stream()
                .mapToDouble(CategoryBudget::getSaldoDisponible)
                .sum();
    }

    @RequestMapping(value = "/budget", method = RequestMethod.GET)
    public Iterable<? extends Budget> obtenerPresupuesto(
            @RequestParam("uen") Long uen,
            @RequestParam(value = "cc", required = false) Long centroCosto,
            @RequestParam(value = "project", required = false) Long projecto
    ) {
        if (centroCosto != null) {
            return categorias.getCategoriasByUenAndCcAndPeriodo(
                    uen,
                    centroCosto,
                    this.periodosRepository.getPeriodoActual("ESA").getNombreOriginal(),
                    Constants.getIdiomaFromRequest()
            );
        } else if (projecto != null) {
            return projectBudget.findAllByUenAndProyectoOrderByTareaAscTipoGastoAsc(uen, projecto);
        }
        throw new IllegalArgumentException();
    }

    @RequestMapping(value = "/budget/proyecto", method = RequestMethod.GET)
    public ProjectBudget obtenerPresupuestoProyecto(
            @RequestParam("uen") Long uen,
            @RequestParam("proyecto") Long proyecto,
            @RequestParam("tarea") Long tarea,
            @RequestParam("gasto") String gasto
    ) {
        return projectBudget.getByUenAndProyectoAndTareaAndTipoGasto(uen, proyecto, tarea, gasto);
    }

    @RequestMapping(value = "/budget/category", params = {"uen", "cc"}, method = RequestMethod.GET)
    public Iterable<CategoryBudget> obtenerCategoriasContables(
            @RequestParam("uen") Long uen,
            @RequestParam("cc") Long centroCosto,
            @RequestParam(value = "period", required = false) String periodo
    ) {

        if (StringUtils.isBlank(periodo)) {
            periodo = this.periodosRepository.getPeriodoActual("ESA").getNombreOriginal();
        }

        return categorias.getCategoriasByUenAndCcAndPeriodo(uen, centroCosto, periodo, Constants.getIdiomaFromRequest());
    }

    @RequestMapping(value = "/budget/transfer", method = RequestMethod.POST)
    @Transactional
    public ResponseEntity transferenciaPresupuesto(@RequestBody List<Transferencia> transferencias
    ) {
        transferencias.forEach(log::debug);

        //<MDA_CONTRALOR>
        String periodoContable = this.periodosRepository.getPeriodoActual("ESA").getNombreOriginal();
        ResponseEntity re;
        long result = 0;
        for (Transferencia t : transferencias) {
            CategoryBudget origen = categorias.findOne(t.getFrom());
            CategoryBudget destino = categorias.getByIdUenAndCentroCostoAndCategoriaAndPeriodo(t.getUen(),
                    t.getCodigoCentroCosto(), t.getCategoria(), periodoContable);
            //MDA_REPORTES_FINANZAS
            result = categorias.transferenciaPresupuesto(origen, destino, t.getAmmount(), t.getUsuario(),
                    t.getModulo(), t.getAprobador(), t.getSolicitante(), t.getRazon(), t.getPeriodo());
            log.debug(" **** transferenciaPresupuesto *** result: " + result);
            if (result != 0) {
                break;
            }
        }

        re = result == 9100 ? new ResponseEntity("{\"error\":\"NO_ALMACEN_TRANSFER\"}", HttpStatus.CONFLICT)
                : result == 9210 ? new ResponseEntity("{\"error\":\"NO_CATEGOY_BUDGET_ORIGIN_ALLOW\"}", HttpStatus.CONFLICT)
                        : result == 9220 ? new ResponseEntity("{\"error\":\"NO_CATEGOY_BUDGET_DESTINY_ALLOW\"}", HttpStatus.CONFLICT)
                                : new ResponseEntity(result == 0 ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT);
        return re;
        //</MDA_CONTRALOR>
    }

    //<MDA_INC_DEC>
    @RequestMapping(value = "/budget/transfer/finanzas", method = RequestMethod.POST)
    @Transactional
    public ResponseEntity transferenciaPresupuestoFinanzas(@RequestBody List<Transferencia> transferencias
    ) {
        //<MDA_CONTRALOR> para finanzas no debe haber ninguna restriccion

        long result = transferencias.stream()
                .map(t -> {
                    CategoryBudget origen = categorias.findOne(t.getFrom());
                    CategoryBudget destino = categorias.getByIdUenAndCentroCostoAndCategoriaAndPeriodo(t.getUen(),
                            t.getCodigoCentroCosto(), t.getCategoria(), t.getPeriodo());
                    log.debug("ORIGEN:" + origen);
                    log.debug("DESTINO:" + destino);
                    return categorias.transferenciaPresupuestoFinanzas(origen, destino, t.getAmmount(), t.getUsuario(),
                            t.getModulo(), t.getAprobador(), t.getSolicitante(), t.getRazon());
                })
                .filter(r -> r != 0)
                .count();

        return new ResponseEntity(result == 0 ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT);
    }
    //</MDA_INC_DEC>

    //<R16788>
    @GetMapping(path = "/periodos/actual", params = {"idioma"})
    public Periodos getPeriodoActual(String idioma
    ) {
        return this.periodosRepository.getPeriodoActual(idioma);
    }

    //MDA_REPORTES_FINANZAS
    @GetMapping(path = "/periodos/desde", params = {"idioma", "anio", "filtrar"})
    @ResponseBody
    public Iterable<Periodos> getPeriodoDesde(String idioma, Integer anio,
            boolean filtrar
    ) {
        Integer mes = 12;
        Integer currenyYear = Calendar.getInstance().get(Calendar.YEAR) + 1;
        if (filtrar) {
            mes = Calendar.getInstance().get(Calendar.MONTH) + 1;
        }
        return this.periodosRepository.getPeriodoDesde(idioma, anio, currenyYear, mes);
    }

    @GetMapping(path = "/periodos/rangoanual", params = {"idioma", "ainicial", "afinal"})
    public Iterable<Periodos> getPeriodosPorRangoAnual(String idioma, Integer ainicial,
            Integer afinal
    ) {
        return this.periodosRepository.findByIdiomaAndAnioBetween(Constants.getIdioma(idioma), ainicial, afinal);
    }

    @GetMapping(path = "/periodos/rangoaniomes", params = {"ainicial", "afinal", "minicial", "mfinal", "idioma"})
    public Iterable<Periodos> getPeriodosPorRangoAnioMes(Integer ainicial, Integer afinal,
            Integer minicial, Integer mfinal,
            String idioma
    ) {
        //<MDA_CONTRALOR>
        return this.periodosRepository.findByPorRangoAnioMes(ainicial, afinal, minicial, mfinal, Constants.getIdioma(idioma));
    }

    @GetMapping(path = "/periodos/anual")
    public Iterable<Periodos> getPeriodosPorAnio(@RequestParam(required = false) Integer anio, String idioma
    ) {
        if (anio == null) {
            anio = Calendar.getInstance().get(Calendar.YEAR);
        }
        return this.periodosRepository.findByIdiomaAndAnioOrderByNumMesDesc(Constants.getIdioma(idioma), anio);
    }

    @GetMapping(path = "/periodos/anualdisponible", params = {"idioma"})
    public Iterable<Periodos> getPeriodosDisponibles(String idioma
    ) {
        return this.periodosRepository.getAllByAnioActualDisponible(Constants.getIdioma(idioma));
    }

    //<R16788>
    @GetMapping("/budget/history")
    public Iterable<BudgetHistory> getBudgetHistory(@RequestParam("uen") Long uen,
            @RequestParam("cc") Long cc,
            @RequestParam(value = "months", required = false) Long months
    ) {
        return categorias.getLastBudgetHistory(uen, cc, months);
    }

    @GetMapping("/budget/status")
    public BudgetStatusUser getBudgetStatusByUser(
            @RequestParam("user") String user,
            @RequestParam("uen") Long uen,
            @RequestParam("cc") Long cc
    ) {
        String periodoActual = this.periodosRepository.getPeriodoActual("ESA").getNombreOriginal();
        return categorias.getBudgetStatusByUser(user, uen, cc, periodoActual)
                .orElseThrow(() -> new NotFoundException(
                messages.getMessage("budget.status.not-found", null, LocaleContextHolder.getLocale())));
    }

    public String parsearRequisiciones(List<RequisicionVO> requisiciones) {
        StringBuilder requisicionesStr = new StringBuilder();

        for (RequisicionVO requisicion : requisiciones) {
            for (LineaVO linea : requisicion.getLineas()) {
                requisicionesStr.append(requisicion.getIdRequisicion());
                requisicionesStr.append("-");
                requisicionesStr.append(linea.getIdPartida());
                requisicionesStr.append(",");
            }
        }
        String regresar = "";
        if (requisicionesStr.length() > 0) {
            regresar = requisicionesStr.substring(0, (requisicionesStr.length() - 1));
        }

        return regresar;
    }

    @Autowired
    private RequisicionRepository requis;

    @Autowired
    private UsuarioRepository users;

    @Autowired
    private LineCompareRepository lines;

    @Autowired
    private SuplierCompareDtoRepository supliers;

    //<ERM#17475>
    @RequestMapping("/{req}/cmp")
    @ResponseBody
    public RequisicionCompare cuadroComparativo(
            @PathVariable("req") Long idRequisicion,
            @RequestParam(value = "lineas", required = false) List<String> lineas,
            @RequestParam(value = "etapa") String etapa
    ) {

        log.debug("Etapa->" + etapa + ",idRequisicion->" + idRequisicion + " , lineas->" + (lineas));
        Integer idestatus; //POR APROBAR
        switch (etapa) {
            case "aprobacion":
                idestatus = 19;
                break;
            case "preaprobacion":
                idestatus = 58;
                break;
            default:
                idestatus = 19;
                break;
        }

        Requisicion r = requis.findOne(idRequisicion);
        List<LineCompare> lineascom = this.lines.getAllByRequisicion(idRequisicion, Constants.getIdiomaFromRequest(), idestatus);

        Set<Long> idLines = lineascom.stream()
                .mapToLong(LineCompare::getLinea)
                .boxed()
                .collect(Collectors.toSet());

        List<SuplierCompareDto> proveedores = supliers.getAllByRequisicion(idRequisicion, idestatus);

        Map<Long, List<SuplierCompareDto>> group = proveedores.stream()
                .collect(Collectors.groupingBy(SuplierCompareDto::getIdProveedor));

        List<SuplierCompare> providerDetails = group.entrySet().stream()
                .map(e -> new SuplierCompare(e.getKey(), e.getValue().get(0).getNombreProveedor(),
                e.getValue().get(0).getTerminosEspecialesPago(),
                e.getValue().stream()
                        .filter(s -> idLines.contains(s.getIdLinea()))
                        .map(SuplierCompare.SuplierCompareDetail::fromDto)
                        .collect(Collectors.toList())))
                .filter(p -> !p.getRfq().isEmpty())
                .collect(Collectors.toList());

        //filtrando para que muestre solo los proveedores que tengan lineas mostradas
        NvcTblOrganizacionesH uen = uens.findOne(r.getUen());
        return RequisicionCompare.builder()
                .id(r.getIdRequisicion())
                .monedaUen(uen.getCurrency())
                .nombreUsuario(users.findOne(r.getUsuario()).getNombreUsuario())
                .lines(lineascom)
                .supliers(providerDetails)
                .build();
    }
    //</ERM#17475>

    @Autowired
    private ApprovalService service;

    @RequestMapping(value = "/obtenerPerfil", params = {"idUsuario", "idNotificacion"}, method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String obtenerPerfil(
            @RequestParam("idUsuario") String idUsuario,
            @RequestParam("idNotificacion") Integer idNotificacion
    ) {
        return this.service.obtenerPerfil(idUsuario, idNotificacion);
    }

    @RequestMapping(value = "/verificarPerfil", params = {"idUsuario", "idNotificacion", "vEsperado"}, method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public Boolean verificarPerfil(
            @RequestParam("idUsuario") String idUsuario,
            @RequestParam("vEsperado") String vEsperado,
            @RequestParam("idNotificacion") Integer idNotificacion
    ) {
        return this.service.verificarPerfil(idUsuario, idNotificacion, vEsperado);
    }

    @RequestMapping(value = "/aprobar", params = {"lang"}, method = RequestMethod.POST)
    @ResponseBody
    public Iterable<ApprovalResponse> aprobar(
            @RequestHeader(value = "modulo") String modulo,
            @RequestBody ApprovalRequest data,
            @RequestParam(value = "critical", required = false) boolean isCritical
    ) {
        log.debug("================Iniciando aprobacion de requisiciones =======================");
        boolean tieneFad = false;
        boolean estaAprobada = false;
        try {
            List<ApprovalResponse> response = Lists.newArrayList();
            for (int i = 0; i < data.getRequisiciones().size(); i++) {
                log.debug("Requisicion: " + data.getRequisiciones().get(i).getIdRequisicion());
                tieneFad = !fadService.getFadByIdReq(data.getRequisiciones().get(i).getIdRequisicion().intValue()).isEmpty();
                log.debug("tieneFad: " + tieneFad);
                if (tieneFad) {                    
                    service.validaLineasFad(data);//<T567460>
                    //Si es fad solo tomo una línea
                    List<LineaVO> lineas = data.getRequisiciones().get(i).getLineas();
                    lineas.subList(1, lineas.size()).clear();
                }
                /*for (int j = 0; j < data.getRequisiciones().get(i).getLineas().size(); j++) {
                    log.debug("--Linea: " + data.getRequisiciones().get(i).getLineas().get(j).getIdPartida());
                    estaAprobada = detalleRequisicionRepository.estaAprobada(data.getRequisiciones().get(i).getIdRequisicion(), data.getRequisiciones().get(i).getLineas().get(j).getIdPartida()) != 0;
                    if (estaAprobada) {
                        response.add(new ApprovalResponse(data.getRequisiciones().get(i).getIdRequisicion(),
                                data.getRequisiciones().get(i).getLineas().get(j).getIdPartida(),
                                false,
                                messages.getMessage("approvals.requi", null, LocaleContextHolder.getLocale()),
                                -1));
                    }
                }*/
            }
            /*if (estaAprobada) {
                return response;
            }*/
        } catch (Exception e) {
//            log.debug("Error al imprimir log de aprobacion");
            log.error(e.getMessage(), e);
        }
        List<ApprovalResponse> responses = service.aprobacion(data, isCritical, modulo);

        Map<Long, Set<Long>> aprobadas = responses.stream()
                .filter(ApprovalResponse::isAprobado)
                .collect(Collectors.groupingBy(ApprovalResponse::getRequisicion,
                        Collectors.mapping(ApprovalResponse::getLinea, Collectors.toSet())));

        data.getRequisiciones().removeIf(r -> !aprobadas.keySet().contains(r.getIdRequisicion()));
        data.getRequisiciones()
                .forEach(r -> r.getLineas().removeIf(l -> !aprobadas.get(r.getIdRequisicion()).contains(l.getIdPartida())));

        if (!data.getRequisiciones().isEmpty()) {
            /*new Thread() {
                @Override
                public void run() {*/
            try {
                //<T395493>
                service.guardaMailAprobacion(data, mailService);
                //</T395493>
            } catch (Exception e) {
                System.out.println("no se fue posible enviar la notificacion=> " + e.getLocalizedMessage());
                System.out.println(e);
            }
            /*}
            }.start();*/
        } else {
            log.debug("sin data para envio de emails");
        }

        log.debug("================Terminando aprobacion de requisiciones =======================");
        try {
            for (int k = 0; k < responses.size(); k++) {
                log.debug("Requisicion response: " + responses.get(k).getRequisicion() + "/"
                        + responses.get(k).getLinea() + ", aprobada = " + responses.get(k).isAprobado());
            }
            if (tieneFad) {
                this.saveComentariosFad(data);
            }
        } catch (Exception e) {
            log.debug("Error al imprimir log de aprobacion");
        }
        log.debug("============================Terminado=========================================");
        return responses;
    }

    private void saveComentariosFad(ApprovalRequest data) {
        for (int i = 0; i < data.getRequisiciones().size(); i++) {
            NvcTblFad fad = data.getRequisiciones().get(i).getFad();
            if (fad != null) {
                switch (data.getRequisiciones().get(i).getAprobacionEspecial()) {
                case "FADPR":
                    reqPorAprobarRepository.guardaComentarioFad(fad.getIdFad(), data.getRequisiciones().get(i).getAprobacionEspecial(), fad.getComentarioAdminProceso());
                    break;
                case "P":
                    reqPorAprobarRepository.guardaComentarioFad(fad.getIdFad(), data.getRequisiciones().get(i).getAprobacionEspecial(), fad.getComentarioAdminProceso());
                    break;
                case "FADAP":
                    reqPorAprobarRepository.guardaComentarioFad(fad.getIdFad(), data.getRequisiciones().get(i).getAprobacionEspecial(), fad.getComentarioAdminPlanta());
                    break;
                case "AP":
                    reqPorAprobarRepository.guardaComentarioFad(fad.getIdFad(), data.getRequisiciones().get(i).getAprobacionEspecial(), fad.getComentarioAdminPlanta());
                    break;
                }
            }
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getUenSuperAprobador", params = {"idUsuario"}, method = RequestMethod.GET)
    public Iterable<Uens> getUensSuperAprobador(String idUsuario) {
        return reqPorAprobarRepository.getUensSuperAprobador(idUsuario);
    }

    @ResponseBody
    @RequestMapping(value = "/getUensPreAprobador", params = {"idUsuario"}, method = RequestMethod.GET)
    public Iterable<Integer> getUensPreAprobador(String idUsuario) {
        return reqPorAprobarRepository.getUensPreAprobador(idUsuario);
    }

    @ResponseBody
    @Transactional
    @RequestMapping(value = "/editarRequiPreAprobacion", method = RequestMethod.POST)
    public ResponseEntity editarRequisicionPreAprobacion(@RequestBody RedicionRequisicionVO partida) {
        Integer estatus = reqPorAprobarRepository.editarRequisicionPreAprobacion(partida);
        return new ResponseEntity(estatus.equals(1) ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @RequestMapping(value = "/preaprobarReq", params = {"lang"}, method = RequestMethod.POST)
    @ResponseBody
    public Iterable<ApprovalResponse> preAprobarRequisicion(
            @RequestHeader(value = "modulo") String modulo,
            @RequestBody ApprovalRequest data
    ) {
        List<ApprovalResponse> responses = service.preaprobacion(data, modulo);
        Map<Long, Set<Long>> aprobadas = responses.stream()
                .filter(ApprovalResponse::isAprobado)
                .collect(Collectors.groupingBy(ApprovalResponse::getRequisicion,
                        Collectors.mapping(ApprovalResponse::getLinea, Collectors.toSet())));
        data.getRequisiciones().removeIf(r -> !aprobadas.keySet().contains(r.getIdRequisicion()));
        data.getRequisiciones()
                .forEach(r -> r.getLineas().removeIf(l -> !aprobadas.get(r.getIdRequisicion()).contains(l.getIdPartida())));
        return responses;
    }

    //<ERM#17422>
    @ResponseBody
    @RequestMapping(value = "/preaprobar", method = RequestMethod.POST)
    public ResponseEntity preAprobarRequisiciones(
            @RequestHeader(value = "modulo") String modulo,
            @RequestParam(value = "aprobador") String aprobador,
            @RequestBody List<RequisicionVO> requisiciones
    ) {
        String stringRequis = parsearRequisiciones(requisiciones);
        Integer estatus = reqPorAprobarRepository.preAprobarRequisciones(stringRequis, modulo, aprobador);
        //<RELEASE ARG>
        HttpStatus status = HttpStatus.CONFLICT;

        if (estatus.equals(1)) {
            status = HttpStatus.OK;
            try {
                this.mailService.notificarPorAprobar(requisiciones);
            } catch (Throwable e) {
                log.debug("----------------- no fue posible enviar los correo a los aprobadores ----------------------------");
                log.debug(e.getLocalizedMessage(), e);
            }

        }

        return new ResponseEntity(status);
        //</RELEASE ARG>
    }
    //</ERM#17422>

    @RequestMapping(value = "/getRolesUsuario", params = {"idUsuario"}, method = RequestMethod.GET)
    public Iterable<RolesPorUsuario> getRolesUsuario(String idUsuario) {
        System.out.println("- - - - - - - - - - - getRolesUsuario{ idUsuario=>" + idUsuario + " } - - - - -  - - - - - ");
        return rolRepository.findByUsuario(idUsuario);
    }

    @Autowired
    private BuyerReasonRepository buyerReason;

    @GetMapping("/reason/{req}/{line}")
    public BuyerReason razonDeComprador(@PathVariable("req") Long requisicion, @PathVariable("line") Long linea) {
        return buyerReason.getBuyerReason(requisicion, linea);
    }

    @ResponseBody
    @Transactional
    @RequestMapping(value = "/actualizaCuentaRequiCritica", method = RequestMethod.POST)
    public ResponseEntity updateCuentaRequiCritica(@RequestBody CuentasFamiliaCC cuenta) {
        System.out.println("---------------actualizaCuentaRequiCritica-----------------");
        int estatus = reqPorAprobarRepository.updateCuenta(
                cuenta.getIdRequisicion().longValue(),
                cuenta.getPartida().longValue(),
                cuenta.getSegmento1(),
                cuenta.getSegmento2(),
                cuenta.getSegmento3(),
                cuenta.getSegmento4(),
                cuenta.getSegmento5(),
                cuenta.getSegmento6(),
                cuenta.getSegmento7(),
                cuenta.getSegmento8(),
                cuenta.getIdCuenta()
        );

        return new ResponseEntity(estatus > 0 ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @ResponseBody
    @RequestMapping(value = "/puedeEditarPreaprobacion", params = {"idRequisicion"}, method = RequestMethod.GET)
    public Boolean puedeEditarPreaprobacion(@RequestParam("idRequisicion") Integer idRequisicion) {
        int total = this.reqPorAprobarRepository.countRequisicionCancApro(idRequisicion);
        return total == 0;
    }

    //<MDA_CONTRALOR>
    @GetMapping(value = "/presupuestos", params = {"uen", "cc", "periodo", "idioma"})
    public Iterable<? extends Budget> obtenerPresupuestos(Long uen, Long cc, String periodo, String idioma) {
        log.debug("uen:" + uen);
        log.debug("cc:" + cc);
        log.debug("periodo:" + periodo);
        log.debug("idioma:" + idioma);
        List<? extends Budget> result = categorias.getCategoriasByUenAndCcAndPeriodo(uen, cc, periodo, idioma);
        log.debug("Elementos encontrados=> " + result.size());
        return result;
    }

    //</MDA_CONTRALOR>    
    @RequestMapping(value = "getConfController/{idUsuario}/{idioma}/{periodo}", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<UenWithDefault> getConfController(@PathVariable String idUsuario, @PathVariable String idioma, @PathVariable String periodo) {
        List<UenWithDefault> uenByUser = uens.getUensActivasByUser(idUsuario);

        for (UenWithDefault uen : uenByUser) {
            if (!uen.isInteruen()) {
                String relaciones = "";
                Configuracion confMeses;
                Calendar calendario = Calendar.getInstance();
                calendario.setTime(new Date());
                Integer month = calendario.get(Calendar.MONTH) + 1;
                Integer mesInicio = month;
                Integer mesFin = month;
                Integer year = calendario.get(Calendar.YEAR);
                ResponseMobile respuesta = new ResponseMobile();
                respuesta.setCcsDestino(new ArrayList());
                respuesta.setCcsOrigen(new ArrayList());
                respuesta.setConfResp(configuracionRepository.findByUenName(uen.getId().intValue(), "PERMITIR_TRANSFERENCIAS_RESPONSABLE"));
                respuesta.setConfDel(configuracionRepository.findByUenName(uen.getId().intValue(), "PERMITIR_TRANSFERENCIAS_DELEGADO"));
                confMeses = configuracionRepository.findByUenName(uen.getId().intValue(), "PERMITIR_TRANSFERENCIAS_MESES");
                if (respuesta.getConfResp().getCondition().equals("Y")) {
                    relaciones = "'Resp'";
                }
                if (respuesta.getConfDel().getCondition().equals("Y")) {
                    relaciones = relaciones.length() != 0 ? relaciones + ",'Del'" : "'Del'";
                }
                if (relaciones.contains("Resp") || relaciones.contains("Del")) {
                    if ("Y".equals(confMeses.getCondition())) {
                        for (int i = 0; i < confMeses.getValues().size(); i++) {
                            Valores value = confMeses.getValues().get(i);
                            if ("Y".equals(value.getCondition()) && value.getCondition() != null) {
                                if ("PASADO".equals(value.getProperty())) {
                                    mesInicio = month - Integer.parseInt(value.getValue());
                                }

                                if ("FUTURO".equals(value.getProperty())) {
                                    mesFin = month + Integer.parseInt(value.getValue());
                                }
                            }
                        }
                        if (mesInicio < 1) {
                            mesInicio = 1;
                        }
                        if (mesFin > 12) {
                            mesFin = 12;
                        }
                    }
                    respuesta.setPeriodos(periodosRepository.findByPorRangoAnioMes(year, year, mesInicio, mesFin, Constants.getIdioma(idioma)));
                    Valores confTranferCc = configuracionRepository.findSingleValue(uen.getId().intValue(), "PERMITIR_TRANSFERENCIAS_CCS");
                    respuesta.setConfTransferCc(confTranferCc);
                    if (confTranferCc.getValue().equals("Y")) {
                        respuesta.setConfTransferOtrosCc(configuracionRepository.findByUenName(uen.getId().intValue(), "PERMITIR_TRANSFERENCIAS_OTROS_CCS"));
                        if (respuesta.getConfTransferOtrosCc().getCondition().equals("Y")) {
                            List<CentroCosto> otrosCc = centros.getAllByIdUenAndIdioma(uen.getId(), idioma);
                            List<CentroCostoUsuario> listOtrosCc = new ArrayList();
                            for (CentroCosto cc : otrosCc) {
                                CentroCostoUsuario otrosCCtem = new CentroCostoUsuario();
                                otrosCCtem.setCodigoCC(cc.getCodigo());
                                otrosCCtem.setNombreCC(cc.getNombre());
                                otrosCCtem.setCc(cc.getId());
                                otrosCCtem.setIdCC(cc.getId());
                                listOtrosCc.add(otrosCCtem);
                            }
                            respuesta.setCcsDestino(listOtrosCc);
                            respuesta.setCcsOrigen(miembros.getByUserUenRelations(idioma, idUsuario, relaciones, uen.getId().intValue()));
                        } else {
                            respuesta.setCcsDestino(miembros.getByUserUenRelations(idioma, idUsuario, relaciones, uen.getId().intValue()));
                            respuesta.setCcsOrigen(respuesta.getCcsDestino());
                        }
                    } else {
                        respuesta.setCcsDestino(miembros.getByUserUenRelations(idioma, idUsuario, relaciones, uen.getId().intValue()));
                        respuesta.setCcsOrigen(respuesta.getCcsDestino());
                    }
                }
                for (CentroCostoUsuario cc : respuesta.getCcsDestino()) {
                    cc.setBalance(getCategoriasByUenAndCcAndPeriodo(uen.getId(), cc.getIdCC(), periodo));

                    CentroCostoUsuario ccTem = respuesta.getCcsOrigen().stream().filter(x
                            -> x.getIdCC() != null
                            && x.getIdCC().equals(cc.getIdCC())).findAny().orElse(null);
                    if (ccTem != null) {
                        cc.setRelacion(ccTem.getRelacion());
                    }
                }
                uen.setRespuesta(respuesta);
            }
        }
        return uenByUser;
    }
}
