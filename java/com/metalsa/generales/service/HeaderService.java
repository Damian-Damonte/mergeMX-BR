/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.generales.service;

import com.metalsa.generales.model.NvcTblRolPerfil;
import com.metalsa.generales.model.PerAllPeopleF;

/**
 *
 * @author miguel.rdz
 */
public interface HeaderService {
    PerAllPeopleF getEmpInfo(Long personId);
    Iterable<NvcTblRolPerfil> getRolesPerfil(String idUsuario);
    Iterable<NvcTblRolPerfil> getRolesDisponibles(String idUsuario);
    String canShowGuide(String idUsuario);
}
