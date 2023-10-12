/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.pojo;

/**
 *
 * @author yair.nunez
 */
public class ReciboParcial {
    private Integer idRequisicion;
    private Integer numeroLinea;
    private Integer numeroRecibo;
    private String fechaRecibo;
    private Double cantidadRecibida;
    private Double cantidadPorRecibir;
    private Integer recibidoEn;

    public ReciboParcial() {
    }

    public Integer getIdRequisicion() {
        return idRequisicion;
    }

    public void setIdRequisicion(Integer idRequisicion) {
        this.idRequisicion = idRequisicion;
    }
    
    public Integer getNumeroLinea() {
        return numeroLinea;
    }

    public void setNumeroLinea(Integer numeroLinea) {
        this.numeroLinea = numeroLinea;
    }

    public Integer getNumeroRecibo() {
        return numeroRecibo;
    }

    public void setNumeroRecibo(Integer numeroRecibo) {
        this.numeroRecibo = numeroRecibo;
    }

    public String getFechaRecibo() {
        return fechaRecibo;
    }

    public void setFechaRecibo(String fechaRecibo) {
        this.fechaRecibo = fechaRecibo;
    }

    public Double getCantidadRecibida() {
        return cantidadRecibida;
    }

    public void setCantidadRecibida(Double cantidadRecibida) {
        this.cantidadRecibida = cantidadRecibida;
    }

    public Double getCantidadPorRecibir() {
        return cantidadPorRecibir;
    }

    public void setCantidadPorRecibir(Double cantidadPorRecibir) {
        this.cantidadPorRecibir = cantidadPorRecibir;
    }

    public Integer getRecibidoEn() {
        return recibidoEn;
    }

    public void setRecibidoEn(Integer recibidoEn) {
        this.recibidoEn = recibidoEn;
    }
    
    @Override
    public String toString() {
        return "ReciboParcial{" + "idRequisicion=" + idRequisicion + ", numeroLinea=" + numeroLinea + ", numeroRecibo=" + numeroRecibo + ", fechaRecibo=" + fechaRecibo + ", cantidadRecibida=" + cantidadRecibida + ", cantidadPorRecibir=" + cantidadPorRecibir + '}';
    }

}
