package com.metalsa.core.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 *
 * @author APOMR10051
 */
@Entity(name = "punchout_backorder")
public class Backorder implements Serializable {

    private static long serialVersionUID = 1L;
    @Id
    @NotNull
    @Column(name="id_backorder")
    private Integer idBackorder;
    
    @Column(name="id_requisicion")
    private Integer idRequisicion;

    @Column(name="id_partida")
    private Integer idPartida;

    @Column(name="cantidad")
    private Integer cantidad;

    @Column(name="tiempo_entrega")
    private Integer tiempoEntrega;
    
    @Transient
    private String udm;

    public Integer getIdBackorder() {
        return idBackorder;
    }

    public void setIdBackorder(Integer idBackorder) {
        this.idBackorder = idBackorder;
    }

    public Integer getIdRequisicion() {
        return idRequisicion;
    }

    public void setIdRequisicion(Integer idRequisicion) {
        this.idRequisicion = idRequisicion;
    }

    public Integer getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(Integer idPartida) {
        this.idPartida = idPartida;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getTiempoEntrega() {
        return tiempoEntrega;
    }

    public void setTiempoEntrega(Integer tiempoEntrega) {
        this.tiempoEntrega = tiempoEntrega;
    }

    public String getUdm() {
        return udm;
    }

    public void setUdm(String udm) {
        this.udm = udm;
    }
}
