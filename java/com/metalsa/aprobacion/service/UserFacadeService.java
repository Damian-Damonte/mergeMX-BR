package com.metalsa.aprobacion.service;

import com.metalsa.aprobacion.model.NotificacionHeader;
import com.metalsa.aprobacion.model.UenWithDefault;
import com.metalsa.aprobacion.repository.MensajeRequisicionRepository;
import com.metalsa.aprobacion.repository.OrganizacionesRepository;
//MDA_CONTRALOR
import com.metalsa.aprobacion.repository.RolesPorUsuarioRepository;
import com.metalsa.recibos.model.Requisitor;
import com.metalsa.utils.Constants;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

/**
 * Created by ruben.bresler on 05/07/2017.
 */
@CrossOrigin(maxAge = 3600)
@RestController
@Api(value = "aprobacion", tags = {"Aprobacion Service API"})
public class UserFacadeService {

    private OrganizacionesRepository orgs;
    private MensajeRequisicionRepository mensajes;
    
	//MDA_CONTRALOR
    @Autowired
    private RolesPorUsuarioRepository rolUsuarioRepository;
    
    @Autowired
    public UserFacadeService(OrganizacionesRepository orgs, MensajeRequisicionRepository mensajes) {
        this.orgs = orgs;
        this.mensajes = mensajes;
    }

    @RequestMapping(value = Constants.URI_API_USUARIOS + "/{user}/uens", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<UenWithDefault> getUensByUser(@PathVariable("user") String user) {
        return orgs.getUensByUser(user);
    }

    @RequestMapping(value = Constants.URI_API_USUARIOS + "/{user}/messages", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<NotificacionHeader> getConversations(@PathVariable("user") String usuario,
            @RequestParam(name = "lang", defaultValue = "ESA",
                    required = false) String idioma) {
        return mensajes.getProcNuevoHeader(usuario, idioma);
    }

    @RequestMapping(value = Constants.URI_API_USUARIOS + "/{user}/messages/count", method = RequestMethod.GET)
    @ResponseBody
    public Long getUnreadedConversations(@PathVariable("user") String usuario) {
        return (long) mensajes.getProcNuevoHeader(usuario, "ESA").stream()
                .filter(n -> !n.isLeido())
                .map(NotificacionHeader::getIdRequisicion)
                .collect(Collectors.toSet())
                .size();
    }

    @RequestMapping(value = Constants.URI_API_USUARIOS + "/{user}/uensactivas", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<UenWithDefault> getUensActivasByUser(@PathVariable("user") String user) {
        return orgs.getUensActivasByUser(user);
    }
    
    @RequestMapping(value = Constants.URI_API_USUARIOS + "/{user}/uensactivas/{proceso}", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<UenWithDefault> getUensActivasByUserRol(@PathVariable("user") String user, @PathVariable("proceso") Integer proceso) {
        return orgs.getUensActivasByUserRol(user, proceso);
    }
	
	//MDA_CONTRALOR
    @RequestMapping(value = Constants.URI_API_USUARIOS + "/getUsersByRol", params = {"idUen", "clave"}, method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Requisitor> getUsuersByRol(Integer idUen, String clave) {
//        return orgs.getUensActivasByUser(idUen);
        return rolUsuarioRepository.getUsersByRol(idUen, clave);
    }
}
