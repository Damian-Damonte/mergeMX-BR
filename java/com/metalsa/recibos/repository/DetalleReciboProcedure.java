/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.repository;

import com.metalsa.recibos.model.DetalleRecibo;
import java.util.List;

/**
 *
 * @author yair.nunez
 */
public interface DetalleReciboProcedure {
    List<DetalleRecibo> getDetalleRecibo(Integer idRequisicion, Integer idPartida);
}
