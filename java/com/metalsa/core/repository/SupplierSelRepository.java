/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.repository;

//import com.metalsa.core.model.SupplierSel;
import com.metalsa.core.model.SupplierSelSPX;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author APOJO9952
 */
@Repository
public interface SupplierSelRepository extends PagingAndSortingRepository<SupplierSelSPX, Long>, SupplierSelProcedures {

    @Procedure(name = "insertaComentarios")
    void returnReqBuyer(@Param("requisiciones") String requisiciones,
            @Param("comentario") String comentario,
            @Param("id_usuario") String id_usuario,
            @Param("modulo") String modulo,
            @Param("estatus") String estatus);

    @Procedure(name = "procesaAprobacionSeleccion")
    String procesaAprobacionSeleccion(@Param("PARAMETROS") String PARAMETROS,
            @Param("P_ID_USUARIO") String P_ID_USUARIO,
            @Param("requisiciones_a_procesar") String requisiciones_a_procesar);
//@Param("MSJ_USUARIO") String MSJ_USUARIO

    @Procedure(name = "envioEmails")
    void envioEmails(
            @Param("ID_REQUISICION") String ID_REQUISICION,
            @Param("ID_LINEA") Integer ID_LINEA);

//    Iterable<SupplierSelSPX> findAllSupCompras(int idRequisicion, int idPartida);
    void updatePartidasDetalle(int idRequisicion, int idPartida);

    void updatePartidasLinea(int idRequisicion, int idPartida);

    @Procedure(name = "prcRespDelRequisicion")
    String prcRespDelRequisicion(@Param("PARAM_ID_UEN") String param_id_uen,
            @Param("PARAM_LENGUAJE") String param_lenguaje,
            @Param("PARAM_CODIGO_CC") String param_codigo,
            @Param("PARAM_IDREQUISICION") String param_idrequisicion);

    Iterable<SupplierSelSPX> regresaAprobadoresProyecto(Integer idRequisicion);

}
