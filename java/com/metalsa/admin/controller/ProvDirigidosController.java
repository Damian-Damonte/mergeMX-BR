package com.metalsa.admin.controller;

import com.google.gson.Gson;
import com.metalsa.admin.model.ProveedorDirigido;
import com.metalsa.admin.model.ProveedorDirigidoPorTipo;
import com.metalsa.admin.pojo.ExcelProvDirigidosPojo;
import com.metalsa.admin.pojo.ProveedorDirigidoRequest;
import com.metalsa.admin.repository.ProvDirigidosRepository;
import com.metalsa.admin.repository.ProvDirigidosService;
import com.metalsa.admin.repository.TipoProvDirigidosRepository;
import com.metalsa.core.model.NvcTblOaProveedoresH;
import com.metalsa.core.repository.ProveedoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j;
import com.metalsa.utils.Constants;
import java.io.ByteArrayInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author miguel.rdz
 *
 */
@RestController
@Api(value = "ProvDirigidos", tags = {"Proveedores Dirigidos Service API"})
@RequestMapping(Constants.URI_API_PROVEEDOR_DIRIGIDO)
@Log4j
public class ProvDirigidosController {

    @Autowired
    private ProvDirigidosRepository provDirigidosRepository;
    @Autowired
    private TipoProvDirigidosRepository tipoProvDirigidosRepository;
    @Autowired
    private ProveedoresRepository proveedoresRepository;
    @Autowired
    private ProvDirigidosService provDirigidosService;

    @GetMapping("/dirigidos/disp/{idUen}")
    Iterable<NvcTblOaProveedoresH> getProveedoresDispDirigidos(@PathVariable("idUen") Integer idUen, @RequestParam("query") String query) {
        Iterable<NvcTblOaProveedoresH> provDisponibles = proveedoresRepository.findDispDirigidosPorUen(idUen, "%" + query.toUpperCase() + "%");
        return provDisponibles;
    }

    @RequestMapping(value = "/uen/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<NvcTblOaProveedoresH> getAll(@PathVariable("id") int idUen) {
        return proveedoresRepository.findPorUen(idUen);
    }

    @GetMapping("/dirigidos/{idUen}")
    Iterable<ProveedorDirigido> getProveedoresDirigidos(@PathVariable("idUen") Integer idUen, @RequestParam("idIdioma") String idIdioma) {
        Iterable<ProveedorDirigido> allProvs = provDirigidosRepository.getProvDirigidosUen(idUen);
        DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        for (ProveedorDirigido pd : allProvs) {
            pd.setTiposProvDirigido(tipoProvDirigidosRepository.getByIdProvDirigido(pd.getIdProvDirigido(), idIdioma));
            pd.setAdjuntos(provDirigidosService.getAdjuntosProvDirigidos(pd.getIdProvDirigido().longValue(), idUen));
            pd.setFechaCreacionString(pd.getFechaCreacion() != null ? formatter.format(pd.getFechaCreacion()) : "");
        }
        return allProvs;
    }

    @GetMapping("/tiposDirigidos")
    Iterable<ProveedorDirigidoPorTipo> getTiposProveedorDirigido(@RequestParam("idIdioma") String idIdioma) {
        Iterable<ProveedorDirigidoPorTipo> allTipos = tipoProvDirigidosRepository.getAllTipos(idIdioma);
        return allTipos;
    }

    @RequestMapping(value = "insertProvDirigido", method = RequestMethod.POST)
    @ResponseBody
    public boolean insertProvDirigido(@RequestBody ProveedorDirigidoRequest req) {
        try {
            if (req != null) {
                provDirigidosService.insertProvDirigido(req);
            }
        } catch (Exception e) {
            log.error("Error en insertProvDirigido:" + e.getMessage());
        }
        return true;
    }

    @RequestMapping(value = "updateProvDirigido", method = RequestMethod.POST)
    @ResponseBody
    public boolean updateProvDirigido(@RequestBody ProveedorDirigidoRequest req) {
        try {
            if (req != null) {
                provDirigidosService.updateProvDirigido(req);
            }
        } catch (Exception e) {
            log.error("Error en updateProvDirigido:" + e.getMessage());
        }
        return true;
    }

    @RequestMapping(value = "deleteProvDirigido", method = RequestMethod.POST)
    @ResponseBody
    public boolean deleteProvDirigido(@RequestBody ProveedorDirigidoRequest req) {
        try {
            if (req != null) {
                provDirigidosService.deleteProvDirigido(req);
            }
        } catch (Exception e) {
            log.error("Error en deleteProvDirigido:" + e.getMessage());
        }
        return true;
    }

    
    @RequestMapping(value = "/getReporteExcel", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<InputStreamResource> getReporteExcel(@RequestBody String json) {

        Gson gson = new Gson();
        HttpHeaders headers = null;
        ExcelProvDirigidosPojo  excelProvDirigidosPojo= gson.fromJson(json, ExcelProvDirigidosPojo.class);
        List<ProveedorDirigido> allProvs = provDirigidosRepository.getProvDirigidosUen(excelProvDirigidosPojo.getIdUen());
        ByteArrayInputStream in = provDirigidosService.getReporteExcel(allProvs,excelProvDirigidosPojo);
        headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=plantilla.xlsx");
        headers.add("Content-Type", "application/xlsx");
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }
}
