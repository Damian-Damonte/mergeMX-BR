/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.utils.entities;

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
public class NvcVmOaProyGastoCuentaPK implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TAREA", nullable = false)
    private long idTarea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CUENTA", nullable = false)
    private BigInteger idCuenta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "TIPO", nullable = false, length = 3)
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RESOURCE_ID", nullable = false)
    private long resourceId;

    public NvcVmOaProyGastoCuentaPK() {
    }

    public NvcVmOaProyGastoCuentaPK(long idTarea, BigInteger idCuenta, String tipo, long resourceId) {
        this.idTarea = idTarea;
        this.idCuenta = idCuenta;
        this.tipo = tipo;
        this.resourceId = resourceId;
    }

    public long getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(long idTarea) {
        this.idTarea = idTarea;
    }

    public BigInteger getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(BigInteger idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public long getResourceId() {
        return resourceId;
    }

    public void setResourceId(long resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idTarea;
        hash += (idCuenta != null ? idCuenta.hashCode() : 0);
        hash += (tipo != null ? tipo.hashCode() : 0);
        hash += (int) resourceId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NvcVmOaProyGastoCuentaPK)) {
            return false;
        }
        NvcVmOaProyGastoCuentaPK other = (NvcVmOaProyGastoCuentaPK) object;
        if (this.idTarea != other.idTarea) {
            return false;
        }
        if ((this.idCuenta == null && other.idCuenta != null) || (this.idCuenta != null && !this.idCuenta.equals(other.idCuenta))) {
            return false;
        }
        if ((this.tipo == null && other.tipo != null) || (this.tipo != null && !this.tipo.equals(other.tipo))) {
            return false;
        }
        if (this.resourceId != other.resourceId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.suite.NvcVmOaProyGastoCuentaPK[ idTarea=" + idTarea + ", idCuenta=" + idCuenta + ", tipo=" + tipo + ", resourceId=" + resourceId + " ]";
    }

}
