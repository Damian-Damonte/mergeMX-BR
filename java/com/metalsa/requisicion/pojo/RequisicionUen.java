/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.requisicion.pojo;

import java.util.List;
import java.util.ArrayList;
import com.metalsa.requisicion.model.Fuentes;

/**
 *
 * @author jose.espindola03
 */
public class RequisicionUen {
    private Integer id;
    private String nombre;
    private final List<RequisicionTipo> tipos;
    
    public RequisicionUen() {
        this.tipos = new ArrayList<>();
    }
    
    public List<RequisicionTipo> getTipos() {
        return tipos;
    }
    
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public RequisicionTipo add(RequisicionTipo tipo) {
        this.tipos.add(tipo);
        return tipo;
    }
    
    public RequisicionTipo createTipo(Fuentes fuente) {
        RequisicionTipo tipo = new RequisicionTipo(fuente);
        this.tipos.add(tipo);
        return tipo;
    } 
}
