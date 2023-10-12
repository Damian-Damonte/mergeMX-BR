package com.metalsa.almacen.service;

import com.metalsa.almacen.model.Despacho;
import com.metalsa.almacen.model.DetalleDespacho;
import com.metalsa.almacen.pojo.Almacen;
import com.metalsa.almacen.pojo.DespachoRequest;
import com.metalsa.aprobacion.model.NvcTblOrganizacionesH;
import com.metalsa.recibos.model.Requisitor;
import org.springframework.data.repository.query.Param;
/**
 *
 * @author miguel.rdz
 */
public interface DespachoService {
    
    Iterable<NvcTblOrganizacionesH> getUensConReserva(@Param("idUsuario") String idUsuario);
    
    Iterable<Almacen> getAlmacenesPorUen(@Param("idUsuario") String idUsuario, @Param("uens") String uens);
    
    Iterable<Requisitor> getRequisitoresPorUen(@Param("idUsuario") String idUsuario, @Param("uens") String uens);
    
    Iterable<Despacho> getDespachosPorUsuario(@Param("idUsuario") String idUsuario);
    
    Iterable<Despacho> getDespachos(DespachoRequest req);
    
    Iterable<DetalleDespacho> getDetalleDespachos(DespachoRequest req);
    
    DetalleDespacho despachar(DetalleDespacho req);
    
    Iterable<Despacho> getPendDespachoByFilters(DespachoRequest req);
    
    Integer cancelarDespacho(DetalleDespacho req,String razon);
    
}
