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
public class NvcTblCategoriesLngPK implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CAT", nullable = false)
    private BigInteger idCat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "ID_IDIOMA", nullable = false, length = 10)
    private String idIdioma;

    public NvcTblCategoriesLngPK() {
    }

    public NvcTblCategoriesLngPK(BigInteger idCat, String idIdioma) {
        this.idCat = idCat;
        this.idIdioma = idIdioma;
    }

    public BigInteger getIdCat() {
        return idCat;
    }

    public void setIdCat(BigInteger idCat) {
        this.idCat = idCat;
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
        hash += (idCat != null ? idCat.hashCode() : 0);
        hash += (idIdioma != null ? idIdioma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NvcTblCategoriesLngPK)) {
            return false;
        }
        NvcTblCategoriesLngPK other = (NvcTblCategoriesLngPK) object;
        if ((this.idCat == null && other.idCat != null) || (this.idCat != null && !this.idCat.equals(other.idCat))) {
            return false;
        }
        if ((this.idIdioma == null && other.idIdioma != null) || (this.idIdioma != null && !this.idIdioma.equals(other.idIdioma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.presupuesto.NvcTblCategoriesLngPK[ idCat=" + idCat + ", idIdioma=" + idIdioma + " ]";
    }

}
