package com.metalsa.core.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 *
 * @author APOOD9272
 */
@Entity(name = "Rol")
public class Rol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Column(name="id_rol")
    private Integer id;
    
    @Column(name="nombre_rol")
    private String nombreRol;
    
    @Column(name="nivel_del_rol")
    private Integer nivelRol;

    @Column(name="datos_de_auditoria")
    private String datosDeAuditoria;

    @Column(name="mascara")
    private String mascara;

    @Column(name="activo")
    private String activo;
    
    @Column(name="clave")
    private String clave;
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rol)) {
            return false;
        }
        Rol other = (Rol) object;
        return !(this.id == null && other.id != null || this.id != null && !this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.metalsa.core.model.Rol[ id=" + id + " ]";
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public Integer getNivelRol() {
        return nivelRol;
    }

    public void setNivelRol(Integer nivelRol) {
        this.nivelRol = nivelRol;
    }

    public String getDatosDeAuditoria() {
        return datosDeAuditoria;
    }

    public void setDatosDeAuditoria(String datosDeAuditoria) {
        this.datosDeAuditoria = datosDeAuditoria;
    }

    public String getMascara() {
        return mascara;
    }

    public void setMascara(String mascara) {
        this.mascara = mascara;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
 
    
    
}
