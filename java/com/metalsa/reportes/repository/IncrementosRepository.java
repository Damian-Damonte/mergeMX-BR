/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.reportes.repository;

import com.metalsa.reportes.model.Incrementos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Oscar del Angel
 */
@Repository
public interface IncrementosRepository extends JpaRepository<Incrementos,Long>,IncrementosProcedure{
   

    
}
