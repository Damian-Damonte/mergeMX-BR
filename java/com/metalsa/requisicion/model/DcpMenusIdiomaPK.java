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
public class DcpMenusIdiomaPK implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_MENU", nullable = false)
    private BigInteger idMenu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "SC_ID", nullable = false, length = 10)
    private String scId;

    public DcpMenusIdiomaPK() {
    }

    public DcpMenusIdiomaPK(BigInteger idMenu, String scId) {
        this.idMenu = idMenu;
        this.scId = scId;
    }

    public BigInteger getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(BigInteger idMenu) {
        this.idMenu = idMenu;
    }

    public String getScId() {
        return scId;
    }

    public void setScId(String scId) {
        this.scId = scId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMenu != null ? idMenu.hashCode() : 0);
        hash += (scId != null ? scId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DcpMenusIdiomaPK)) {
            return false;
        }
        DcpMenusIdiomaPK other = (DcpMenusIdiomaPK) object;
        if ((this.idMenu == null && other.idMenu != null) || (this.idMenu != null && !this.idMenu.equals(other.idMenu))) {
            return false;
        }
        if ((this.scId == null && other.scId != null) || (this.scId != null && !this.scId.equals(other.scId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.admin.DcpMenusIdiomaPK[ idMenu=" + idMenu + ", scId=" + scId + " ]";
    }

}
