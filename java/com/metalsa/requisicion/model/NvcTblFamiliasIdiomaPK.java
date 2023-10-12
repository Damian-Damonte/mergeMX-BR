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
public class NvcTblFamiliasIdiomaPK implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_FAMILIA", nullable = false)
    private BigInteger idFamilia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "ID_IDIOMA", nullable = false, length = 4)
    private String idIdioma;

    public NvcTblFamiliasIdiomaPK() {
    }

    public NvcTblFamiliasIdiomaPK(BigInteger idFamilia, String idIdioma) {
        this.idFamilia = idFamilia;
        this.idIdioma = idIdioma;
    }

    public BigInteger getIdFamilia() {
        return idFamilia;
    }

    public void setIdFamilia(BigInteger idFamilia) {
        this.idFamilia = idFamilia;
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
        hash += (idFamilia != null ? idFamilia.hashCode() : 0);
        hash += (idIdioma != null ? idIdioma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NvcTblFamiliasIdiomaPK)) {
            return false;
        }
        NvcTblFamiliasIdiomaPK other = (NvcTblFamiliasIdiomaPK) object;
        if ((this.idFamilia == null && other.idFamilia != null) || (this.idFamilia != null && !this.idFamilia.equals(other.idFamilia))) {
            return false;
        }
        if ((this.idIdioma == null && other.idIdioma != null) || (this.idIdioma != null && !this.idIdioma.equals(other.idIdioma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.NvcTblFamiliasIdiomaPK[ idFamilia=" + idFamilia + ", idIdioma=" + idIdioma + " ]";
    }

}
