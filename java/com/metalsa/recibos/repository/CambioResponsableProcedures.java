/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.repository;

import com.metalsa.recibos.model.ResultadoCambioResponsable;
import java.util.List;

/**
 *
 * @author yair.nunez
 */
public interface CambioResponsableProcedures {
    List<ResultadoCambioResponsable> consultaCambioResponsable(Integer idRequisicion,String idUsuario);
    boolean actualizarRecibe(Integer idRequisicion, String idUsuario, String tipoUsuario);
}
