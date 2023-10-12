/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.utilerias.service;

import com.metalsa.utilerias.model.ClasificacionArbol;
import com.metalsa.utilerias.pojo.AprobarExamenPojo;
import com.metalsa.utilerias.pojo.MyHDRowSetPojo;
import java.util.List;

/**
 *
 * @author jose.jimenez07
 */
public interface UtileriasService {

    List<ClasificacionArbol> getReclasificacion(Long idSubFamila);

    void guardarCalificacion(MyHDRowSetPojo myHDRowSetPojo);
    AprobarExamenPojo aproboExamen(String url, String idUsuario, Integer idRol);

}
