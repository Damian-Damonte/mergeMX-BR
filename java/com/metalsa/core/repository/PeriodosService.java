/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.repository;

import com.metalsa.core.model.Periodos;
import java.util.List;

/**
 *
 * @author jose.jimenez07
 */
public interface PeriodosService {

    List<Periodos> findByPorRangoAnioMes(Integer anioinicial,Integer aniofinal,Integer mesinicial,Integer mesfinal,String idioma);
}
