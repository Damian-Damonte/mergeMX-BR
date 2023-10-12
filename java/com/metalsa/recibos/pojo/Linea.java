/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.pojo;

import com.metalsa.recibos.model.Uom;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yair.nunez
 */
public class Linea {
    private Integer numeroLinea;
    private Integer idRequisicion;
    private String urgente;
    private Integer idOrdenCompra;
    private String descripcion;
    private String fechaRecibo;
    private Integer numeroRecibo;
    private String fechaNecesidad;
    private String fechaVencimiento;
    private Double cantidad;
    private Double cantidadRecibida;
    private Double cantidadPorRecibir;
    private String udm;
    private String nombreProveedor;
    private String receptor;
    private String estatus;
    private Integer idPartidaOc;
    private Integer idLocalizacion;
    private Integer idUen;
    private Integer idProveedor;
    private Integer idCc;
    private String comprador;
    private String fechaRequisicion;
    private String requisitor;
    private List<ReciboParcial> recibos;
    private String notificaProveedor;
    private List<Uom> listaUom;
    private Integer provTieneContacto;
    

    

    public Linea() {
        recibos = new ArrayList<>();
        listaUom = new ArrayList<>();
        cantidadRecibida=0.0;
    }

    public Integer getProvTieneContacto() {
        return provTieneContacto;
    }

    public void setProvTieneContacto(Integer provTieneContacto) {
        this.provTieneContacto = provTieneContacto;
    }
    
    public String getNotificaProveedor() {
        return notificaProveedor;
    }

    public void setNotificaProveedor(String notificaProveedor) {
        this.notificaProveedor = notificaProveedor;
    }

    public List<Uom> getListaUom() {
        return listaUom;
    }

    public void setListaUom(List<Uom> listaUom) {
        this.listaUom = listaUom;
    }
    
    public List<ReciboParcial> getRecibos() {
        return recibos;
    }

    public void setRecibos(List<ReciboParcial> recibos) {
        this.recibos = recibos;
    }
    
    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    public String getFechaRequisicion() {
        return fechaRequisicion;
    }

    public void setFechaRequisicion(String fechaRequisicion) {
        this.fechaRequisicion = fechaRequisicion;
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

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Integer getIdCc() {
        return idCc;
    }

    public void setIdCc(Integer idCc) {
        this.idCc = idCc;
    }
    
    public Integer getIdLocalizacion() {
        return idLocalizacion;
    }

    public void setIdLocalizacion(Integer idLocalizacion) {
        this.idLocalizacion = idLocalizacion;
    }
    
    

    public Integer getIdRequisicion() {
        return idRequisicion;
    }

    public void setIdRequisicion(Integer idRequisicion) {
        this.idRequisicion = idRequisicion;
    }
    
    
    public Integer getIdPartidaOc() {
        return idPartidaOc;
    }

    public void setIdPartidaOc(Integer idPartidaOc) {
        this.idPartidaOc = idPartidaOc;
    }
    
    
    public Integer getNumeroLinea() {
        return numeroLinea;
    }

    public void setNumeroLinea(Integer numeroLinea) {
        this.numeroLinea = numeroLinea;
    }

    public String getFechaRecibo() {
        return fechaRecibo;
    }

    public void setFechaRecibo(String fechaRecibo) {
        this.fechaRecibo = fechaRecibo;
    }

    public Integer getNumeroRecibo() {
        return numeroRecibo;
    }

    public void setNumeroRecibo(Integer numeroRecibo) {
        this.numeroRecibo = numeroRecibo;
    }

    public String getReceptor() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }
    
    

    public String getUrgente() {
        return urgente;
    }

    public void setUrgente(String urgente) {
        this.urgente = urgente;
    }

    public Integer getIdOrdenCompra() {
        return idOrdenCompra;
    }

    public void setIdOrdenCompra(Integer idOrdenCompra) {
        this.idOrdenCompra = idOrdenCompra;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaNecesidad() {
        return fechaNecesidad;
    }

    public void setFechaNecesidad(String fechaNecesidad) {
        this.fechaNecesidad = fechaNecesidad;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
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

    public String getUdm() {
        return udm;
    }

    public void setUdm(String udm) {
        this.udm = udm;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
    
    
}
