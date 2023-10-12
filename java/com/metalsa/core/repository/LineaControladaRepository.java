package com.metalsa.core.repository;

import com.metalsa.cart.model.NvcTblCarroCompra;

/**
 *
 * @author Gamaliel Espinoza M.
 */
public interface LineaControladaRepository {
    void register(NvcTblCarroCompra carro, Integer idRequisicion, Integer idPartida);
}
