/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import static javax.persistence.ParameterMode.IN;
import static javax.persistence.ParameterMode.REF_CURSOR;
import javax.persistence.StoredProcedureParameter;

/**
 *
 * @author yair.nunez
 */
@Entity
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
            name = "getAlmacenRecibos",
            resultClasses = AlmacenRecibos.class,
            procedureName = "NVC_RECIBOS_SPX_PKG.PRC_SPX_DETALLE_ALMACEN",
            parameters = {
                @StoredProcedureParameter(mode = REF_CURSOR, name = "V_CURSOR", type = void.class),
                @StoredProcedureParameter(mode = IN, name = "P_USUARIO", type = String.class)
            }
    )
})
@IdClass(AlmacenRecibosPK.class)
public class AlmacenRecibos implements Serializable {

    @Id
    @Column(name = "ID_REQUISICION")
    private Integer idRequisicion;
    
    @Column(name = "FECHA_REQUERIDA")
    private String fechaRequerida;
    
    @Column(name = "COMPRADOR")
    private String comprador;
    
    @Column(name = "REQUISITOR")
    private String requisitor;
    
    @Id
    @Column(name = "ID_PARTIDA")
    private Integer idPartida;
    
    @Column(name = "URGENTE")
    private String urgente;
    
    @Column(name = "ID_ORDEN_DE_COMPRA")
    private Integer idOrdenCompra;
    
    @Column(name = "DESCRIPCION_PRODUCTO")
    private String descripcionProducto;
    
    @Column(name = "FECHA_RECIBO")
    private String fechaRecibo;
    
    @Id
    @Column(name = "NUMERO_RECIBO")
    private Integer numeroRecibo;
    
    @Column(name = "CANTIDAD_REQUERIDA")
    private Double cantidadRequerida;
    
    @Column(name = "CANTIDAD_ENTREGADA")
    private Double cantidadEntregada;
    
    @Column(name = "CANTIDAD_PENDIENTE")
    private Double cantidadPendiente;
    
    @Column(name = "ID_UNIDAD_DE_MEDIDA")
    private String idUnidadMedida;
    
    @Column(name = "NOMBRE_PROVEEDOR")
    private String nombreProveedor;
    
    @Column(name = "RECEPTOR")
    private String receptor;
    
    @Column(name = "ESTATUS")
    private String estatus;
            
    @Column(name = "ID_PRODUCTO")
    private Integer idProducto;
    
    @Column(name = "ID_PARTIDA_OC")
    private Integer idPartidaOc;
    
    @Column(name = "FUENTE")
    private String fuente;
    
    @Column(name = "ID_PROVEEDOR")
    private Integer idProveedor;
    
    @Column(name = "PRECIO")
    private Double precio;
    
    @Column(name = "ID_MONEDA")
    private String idMoneda;
    
    @Column(name = "ID_LOCALIZACION")
    private Integer idLocalizacion;
    
    @Column(name = "ID_UEN")
    private Integer idUen;
    
    @Column(name = "ID_USUARIO")
    private String idUsuario;

    @Column(name = "SOLICITANTE")
    private String solicitante;
    
    @Column(name = "EMAIL_SOLICITANTE")
    private String emailSolicitante;
    
    @Column(name = "USUARIO_EMAIL")
    private String usuarioEmail;
    
    @Column(name = "CANT_TOLERANCIA")
    private Double cantidadTolerancia; 
    
    @Column(name = "FECHA")
    private String fecha;
    
    @Column(name = "ID_CC")
    private Integer idCc;
    
    @Column(name = "NOMBRE_CC")
    private String nombreCc;
    
    @Column(name = "CODIGO_CC")
    private String codigoCc;

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

    public String getCodigoCc() {
        return codigoCc;
    }

    public void setCodigoCc(String codigoCc) {
        this.codigoCc = codigoCc;
    }
    
    
    public Integer getIdRequisicion() {
        return idRequisicion;
    }

