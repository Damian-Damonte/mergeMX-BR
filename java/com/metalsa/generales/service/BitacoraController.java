package com.metalsa.generales.service;

import com.metalsa.generales.model.BitacoraTrfPresupuesto;
import com.metalsa.generales.repository.BitacoraTrfPresupuestoRepository;
import com.metalsa.utils.Constants;
import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
@RestController
@RequestMapping(Constants.URI_API_BITACORA)
@Log4j
public class BitacoraController {

    private BitacoraTrfPresupuestoRepository trfPresupuesto;

    public BitacoraController(BitacoraTrfPresupuestoRepository trfPresupuesto) {
        this.trfPresupuesto = trfPresupuesto;
    }

    @GetMapping("/budget")
    public Iterable<BitacoraTrfPresupuesto> getBudgetTransferences() {
        return trfPresupuesto.findAllByOrderById();
    }
}
