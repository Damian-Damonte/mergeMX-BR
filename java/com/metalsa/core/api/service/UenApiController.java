/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.api.service;

import com.metalsa.core.model.Chatcomprador;
import com.metalsa.core.model.MotivoRegresoView;
import com.metalsa.core.model.OaUens;
import com.metalsa.core.model.UenPorUsuario;
import com.metalsa.core.model.UserUenRol;
import com.metalsa.core.repository.ChatCompradorRepository;
import com.metalsa.core.repository.MotivoRegresoRepository;
import com.metalsa.core.repository.OaUensRepository;
import com.metalsa.core.repository.UenPorUsuarioRepository;
import com.metalsa.core.repository.UserUenRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import java.math.BigInteger;
import java.util.logging.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author edgar.leal
 */
@RestController
@Api(value = "uen", tags = {"Uen Service API"})
@RequestMapping("/api/v1/uen")
public class UenApiController {
    
    @Autowired
    private UserUenRolRepository userUenRolRepository;
    
    
    @Autowired
    private UenPorUsuarioRepository uenPorUsuarioRepository;
    
    
    @Autowired
    private ChatCompradorRepository chatCompradorRepository;
    
    @Autowired
    private MotivoRegresoRepository motivoRegresoRepository;
    
    @Autowired
    private OaUensRepository oaUensRepository;
    
    static Logger log = Logger.getLogger(UenApiController.class.getName());
    
    /**
     * 
     * 
     * 
     * @param idUen es la uen a consultar
     * @param tipo es el tipo de usuario que se quiere recuperar
     * @return todos los usuarios con el rol que pertenecen a la uen
     */
    @RequestMapping(value = "/{idUen}/{tipo}", method = RequestMethod.GET)    
    public @ResponseBody
    Iterable<UserUenRol> getAll(@PathVariable("idUen") int idUen, @PathVariable("tipo") int tipo) {
        return userUenRolRepository.usuario(idUen, tipo);  //3
    }
    
    
    /**
     * 
     * 
     * @param requi
     * @return dada una requisicion envia el chat del comprador.
     */
    @RequestMapping(value = "/requisicion/{requi}", method = RequestMethod.GET)    
    public @ResponseBody
    Iterable<Chatcomprador> getRequi(@PathVariable("requi") int requi) {
        
        log.warning("TEST");
        log.info("TEST");
        return chatCompradorRepository.findByIdRequisicion(requi); 
    }
    
    
    
    @RequestMapping(value = "/usuario/{idUsuario}/{tipo}", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<UenPorUsuario> getAll(@PathVariable("idUsuario") String idUsuario, @PathVariable("tipo") String tipo)
    {
        Iterable<UenPorUsuario> resultado = null;
        if (null == tipo){
            resultado = uenPorUsuarioRepository.uenPorUsuario(idUsuario);
        }else switch (tipo) {
            case "uenporusuario":
                resultado = uenPorUsuarioRepository.uenPorUsuario(idUsuario);
                break;
            case "uenPorReq":
                resultado = uenPorUsuarioRepository.uenPorUsuarioSelCot(idUsuario);
                break;
            default:
                resultado = uenPorUsuarioRepository.uenPorUsuario(idUsuario);
                break;
        }
        return resultado;
    }
    
    @RequestMapping(value = "/motivo/{idPartida}/{idRequi}", method = RequestMethod.GET)
    public @ResponseBody
    MotivoRegresoView getMotivo(@PathVariable("idPartida") BigInteger idPartida, @PathVariable("idRequi") BigInteger idRequi)
    {
        //return motivoRegresoRepository.findAll();
        return motivoRegresoRepository.findByIdRequisicionAndIdPartida(idRequi, idPartida);
    }
    
    
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    Page<UserUenRol> getAllPaged(@RequestParam(value = "page", required = true, defaultValue = "0") int page,
            @RequestParam(value = "size", required = true, defaultValue = "10") int size,
            @RequestParam(value = "sort", required = false, defaultValue = "") String[] sorting) {
        if (sorting != null && sorting.length > 0) {
            return userUenRolRepository.findAll(new PageRequest(page, size, new Sort(sorting)));
        }
        return userUenRolRepository.findAll(new PageRequest(page, size));
    }
    
    @RequestMapping("*")
    @ResponseBody
    public String fallbackMethod(){
            return "Error en la petición";
    }
    
    @RequestMapping(value = "/uensPorRequisicionesUsuario/{idUsuario}", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<OaUens> uensPorRequisicionesUsuario(@PathVariable("idUsuario") String idUsuario)
    {
        Iterable<OaUens> resultado = null;
        
        resultado = oaUensRepository.uensPorRequisicionesUsuario(idUsuario);        
        
        return resultado;
    }
    
    @RequestMapping(value = "/uensPorUsuario/{idUsuario}", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<OaUens> uensPorUsuario(@PathVariable("idUsuario") String idUsuario)
    {       
        return oaUensRepository.uensPorUsuario(idUsuario);
    }
    
    @RequestMapping(value = "/uensPorUsuario/uensActivas/{idUsuario}", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<OaUens> uensActivasPorUsuario(@PathVariable("idUsuario") String idUsuario)
    {       
        return oaUensRepository.getUensActivasPorUsuario(idUsuario);
    }
    
    // <R17486>
    @RequestMapping(value = "/uensPorUsuarioRecibo/{idUsuario}", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<OaUens> uensPorUsuarioRecibo(@PathVariable("idUsuario") String idUsuario)
    {       
        return oaUensRepository.uensPorUsuarioRecibo(idUsuario);
    }
    
    // </R17486>
}
