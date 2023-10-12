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
public class DcpCategoriasIdiomaPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CAT", nullable = false)
    private BigInteger idCat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "SC_ID", nullable = false, length = 10)
    private String scId;

    public DcpCategoriasIdiomaPK() {
    }

    public DcpCategoriasIdiomaPK(BigInteger idCat, String scId) {
        this.idCat = idCat;
        this.scId = scId;
    }

    public BigInteger getIdCat() {
        return idCat;
    }

    public void setIdCat(BigInteger idCat) {
        this.idCat = idCat;
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
        hash += (idCat != null ? idCat.hashCode() : 0);
        hash += (scId != null ? scId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DcpCategoriasIdiomaPK)) {
            return false;
        }
        DcpCategoriasIdiomaPK other = (DcpCategoriasIdiomaPK) object;
        if ((this.idCat == null && other.idCat != null) || (this.idCat != null && !this.idCat.equals(other.idCat))) {
            return false;
        }
        if ((this.scId == null && other.scId != null) || (this.scId != null && !this.scId.equals(other.scId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.admin.DcpCategoriasIdiomaPK[ idCat=" + idCat + ", scId=" + scId + " ]";
    }

}
