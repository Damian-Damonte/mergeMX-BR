/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.requisicion.pojo;

import java.util.Date;
import java.util.List;

/**
 *
 * @author jose.espindola03
 */
public class RequisicionCreateRequestDetalle {
    String fuente;
    String descripcion;
    String uom;
    Integer idProducto;
    Integer idPartida;
    Integer idUenSurtidora;
    Integer idUen;
    Integer idSubfamilia;
    Integer idProyecto;
    Date fechaNecesidad;
    String siguienteAprobador;
    Integer idCentroCosto;
    String comprador;
    String vendorPartNumber;
    String urgente;
    String razonUrgencia;
    Double cantidad;
    Integer idCuenta;
    String segmento1;
    String segmento2;
    String segmento3;
    String segmento4;
    String segmento5;
    String segmento6;
    String segmento7;
    String segmento8;
    String estatus;
    Integer idEstatus;
    private List<RequisicionCreateRequestMultiCC> detalleMultiCc;
    Integer idLocalizacion;
    Integer idIva;
    String iva;
    Integer idContrato;
    Integer idTarea;
    String tipoGasto;
    String notasComprador;
    String aprobacionEspecial;
    String idMoneda;
    Integer idCarroCompra;
    String codProducto;
    Integer tiempoEntrega;
    Double montoExtendido;
    Double precio;
    String typeDocumentoMaximo;
    String documentoValueMaximo;
    
    public String getFuente() {
        return this.fuente;
    }
    
    public boolean isFuente(String fuente) {
        return fuente != null ? fuente.equals(fuente) : false;
    }
    
    public boolean isAnyFuente(String... fuentes) {
        for (String f : fuentes) {
            if (isFuente(f)) {
                return true;
            }
        }
        return false;
    }
    
    public Integer getIdProducto() {
        return this.idProducto;
    }
    
    public Integer getIdPartida() {
        return this.idPartida;
    }
    
    public Integer getIdUenSurtidora() {
        return this.idUenSurtidora;
    }
    
    public void setIdUenSurtidora(Integer idUen) {
        this.idUenSurtidora = idUen;
    }
    
    public Integer getIdUen() {
        return this.idUen;
    }
    
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public String getUom() {
        return this.uom;
    }
    
    public Integer getIdSubfamilia() {
        return this.idSubfamilia;
    }
    
    public Date getFechaNecesidad() {
        return this.fechaNecesidad;
    }
    
    public String getSiguienteAprobador() {
        return this.siguienteAprobador;
    }
    
    public Integer getIdCentroCosto() {
        return this.idCentroCosto;
    }
    
    public String getComprador() {
        return this.comprador;
    }
    
    public String getVendorPartNumber() {
        return this.vendorPartNumber;
    }
    
    public String getUrgente() {
        return this.urgente;
    }
    
    public String getRazonUrgencia() {
        return this.razonUrgencia;
    }
    
    public Double getCantidad() {
        return this.cantidad;
    }
    
    public List<RequisicionCreateRequestMultiCC> getDetalleMultiCc() {
        return this.detalleMultiCc;
    }
    
    public Integer getIdCuenta() {
        return this.idCuenta;
    }
    
    public String getSegmento1() {
        return this.segmento1;
    }
    
    public String getSegmento2() {
        return this.segmento2;
    }
    
    public String getSegmento3() {
        return this.segmento3;
    }
    
    public String getSegmento4() {
        return this.segmento4;
    }
    
    public String getSegmento5() {
        return this.segmento5;
    }
    
    public String getSegmento6() {
        return this.segmento6;
    }
    
    public String getSegmento7() {
        return this.segmento7;
    }
    
    public String getSegmento8() {
        return this.segmento8;
    }
    
    public Integer getIdLocalizacion() {
        return this.idLocalizacion;
    }
    
    public void setIdLocalizacion(Integer id) {
        this.idLocalizacion = id;
    }
    
    public String getIva() {
        return this.iva;
    }
    
    public void setIva(String iva) {
        this.iva = iva;
    }
    
    public Integer getIdContrato() {
        return this.idContrato;
    }
    
    public void setIdContrato(Integer id) {
        this.idContrato = id;
    }
    
    public Integer getIdProyecto() {
        return this.idProyecto;
    }
    
    public void setIdProyecto(Integer id) {
        this.idProyecto = id;
    }
    
    public Integer getIdTarea() {
        return this.idTarea;
    }
    
    public void setIdtarea(Integer id) {
        this.idTarea = id;
    }
    
    public String getTipoGasto() {
        return this.tipoGasto;
    }
    
    public void setTipoGasto(String tipo) {
        this.tipoGasto = tipo;
    }
    
    public String getEstatus() {
        return this.estatus;
    }
    
    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
    
    public Integer getIdEstatus() {
        return this.idEstatus;
    }
    
    public void setIdEstatus(Integer estatus) {
        this.idEstatus = estatus;
    }
    
    public String getNotasComprador() {
        return this.notasComprador;
    }
    
    public String trimmedNotasComprador() {
        if (notasComprador == null) {
            return "";
        }
        String trimmed = notasComprador.trim();
        int length = trimmed.length();
        
        // TODO: revisar por qué el trim es de 960 cuando el texto
        // es mayor a 950, no parecer ser un movimienot lógico, a
        // menos claro que haya sido un error, yo solo copié la 
        // lógica para evitar introducor nuevos bugs.
        return trimmed.substring(0, length > 950 ? 960 : length);
    }
    
    public String getAprobacionEspecial() {
        return this.aprobacionEspecial;
    }
    
    public void setAprobacionEpecial(String aprobacion) {
        this.aprobacionEspecial = aprobacion;
    }
    
    public String getIdMoneda() {
        return this.idMoneda;
    }
    
    public void setIdMoneda(String moneda) {
        this.idMoneda = moneda;
    }
    
    public Integer getIdCarroCompra() {
        return this.idCarroCompra;
    }
    
    public void setIdCarroCompra(Integer id) {
        this.idCarroCompra = id;
    }
    
    public String getCodProducto() {
        return this.codProducto;
    }
    
    public void setCodProducto(String cod) {
        this.codProducto = cod;
    }
    
    public Integer getTiempoEntrega() {
        return tiempoEntrega;
    }

    public void setTiempoEntrega(Integer tiempoEntrega) {
        this.tiempoEntrega = tiempoEntrega;
    }
    
    public Double getMontoExtendido() {
        return montoExtendido;
    }

    public void setMontoExtendido(Double montoExtendido) {
        this.montoExtendido = montoExtendido;
    }
    
    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    
    public boolean hasProduct() {
        return this.idProducto != null && this.idProducto > 0;
    }
    
    public boolean hasMultiCc() {
        return getDetalleMultiCc() != null && !getDetalleMultiCc().isEmpty();
    }
    
    /**
     * Check if the and the surtidora Uen's are the same.
     * @return 
     */
    public boolean areBothUensTheSame() {
        return this.idUenSurtidora != null
                && this.idUen != null
                && this.idUenSurtidora.equals(this.idUen);
    }
    
    public boolean hasTypeDocumentoMaximo() {
        return typeDocumentoMaximo != null
                && !typeDocumentoMaximo.isEmpty();
    }
    
    // public boolean hasTypeDocumentoMaximoValue() {
     //   return typeDocumentoMaximoValue != null
      //          && !typeDocumentoMaximoValue.isEmpty();
    //}
 }
