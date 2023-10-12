package com.metalsa.requisicion.repository;

import com.metalsa.cart.model.NvcTblCarroCompra;

/**
 *
 * @author jose.espindola03
 */
public interface CarroCompraRepository {
    public void editSpot(NvcTblCarroCompra carro, Integer idPartida, Integer idRequisicion);
    public NvcTblCarroCompra getCarroById(Integer id);
    public NvcTblCarroCompra getCarroById(Long id);
    public com.metalsa.aprobacion.model.CarroCompra getCarroCompra(
            com.metalsa.catalogo.model.NvcVItemsAll item,
            String userId
    );
}
