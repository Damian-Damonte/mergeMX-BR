package com.metalsa.aprobacion.service;

import com.metalsa.aprobacion.model.CuentasFamiliaCC;
import com.metalsa.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.metalsa.aprobacion.repository.CuentasFamiliaCCRepository;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author APOOD9272
 */
@RestController
@RequestMapping(Constants.URI_API_CUENTA_FAM_CC)
public class CuentaFamiliaController {

    @Autowired
    private CuentasFamiliaCCRepository cuentas;

    @RequestMapping(path = "/{uen}/{codigo_cc}/{familia}/{idioma}", method = RequestMethod.GET)
    public Iterable<CuentasFamiliaCC> getCuentasPorFamilia(
            @PathVariable(name = "uen") Long idUen,
            @PathVariable(name = "codigo_cc") String codigoCC,
            @PathVariable(name = "familia") Long idFamilia,
            @PathVariable(name = "idioma") String idIdioma
    ) {
        return cuentas.getCuentasByUenCCFamilia(idUen, codigoCC, idFamilia, idIdioma);
    }

    @RequestMapping(path = "/getCuentas", method = RequestMethod.GET,params = {"uen","codigo_cc","familia","cuenta","idioma"})
    public CuentasFamiliaCC getCuentaByCuenta(
            @RequestParam(name = "uen") Long idUen,
            @RequestParam(name = "codigo_cc") String codigoCC,
            @RequestParam(name = "familia") Long idFamilia,
            @RequestParam(name = "cuenta") Long idCuenta,
            @RequestParam(name = "idioma") String idIdioma
    ) {
        return cuentas.getCuentaByCuenta(idUen, codigoCC, idFamilia, idCuenta,idIdioma);
    }

}
