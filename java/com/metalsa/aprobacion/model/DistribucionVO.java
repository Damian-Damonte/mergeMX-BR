/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.aprobacion.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 *
 * @author juanangelmunozolvera
 */

@Data
public class DistribucionVO {
    @JsonProperty("id_cc")
    private Integer idCC;
    
    @JsonProperty("tipo_de_relacion")
    private String tipoRelacion;
    
    @JsonProperty("codigo_cc")
    private String codigoCc;
    
    @JsonProperty("id_cuenta")
    private Integer idCuenta;
    
    @JsonProperty("monto")
    private Double monto;
    
    @JsonProperty("porcentaje")
    private Double porcentaje;
}
