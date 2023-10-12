/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.compradorCC.controlller;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.metalsa.aprobacion.model.NvcTblOrganizacionesH;
import com.metalsa.aprobacion.repository.CentroCostoRepository;
import com.metalsa.compradorCC.pojo.CategoriaPojo;
import com.metalsa.compradorCC.pojo.CompradorCcPojo;
import com.metalsa.compradorCC.pojo.DatosTabla;
import com.metalsa.compradorCC.pojo.ExcelComprador;
import com.metalsa.compradorCC.pojo.MessageRest;
import com.metalsa.compradorCC.pojo.RequestFiltros;
import com.metalsa.compradorCC.pojo.ResponseCombos;
import com.metalsa.compradorCC.service.CompradorCcService;
import com.metalsa.compradorCC.pojo.UenPojo;
import com.metalsa.finanzas.model.AdjuntoPojo;
import com.metalsa.utils.Constants;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
// el id_comprador se cambia a id ya que en el combo que esta dentro de la tabla marca
//error si no corresponde al nombre id 
@RestController
@RequestMapping(Constants.URI_API_COMPRADORCC)
@Log4j
public class CompradorCcController {

    @Autowired
    private CentroCostoRepository centros;

    @Autowired
    private CompradorCcService compradorCcService;

    private MessageRest messages;

    private final ExcelComprador excel = new ExcelComprador();

    @RequestMapping("getUenCc/{idUsuario}")
    @ResponseBody
    public Iterable<NvcTblOrganizacionesH> getUenCc(@PathVariable String idUsuario) {         
        Iterable<NvcTblOrganizacionesH> listUensCc = compradorCcService.getUensCc(idUsuario);
        
        return listUensCc;
    }

    @RequestMapping(value = "getDatos", method = RequestMethod.POST)
    @ResponseBody
    public ResponseCombos getCcByUen(@RequestBody UenPojo uen) {
        ResponseCombos json = new ResponseCombos();
        try {
            if (uen != null) {
                //MDA_REPORTES_FINANZAS
                json.setListCc(centros.getAllByIdUenAndIdioma(uen.getId(),uen.getIdioma()));
                json.setListCompradorCc(compradorCcService.getBuyerCc(uen.getId()));
                json.setListAdministradorCc(compradorCcService.getAdministrador(uen.getId()));
                json.setListPrevComprador(compradorCcService.getPrevComprador(uen.getId()));
                json.setListAllComprador(compradorCcService.getAllComprador(uen.getId())); //getCacheComprador trae todos los usuarios que son compradores
                json.setResponsableConfig(compradorCcService.getAdministradorPorUen(uen.getId()));
            }
        } catch (Exception e) {
            log.error("Error al obtener los centros de costo:" + e.getMessage());
        }
        return json;
    }

    @RequestMapping(value = "getDatosByfiltros", method = RequestMethod.POST)
    @ResponseBody
    public Iterable<DatosTabla> getDatosByfiltros(@RequestBody String json) throws IOException {
        Gson gson;
        RequestFiltros requestFiltros;
        List<DatosTabla> datosTabla = null;
        try {
            gson = new Gson();
            requestFiltros = gson.fromJson(json, RequestFiltros.class);
            datosTabla = compradorCcService.getDatosByFiltros(requestFiltros);
        } catch (JsonSyntaxException e) {
            log.error("Error al obtener los datos" + e.getMessage());
        }
        return datosTabla;
    }

    @RequestMapping(value = "saveDatosCompradorCc", method = RequestMethod.POST)
    @ResponseBody
    public MessageRest saveDatosCompradorCc(@RequestBody List<DatosTabla> datosTabla) throws IOException {
        try {
            messages = new MessageRest();
            compradorCcService.saveDatosCompradorCc(datosTabla);
            messages.setMenssage("Se ha guardado exitosamente");
        } catch (Exception e) {
            log.error("Error al obtener los datos" + e.getMessage());
            messages.setMenssage("Error al guardar");
            messages.setError(e.getMessage());
            return messages;
        }
        return messages;
    }

