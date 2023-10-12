/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.aprobacion.repository;

import com.metalsa.aprobacion.model.OaProyectoCuenta;
import com.metalsa.aprobacion.model.OaProyectoGastoCuenta;
import com.metalsa.aprobacion.model.OaProyectoTarea;
import com.metalsa.aprobacion.model.OaProyectos;
import com.metalsa.aprobacion.model.PresupuestoProyecto;
import java.util.List;

/**
 *
 * @author APOOD9272
 */
public interface ProyectoProcedures {

    List<OaProyectos> getProyectosUenUsuario(String idUsuario, Integer idUen);

    List<OaProyectoTarea> getTareasProyecto(Long idProyecto);

    List<OaProyectoGastoCuenta> getErogacionesProyecto(Long idProyecto, Long idTarea, String tipo, String reqAlm);

    List<OaProyectoCuenta> getCuentasProyecto(Long idProyecto, Long idTarea, String tipoErogacion, String lenguaje);
    
    List<String> getAprobadoresProyecto(Long idrequisicion);
    
    List<PresupuestoProyecto> getProyectosByIdUsuario(String idUsuario);
   
    List<PresupuestoProyecto> getProyectBudgetById(Integer idProyecto);
    
}
