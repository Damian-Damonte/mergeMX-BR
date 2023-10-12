/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;

/**
 *
 * @author yair.nunez
 */
public class ResultadoCambioResponsablePK implements Serializable {
    @Column(name="ID_REQUISICION")
    private Integer idRequisicion;

    @Column(name="ID_PARTIDA")
    private Integer idLinea;

    public Integer getIdRequisicion() {
        return idRequisicion;
    }

    public void setIdRequisicion(Integer idRequisicion) {
        this.idRequisicion = idRequisicion;
    }

    public Integer getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(Integer idLinea) {
        this.idLinea = idLinea;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.idRequisicion);
        hash = 37 * hash + Objects.hashCode(this.idLinea);
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
        final ResultadoCambioResponsablePK other = (ResultadoCambioResponsablePK) obj;
        if (!Objects.equals(this.idRequisicion, other.idRequisicion)) {
            return false;
        }
        return Objects.equals(this.idLinea, other.idLinea);
    }

    
    
    
}
