/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.repository;

import com.metalsa.aprobacion.model.NvcTblOrganizacionesH;

/**
 *
 * @author jose.espindola03
 */
public interface OrganizacionesRepository {
    NvcTblOrganizacionesH getById(Integer id);
}
