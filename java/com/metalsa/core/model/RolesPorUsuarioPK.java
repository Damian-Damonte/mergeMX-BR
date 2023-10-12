/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author edgar.leal
 */
@Embeddable
public class RolesPorUsuarioPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ROL")
    private int idRol;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ID_USUARIO")
    private String idUsuario;

    public RolesPorUsuarioPK() {
    }

    public RolesPorUsuarioPK(int idRol, String idUsuario) {
        this.idRol = idRol;
        this.idUsuario = idUsuario;
    }

    public long getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idRol;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof RolesPorUsuarioPK)) {
            return false;
        }
        RolesPorUsuarioPK other = (RolesPorUsuarioPK) object;
        if (this.idRol != other.idRol) {
            return false;
        }
        return this.idUsuario.equals(other.idUsuario);
    }

    @Override
    public String toString() {
        return "com.metalsa.core.model.RolesPorUsuarioPK[ idRol=" + idRol + ", idUsuario=" + idUsuario + " ]";
    }
    
}
