package com.metalsa.requisicion.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author APOJA5585
 */
@Embeddable
public class CcPorCompradorPK implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_UEN")
    private long idUen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CC")
    private long idCc;

    public CcPorCompradorPK() {
    }

    public CcPorCompradorPK(long idUen, long idCc) {
        this.idUen = idUen;
        this.idCc = idCc;
    }

    public long getIdUen() {
        return idUen;
    }

    public void setIdUen(long idUen) {
        this.idUen = idUen;
    }

    public long getIdCc() {
        return idCc;
    }

    public void setIdCc(long idCc) {
        this.idCc = idCc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idUen;
        hash += (int) idCc;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CcPorCompradorPK)) {
            return false;
        }
        CcPorCompradorPK other = (CcPorCompradorPK) object;
        if (this.idUen != other.idUen) {
            return false;
        }
        if (this.idCc != other.idCc) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.CcPorCompradorPK[ idUen=" + idUen + ", idCc=" + idCc + " ]";
    }

}
