package com.metalsa.aprobacion.service;

import com.metalsa.aprobacion.model.*;
import com.metalsa.aprobacion.repository.ProyectoRepository;
import com.metalsa.aprobacion.repository.UsuarioPreAprobadorRepository;
import com.metalsa.core.model.Periodos;
import com.metalsa.core.repository.PeriodosRepository;
import com.metalsa.generales.model.ParametroUenCia;
import com.metalsa.generales.service.ConfigurationService;
import com.metalsa.utils.Constants;
import java.util.ArrayList;//<RELEASE ARG/>
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;//<RELEASE ARG/>
import org.springframework.transaction.annotation.Transactional;//<RELEASE ARG/>
import java.util.Objects;

/**
 * @author APOOD9272
 */
@RestController
@RequestMapping(Constants.URI_API_PROYECTO)
@CrossOrigin
@Log4j
public class ProyectoController {

    private static final String ADMIN_PREAPROBACION = "PRE_APPROVAL_ADMIN"; // esto esta mal etiquetado en la BD
    //<RELEASE ARG>
    @Autowired
    private ProyectoRepository proyectos;

    @Autowired
    private UsuarioPreAprobadorRepository preAprobadores;

    @Autowired
    private PreaprobadorProyectoService preaprobacionService;
    //</RELEASE ARG>

    @Autowired
    private PeriodosRepository periodosRepository;

    private ConfigurationService config;

    public ProyectoController(ProyectoRepository proyectos, UsuarioPreAprobadorRepository preAprobadores, ConfigurationService config) {
        this.proyectos = proyectos;
        this.preAprobadores = preAprobadores;
        this.config = config;
    }

    @RequestMapping(value = "/getProyectosUen", method = RequestMethod.GET)
    public Iterable<OaProyectos> getProyectosUen(
            @RequestParam("uen") Integer uen
    ) {
        return this.proyectos.findByIdUen(uen);
    }

    @ResponseBody
    @RequestMapping(value = "/getProyectos", method = RequestMethod.GET)
    public Iterable<OaProyectos> getProyectosUenUsuario(@RequestParam(name = "idUsuario") String idUsuario, @RequestParam(name = "idUen") Integer idUen) {
        return proyectos.getProyectosUenUsuario(idUsuario, idUen);
    }

    @RequestMapping(value = "/getTareasProyectos", method = RequestMethod.GET)
    public Iterable<OaProyectoTarea> getTareasProyecto(@RequestParam(name = "idProyecto") Long idProyecto) {
        return proyectos.getTareasProyecto(idProyecto);
    }

    @RequestMapping(value = "/getErogacionesProyectos", method = RequestMethod.GET)
    public Iterable<OaProyectoGastoCuenta> getErogacionesProyecto(
            @RequestParam(name = "idProyecto") Long idProyecto,
            @RequestParam(name = "idTarea") Long idTarea,
            @RequestParam(name = "tipo") String tipo,
            @RequestParam(name = "reqAlm") String reqAlm
    ) {
        return proyectos.getErogacionesProyecto(idProyecto, idTarea, tipo, reqAlm);
    }

    @RequestMapping(value = "/getCuentasProyectos", method = RequestMethod.GET)
    public Iterable<OaProyectoCuenta> getCuentasProyecto(
            @RequestParam(name = "idProyecto") Long idProyecto,
            @RequestParam(name = "idTarea") Long idTarea,
            @RequestParam(name = "tipoErogacion") String tipoErogacion,
            @RequestParam(name = "lenguaje") String lenguaje
    ) {
        return proyectos.getCuentasProyecto(idProyecto, idTarea, tipoErogacion, lenguaje);
    }

    @RequestMapping(value = "/getAprobadorProyecto", params = {"idProyecto"}, method = RequestMethod.GET)
    public String getAprobadorProyecto(Long idProyecto) {
        String usuario = proyectos.getAprobadorProyecto(idProyecto);
        if (null != usuario) {
            usuario = "\"" + usuario + "\"";
        }

        String aprobador = "{\"aprobador\":" + usuario + "}";
        return aprobador;
    }

    @GetMapping("/preaprobadores/{uen}")
    public Iterable<UsuarioPreAprobador> getUsuariosPreAprobadores(@PathVariable("uen") Long uen) {
        return preAprobadores.findAllByIdUenOrderByIdProyecto(uen);
    }

    //<RELEASE ARG>
    @GetMapping("/preaprobacion/isPreaprobador")
    public boolean isPreaprobador(@RequestParam("usuario") String usuario) {
        return this.preaprobacionService.isPreaprobador(usuario);
    }

    @GetMapping("/preaprobacion/getproyectos")
    public Iterable<Proyecto> getProyectos(@RequestParam("uen") Long uen) {
        return this.preaprobacionService.findByUen(uen);
    }

    @GetMapping("/preaprobacion/getproyecto")
    public Proyecto getProyecto(@RequestParam("proyecto") Long proyecto) {
        return this.preaprobacionService.findByIdProyecto(proyecto);
    }

