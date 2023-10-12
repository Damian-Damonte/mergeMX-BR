package com.metalsa.aprobacion.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
@Data
public class CheckBudgetRequest {
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
    @JsonProperty("id_proyecto")
    private Long proyecto;
    @JsonProperty("id_tarea")
    private Long tarea;
    @JsonProperty("tipo_gasto")
    private String tipoGasto;
    @JsonProperty("aprobador")
    private String aprobador;//<T420188>
    @JsonProperty("id_usuario")
    private String idUsuario;   //Contralor
    @JsonProperty("pre_aprobacion")
    private boolean preAprobacion;   //Contralor
    @JsonProperty("id_uen")
    private Long idUen;//<T428940>
    @JsonProperty("control_level")
    private String ControlLevel;//<T428940>
    @JsonProperty("id_tipo_cargo")
    private Integer idTipoCargo;//<MULTI_CC>
    @JsonProperty("distribuciones")
    private List<DistribucionRequi> distribucionCC;//<MULTI_CC>   
}
