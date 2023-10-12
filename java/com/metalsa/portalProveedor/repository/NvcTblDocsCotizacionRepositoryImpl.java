/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.portalProveedor.repository;

import com.metalsa.portalProveedor.model.NvcTblDocsCotizacion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jose.jimenez07
 */
public class NvcTblDocsCotizacionRepositoryImpl {
    
    @PersistenceContext
    private EntityManager em;
    
    public NvcTblDocsCotizacion getByIdDocumento(Integer idDocumento) {
        em.clear();
        List<NvcTblDocsCotizacion> result = em.createQuery("SELECT n FROM NvcTblDocsCotizacion n where n.idDocumento=:idDocumento", NvcTblDocsCotizacion.class)
                .setParameter("idDocumento", idDocumento).getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
}
