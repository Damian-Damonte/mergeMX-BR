/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.repository;

import com.metalsa.recibos.model.ImprimeRecibo;
import java.util.List;

/**
 *
 * @author edgar.leal
 */
public interface ManipulacionReciboProcedure {
    
    //<R17486>
     List<ImprimeRecibo> imprimeRecibo(String P_USUARIO, String P_FORMAT_DATE, 
             String P_REQUICISION, String P_ORDEN_COMPRA, String P_FINI, String P_FFIN, 
             String P_FOLIO,String P_ID_UENS, String P_MOSTRAR_RECIBOS, Integer P_PAGINA, Integer P_PAGSIZE );
     
     Integer cuentaRecibo(String P_USUARIO, String P_FORMAT_DATE, 
             String P_REQUICISION, String P_ORDEN_COMPRA, String P_FINI, String P_FFIN, 
             String P_FOLIO,String P_ID_UENS, String P_MOSTRAR_RECIBOS );
     //</R17486>
     
     List<ImprimeRecibo> detalleRecibo(String P_REQUICISION, String P_FOLIO, String P_FORMAT_DATE);
    
}
