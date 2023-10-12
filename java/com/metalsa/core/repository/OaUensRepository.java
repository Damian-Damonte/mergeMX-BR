/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.repository;

import com.metalsa.core.model.OaUens;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author aayala
 */
public interface OaUensRepository extends PagingAndSortingRepository<OaUens, Long>{
   
    Iterable<OaUens> uensPorRequisicionesUsuario(String idUsuario);
    
    Iterable<OaUens> uensPorUsuario(String idUsuario);
    
    Iterable<OaUens> getUensActivasPorUsuario(String idUsuario);
    
    //<R17486>
    Iterable<OaUens> uensPorUsuarioRecibo(String idUsuario);
    
}
