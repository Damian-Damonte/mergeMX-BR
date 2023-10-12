/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.inventarioFisico;

import com.google.gson.Gson;
import com.metalsa.almacen.pojo.Almacen;
<<<<<<< HEAD
import com.metalsa.almacen.service.DespachoService;
import com.metalsa.almacen.service.InventarioFisicoService;
import com.metalsa.aprobacion.model.Usuario;
import com.metalsa.contralor.model.RequestBodyConfiguration;
=======
import com.metalsa.almacen.service.InventarioFisicoService;
import com.metalsa.aprobacion.model.Usuario;
>>>>>>> mexico
import com.metalsa.utils.Constants;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.PostMapping;
=======
>>>>>>> mexico
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hector.gutierrez02
 */
@RestController
@RequestMapping(Constants.URI_API_INVENTARIO_FISICO)
public class inventarioFisicoController {

    @Autowired
    private InventarioFisicoService invFis;

    @RequestMapping(value = "/almacenesPorUen/{iduen}", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<Almacen> uensPorUsuarioRecibo(@PathVariable("iduen") int iduen) {
        return invFis.getAlmacenesPorUen(iduen);
    }
<<<<<<< HEAD
    
=======

    @RequestMapping(value = "/almacenesAll/", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<AlmacenInvFisRequest> getAlmacenes() {
        return invFis.getAlmacenes();
    }

>>>>>>> mexico
    @RequestMapping(value = "/usuariosActivos/", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<Usuario> getAllUserActive() {
        return invFis.geUsuariosActivos();
    }
<<<<<<< HEAD
    
=======

>>>>>>> mexico
    @RequestMapping(value = "/BlockInventarioFisico/", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<InventarioFisicoRequest> getAllBloackInventarioFisico() {
        return invFis.getAllBlockInventarioFisico();
    }
<<<<<<< HEAD
    
    @RequestMapping(value = "/InsertaBloqueoInventario", method = RequestMethod.POST)
    public @ResponseBody
    String InsertaBloqueoInventario(@RequestBody String listJson) {
        String result="";
        Gson gson = new Gson();
        Gson gsonResponce = new Gson();
        InventarioFisicoRequest[] listBlock = gson.fromJson(listJson, InventarioFisicoRequest[].class);
        for (InventarioFisicoRequest objList: listBlock)
        {
            result=invFis.insertBloqueoInventario(objList);
            if(!result.equals("OK"))
            {
                return gsonResponce.toJson(result);
            }
        }
        
        return gsonResponce.toJson(result);
    }
    
    @RequestMapping(value = "/EliminaBloqueoInventario", method = RequestMethod.POST)
    public @ResponseBody
    String EliminaBloqueoInventario(@RequestBody String listJson) {
        String result="";
        Gson gson = new Gson();
        Gson gsonResponce = new Gson();
        InventarioFisicoRequest[] listBlock = gson.fromJson(listJson, InventarioFisicoRequest[].class);
        for (InventarioFisicoRequest objList: listBlock)
        {
            result=invFis.EliminaBloqueoInventario(objList);
            if(!result.equals("OK"))
            {
                return gsonResponce.toJson(result);
            }
        }
        
=======

    @RequestMapping(value = "/InsertaBloqueoInventario", method = RequestMethod.POST)
    public @ResponseBody
    String InsertaBloqueoInventario(@RequestBody String listJson) {
        String result = "";
        Gson gson = new Gson();
        Gson gsonResponce = new Gson();
        InventarioFisicoRequest[] listBlock = gson.fromJson(listJson, InventarioFisicoRequest[].class);
        for (InventarioFisicoRequest objList : listBlock) {
            result = invFis.insertBloqueoInventario(objList);
            if (!result.equals("OK")) {
                return gsonResponce.toJson(result);
            }
        }

        return gsonResponce.toJson(result);
    }

    @RequestMapping(value = "/EliminaBloqueoInventario", method = RequestMethod.POST)
    public @ResponseBody
    String EliminaBloqueoInventario(@RequestBody String listJson) {
        String result = "";
        Gson gson = new Gson();
        Gson gsonResponce = new Gson();
        InventarioFisicoRequest[] listBlock = gson.fromJson(listJson, InventarioFisicoRequest[].class);
        for (InventarioFisicoRequest objList : listBlock) {
            result = invFis.EliminaBloqueoInventario(objList);
            if (!result.equals("OK")) {
                return gsonResponce.toJson(result);
            }
        }

        return gsonResponce.toJson(result);
    }

    @RequestMapping(value = "/ProgramarBanner", method = RequestMethod.POST)
    public @ResponseBody
    String ProgramaBanner(@RequestBody String listJson) {
        Gson gsonResponce = new Gson();
        String result = invFis.insertConfigBanner(listJson);
        return gsonResponce.toJson(result);
    }
    
    @RequestMapping(value = "/getConfigBanner", method = RequestMethod.GET)
    public @ResponseBody
    String getConfigBanner() {
        Gson gsonResponce = new Gson();
        String result = invFis.getConfigBanner();
>>>>>>> mexico
        return gsonResponce.toJson(result);
    }
}
