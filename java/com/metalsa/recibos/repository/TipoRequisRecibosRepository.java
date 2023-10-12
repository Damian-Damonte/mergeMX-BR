/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.repository;

import com.metalsa.recibos.model.SpxTipoDeRequisicion;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author edgar.leal
 */
public interface TipoRequisRecibosRepository extends PagingAndSortingRepository<SpxTipoDeRequisicion, Long>{
    
    
}
