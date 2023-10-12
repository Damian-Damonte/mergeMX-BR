/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.search_engine.model;

/**
 *
 * @author jose.espindola03
 */

public class PendingOrder {
    
    private String numOrdenCompra;    
    private String fechaPromesa;

    public String getFechaPromesa() {
        return fechaPromesa;
    }
    public void setFechaPromesa(String fechaPromesa) {
        this.fechaPromesa = fechaPromesa;
    }

    public String getNumOrdenCompra() {
        return numOrdenCompra;
    }
    public void setNumOrdenCompra(String numOrdenCompra) {
        this.numOrdenCompra = numOrdenCompra;
    }

}
