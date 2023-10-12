package com.metalsa.aprobacion.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
@Data
public class ApprovalRequestItem {
    @JsonProperty("id_requisicion")
    private Long requisicion;
    @JsonProperty("id_partida")
    private Long linea;
    @JsonProperty("monto_extendido")
    private Double monto;
    @JsonProperty("id_moneda")
    private String moneda;
    @JsonProperty("id_cuenta")
    private Long cuenta;
}
