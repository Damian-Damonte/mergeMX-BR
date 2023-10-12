/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.repository;

import com.metalsa.recibos.model.ImprimeRecibo;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


/**
 *
 * @author edgar.leal
 */
@Repository
public interface ManipulacionReciboRepository extends PagingAndSortingRepository<ImprimeRecibo, Long>, ManipulacionReciboProcedure{
    
    /*@Override
    @Procedure(name = "PRC_SPX_REQS_POR_RECIBIR")
    List<ReciboConsulta> PRC_SPX_REQS_POR_RECIBIR( @Param("P_ID_REQUISICION") String P_ID_REQUISICION, @Param("P_ID_ORDEN_DE_COMPRA") String P_ID_ORDEN_DE_COMPRA, @Param("P_UEN")  String P_UEN, @Param("P_FECHA_INICIAL")  String P_FECHA_INICIAL, @Param("P_FECHA_FINAL")  String P_FECHA_FINAL, @Param("P_URGENTE")  String P_URGENTE, @Param("P_ID_PROVEEDOR")  String P_ID_PROVEEDOR, @Param("P_ID_CC")  String P_ID_CC, @Param("P_ID_REQUISITOR")  String P_ID_REQUISITOR);*/
    

}