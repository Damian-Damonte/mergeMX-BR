package com.metalsa.requisicion.model;

import com.metalsa.aprobacion.model.NvcTblOrganizacionesH;
import com.metalsa.catalogo.model.NvcTblAlmacenH;
import com.metalsa.core.model.NvcTblProvSitesH;
import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;

/**
 *
 * @author juliocisneros
 */
@Entity
@Table(name = "NVC_VM_OA_EXISTENCIAS", catalog = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NvcVmOaExistencias.findAll", query = "SELECT n FROM NvcVmOaExistencias n")
    ,
    @NamedQuery(name = "NvcVmOaExistencias.findByIdUen", query = "SELECT n FROM NvcVmOaExistencias n WHERE n.nvcVmOaExistenciasPK.idUen = ?1 order by n.codProducto")
    ,
    @NamedQuery(name = "NvcVmOaExistencias.findByIdAlmacen", query = "SELECT n FROM NvcVmOaExistencias n WHERE n.nvcVmOaExistenciasPK.idAlmacen = :idAlmacen")
    ,
    @NamedQuery(name = "NvcVmOaExistencias.findByIdProducto", query = "SELECT n FROM NvcVmOaExistencias n WHERE n.nvcVmOaExistenciasPK.idProducto = :idProducto")
    ,
    @NamedQuery(name = "NvcVmOaExistencias.findByIdProductoIdUenIdAlmacen",
            query = "SELECT n FROM NvcVmOaExistencias n "
            + "WHERE n.nvcVmOaExistenciasPK.idProducto = :idProducto "
            + " AND n.nvcVmOaExistenciasPK.idUen = :idUen "
            + " AND n.nvcVmOaExistenciasPK.idAlmacen = :idAlmacen ")
    ,
    @NamedQuery(name = "NvcVmOaExistencias.findByCodProducto", query = "SELECT n FROM NvcVmOaExistencias n WHERE n.codProducto = :codProducto")
    ,
    @NamedQuery(name = "NvcVmOaExistencias.findByDescripcionProducto", query = "SELECT n FROM NvcVmOaExistencias n WHERE n.descripcionProducto = :descripcionProducto")
    ,
    @NamedQuery(name = "NvcVmOaExistencias.findByCosto", query = "SELECT n FROM NvcVmOaExistencias n WHERE n.costo = :costo")
    ,
    @NamedQuery(name = "NvcVmOaExistencias.findByPrecioLista", query = "SELECT n FROM NvcVmOaExistencias n WHERE n.precioLista = :precioLista")
    ,
    @NamedQuery(name = "NvcVmOaExistencias.findByInventoryItemStatusCode", query = "SELECT n FROM NvcVmOaExistencias n WHERE n.inventoryItemStatusCode = :inventoryItemStatusCode")
    ,
    @NamedQuery(name = "NvcVmOaExistencias.findByIdUnidadDeMedida", query = "SELECT n FROM NvcVmOaExistencias n WHERE n.idUnidadDeMedida = :idUnidadDeMedida")
    ,
    @NamedQuery(name = "NvcVmOaExistencias.findByIdFamilia", query = "SELECT n FROM NvcVmOaExistencias n WHERE n.idFamilia = :idFamilia")
    ,
    @NamedQuery(name = "NvcVmOaExistencias.findByCantidadOnHand", query = "SELECT n FROM NvcVmOaExistencias n WHERE n.cantidadOnHand = :cantidadOnHand")
    ,
    @NamedQuery(name = "NvcVmOaExistencias.findByCantidadReservada", query = "SELECT n FROM NvcVmOaExistencias n WHERE n.cantidadReservada = :cantidadReservada")
    ,
    @NamedQuery(name = "NvcVmOaExistencias.findByCantidadDisponible", query = "SELECT n FROM NvcVmOaExistencias n WHERE n.cantidadDisponible = :cantidadDisponible")
    ,
    @NamedQuery(name = "NvcVmOaExistencias.findByImagen", query = "SELECT n FROM NvcVmOaExistencias n WHERE n.imagen = :imagen")
    ,
    @NamedQuery(name = "NvcVmOaExistencias.findByDocumento", query = "SELECT n FROM NvcVmOaExistencias n WHERE n.documento = :documento")
    ,
    @NamedQuery(name = "NvcVmOaExistencias.findByFuente", query = "SELECT n FROM NvcVmOaExistencias n WHERE n.fuente = :fuente")
    ,
    @NamedQuery(name = "NvcVmOaExistencias.findByPartNum", query = "SELECT n FROM NvcVmOaExistencias n WHERE n.partNum = :partNum")
    ,
    @NamedQuery(name = "NvcVmOaExistencias.findByReparable", query = "SELECT n FROM NvcVmOaExistencias n WHERE n.reparable = :reparable")
    ,
    @NamedQuery(name = "NvcVmOaExistencias.findByGarantia", query = "SELECT n FROM NvcVmOaExistencias n WHERE n.garantia = :garantia")
    ,
    @NamedQuery(name = "NvcVmOaExistencias.findByManufacturerName", query = "SELECT n FROM NvcVmOaExistencias n WHERE n.manufacturerName = :manufacturerName")
    ,
    @NamedQuery(name = "NvcVmOaExistencias.findByMfgPartNum", query = "SELECT n FROM NvcVmOaExistencias n WHERE n.mfgPartNum = :mfgPartNum")
    ,
    @NamedQuery(name = "NvcVmOaExistencias.findByProductSegment", query = "SELECT n FROM NvcVmOaExistencias n WHERE n.productSegment = :productSegment")})
