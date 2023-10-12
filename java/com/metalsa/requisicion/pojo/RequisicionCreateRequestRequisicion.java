/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.requisicion.pojo;

import java.util.List;

/**
 *
 * @author jose.espindola03
 */
public class RequisicionCreateRequestRequisicion {
    Integer idProducto;
    Integer idUen;
    Integer idProveedor;
    Integer idSiteProveedor;
    Integer idAlmacen;
    String fuente;
    String requisitor;
    String estatus;
    String datosDeAuditoria;
    String spx;
    String appOrigen;
    String tipo;
    List<RequisicionCreateRequestDetalle> detalles;
    
    public String getTipo() {
        return tipo;
    }
    
    public String getFuente() {
        return fuente;
    }
    
    public Integer getIdProducto() {
        return idProducto;
    }
    
    public String getRequisitor() {
        return requisitor;
    }
    
    public String getEstatus() {
        return this.estatus;
    }
    
    public String getDatosDeAuditoria() {
        return this.datosDeAuditoria;
    }
    
    public Integer getIdUen() {
        return this.idUen;
    }
    
    public String getSpx() {
        return this.spx;
    }
    
    public Integer getIdProveedor() {
        return this.idProveedor;
    }
    
    public Integer getIdSiteProveedor() {
        return this.idSiteProveedor;
    }
    
    public Integer getIdAlmacen() {
        return this.idAlmacen;
    }
    
    public List<RequisicionCreateRequestDetalle> getDetalles() {
        return this.detalles;
    }
    
    public void setDetalles(List<RequisicionCreateRequestDetalle> detalles) {
        this.detalles = detalles;
    }
    
    public boolean isFuente(String fuente) {
        return fuente != null ? fuente.equals(fuente) : false;
    }
    
    public String getAppOrigen() {
        return this.appOrigen;
    }
 }
