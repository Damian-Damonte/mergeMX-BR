/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.repository;

import com.metalsa.recibos.model.Requisitor;
import java.util.List;

/**
 *
 * @author yair.nunez
 */
public interface RequisitorProcedure {
    List<Requisitor> obtenerRequisitores(String idUsuario);
}
