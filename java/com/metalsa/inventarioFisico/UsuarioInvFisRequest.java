/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.inventarioFisico;

import java.util.Objects;

/**
 *
 * @author hector.gutierrez02
 */
public class UsuarioInvFisRequest {

    String id_usuario;
    String nombre_usuario;

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UsuarioInvFisRequest u = (UsuarioInvFisRequest) obj;
        return id_usuario.equals(u.id_usuario) && nombre_usuario.equals(u.nombre_usuario);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.id_usuario);
        hash = 67 * hash + Objects.hashCode(this.nombre_usuario);
        return hash;
    }
}
