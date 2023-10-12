/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.aprobacion.service;

import com.metalsa.core.model.Uens;
import com.metalsa.core.repository.MotorRepository;
import com.metalsa.utils.Constants;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author jose.rivera02
 */
@RestController
@Api(value = "motor", tags = {"Motor Service API"})
@RequestMapping(Constants.URI_API_MOTOR)
public class MotorController {
    
    @Autowired
    private MotorRepository motorRepository;
    
    @RequestMapping(value = "/obtenerUensUsuario/{idUsuario}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Uens> obtenerUensUsuario(@PathVariable("idUsuario") String idUsuario)
    {System.err.println("EN LISTA UENS....");
        return motorRepository.obtenerUensUsuario(idUsuario);
    }
}
