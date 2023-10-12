/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.repository;

import com.metalsa.core.model.UenPorUsuario;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *   
 * @author edgar.leal
 * 
 */
public interface UenPorUsuarioRepository extends PagingAndSortingRepository<UenPorUsuario, Long>{
   
    Iterable<UenPorUsuario> uenPorUsuario(String idUsuario);
    Iterable<UenPorUsuario> uenPorUsuarioSelCot(String idUsuario);
    
}
