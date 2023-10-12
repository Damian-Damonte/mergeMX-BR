package com.metalsa.requisicion.repository;

import com.metalsa.cart.model.NvcTblCarroCompra;
import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.ParameterMode;

/**
 *
 * @author Gamaliel Espinoza M.
 */
public class CarroCompraRepositoryImpl implements CarroCompraRepository  {
    private final EntityManager em;
    
    private static final String SPOT_UPDATE_SP = "NVC_PKG_CARRO_SPX.SP_EDIT_SPOT_DCOMPRAS";
    
    public CarroCompraRepositoryImpl(EntityManager em) {
        this.em = em;
    }
    
    @Override
    public NvcTblCarroCompra getCarroById(Integer id) {
        return (NvcTblCarroCompra) em.createQuery(
                "SELECT d FROM com.metalsa.cart.model.NvcTblCarroCompra d "
                + "WHERE d.idCarroCompra = :id")
                .setParameter("id", id)
                .getSingleResult();
    }
    
    @Override
    public NvcTblCarroCompra getCarroById(Long id) {
        return getCarroById(id.intValue());
    }
    
    @Override
    public void editSpot(NvcTblCarroCompra carro, Integer idPartida, Integer idRequisicion) {
        StoredProcedureQuery sp = em.createStoredProcedureQuery(SPOT_UPDATE_SP);
        sp.registerStoredProcedureParameter("PARAM_ID_REQUISICION", Integer.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("PARAM_ID_PARTIDA", Integer.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("P_ID_CARRO", Integer.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("P_RETURN", String.class, ParameterMode.OUT);

        sp.setParameter("PARAM_ID_REQUISICION", idRequisicion);
        sp.setParameter("PARAM_ID_PARTIDA", idPartida);
        sp.setParameter("P_ID_CARRO", carro.getIdCarroCompra());

        sp.execute();
    }
    
     @Override
    public com.metalsa.aprobacion.model.CarroCompra getCarroCompra(
            com.metalsa.catalogo.model.NvcVItemsAll item,
            String userId
    ) {
        try {
            return (com.metalsa.aprobacion.model.CarroCompra) em.createQuery(
                "SELECT n " +
                "FROM CarroCompra n " +
                "WHERE n.item=:idItem " +
                "AND n.usuario=:idUsuario " +
                "AND n.idTipoPrecio=:idTipoPrecio " +
                "AND n.requisicion is null")
                .setParameter("idItem", item.getNvcVItemsAllPK().getIdItem().longValue())
                .setParameter("idTipoPrecio", item.getIdTipoPrecio())
                .setParameter("idUsuario", userId)
                .getSingleResult();
        } catch (javax.persistence.NoResultException ex) {
            return null;
        }
    }
}
