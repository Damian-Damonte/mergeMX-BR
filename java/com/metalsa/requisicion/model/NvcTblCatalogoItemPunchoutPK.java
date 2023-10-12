package com.metalsa.requisicion.model;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class NvcTblCatalogoItemPunchoutPK implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ITEM_PUNCHOUT", nullable = false)
    private BigInteger idItemPunchout;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ITEM", nullable = false)
    private BigInteger idItem;

    public NvcTblCatalogoItemPunchoutPK() {
    }

    public BigInteger getIdItemPunchout() {
        return idItemPunchout;
    }

    public void setIdItemPunchout(BigInteger idItemPunchout) {
        this.idItemPunchout = idItemPunchout;
    }

    public BigInteger getIdItem() {
        return idItem;
    }

    public void setIdItem(BigInteger idItem) {
        this.idItem = idItem;
    }

    public NvcTblCatalogoItemPunchoutPK(BigInteger idItemPunchout, BigInteger idItem) {
        this.idItemPunchout = idItemPunchout;
        this.idItem = idItem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idItemPunchout != null ? idItemPunchout.hashCode() : 0);
        hash += (idItem != null ? idItem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NvcTblCatalogoItemPunchoutPK)) {
            return false;
        }
        NvcTblCatalogoItemPunchoutPK other = (NvcTblCatalogoItemPunchoutPK) object;
        if ((this.idItemPunchout == null && other.idItemPunchout != null) || (this.idItemPunchout != null && !this.idItemPunchout.equals(other.idItemPunchout))) {
            return false;
        }
        if ((this.idItem == null && other.idItem != null) || (this.idItem != null && !this.idItem.equals(other.idItem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.spx.entities.NvcTblCatalogoItemPunchoutPK[ idItemPunchout=" + idItemPunchout + ", idItem=" + idItem + " ]";
    }

}
