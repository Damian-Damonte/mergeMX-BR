/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.matriz.pagos.service;

import com.metalsa.aprobacion.model.NvcTblOrganizacionesH;
import com.metalsa.matriz.pagos.dto.FiltrosMatrizPagosDTO;
import com.metalsa.matriz.pagos.dto.ResponseMatrizDTO;
import java.io.ByteArrayInputStream;


/**
 *
 * @author jose.jimenez07
 */
public interface MatrizPagosService {

    ResponseMatrizDTO getDatosMatrizPagos(FiltrosMatrizPagosDTO filtros);

    Iterable<NvcTblOrganizacionesH> getUens(String idUsuario);

    ByteArrayInputStream getReporteExcel(FiltrosMatrizPagosDTO filtrosMatrizPagosDTO);

}
