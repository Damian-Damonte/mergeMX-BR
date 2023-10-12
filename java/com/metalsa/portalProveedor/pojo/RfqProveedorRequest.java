/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.portalProveedor.pojo;

import java.util.Date;

/**
 *
 * @author mlopez
 */
public class RfqProveedorRequest {
    private int idProveedor;
    private int uenSelected;
    private String startDate;
    private String endDate;
    private String idioma;

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }
    
    public int getUenSelected() {
        return uenSelected;
    }

    public void setUenSelected(int uenSelected) {
        this.uenSelected = uenSelected;
    }
    
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
    
    
}
