/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.repository;

import com.metalsa.recibos.model.ReporteRecibos;
import java.util.List;


/**
 *
 * @author yair.nunez
 */
public interface ReporteRecibosProcedures {
    boolean generarReporteRecibos(String parametros,String parametros2,String idUsuario,String nombreReporte);
    List<ReporteRecibos> obtenerReporte(Integer idReporte);
    boolean eliminarReporte(Integer idReporte);
    String obtenerTimestamp();
    String obtenerWhereQuery(String timestamp);
    String insertaClave(String llave,String valor, String timestamp,String tipoDato,String proposito,Integer orden);
    String enviarCorreoProveedor(String timestamp);
}
