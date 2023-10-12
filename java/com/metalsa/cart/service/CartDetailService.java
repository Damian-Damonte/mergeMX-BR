package com.metalsa.cart.service;

import com.metalsa.aprobacion.model.CarroCompra;
import com.metalsa.utils.entities.NvcTblCarroCompraDetalle;

public interface CartDetailService {
    
    public NvcTblCarroCompraDetalle findOrCreate(CarroCompra carroCompra);

}