    @RequestMapping(value = "getDatosPrevAdm", method = RequestMethod.POST)//servicio para actualizar los combos despues de guardar combo comprador previo
    @ResponseBody                                                           // combo administradores, combo cache para el combo del header y del row de la tabla 
    public ResponseCombos getDatosPrevAdm(@RequestBody UenPojo uen) {
        ResponseCombos json = new ResponseCombos();
        try {
            if (uen != null) {
                json.setListCompradorCc(compradorCcService.getBuyerCc(uen.getId()));
                json.setListAdministradorCc(compradorCcService.getAdministrador(uen.getId()));
                json.setListPrevComprador(compradorCcService.getPrevComprador(uen.getId()));
                json.setListAllComprador(compradorCcService.getAllComprador(uen.getId()));
            }
        } catch (Exception e) {
            log.error("Error al obtener los centros de costo:" + e.getMessage());
        }
        return json;
    }

    @RequestMapping(value = "downloadPlantilla", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<InputStreamResource> downloadPlantilla(@RequestBody String json) throws IOException {
        Gson gson;
        RequestFiltros requestFiltros;
        List<DatosTabla> datosTabla;
        List<CompradorCcPojo> listComprador;
        List<CompradorCcPojo> listUser;
        String idioma;
        ByteArrayInputStream in = null;
        HttpHeaders headers = null;
        try {
            gson = new Gson();
            requestFiltros = gson.fromJson(json, RequestFiltros.class);
            if (requestFiltros.getTipoFiltro() != null && requestFiltros.getTipoFiltro() == 2) { // 2 complete filter 
                requestFiltros.setModelCompradorXcC(null);
                requestFiltros.setModelAdministradorCc(null);
                requestFiltros.setModelListaCc(null);
                requestFiltros.setModelPrevComprador(null);
                requestFiltros.setFechaInicio(null);
                requestFiltros.setFechaFin(null);

            }
            listComprador = compradorCcService.getBuyerCc(requestFiltros.getModelListaUensCc().get(0).getId());
            listUser = compradorCcService.getAllComprador(requestFiltros.getModelListaUensCc().get(0).getId());  // todos los usuarios que son compradores
            datosTabla = compradorCcService.getDatosByFiltros(requestFiltros);
            idioma = requestFiltros.getModelListaUensCc().get(0).getIdioma();
            in = excel.creaPlantillaCc(datosTabla, listComprador, listUser, idioma, requestFiltros,null); // donde null es listCategoria, aqui no se usa por lo tanto es null
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
    }

    @RequestMapping(value = "leerPlantillaCompradores", method = RequestMethod.POST)
    @ResponseBody
    public Iterable<DatosTabla> leerPlantillaCompradores(@RequestBody AdjuntoPojo data) throws IOException {
        List<DatosTabla> datosTabla = excel.leerExcelComprador(data);

        return datosTabla;
    }

    /* Pestaña 2 **********************************************/
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
                json.setListCompradorFam(compradorCcService.getCompradorFam(uen.getId()));
                json.setListAdministradorCc(compradorCcService.getAdministradorFam(uen.getId()));
                json.setListPrevComprador(compradorCcService.getPrevCompradorFam(uen.getId()));
                json.setListCategoria(compradorCcService.getDatosByFiltrosFam(requestFiltros));
                json.setListAllComprador(compradorCcService.getAllComprador(uen.getId()));
                json.setResponsableConfig(compradorCcService.getAdministradorPorUen(uen.getId()));
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
            listCategoria = compradorCcService.getDatosByFiltrosFam(requestFiltros);
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
            compradorCcService.saveDatosFamilias(datosTabla.getListDc());
            messages.setMenssage("Se ha guardado exitosamente");
        } catch (Exception e) {
            log.error("Error al obtener los datos" + e.getMessage());
            messages.setMenssage("Error al guardar");
            messages.setError(e.getMessage());
            return messages;
        }
        return messages;
    }

    @RequestMapping(value = "downloadPlantillaFam", method = RequestMethod.POST)
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

            listComprador = compradorCcService.getCompradorFam(requestFiltros.getModelListaUensCc().get(0).getId());
            listUser = compradorCcService.getAllComprador(requestFiltros.getModelListaUensCc().get(0).getId());  // todos los usuarios que son compradores
            listCategoria = compradorCcService.getDatosByFiltrosFam(requestFiltros);
            idioma = requestFiltros.getModelListaUensCc().get(0).getIdioma();
            in = excel.creaPlantillaCc(null, listComprador, listUser, idioma, requestFiltros,listCategoria);
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
    }
}
