/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.admonCategorias.controlller;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.metalsa.admonCategorias.pojo.RequestCategorias;
import com.metalsa.admonCategorias.service.AdmonCategoriasService;
import com.metalsa.aprobacion.model.NvcTblOrganizacionesH;
import com.metalsa.compradorCC.pojo.CategoriaPojo;
import com.metalsa.compradorCC.pojo.MessageRest;
import com.metalsa.compradorCC.pojo.RequestFiltros;
import com.metalsa.compradorCC.pojo.ResponseCombos;
import com.metalsa.compradorCC.pojo.UenPojo;
import com.metalsa.compradorCC.service.CompradorCcService;
import com.metalsa.core.model.NvcTblConfFam;
import com.metalsa.core.repository.ConfFamRepository;
import com.metalsa.utils.Constants;
import io.swagger.annotations.Api;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jose.Jimemez07
 */
@RestController
@Api(value = "conf_fam", tags = {"Configuracion de Familias Service API"})
@RequestMapping(Constants.URL_CATEGORIAS)
@Log4j
public class ConfFamController {

    @Autowired
    private ConfFamRepository confFamRepository;

    @Autowired
    private AdmonCategoriasService admonCategoriasService;

    @Autowired
    private CompradorCcService compradorCcService;

    private MessageRest messages;

    @RequestMapping(value = "getUenCc", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<NvcTblOrganizacionesH> getUenCc() {
        Iterable<NvcTblOrganizacionesH> listUensCc = admonCategoriasService.getUensCc();

        return listUensCc;
    }

    @RequestMapping(value = "/{id_conf}", method = RequestMethod.GET)
    public @ResponseBody
    NvcTblConfFam getById(@PathVariable("id_conf") int idConf) {
        return confFamRepository.findOne(idConf);
    }

    @RequestMapping(value = "getDatosByfiltrosFam", method = RequestMethod.POST)
    @ResponseBody
    public Iterable<CategoriaPojo> getDatosByfiltrosFam(@RequestBody String json) throws IOException {
        Gson gson;
        RequestFiltros requestFiltros;
        List<CategoriaPojo> listCategoria = null;
        try {
            gson = new Gson();
            requestFiltros = gson.fromJson(json, RequestFiltros.class);
            listCategoria = admonCategoriasService.getDatosByFiltros(requestFiltros);
        } catch (JsonSyntaxException e) {
            log.error("Error al obtener los datos" + e.getMessage());
        }
        return listCategoria;
    }

    @RequestMapping(value = "saveConfiguracionFamilia", method = RequestMethod.POST)
    @ResponseBody
    public MessageRest saveDatos(@RequestBody String json) throws IOException {
        Gson gson;
        try {
            gson = new Gson();
            RequestCategorias requestCategorias = gson.fromJson(json, RequestCategorias.class);
            messages = new MessageRest();
            admonCategoriasService.saveDatosFamilia(requestCategorias.getConfFamPojoList(), requestCategorias.getIdUsuario());
            messages.setMenssage("Se ha guardado exitosamente");
        } catch (Exception e) {
            log.error("Error al obtener los datos" + e.getMessage());
            messages.setMenssage("Error al guardar");
            messages.setError(e.getMessage());
            return messages;
        }
        return messages;
    }

    @RequestMapping(value = "saveDatosComentarios", method = RequestMethod.POST)
    @ResponseBody
    public MessageRest saveDatosComentarios(@RequestBody String json) throws IOException {
        Gson gson;
        try {
            gson = new Gson();
            RequestCategorias requestCategorias = gson.fromJson(json, RequestCategorias.class);
            messages = new MessageRest();
            admonCategoriasService.saveDatosComentarios(requestCategorias.getConfFamPojoList());
            messages.setMenssage("Se ha guardado exitosamente");
        } catch (Exception e) {
            log.error("Error al obtener los datos" + e.getMessage());
            messages.setMenssage("Error al guardar");
            messages.setError(e.getMessage());
            return messages;
        }
        return messages;
    }

    @RequestMapping(value = "getDatosComboFam", method = RequestMethod.POST)
    @ResponseBody
    public ResponseCombos getDatosComboFam(@RequestBody UenPojo uen) throws IOException {
        ResponseCombos json = null;

        RequestFiltros requestFiltros = new RequestFiltros();
        requestFiltros.setModelListaUensCc(new ArrayList<>());
        requestFiltros.getModelListaUensCc().add(uen);
        try {
            if (uen != null) {
                json = new ResponseCombos();
                json.setListAdministradorCc(admonCategoriasService.getAdministradorFam(uen.getId()));
                json.setListCategoria(compradorCcService.getDatosByFiltrosFam(requestFiltros));
            }
        } catch (Exception e) {
            log.error(e);
        }

        return json;
    }
}
