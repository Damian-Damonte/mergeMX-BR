package com.metalsa.cart.repository;

import com.metalsa.aprobacion.model.CarroCompra;
import java.util.List;
import com.metalsa.cart.model.NvcTblCarroCompra;
import com.metalsa.catalogo.model.NvcVItemsAll;
import com.metalsa.utils.entities.NvcVCarroCompra;

/**
 *
 * @author Gamaliel Espinoza M.
 */
public interface CartRepository {
    NvcTblCarroCompra findOneById(Integer id);
    List<NvcVCarroCompra> findAllViewsByUserId(String userId);
    com.metalsa.cart.model.NvcTblCarroCompra findCartById(Integer id);

    default CarroCompra findCarroByInterUen(NvcVItemsAll item, String userId, boolean isInteruen) {
        return null;
    }
}
