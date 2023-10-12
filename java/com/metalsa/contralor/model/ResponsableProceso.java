package com.metalsa.contralor.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Oscar del Angel
 */
@Entity
public class ResponsableProceso implements Serializable {

    @Id
    @Column(name = "id_usuario")
    private String idUsuario;

    @Column(name = "nombre_usuario")
    private String nombre_usuario;

    public ResponsableProceso() {
    }

    public ResponsableProceso(String idUsuario, String nombre_usuario) {
        this.idUsuario = idUsuario;
        this.nombre_usuario = nombre_usuario;
    }

    
    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    
    
    
    
}
