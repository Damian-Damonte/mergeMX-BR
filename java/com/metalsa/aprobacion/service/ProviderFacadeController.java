package com.metalsa.aprobacion.service;

import com.metalsa.core.model.NvcTblOaProveedoresH;
import com.metalsa.core.repository.ProveedoresRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by ruben.bresler on 10/07/2017.
 */
@RestController
@Api(value = "proveedores", tags = {"Proveedores Service API"})
@RequestMapping("/api/v1/proveedores")
public class ProviderFacadeController {

    @Autowired
    private ProveedoresRepository proveedores;

    @RequestMapping(params = {"uens"}, method = RequestMethod.GET)
    public @ResponseBody
    Iterable<NvcTblOaProveedoresH> getAllProveedoresPorUens(@RequestParam("uens") List<Long> uens) {
        return proveedores.findProveedoresByUens(uens);
    }
}
