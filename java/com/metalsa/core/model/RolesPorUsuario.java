/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author edgar.leal
 */
@Entity
@Table(name = "ROLES_POR_USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RolesPorUsuario.findAll", query = "SELECT r FROM RolesPorUsuario r")
    , @NamedQuery(name = "RolesPorUsuario.findByIdRol", query = "SELECT r FROM RolesPorUsuario r WHERE r.rolesPorUsuarioPK.idRol = :idRol")
    , @NamedQuery(name = "RolesPorUsuario.findByIdUsuario", query = "SELECT r FROM RolesPorUsuario r WHERE r.rolesPorUsuarioPK.idUsuario = :idUsuario")
    , @NamedQuery(name = "RolesPorUsuario.findByDatosDeAuditoria", query = "SELECT r FROM RolesPorUsuario r WHERE r.datosDeAuditoria = :datosDeAuditoria")})
public class RolesPorUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RolesPorUsuarioPK rolesPorUsuarioPK;
    @Size(max = 50)
    @Column(name = "DATOS_DE_AUDITORIA")
    private String datosDeAuditoria;    
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private UserUenRol userUenRol;

    public RolesPorUsuario() {
    }

    public RolesPorUsuario(RolesPorUsuarioPK rolesPorUsuarioPK) {
        this.rolesPorUsuarioPK = rolesPorUsuarioPK;
    }

    public RolesPorUsuario(int idRol, String idUsuario) {
        this.rolesPorUsuarioPK = new RolesPorUsuarioPK(idRol, idUsuario);
    }

    public RolesPorUsuarioPK getRolesPorUsuarioPK() {
        return rolesPorUsuarioPK;
    }

    public void setRolesPorUsuarioPK(RolesPorUsuarioPK rolesPorUsuarioPK) {
        this.rolesPorUsuarioPK = rolesPorUsuarioPK;
    }

    public String getDatosDeAuditoria() {
        return datosDeAuditoria;
    }

    public void setDatosDeAuditoria(String datosDeAuditoria) {
        this.datosDeAuditoria = datosDeAuditoria;
    }

    public UserUenRol getUserUenRol() {
        return userUenRol;
    }

    public void setUserUenRol(UserUenRol userUenRol) {
        this.userUenRol = userUenRol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolesPorUsuarioPK != null ? rolesPorUsuarioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolesPorUsuario)) {
            return false;
        }
        RolesPorUsuario other = (RolesPorUsuario) object;
        return this.rolesPorUsuarioPK.equals(other.rolesPorUsuarioPK);
    }

    @Override
    public String toString() {
        return "com.metalsa.core.model.RolesPorUsuario[ rolesPorUsuarioPK=" + rolesPorUsuarioPK + " ]";
    }
    
}
