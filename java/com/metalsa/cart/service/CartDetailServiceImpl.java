package com.metalsa.cart.service;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.metalsa.aprobacion.model.CarroCompra;
import com.metalsa.utils.entities.NvcTblCarroCompraDetalle;

@Service
public class CartDetailServiceImpl implements CartDetailService{

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public NvcTblCarroCompraDetalle findOrCreate(CarroCompra carroCompra) {
        TypedQuery<NvcTblCarroCompraDetalle> query = em.createNamedQuery("NvcTblCarroCompraDetalle.findByIdCarroCom", NvcTblCarroCompraDetalle.class);        
        query.setParameter("idCarroCompra", carroCompra.getId());
        NvcTblCarroCompraDetalle item;
        try {
            item = query.getSingleResult();    
        } catch (NoResultException e) {                       
            item = new NvcTblCarroCompraDetalle();
            item.setIdCarroCompra(carroCompra);
            item.setIdTipoAsignacion(2);
            item.setUsuarioCreacion(carroCompra.getUsuario());
            item.setUsuarioActualizacion(carroCompra.getUsuario());
            item.setSegmentoProducto(0);
            item.setFechaActualizacion(new Date());
            item.setFechaCreacion(new Date());
            em.persist(item);
        }        

        return item;
    }
    
}
