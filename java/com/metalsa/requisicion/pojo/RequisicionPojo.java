/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.requisicion.pojo;

import java.util.List;
import java.util.Date;

/**
 *
 * @author jose.espindola03
 */
public class RequisicionPojo {
    Integer idUen;
    Integer idProveedor;
    Integer idSiteProveedor;
    Integer idAlmacen;
    Integer idCarroCompra;
    Integer idProyecto;
    String requisitor;
    String nombreRequisitor;
    String fuente;
    String estatus;
    String datosDeAuditoria;
    String spx;
    String appOrigen;
    String tipo;
    Integer idRequisicion;
    String idUsuarioCreacion;
    Date fechaRequisicion;
    List<DetalleDeRequisicionPojo> detalles;
    Integer idCentroCostos;
    Integer tipoRecibo;
    String nombreProveedor;
    private String nombreSite;
    
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String getFuente() {
        return fuente;
    }
    
    public void setFuente(String fuente) {
        this.fuente = fuente;
    }
    
    public String getEstatus() {
        return this.estatus;
    }
    
    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
    
    public String getDatosDeAuditoria() {
        return this.datosDeAuditoria;
    }
    
    public void setDatosDeAuditoria(String auditoria) {
        this.datosDeAuditoria = auditoria;
    }
    
    public Integer getIdUen() {
        return this.idUen;
    }
    
    public void setIdUen(Integer uen) {
        this.idUen = uen;
    }
    
    public String getSpx() {
        return this.spx;
    }
    
    public void setSpx(String spx) {
        this.spx = spx;
    }
    
    public Integer getIdProveedor() {
        return this.idProveedor;
    }
    
    public void setIdProveedor(Integer id) {
        this.idProveedor = id;
    }
    
    public Integer getIdSiteProveedor() {
        return this.idSiteProveedor;
    }
    
    public void setIdSiteProveedor(Integer id) {
        this.idSiteProveedor = id;
    }
    
    public Integer getIdAlmacen() {
        return this.idAlmacen;
    }
    
    public void setIdAlmacen(Integer id) {
        this.idAlmacen = id;
    }
    
    public List<DetalleDeRequisicionPojo> getDetalles() {
        return this.detalles;
    }
    
    public void setDetalles(List<DetalleDeRequisicionPojo> detalles) {
        this.detalles = detalles;
    }
    
    public void setDetalleDeRequisicionPojos(List<DetalleDeRequisicionPojo> detalles) {
        this.detalles = detalles;
    }
    
    public boolean isFuente(String fuente) {
        return fuente != null ? fuente.equals(fuente) : false;
    }
    
    public String getAppOrigen() {
        return this.appOrigen;
    }
    
    public void setAppOrigen(String origen) {
        this.appOrigen = origen;
    }
    
    public Integer getIdRequisicion() {
        return this.idRequisicion;
    }
    
    public void setIdRequisicion(Integer id) {
        this.idRequisicion = id;
    }
    
    public Integer getIdCarroCompra() {
        return this.idCarroCompra;
    }
    
    public void setIdCarroCompra(Integer id) {
        this.idCarroCompra = id;
    }
    
    public String idUsaurioCreacion() {
        return this.idUsuarioCreacion;
    }
    
    public void setIdUsuarioCreacion(String id) {
        this.idUsuarioCreacion = id;
    }
    
    public void setRequisitor(String requisitor) {
        this.requisitor = requisitor;
    }
    
    public String getRequisitor() {
        return this.requisitor;
    }
    
    public void setNombreRequisitor(String requisitor) {
        this.nombreRequisitor = requisitor;
    }
    
    public String getNombreRequisitor() {
        return this.nombreRequisitor;
    }
    
    public Date getFechaRequisicion() {
        return this.fechaRequisicion;
    }
    
    public void setFechaRequisicion(Date fecha) {
        this.fechaRequisicion = fecha;
    }
    
    public Integer getIdCentroCostos() {
        return this.idCentroCostos;
    }
    
    public void setIdCentroCostos(Integer id) {
        this.idCentroCostos = id;
    }
    
    public Integer getTipoRecibo() {
        return this.tipoRecibo;
    }
    
    public void setTipoRecibo(Integer tipo) {
        this.tipoRecibo = tipo;
    }
    
    public Integer getIdProyecto() {
        return this.idProyecto;
    }
    
    public void setIdProyecto(Integer id) {
        this.idProyecto = id;
    }
    
    public String getNombreProveedor() {
        return this.nombreProveedor;
    }
    
    public void setNombreProveedor(String nombre) {
        this.nombreProveedor = nombre;
    }
    
    public String getNombreSite() {
        return this.nombreSite;
    }
    
    public void setNombreSite(String nombre) {
        this.nombreSite = nombre;
    }

 }
