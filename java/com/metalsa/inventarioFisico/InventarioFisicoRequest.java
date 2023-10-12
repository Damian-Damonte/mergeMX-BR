/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.inventarioFisico;

import java.util.List;
import java.util.Map;

/**
 *
 * @author hector.gutierrez02
 */
public class InventarioFisicoRequest {
    
    private int activo;
    private Map<String, String> uen;
    private List<FuenteInvFisRequest> fuentes;
    private List<UsuarioInvFisRequest> usuarios;
<<<<<<< HEAD
=======
    private List<AlmacenInvFisRequest> almacenes;
>>>>>>> mexico

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public Map<String, String> getUen() {
        return uen;
    }

    public void setUen(Map<String, String> uen) {
        this.uen = uen;
    }

    public List<FuenteInvFisRequest> getFuentes() {
        return fuentes;
    }

    public void setFuentes(List<FuenteInvFisRequest> fuentes) {
        this.fuentes = fuentes;
    }

    public List<UsuarioInvFisRequest> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioInvFisRequest> usuarios) {
        this.usuarios = usuarios;
    }

<<<<<<< HEAD
=======
    public List<AlmacenInvFisRequest> getAlmacen() {
        return almacenes;
    }

    public void setAlmacen(List<AlmacenInvFisRequest> almacenes) {
        this.almacenes = almacenes;
    }

>>>>>>> mexico
}
