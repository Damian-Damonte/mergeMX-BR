/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.requisicion.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import com.metalsa.aprobacion.model.NvcTblOrganizacionesH;
import com.metalsa.core.model.NvcTblFamilias;
import com.metalsa.aprobacion.model.Usuario;
import com.metalsa.catalogo.model.IcomAccCategories;
import com.metalsa.catalogo.model.NvcVmOaIvaH;
import com.metalsa.core.model.NvcTblProyectosH;
import com.metalsa.core.model.NvcTblOaTareasH;
import com.metalsa.core.model.NvcTblOaCcH;
import com.metalsa.catalogo.model.NvcTblOaLocalizacionesH;

/**
 *
 * @author juliocisneros
 */
@Entity
@Table(name = "DETALLE_DE_REQUISICION", catalog = "")
@XmlRootElement
@NamedQueries({
    
})
public class DetalleDeRequisicion implements Serializable {
    @EmbeddedId
    protected DetalleDeRequisicionPK pk;
    
    @Size(max = 80)
    @Column(name = "DATOS_DE_AUDITORIA", length = 80)
    private String datosDeAuditoria;
    
    @Column(name = "ID_UEN")
    private Integer idUen;
    
    @Column(name = "IVA")
    private String iva;
    
    @Column(name = "ID_UEN_SURTIDORA")
    private Integer idUenSurtidora;
    
    @Size(max = 255)
    @Column(name = "DESCRIPCION_PRODUCTO", length = 255)
    private String descripcionProducto;
    
    @Size(max = 18)
    @Column(name = "ID_UNIDAD_DE_MEDIDA", length = 18)
    private String idUnidadDeMedida;
    
    /*@JoinColumn(name = "ID_FAMILIA", referencedColumnName = "ID_FAMILIA")
    @ManyToOne(fetch = FetchType.LAZY)
    private NvcTblFamilias familia;*/
    
    @Column(name = "ID_FAMILIA")
    private Integer idFamilia;
    
    @Column(name = "FECHA_REQUERIDA")
    @Temporal(TemporalType.DATE)
    private Date fechaRequerida;
    
    @Column(name = "ID_ESTATUS")
    private Integer idEstatus;
    
    @Column(name = "SIGUIENTE_APROBADOR")
    public String siguienteAprobador;
    
    @JoinColumn(name = "COMPRADOR", referencedColumnName = "ID_USUARIO")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario comprador;
    
    @Size(max = 150)
    @Column(name = "VENDOR_PART_NUMBER", length = 150)
    private String vendorPartNumber;
    
    @Size(max = 1)
    @Column(name = "URGENTE", length = 1)
    private String urgente;
    
    @Size(max = 255)
    @Column(name = "RAZON_URGENCIA", length = 255)
    private String razonUrgencia;
    
    @Column(name = "CANTIDAD_REQUERIDA")
    private BigDecimal cantidadRequerida;
    
    @Column(name = "ID_CUENTA")
    private Long idCuenta;
    
    @Size(max = 10)
    @Column(name = "FUENTE", length = 10)
    private String fuente;
    
    @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ACC_CAT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private IcomAccCategories category;
    
    @Size(max = 150)
    @Column(name = "SEGMENTO_1", length = 150)
    private String segmento1;
    @Size(max = 150)
    @Column(name = "SEGMENTO_2", length = 150)
    private String segmento2;
    @Size(max = 150)
    @Column(name = "SEGMENTO_3", length = 150)
    private String segmento3;
    @Size(max = 150)
    @Column(name = "SEGMENTO_4", length = 150)
    private String segmento4;
    @Size(max = 150)
    @Column(name = "SEGMENTO_5", length = 150)
    private String segmento5;
    @Size(max = 150)
    @Column(name = "SEGMENTO_6", length = 150)
    private String segmento6;
    @Size(max = 150)
    @Column(name = "SEGMENTO_7", length = 150)
    private String segmento7;
    @Size(max = 150)
    @Column(name = "SEGMENTO_8", length = 150)
    private String segmento8;
    
    @Column(name = "ID_CONTRATO")
    private Long idContrato;
    
