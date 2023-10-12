/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.reportes.repository;

import com.metalsa.reportes.model.Incrementos;
import com.metalsa.reportes.model.Parametros;
import java.util.List;

/**
 *
 * @author Oscar del Angel
 */
interface IncrementosProcedure {

    List<Incrementos> getReporte(Parametros parametros);
}
