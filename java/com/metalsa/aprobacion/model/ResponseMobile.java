/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.aprobacion.model;

import com.metalsa.contralor.model.Configuracion;
import com.metalsa.contralor.model.Valores;
import com.metalsa.core.model.CentroCostoUsuario;
import com.metalsa.core.model.Periodos;
import java.util.List;
import lombok.Data;

/**
 *
 * @author jose.jimenez07
 */
@Data
public class ResponseMobile {
    Configuracion confResp;
    Configuracion confDel;
    List<Periodos> periodos;
    Valores confTransferCc;
    Configuracion confTransferOtrosCc;
    List<CentroCostoUsuario> ccsOrigen;
    List<CentroCostoUsuario> ccsDestino;
    
}
