/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.utilerias.controller;

import com.google.gson.Gson;
import com.metalsa.utilerias.model.ClasificacionArbol;
import com.metalsa.utilerias.pojo.AprobarExamenPojo;
import com.metalsa.utilerias.pojo.MyHDRowSetPojo;
import com.metalsa.utilerias.service.UtileriasService;
import com.metalsa.utils.Constants;
import java.util.Iterator;
import java.util.List;
import lombok.extern.log4j.Log4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author jose.jimenez07
 */
@RestController
@RequestMapping(Constants.URI_API_UTILERIAS)
@Log4j
public class UtileriasController {

    @Autowired
    UtileriasService utileriasService;
    
    @Value("${ml.url.azure}")
    private String azureUrl;
    @Value("${ml.token}")
    private String token;

    @RequestMapping(value = "getReclasificacion", method = RequestMethod.POST)
    @ResponseBody
    public String getReclasificacion(@RequestBody List<String> palabras) {
        String palabraJson = "";
        List<ClasificacionArbol> clasificacionArbol;
        JSONObject listClasificacionArbol = new JSONObject();
        String requestBody = "{\n"
                + "  \"Inputs\": {\n"
                + "    \"input1\": [\n"
                + "         [palabrasJson]\n"
                + "      ]\n"
                + "  },\n"
                + "  \"GlobalParameters\": {}\n"
                + "}";
        Iterator<String> iterator = palabras.iterator();
        while (iterator.hasNext()) {
            String palabra = iterator.next();
            if (palabra != null) {
                palabra = palabra.replace("\"", "");
            }
            palabraJson += "{\"PALABRAS\":\"" + palabra + "\"}";
            if (iterator.hasNext()) {
                palabraJson += ",";
            }
        }
        requestBody = requestBody.replace("[palabrasJson]", palabraJson);
        try {
            HttpPost request = new HttpPost(azureUrl);
            String respuesta;
            Long idSubFamilia;
            StringEntity params = new StringEntity(requestBody, "UTF-8");
            request.addHeader("content-type", "application/json");
            request.addHeader("Authorization", token);
            request.setEntity(params);
            try (CloseableHttpClient httpClient = HttpClients.createDefault();
                    CloseableHttpResponse response = httpClient.execute(request)) {
                respuesta = EntityUtils.toString(response.getEntity());
            }
            JSONObject json = new JSONObject(respuesta);
            JSONObject results = new JSONObject(json.get("Results").toString());
            JSONArray jArray = results.getJSONArray("probabilidades");
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject resultsPalabra = new JSONObject(jArray.get(i).toString());
                idSubFamilia = Long.parseLong(resultsPalabra.getString("Scored Labels"));
                log.info("Scored:" + idSubFamilia);
                clasificacionArbol = utileriasService.getReclasificacion(idSubFamilia);
                if (clasificacionArbol != null && !clasificacionArbol.isEmpty() && !palabras.isEmpty()) {
                    clasificacionArbol.get(0).setPalabra(resultsPalabra.getString("PALABRAS"));
                }
                listClasificacionArbol.put("palabra_" + i, clasificacionArbol);
            }
        } catch (Exception e) {
            log.error("getReclasificacion", e);
        }
        return listClasificacionArbol.toString();
    }

    @RequestMapping(value = "guardarCalificacion", method = RequestMethod.POST)
    @ResponseBody
    public String guardarCalificacion(@RequestBody String json) {
        try {
            Gson gson = new Gson();
            MyHDRowSetPojo myHDRowSetPojo = gson.fromJson(json, MyHDRowSetPojo.class);
            utileriasService.guardarCalificacion(myHDRowSetPojo);
            log.debug("hola");
        } catch (Exception e) {
            log.error(e);
        }
        log.info("Hola mundo");
        return "";
    }

    @GetMapping("aproboExamen/{url}/{idUsuario}/{idRol}")
    public AprobarExamenPojo aproboExamen(@PathVariable("url") String url, @PathVariable("idUsuario") String idUsuario, @PathVariable("idRol") Integer idRol) {
        return utileriasService.aproboExamen(url, idUsuario, idRol);
    }

    @GetMapping("/status")
    public String statusApp() {
        return "UP";
    }
}
