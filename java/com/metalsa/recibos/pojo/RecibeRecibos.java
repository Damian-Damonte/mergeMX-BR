/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.pojo;

import java.util.List;

/**
 *
 * @author edgar.leal
 */
public class RecibeRecibos {
    
    List<PayloadRecibos> datos;
    String id_usuario;
    String idioma;

    public RecibeRecibos() {
    }

    public RecibeRecibos(List<PayloadRecibos> datos, String id_usuario) {
        this.datos = datos;
        this.id_usuario = id_usuario;
    }

    public List<PayloadRecibos> getDatos() {
        return datos;
    }

    public void setDatos(List<PayloadRecibos> datos) {
        this.datos = datos;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }
    
    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    

    
    
}
