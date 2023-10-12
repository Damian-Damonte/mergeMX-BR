/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.repository;

import com.metalsa.recibos.model.ReciboConsulta;
import java.util.List;

/**
 *
 * @author edgar.leal
 */
public interface ReciboConsultaProcedures {
    
    //<R17231>
    List<ReciboConsulta> PRC_SPX_REQS_POR_RECIBIR(String P_ID_REQUISICION, String P_ID_ORDEN_DE_COMPRA, String P_UEN, String P_FECHA_INICIAL, String P_FECHA_FINAL, String P_URGENTE, String P_ID_PROVEEDOR, String P_ID_CC, String P_ID_REQUISITOR,String P_ID_USUARIO);
    //</R17231>
    
    
    /*List<GastoAdicional> getGastosAdicionales(Integer idRequisicion, Integer idPartida,String idIdioma);

    Integer regresarRequisiciones(String requisiciones, String razonRechazo, String idAprobador);

    Integer cancelarRequisicion(String requisiciones, String razonRechazo, String idAprobador);*/
}

