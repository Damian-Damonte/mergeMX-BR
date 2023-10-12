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
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
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
@Table(name = "UEN_POR_USUARIO")
@XmlRootElement
@NamedNativeQueries({
    @NamedNativeQuery(name = "UenPorUsuario.uenPorUsuario",
            query="select * from uen_por_usuario where id_usuario = ?1",
            resultClass = UenPorUsuario.class
    ),
    @NamedNativeQuery(name = "UenPorUsuario.uenPorUsuarioSelCot",
            query="select u.* " +
            "from uen_por_usuario u " +
            "join requisicion r on r.id_usuario = u.id_usuario and r.id_uen = u.id_uen " +
            "join detalle_de_requisicion d on d.id_estatus = 27 and d.id_requisicion = r.id_requisicion " +
            "where u.id_usuario = ?1 group by u.id_uen, u.id_usuario, u.uen_default",
            resultClass = UenPorUsuario.class
    )
})
@NamedQueries({
    @NamedQuery(name = "UenPorUsuario.findAll", query = "SELECT u FROM UenPorUsuario u")
    , @NamedQuery(name = "UenPorUsuario.findByIdUen", query = "SELECT u FROM UenPorUsuario u WHERE u.uenPorUsuarioPK.idUen = :idUen")
    , @NamedQuery(name = "UenPorUsuario.findByIdUsuario", query = "SELECT u FROM UenPorUsuario u WHERE u.uenPorUsuarioPK.idUsuario = :idUsuario")
    , @NamedQuery(name = "UenPorUsuario.findByUenDefault", query = "SELECT u FROM UenPorUsuario u WHERE u.uenDefault = :uenDefault")})
public class UenPorUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UenPorUsuarioPK uenPorUsuarioPK;
    @Size(max = 1)
    @Column(name = "UEN_DEFAULT")
    private String uenDefault;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private UserUenRol userUenRol;

    public UenPorUsuario() {
    }

    public UenPorUsuario(UenPorUsuarioPK uenPorUsuarioPK) {
        this.uenPorUsuarioPK = uenPorUsuarioPK;
    }

    public UenPorUsuario(int idUen, String idUsuario) {
        this.uenPorUsuarioPK = new UenPorUsuarioPK(idUen, idUsuario);
    }

    public UenPorUsuarioPK getUenPorUsuarioPK() {
        return uenPorUsuarioPK;
    }

    public void setUenPorUsuarioPK(UenPorUsuarioPK uenPorUsuarioPK) {
        this.uenPorUsuarioPK = uenPorUsuarioPK;
    }

    public String getUenDefault() {
        return uenDefault;
    }

    public void setUenDefault(String uenDefault) {
        this.uenDefault = uenDefault;
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
        hash += (uenPorUsuarioPK != null ? uenPorUsuarioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof UenPorUsuario)) {
            return false;
        }
        UenPorUsuario other = (UenPorUsuario) object;
        return this.uenPorUsuarioPK.equals(other.uenPorUsuarioPK);
    }

    @Override
    public String toString() {
        return "com.metalsa.core.model.UenPorUsuario[ uenPorUsuarioPK=" + uenPorUsuarioPK + " ]";
    }

}
