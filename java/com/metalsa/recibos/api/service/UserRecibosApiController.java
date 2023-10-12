/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.api.service;

import com.metalsa.recibos.model.UserRecibo;
import com.metalsa.recibos.repository.UserRecibosRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * 
 * @author  Edgar Leal
 * 
 * 
 * 
 */

@RestController
@Api(value = "UserRecibos", tags = {"User Service API"})
@RequestMapping("/api/v1/recibos/user")
public class UserRecibosApiController {

    @Autowired
    private UserRecibosRepository userRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<UserRecibo> getAll() {
        return userRepository.findAll();
    }
    
    @RequestMapping(value = "/allActive", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<UserRecibo> getAllActive() {
        return userRepository.usuarioActivo();
    }
    
    @RequestMapping(value = "/{idUen}/{tipo}", method = RequestMethod.GET)    
    public @ResponseBody
    Iterable<UserRecibo> getAll(@PathVariable("idUen") int idUen, @PathVariable("tipo") int tipo) {
        return userRepository.usuarioRecibo(idUen, tipo);  //3
    }
    
    @RequestMapping(value = "/porTipo/{tipo}", method = RequestMethod.GET)    
    public @ResponseBody
    Iterable<UserRecibo> getAll(@PathVariable("tipo") int tipo) {
        return userRepository.usuarioPorRol(tipo);  //3
    }
    
    @RequestMapping(value = "/receptores")
    public @ResponseBody Iterable<UserRecibo> getReceptores(){
        return userRepository.usuarioReceptor();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    UserRecibo getById(@PathVariable("id") String id) { /*<M26022018>*/
        return userRepository.findOne(id);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    UserRecibo addUser(@RequestBody UserRecibo user) {
        UserRecibo result;
        try {
            result = userRepository.save(user);
        } catch (Exception e) {
            result = null;
        }
        return result;
    }

    /*<M26022018>*/
    /*@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public @ResponseBody
    UserRecibo editUser(@PathVariable("id") Long id, @RequestBody UserRecibo user) {
        UserRecibo result;
        result = userRepository.findOne(id);
        if (result == null) {
            return null;
        }
        try {
            result = userRepository.save(user);
        } catch (Exception e) {
            result = null;
        }
        return result;
    }*/
    /*</M26022018>*/

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    Page<UserRecibo> getAllPaged(@RequestParam(value = "page", required = true, defaultValue = "0") int page,
            @RequestParam(value = "size", required = true, defaultValue = "10") int size,
            @RequestParam(value = "sort", required = false, defaultValue = "") String[] sorting) {
        if (sorting != null && sorting.length > 0) {
            return userRepository.findAll(new PageRequest(page, size, new Sort(sorting)));
        }
        return userRepository.findAll(new PageRequest(page, size));
    }
}