    public void setIdRequisicion(Integer idRequisicion) {
        this.idRequisicion = idRequisicion;
    }

    public String getFechaRequerida() {
        return fechaRequerida;
    }

    public void setFechaRequerida(String fechaRequerida) {
        this.fechaRequerida = fechaRequerida;
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

    public Integer getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(Integer idPartida) {
        this.idPartida = idPartida;
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

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
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

    public Double getCantidadRequerida() {
        return cantidadRequerida;
    }

    public void setCantidadRequerida(Double cantidadRequerida) {
        this.cantidadRequerida = cantidadRequerida;
    }
    
    public Double getCantidadEntregada() {
        return cantidadEntregada;
    }

    public void setCantidadEntregada(Double cantidadEntregada) {
        this.cantidadEntregada = cantidadEntregada;
    }

    public Double getCantidadPendiente() {
        return cantidadPendiente;
    }

    public void setCantidadPendiente(Double cantidadPendiente) {
        this.cantidadPendiente = cantidadPendiente;
    }

    public String getIdUnidadMedida() {
        return idUnidadMedida;
    }

    public void setIdUnidadMedida(String idUnidadMedida) {
        this.idUnidadMedida = idUnidadMedida;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getReceptor() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getIdPartidaOc() {
        return idPartidaOc;
    }

    public void setIdPartidaOc(Integer idPartidaOc) {
        this.idPartidaOc = idPartidaOc;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getIdMoneda() {
        return idMoneda;
    }

    public void setIdMoneda(String idMoneda) {
        this.idMoneda = idMoneda;
    }

    public Integer getIdLocalizacion() {
        return idLocalizacion;
    }

    public void setIdLocalizacion(Integer idLocalizacion) {
        this.idLocalizacion = idLocalizacion;
    }

    public Integer getIdUen() {
        return idUen;
    }

    public void setIdUen(Integer idUen) {
        this.idUen = idUen;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public String getEmailSolicitante() {
        return emailSolicitante;
    }

    public void setEmailSolicitante(String emailSolicitante) {
        this.emailSolicitante = emailSolicitante;
    }

    public String getUsuarioEmail() {
        return usuarioEmail;
    }

    public void setUsuarioEmail(String usuarioEmail) {
        this.usuarioEmail = usuarioEmail;
    }

    public Double getCantidadTolerancia() {
        return cantidadTolerancia;
    }

    public void setCantidadTolerancia(Double cantidadTolerancia) {
        this.cantidadTolerancia = cantidadTolerancia;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "AlmacenRecibos{" + "idRequisicion=" + idRequisicion + ", fechaRequerida=" + fechaRequerida + ", comprador=" + comprador + ", requisitor=" + requisitor + ", idPartida=" + idPartida + ", urgente=" + urgente + ", idOrdenCompra=" + idOrdenCompra + ", descripcionProducto=" + descripcionProducto + ", fechaRecibo=" + fechaRecibo + ", numeroRecibo=" + numeroRecibo + ", cantidadRequerida=" + cantidadRequerida + ", cantidadEntregada=" + cantidadEntregada + ", cantidadPendiente=" + cantidadPendiente + ", idUnidadMedida=" + idUnidadMedida + ", nombreProveedor=" + nombreProveedor + ", receptor=" + receptor + ", estatus=" + estatus + ", idProducto=" + idProducto + ", idPartidaOc=" + idPartidaOc + ", fuente=" + fuente + ", idProveedor=" + idProveedor + ", precio=" + precio + ", idMoneda=" + idMoneda + ", idLocalizacion=" + idLocalizacion + ", idUen=" + idUen + ", idUsuario=" + idUsuario + ", solicitante=" + solicitante + ", emailSolicitante=" + emailSolicitante + ", usuarioEmail=" + usuarioEmail + ", cantidadTolerancia=" + cantidadTolerancia + ", fecha=" + fecha + '}';
    }

}
