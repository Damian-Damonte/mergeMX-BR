/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.pojo;

import java.util.Objects;

/**
 *
 * @author edgar.leal
 */
public class Quotation {
    
    private Integer id_requisicion;
    private Integer id_partida;
    private String descripcion_producto;
    private Double cantidad_requerida;
    private String id_unidad_de_medida;
    private String valor;
    private Integer prov;
    private String urgente;
    private Integer active;
    private String motivo_regreso;

    public Quotation() {
    }

    public Integer getId_requisicion() {
        return id_requisicion;
    }

    public void setId_requisicion(Integer id_requisicion) {
        this.id_requisicion = id_requisicion;
    }

    public Integer getId_partida() {
        return id_partida;
    }

    public void setId_partida(Integer id_partida) {
        this.id_partida = id_partida;
    }

    public String getDescripcion_producto() {
        return descripcion_producto;
    }

    public void setDescripcion_producto(String descripcion_producto) {
        this.descripcion_producto = descripcion_producto;
    }

    public Double getCantidad_requerida() {
        return cantidad_requerida;
    }

    public void setCantidad_requerida(Double cantidad_requerida) {
        this.cantidad_requerida = cantidad_requerida;
    }

    public String getId_unidad_de_medida() {
        return id_unidad_de_medida;
    }

    public void setId_unidad_de_medida(String id_unidad_de_medida) {
        this.id_unidad_de_medida = id_unidad_de_medida;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Integer getProv() {
        return prov;
    }

    public void setProv(Integer prov) {
        this.prov = prov;
    }

    public String getUrgente() {
        return urgente;
    }

    public void setUrgente(String urgente) {
        this.urgente = urgente;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getMotivo_regreso() {
        return motivo_regreso;
    }

    public void setMotivo_regreso(String motivo_regreso) {
        this.motivo_regreso = motivo_regreso;
    }

    @Override
    public String toString() {
        return "Quotation{" + "id_requisicion=" + id_requisicion + ", id_partida=" + id_partida + ", descripcion_producto=" + descripcion_producto + ", cantidad_requerida=" + cantidad_requerida + ", id_unidad_de_medida=" + id_unidad_de_medida + ", valor=" + valor + ", prov=" + prov + ", urgente=" + urgente + ", active=" + active + ", motivo_regreso=" + motivo_regreso + '}';
    }
    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.id_requisicion);
        hash = 79 * hash + Objects.hashCode(this.id_partida);
        hash = 79 * hash + Objects.hashCode(this.descripcion_producto);
        hash = 79 * hash + Objects.hashCode(this.cantidad_requerida);
        hash = 79 * hash + Objects.hashCode(this.id_unidad_de_medida);
        hash = 79 * hash + Objects.hashCode(this.valor);
        hash = 79 * hash + Objects.hashCode(this.prov);
        hash = 79 * hash + Objects.hashCode(this.urgente);
        hash = 79 * hash + Objects.hashCode(this.active);
        hash = 79 * hash + Objects.hashCode(this.motivo_regreso);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Quotation other = (Quotation) obj;
        if (!Objects.equals(this.descripcion_producto, other.descripcion_producto)) {
            return false;
        }
        if (!Objects.equals(this.id_unidad_de_medida, other.id_unidad_de_medida)) {
            return false;
        }
        if (!Objects.equals(this.valor, other.valor)) {
            return false;
        }
        if (!Objects.equals(this.urgente, other.urgente)) {
            return false;
        }
        if (!Objects.equals(this.motivo_regreso, other.motivo_regreso)) {
            return false;
        }
        if (!Objects.equals(this.id_requisicion, other.id_requisicion)) {
            return false;
        }
        if (!Objects.equals(this.id_partida, other.id_partida)) {
            return false;
        }
        if (!Objects.equals(this.cantidad_requerida, other.cantidad_requerida)) {
            return false;
        }
        if (!Objects.equals(this.prov, other.prov)) {
            return false;
        }
        return Objects.equals(this.active, other.active);
    }
    
    
    
}
