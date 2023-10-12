/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.requisicion.service;

import com.metalsa.aprobacion.model.Usuario;
import com.metalsa.aprobacion.model.Requisicion;
import com.metalsa.core.model.NvcTblOaProveedoresH;
import com.metalsa.requisicion.pojo.RequisicionFad;
import com.metalsa.requisicion.pojo.RequisicionFadRequest;
import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author miguel.rdz
 */
public interface ConsRequisicionService {

    List<RequisicionFad> getRequisFad(RequisicionFadRequest request);
    
    List<NvcTblOaProveedoresH> getLikeByUenWithFad(String uens, String query);
    
    List<Usuario> getUserFadByTipo(String uens, String tipo);
    
    List<RequisicionFad> findFadByFilters(RequisicionFadRequest request);
    
    public ByteArrayInputStream exportToExcel(RequisicionFadRequest request, Locale locale);
    
    public Requisicion createRequisicion(Requisicion requisicion);
    
}
