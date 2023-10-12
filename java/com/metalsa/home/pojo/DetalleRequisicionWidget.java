/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.home.pojo;

import com.metalsa.aprobacion.model.DetalleRequisicion;

/**
 *
 * @author APOMR10051
 */
public class DetalleRequisicionWidget {

    private Long requisicion;
    private Long linea;
    private String descripcionProducto;
    private String estatusDescripcion;
    private Long ordenCompra;
    private String partidaOc;
    private String estatusOc;
    private String fuente;

    public DetalleRequisicionWidget(DetalleRequisicion dr) {
        this.requisicion = dr.getRequisicion();
        this.linea = dr.getLinea();
        this.descripcionProducto = dr.getDescripcionProducto();
        this.estatusDescripcion = dr.getEstatusDescripcion();
        this.ordenCompra = dr.getOrdenCompra();
        this.partidaOc = dr.getPartidaOc();
        this.estatusOc = dr.getEstatusOc();
        this.fuente = dr.getFuente().equals("K") || dr.getFuente().equals("L") || dr.getFuente().equals("D") ? "A" : dr.getFuente();
    }

    public Long getRequisicion() {
        return requisicion;
    }

    public void setRequisicion(Long requisicion) {
        this.requisicion = requisicion;
    }

    public Long getLinea() {
        return linea;
    }

    public void setLinea(Long linea) {
        this.linea = linea;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public String getEstatusDescripcion() {
        return estatusDescripcion;
    }

    public void setEstatusDescripcion(String estatusDescripcion) {
        this.estatusDescripcion = estatusDescripcion;
    }

    public Long getOrdenCompra() {
        return ordenCompra;
    }

    public void setOrdenCompra(Long ordenCompra) {
        this.ordenCompra = ordenCompra;
    }

    public String getPartidaOc() {
        return partidaOc;
    }

    public void setPartidaOc(String partidaOc) {
        this.partidaOc = partidaOc;
    }

    public String getEstatusOc() {
        return estatusOc;
    }

    public void setEstatusOc(String estatusOc) {
        this.estatusOc = estatusOc;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

}
