/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.repository;

import com.metalsa.recibos.model.ReporteRecibos;
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
public interface ReporteRecibosRepository extends PagingAndSortingRepository<ReporteRecibos,Long>,ReporteRecibosProcedures{
    
    
    
    @Override
    @Procedure(name = "generarReporteRecibos")
    boolean generarReporteRecibos( 
            @Param("EL_WHERE_ARMADO") String parametros,
            @Param("EL_WHERE_ARMADO2") String parametros2,
            @Param("P_ID_USUARIO") String idUsuario,
            @Param("P_NOMBRE_REPORTE") String nombreReporte);
    
    @Override
    @Procedure(name = "obtenerReporte")
    List<ReporteRecibos> obtenerReporte( @Param("p_id_reporte") Integer idReporte);
    
    @Override
    @Procedure(name = "eliminarReporte")
    boolean eliminarReporte( @Param("p_id_reporte") Integer idReporte);
    
    @Override
    @Procedure(name = "obtenerTimestamp")
    String obtenerTimestamp();
    
    @Override
    @Procedure(name = "obtenerWhereQuery")
    String obtenerWhereQuery(@Param("randomTimeStampa") String timestamp);
    
    @Override
    @Procedure(name = "insertaClave")
    String insertaClave(@Param("pllave") String llave,@Param("pvalor") String valor,@Param("randomTimeStampa") String timestamp,@Param("ptipodato") String tipoDato,@Param("p_proposito") String proposito,@Param("p_proposito") Integer orden);
    
    @Override
    @Procedure(name = "enviarCorreoProveedor")
    String enviarCorreoProveedor(@Param("p_id_session_tmp") String timestamp);
    
    
    
}
