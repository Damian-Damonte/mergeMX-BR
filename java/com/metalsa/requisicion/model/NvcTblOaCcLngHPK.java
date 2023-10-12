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
public class NvcTblOaCcLngHPK implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_UEN", nullable = false)
    private BigInteger idUen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CC", nullable = false)
    private BigInteger idCc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "ID_LANG", nullable = false, length = 12)
    private String idLang;

    public NvcTblOaCcLngHPK() {
    }

    public NvcTblOaCcLngHPK(BigInteger idUen, BigInteger idCc, String idLang) {
        this.idUen = idUen;
        this.idCc = idCc;
        this.idLang = idLang;
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

    public String getIdLang() {
        return idLang;
    }

    public void setIdLang(String idLang) {
        this.idLang = idLang;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUen != null ? idUen.hashCode() : 0);
        hash += (idCc != null ? idCc.hashCode() : 0);
        hash += (idLang != null ? idLang.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NvcTblOaCcLngHPK)) {
            return false;
        }
        NvcTblOaCcLngHPK other = (NvcTblOaCcLngHPK) object;
        if ((this.idUen == null && other.idUen != null) || (this.idUen != null && !this.idUen.equals(other.idUen))) {
            return false;
        }
        if ((this.idCc == null && other.idCc != null) || (this.idCc != null && !this.idCc.equals(other.idCc))) {
            return false;
        }
        if ((this.idLang == null && other.idLang != null) || (this.idLang != null && !this.idLang.equals(other.idLang))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.suite.NvcTblOaCcLngHPK[ idUen=" + idUen + ", idCc=" + idCc + ", idLang=" + idLang + " ]";
    }
    
}
