/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.aprobacion.repository;

import com.metalsa.aprobacion.model.GastoAdicional;
import com.metalsa.aprobacion.model.RedicionRequisicionVO;
import com.metalsa.aprobacion.model.ReqPorAprobar;
import com.metalsa.core.model.OaUens;
import com.metalsa.core.model.Uens;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import org.springframework.data.jpa.repository.Modifying;

/**
 * @author jose.rivera02
 */
public interface ReqPorAprobarRepository extends PagingAndSortingRepository<ReqPorAprobar, Long>, ReqPorAprobarProcedures {

    /**
     * tipos de procedimientos
     */
    enum APPROVAL_TYPE {
        POR_APROBAR,
        POR_PREAPROBAR,
        POR_SUPERAPROBAR,
        POR_FUERARANGO,
        POR_CRITICAS
    };

    @Procedure(name = "getGastosAdicionales")
    @Override
    List<GastoAdicional> getGastosAdicionales(@Param("p_id_requisicion") Integer idRequisicion, @Param("p_id_partida") Integer idPartida, @Param("p_id_idioma") String idIdioma, @Param("p_aprobador") String aprobador);

    @Procedure(name = "regresarRequisiciones")
    Integer regresarRequisicion(
            @Param("p_requisicion") Long requisicion,
            @Param("p_partida") Long partida,
            @Param("p_comentario") String comentario,
            @Param("p_aprobador") String aprobador,
            @Param("p_modulo") String modulo
    );

    @Procedure(name = "cancelarRequisiciones")
    Integer cancelarRequisiciones(
            @Param("requisiciones") String requisiciones,
            @Param("comentario") String comentario,
            @Param("aprobador") String aprobador,
            @Param("p_modulo") String modulo
    );

    List<Uens> getUensSuperAprobador(String idUsuario);

    List<Integer> getUensPreAprobador(String idUsuario);
    
    Integer editarRequisicionPreAprobacion(RedicionRequisicionVO partida);

    @Override
    Integer preaprobarRequisicion(Long requisicion, Long linea, String aprobador,String modulo);
    
    @Override
    Integer aprobarRequisicion(Long requisicion, Long linea, String aprobador,String modulo, Integer idCuenta);
    
    @Override
    Integer iniAprobarRequisicion(Long requisicion, Long linea, String aprobador,String modulo, Integer idCuenta);

    @Override
    void bloquearDetalle(Long requisicion, Long linea, Integer idCuenta,String estado);

    @Query(nativeQuery = true, value = "select pkg_iso.sl_uen_encuesta(:p_requisicion) from dual")
    String verificarISO(@Param("p_requisicion") Long requisicion);

    @Query(nativeQuery = true, value = "select pkg_iso.up_status_req(:p_requisicion, :p_partida) from dual")
    String aprobarISO(@Param("p_requisicion") Long requisicion, @Param("p_partida") Long linea);

    @Query(name = "ReqPorAprobar.obtenerPerfil")
    String obtenerValorPerfil(@Param("id_usuario") String idUsuario, @Param("id_notificacion") Integer idNotificacion);

    @Query(name = "ReqPorAprobar.countRequisicionCancApro")
    int countRequisicionCancApro(@Param("id_requisicion") Integer idRequisicion);

    @Modifying
    @Query(name = "DetalleRequisicion.updateCuenta")
    int updateCuenta(Long idRequisicion, Long partida, String segmento1,
            String segmento2, String segmento3, String segmento4, String segmento5, String segmento6,
            String segmento7, String segmento8, Long idCuenta
    );
    
    //<RELEASE ARG>
    @Query(name = "ReqPorAprobar.getDetalleRequisicion")
    List<ReqPorAprobar> getDetallePorAprobar(Integer idRequisicion);

    @Query(name = "ReqPorAprobar.getOneByIdRequisicion")
    ReqPorAprobar getRequisicion(Integer idRequisicion);
    //</RELEASE ARG>
    @Query(name = "ReqPorAprobar.getByIdRequisicion")
    ReqPorAprobar getByIdRequisicion (@Param("id_requisicion")Integer idRequisicion,@Param("id_partida")Integer idPartida);

    @Query(name = "OaUens.findMainValuesById")
    OaUens findMainValuesById(@Param("id_uen") Long uen);
    

}
