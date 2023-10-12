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
import static javax.persistence.ParameterMode.OUT;
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
            name = "generarReporteRecibos",
            procedureName = "NVC_RECIBOS_SPX_PKG.REPORTE_RECIBOS",
            parameters = {
                @StoredProcedureParameter(mode = IN, name = "EL_WHERE_ARMADO", type = String.class),
                @StoredProcedureParameter(mode = IN, name = "EL_WHERE_ARMADO2", type = String.class),
                @StoredProcedureParameter(mode = IN, name = "P_ID_USUARIO", type = String.class),
                @StoredProcedureParameter(mode = IN, name = "P_NOMBRE_REPORTE", type = String.class)
            }
    ),
    @NamedStoredProcedureQuery(
            name = "obtenerReporte",
            resultClasses = ReporteRecibos.class,
            procedureName = "NVC_RECIBOS_SPX_PKG.getReporteDetalle",
            parameters = {
                @StoredProcedureParameter(mode = REF_CURSOR, name = "V_CURSOR", type = void.class),
                @StoredProcedureParameter(mode = IN, name = "p_id_reporte", type = Integer.class)
            }
    ),
    @NamedStoredProcedureQuery(
            name = "eliminarReporte",
            procedureName = "NVC_RECIBOS_SPX_PKG.deleteReporteMaestro",
            parameters = {
                @StoredProcedureParameter(mode = IN, name = "p_id_reporte", type = Integer.class)
            }
    ),
    @NamedStoredProcedureQuery(
            name = "obtenerTimestamp",
            procedureName = "UTILERIAS_PKG.prcRandomTimeStampa",
            parameters = {
                @StoredProcedureParameter(mode = OUT, name = "eltiempoestampa", type = String.class)
            }
    ),
    @NamedStoredProcedureQuery(
            name = "obtenerWhereQuery",
            procedureName = "UTILERIAS_PKG.prcGeneraWhereQuery",
            parameters = {
                @StoredProcedureParameter(mode = IN, name = "randomTimeStampa", type = String.class),
                @StoredProcedureParameter(mode = OUT, name = "consulta", type = String.class)
            }
    ),
    @NamedStoredProcedureQuery(
            name = "insertaClave",
            procedureName = "UTILERIAS_PKG.insertaClavesQuery",
            parameters = {
                @StoredProcedureParameter(mode = IN, name = "pllave", type = String.class),
                @StoredProcedureParameter(mode = IN, name = "pvalor", type = String.class),
                @StoredProcedureParameter(mode = IN, name = "randomTimeStampa", type = String.class),
                @StoredProcedureParameter(mode = IN, name = "ptipodato", type = String.class),
                @StoredProcedureParameter(mode = IN, name = "p_proposito", type = String.class),
                @StoredProcedureParameter(mode = IN, name = "p_orden", type = Integer.class)
            }
    ),
    @NamedStoredProcedureQuery(
            name = "enviarCorreoProveedor",
            procedureName = "PKG_EMAILS_RECIBOS.email_proveedor_occlose",
            parameters = {
                @StoredProcedureParameter(mode = IN, name = "p_id_session_tmp", type = String.class)
            }
    )
})
@IdClass(ReporteRecibosPK.class)
public class ReporteRecibos implements Serializable{
    
    @Id
    @Column(name = "ID_REQUISICION")
    private Integer idRequisicion;
    
    @Id
    @Column(name = "ID_PARTIDA")
    private Integer idPartida;
    
    @Column(name = "ID_PARTIDA_OC")
    private Integer idPartidaOc;
    
    @Column(name = "PRECIO_UNITARIO")
    private Double precioUnitario;
    
    @Column(name = "MONTO")
    private Double monto;
    
    @Column(name = "NOMBRE_PROVEEDOR")
    private String nombreProveedor;
    
    @Column(name = "FECHA_REQUISICION")
    private String fechaRequisicion;
    
    @Column(name = "T_VIEW")
    private String tView;
    
    @Column(name = "ID_UEN")
    private Integer idUen;
    
    @Column(name = "ID_ORDEN_DE_COMPRA")
    private Integer idOrdenCompra;
    
    @Column(name = "ID_PROVEEDOR")
    private Integer idProveedor;
    
    @Column(name = "CLOSED_CODE")
    private String closedCode;
    
    @Column(name = "AUTHORIZATION_STATUS")
    private String authorizationStatus;
    
