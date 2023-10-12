/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.asignacionFamilias.controller;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.metalsa.aprobacion.model.NvcTblOrganizacionesH;
import com.metalsa.compradorCC.pojo.RequestFiltros;
import com.metalsa.compradorCC.pojo.ResponseCombos;
import com.metalsa.compradorCC.pojo.UenPojo;
import com.metalsa.asignacionFamilias.service.AsignacionFamiliasService;
import com.metalsa.compradorCC.pojo.CategoriaPojo;
import com.metalsa.compradorCC.pojo.DatosTabla;
import com.metalsa.compradorCC.pojo.MessageRest;
import com.metalsa.compradorCC.service.CompradorCcService;
import com.metalsa.utils.Constants;
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
 * @author jose.jimenez07
 */
@RestController
@RequestMapping(Constants.URI_API_ASIGNACION_FAMILIAS)
@Log4j
public class AsignacionFamiliasController {

    @Autowired
    private AsignacionFamiliasService asignacionFamilias;
    @Autowired
    private CompradorCcService compradorCcService;

    private MessageRest messages;

    @RequestMapping("getUenCc/{idUsuario}")
    @ResponseBody
    public Iterable<NvcTblOrganizacionesH> getUenCc(@PathVariable String idUsuario) {
        Iterable<NvcTblOrganizacionesH> listUensCc = compradorCcService.getUensCc(idUsuario);

        return listUensCc;
    }

    /* Pestaña 2 **********************************************/
    @RequestMapping(value = "getDatosComboFam", method = RequestMethod.POST)
    @ResponseBody
    public ResponseCombos getDatosComboFam(@RequestBody String listJson) throws IOException {
        ResponseCombos json = null;
        Gson gson = new Gson();
        RequestFiltros requestFiltros = new RequestFiltros();
        UenPojo uen = gson.fromJson(listJson, UenPojo.class);
        requestFiltros.setModelListaUensCc(new ArrayList<>());
        requestFiltros.getModelListaUensCc().add(uen);
        requestFiltros.setTipoAprobacion("".equals(uen.getTipoAprobacion()) ? "" : uen.getTipoAprobacion().toUpperCase());
        try {
            if (uen != null) {
                json = new ResponseCombos();
                json.setListCompradorFam(asignacionFamilias.getCompradorFam(uen.getId(), requestFiltros.getTipoAprobacion()));
                json.setListAdministradorCc(asignacionFamilias.getAdministradorFam(uen.getId(), requestFiltros.getTipoAprobacion()));
                json.setListPrevComprador(asignacionFamilias.getPrevCompradorFam(uen.getId(), requestFiltros.getTipoAprobacion()));
                json.setListCategoria(asignacionFamilias.getDatosByFiltrosFam(requestFiltros));
                json.setListAllComprador(asignacionFamilias.getAllComprador(uen.getId(), requestFiltros.getTipoAprobacion()));
                json.setResponsableConfig(asignacionFamilias.getAdministradorPorUen(uen.getId(), requestFiltros.getTipoAprobacion()));
            }
        } catch (Exception e) {
            log.error(e);
        }

        return json;
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
            listCategoria = asignacionFamilias.getDatosByFiltrosFam(requestFiltros);
        } catch (JsonSyntaxException e) {
            log.error("Error al obtener los datos" + e.getMessage());
        }
        return listCategoria;
    }

    @RequestMapping(value = "saveDatosFamilias", method = RequestMethod.POST)
    @ResponseBody
    public MessageRest saveDatosFamilias(@RequestBody String json) throws IOException {
        try {
            Gson gson = new Gson();
            DatosTabla datosTabla;
            messages = new MessageRest();
            datosTabla = gson.fromJson(json, DatosTabla.class);
            asignacionFamilias.saveDatosFamilias(datosTabla.getListDc(), datosTabla.getTipoAprobacion());
            messages.setMenssage("Se ha guardado exitosamente");
        } catch (Exception e) {
            log.error("Error al obtener los datos" + e.getMessage());
            messages.setMenssage("Error al guardar");
            messages.setError(e.getMessage());
            return messages;
        }
        return messages;
    }

    /*@RequestMapping(value = "downloadPlantillaFam", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<InputStreamResource> downloadPlantillaFam(@RequestBody String json) throws IOException {
        Gson gson;
        RequestFiltros requestFiltros;
        List<CompradorCcPojo> listComprador;
        List<CompradorCcPojo> listUser;
        String idioma;
        ByteArrayInputStream in = null;
        HttpHeaders headers = null;
        List<CategoriaPojo> listCategoria;
        try {
            gson = new Gson();
            requestFiltros = gson.fromJson(json, RequestFiltros.class);
            if (requestFiltros.getTipoFiltro() != null && requestFiltros.getTipoFiltro() == 2) { // 2 complete filter 
                requestFiltros.setListIdCategoria(null);
                requestFiltros.setListIdFamilia(null);
                requestFiltros.setListIdSubFamilia(null);
                requestFiltros.setModelCompradorFam(null);
                requestFiltros.setModelAdministradorFam(null);
                requestFiltros.setModelPrevCompradorFam(null);
                requestFiltros.setFechaInicioFam(null);
                requestFiltros.setFechaFinFam(null);
            }

            listComprador = asignacionFamilias.getCompradorFam(requestFiltros.getModelListaUensCc().get(0).getId(), requestFiltros.getTipoAprobacion());
            listUser = asignacionFamilias.getAllComprador(requestFiltros.getModelListaUensCc().get(0).getId(), requestFiltros.getTipoAprobacion());  // todos los usuarios que son compradores
            listCategoria = asignacionFamilias.getDatosByFiltrosFam(requestFiltros);
            idioma = requestFiltros.getModelListaUensCc().get(0).getIdioma();
            in = null;//excel.creaPlantillaCc(null, listComprador, listUser, idioma, requestFiltros,listCategoria);
            headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=plantilla.xlsx");
            headers.add("Content-Type", "application/xlsx");

        } catch (JsonSyntaxException e) {
            log.error("Error al obtener los datos" + e.getMessage());
        }
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }*/
}
