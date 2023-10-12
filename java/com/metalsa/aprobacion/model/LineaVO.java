package com.metalsa.aprobacion.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

/**
 *
 * @author APOOD9272
 */
@Data
public class LineaVO {

    @JsonProperty("monto_extendido")
    private Double montoExtendido;

    @JsonProperty("id_partida")
    private Long idPartida;

    @JsonProperty("id_cuenta")
    private Long idCuenta;

    @JsonProperty("descripcion_item")
    private String descripcionItem;

    @JsonProperty("id_comprador")
    private String idComprador;

    @JsonProperty("id_moneda")
    private String idMoneda;

    @JsonProperty("nombre_proveedor")
    private String nombreProveedor;

    @JsonProperty("siguiente_aprobador")
    private String siguienteAprobador;

    private String razon;

    private String solucion;
    
    @JsonProperty("id_tipo_cargo")
    private Integer idTipoCargo;
    
    private List<DistribucionVO> distribuciones;

}
