/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.requisicion.model;

import com.metalsa.catalogo.model.NvcTblCatalogoItem;
import com.metalsa.core.model.DcEstatus;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.persistence.annotations.AdditionalCriteria;

/**
 *
 * @author APODR7218
 */
@Entity
@Table(name = "NVC_TBL_V_CATALOGO_ITEM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NvcTblVCatalogoItem.findAll", query = "SELECT n FROM NvcTblVCatalogoItem n")
    ,
    @NamedQuery(name = "NvcTblVCatalogoItem.findByIdItemV", query = "SELECT n FROM NvcTblVCatalogoItem n WHERE n.idItemV = :idItemV")
    ,
    @NamedQuery(name = "NvcTblVCatalogoItem.findByIdCatalogoV", query = "SELECT n FROM NvcTblVCatalogoItem n WHERE n.idCatalogoV = :idCatalogoV")
    ,
    @NamedQuery(name = "NvcTblVCatalogoItem.findByIdItem", query = "SELECT n FROM NvcTblVCatalogoItem n WHERE n.idItem = :idItem")
    ,
    @NamedQuery(name = "NvcTblVCatalogoItem.findByIdCatalogoUen", query = "SELECT n FROM NvcTblVCatalogoItem n WHERE n.idCatalogoUen = :idCatalogoUen")
    ,
    @NamedQuery(name = "NvcTblVCatalogoItem.findByCodigoItem", query = "SELECT n FROM NvcTblVCatalogoItem n WHERE n.codigoItem = :codigoItem")
    ,
    @NamedQuery(name = "NvcTblVCatalogoItem.findByDescripcion", query = "SELECT n FROM NvcTblVCatalogoItem n WHERE n.descripcion = :descripcion")
    ,
    @NamedQuery(name = "NvcTblVCatalogoItem.findByNumeroParteFabricante", query = "SELECT n FROM NvcTblVCatalogoItem n WHERE n.numeroParteFabricante = :numeroParteFabricante")
    ,
    @NamedQuery(name = "NvcTblVCatalogoItem.findByNumeroParteProveedor", query = "SELECT n FROM NvcTblVCatalogoItem n WHERE n.numeroParteProveedor = :numeroParteProveedor")
    ,
    @NamedQuery(name = "NvcTblVCatalogoItem.findByFabricante", query = "SELECT n FROM NvcTblVCatalogoItem n WHERE n.fabricante = :fabricante")
    ,
    @NamedQuery(name = "NvcTblVCatalogoItem.findByMaterial", query = "SELECT n FROM NvcTblVCatalogoItem n WHERE n.material = :material")
    ,
    @NamedQuery(name = "NvcTblVCatalogoItem.findByColor", query = "SELECT n FROM NvcTblVCatalogoItem n WHERE n.color = :color")
    ,
    @NamedQuery(name = "NvcTblVCatalogoItem.findByMedidas", query = "SELECT n FROM NvcTblVCatalogoItem n WHERE n.medidas = :medidas")
    ,
    @NamedQuery(name = "NvcTblVCatalogoItem.findByUdm", query = "SELECT n FROM NvcTblVCatalogoItem n WHERE n.udm = :udm")
    ,
    @NamedQuery(name = "NvcTblVCatalogoItem.findByPrecioUnitario", query = "SELECT n FROM NvcTblVCatalogoItem n WHERE n.precioUnitario = :precioUnitario")
    ,
    @NamedQuery(name = "NvcTblVCatalogoItem.findByPrecioMercado", query = "SELECT n FROM NvcTblVCatalogoItem n WHERE n.precioMercado = :precioMercado")
    ,
    @NamedQuery(name = "NvcTblVCatalogoItem.findByMuestraPrecioMercado", query = "SELECT n FROM NvcTblVCatalogoItem n WHERE n.muestraPrecioMercado = :muestraPrecioMercado")
    ,
    @NamedQuery(name = "NvcTblVCatalogoItem.findByTiempoEntrega", query = "SELECT n FROM NvcTblVCatalogoItem n WHERE n.tiempoEntrega = :tiempoEntrega")
    ,
    @NamedQuery(name = "NvcTblVCatalogoItem.findByIdMoneda", query = "SELECT n FROM NvcTblVCatalogoItem n WHERE n.idMoneda = :idMoneda")
    ,
    @NamedQuery(name = "NvcTblVCatalogoItem.findByIdIva", query = "SELECT n FROM NvcTblVCatalogoItem n WHERE n.idIva = :idIva")
    ,
    @NamedQuery(name = "NvcTblVCatalogoItem.findByIdSubfamilia", query = "SELECT n FROM NvcTblVCatalogoItem n WHERE n.idSubfamilia = :idSubfamilia")
    ,
    @NamedQuery(name = "NvcTblVCatalogoItem.findByIdEstatus", query = "SELECT n FROM NvcTblVCatalogoItem n WHERE n.idEstatus = :idEstatus")
    ,
    @NamedQuery(name = "NvcTblVCatalogoItem.findByFechaCreacion", query = "SELECT n FROM NvcTblVCatalogoItem n WHERE n.fechaCreacion = :fechaCreacion")
    ,
    @NamedQuery(name = "NvcTblVCatalogoItem.findByFechaActualizacion", query = "SELECT n FROM NvcTblVCatalogoItem n WHERE n.fechaActualizacion = :fechaActualizacion")
    ,
    @NamedQuery(name = "NvcTblVCatalogoItem.findByUsuarioCreacion", query = "SELECT n FROM NvcTblVCatalogoItem n WHERE n.usuarioCreacion = :usuarioCreacion")
    ,
    @NamedQuery(name = "NvcTblVCatalogoItem.findByUsuarioActualizacion", query = "SELECT n FROM NvcTblVCatalogoItem n WHERE n.usuarioActualizacion = :usuarioActualizacion")
    ,
    @NamedQuery(name = "NvcTblVCatalogoItem.findBySpotMarca", query = "SELECT n FROM NvcTblVCatalogoItem n WHERE n.spotMarca = :spotMarca")
    ,
    @NamedQuery(name = "NvcTblVCatalogoItem.findBySpotNombreGenerico", query = "SELECT n FROM NvcTblVCatalogoItem n WHERE n.spotNombreGenerico = :spotNombreGenerico")
    ,
    @NamedQuery(name = "NvcTblVCatalogoItem.findBySpotFechaNecesidad", query = "SELECT n FROM NvcTblVCatalogoItem n WHERE n.spotFechaNecesidad = :spotFechaNecesidad")
    ,
    @NamedQuery(name = "NvcTblVCatalogoItem.findBySpotUrgente", query = "SELECT n FROM NvcTblVCatalogoItem n WHERE n.spotUrgente = :spotUrgente")
    ,
    @NamedQuery(name = "NvcTblVCatalogoItem.findByIdLinea", query = "SELECT n FROM NvcTblVCatalogoItem n WHERE n.idLinea = :idLinea")
    ,
    @NamedQuery(name = "NvcTblVCatalogoItem.findByIdRazonUrgencia", query = "SELECT n FROM NvcTblVCatalogoItem n WHERE n.idRazonUrgencia = :idRazonUrgencia")
    ,
    @NamedQuery(name = "NvcTblVCatalogoItem.findBySpotComentariosComprador", query = "SELECT n FROM NvcTblVCatalogoItem n WHERE n.spotComentariosComprador = :spotComentariosComprador")
    ,
    @NamedQuery(name = "NvcTblVCatalogoItem.findByActivo", query = "SELECT n FROM NvcTblVCatalogoItem n WHERE n.activo = :activo")})