    @Column(name = "CANCEL_FLAG")
    private String cancelFlag;
    
    @Column(name = "ID_LOCALIZACION")
    private Integer idLocalizacion;
    
    @Column(name = "SHIP_TO_LOCATION_ID")
    private Integer shipToLocationId;
    
    @Column(name = "LOCALIZACION_IGUAL")
    private Integer localizacionIgual;
    
    @Column(name = "REQ_ESTADO")
    private String reqEstado;
    
    @Column(name = "ID_CC")
    private Integer idCC;
    
    @Column(name = "ID_MONEDA")
    private String idMoneda;
    
    @Column(name = "ID_REQUISITOR")
    private String idRequisitor;
    
    @Column(name = "URGENTE")
    private String urgente;
    
    @Column(name = "COMPRADOR")
    private String comprador;
    
    @Column(name = "ID_COMPRADOR")
    private String idComprador;
    
    @Column(name = "REQUISITOR")
    private String requisitor;
    
    @Column(name = "CANTIDAD_REQUERIDA")
    private Double cantidadRequerida;
    
    @Column(name = "CANTIDAD_ENTREGADA")
    private Double cantidadEntregada;
    
    @Column(name = "CANTIDAD_PENDIENTE")
    private Double cantidadPendiente;
    
    @Column(name = "ID_UNIDAD_DE_MEDIDA")
    private String idUnidadDeMedida;
    
    @Column(name = "DESCRIPCION_PRODUCTO")
    private String descripcionProducto;
    
    @Column(name = "FECHA_REQUERIDA")
    private String fechaRequerida;
    
    @Column(name = "FUENTE")
    private String fuente;
    
    @Id
    @Column(name = "FOLIO")
    private Double folio;
    
    @Column(name = "FECHA_DE_RECEPCION")
    private String fechaRecepcion;
    
    @Column(name = "ID_RECEPTOR")
    private String idReceptor;
    
    @Column(name = "RECEPTOR")
    private String receptor;
    
    @Column(name = "CANTIDAD_RECIBIDA")
    private Double cantidadRecibida; 
    
    @Column(name = "STARS")
    private Integer stars;
    
    @Column(name = "DIFERENCIA_DIAS")
    private Integer diferenciaDias;
    
    @Column(name = "RECIBIDO_EN")
    private String recibidoEn;
    
    @Column(name = "CONSULTA")
    private String consulta;

    public ReporteRecibos() {
    }

    public Integer getIdRequisicion() {
        return idRequisicion;
    }

    public void setIdRequisicion(Integer idRequisicion) {
        this.idRequisicion = idRequisicion;
    }

    public Integer getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(Integer idPartida) {
        this.idPartida = idPartida;
    }

    public Integer getIdPartidaOc() {
        return idPartidaOc;
    }

    public void setIdPartidaOc(Integer idPartidaOc) {
        this.idPartidaOc = idPartidaOc;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }
    
    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getFechaRequisicion() {
        return fechaRequisicion;
    }

    public void setFechaRequisicion(String fechaRequisicion) {
        this.fechaRequisicion = fechaRequisicion;
    }

    public String gettView() {
        return tView;
    }

    public void settView(String tView) {
        this.tView = tView;
    }

    public Integer getIdUen() {
        return idUen;
    }

    public void setIdUen(Integer idUen) {
        this.idUen = idUen;
    }

    public Integer getIdOrdenCompra() {
        return idOrdenCompra;
    }

