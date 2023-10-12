/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.model;

/**
 *
 * @author APOJO9952
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author APOJO9952
 */
@Entity
@Table(name = "NVC_TBL_DOCS_COTIZACION")
@NamedQuery(name = "QuotationDoc.findByIdCotizacion",
        query = "select u from QuotationDoc u where u.idCotizacion = ?1")

public class QuotationDoc implements Serializable {

    @Column(name = "CONSECUTIVO")
    private Integer consecutivo;
    @Column(name = "ID_COTIZACION")
    private Integer idCotizacion;
    @Id
    @Column(name = "ID_DOCUMENTO")
    private Integer idDocumento;
    @Column(name = "ENVIADO")
    private String enviado;
    @Size(max = 150)
    @Column(name = "DESCRIPCION", length = 150)
    private String descripcion;
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "ID_USUARIO")
    private String idUsuario;
    @Size(max = 100)
    @Column(name = "MIME", length = 100)
    private String mime;
    @Column(name = "TAMANO")
    private Long tamano;
    @Size(max = 300)
    @Column(name = "RUTA", length = 300)
    private String ruta;
    @Size(max = 100)
    @Column(name = "NOMBRE_FORMATO", length = 100)
    private String nombreFormato;
    @Size(max = 300)
    @Column(name = "RUTA_RAIZ", length = 300)
    private String rutaRaiz;
    @Column(name = "USUARIO_MODIFICA")
    private String usuarioModifica;
    @Column(name = "FECHA_MODIFICA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModifica;

    public Integer getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Integer consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Integer getIdCotizacion() {
        return idCotizacion;
    }

    public void setIdCotizacion(Integer idCotizacion) {
        this.idCotizacion = idCotizacion;
    }

    public Integer getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Integer idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getEnviado() {
        return enviado;
    }

    public void setEnviado(String enviado) {
        this.enviado = enviado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public Date getFecha() {
        return fecha != null ? new Date(fecha.getTime()) : new Date();
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha != null ? new Date(fecha.getTime()) : new Date();
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public Long getTamano() {
        return tamano;
    }

    public void setTamano(Long tamano) {
        this.tamano = tamano;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getNombreFormato() {
        return nombreFormato;
    }

    public void setNombreFormato(String nombreFormato) {
        this.nombreFormato = nombreFormato;
    }

    public String getRutaRaiz() {
        return rutaRaiz;
    }

    public void setRutaRaiz(String rutaRaiz) {
        this.rutaRaiz = rutaRaiz;
    }

    public String getUsuarioModifica() {
        return usuarioModifica;
    }

    public void setUsuarioModifica(String usuarioModifica) {
        this.usuarioModifica = usuarioModifica;
    }

    public Date getFechaModifica() {
        return fechaModifica != null ? new Date(fechaModifica.getTime()) : new Date();
    }

    public void setFechaModifica(Date fechaModifica) {
        this.fechaModifica = fechaModifica != null ? new Date(fechaModifica.getTime()) : new Date();
    }

    
    
}
