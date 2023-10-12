/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.model;

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
public class UenPorUsuarioReciboPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_UEN", nullable = false)
    private int idUen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ID_USUARIO", nullable = false, length = 10)
    private String idUsuario;

    public UenPorUsuarioReciboPK() {
    }

    public UenPorUsuarioReciboPK(int idUen, String idUsuario) {
        this.idUen = idUen;
        this.idUsuario = idUsuario;
    }

    public long getIdUen() {
        return idUen;
    }

    public void setIdUen(int idUen) {
        this.idUen = idUen;
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
        hash += (int) idUen;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof UenPorUsuarioReciboPK)) {
            return false;
        }
        UenPorUsuarioReciboPK other = (UenPorUsuarioReciboPK) object;
        if (this.idUen != other.idUen) {
            return false;
        }
        return this.idUsuario.equals(other.idUsuario);
    }

    @Override
    public String toString() {
        return "com.metalsa.core.model.UenPorUsuarioPK[ idUen=" + idUen + ", idUsuario=" + idUsuario + " ]";
    }
    
}