package com.metalsa.requisicion.model;

import java.io.Serializable;
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
public class ParametrosConfigIdiomaPK implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PARAMETRO", nullable = false)
    private long idParametro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "ID_IDIOMA", nullable = false, length = 10)
    private String idIdioma;

    public ParametrosConfigIdiomaPK() {
    }

    public ParametrosConfigIdiomaPK(long idParametro, String idIdioma) {
        this.idParametro = idParametro;
        this.idIdioma = idIdioma;
    }

    public long getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(long idParametro) {
        this.idParametro = idParametro;
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
        hash += (int) idParametro;
        hash += (idIdioma != null ? idIdioma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParametrosConfigIdiomaPK)) {
            return false;
        }
        ParametrosConfigIdiomaPK other = (ParametrosConfigIdiomaPK) object;
        if (this.idParametro != other.idParametro) {
            return false;
        }
        if ((this.idIdioma == null && other.idIdioma != null) || (this.idIdioma != null && !this.idIdioma.equals(other.idIdioma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.admin.ParametrosConfigIdiomaPK[ idParametro=" + idParametro + ", idIdioma=" + idIdioma + " ]";
    }

}
