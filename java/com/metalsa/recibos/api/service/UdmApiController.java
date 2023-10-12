/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.api.service;

import com.metalsa.recibos.model.Udm;
import com.metalsa.recibos.repository.UdmRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author yair.nunez
 */
@RestController
@Api(value = "udm", tags = {"Unidad de Medida Service API"})
@RequestMapping("/api/v1/udm")
public class UdmApiController {
    
    @Autowired
    UdmRepository udmRepository;
    
    @RequestMapping(value = "/all/{language}", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<Udm> getRazon(@PathVariable("language") String language) {
        String idioma;
        switch(language){
            case "es": idioma = "ESA"; break;
            default: idioma = "US"; break;
        }
        return udmRepository.findUdmByLanguage(idioma);
    }
}
