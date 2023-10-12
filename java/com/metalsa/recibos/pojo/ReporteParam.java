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
public class ReporteParam {
    private String idUsuario;
    private String nombreReporte;
    private Integer idUen;
    private List<String> compradores;
    private List<Integer> proveedores;
    private List<String> requisitores;
    private List<Integer> recibidoen;
    private List<String> receptores;
    private Integer requisicion;
    private Integer numeroRecibo;
    private String fechaRequisicionInicial;
    private String fechaRequisicionFinal;
    private String fechaReciboInicial;
    private String fechaReciboFinal;
    private Integer ordenDeCompra;
    
    private Integer idReporte;

    public ReporteParam() {
        compradores = new ArrayList<>();
        proveedores = new ArrayList<>();
        requisitores = new ArrayList<>();
        recibidoen = new ArrayList<>();
        receptores = new ArrayList<>();
    }

    public Integer getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(Integer idReporte) {
        this.idReporte = idReporte;
    }
    
    public Integer getIdUen() {
        return idUen;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreReporte() {
        return nombreReporte;
    }

    public void setNombreReporte(String nombreReporte) {
        this.nombreReporte = nombreReporte;
    }
    

    public void setIdUen(Integer idUen) {
        this.idUen = idUen;
    }

    public List<String> getCompradores() {
        return compradores;
    }

    public void setCompradores(List<String> compradores) {
        this.compradores = compradores;
    }

    public List<Integer> getProveedores() {
        return proveedores;
    }

    public void setProveedores(List<Integer> proveedores) {
        this.proveedores = proveedores;
    }

    public Integer getRequisicion() {
        return requisicion;
    }

    public void setRequisicion(Integer requisicion) {
        this.requisicion = requisicion;
    }

    public List<String> getRequisitores() {
        return requisitores;
    }

    public void setRequisitores(List<String> requisitores) {
        this.requisitores = requisitores;
    }

    public Integer getNumeroRecibo() {
        return numeroRecibo;
    }

    public void setNumeroRecibo(Integer numeroRecibo) {
        this.numeroRecibo = numeroRecibo;
    }

    public String getFechaRequisicionInicial() {
        return fechaRequisicionInicial;
    }

    public void setFechaRequisicionInicial(String fechaRequisicionInicial) {
        this.fechaRequisicionInicial = fechaRequisicionInicial;
    }

    public String getFechaRequisicionFinal() {
        return fechaRequisicionFinal;
    }

    public void setFechaRequisicionFinal(String fechaRequisicionFinal) {
        this.fechaRequisicionFinal = fechaRequisicionFinal;
    }

    public String getFechaReciboInicial() {
        return fechaReciboInicial;
    }

    public void setFechaReciboInicial(String fechaReciboInicial) {
        this.fechaReciboInicial = fechaReciboInicial;
    }

    public String getFechaReciboFinal() {
        return fechaReciboFinal;
    }

    public void setFechaReciboFinal(String fechaReciboFinal) {
        this.fechaReciboFinal = fechaReciboFinal;
    }

    public List<Integer> getRecibidoen() {
        return recibidoen;
    }

    public void setRecibidoen(List<Integer> recibidoen) {
        this.recibidoen = recibidoen;
    }

    public List<String> getReceptores() {
        return receptores;
    }

    public void setReceptores(List<String> receptores) {
        this.receptores = receptores;
    }

    public Integer getOrdenDeCompra() {
        return ordenDeCompra;
    }

    public void setOrdenDeCompra(Integer ordenDeCompra) {
        this.ordenDeCompra = ordenDeCompra;
    }

    @Override
    public String toString() {
        return "ReporteParam{" + "idUsuario=" + idUsuario + ", nombreReporte=" + nombreReporte + ", idUen=" + idUen + ", compradores=" + compradores + ", proveedores=" + proveedores + ", requisitores=" + requisitores + ", recibidoen=" + recibidoen + ", receptores=" + receptores + ", requisicion=" + requisicion + ", numeroRecibo=" + numeroRecibo + ", fechaRequisicionInicial=" + fechaRequisicionInicial + ", fechaRequisicionFinal=" + fechaRequisicionFinal + ", fechaReciboInicial=" + fechaReciboInicial + ", fechaReciboFinal=" + fechaReciboFinal + ", ordenDeCompra=" + ordenDeCompra + ", idReporte=" + idReporte + '}';
    }

    
    
}
