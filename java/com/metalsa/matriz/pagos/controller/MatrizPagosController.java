/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.matriz.pagos.controller;

import com.google.gson.Gson;
import com.metalsa.aprobacion.model.NvcTblOrganizacionesH;
import com.metalsa.aprobacion.model.OaProyectos;
import com.metalsa.aprobacion.repository.ProyectoRepository;
import com.metalsa.compradorCC.service.CompradorCcService;
import com.metalsa.matriz.pagos.dto.FiltrosMatrizPagosDTO;
import com.metalsa.matriz.pagos.dto.ResponseMatrizDTO;
import com.metalsa.matriz.pagos.dto.UenProyectoDTO;
import com.metalsa.matriz.pagos.service.MatrizPagosService;

import com.metalsa.utils.Constants;
import java.io.ByteArrayInputStream;
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
@RestController
@RequestMapping(Constants.URI_API_MATRIZ_PAGOS)
@Log4j
public class MatrizPagosController {

    @Autowired
    MatrizPagosService matrizPagosService;
    @Autowired
    CompradorCcService compradorCcService;
    @Autowired
    private ProyectoRepository proyectoRepository;

    @RequestMapping(value = "getDatosMatrizPagos", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMatrizDTO getDatosMatrizPagos(@RequestBody String json) {
        Gson gson = new Gson();
        FiltrosMatrizPagosDTO filtros = gson.fromJson(json, FiltrosMatrizPagosDTO.class);
        return matrizPagosService.getDatosMatrizPagos(filtros);
    }

    @RequestMapping(value = "getCombosProyectos", method = RequestMethod.POST)
    @ResponseBody
    public Iterable<UenProyectoDTO> getCombosProyectos(@RequestBody String json) {
        Gson gson = new Gson();
        FiltrosMatrizPagosDTO filtros = gson.fromJson(json, FiltrosMatrizPagosDTO.class);
        List<UenProyectoDTO> uenProyectoDTO = new ArrayList();
        if (filtros.getModelListaUen() != null && !filtros.getModelListaUen().isEmpty()) {
            for (NvcTblOrganizacionesH uen : filtros.getModelListaUen()) {
                UenProyectoDTO uenProyectoDTO_ = new UenProyectoDTO();
                List<OaProyectos> listCombosProyectos = new ArrayList();
                Integer idUen = uen.getId() == null ? null : uen.getId().intValue();
                if (idUen != null) {
                    listCombosProyectos.addAll(proyectoRepository.findByIdUen(idUen));
                }
                uenProyectoDTO_.setUen(uen);
                uenProyectoDTO_.setListProyectos(listCombosProyectos);
                uenProyectoDTO.add(uenProyectoDTO_);
            }
        }
        return uenProyectoDTO;
    }

    @RequestMapping("getComboUen/{idUsuario}")
    @ResponseBody
    public Iterable<NvcTblOrganizacionesH> getComboUen(@PathVariable String idUsuario) {
        Iterable<NvcTblOrganizacionesH> listUensCc = matrizPagosService.getUens(idUsuario);
        return listUensCc;
    }

    @RequestMapping(value = "getReporteExcel", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<InputStreamResource> getReporteExcel(@RequestBody String json) {
        Gson gson = new Gson();
        HttpHeaders headers = null;
        FiltrosMatrizPagosDTO filtros = gson.fromJson(json, FiltrosMatrizPagosDTO.class);

        ByteArrayInputStream in = matrizPagosService.getReporteExcel(filtros);
        headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=plantilla.xlsx");
        headers.add("Content-Type", "application/xlsx");
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }

}