@AdditionalCriteria("this.activo = 1")
public class NvcTblVCatalogoItem implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ITEM_V")
    @SequenceGenerator(name = "CATALOGO_ITEM_V_SEQ_GEN", sequenceName = "CATALOGO_ITEM_V_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "CATALOGO_ITEM_V_SEQ_GEN")
    private Integer idItemV;

    @JoinColumn(name = "ID_CATALOGO_V", referencedColumnName = "ID_CATALOGO_V")
    @ManyToOne(optional = false)
    private NvcTblVCatalogo idCatalogoV;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ITEM")
    private Integer idItem;
    @Column(name = "ID_CATALOGO_UEN")
    private Integer idCatalogoUen;
    @Size(max = 50)
    @Column(name = "CODIGO_ITEM")
    private String codigoItem;
    @Size(max = 200)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Size(max = 50)
    @Column(name = "NUMERO_PARTE_FABRICANTE")
    private String numeroParteFabricante;
    @Size(max = 50)
    @Column(name = "NUMERO_PARTE_PROVEEDOR")
    private String numeroParteProveedor;
    @Size(max = 100)
    @Column(name = "FABRICANTE")
    private String fabricante;
    @Size(max = 100)
    @Column(name = "MATERIAL")
    private String material;
    @Size(max = 100)
    @Column(name = "COLOR")
    private String color;
    @Size(max = 100)
    @Column(name = "MEDIDAS")
    private String medidas;
    @JoinColumn(name = "UDM", referencedColumnName = "ID_UDM")
    @ManyToOne
    private NvcTblUdm udm;
    @Column(name = "PRECIO_UNITARIO")
    private Double precioUnitario;
    @Column(name = "PRECIO_MERCADO")
    private Double precioMercado;
    @Column(name = "MUESTRA_PRECIO_MERCADO")
    private String muestraPrecioMercado;
    @Column(name = "TIEMPO_ENTREGA")
    private Integer tiempoEntrega;

    @Column(name = "ID_IVA")
    private Integer idIva;
    @Column(name = "ID_SUBFAMILIA")
    private Integer idSubfamilia;
    @JoinColumn(name = "ID_ESTATUS", referencedColumnName = "SC_ID")
    @ManyToOne
    private DcEstatus idEstatus;
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FECHA_ACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @Size(max = 256)
    @Column(name = "USUARIO_CREACION")
    private String usuarioCreacion;
    @Size(max = 256)
    @Column(name = "USUARIO_ACTUALIZACION")
    private String usuarioActualizacion;
    @Size(max = 200)
    @Column(name = "SPOT_MARCA")
    private String spotMarca;
    @Size(max = 500)
    @Column(name = "SPOT_NOMBRE_GENERICO")
    private String spotNombreGenerico;
    @Column(name = "SPOT_FECHA_NECESIDAD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date spotFechaNecesidad;
    @Column(name = "SPOT_URGENTE")
    private Character spotUrgente;
    @Column(name = "ID_LINEA")
    private Integer idLinea;

	// <CAT_VAR>
    @Column(name = "MAX_PRECIO_UNITARIO")
    private Double maxPrecioUnitario;

    @Column(name = "DESCUENTO")
    private Integer descuento;

    @Column(name = "ID_TEMPLATE")
    private Long idTemplate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemsByRango", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<NvcTblItemVrango> rangoInfo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemsBymes", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<NvcTblItemVmes> mesInfo;
    // <CAT_VAR>
    
    @JoinColumn(name = "ID_RAZON_URGENCIA", referencedColumnName = "ID_RAZON_URGENCIA")
    @ManyToOne
    private NvcTblRazonUrgencia idRazonUrgencia;

    @JoinColumn(name = "ID_MONEDA", referencedColumnName = "ID_MONEDA")
    @ManyToOne
    private NvcTblMonedasH idMoneda;

    @Size(max = 500)
    @Column(name = "SPOT_COMENTARIOS_COMPRADOR")
    private String spotComentariosComprador;
    @Column(name = "ACTIVO")
    private Integer activo;
//    @Column(name = "TIPO_RECIBO")
//    private Integer tipoRecibo;
    @Size(max = 256)
    @Column(name = "ADMINISTRADOR")
    private String administrador;
    @JoinColumn(name = "ITEM_PUBLICADO", referencedColumnName = "SC_ID")
    @ManyToOne
    private DcEstatus itemPublicado;

    @JoinColumn(name = "ID_CATALOGO_UEN_V", referencedColumnName = "ID_CATALOGO_UEN_V", updatable = true, insertable = true)
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private NvcTblVCatalogoUen idCatalogoUenV;

    public NvcTblVCatalogoItem() {
    }

    public NvcTblVCatalogoItem(NvcTblCatalogoItem item) {

        if (item != null) {
            Calendar c1 = Calendar.getInstance();
            this.idItemV = null;
            this.idCatalogoV = null;
            this.idItem = item.getIdItem() != null ? item.getIdItem() : null;
            this.idCatalogoUen = item.getIdCatalogoUen() != null ? item.getIdCatalogoUen() : null;
            this.codigoItem = item.getCodigoItem() != null ? item.getCodigoItem() : null;
            this.descripcion = item.getDescripcion() != null ? item.getDescripcion() : null;
            this.numeroParteFabricante = item.getNumeroParteFabricante() != null ? item.getNumeroParteFabricante() : null;
            this.numeroParteProveedor = item.getNumeroParteProveedor() != null ? item.getNumeroParteProveedor() : null;
            this.fabricante = item.getFabricante() != null ? item.getFabricante() : null;
            this.material = item.getMaterial() != null ? item.getMaterial() : null;
            this.color = item.getColor() != null ? item.getColor() : null;
            this.medidas = item.getMedidas() != null ? item.getMaterial() : null;
            this.udm = item.getUnidadDeMedida() != null ? item.getUnidadDeMedida() : null;
            this.precioUnitario = item.getPrecioUnitario() != null ? item.getPrecioUnitario() : null;
            this.precioMercado = item.getPrecioMercado() != null ? item.getPrecioMercado() : null;
            this.muestraPrecioMercado = item.getMuestraPrecioMercado() != null ? item.getMuestraPrecioMercado() : null;
            this.tiempoEntrega = item.getTiempoEntrega() != null ? item.getTiempoEntrega() : null;
            this.idIva = item.getIdIva() != null ? item.getIdIva() : null;
            this.idSubfamilia = item.getIdSubfamilia() != null ? item.getIdSubfamilia() : null;
            this.idEstatus = item.getEstatus();
            this.fechaCreacion = c1.getTime();
            this.usuarioCreacion = item.getUsuarioActualizacion() != null ? item.getUsuarioActualizacion() : null;
            this.spotMarca = item.getSpotMarca() != null ? item.getSpotMarca() : null;
            this.spotNombreGenerico = item.getSpotNombreGenerico() != null ? item.getSpotNombreGenerico() : null;
            this.spotFechaNecesidad = item.getSpotFechaNecesidad() != null ? item.getSpotFechaNecesidad() : null;
            this.spotUrgente = item.getSpotUrgente() != null ? item.getSpotUrgente() : null;
            this.idLinea = item.getIdLinea() != null ? item.getIdLinea() : null;
            this.idRazonUrgencia = item.getRazonUrgencia();
            this.idMoneda = item.getMoneda();
            this.spotComentariosComprador = item.getSpotComentariosComprador() != null ? item.getSpotComentariosComprador() : null;
//            this.tipoRecibo = item.getTipoRecibo() != null ? item.getTipoRecibo() : null;
            this.administrador = item.getAdministrador() != null ? item.getAdministrador() : null;
            this.itemPublicado = item.getItemPublicadoEstatus();
            this.activo = item.getActivo() != null ? item.getActivo() : null;
            // <CAT_VAR>
            this.descuento = item.getDescuento() != null ? item.getDescuento() : null;
            this.maxPrecioUnitario = item.getMaxPrecioUnitario() != null ? item.getMaxPrecioUnitario() : null;
            // <CAT_VAR>
        }

    }

    public NvcTblVCatalogoItem(Integer idItemV) {
        this.idItemV = idItemV;
    }

    public NvcTblVCatalogoItem(Integer idItemV, NvcTblVCatalogo idCatalogoV, Integer idItem) {
        this.idItemV = idItemV;
        this.idCatalogoV = idCatalogoV;
        this.idItem = idItem;
    }

    public Integer getIdItemV() {
        return idItemV;
    }

    public void setIdItemV(Integer idItemV) {
        this.idItemV = idItemV;
    }

    public NvcTblVCatalogo getIdCatalogoV() {
        return idCatalogoV;
    }

    public void setIdCatalogoV(NvcTblVCatalogo idCatalogoV) {
        this.idCatalogoV = idCatalogoV;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public Integer getIdCatalogoUen() {
        return idCatalogoUen;
    }

    public void setIdCatalogoUen(Integer idCatalogoUen) {
        this.idCatalogoUen = idCatalogoUen;
    }

    public String getCodigoItem() {
        return codigoItem;
    }

    public void setCodigoItem(String codigoItem) {
        this.codigoItem = codigoItem;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNumeroParteFabricante() {
        return numeroParteFabricante;
    }

    public void setNumeroParteFabricante(String numeroParteFabricante) {
        this.numeroParteFabricante = numeroParteFabricante;
    }

    public String getNumeroParteProveedor() {
        return numeroParteProveedor;
    }

    public void setNumeroParteProveedor(String numeroParteProveedor) {
        this.numeroParteProveedor = numeroParteProveedor;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMedidas() {
        return medidas;
    }

    public void setMedidas(String medidas) {
        this.medidas = medidas;
    }

    public NvcTblUdm getUdm() {
        return udm;
    }

    public void setUdm(NvcTblUdm udm) {
        this.udm = udm;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getPrecioMercado() {
        return precioMercado;
    }

    public void setPrecioMercado(Double precioMercado) {
        this.precioMercado = precioMercado;
    }

    public String getMuestraPrecioMercado() {
        return muestraPrecioMercado;
    }

    public void setMuestraPrecioMercado(String muestraPrecioMercado) {
        this.muestraPrecioMercado = muestraPrecioMercado;
    }

    public Integer getTiempoEntrega() {
        return tiempoEntrega;
    }

    public void setTiempoEntrega(Integer tiempoEntrega) {
        this.tiempoEntrega = tiempoEntrega;
    }

    public NvcTblMonedasH getIdMoneda() {
        return idMoneda;
    }

    public void setIdMoneda(NvcTblMonedasH idMoneda) {
        this.idMoneda = idMoneda;
    }

    public Integer getIdIva() {
        return idIva;
    }

    public void setIdIva(Integer idIva) {
        this.idIva = idIva;
    }

    public Integer getIdSubfamilia() {
        return idSubfamilia;
    }

    public void setIdSubfamilia(Integer idSubfamilia) {
        this.idSubfamilia = idSubfamilia;
    }

    public DcEstatus getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(DcEstatus idEstatus) {
        this.idEstatus = idEstatus;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public String getUsuarioActualizacion() {
        return usuarioActualizacion;
    }

    public void setUsuarioActualizacion(String usuarioActualizacion) {
        this.usuarioActualizacion = usuarioActualizacion;
    }

    public String getSpotMarca() {
        return spotMarca;
    }

    public void setSpotMarca(String spotMarca) {
        this.spotMarca = spotMarca;
    }

    public String getSpotNombreGenerico() {
        return spotNombreGenerico;
    }

    public void setSpotNombreGenerico(String spotNombreGenerico) {
        this.spotNombreGenerico = spotNombreGenerico;
    }

    public Date getSpotFechaNecesidad() {
        return spotFechaNecesidad;
    }

    public void setSpotFechaNecesidad(Date spotFechaNecesidad) {
        this.spotFechaNecesidad = spotFechaNecesidad;
    }

    public Character getSpotUrgente() {
        return spotUrgente;
    }

    public void setSpotUrgente(Character spotUrgente) {
        this.spotUrgente = spotUrgente;
    }

    public Integer getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(Integer idLinea) {
        this.idLinea = idLinea;
    }

    public NvcTblRazonUrgencia getIdRazonUrgencia() {
        return idRazonUrgencia;
    }

    public void setIdRazonUrgencia(NvcTblRazonUrgencia idRazonUrgencia) {
        this.idRazonUrgencia = idRazonUrgencia;
    }

    public String getSpotComentariosComprador() {
        return spotComentariosComprador;
    }

    public void setSpotComentariosComprador(String spotComentariosComprador) {
        this.spotComentariosComprador = spotComentariosComprador;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

//    public Integer getTipoRecibo() {
//        return tipoRecibo;
//    }
//
//    public void setTipoRecibo(Integer tipoRecibo) {
//        this.tipoRecibo = tipoRecibo;
//    }
    public String getAdministrador() {
        return administrador;
    }

    public void setAdministrador(String administrador) {
        this.administrador = administrador;
    }

    public NvcTblVCatalogoUen getIdCatalogoUenV() {
        return idCatalogoUenV;
    }

    public void setIdCatalogoUenV(NvcTblVCatalogoUen idCatalogoUenV) {
        this.idCatalogoUenV = idCatalogoUenV;
    }

    public DcEstatus getItemPublicado() {
        return itemPublicado;
    }

    public void setItemPublicado(DcEstatus itemPublicado) {
        this.itemPublicado = itemPublicado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idItemV != null ? idItemV.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NvcTblVCatalogoItem)) {
            return false;
        }
        NvcTblVCatalogoItem other = (NvcTblVCatalogoItem) object;
        if ((this.idItemV == null && other.idItemV != null) || (this.idItemV != null && !this.idItemV.equals(other.idItemV))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.spx.entities.NvcTblVCatalogoItem[ idItemV=" + idItemV + " ]";
    }
    
	// <CAT_VAR>
    public List<NvcTblItemVrango> getRangoInfo() {
        return rangoInfo;
    }

    public void setRangoInfo(List<NvcTblItemVrango> rangoInfo) {
        this.rangoInfo = rangoInfo;
    }

    public List<NvcTblItemVmes> getMesInfo() {
        return mesInfo;
    }

    public void setMesInfo(List<NvcTblItemVmes> mesInfo) {
        this.mesInfo = mesInfo;
    }
    public Double getMaxPrecioUnitario() {
        return maxPrecioUnitario;
    }

    public void setMaxPrecioUnitario(Double maxPrecioUnitario) {
        this.maxPrecioUnitario = maxPrecioUnitario;
    }

    public Integer getDescuento() {
        return descuento;
    }

    public void setDescuento(Integer descuento) {
        this.descuento = descuento;
    }

    public Long getIdTemplate() {
        return idTemplate;
    }

    public void setIdTemplate(Long idTemplate) {
        this.idTemplate = idTemplate;
    }
    // <CAT_VAR>
}
