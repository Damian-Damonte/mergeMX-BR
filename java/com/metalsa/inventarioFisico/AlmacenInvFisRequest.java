/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.inventarioFisico;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author hector.gutierrez02
 */
public class AlmacenInvFisRequest implements Serializable {

    private Integer id_uen;
    private Integer id_localizacion;
    private Integer id_almacen;

    public Integer getId_uen() {
        return id_uen;
    }

    public void setId_uen(Integer id_uen) {
        this.id_uen = id_uen;
    }

    public Integer getId_localizacion() {
        return id_localizacion;
    }

    public void setId_localizacion(Integer id_localizacion) {
        this.id_localizacion = id_localizacion;
    }

    public Integer getId_almacen() {
        return id_almacen;
    }

    public void setId_almacen(Integer id_almacen) {
        this.id_almacen = id_almacen;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AlmacenInvFisRequest u = (AlmacenInvFisRequest) obj;
        return (this.id_uen == u.id_uen || (this.id_uen != null && this.id_uen.equals(u.id_uen))) &&
                (this.id_localizacion == u.id_localizacion || (this.id_localizacion != null && this.id_localizacion.equals(u.id_localizacion))) &&
                (this.id_almacen == u.id_almacen || (this.id_almacen != null && this.id_almacen.equals(u.id_almacen)));
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.id_uen);
        hash = 67 * hash + Objects.hashCode(this.id_localizacion);
        hash = 67 * hash + Objects.hashCode(this.id_almacen);
        return hash;
    }
}
