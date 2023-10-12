package com.metalsa.requisicion.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author APOPH5586
 */
@Embeddable
public class ParametrosPorUenCiaPK implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_UEN", nullable = false)
    private long idUen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PARAMETRO", nullable = false)
    private long idParametro;

    public ParametrosPorUenCiaPK() {
    }

    public ParametrosPorUenCiaPK(long idUen, long idParametro) {
        this.idUen = idUen;
        this.idParametro = idParametro;
    }

    public long getIdUen() {
        return idUen;
    }

    public void setIdUen(long idUen) {
        this.idUen = idUen;
    }

    public long getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(long idParametro) {
        this.idParametro = idParametro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idUen;
        hash += (int) idParametro;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParametrosPorUenCiaPK)) {
            return false;
        }
        ParametrosPorUenCiaPK other = (ParametrosPorUenCiaPK) object;
        if (this.idUen != other.idUen) {
            return false;
        }
        if (this.idParametro != other.idParametro) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.admin.ParametrosPorUenCiaPK[ idUen=" + idUen + ", idParametro=" + idParametro + " ]";
    }

}
