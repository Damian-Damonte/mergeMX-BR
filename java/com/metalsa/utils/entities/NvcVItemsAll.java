/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.utils.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

import java.util.List;

import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author APODR7218
 */
@Entity
@Table(name = "NVC_V_ITEMS_ALL")
//@NamedQueries({
//    @NamedQuery(name = "NvcVItemsAll.findOne", query = "SELECT item FROM com.metalsa.utils.entities.NvcVItemsAll item WHERE item.idItem=:idItem and item.codigoItem=:codigoItem")
//})
public class NvcVItemsAll implements Serializable {

    private static long serialVersionUID = 1L;
    @Transient
    DecimalFormat formatter = new DecimalFormat("#0.0000");  

    
    @Id
    @Column(name = "CODIGOITEM")
    private String codigoItem;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "IDCATALOGOUEN")
    private Integer idCatalogoUen;
    @Column(name = "NOMBRECATALOGO")
    private String nombreCatalogo;
    @Column(name = "NUMEROPARTEFABRICANTE")
    private String numeroParteFabricante;
    @Column(name = "NUMEROPARTEPROVEEDOR")
    private String numeroParteProveedor;
    @Column(name = "FABRICANTE")
    private String fabricante;
    @Column(name = "MATERIAL")
    private String material;
    @Column(name = "COLOR")
    private String color;
    @Column(name = "MEDIDAS")
    private String medidas;
    @Column(name = "UDM")
    private String udm;
    @Column(name = "PRECIO")
    private Double precio;
    @Column(name = "PRECIOUNITARIO")
    private Double precioUnitario;
    @Column(name = "PRECIOMERCADO")
    private Double precioMercado;
    @Column(name = "MUESTRAPRECIOMERCADO")
    private String muestraPrecioMercado;
    @Column(name = "TIEMPOENTREGA")
    private Integer tiempoEntrega;
    @Column(name = "IDMONEDA")
    private String idMoneda;
    @Column(name = "IDIVA")
    private Integer idIva;
    @Column(name = "NOMBREIVA")
    private String nombreIva;

    @Column(name = "IDCATEGORIA")
    private Integer categoria;
    @Column(name = "NOMBRECATEGORIA")
    private String nombreCategoria;
    @Column(name = "IDFAMILIA")
    private Integer familia;
    @Column(name = "NOMBREFAMILIA")
    private String nombreFamilia;
    @Column(name = "IDSUBFAMILIA")
    private Integer subfamilia;
    @Column(name = "NOMBRESUBFAMILIA")
    private String nombreSubfamilia;
    @Column(name = "IDRAZONURGENCIA")
    private Integer idRazonUrgencia;
    @Column(name = "IDLINEA")
    private Integer idLinea;
    @Column(name = "IDESTATUS")
    private Integer idEstatus;
    @Column(name = "ACTIVO")
    private Integer activo;
    @Column(name = "PUBLICADO")
    private Integer publicado;
    @Column(name = "TIPOITEM")
    private Integer tipoItem;

    @Column(name = "IDPROVEEDOR")
    private Integer idProveedor;
    @Column(name = "NOMBREPROVEEDOR")
    private String nombreProveedor;
    @Column(name = "CANTIDADONHAND")
    private BigDecimal cantidadOnHand;
    @Column(name = "CANTIDADRESERVADA")
    private BigDecimal cantidadReservada;
    @Column(name = "CANTIDADDISPONIBLE")
    private BigDecimal cantidadDisponible;

    @Column(name = "NOMBREUEN")
    private String nombreUen;

    @Column(name = "GARANTIA")
    private String garantia;
    @Column(name = "REPARABLE")
    private String reparable;
    @Column(name = "CAMPOBUSQUEDA")
    private String campoBusqueda;
    @Column(name = "URLFTP")
    private String urlftp;
    @Column(name = "FECHACREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "TIPORECIBO")
    private Integer tipoRecibo;
    @Column(name = "SUBINVENTARIOS")
    private String subinventariosStr;
    @Column(name = "FUENTE")
    private String fuente;
    @Column(name = "NOMBREALMACEN")
    private String nombreAlmacen;

    @Column(name = "IDALMACEN")   // <R19103>
    private Integer idAlmacen;    // <R19103>

    @Column(name = "LOCALIZACION")
    private String localizacion;

    @Transient
    private boolean favorito;
    @Transient
    private boolean garantiaBool;
    @Transient
    private boolean reparableBool;
    
    @Transient
    private Double cantidad;
    @Transient
    private Integer esInterUen;
    @Transient
    private Integer idLocalizacion;
        
    @Transient
    private String localizacionesTxt;
    @Transient    
    private String monedaDefault;
    @Transient
    private Double precioCambiado;
    @Transient
    private Integer minimo;
    @Transient
    private Integer maximo;
    @Transient
    private String nombreFuente;

    // <CAT_VAR>
    @Column(name = "ID_TIPO_PRECIO")
    private Integer idTipoPrecio;
    @Column(name = "MAX_PRECIO_UNITARIO")
    private Double maxPrecioUnitario;
    @Column(name = "DESCUENTO")
    private Integer descuento;
    @Column(name = "ID_TEMPLATE")
    private Integer idTemplate;
    @Column(name = "PUNCHOUT")
    private Integer punchout;
    @Column(name = "PUNCHOUT_PROV_LOGO")
    private String punchoutProvLogo;

    @Transient
    private String nombreAllVariables;
    
    @Transient
    private Integer cantidadPedidoEsp;
    @Transient 
    private boolean specialRequest;
    
    @Transient
    private String nombreCategoriaIdioma;
    @Transient
    private String nombreFamiliaIdioma;
    @Transient
    private String nombreSubfamiliaIdioma;
    // <CAT_VAR>

    // @ManyToOne
    //@JoinTable(name="NVC_V_ITEMS_ALL",joinColumns={@JoinColumn(name="idItem")})
    //private NvcTblItemFavorito favoritos;
//    @ManyToMany
//    @JoinTable(name="NVC_V_ITEMS_ALL", joinColumns={@JoinColumn(name="IDITEM")}, inverseJoinColumns={@JoinColumn(name="IDITEM", referencedColumnName = "ID_ITEM")})
//    private List<NvcTblItemFavorito> favoritos;
    @Override
    public int hashCode() {
        int hash = 0;
        
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NvcVItemsAll)) {
            return false;
        }
        NvcVItemsAll other = (NvcVItemsAll) object;
       
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.spx.entities.NvcVItemsAll[ id=" + "getNvcVItemsAllPK().getIdItem()" + " ]";
    }

    /**
     * @return the idItem
     */
