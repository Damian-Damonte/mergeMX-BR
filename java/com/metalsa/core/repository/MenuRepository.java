/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.repository;
import com.metalsa.core.model.Menu;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
/**
 *
 * @author jose.rivera02
 */
@Repository
public interface MenuRepository extends PagingAndSortingRepository<Menu, Long>{
        
    @Procedure(name = "obtenerMenu")
    String obtenerMenu(@Param("P_USER_ID") String P_USER_ID , @Param("P_IDIOMA") String P_IDIOMA);
    
    @Procedure(name = "obtenerRequisRegresadas")
    Integer obtenerRequisRegresadas(@Param("p_id_requisitor") String p_id_requisitor);

}



