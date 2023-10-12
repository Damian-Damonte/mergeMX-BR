/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.aprobacion.service;

import com.metalsa.core.repository.MenuRepository;
import com.metalsa.utils.Constants;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author jose.rivera02
 */
@RestController
@Api(value = "menu", tags = {"Menu Service API"})
@RequestMapping(Constants.URI_API_MENU)
public class MenuController {
    
    @Autowired
    private MenuRepository menuRepository;
    
    @RequestMapping(value = "/menuUsuario/{idUsuario}/{idioma}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String getMenuUsuario(@PathVariable("idUsuario") String idUsuario,
            @PathVariable("idioma") String idioma,
            @RequestParam(value = "token2", required = false, defaultValue = "false") String token2)
    {
        return menuRepository.obtenerMenu(idUsuario, idioma);
    }
    
    
    @RequestMapping(value = "/requisRegresadas/{idUsuario}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Integer obtenerRequisRegresadas(@PathVariable("idUsuario") String idUsuario,
            @RequestParam(value = "token2", required = false, defaultValue = "false") String token2)
    {
        return menuRepository.obtenerRequisRegresadas(idUsuario);
    }
    
}
