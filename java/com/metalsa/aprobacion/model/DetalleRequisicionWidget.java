package com.metalsa.aprobacion.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Clase para mapeo de detalle de requisicion en el widget
 */
//<R17226>
@Entity(name = "detalle_de_requisicion_widget")
@IdClass(DetalleRequisicionWidget.Key.class)
public class DetalleRequisicionWidget implements Serializable {

    @Id
    @Column(name = "id_requisicion")
    private Long requisicion;
    @Id
    @Column(name = "id_partida")
    private Long linea;
    @Column(name = "descripcion_producto")
    private String descripcionProducto;
    @Column(name = "desc_estatus")
    private String descEstatus;
    @Column(name = "id_orden_de_compra")
    private Long ordenCompra;
    @Column(name = "id_partida_oc")
    private String partidaOc;
    @Column(name = "estatus_oc")
    private String estatusOc;
    @Column(name = "fuente")
    private String fuente;
    @Column(name = "pedido_especial")
    private Integer pedidoEspecial;
    //<RELEASE ARG>
    public DetalleRequisicionWidget() {

    }

    public DetalleRequisicionWidget(Long requisicion, Long linea, Long ordenCompra) {
        this.requisicion = requisicion;
        this.linea = linea;
        this.ordenCompra = ordenCompra;
    }

    public Long getRequisicion() {
        return requisicion;
    }

    public void setRequisicion(Long requisicion) {
        this.requisicion = requisicion;
    }

    public Long getLinea() {
        return linea;
    }

    public void setLinea(Long linea) {
        this.linea = linea;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public String getDescEstatus() {
        return descEstatus;
    }

    public void setDescEstatus(String descEstatus) {
        this.descEstatus = descEstatus;
    }

    public Long getOrdenCompra() {
        return ordenCompra;
    }

    public void setOrdenCompra(Long ordenCompra) {
        this.ordenCompra = ordenCompra;
    }

    public String getPartidaOc() {
        return partidaOc;
    }

    public void setPartidaOc(String partidaOc) {
        this.partidaOc = partidaOc;
    }

    public String getEstatusOc() {
        return estatusOc;
    }

    public void setEstatusOc(String estatusOc) {
        this.estatusOc = estatusOc;
    }

    public String getFuente() {
        return this.fuente.equals("K") || this.fuente.equals("L") || this.fuente.equals("D") ? "A" : this.fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public Integer getPedidoEspecial() {
        return pedidoEspecial;
    }

    public void setPedidoEspecial(Integer pedidoEspecial) {
        this.pedidoEspecial = pedidoEspecial;
    }

    /**
     * Llave embebida para JPA
     */
    @Data
    public static class Key implements Serializable {

        @Id
        @Column(name = "id_requisicion")
        private Long requisicion;

        @Id
        @Column(name = "id_partida")
        private Long linea;
    }
}
//</R17226>
