/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.portalProveedor.repository;

import com.metalsa.portalProveedor.model.UnidadMedida;
import com.metalsa.portalProveedor.model.Moneda;
import com.metalsa.portalProveedor.pojo.OaIvaUenPojo;
import com.metalsa.portalProveedor.pojo.PoVendorPojo;
import com.metalsa.portalProveedor.pojo.RfqPojo;
import com.metalsa.portalProveedor.pojo.RfqProveedorRequest;
import java.util.List;

/**
 *
 * @author mlopez
 */
public interface PortalProveedorRepository {

    List<RfqPojo> getCotizacionesProveedor(RfqProveedorRequest params);

    List<OaIvaUenPojo> getIvaByUen(Integer idUen);

    List<Moneda> getMonedasActivas();

    List<UnidadMedida> getUnidadMedidaByIdioma(String idioma);

    PoVendorPojo getProveedorByVendorEncoded(String vendorEncoded);

    boolean guardarIdCotizacion(String idRfq, String idCotizacion);
}
