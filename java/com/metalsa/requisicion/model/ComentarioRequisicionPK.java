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
 * @author Gamaliel Espinoza M.
 */
@Embeddable
public class ComentarioRequisicionPK implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_REQUISICION", nullable = false)
    private BigInteger idRequisicion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_LINEA", nullable = false)
    private BigInteger idLinea;

    public ComentarioRequisicionPK() {
    }

    public ComentarioRequisicionPK(BigInteger idRequisicion, BigInteger idLinea) {
        this.idRequisicion = idRequisicion;
        this.idLinea = idLinea;
    }

    public BigInteger getIdRequisicion() {
        return idRequisicion;
    }

    public void setIdRequisicion(BigInteger idRequisicion) {
        this.idRequisicion = idRequisicion;
    }

    public BigInteger getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(BigInteger idLinea) {
        this.idLinea = idLinea;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRequisicion != null ? idRequisicion.hashCode() : 0);
        hash += (idLinea != null ? idLinea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComentarioRequisicionPK)) {
            return false;
        }
        ComentarioRequisicionPK other = (ComentarioRequisicionPK) object;
        if ((this.idRequisicion == null && other.idRequisicion != null) || (this.idRequisicion != null && !this.idRequisicion.equals(other.idRequisicion))) {
            return false;
        }
        if ((this.idLinea == null && other.idLinea != null) || (this.idLinea != null && !this.idLinea.equals(other.idLinea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.ComentarioRequisicionPK[ idRequisicion=" + idRequisicion + ", idLinea=" + idLinea + " ]";
    }

}
