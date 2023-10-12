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
public class RequisicionTipo {
    // private String name;
    private Integer tipo;
    private List<RequisicionPojo> requisiciones;
    
    public RequisicionTipo(Fuentes fuente) {
        this.tipo = fuente.getCodigo();
        this.requisiciones = new ArrayList<>();
    }
    
    public List<RequisicionPojo> getRequisiciones() {
        return this.requisiciones;
    }
    
    public boolean isAnyTipo(Integer... tipos) {
        for (Integer t : tipos) {
            if (this.tipo != null && this.tipo.equals(t)) {
                return true;
            }
        }
        return false;
    }
    
    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }
    
    public Integer getTipo() {
        return this.tipo;
    }
    
    public RequisicionPojo addRequisicion(
            RequisicionPojo requisicion
    ) {
        this.requisiciones.add(requisicion);
        return requisicion;
    }
}
