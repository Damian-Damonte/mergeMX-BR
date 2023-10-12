package com.metalsa.core.repository;

import com.metalsa.catalogo.model.NvcTblCatalogoItem;
import com.metalsa.catalogo.model.NvcVItemsAll;
import com.metalsa.requisicion.model.NvcVmOaExistencias;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gamaliel Espinoza M.
 */
@Repository
public class ItemsRepositoryImpl implements ItemsRepository {
    private final EntityManager em;
    
    public ItemsRepositoryImpl(EntityManager em) {
        this.em = em;
    }
    
    @Override
    public NvcVItemsAll findByCodigo(String codigo) {
        return (NvcVItemsAll) em.createQuery(
                "SELECT e FROM NvcVItemsAll e " +
                        "WHERE e.codigoItem = :codigo")
                .setParameter("codigo", codigo)
                .getSingleResult();
    }
    
    @Override
    public NvcTblCatalogoItem findItemById(Integer idItem) {
        return (NvcTblCatalogoItem) em.createQuery(
                "SELECT e FROM NvcTblCatalogoItem e " +
                "WHERE e.idItem = :id")
                .setParameter("id", idItem)
                .getSingleResult();
    }
    
    @Override
    public NvcVItemsAll findById(Integer id) {
        return (NvcVItemsAll) em.createQuery(
                "SELECT e FROM CatalogItem e " +
                        "WHERE e.idItem = :id " +
                        "LIMIT 1")
                .setParameter("id", id)
                .getSingleResult();
    }
    
    @Override
    public NvcVItemsAll findOneByIdAndCodigo(Integer idItem, String codigo) {
        return (NvcVItemsAll) em.createQuery(
                "SELECT item " +
                "FROM com.metalsa.catalogo.model.NvcVItemsAll item " +
                "WHERE item.nvcVItemsAllPK.idItem=:idItem " +
                "AND item.codigoItem=:codigoItem")
                .setParameter("idItem", idItem)
                .setParameter("codigoItem", codigo)
                .getSingleResult();
    }
    
    @Override
    public NvcVItemsAll findViewByPk(Integer idItem, Integer idUen, Integer idAlmacen) {
        try {
            return (NvcVItemsAll) em.createQuery(
                "SELECT v " +
                "FROM com.metalsa.catalogo.model.NvcVItemsAll v " +
                "WHERE v.nvcVItemsAllPK.idItem = :idItem " +
                "AND v.nvcVItemsAllPK.uen = :idUen " +
                "AND v.nvcVItemsAllPK.idAlmacen = :idAlmacen")
                .setParameter("idItem", idItem)
                .setParameter("idUen", idUen)
                .setParameter("idAlmacen", idAlmacen)
                .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
    
    @Override
    public NvcVItemsAll findViewByItemUenFuente(Integer uen, Integer idItem, String fuente) {
        if (uen == null || idItem == null || fuente == null) {
            return null;
        }
        return (NvcVItemsAll) em.createQuery(
                "SELECT v " +
                "FROM NvcVItemsAll v " +
                "WHERE v.nvcVItemsAllPK.idItem = :idItem " +
                "AND v.nvcVItemsAllPK.uen = :idUen " +
                "AND v.fuente = :fuente")
                .setParameter("idItem", idItem)
                .setParameter("idUen", uen)
                .setParameter("fuente", fuente)
                .getSingleResult();
    }
    
    @Override
    public NvcVmOaExistencias getExistencia(
        Integer idProducto,
        Integer idUen,
        Integer idAlmacen
    ) {
        try {
            return (NvcVmOaExistencias) em.createQuery(
                    "SELECT n FROM NvcVmOaExistencias n "
                        + "WHERE n.nvcVmOaExistenciasPK.idProducto = :idProducto "
                        + " AND n.nvcVmOaExistenciasPK.idUen = :idUen "
                        + " AND n.nvcVmOaExistenciasPK.idAlmacen = :idAlmacen")
                    .setParameter("idProducto", idProducto)
                    .setParameter("idUen", idUen)
                    .setParameter("idAlmacen", idAlmacen)
                    .getSingleResult();
        } catch (NoResultException err) {
            return null;
        }
    }

}
