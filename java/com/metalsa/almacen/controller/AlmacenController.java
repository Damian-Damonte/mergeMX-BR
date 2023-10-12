package com.metalsa.almacen.controller;

import com.google.gson.Gson;
import com.metalsa.almacen.model.Despacho;
import com.metalsa.almacen.model.DetalleDespacho;
import com.metalsa.almacen.pojo.Almacen;
import com.metalsa.almacen.pojo.CancelDespRequest;
import com.metalsa.almacen.pojo.DespachoRequest;
import com.metalsa.almacen.service.DespachoService;
import com.metalsa.aprobacion.model.NvcTblOrganizacionesH;
import com.metalsa.recibos.model.Requisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j;
import com.metalsa.utils.Constants;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author miguel.rdz
 *
 */
@RestController
@Api(value = "Almacen", tags = {"Almacen Service API"})
@RequestMapping(Constants.URI_API_ALMACEN)
@Log4j
public class AlmacenController {

    @Autowired
    private DespachoService despachoService;

    @Qualifier("application")
    @Autowired
    private MessageSource messages;

    @GetMapping("/despacho/getUens/{idUsuario}")
    public Iterable<NvcTblOrganizacionesH> getUensConReserva(@PathVariable("idUsuario") String idUsuario) {
        return despachoService.getUensConReserva(idUsuario);
    }

    @GetMapping("/despacho/getAlmacenes/{idUsuario}")
    public Iterable<Almacen> getAlmacenesPorUen(@PathVariable("idUsuario") String idUsuario, @RequestParam("uens") String uens) {
        return despachoService.getAlmacenesPorUen(idUsuario, uens);
    }

    @GetMapping("/despacho/getRequisitores/{idUsuario}")
    public Iterable<Requisitor> getRequisitoresPorUen(@PathVariable("idUsuario") String idUsuario, @RequestParam("uens") String uens) {
        return despachoService.getRequisitoresPorUen(idUsuario, uens);
    }

    @GetMapping("/despacho/getDespachos/{idUsuario}")
    public Iterable<Despacho> getDespachosPorUsuario(@PathVariable("idUsuario") String idUsuario) {
        return despachoService.getDespachosPorUsuario(idUsuario);
    }

    @RequestMapping(value = "/despacho/getDespachos", method = RequestMethod.POST)
    @ResponseBody
    public Iterable<Despacho> getDespachos(@RequestBody DespachoRequest req) {
        try {
            if (req != null) {
                return despachoService.getDespachos(req);
            }
        } catch (Exception e) {
            log.error("Error en getDespachos:" + e.getMessage());
        }
        return null;
    }

    @RequestMapping(value = "/despacho/getDetalleDespachos", method = RequestMethod.POST)
    @ResponseBody
    public Iterable<DetalleDespacho> getDetalleDespachos(@RequestBody DespachoRequest req) {
        try {
            if (req != null) {
                return despachoService.getDetalleDespachos(req);
            }
        } catch (Exception e) {
            log.error("Error en getDespachos:" + e.getMessage());
        }
        return null;
    }

    @RequestMapping(value = "/despacho/despachar", method = RequestMethod.POST)
    @ResponseBody
    public Iterable<DetalleDespacho> despachar(@RequestBody DespachoRequest req) {
        try {
            if (req != null) {
                req.getRequisPorDespachar().stream().map((requi) -> req.getLineasPorDespachar().stream()
                        .filter(elem -> Objects.equals(elem.getIdRequisicion(), requi.getIdRequisicion()))
                        .collect(Collectors.toList())).forEachOrdered((requiLineas) -> {
                    int flag = 1;
                    int lineaCnt = 1;
                    int lineas = requiLineas.size();
                    for (DetalleDespacho lineaDespacho : requiLineas) {
                        if (lineaCnt == lineas) {
                            flag = 100;
                        } else if (lineaCnt > 1) {
                            flag = 2;
                        }
                        lineaCnt++;
                        lineaDespacho.setConsigFlag(flag);
                        lineaDespacho.setIdUsuario(req.getIdUsuario());
                        lineaDespacho.setIdIdioma(req.getIdIdioma());
                        lineaDespacho.setIdUsuarioRecibe(req.getIdUsuarioRecibe());
                        despachoService.despachar(lineaDespacho);
                    }
                });
                return req.getLineasPorDespachar();
            }
        } catch (Exception e) {
            log.error("Error en despachar:" + e.getMessage());
        }
        return null;
    }

    @RequestMapping(value = "/despacho/getPendDespachoByFilters", method = RequestMethod.POST)
    @ResponseBody
    public Iterable<Despacho> getPendDespachoByFilters(@RequestBody DespachoRequest req) {
        try {
            if (req != null) {
                return despachoService.getPendDespachoByFilters(req);
            }
        } catch (Exception e) {
            log.error("Error en getPendDespachoByFilters:" + e.getMessage());
        }
        return null;
    }

    @RequestMapping(value = "/despacho/cancelarDespacho", method = RequestMethod.POST)
    @ResponseBody
    public Integer cancelarDespacho(@RequestBody String json) {
        Integer response = 0;

        try {
            if (json != null) {
                Gson gson = new Gson();
                CancelDespRequest items = gson.fromJson(json, CancelDespRequest.class);
                response = 0;
                for (DetalleDespacho detalleDespacho : items.getDetalleDespacho()) {
                    if (!despachoService.cancelarDespacho(detalleDespacho, items.getRazonCancelacion()).equals(0)) {
                        throw new Exception("Error requisicion :" + detalleDespacho.getIdRequisicion()
                                + " partida:" + detalleDespacho.getIdPartida());
                    }
                }
            }
        } catch (Exception e) {
            response = -1;
            log.error("Error en getDespachos:" + e.getMessage());
        }
        return response;
    }
}
