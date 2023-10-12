/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.portalProveedor.controller;

import com.metalsa.core.model.NvcTblOaProveedoresH;
import com.metalsa.portalProveedor.model.UnidadMedida;
import com.metalsa.portalProveedor.model.Moneda;
import com.metalsa.portalProveedor.model.NvcTblDocsCotizacion;
import com.metalsa.portalProveedor.model.NvcTblGastoAdicional;
import com.metalsa.portalProveedor.model.NvcTblReqLineaProv;
import com.metalsa.portalProveedor.pojo.NvcTblDocsCotizacionPojo;
import com.metalsa.portalProveedor.pojo.OaIvaUenPojo;
import com.metalsa.portalProveedor.pojo.PoVendorPojo;
import com.metalsa.portalProveedor.pojo.RfqLineaRequest;
import com.metalsa.portalProveedor.pojo.RfqPojo;
import com.metalsa.portalProveedor.pojo.RfqProveedorRequest;
import com.metalsa.utils.Constants;
import java.util.List;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.metalsa.portalProveedor.service.PortalProveedorService;
import java.math.BigDecimal;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author mlopez
 */
@RestController
@RequestMapping(Constants.URI_API_PROVEEDOR_PORTAL)
@CrossOrigin
@Log4j

public class PortalProveedorController {

    @Autowired
    private PortalProveedorService service;

    @RequestMapping(value = "/getCotizacionesProveedor", method = RequestMethod.POST)
    @ResponseBody
    public Iterable<RfqPojo> getCotizacionensProveedor(@RequestBody RfqProveedorRequest request) {
        return service.getCotizacionesProveedor(request);
    }

    @RequestMapping(value = "/getProveedorByLikeName/{proveedor}/{idUen}", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<NvcTblOaProveedoresH> getProveedoresByLikeName(@PathVariable String proveedor, @PathVariable BigDecimal idUen) {
        return service.getProveedorByLikeName(proveedor, idUen);
    }

    @RequestMapping(value = "/getIvaByUen/{idUen}", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<OaIvaUenPojo> getIvaByUen(@PathVariable Integer idUen) {
        return service.getIvaByUen(idUen);
    }

    @RequestMapping(value = "/getMonedasActivas", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Moneda> getMonedasActivas() {
        return service.getMonedasActivas();
    }

    @RequestMapping(value = "/getUnidadMedidaByIdioma/{idioma}", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<UnidadMedida> getUnidadMedidaByIdioma(@PathVariable String idioma) {
        return service.getUnidadMedidaByIdioma(idioma);
    }

    @RequestMapping(value = "/getLineaCotByRfqAndSupplier", method = RequestMethod.POST)
    @ResponseBody
    public Iterable<NvcTblReqLineaProv> getLineaCotByRfqAndSupplier(@RequestBody RfqLineaRequest request) {
        List<NvcTblReqLineaProv> list = service.getLineaByRfqAndSupplier(request);
        return list;
    }

    @RequestMapping(value = "/getGastosAdicionalesByIdReqLineaProv/{idReqLineaProv}", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<NvcTblGastoAdicional> getGastosAdicionalesByIdReqLineaProv(@PathVariable Integer idReqLineaProv) {
        return service.getGastoAdicionalByIdReqLineaProv(idReqLineaProv);
    }

    @RequestMapping(value = "/saveGastosAdicionales", method = RequestMethod.POST)
    @ResponseBody
    public boolean saveGastosAdicionales(@RequestBody List<NvcTblGastoAdicional> request) {
        return service.saveGastoAdicional(request);
    }

    @RequestMapping(value = "/deleteGasto", method = RequestMethod.POST)
    @ResponseBody
    public boolean deleteGastosAdicionales(@RequestBody NvcTblGastoAdicional request) {
        return service.deleteGastoAdicional(request);
    }

    @RequestMapping(value = "/saveReqLineaProv", method = RequestMethod.PUT)
    @ResponseBody
    public boolean saveReqLineaProv(@RequestBody List<NvcTblReqLineaProv> request) {
        return service.saveReqLineaProv(request);
    }

    @RequestMapping(value = "/sendQuotation", method = RequestMethod.POST)
    @ResponseBody
    public boolean sendQuotation(@RequestBody List<NvcTblReqLineaProv> request) {
        return service.sendQuaotation(request);
    }

    @RequestMapping(value = "/saveFiles", method = RequestMethod.POST)
    @ResponseBody
    public boolean saveCotizacionFiles(@RequestBody List<NvcTblDocsCotizacionPojo> request) {
        return service.saveDocCotizacion(request);
    }

    @RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
    @ResponseBody
    public boolean deleteFiles(@RequestBody NvcTblDocsCotizacion request) {
        return service.deleteDocCotizacion(request);
    }

    @GetMapping("/getProveedorByVendorEncoded")
    public PoVendorPojo getProveedorByVendorEncoded(@CookieValue(name = "vendorId") String proveedor) {
        return service.getProveedorByVendorEncoded(proveedor);
    }

    @GetMapping("/guardarIdCotizacion/{idRfq}/{idCotizacion}")
    public boolean guardarIdCotizacion(@PathVariable String idRfq, @PathVariable String idCotizacion) {
        return service.guardarIdCotizacion(idRfq, idCotizacion);
    }
}
