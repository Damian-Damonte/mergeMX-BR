package com.metalsa.cart.repository;

import com.metalsa.aprobacion.model.CarroCompra;
import com.metalsa.cart.model.NvcTblCarroCompra;
import com.metalsa.catalogo.model.NvcVItemsAll;
import com.metalsa.catalogo.model.NvcVItemsAllPK;
import com.metalsa.utils.entities.NvcVCarroCompra;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
/**
 *
 * @author Gamaliel Espinoza M.
 */
@Repository
public class CartRepositoryImpl implements CartRepository {
    @Autowired
    EntityManager em;
            
    @Override
    public NvcTblCarroCompra findOneById(Integer id) {
        return (NvcTblCarroCompra) em.createQuery(
                "SELECT n FROM NvcTblCarroCompra n " +
                "WHERE n.idCarroCompra = :id")
                .setParameter("id", id)
                .getSingleResult();
    }
    
    @Override
    public List<NvcVCarroCompra> findAllViewsByUserId(String userId) {
        List<NvcVCarroCompra> items = em.createQuery(
                "SELECT n FROM NvcVCarroCompra n " +
                "WHERE n.idUsuarioCreacion = :uid AND n.idRequisicion = null")
                .setParameter("uid", userId)
                .getResultList();
        System.out.println("findAllViewsByUserId: items.lengtht = " + items.size());
        return items;
    }
    
    @Override
    public com.metalsa.cart.model.NvcTblCarroCompra findCartById(Integer id) {
        return (com.metalsa.cart.model.NvcTblCarroCompra) em.createQuery(
                "SELECT n FROM com.metalsa.cart.model.NvcTblCarroCompra n " +
                "WHERE n.idCarroCompra = :id")
                .setParameter("id", id)
                .getSingleResult();
    }
    
    @Override
    public CarroCompra findCarroByInterUen(NvcVItemsAll item, String userId, boolean isInteruen) {
        TypedQuery<CarroCompra> query = em.createQuery(
                String.format(
                    "SELECT n " +
                    "FROM CarroCompra n " +
                    "WHERE n.itemAlmacen = :itemAlmacen " +
                    "AND n.%s = :idUen " +
                    "AND n.almacen = :idAlmacen " +
                    "and n.usuario = :idUsuario",
                   isInteruen ? "uenSurtidora" : "uen"
                ),
                CarroCompra.class
        );
        
        NvcVItemsAllPK pk = item.getNvcVItemsAllPK();
        
        query.setParameter("idAlmacen", pk.getIdAlmacen().longValue());
        query.setParameter("itemAlmacen", pk.getIdItem().longValue());
        query.setParameter("idUsuario", userId);
        query.setParameter("idUen", pk.getUen().longValue());
        
        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
