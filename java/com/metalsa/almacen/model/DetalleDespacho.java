package com.metalsa.almacen.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 *
 * @author APOMR10051
 */
@Data
public class DetalleDespacho implements Serializable {

    @SerializedName(value = "id_requisicion")
    private Integer idRequisicion;
    @SerializedName(value = "id_partida")
    private Integer idPartida;
    private String codigoProducto;
    private String descripcionProducto;
    private Double precio;
    private String fechaAprobacion;
    private String segmento1;
    private String segmento2;
    private String segmento3;
    private BigDecimal cantidadEntregada;
    private BigDecimal cantidadRequerida;
    private BigDecimal cantidadPendiente;
    private BigDecimal cantidadPorDespachar;
    private BigDecimal cantidadPorDespachaTem;
    private String reparable;
    private String garantia;
    private String subinventario;
    private String locator;
    private String numParteFabricante;
    //Atributos para despacho
    private Integer folio;
    private Integer consigFlag;
    @SerializedName(value = "id_usuario")
    private String idUsuario;
    @SerializedName(value = "id_usuario_recibe")
    private String idUsuarioRecibe;
    @SerializedName(value = "id_idioma")
    private String idIdioma;
    private String msgOut;

}
