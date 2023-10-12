/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.repository;

import com.metalsa.core.model.Uens;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jose.rivera02
 */
@Repository
public interface MotorRepository extends PagingAndSortingRepository<Uens, Long>{
    
    @Procedure(name = "obtenerUensUsuario")
    List<Uens> obtenerUensUsuario(@Param("P_ID_USER") String P_USER_ID);
}
