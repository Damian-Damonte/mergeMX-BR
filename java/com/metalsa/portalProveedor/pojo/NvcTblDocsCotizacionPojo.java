/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.portalProveedor.pojo;

import com.metalsa.finanzas.model.AdjuntoPojo;
import java.util.List;
import lombok.Data;

/**
 *
 * @author mlopez
 */

@Data
public class NvcTblDocsCotizacionPojo {
    private Integer idCotizacion;
    private List<AdjuntoPojo> adjuntos;
}