public class NvcVmOaExistencias implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NvcVmOaExistenciasPK nvcVmOaExistenciasPK;
    @Size(max = 120)
    @Column(name = "COD_PRODUCTO", length = 120)
    private String codProducto;
    @Size(max = 1344)
    @Column(name = "DESCRIPCION_PRODUCTO", length = 1344)
    private String descripcionProducto;
    @Column(name = "COSTO")
    private BigDecimal costo;
    @Column(name = "PRECIO_LISTA")
    private BigInteger precioLista;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "INVENTORY_ITEM_STATUS_CODE", nullable = false, length = 30)
    private String inventoryItemStatusCode;
    @Size(max = 9)
    @Column(name = "ID_UNIDAD_DE_MEDIDA", length = 9)
    private String idUnidadDeMedida;
    @Column(name = "ID_FAMILIA")
    private BigInteger idFamilia;
    @JsonIgnore
    @Column(name = "CANTIDAD_ON_HAND")
    private BigInteger cantidadOnHand;
    @Column(name = "CANTIDAD_RESERVADA")
    private BigInteger cantidadReservada;
    @Column(name = "CANTIDAD_DISPONIBLE")
    private BigInteger cantidadDisponible;
    @Size(max = 1)
    @Column(name = "IMAGEN", length = 1)
    private String imagen;
    @Column(name = "DOCUMENTO")
    private Character documento;
    @Size(max = 3)
    @Column(name = "FUENTE", length = 3)
    private String fuente;
    @Size(max = 450)
    @Column(name = "PART_NUM", length = 450)
    private String partNum;
    @Size(max = 720)
    @Column(name = "REPARABLE", length = 720)
    private String reparable;
    @Size(max = 720)
    @Column(name = "GARANTIA", length = 720)
    private String garantia;
    @Size(max = 720)
    @Column(name = "MANUFACTURER_NAME", length = 720)
    private String manufacturerName;
    @Size(max = 450)
    @Column(name = "MFG_PART_NUM", length = 450)
    private String mfgPartNum;
    @Size(max = 720)
    @Column(name = "PRODUCT_SEGMENT", length = 720)
    private String productSegment;
    @JoinColumns({
        @JoinColumn(name = "VENDOR_ID", referencedColumnName = "ID_PROVEEDOR"),
        @JoinColumn(name = "VENDOR_SITE_ID", referencedColumnName = "ID_SUCURSAL_PROVEEDOR")})
    @ManyToOne(fetch = FetchType.LAZY)
    private NvcTblProvSitesH nvcTblProvSitesH;
    @JoinColumn(name = "ID_UEN", referencedColumnName = "ID_UEN", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private NvcTblOrganizacionesH nvcTblOrganizacionesH;
    @JoinColumn(name = "ID_MONEDA", referencedColumnName = "ID_MONEDA")
    @ManyToOne(fetch = FetchType.LAZY)
    private NvcTblMonedasH idMoneda;
    @JoinColumn(name = "ID_ALMACEN", referencedColumnName = "ID_ALMACEN", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private NvcTblAlmacenH nvcTblAlmacenH;

    public NvcVmOaExistencias() {
    }

    public NvcVmOaExistencias(NvcVmOaExistenciasPK nvcVmOaExistenciasPK) {
        this.nvcVmOaExistenciasPK = nvcVmOaExistenciasPK;
    }

    public NvcVmOaExistencias(NvcVmOaExistenciasPK nvcVmOaExistenciasPK, String inventoryItemStatusCode) {
        this.nvcVmOaExistenciasPK = nvcVmOaExistenciasPK;
        this.inventoryItemStatusCode = inventoryItemStatusCode;
    }

    public NvcVmOaExistencias(Integer idUen, Integer idAlmacen, Integer idProducto) {
        this.nvcVmOaExistenciasPK = new NvcVmOaExistenciasPK(idUen, idAlmacen, idProducto);
    }

    public NvcVmOaExistenciasPK getNvcVmOaExistenciasPK() {
        return nvcVmOaExistenciasPK;
    }

    public void setNvcVmOaExistenciasPK(NvcVmOaExistenciasPK nvcVmOaExistenciasPK) {
        this.nvcVmOaExistenciasPK = nvcVmOaExistenciasPK;
    }

    public String getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(String codProducto) {
        this.codProducto = codProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public BigInteger getPrecioLista() {
        return precioLista;
    }

    public void setPrecioLista(BigInteger precioLista) {
        this.precioLista = precioLista;
    }

    public String getInventoryItemStatusCode() {
        return inventoryItemStatusCode;
    }

    public void setInventoryItemStatusCode(String inventoryItemStatusCode) {
        this.inventoryItemStatusCode = inventoryItemStatusCode;
    }

    public String getIdUnidadDeMedida() {
        return idUnidadDeMedida;
    }

    public void setIdUnidadDeMedida(String idUnidadDeMedida) {
        this.idUnidadDeMedida = idUnidadDeMedida;
    }

    public BigInteger getIdFamilia() {
        return idFamilia;
    }

    public void setIdFamilia(BigInteger idFamilia) {
        this.idFamilia = idFamilia;
    }

    public BigInteger getCantidadOnHand() {
        return cantidadOnHand;
    }

    public void setCantidadOnHand(BigInteger cantidadOnHand) {
        this.cantidadOnHand = cantidadOnHand;
    }

    public BigInteger getCantidadReservada() {
        return cantidadReservada;
    }

    public void setCantidadReservada(BigInteger cantidadReservada) {
        this.cantidadReservada = cantidadReservada;
    }

    public BigInteger getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(BigInteger cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Character getDocumento() {
        return documento;
    }

    public void setDocumento(Character documento) {
        this.documento = documento;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public String getPartNum() {
        return partNum;
    }

    public void setPartNum(String partNum) {
        this.partNum = partNum;
    }

    public String getReparable() {
        return reparable;
    }

    public void setReparable(String reparable) {
        this.reparable = reparable;
    }

    public String getGarantia() {
        return garantia;
    }

    public void setGarantia(String garantia) {
        this.garantia = garantia;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getMfgPartNum() {
        return mfgPartNum;
    }

    public void setMfgPartNum(String mfgPartNum) {
        this.mfgPartNum = mfgPartNum;
    }

    public String getProductSegment() {
        return productSegment;
    }

    public void setProductSegment(String productSegment) {
        this.productSegment = productSegment;
    }

    public NvcTblProvSitesH getNvcTblProvSitesH() {
        return nvcTblProvSitesH;
    }

    public void setNvcTblProvSitesH(NvcTblProvSitesH nvcTblProvSitesH) {
        this.nvcTblProvSitesH = nvcTblProvSitesH;
    }

    public NvcTblOrganizacionesH getNvcTblOrganizacionesH() {
        return nvcTblOrganizacionesH;
    }

    public void setNvcTblOrganizacionesH(NvcTblOrganizacionesH nvcTblOrganizacionesH) {
        this.nvcTblOrganizacionesH = nvcTblOrganizacionesH;
    }

    public NvcTblMonedasH getIdMoneda() {
        return idMoneda;
    }

    public void setIdMoneda(NvcTblMonedasH idMoneda) {
        this.idMoneda = idMoneda;
    }
    
    public NvcTblAlmacenH getNvcTblAlmacenH() {
        return nvcTblAlmacenH;
    }

    public void setNvcTblAlmacenH(NvcTblAlmacenH nvcTblAlmacenH) {
        this.nvcTblAlmacenH = nvcTblAlmacenH;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nvcVmOaExistenciasPK != null ? nvcVmOaExistenciasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NvcVmOaExistencias)) {
            return false;
        }
        NvcVmOaExistencias other = (NvcVmOaExistencias) object;
        if ((this.nvcVmOaExistenciasPK == null && other.nvcVmOaExistenciasPK != null) || (this.nvcVmOaExistenciasPK != null && !this.nvcVmOaExistenciasPK.equals(other.nvcVmOaExistenciasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.NvcVmOaExistencias[ nvcVmOaExistenciasPK=" + nvcVmOaExistenciasPK + " ]";
    }

}
