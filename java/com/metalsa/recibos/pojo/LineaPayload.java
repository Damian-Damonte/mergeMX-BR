/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.pojo;


/**
 *
 * @author edgar.leal
 */
public class LineaPayload {
    

    private Integer numero_linea; 

    private Integer id_requisicion;

    private String urgente;

    private Integer id_orden_compra;

    private String descripcion;

    private String fecha_necesidad;

    private String fecha_vencimiento;

    private Double cantidad;

    private Double cantidad_recibida;

    private Double cantidad_por_recibir;

    private String udm;
    
    private String udm_cambiada;

    private String nombre_proveedor;

    private String estatus;

    private Integer id_partida_oc; 

    private Integer id_localizacion;
    
    private Integer id_uen;
    
    private Integer id_proveedor;
    
    private Boolean cerrar;
    
    private Boolean notificar;
    
    private String c_cerrado;
    
    private Double aux_cantidad;
    
    
    

    public LineaPayload() {
    }

    public String getUdm_cambiada() {
        return udm_cambiada;
    }

    public void setUdm_cambiada(String udm_cambiada) {
        this.udm_cambiada = udm_cambiada;
    }
    
    public Double getAux_cantidad() {
        return aux_cantidad;
    }

    public void setAux_cantidad(Double aux_cantidad) {
        this.aux_cantidad = aux_cantidad;
    }

    public String getC_cerrado() {
        return c_cerrado;
    }

    public void setC_cerrado(String c_cerrado) {
        this.c_cerrado = c_cerrado;
    }
    
    

    public Boolean getCerrar() {
        return cerrar;
    }

    public void setCerrar(Boolean cerrar) {
        this.cerrar = cerrar;
    }

    public Boolean getNotificar() {
        return notificar;
    }

    public void setNotificar(Boolean notificar) {
        this.notificar = notificar;
    }
    
    

    public Integer getId_uen() {
        return id_uen;
    }

    public void setId_uen(Integer id_uen) {
        this.id_uen = id_uen;
    }

    public Integer getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(Integer id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    
    public Integer getNumero_linea() {
        return numero_linea;
    }

    public void setNumero_linea(Integer numero_linea) {
        this.numero_linea = numero_linea;
    }

    public Integer getId_requisicion() {
        return id_requisicion;
    }

    public void setId_requisicion(Integer id_requisicion) {
        this.id_requisicion = id_requisicion;
    }

    public String getUrgente() {
        return urgente;
    }

    public void setUrgente(String urgente) {
        this.urgente = urgente;
    }

    public Integer getId_orden_compra() {
        return id_orden_compra;
    }

    public void setId_orden_compra(Integer id_orden_compra) {
        this.id_orden_compra = id_orden_compra;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha_necesidad() {
        return fecha_necesidad;
    }

    public void setFecha_necesidad(String fecha_necesidad) {
        this.fecha_necesidad = fecha_necesidad;
    }

    public String getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(String fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getCantidad_recibida() {
        return cantidad_recibida;
    }

    public void setCantidad_recibida(Double cantidad_recibida) {
        this.cantidad_recibida = cantidad_recibida;
    }

    public Double getCantidad_por_recibir() {
        return cantidad_por_recibir;
    }

    public void setCantidad_por_recibir(Double cantidad_por_recibir) {
        this.cantidad_por_recibir = cantidad_por_recibir;
    }

    public String getUdm() {
        return udm;
    }

    public void setUdm(String udm) {
        this.udm = udm;
    }

    public String getNombre_proveedor() {
        return nombre_proveedor;
    }

    public void setNombre_proveedor(String nombre_proveedor) {
        this.nombre_proveedor = nombre_proveedor;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Integer getId_partida_oc() {
        return id_partida_oc;
    }

    public void setId_partida_oc(Integer id_partida_oc) {
        this.id_partida_oc = id_partida_oc;
    }

    public Integer getId_localizacion() {
        return id_localizacion;
    }

    public void setId_localizacion(Integer id_localizacion) {
        this.id_localizacion = id_localizacion;
    }

}