//    public Integer getIdItem() {
//        return idItem;
//    }
//
//    /**
//     * @param idItem the idItem to set
//     */
//    public void setIdItem(Integer idItem) {
//        this.idItem = idItem;
//    }
    /**
     * @return the codigoItem
     */
    public String getCodigoItem() {
        return codigoItem;
    }

    /**
     * @param codigoItem the codigoItem to set
     */
    public void setCodigoItem(String codigoItem) {
        this.codigoItem = codigoItem;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the idCatalogoUen
     */
    public Integer getIdCatalogoUen() {
        return idCatalogoUen;
    }

    /**
     * @param idCatalogoUen the idCatalogoUen to set
     */
    public void setIdCatalogoUen(Integer idCatalogoUen) {
        this.idCatalogoUen = idCatalogoUen;
    }

    /**
     * @return the nombreCatalogo
     */
    public String getNombreCatalogo() {
        return nombreCatalogo;
    }

    /**
     * @param nombreCatalogo the nombreCatalogo to set
     */
    public void setNombreCatalogo(String nombreCatalogo) {
        this.nombreCatalogo = nombreCatalogo;
    }

    /**
     * @return the numeroParteFabricante
     */
    public String getNumeroParteFabricante() {
        return numeroParteFabricante;
    }

    /**
     * @param numeroParteFabricante the numeroParteFabricante to set
     */
    public void setNumeroParteFabricante(String numeroParteFabricante) {
        this.numeroParteFabricante = numeroParteFabricante;
    }

    /**
     * @return the numeroParteProveedor
     */
    public String getNumeroParteProveedor() {
        return numeroParteProveedor;
    }

    /**
     * @param numeroParteProveedor the numeroParteProveedor to set
     */
    public void setNumeroParteProveedor(String numeroParteProveedor) {
        this.numeroParteProveedor = numeroParteProveedor;
    }

    /**
     * @return the fabricante
     */
    public String getFabricante() {
        return fabricante;
    }

    /**
     * @param fabricante the fabricante to set
     */
    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    /**
     * @return the material
     */
    public String getMaterial() {
        return material;
    }

    /**
     * @param material the material to set
     */
    public void setMaterial(String material) {
        this.material = material;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the medidas
     */
    public String getMedidas() {
        return medidas;
    }

    /**
     * @param medidas the medidas to set
     */
    public void setMedidas(String medidas) {
        this.medidas = medidas;
    }

    /**
     * @return the udm
     */
    public String getUdm() {
        return udm;
    }

    /**
     * @param udm the udm to set
     */
    public void setUdm(String udm) {
        this.udm = udm;
    }

    /**
     * @return the precioUnitario
     */
    public Double getPrecioUnitario() {
        if (precioUnitario != null) {
            return Double.valueOf(formatter.format(precioUnitario));
        }
        return precioUnitario;
    }

    /**
     * @param precioUnitario the precioUnitario to set
     */
    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    /**
     * @return the precioMercado
     */
    public Double getPrecioMercado() {
        if (precioMercado != null) {
            return Double.valueOf(formatter.format(precioMercado));
        }
        return precioMercado;
    }

    /**
     * @param precioMercado the precioMercado to set
     */
    public void setPrecioMercado(Double precioMercado) {
        this.precioMercado = precioMercado;
    }

    /**
     * @return the muestraPrecioMercado
     */
    public String getMuestraPrecioMercado() {
        return muestraPrecioMercado;
    }

    /**
     * @param muestraPrecioMercado the muestraPrecioMercado to set
     */
    public void setMuestraPrecioMercado(String muestraPrecioMercado) {
        this.muestraPrecioMercado = muestraPrecioMercado;
    }

    /**
     * @return the tiempoEntrega
     */
    public Integer getTiempoEntrega() {
        return tiempoEntrega;
    }

    /**
     * @param tiempoEntrega the tiempoEntrega to set
     */
    public void setTiempoEntrega(Integer tiempoEntrega) {
        this.tiempoEntrega = tiempoEntrega;
    }

    /**
     * @return the idMoneda
     */
    public String getIdMoneda() {
        return idMoneda;
    }

    /**
     * @param idMoneda the idMoneda to set
     */
    public void setIdMoneda(String idMoneda) {
        this.idMoneda = idMoneda;
    }

    /**
     * @return the idIva
     */
    public Integer getIdIva() {
        return idIva;
    }

    /**
     * @param idIva the idIva to set
     */
    public void setIdIva(Integer idIva) {
        this.idIva = idIva;
    }

    /**
     * @return the nombreIva
     */
    public String getNombreIva() {
        return nombreIva;
    }

    /**
     * @param nombreIva the nombreIva to set
     */
    public void setNombreIva(String nombreIva) {
        this.nombreIva = nombreIva;
    }

    /**
     * @return the uen
     */
//    public Integer getUen() {
//        return uen;
//    }
//
//    /**
//     * @param uen the uen to set
//     */
//    public void setUen(Integer uen) {
//        this.uen = uen;
//    }
    /**
     * @return the categoria
     */
    public Integer getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the familia
     */
    public Integer getFamilia() {
        return familia;
    }

    /**
     * @param familia the familia to set
     */
    public void setFamilia(Integer familia) {
        this.familia = familia;
    }

    /**
     * @return the subfamilia
     */
    public Integer getSubfamilia() {
        return subfamilia;
    }

    /**
     * @param subfamilia the subfamilia to set
     */
    public void setSubfamilia(Integer subfamilia) {
        this.subfamilia = subfamilia;
    }

    /**
     * @return the idRazonUrgencia
     */
    public Integer getIdRazonUrgencia() {
        return idRazonUrgencia;
    }

    /**
     * @param idRazonUrgencia the idRazonUrgencia to set
     */
    public void setIdRazonUrgencia(Integer idRazonUrgencia) {
        this.idRazonUrgencia = idRazonUrgencia;
    }

    /**
     * @return the idLinea
     */
    public Integer getIdLinea() {
        return idLinea;
    }

    /**
     * @param idLinea the idLinea to set
     */
    public void setIdLinea(Integer idLinea) {
        this.idLinea = idLinea;
    }

    /**
     * @return the idEstatus
     */
    public Integer getIdEstatus() {
        return idEstatus;
    }

    /**
     * @param idEstatus the idEstatus to set
     */
    public void setIdEstatus(Integer idEstatus) {
        this.idEstatus = idEstatus;
    }

    /**
     * @return the activo
     */
    public Integer getActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    /**
     * @return the publicado
     */
    public Integer getPublicado() {
        return publicado;
    }

    /**
     * @param publicado the publicado to set
     */
    public void setPublicado(Integer publicado) {
        this.publicado = publicado;
    }

    /**
     * @return the tipoItem
     */
    public Integer getTipoItem() {
        return tipoItem;
    }

    /**
     * @param tipoItem the tipoItem to set
     */
    public void setTipoItem(Integer tipoItem) {
        this.tipoItem = tipoItem;
    }

    /**
     * @return the idProveedor
     */
    public Integer getIdProveedor() {
        return idProveedor;
    }

    /**
     * @param idProveedor the idProveedor to set
     */
    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    /**
     * @return the nombreProveedor
     */
    public String getNombreProveedor() {
        return nombreProveedor;
    }

    /**
     * @param nombreProveedor the nombreProveedor to set
     */
    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    /**
     * @return the cantidadOnHand
     */
    public BigDecimal getCantidadOnHand() {
        return cantidadOnHand;
    }

    /**
     * @param cantidadOnHand the cantidadOnHand to set
     */
    public void setCantidadOnHand(BigDecimal cantidadOnHand) {
        this.cantidadOnHand = cantidadOnHand;
    }

    /**
     * @return the cantidadReservada
     */
    public BigDecimal getCantidadReservada() {
        return cantidadReservada;
    }

    /**
     * @param cantidadReservada the cantidadReservada to set
     */
    public void setCantidadReservada(BigDecimal cantidadReservada) {
        this.cantidadReservada = cantidadReservada;
    }

    /**
     * @return the cantidadDisponible
     */
    public BigDecimal getCantidadDisponible() {
        return cantidadDisponible;
    }

    /**
     * @param cantidadDisponible the cantidadDisponible to set
     */
    public void setCantidadDisponible(BigDecimal cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    /**
     * @return the favorito
     */
    public boolean isFavorito() {
        return favorito;
    }

    /**
     * @param favorito the favorito to set
     */
    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    /**
     * @return the garantia
     */
    public String getGarantia() {
        return garantia;
    }

    /**
     * @param garantia the garantia to set
     */
    public void setGarantia(String garantia) {
        this.garantia = garantia;
    }

    /**
     * @return the reparable
     */
    public String getReparable() {
        return reparable;
    }

    /**
     * @param reparable the reparable to set
     */
    public void setReparable(String reparable) {
        this.reparable = reparable;
    }

    /**
     * @return the campoBusqueda
     */
    public String getCampoBusqueda() {
        return campoBusqueda;
    }

    /**
     * @param campoBusqueda the campoBusqueda to set
     */
    public void setCampoBusqueda(String campoBusqueda) {
        this.campoBusqueda = campoBusqueda;
    }

   

    
    

    /**
     * @return the garantiaBool
     */
    public boolean isGarantiaBool() {
        return garantia.equals("Y");
    }

    /**
     * @param garantiaBool the garantiaBool to set
     */
    public void setGarantiaBool(boolean garantiaBool) {
        this.garantiaBool = garantiaBool;
    }

    /**
     * @return the reparableBool
     */
    public boolean isReparableBool() {
        return reparable.equals("Y");
    }

    /**
     * @param reparableBool the reparableBool to set
     */
    public void setReparableBool(boolean reparableBool) {
        this.reparableBool = reparableBool;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getNombreFamilia() {
        return nombreFamilia;
    }

    public void setNombreFamilia(String nombreFamilia) {
        this.nombreFamilia = nombreFamilia;
    }

    public String getNombreSubfamilia() {
        return nombreSubfamilia;
    }

    public void setNombreSubfamilia(String nombreSubfamilia) {
        this.nombreSubfamilia = nombreSubfamilia;
    }

    /**
     * @return the urlftp
     */
    public String getUrlftp() {
        return urlftp;
    }

    /**
     * @param urlftp the urlftp to set
     */
    public void setUrlftp(String urlftp) {
        this.urlftp = urlftp;
    }


    /**
     * @return the cantidad
     */
    public Double getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Column(name = "UEN")
    private Integer uen;
    @Column(name = "IDITEM")
    private Long idItem;

    public Integer getUen() {
        return uen;
    }

    public void setUen(Integer uen) {
        this.uen = uen;
    }

    public Long getIdItem() {
        return idItem;
    }

    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }

    public String getNombreUen() {
        return nombreUen;
    }

    public void setNombreUen(String nombreUen) {
        this.nombreUen = nombreUen;
    }

    public Double getPrecio() {
        if (precio != null) {
            return Double.valueOf(formatter.format(precio));
        }
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getEsInterUen() {
        return esInterUen;
    }

    public void setEsInterUen(Integer esInterUen) {
        this.esInterUen = esInterUen;
    }

    public Integer getIdLocalizacion() {
        return idLocalizacion;
    }

    public void setIdLocalizacion(Integer idLocalizacion) {
        this.idLocalizacion = idLocalizacion;
    }

    public Integer getTipoRecibo() {
        return tipoRecibo;
    }

    public void setTipoRecibo(Integer tipoRecibo) {
        this.tipoRecibo = tipoRecibo;
    }

    public String getLocalizacionesTxt() {
        return localizacionesTxt;
    }

    public void setLocalizacionesTxt(String localizacionesTxt) {
        this.localizacionesTxt = localizacionesTxt;
    }

    public String getMonedaDefault() {
        return monedaDefault;
    }

    public void setMonedaDefault(String monedaDefault) {
        this.monedaDefault = monedaDefault;
    }

    public Double getPrecioCambiado() {
        if (precioCambiado != null) {
            return Double.valueOf(formatter.format(precioCambiado));
        }
        return precioCambiado;
    }

    public void setPrecioCambiado(Double precioCambiado) {
        this.precioCambiado = precioCambiado;
    }


    public String getSubinventariosStr() {
        return subinventariosStr;
    }

    public void setSubinventariosStr(String subinventariosStr) {
        this.subinventariosStr = subinventariosStr;
    }

    public String getLongDescription() {
        return descripcion;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public Integer getMinimo() {
        return minimo;
    }

    public void setMinimo(Integer minimo) {
        this.minimo = minimo;
    }

    public Integer getMaximo() {
        return maximo;
    }

    public void setMaximo(Integer maximo) {
        this.maximo = maximo;
    }

    /**
     * @return the nombreAlmacen
     */
    public String getNombreAlmacen() {
        return nombreAlmacen;
    }

    /**
     * @param nombreAlmacen the nombreAlmacen to set
     */
    public void setNombreAlmacen(String nombreAlmacen) {
        this.nombreAlmacen = nombreAlmacen;
    }

    /**
     * @return the nombreFuente
     */
    public String getNombreFuente() {
        return nombreFuente;
    }

    /**
     * @param nombreFuente the nombreFuente to set
     */
    public void setNombreFuente(String nombreFuente) {
        this.nombreFuente = nombreFuente;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    // <R19103>
    public Integer getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(Integer idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    // </R19103>
    // <CAT_VAR>
    public Integer getIdTipoPrecio() {
        return idTipoPrecio;
    }

    public void setIdTipoPrecio(Integer idTipoPrecio) {
        this.idTipoPrecio = idTipoPrecio;
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

    public Integer getIdTemplate() {
        return idTemplate;
    }

    public void setIdTemplate(Integer idTemplate) {
        this.idTemplate = idTemplate;
    }

    public String getNombreAllVariables() {
        return nombreAllVariables;
    }

    public void setNombreAllVariables(String nombreAllVariables) {
        this.nombreAllVariables = nombreAllVariables;
    }
    // <CAT_VAR>

    public Integer getPunchout() {
        return punchout;
    }

    public void setPunchout(Integer punchout) {
        this.punchout = punchout;
    }

    public String getPunchoutProvLogo() {
        return punchoutProvLogo;
    }

    public void setPunchoutProvLogo(String punchoutProvLogo) {
        this.punchoutProvLogo = punchoutProvLogo;
    }

    public Integer getCantidadPedidoEsp() {
        return cantidadPedidoEsp;
    }

    public void setCantidadPedidoEsp(Integer cantidadPedidoEsp) {
        this.cantidadPedidoEsp = cantidadPedidoEsp;
    }

    public boolean isSpecialRequest() {
        return specialRequest;
    }

    public void setSpecialRequest(boolean specialRequest) {
        this.specialRequest = specialRequest;
    }

    /**
     * @return the nombreCategoriaIdioma
     */
    public String getNombreCategoriaIdioma() {
        return nombreCategoriaIdioma;
    }

    /**
     * @param nombreCategoriaIdioma the nombreCategoriaIdioma to set
     */
    public void setNombreCategoriaIdioma(String nombreCategoriaIdioma) {
        this.nombreCategoriaIdioma = nombreCategoriaIdioma;
    }

    /**
     * @return the nombreFamiliaIdioma
     */
    public String getNombreFamiliaIdioma() {
        return nombreFamiliaIdioma;
    }

    /**
     * @param nombreFamiliaIdioma the nombreFamiliaIdioma to set
     */
    public void setNombreFamiliaIdioma(String nombreFamiliaIdioma) {
        this.nombreFamiliaIdioma = nombreFamiliaIdioma;
    }

    /**
     * @return the nombreSubfamiliaIdioma
     */
    public String getNombreSubfamiliaIdioma() {
        return nombreSubfamiliaIdioma;
    }

    /**
     * @param nombreSubfamiliaIdioma the nombreSubfamiliaIdioma to set
     */
    public void setNombreSubfamiliaIdioma(String nombreSubfamiliaIdioma) {
        this.nombreSubfamiliaIdioma = nombreSubfamiliaIdioma;
    }




    
}
