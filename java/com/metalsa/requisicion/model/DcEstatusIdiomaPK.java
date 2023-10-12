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
import javax.validation.constraints.Size;

/**
 *
 * @author APOPH5586
 */
@Embeddable
public class DcEstatusIdiomaPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ESTATUS", nullable = false)
    private BigInteger idEstatus;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "ID_IDIOMA", nullable = false, length = 10)
    private String idIdioma;

    public DcEstatusIdiomaPK() {
    }

    public DcEstatusIdiomaPK(BigInteger idEstatus, String idIdioma) {
        this.idEstatus = idEstatus;
        this.idIdioma = idIdioma;
    }

    public BigInteger getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(BigInteger idEstatus) {
        this.idEstatus = idEstatus;
    }

    public String getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(String idIdioma) {
        this.idIdioma = idIdioma;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstatus != null ? idEstatus.hashCode() : 0);
        hash += (idIdioma != null ? idIdioma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DcEstatusIdiomaPK)) {
            return false;
        }
        DcEstatusIdiomaPK other = (DcEstatusIdiomaPK) object;
        if ((this.idEstatus == null && other.idEstatus != null) || (this.idEstatus != null && !this.idEstatus.equals(other.idEstatus))) {
            return false;
        }
        if ((this.idIdioma == null && other.idIdioma != null) || (this.idIdioma != null && !this.idIdioma.equals(other.idIdioma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.admin.DcEstatusIdiomaPK[ idEstatus=" + idEstatus + ", idIdioma=" + idIdioma + " ]";
    }

}
