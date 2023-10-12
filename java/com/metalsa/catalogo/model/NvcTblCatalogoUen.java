package com.metalsa.catalogo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.type.StringType;

/**
 *
 * @author APOMS7376
 */
@Entity
@Table(name = "NVC_TBL_CATALOGO_UEN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NvcTblCatalogoUen.findAll", query = "SELECT n FROM NvcTblCatalogoUen n")
    ,
    @NamedQuery(name = "NvcTblCatalogoUen.findByIdCatalogoUen", query = "SELECT n FROM NvcTblCatalogoUen n WHERE n.idCatalogoUen = :idCatalogoUen")
})
@SqlResultSetMappings({
    @SqlResultSetMapping(
            name = "CatUenMapping",
            classes = {
                @ConstructorResult(
                        targetClass = NvcTblCatalogoUen.class,
                        columns = {
                            @ColumnResult(name = "ID_CATALOGO_UEN", type = Integer.class)
                            ,
                            @ColumnResult(name = "ID_UEN", type = Integer.class)
                            ,
                            @ColumnResult(name = "NOMBRE_UEN", type = StringType.class)
                            ,
                            @ColumnResult(name = "TIPO_RECIBO", type = Integer.class)
                            ,
                            @ColumnResult(name = "ID_FACTURACION", type = Integer.class)
                            ,
                            @ColumnResult(name = "OC_MANUAL", type = StringType.class)
                            ,
                            @ColumnResult(name = "ID_COMPRADOR", type = StringType.class)
                            ,
                            @ColumnResult(name = "NOMBRE_COMPRADOR", type = StringType.class)
                            ,
                            @ColumnResult(name = "ID_IVA", type = Integer.class)
                        }
                )}
    )
})
@NamedNativeQueries({
    @NamedNativeQuery(
            name = "NvcTblCatalogoUen.getByIdCatalogo",
            resultClass = NvcTblCatalogoUen.class,
            resultSetMapping = "CatUenMapping",
            query = "select cu.id_catalogo_uen, cu.id_uen, uen.organization_name nombre_uen, cu.tipo_recibo, "
            + "cu.id_facturacion, cu.oc_manual, cu.id_comprador, comp.nombre_usuario nombre_comprador, cu.id_iva "
            + "from nvc_tbl_catalogo_uen cu join oa_uens uen on cu.id_uen = uen.organization_id "
            + "join usuario comp on cu.id_comprador = comp.id_usuario "
            + "where cu.id_catalogo = :idCatalogo and cu.activo = 1 "
    )
    ,
    @NamedNativeQuery(
            name = "NvcTblCatalogoUen.getByIdCatalogoUen",
            resultClass = NvcTblCatalogoUen.class,
            resultSetMapping = "CatUenMapping",
            query = "select cu.id_catalogo_uen, cu.id_uen, uen.organization_name nombre_uen, cu.tipo_recibo, "
            + "cu.id_facturacion, cu.oc_manual, cu.id_comprador, comp.nombre_usuario nombre_comprador, cu.id_iva "
            + "from nvc_tbl_catalogo_uen cu join oa_uens uen on cu.id_uen = uen.organization_id "
            + "join usuario comp on cu.id_comprador = comp.id_usuario "
            + "where cu.id_catalogo_uen = :idCatalogoUen and cu.activo = 1"
    )
})
public class NvcTblCatalogoUen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CATALOGO_UEN")
    private Integer idCatalogoUen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "USUARIO_CREACION")
    private String usuarioCreacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "USUARIO_ACTUALIZACION")
    private String usuarioActualizacion;
    @Column(name = "ACTIVO")
    private Integer activo;
    @Column(name = "ID_UEN")
    private Integer idUen;
    @Column(name = "ID_CATALOGO")
    private Integer idCatalogo;
    @Column(name = "TIPO_RECIBO")
    private Integer tipoRecibo;
    @Column(name = "ID_FACTURACION")
    private Integer idFacturacion;
    @Column(name = "OC_MANUAL")
    private String ocManual;
    @Column(name = "ID_COMPRADOR")
    private String idComprador;
    @Column(name = "ID_TIPO_PRECIO")
    private Integer tipoPrecio;
    @Column(name = "ID_IVA")
    private Integer idIva;
    @Transient
    private String nombreUen;
    @Transient
    private String nombreComprador;
    @Transient
    private List<NvcVmOaIvaH> ivaList;
    @Transient
    private List<NvcTblOaLocalizacionesH> billToList;
    @Transient
    private String aprobadorCatalogo;

    public NvcTblCatalogoUen() {
    }

    public NvcTblCatalogoUen(Integer idCatalogoUen, Integer idUen, String nombreUen, Integer tipoRecibo,
            Integer idFacturacion, String ocManual, String idComprador, String nombreComprador, Integer idIva) {
        this.idCatalogoUen = idCatalogoUen;
        this.idUen = idUen;
        this.nombreUen = nombreUen;
        this.tipoRecibo = tipoRecibo;
        this.idFacturacion = idFacturacion;
        this.ocManual = ocManual;
        this.idComprador = idComprador;
        this.nombreComprador = nombreComprador;
        this.idIva = idIva;
    }

    public NvcTblCatalogoUen(Integer idCatalogoUen, Date fechaCreacion, Date fechaActualizacion, String usuarioCreacion, String usuarioActualizacion) {
        this.idCatalogoUen = idCatalogoUen;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.usuarioCreacion = usuarioCreacion;
        this.usuarioActualizacion = usuarioActualizacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCatalogoUen != null ? idCatalogoUen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof NvcTblCatalogoUen)) {
            return false;
        }
        NvcTblCatalogoUen other = (NvcTblCatalogoUen) object;
        if ((this.idCatalogoUen == null && other.idCatalogoUen != null) || (this.idCatalogoUen != null && !this.idCatalogoUen.equals(other.idCatalogoUen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.spx.ws.catalogo.NvcTblCatalogoUen[ idCatalogoUen=" + idCatalogoUen + " ]";
    }

    public Integer getIdCatalogoUen() {
        return idCatalogoUen;
    }

    public void setIdCatalogoUen(Integer idCatalogoUen) {
        this.idCatalogoUen = idCatalogoUen;
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

    public Integer getIdUen() {
        return idUen;
    }

    public void setIdUen(Integer idUen) {
        this.idUen = idUen;
    }

    public Integer getIdCatalogo() {
        return idCatalogo;
    }

    public void setIdCatalogo(Integer idCatalogo) {
        this.idCatalogo = idCatalogo;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public Integer getTipoRecibo() {
        return tipoRecibo;
    }

    public void setTipoRecibo(Integer tipoRecibo) {
        this.tipoRecibo = tipoRecibo;
    }

    public Integer getIdFacturacion() {
        return idFacturacion;
    }

    public void setIdFacturacion(Integer idFacturacion) {
        this.idFacturacion = idFacturacion;
    }

    public String getOcManual() {
        return ocManual;
    }

    public void setOcManual(String ocManual) {
        this.ocManual = ocManual;
    }

    public String getIdComprador() {
        return idComprador;
    }

    public void setIdComprador(String idComprador) {
        this.idComprador = idComprador;
    }

    public Integer getTipoPrecio() {
        return tipoPrecio;
    }

    public void setTipoPrecio(Integer tipoPrecio) {
        this.tipoPrecio = tipoPrecio;
    }

    public Integer getIdIva() {
        return idIva;
    }

    public void setIdIva(Integer idIva) {
        this.idIva = idIva;
    }

    public String getNombreUen() {
        return nombreUen;
    }

    public void setNombreUen(String nombreUen) {
        this.nombreUen = nombreUen;
    }

    public String getNombreComprador() {
        return nombreComprador;
    }

    public void setNombreComprador(String nombreComprador) {
        this.nombreComprador = nombreComprador;
    }

    public List<NvcVmOaIvaH> getIvaList() {
        return ivaList;
    }

    public void setIvaList(List<NvcVmOaIvaH> ivaList) {
        this.ivaList = ivaList;
    }

    public List<NvcTblOaLocalizacionesH> getBillToList() {
        return billToList;
    }

    public void setBillToList(List<NvcTblOaLocalizacionesH> billToList) {
        this.billToList = billToList;
    }

    public String getAprobadorCatalogo() {
        return aprobadorCatalogo;
    }

    public void setAprobadorCatalogo(String aprobadorCatalogo) {
        this.aprobadorCatalogo = aprobadorCatalogo;
    }

}
