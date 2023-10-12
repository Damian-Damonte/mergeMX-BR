/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.portalProveedor.repository;

import com.metalsa.portalProveedor.model.NvcTblDocsCotizacion;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mlopez
 */

@Repository
public interface NvcTblDocsCotizacionRepository extends PagingAndSortingRepository<NvcTblDocsCotizacion, Integer>{

    NvcTblDocsCotizacion getByIdDocumento(Integer idDocumento);
}