    public void setIdOrdenCompra(Integer idOrdenCompra) {
        this.idOrdenCompra = idOrdenCompra;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getClosedCode() {
        return closedCode;
    }

    public void setClosedCode(String closedCode) {
        this.closedCode = closedCode;
    }

    public String getAuthorizationStatus() {
        return authorizationStatus;
    }

    public void setAuthorizationStatus(String authorizationStatus) {
        this.authorizationStatus = authorizationStatus;
    }

    public String getCancelFlag() {
        return cancelFlag;
    }

    public void setCancelFlag(String cancelFlag) {
        this.cancelFlag = cancelFlag;
    }

    public Integer getIdLocalizacion() {
        return idLocalizacion;
    }

    public void setIdLocalizacion(Integer idLocalizacion) {
        this.idLocalizacion = idLocalizacion;
    }

    public Integer getShipToLocationId() {
        return shipToLocationId;
    }

    public void setShipToLocationId(Integer shipToLocationId) {
        this.shipToLocationId = shipToLocationId;
    }

    public Integer getLocalizacionIgual() {
        return localizacionIgual;
    }

    public void setLocalizacionIgual(Integer localizacionIgual) {
        this.localizacionIgual = localizacionIgual;
    }

    public String getReqEstado() {
        return reqEstado;
    }

    public void setReqEstado(String reqEstado) {
        this.reqEstado = reqEstado;
    }

    public Integer getIdCC() {
        return idCC;
    }

    public void setIdCC(Integer idCC) {
        this.idCC = idCC;
    }

    public String getIdMoneda() {
        return idMoneda;
    }

    public void setIdMoneda(String idMoneda) {
        this.idMoneda = idMoneda;
    }

    public String getIdRequisitor() {
        return idRequisitor;
    }

    public void setIdRequisitor(String idRequisitor) {
        this.idRequisitor = idRequisitor;
    }

    public String getUrgente() {
        return urgente;
    }

    public void setUrgente(String urgente) {
        this.urgente = urgente;
    }

    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    public String getIdComprador() {
        return idComprador;
    }

    public void setIdComprador(String idComprador) {
        this.idComprador = idComprador;
    }

    public String getRequisitor() {
        return requisitor;
    }

    public void setRequisitor(String requisitor) {
        this.requisitor = requisitor;
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

    public String getIdUnidadDeMedida() {
        return idUnidadDeMedida;
    }

    public void setIdUnidadDeMedida(String idUnidadDeMedida) {
        this.idUnidadDeMedida = idUnidadDeMedida;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public String getFechaRequerida() {
        return fechaRequerida;
    }

    public void setFechaRequerida(String fechaRequerida) {
        this.fechaRequerida = fechaRequerida;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public Double getFolio() {
        return folio;
    }

    public void setFolio(Double folio) {
        this.folio = folio;
    }

    public String getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(String fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public String getIdReceptor() {
        return idReceptor;
    }

    public void setIdReceptor(String idReceptor) {
        this.idReceptor = idReceptor;
    }

    public String getReceptor() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }

    public Double getCantidadRecibida() {
        return cantidadRecibida;
    }

    public void setCantidadRecibida(Double cantidadRecibida) {
        this.cantidadRecibida = cantidadRecibida;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public Integer getDiferenciaDias() {
        return diferenciaDias;
    }

    public void setDiferenciaDias(Integer diferenciaDias) {
        this.diferenciaDias = diferenciaDias;
    }

    public String getRecibidoEn() {
        return recibidoEn;
    }

    public void setRecibidoEn(String recibidoEn) {
        this.recibidoEn = recibidoEn;
    }

    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }
    
    

    @Override
    public String toString() {
        return "ReporteRecibos{" + "idRequisicion=" + idRequisicion + ", idPartida=" + idPartida + ", nombreProveedor=" + nombreProveedor + ", fechaRequisicion=" + fechaRequisicion + ", tView=" + tView + ", idUen=" + idUen + ", idOrdenCompra=" + idOrdenCompra + ", idProveedor=" + idProveedor + ", closedCode=" + closedCode + ", authorizationStatus=" + authorizationStatus + ", cancelFlag=" + cancelFlag + ", idLocalizacion=" + idLocalizacion + ", shipToLocationId=" + shipToLocationId + ", localizacionIgual=" + localizacionIgual + ", reqEstado=" + reqEstado + ", idCC=" + idCC + ", idMoneda=" + idMoneda + ", idRequisitor=" + idRequisitor + ", urgente=" + urgente + ", comprador=" + comprador + ", idComprador=" + idComprador + ", requisitor=" + requisitor + ", cantidadRequerida=" + cantidadRequerida + ", cantidadEntregada=" + cantidadEntregada + ", cantidadPendiente=" + cantidadPendiente + ", idUnidadDeMedida=" + idUnidadDeMedida + ", descripcionProducto=" + descripcionProducto + ", fechaRequerida=" + fechaRequerida + ", fuente=" + fuente + ", folio=" + folio + ", fechaRecepcion=" + fechaRecepcion + ", idReceptor=" + idReceptor + ", receptor=" + receptor + ", cantidadRecibida=" + cantidadRecibida + ", stars=" + stars + ", diferenciaDias=" + diferenciaDias + ", recibidoEn=" + recibidoEn + ", consulta=" + consulta + '}';
    }
    
}
