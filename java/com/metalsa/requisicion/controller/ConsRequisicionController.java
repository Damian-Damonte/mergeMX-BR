package com.metalsa.requisicion.controller;

import com.google.gson.JsonSyntaxException;
import com.metalsa.aprobacion.model.Usuario;
import com.metalsa.core.model.NvcTblOaProveedoresH;
import com.metalsa.core.repository.ProveedoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.RequestBody;
import com.metalsa.utils.Constants;
import com.metalsa.requisicion.service.ConsRequisicionService;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Java utils
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

// POJO's
import com.metalsa.requisicion.pojo.*;

/**
 *
 * @author miguel.rdz
 *
 */
@RestController
@Api(value = "Consulta", tags = {"Consulta Service API"})
@RequestMapping(Constants.URI_API_REQUISIONES)
@Log4j
public class ConsRequisicionController {

    @Autowired
    private ConsRequisicionService requiService;
    @Autowired
    private ProveedoresRepository proveedoresRepository;

    @RequestMapping(value = "getRequisFad", method = RequestMethod.POST)
    @ResponseBody
    public Iterable<RequisicionFad> getRequisFad(@RequestBody RequisicionFadRequest req) {
        RequisicionFadResponse response = new RequisicionFadResponse();
        try {
            if (req != null) {
                return requiService.getRequisFad(req);
            }
        } catch (Exception e) {
            log.error("Error al obtener getRequisFad:" + e.getMessage());
        }
        return null;
    }

    @RequestMapping(value = "getProvsFad", method = RequestMethod.POST)
    @ResponseBody
    public Iterable<NvcTblOaProveedoresH> getProvsFad(@RequestBody RequisicionFadRequest req, @RequestParam("query") String query) {
        Iterable<NvcTblOaProveedoresH> provDisponibles;
        List<String> uenList = new ArrayList();
        if (req.getUen().size() > 0) {
            req.getUen().forEach((u) -> {
                uenList.add(u.getId().toString());
            });
            String uensStr = String.join(",", uenList);
            provDisponibles = requiService.getLikeByUenWithFad(uensStr, query);
        } else {
            provDisponibles = proveedoresRepository.findLikeWithFad("%" + query.toUpperCase() + "%");
        }
        return provDisponibles;
    }

    @RequestMapping(value = "getRequisitoresFad", method = RequestMethod.POST)
    @ResponseBody
    public Iterable<Usuario> getRequisitoresFad(@RequestBody RequisicionFadRequest req) {
        Iterable<Usuario> requisitores = null;
        List<String> uenList = new ArrayList();
        if (req.getUen().size() > 0) {
            req.getUen().forEach((u) -> {
                uenList.add(u.getId().toString());
            });
            String uensStr = String.join(",", uenList);
            requisitores = requiService.getUserFadByTipo(uensStr, "REQ");
        }
        return requisitores;
    }

    @RequestMapping(value = "getCompradoresFad", method = RequestMethod.POST)
    @ResponseBody
    public Iterable<Usuario> getCompradoresFad(@RequestBody RequisicionFadRequest req) {
        Iterable<Usuario> compradores = null;
        List<String> uenList = new ArrayList();
        if (req.getUen().size() > 0) {
            req.getUen().forEach((u) -> {
                uenList.add(u.getId().toString());
            });
            String uensStr = String.join(",", uenList);
            compradores = requiService.getUserFadByTipo(uensStr, "COMP");
        }
        return compradores;
    }

    @RequestMapping(value = "getAutoresFad", method = RequestMethod.POST)
    @ResponseBody
    public Iterable<Usuario> getAutoresFad(@RequestBody RequisicionFadRequest req) {
        Iterable<Usuario> autores = null;
        List<String> uenList = new ArrayList();
        if (req.getUen().size() > 0) {
            req.getUen().forEach((u) -> {
                uenList.add(u.getId().toString());
            });
            String uensStr = String.join(",", uenList);
            autores = requiService.getUserFadByTipo(uensStr, "AUT");
        }
        return autores;
    }

    @RequestMapping(value = "findFadByFilters", method = RequestMethod.POST)
    @ResponseBody
    public Iterable<RequisicionFad> findFadByFilters(@RequestBody RequisicionFadRequest req) {
        try {
            if (req != null) {
                return requiService.findFadByFilters(req);
            }
        } catch (Exception e) {
            log.error("Error en findFadByFilters:" + e.getMessage());
        }
        return null;
    }

    @PostMapping(path = "/fad/exportToExcel")
    @ResponseBody
    public ResponseEntity<InputStreamResource> exportToExcel(@RequestBody RequisicionFadRequest req) {
        ByteArrayInputStream in = null;
        HttpHeaders headers = null;
        try {
            in = requiService.exportToExcel(req, LocaleContextHolder.getLocale());
            headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=rep.xlsx");
            headers.add("Content-Type", "application/xlsx");

        } catch (JsonSyntaxException e) {
            log.error("Error al obtener los datos" + e.getMessage());
        }
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }
}
