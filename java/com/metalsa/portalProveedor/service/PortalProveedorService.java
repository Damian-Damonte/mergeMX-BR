/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.portalProveedor.service;

import com.metalsa.core.model.NvcTblOaProveedoresH;
import com.metalsa.portalProveedor.model.UnidadMedida;
import com.metalsa.portalProveedor.model.Moneda;
import com.metalsa.portalProveedor.model.NvcTblDocsCotizacion;
import com.metalsa.portalProveedor.model.NvcTblGastoAdicional;
import com.metalsa.portalProveedor.model.NvcTblReqLineaProv;
import com.metalsa.portalProveedor.pojo.NvcTblDocsCotizacionPojo;
import com.metalsa.portalProveedor.pojo.OaIvaUenPojo;
import com.metalsa.portalProveedor.pojo.PoVendorPojo;
import com.metalsa.portalProveedor.pojo.RfqLineaRequest;
import com.metalsa.portalProveedor.pojo.RfqPojo;
import com.metalsa.portalProveedor.pojo.RfqProveedorRequest;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author mlopez
 */
public interface PortalProveedorService {

    List<NvcTblOaProveedoresH> getProveedorByLikeName(String name, BigDecimal idUen);

    List<RfqPojo> getCotizacionesProveedor(RfqProveedorRequest request);

    List<OaIvaUenPojo> getIvaByUen(Integer idUen);

    List<Moneda> getMonedasActivas();

    List<UnidadMedida> getUnidadMedidaByIdioma(String idioma);

    List<NvcTblReqLineaProv> getLineaByRfqAndSupplier(RfqLineaRequest request);

    List<NvcTblGastoAdicional> getGastoAdicionalByIdReqLineaProv(Integer idReqLineaProv);

    boolean saveGastoAdicional(List<NvcTblGastoAdicional> gastos);

    boolean deleteGastoAdicional(NvcTblGastoAdicional gasto);

    boolean saveReqLineaProv(List<NvcTblReqLineaProv> lineas);

    boolean sendQuaotation(List<NvcTblReqLineaProv> lineas);

    boolean saveDocCotizacion(List<NvcTblDocsCotizacionPojo> cotizacion);

    boolean deleteDocCotizacion(NvcTblDocsCotizacion doc);

    PoVendorPojo getProveedorByVendorEncoded(String vendorEncoded);

    boolean guardarIdCotizacion(String idRfq, String idCotizacion);
}
