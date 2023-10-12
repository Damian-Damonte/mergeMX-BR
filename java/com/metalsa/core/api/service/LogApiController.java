/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.api.service;

/**
 *
 * @author edgar.leal
 */
 

import com.metalsa.core.repository.LogRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Log", tags = {"Log Service API"})
@RequestMapping("/api/v1/log")
public class LogApiController {
    
    @Autowired
    private LogRepository logRepository;
    
    @RequestMapping(value = "/registrar", method = RequestMethod.GET)
    public @ResponseBody
    String registrar() {
        String check;
        try {
            logRepository.AGREGAR_LOG_REQUISICION(1222885, 2, "SPX-RECIBOS", "OLIP", "Eliminar", "Check", "");
            check = "Si";
        } catch (Exception e) {
            check = e.getCause() + e.getMessage();
        }
        
        return check;
    }
}
