package com.metalsa.perfil.controller;

import com.metalsa.aprobacion.service.MailNotificationService;
import com.metalsa.generales.model.PerAllPeopleF;
import com.metalsa.generales.service.HeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import com.metalsa.perfil.pojo.PerfilRequest;
import com.metalsa.perfil.pojo.PerfilResponse;
import com.metalsa.perfil.pojo.UenWithCCDefault;
import com.metalsa.perfil.service.PerfilService;
import com.metalsa.recibos.repository.UserRecibosRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.RequestBody;
import com.metalsa.utils.Constants;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minidev.json.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author miguel.rdz
 *
 */
@RestController
@Api(value = "Perfil", tags = {"Perfil Service API"})
@RequestMapping(Constants.URI_API_PERFIL)
@Log4j
public class PerfilController {

    @Autowired
    private PerfilService perfil;
    @Autowired
    private MailNotificationService mailService;
    @Autowired
    private HeaderService headerService;
    @Autowired
    private UserRecibosRepository userRepository;

    @RequestMapping(value = "getDatosPerfil", method = RequestMethod.POST)
    @ResponseBody
    public PerfilResponse getDatosPerfil(@RequestBody PerfilRequest req) {
        PerfilResponse response = new PerfilResponse();
        try {
            if (req != null) {
                if (req.getPerfilActivo().equals("SPX_REQUISITOR")) {
                    response.setUens(perfil.getUensPorUsuario(req.getIdUsuario(), req.getIdIdioma()));
                    response.setUenDefault(perfil.getDefaultUenPorUsuario(req.getIdUsuario()));
                    response.setIdiomas(perfil.getIdiomas());
                } else if (req.getPerfilActivo().equals("COMPRADOR")) {
                    response.setMonedas(perfil.getMonedas());
                    response.setMonedaDefault(perfil.getMonedaDefault(req.getIdUsuario()));
                } else if (req.getPerfilActivo().equals("SPX_APROBADOR_REQUISICION")) {
                    response.setCcResponsable(perfil.getUserCcByRelacion(req.getIdUsuario(), req.getIdIdioma(), "Resp"));
                    response.setCcDelegado(perfil.getUserCcByRelacion(req.getIdUsuario(), req.getIdIdioma(), "Del"));
                }
                response.setEmailUser(perfil.getUsuario(req.getIdUsuario()).getEmail());
                response.setUnidadesTiempo(perfil.getUnidadesTiempo(req.getIdIdioma()));
                response.setOpciones(perfil.getOpcionesPerfil(req.getIdUsuario(), req.getIdIdioma()));
            }
        } catch (Exception e) {
            log.error("Error al obtener getDatosPerfil:" + e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "savePerfil", method = RequestMethod.POST)
    @ResponseBody
    public PerfilResponse savePerfil(@RequestBody PerfilRequest req) {
        PerfilResponse res = new PerfilResponse();
        try {
            if (req != null) {
                perfil.saveConfiguracion(req);
                perfil.saveNotificaciones(req);
            }
        } catch (Exception e) {
            log.error("Error al obtener savePerfil:" + e.getMessage());
        }
        return res;
    }

    @RequestMapping(value = "sendRolRequest", method = RequestMethod.POST)
    @ResponseBody
    public PerfilResponse sendRolRequest(@RequestBody PerfilRequest req) {
        PerfilResponse res = new PerfilResponse();
        try {
            if (req != null) {
                StringBuilder uensUsuarioStr = new StringBuilder();
                List<UenWithCCDefault> uens = perfil.getUensPorUsuario(req.getIdUsuario(), req.getIdIdioma());
                uens.forEach((uen) -> {
                    uensUsuarioStr.append(uen.getNombre()).append("|");
                });
                PerAllPeopleF empInfo = headerService.getEmpInfo(userRepository.findOne(req.getIdUsuario()).getIdEmpleado());
                if (mailService.sendRolRequest(req.getIdUsuario(), empInfo, uensUsuarioStr.toString(), req.getSolicitudRoles())) {
                    req.getSolicitudRoles().forEach((solicitud) -> {
                        perfil.insertRolPerfil(req.getIdUsuario(), solicitud);
                    });
                }
            }
        } catch (Exception e) {
            log.error("Error al obtener sendRolRequest:" + e.getMessage());
        }
        return res;
    }

    @RequestMapping(value = "sendUenRequest", method = RequestMethod.POST)
    @ResponseBody
    public PerfilResponse sendUenRequest(@RequestBody PerfilRequest req) {
        PerfilResponse res = new PerfilResponse();
        try {
            if (req != null) {
                StringBuilder uensUsuarioStr = new StringBuilder();
                List<UenWithCCDefault> uens = perfil.getUensPorUsuario(req.getIdUsuario(), req.getIdIdioma());
                uens.forEach((uen) -> {
                    uensUsuarioStr.append(uen.getNombre()).append("|");
                });
                PerAllPeopleF empInfo = headerService.getEmpInfo(userRepository.findOne(req.getIdUsuario()).getIdEmpleado());
                mailService.sendUenRequest(req.getIdUsuario(), empInfo, uensUsuarioStr.toString(), req.getSolicitudUens());
            }
        } catch (Exception e) {
            log.error("Error al obtener sendUenRequest:" + e.getMessage());
        }
        return res;
    }

    @RequestMapping(value = "/showGuiaPerfil/{idUsuario}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String showGuiaPerfil(@PathVariable("idUsuario") String idUsuario) {
        String jsonText = "";
        try {
            JSONObject obj = new JSONObject();
            obj.put("show", headerService.canShowGuide(idUsuario));
            StringWriter out = new StringWriter();
            obj.writeJSONString(out);
            jsonText = out.toString();
        } catch (IOException ex) {
            Logger.getLogger(PerfilController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jsonText;
    }

    @RequestMapping(value = "updateGuiaPerfil", method = RequestMethod.POST)
    @ResponseBody
    public PerfilResponse updateGuiaPerfil(@RequestBody PerfilRequest req) {
        PerfilResponse res = new PerfilResponse();
        try {
            if (req != null) {
                perfil.updateGuiaPerfil(req);
            }
        } catch (Exception e) {
            log.error("Error al obtener savePerfil:" + e.getMessage());
        }
        return res;
    }
}
