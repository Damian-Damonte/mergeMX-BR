package com.metalsa.generales.service;

import com.metalsa.generales.model.ParametroByUen;
import com.metalsa.generales.model.ParametroUenCia;
import com.metalsa.generales.repository.ParametroUenRepository;
import com.metalsa.utils.Constants;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
@RestController
@Log4j
@RequestMapping(Constants.URI_API_PARAMETROS)
public class ParametrosController {

    private ParametroUenRepository parametros;
    private ConfigurationService service;

    public ParametrosController(ParametroUenRepository parametros, ConfigurationService service) {
        this.parametros = parametros;
        this.service = service;
    }

    @GetMapping
    public Iterable<ParametroByUen> getParameters(@RequestParam(value = "uen", required = false) Long uen) {

        if(Objects.isNull(uen))
            return parametros.findAll();

        return parametros.findAllByIdUen(uen);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateParameter(ParametroByUen param) {
        service.updateParametro(new ParametroUenCia(param.getIdUen(), param.getIdParametro(), param.getValor()));
    }
    
    //<RELEASE_ETOWN>
    @GetMapping(value = "/getParametroByUenAndId")
    public ParametroUenCia findParametroSPXByNombre(@RequestParam(value = "uen", required = true) Integer uen,
            @RequestParam(value = "parametro", required = true) String parametro) {

        return service.findParametroSPXByNombre(uen, parametro);
    }
    //</RELEASE_ETOWN>
}
