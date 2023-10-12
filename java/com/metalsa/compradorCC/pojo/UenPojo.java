/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.compradorCC.pojo;

/**
 *
 * @author jose.jimenez07
 */
public class UenPojo {

    private Long id;

    private String nombre;

    private String currency;
    
    private String idioma;
    
    private String tipoAprobacion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getTipoAprobacion() {
        return tipoAprobacion;
    }

    public void setTipoAprobacion(String tipoAprobacion) {
        this.tipoAprobacion = tipoAprobacion;
    }
    
    

}
