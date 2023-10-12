/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.repository;

import com.metalsa.core.model.Chatcomprador;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author edgar.leal
 */
@Repository
public interface ChatCompradorRepository extends PagingAndSortingRepository<Chatcomprador, Long>{
    Iterable<Chatcomprador> findByIdRequisicion(int requi);
    
}
