/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.requisicion.model;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author juliocisneros
 */
@Embeddable
public class DetalleDeRequisicionPK implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PARTIDA", nullable = false)
    private Long idPartida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_REQUISICION", nullable = false)
    private Long idRequisicion;

    public DetalleDeRequisicionPK() {
    }

    public DetalleDeRequisicionPK(Long idPartida, Long idRequisicion) {
        this.idPartida = idPartida;
        this.idRequisicion = idRequisicion;
    }

    public Long getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(Long idPartida) {
        this.idPartida = idPartida;
    }

    public Long getIdRequisicion() {
        return idRequisicion;
    }

    public void setIdRequisicion(Long idRequisicion) {
        this.idRequisicion = idRequisicion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPartida != null ? idPartida.hashCode() : 0);
        hash += (idRequisicion != null ? idRequisicion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleDeRequisicionPK)) {
            return false;
        }
        DetalleDeRequisicionPK other = (DetalleDeRequisicionPK) object;
        if ((this.idPartida == null && other.idPartida != null) || (this.idPartida != null && !this.idPartida.equals(other.idPartida))) {
            return false;
        }
        if ((this.idRequisicion == null && other.idRequisicion != null) || (this.idRequisicion != null && !this.idRequisicion.equals(other.idRequisicion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.rfq.DetalleDeRequisicionPK[ idPartida=" + idPartida + ", idRequisicion=" + idRequisicion + " ]";
    }

}
