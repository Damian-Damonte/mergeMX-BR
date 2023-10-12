package com.metalsa.aprobacion.service;

import com.metalsa.aprobacion.model.Preaprobador;
import com.metalsa.aprobacion.model.Proyecto;
import com.metalsa.aprobacion.repository.PreaprobadorProyectoRepository;
import com.metalsa.aprobacion.repository.PreaprobadorRepository;
import com.metalsa.generales.model.Parametro;
import com.metalsa.generales.model.ParametroUenCia;
import com.metalsa.generales.service.ConfigurationService;
import com.metalsa.utils.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author APOOD9272
 */
@Service
@Log4j
public class PreaprobadorProyectoService {

    private final Short ACTIVE = 1;

    @Autowired
    private PreaprobadorProyectoRepository proyectoRepository;

    @Autowired
    private PreaprobadorRepository preaprobadorRepository;

    @Autowired
    private ConfigurationService config;

    @Autowired
    private MailNotificationService mailService;

    public List<Proyecto> findAllActive() {
        return this.proyectoRepository.findAllByActiveOrderByIdUen(this.ACTIVE);
    }

    public List<Proyecto> findByUen(Long idUen) {
        return this.proyectoRepository.findAllByIdUenAndActive(idUen, ACTIVE);
    }

    public List<Proyecto> findProyectosUsuario(String idUsuario) {
        return this.proyectoRepository.findProyectosByUser(idUsuario);
    }

    public List<Preaprobador> findPreaprobadores() {
        return preaprobadorRepository.findPreaprobadores();
    }

    public Preaprobador findPreaprobadorDefault(Long idUen) {
        return preaprobadorRepository.findPreaprobadorDefault(idUen);
    }

    public Proyecto findByIdProyecto(Long idProyecto) {
        return this.proyectoRepository.findOne(idProyecto);
    }

    public boolean isPreaprobador(String usuario) {

        //obtener parametro administrador de preaprobacion
        Parametro parameter = this.config.getParameterByName(Constants.ADMIN_PREAPROBACION);
        if (parameter != null) {

            //verificar si el usuario  es administrador en cualquier uen
            List<ParametroUenCia> uencia = this.config.findAllByIdParameterValue(parameter.getIdParametro(), usuario);
            if (uencia.isEmpty()) {

                //buscar parametro de aprobador default    
                parameter = this.config.getParameterByName(Constants.PREAPROBADOR_DEFAULT);
                if (parameter != null) {

                    //verificar si él es preaprobador default de alguna uen
                    //|| si él es preaprobador de algun proyecto
                    return this.config.findAllByIdParameterValue(parameter.getIdParametro(), usuario).size() > 0
                            || this.preaprobadorRepository.countProyectosUsuario(usuario) > 0;

                }
                return false;
            }
            return true;
        }
        return false;

    }

    public void eliminarPreaprobadoresPorProyecto(Long idProyecto, List<String> preaprobadores) {
        log.debug("- - - - - - - - - - eliminado aprobadores del proyecto - - - - - - - - - -");
        String users = null;
        if (null != preaprobadores) {
            if (preaprobadores.isEmpty()) {
                return;
            }
            users = String.join(",", preaprobadores);
            log.debug("users->" + users);
        }
        this.preaprobadorRepository.removerPreaprobador(idProyecto, users);
    }

    public void eliminarRolNoPreaprobadores() {
        log.debug("removiendo rol 'PREAPROBADOR PROYECTO' a los usuarios que ya no son preaprobadores");
        this.preaprobadorRepository.eliminaRolNoPreaprobador();
    }

    public void agregarRolPreaprobadorFaltante() {
        log.debug("- - - - - - - - - - agregando rol 'PREAPROBADOR PROYECTO' a los usuarios que no contiene el rol - - - - - - - - - -");
        Integer status = this.preaprobadorRepository.agregarRolPreaprobador();
        log.debug("status de operacion->" + status);
    }

    public void notificarNuevosPreaprobadores(List<Proyecto> proyectos) {
        log.debug("- - - - - - - - - - preparando datos para el envio de notificacion - - - - - - - - - -");
        Map<String, List<Proyecto>> preaprobadores = new HashMap<>();

        for (Proyecto proyecto : proyectos) {

            for (Preaprobador preaprobador : proyecto.getPreaprobadores()) {

                List<Proyecto> p = preaprobadores.getOrDefault(preaprobador.getIdUsuario(), new ArrayList<>());
                if (p.isEmpty()) {
                    preaprobadores.put(preaprobador.getIdUsuario(), p);
                }
                p.add(proyecto);
            }
        }

        boolean status = mailService.notificarPreaprobadoresProyecto(preaprobadores);
        log.debug("status de operacion->" + status);

    }

    public void asignarPreaprobadores(Proyecto proyecto) {
        // 0 = error 
        // 1 = asignacion exitosa

        List<String> us = proyecto.getPreaprobadores().stream().map(Preaprobador::getIdUsuario).collect(Collectors.toList());
        if (false == us.isEmpty()) {
            log.debug("------------- asignando preaprobadores --------------------------");

            String preaprobadores = String.join(",", us);
            log.debug("preaprobadores->" + preaprobadores);
            Integer status = this.preaprobadorRepository.asignarPreaprobadores(proyecto.getIdProyecto(), preaprobadores);
            log.debug("estatus de operacion - -> " + status);
        }
    }

}
