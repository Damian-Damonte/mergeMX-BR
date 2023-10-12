/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.aprobacion.repository;

import com.metalsa.aprobacion.model.CentroCosto;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author jose.jimenez07
 */
public interface CentroCostoInterface {
    List<CentroCosto> getAllDistinctCCS(String idioma);   
    List<CentroCosto> getAllByIdUenAndIdioma(Long uen, String idioma);//</R16788>
    List<CentroCosto> getAllByRespAndDel(Long uen, String usuario,String idioma);
}
