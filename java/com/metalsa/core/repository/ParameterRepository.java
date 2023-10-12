/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.repository;

import com.metalsa.core.pojo.UenParameter;

/**
 *
 * @author jose.espindola03
 */
public interface ParameterRepository {
    boolean checkUenAndFuente(String param, Integer idUen, String fuente);
    UenParameter findParameterUenByUenName(Integer idUen, String paramName);
}