    @JoinColumn(name = "ID_PROYECTO", referencedColumnName = "ID_PROYECTO")
    @ManyToOne(fetch = FetchType.LAZY)
    private NvcTblProyectosH proyecto;
    
    @Size(max = 25)
    @Column(name = "COD_PROYECTO", length = 25)
    private String codProyecto;
    
    @Size(max = 25)
    @Column(name = "COD_TAREA", length = 25)
    private String codTarea;
    
    @Size(max = 50)
    @Column(name = "TIPO_GASTO", length = 50)
    private String tipoGasto;
    
    @Column(name = "ID_CC", length = 50)
    private Integer idCentroCosto;
    
    @JoinColumn(name = "ID_LOCALIZACION", referencedColumnName = "ID_LOCALIZACION")
    @ManyToOne(fetch = FetchType.LAZY)
    private NvcTblOaLocalizacionesH localizacion;
    
    @Column(name = "NOTES_TO_BUYER")
    private String notesToBuyer;
    
    @Column(name = "APROBACION_ESPECIAL", length = 15)
    private String aprobacionEspecial;
    
    @Column(name = "ID_MONEDA")
    private String idMoneda;
    
    @Column(name = "ID_PART_NUMBER")
    private Integer idPartNumber;
    
    @Column(name = "COD_PRODUCTO", length = 50)
    private String codProducto;
    
    @Column(name = "LEAD_TIME")
    private Short leadTime;
    
    @Column(name = "MONTO_EXTENDIDO")
    private Double montoExtendido;
    
    @Column(name = "PRECIO")
    private Double precio;
    
    @Column(name = "TIPO_RECIBO")
    private Integer tipoRecibo;
    
    @Column(name = "ID_PRODUCTO", length = 30)
    private String idProducto;
    
    @Column(name = "ID_TAREA")
    private Integer idTarea;
    
    @Column(name = "ID_TIPO_CARGO")
    private Integer idTipoCargo;

    @Column(name = "ESTATUS")
    private String estatus;

    public DetalleDeRequisicion(Long idPartida, Long idRequisicion) {
        this.pk = new DetalleDeRequisicionPK(
            idPartida,
            idRequisicion
        );
    }
    
