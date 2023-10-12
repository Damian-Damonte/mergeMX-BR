/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.aprobacion.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

/**
 *
 * @author juanangelmunozolvera
 * @date 13/Nov/2019
 * <MDA_MULTICC>
 */
@Data
public class DistribucionRequi {
    @JsonProperty("codigo_cc")
    private String codigoCc;
    @JsonProperty("id_cc")
    private Long idCc;
    @JsonProperty("id_cuenta")
    private Long idCuenta;
    @JsonProperty("monto")
    private Double monto;
    @JsonProperty("porcentaje")
    private Double porcentaje;
    @JsonProperty("tipo_de_relacion")
    private String tipoDeRelacion;
}
