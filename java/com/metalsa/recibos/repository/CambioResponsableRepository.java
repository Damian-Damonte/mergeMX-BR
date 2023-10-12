/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.repository;

import com.metalsa.recibos.model.ResultadoCambioResponsable;
import java.util.List;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author yair.nunez
 */
public interface CambioResponsableRepository extends PagingAndSortingRepository<ResultadoCambioResponsable,Long>, CambioResponsableProcedures{
    @Override
    @Procedure(name = "consultaCambioResponsable")
    List<ResultadoCambioResponsable> consultaCambioResponsable(@Param("p_id_requisicion") Integer idRequisicion,@Param("p_id_usuario") String idUsuario);
    
    @Override
    @Procedure(name = "actualizarRecibe")
    boolean actualizarRecibe( 
            @Param("p_id_requisicion") Integer idRequisicion,
            @Param("p_id_usuario") String idUsuario,
            @Param("p_tipo_usuario") String tipoUsuario);
}
