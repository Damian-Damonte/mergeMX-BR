package com.metalsa.generales.service;

import com.metalsa.generales.model.Parametro;
import com.metalsa.generales.model.ParametroUenCia;
import com.metalsa.generales.repository.ParametroRepository;
import com.metalsa.generales.repository.ParametroUenCiaRepository;
import java.util.List;//<RELEASE ARG/>
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;//<RELEASE ARG/>

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
@Service
@Log4j
public class ConfigurationService {
	//<RELEASE ARG>
    @Autowired
    private ParametroRepository parametros;
    
    @Autowired
    private ParametroUenCiaRepository parametrosUen;

    public ConfigurationService(ParametroRepository parametros, ParametroUenCiaRepository parametrosUen) {
        this.parametros = parametros;
        this.parametrosUen = parametrosUen;
    }

    public Parametro createParametro(Parametro p) {
        return parametros.save(p);
    }

    public Parametro getParameterByName(String nombre){
         return parametros.findByNombre(nombre).orElseGet(null);
    }
    //</RELEASE ARG>

    public void updateParametro(ParametroUenCia param) {
        Optional<ParametroUenCia> p = parametrosUen.findByIdUenAndIdParametro(param.getIdUen(), param.getIdParametro());

        if(p.isPresent()) {
            p.get().setValor(param.getValor());
        } else {
            parametrosUen.save(param);
        }
    }

    public ParametroUenCia getParametro(Long uen, Long parametro) {
        return parametrosUen.findOne(new ParametroUenCia.Pk(uen, parametro));
    }

    public ParametroUenCia getParametro(Long uen, String nombre) {
        return parametros.findByNombre(nombre)
                .map(p -> getParametro(uen, p.getIdParametro()))
                .orElse(null);
    }
    //<RELEASE ARG>
    public List<ParametroUenCia> findAllByIdParameter(Long idParameter){
        return this.parametrosUen.findAllByIdParametro(idParameter);
    }
    
    public List<ParametroUenCia> findAllByIdParameterValue(Long idParameter,String value){
        return this.parametrosUen.findAllByIdParametroAndValor(idParameter,value);
    }
    //</RELEASE ARG>
    
    //<RELEASE_ETOWN>
     public ParametroUenCia findParametroSPXByNombre(Integer uen, String parametro){
        return this.parametrosUen.findParametroSPXByNombre(uen, parametro);
     }
    //<RELEASE_ETOWN>
}
