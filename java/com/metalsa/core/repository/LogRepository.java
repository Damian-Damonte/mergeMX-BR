/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.repository;

import com.metalsa.core.model.Log;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author edgar.leal
 */
@Repository
public interface LogRepository extends PagingAndSortingRepository<Log, Long>{
    
    @Procedure(name = "AGREGAR_LOG_REQUISICION")
    void AGREGAR_LOG_REQUISICION(@Param("P_ID_REQUISICION") Integer P_ID_REQUISICION , @Param("P_ID_PARTIDA") Integer P_ID_PARTIDA, @Param("P_MODULO") String P_MODULO, @Param("P_ID_USUARIO") String P_ID_USUARIO,  @Param("P_ACCION") String P_ACCION, @Param("P_MENSAJE") String P_MENSAJE, @Param("P_ORA_ERROR") String P_ORA_ERROR);
}