    public DetalleDeRequisicion() {
        
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
    
    public void setPK(DetalleDeRequisicionPK pk) {
        this.pk = pk;
    }
    
    public DetalleDeRequisicionPK getPK() {
        return this.pk;
    }
    
    public void setDatosDeAuditoria(String datos) {
        this.datosDeAuditoria = datos;
    }
    
    public void setIdUen(Integer uen) {
        this.idUen = uen;
    }
    
    public Integer getIdUen() {
        return this.idUen;
    }
    
    public void setIdUenSurtidora(Integer uen) {
        this.idUenSurtidora = uen;
    }
    
    public String getIva() {
        return this.iva;
    }
    
    public void setIva(String iva) {
        this.iva = iva;
    }
    
    public void setDescripcionProducto(String descripcion) {
        this.descripcionProducto = descripcion;
    }
    
    public void setIdUnidadDeMedida(String uom) {
        this.idUnidadDeMedida = uom;
    }
    
    /*public void setFamilia(NvcTblFamilias familia) {
        this.familia = familia;
    }*/
   
    public void setIdFamilia(Integer id) {
        this.idFamilia = id;
    }
    
    public void setFechaRequerida(Date fecha) {
        this.fechaRequerida = fecha;
    }
    
    public Date getFechaRequerida() {
        return this.fechaRequerida;
    }
    
    public String getSiguienteAprobador() {
        return this.siguienteAprobador;
    }
    
    public void setSiguienteAprobador(String aprobador) {
        this.siguienteAprobador = aprobador;
    }
    
    public Usuario getComprador() {
        return this.comprador;
    }
    
    public void setComprador(Usuario comprador) {
        this.comprador = comprador;
    }
    
    public void setVendorPartNumber(String partNumber) {
        this.vendorPartNumber = partNumber;
    }
    
    public void setUrgente(String urgente) {
        this.urgente = urgente;
    }
    
    public void setRazonUrgencia(String razon) {
        this.razonUrgencia = razon;
    }
    
    public void setCantidadRequerida(BigDecimal cantidad) {
        this.cantidadRequerida = cantidad;
    }
    
    public void setFuente(String fuente) {
        this.fuente = fuente;
    }
    
    public String getFuente() {
        return this.fuente;
    }
    
    public Long getIdCuenta() {
        return this.idCuenta;
    }
    
    public void setIdCuenta(Long idCuenta) {
        this.idCuenta = idCuenta;
    }
    
    public IcomAccCategories getCategory() {
        return this.category;
    }
    
    public void setCategory(IcomAccCategories category) {
        this.category = category;
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
    
    public void setSegmento1(String segmento) {
        this.segmento1 = segmento;
    }
    
    public void setSegmento2(String segmento) {
        this.segmento2 = segmento;
    }
    
    public void setSegmento3(String segmento) {
        this.segmento3 = segmento;
    }
    
    public void setSegmento4(String segmento) {
        this.segmento4 = segmento;
    }
    
    public void setSegmento5(String segmento) {
        this.segmento5 = segmento;
    }
    
    public void setSegmento6(String segmento) {
        this.segmento6 = segmento;
    }
    
    public void setSegmento7(String segmento) {
        this.segmento7 = segmento;
    }
    
    public void setSegmento8(String segmento) {
        this.segmento8 = segmento;
    }
    
    public Long getIdContrato() {
        return this.idContrato;
    }
    
    public void setIdContrato(Long id) {
        this.idContrato = id;
    }
    
    public NvcTblProyectosH getProyecto() {
        return this.proyecto;
    }
    
    public void setProyecto(NvcTblProyectosH proyecto) {
        this.proyecto = proyecto;
    }
    
    public void setCodProyecto(String cod) {
        this.codProyecto = cod;
    }
    
    public String getCodProyecto() {
        return this.codProyecto;
    }
    
    public String getCodTarea() {
        return this.codTarea;
    }
    
    public void setCodTarea(String cod) {
        this.codTarea = cod;
    }
    
    public String getTipoGasto() {
        return this.tipoGasto;
    }
    
    public void setTipoGasto(String tipo) {
        this.tipoGasto = tipo;
    }
    
    public NvcTblOaLocalizacionesH getLocalizacion() {
        return this.localizacion;
    }

    public void setLocalizacion(NvcTblOaLocalizacionesH localizacion) {
        this.localizacion = localizacion;
    }
    
    public Integer getIdCentroCosto() {
        return this.idCentroCosto;
    }
    
    public void setIdCentroCosto(Integer id) {
        this.idCentroCosto = id;
    }
    
    public Integer getIdEstatus() {
        return this.idEstatus;
    }
    
    public void setIdEstatus(Integer id) {
        this.idEstatus = id;
    }
    
    public void setNotesToBuyer(String notes) {
        this.notesToBuyer = notes;
    }
    
    public String notesToBuyer() {
        return this.notesToBuyer;
    }
    
    public String getAprobacionEspecial() {
        return this.aprobacionEspecial;
    }
    
    public void setAprobacionEspecial(String aprobacion) {
        this.aprobacionEspecial = aprobacion;
    }
    
    public String getIdMoneda() {
        return this.idMoneda;
    }
    
    public void setIdMoneda(String moneda) {
        this.idMoneda = moneda;
    }
    
    public Integer getIdPartNumber() {
        return this.idPartNumber;
    }
    
    public void setIdPartNumber(Integer number) {
        this.idPartNumber = number;
    }
    
    public String getCodProducto() {
        return this.codProducto;
    }
    
    public void setCodProducto(String cod) {
        this.codProducto = cod;
    }

    public Short getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(Short leadTime) {
        this.leadTime = leadTime;
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
    
    public Integer getTipoRecibo() {
        return tipoRecibo;
    }

    public void setTipoRecibo(Integer tipoRecibo) {
        this.tipoRecibo = tipoRecibo;
    }
    
    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }
    
    public Integer getIdTarea() {
        return this.idTarea;
    }
    
    public void setIdTarea(Integer id) {
        this.idTarea = id;
    }
    
    public Integer getIdTipoCargo() {
        return this.idTipoCargo;
    }
    
    public void setIdTipoCargo(Integer id) {
        this.idTipoCargo = id;
    }
}
