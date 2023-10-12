/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.repository;


import com.metalsa.recibos.model.Uom;
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
public interface UomReciboRepository extends PagingAndSortingRepository<Uom,Long>,UomProcedure{
    @Override
    @Procedure(name = "obtenerListaUom")
    List<Uom> obtenerListaUom(@Param("p_unit_of_measure_sel") String unidad);
}
