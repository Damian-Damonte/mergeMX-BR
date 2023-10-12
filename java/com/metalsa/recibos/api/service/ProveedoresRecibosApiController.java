/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.api.service;

/**
 *
 * @author edgar.leal
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.metalsa.core.model.NvcTblOaProveedoresH;
import com.metalsa.core.repository.ProveedoresRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author edgar.leal
 */
@RestController
@Api(value = "proveedores recibo", tags = {"Proveedores recibo Service API"})
@RequestMapping("/api/v1/recibo/proveedores")
public class ProveedoresRecibosApiController {
    
    
    @Autowired
    private ProveedoresRepository proveedoresRepository;
    
    /**
     * 
     *
     * @param idUen 
     * @return Dada una UEN arroja un listado de proveedores asociados a la misma.
     */
    @RequestMapping(value = "/uen/{id}", method = RequestMethod.GET)    
    public @ResponseBody
    Iterable<NvcTblOaProveedoresH> getAll(@PathVariable("id") int idUen) {
        return proveedoresRepository.findPorUen(idUen);
    }
    
    @RequestMapping(value = "/uen/{id}/{idUsuario}", method = RequestMethod.GET)    
    public @ResponseBody
    Iterable<NvcTblOaProveedoresH> getAllProveedoresPorUenRequisicion(@PathVariable("id") int idUen, @PathVariable("idUsuario") String idUsuario) {
        return proveedoresRepository.proveedoresPorUenRequisicion(idUsuario, idUen);
    }
    
    @RequestMapping(value = "/{idUsuario}", method = RequestMethod.GET)    
    public @ResponseBody
    Iterable<NvcTblOaProveedoresH> getAllProveedoresPorRequisicion(@PathVariable("idUsuario") String idUsuario) {
        return proveedoresRepository.proveedoresPorRequisicion(idUsuario);
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    Page<NvcTblOaProveedoresH> getAllPaged(@RequestParam(value = "page", required = true, defaultValue = "0") int page,
            @RequestParam(value = "size", required = true, defaultValue = "10") int size,
            @RequestParam(value = "sort", required = false, defaultValue = "") String[] sorting) {
        if (sorting != null && sorting.length > 0) {
            return proveedoresRepository.findAll(new PageRequest(page, size, new Sort(sorting)));
        }
        return proveedoresRepository.findAll(new PageRequest(page, size));
    }
    
    
}
