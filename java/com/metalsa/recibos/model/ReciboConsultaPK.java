/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author edgar.leal
 */
public class ReciboConsultaPK implements Serializable{
    //@Column(name="id_requisicion")
    private static final long serialVersionUID = 1L;
    //@Column(name = "ID_REQUISICION")
    private Integer ID_REQUISICION;

    //@Column(name="id_partida")
    //@Column(name = "ID_PARTIDA")
    private Integer ID_PARTIDA;

    public Integer getID_REQUISICION() {
        return ID_REQUISICION;
    }

    public void setID_REQUISICION(Integer ID_REQUISICION) {
        this.ID_REQUISICION = ID_REQUISICION;
    }

    public Integer getID_PARTIDA() {
        return ID_PARTIDA;
    }

    public void setID_PARTIDA(Integer ID_PARTIDA) {
        this.ID_PARTIDA = ID_PARTIDA;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.ID_REQUISICION);
        hash = 37 * hash + Objects.hashCode(this.ID_PARTIDA);
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
        final ReciboConsultaPK other = (ReciboConsultaPK) obj;
        if (!Objects.equals(this.ID_REQUISICION, other.ID_REQUISICION)) {
            return false;
        }
        return Objects.equals(this.ID_PARTIDA, other.ID_PARTIDA);
    }
}
