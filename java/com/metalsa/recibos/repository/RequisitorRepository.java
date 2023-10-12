/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.repository;

import com.metalsa.recibos.model.Requisitor;
import java.util.List;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author yair.nunez
 */
@Repository
public interface RequisitorRepository extends PagingAndSortingRepository<Requisitor,Long>, RequisitorProcedure{
    @Override
    @Procedure(name = "obtenerRequisitores")
    List<Requisitor> obtenerRequisitores(@Param("p_id_usuario") String idUsuario);
}