    @GetMapping("/preaprobacion/getpreaprobadores")
    public List<Preaprobador> getPreaprobadores() {
        return this.preaprobacionService.findPreaprobadores();
    }

    @GetMapping("/preaprobacion/getpreaprobador/default/{uen}")
    public Preaprobador getPreaprobadorDefault(Long uen) {
        return this.preaprobacionService.findPreaprobadorDefault(uen);
    }

    @PostMapping("/preaprobacion/setpreaprobadores/{uen}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Transactional
    public void setPreparobadores(@PathVariable("uen") Long uen, @RequestBody List<Proyecto> proyectos) {

        for (Proyecto proyecto : proyectos) {
            log.debug("id_proyecto->" + proyecto.getIdProyecto() + ",  nombre_proyecto-> " + proyecto.getNombreProyecto());

            if (proyecto.getPreaprobadores().isEmpty()) {
                this.preaprobacionService.eliminarPreaprobadoresPorProyecto(proyecto.getIdProyecto(), null);
            } else {

                List<String> toDelete = new ArrayList<>();

                Proyecto p = this.preaprobacionService.findByIdProyecto(proyecto.getIdProyecto());
                for (Preaprobador preaprobador : p.getPreaprobadores()) {

                    //si el preaprobador ya no fue seleccionado como preaprobador
                    if (false == proyecto.getPreaprobadores().contains(preaprobador)) {
                        toDelete.add(preaprobador.getIdUsuario()); //remover este preaprobador
                    } else {
                        //si se encuentra, entonces este usuario ya no sera agregado
                        proyecto.getPreaprobadores().remove(preaprobador);
                    }
                }

                //asignar preaprobadores al proyecto
                this.preaprobacionService.asignarPreaprobadores(proyecto);

                this.preaprobacionService.eliminarPreaprobadoresPorProyecto(proyecto.getIdProyecto(), toDelete);

            }
        }

        //agregar los rol de preaprobador a los usuarios que son preaprobadores
        //pero no contiene el rol
        this.preaprobacionService.agregarRolPreaprobadorFaltante();

        new Thread() {
            @Override
            public void run() {

                //eliminar roles de los usuario que ya no son preaprobadores
                preaprobacionService.eliminarRolNoPreaprobadores();

                //enviar notificacion a los preaprobadores
                preaprobacionService.notificarNuevosPreaprobadores(proyectos);
            }
        }.start();

    }
    //</RELEASE ARG>

    @PostMapping("/preaprobadores")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void asignarUsuarioPreAprobador(@RequestBody List<UsuarioPreAprobador> permisos) {
        permisos.forEach(this::asignarUsuarioAPreAprobacion);
    }

    @GetMapping("/checkPreAprobacion")
    public boolean userHasPreApproval(@RequestParam("user") String user, @RequestParam("uen") Long uen) {
        ParametroUenCia admin = config.getParametro(uen, ADMIN_PREAPROBACION);
        log.debug(admin);
        return admin != null && user.equalsIgnoreCase(admin.getValor())
                || preAprobadores.findAllByIdUenOrderByIdProyecto(uen).stream()
                        .filter(Objects::nonNull)
                        .peek(log::debug)
                        .anyMatch(a -> user.equalsIgnoreCase(a.getIdUsuario()));
    }

    private void asignarUsuarioAPreAprobacion(UsuarioPreAprobador p) {
        log.debug("asignar preaprobacion: " + preAprobadores.asignarUsuario(p.getIdUen(), p.getIdProyecto(), p.getIdUsuario()));
    }

    @GetMapping("/getProyectsByUser")
    public Iterable<PresupuestoProyecto> getProyectosByIdUsuario(@RequestParam("idUsuario") String idUsuario) {
        log.debug(" **** getProyectosByIdUsuario *** idUsuario:" + idUsuario);
        List<PresupuestoProyecto> pProyectos = null;
        pProyectos = proyectos.getProyectosByIdUsuario(idUsuario);
        return pProyectos;
    }

    @GetMapping("/getProyectBudgetById")
    public Iterable<PresupuestoProyecto> getProyectBudgetById(@RequestParam("idProyecto") Integer idProyecto) {
        log.debug(" **** getProyectBudgetById *** idProyecto:" + idProyecto);
        List<PresupuestoProyecto> pProyectos = null;
        pProyectos = proyectos.getProyectBudgetById(idProyecto);
        return pProyectos;
    }

    @GetMapping(path = "/periodos/desde") //, params = {"idioma", "idProyecto"})
    public Iterable<Periodos> getPeriodoProyDesde(@RequestParam("idioma") String idioma, @RequestParam("idProyecto") Integer idProyecto) {
        log.debug(" **** getPeriodoProyDesde *** ");
        return periodosRepository.findPeriodByProyectFrom(idioma, idProyecto);
    }

}
