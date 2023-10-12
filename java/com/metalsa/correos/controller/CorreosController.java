package com.metalsa.correos.controller;

import com.metalsa.aprobacion.model.PlantillaCorreo;
import com.metalsa.aprobacion.repository.PlantillaCorreoRepository;
import com.metalsa.aprobacion.service.MailNotificationService;
import com.metalsa.correos.model.NvcTblTipoCorreo;
import com.metalsa.correos.pojo.NotificacionRequest;
import com.metalsa.correos.repository.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j;
import com.metalsa.utils.Constants;
import org.springframework.web.bind.annotation.GetMapping;
import com.metalsa.correos.repository.TipoCorreoRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author miguel.rdz
 *
 */
@RestController
@Api(value = "Motor", tags = {"Correos Service API"})
@RequestMapping(Constants.URI_API_CORREOS)
@Log4j
public class CorreosController {

    @Autowired
    private TipoCorreoRepository tipoCorreoRepository;
    @Autowired
    private PlantillaCorreoRepository correoRepository;
    @Autowired
    private NotificacionService notificacionService;
        @Autowired
    private MailNotificationService mailService;

    @GetMapping("/getTipos")
    Iterable<NvcTblTipoCorreo> getTiposCorreo() {
        return tipoCorreoRepository.findAllByOrderByIdTipoCorreo();
    }

    @GetMapping("/getByTipo/{idTipoCorreo}")
    Iterable<PlantillaCorreo> getCorreosByTipo(@PathVariable("idTipoCorreo") Integer idTipoCorreo) {
        return correoRepository.getByIdTipoCorreo(idTipoCorreo);
    }

    @RequestMapping(value = "updateNotificacion", method = RequestMethod.POST)
    @ResponseBody
    public boolean updateNotificacion(@RequestBody NotificacionRequest req) {
        try {
            if (req != null) {
                notificacionService.update(req);
            }
        } catch (Exception e) {
            log.error("Error en updateNotificacion:" + e.getMessage());
        }
        return true;
    }
    
    @RequestMapping(value = "testNotificacion", method = RequestMethod.POST)
    @ResponseBody
    public boolean testNotificacion(@RequestBody NotificacionRequest req) {
        try {
            if (req != null) {
                mailService.sendTestMail(req);
            }
        } catch (Exception e) {
            log.error("Error en updateNotificacion:" + e.getMessage());
        }
        return true;
    }
}
