/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yair.nunez
 */
public class Requisicion {
    private Integer idRequisicion;
    private String fechaCreacion;
    private String comprador;
    private String requisitor;
    private String fuente;
    private Integer idUen;
    private Integer idCc;
    private String nombreCc;
    private String codigoCc;
    private String tipoRequisicion;
    private Integer lineasVencidas;
    private String notificaProveedor;
    List<Linea> lineas;
    private Integer pedidoEspecial;

    public Requisicion() {
        lineas = new ArrayList<>();
        lineasVencidas = 0;
    }

    public Integer getLineasVencidas() {
        return lineasVencidas;
    }

    public void setLineasVencidas(Integer lineasVencidas) {
        this.lineasVencidas = lineasVencidas;
    }
    
    public String getNotificaProveedor() {
        return notificaProveedor;
    }

    public void setNotificaProveedor(String notificaProveedor) {
        this.notificaProveedor = notificaProveedor;
    }
    
    

    public String getTipoRequisicion() {
        return tipoRequisicion;
    }

    public void setTipoRequisicion(String tipoRequisicion) {
        this.tipoRequisicion = tipoRequisicion;
    }
    
    public Integer getIdRequisicion() {
        return idRequisicion;
    }

    public void setIdRequisicion(Integer idRequisicion) {
        this.idRequisicion = idRequisicion;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getCodigoCc() {
        return codigoCc;
    }

    public void setCodigoCc(String codigoCc) {
        this.codigoCc = codigoCc;
    }
    
    

    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    public String getRequisitor() {
        return requisitor;
    }

    public void setRequisitor(String requisitor) {
        this.requisitor = requisitor;
    }

    public Integer getIdUen() {
        return idUen;
    }

    public void setIdUen(Integer idUen) {
        this.idUen = idUen;
    }

    public Integer getIdCc() {
        return idCc;
    }

    public void setIdCc(Integer idCc) {
        this.idCc = idCc;
    }

    public String getNombreCc() {
        return nombreCc;
    }

    public void setNombreCc(String nombreCc) {
        this.nombreCc = nombreCc;
    }
    
    public List<Linea> getLineas() {
        return lineas;
    }

    public void setLineas(List<Linea> lineas) {
        this.lineas = lineas;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public Integer getPedidoEspecial() {
        return pedidoEspecial;
    }

    public void setPedidoEspecial(Integer pedidoEspecial) {
        this.pedidoEspecial = pedidoEspecial;
    }
    
}
