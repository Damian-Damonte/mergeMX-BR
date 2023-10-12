/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.repository;

import com.metalsa.recibos.model.PreguntasRecibos;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author edgar.leal
 */
public interface PreguntasRecibosRepository extends PagingAndSortingRepository<PreguntasRecibos, Long> {
    List<PreguntasRecibos> findByIdioma(String idioma);
}
    
 
