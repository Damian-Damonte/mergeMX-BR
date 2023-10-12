/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.metalsa.core.model;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author APOPH5586
 */
@Embeddable
public class NvcTblOaCcHPK implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_UEN", nullable = false)
    private BigInteger idUen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CC", nullable = false)
    private BigInteger idCc;

    public NvcTblOaCcHPK() {
    }

    public NvcTblOaCcHPK(BigInteger idUen, BigInteger idCc) {
        this.idUen = idUen;
        this.idCc = idCc;
    }

    public BigInteger getIdUen() {
        return idUen;
    }

    public void setIdUen(BigInteger idUen) {
        this.idUen = idUen;
    }

    public BigInteger getIdCc() {
        return idCc;
    }

    public void setIdCc(BigInteger idCc) {
        this.idCc = idCc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUen != null ? idUen.hashCode() : 0);
        hash += (idCc != null ? idCc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NvcTblOaCcHPK)) {
            return false;
        }
        NvcTblOaCcHPK other = (NvcTblOaCcHPK) object;
        if ((this.idUen == null && other.idUen != null) || (this.idUen != null && !this.idUen.equals(other.idUen))) {
            return false;
        }
        if ((this.idCc == null && other.idCc != null) || (this.idCc != null && !this.idCc.equals(other.idCc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.admin.NvcTblOaCcHPK[ idUen=" + idUen + ", idCc=" + idCc + " ]";
    }
    
}
