package com.metalsa.admin.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @author APOMR10051
 */
@Entity
@Table(name = "PROVEEDOR_DIRIGIDO")
@XmlRootElement
@SqlResultSetMappings({
    @SqlResultSetMapping(
            name = "provDirMapping",
            classes = {
                @ConstructorResult(
                        targetClass = ProveedorDirigido.class,
                        columns = {
                            @ColumnResult(name = "ID_PROV_DIRIGIDO", type = Integer.class)
                            ,
                            @ColumnResult(name = "ID_PROVEEDOR", type = Integer.class)
                            ,
                            @ColumnResult(name = "NOMBRE_PROVEEDOR", type = StringType.class)
                            ,
                            @ColumnResult(name = "ID_UEN", type = Integer.class)
                            ,
                            @ColumnResult(name = "MARCA", type = StringType.class)
                            ,
                            @ColumnResult(name = "FECHA_CREACION", type = Date.class)
                            ,
                            @ColumnResult(name = "FECHA_ACTUALIZACION", type = Date.class)
                            ,
                            @ColumnResult(name = "USUARIO_CREACION", type = StringType.class)
                            ,
                            @ColumnResult(name = "USUARIO_ACTUALIZACION", type = StringType.class)
                            ,
                            @ColumnResult(name = "ACTIVO", type = Integer.class)
                        }
                )
            }
    )
})
@NamedQueries({
    @NamedQuery(name = "ProveedorDirigido.findAll", query = "SELECT n FROM ProveedorDirigido n WHERE n.activo = 1")
})
@NamedNativeQueries({
    @NamedNativeQuery(
            name = "ProveedorDirigido.getProvDirigidosUen",
            resultClass = ProveedorDirigido.class,
            resultSetMapping = "provDirMapping",
            query = "select pd.id_prov_dirigido, pd.id_proveedor, p.nombre_proveedor, pd.id_uen, pd.marca, "
            + "pd.fecha_creacion, pd.fecha_actualizacion, pd.usuario_creacion, pd.usuario_actualizacion, pd.activo "
            + "from proveedor_dirigido pd join nvc_tbl_oa_proveedores_h p on pd.id_proveedor = p.id_proveedor "
            + "where pd.activo = 1 and pd.id_uen = :idUen order by p.nombre_proveedor"
    )
})
public class ProveedorDirigido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PROV_DIRIGIDO")
    private Integer idProvDirigido;
    @Column(name = "ID_PROVEEDOR")
    private Integer idProveedor;
    @Transient
    private String nombreProveedor;
    @Column(name = "ID_UEN")
    private Integer idUen;
    @Size(min = 1, max = 256)
    @Column(name = "MARCA")
    private String marca;
    @JsonFormat(pattern = "dd-MMM-yyyy")
    @Column(name = "FECHA_CREACION")
    private Date fechaCreacion;
    @Column(name = "FECHA_ACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @Column(name = "USUARIO_CREACION")
    private String usuarioCreacion;
    @Size(max = 256)
    @Column(name = "USUARIO_ACTUALIZACION")
    private String usuarioActualizacion;
    @Column(name = "ACTIVO")
    private Integer activo;
    @Transient
    private List<ProveedorDirigidoPorTipo> tiposProvDirigido;
    @Transient
    private List<NvcTblDocumento> adjuntos;
    @Transient
    private List<NvcTblDocumento> adjuntosPreview;
    private String fechaCreacionString;

    public ProveedorDirigido() {

    }

    public ProveedorDirigido(Integer idProvDirigido, Integer idProveedor, String nombreProveedor, Integer idUen, String marca, Date fechaCreacion,
            Date fechaActualizacion, String usuarioCreacion, String usuarioActualizacion, Integer activo) {
        this.idProvDirigido = idProvDirigido;
        this.idProveedor = idProveedor;
        this.nombreProveedor = nombreProveedor;
        this.idUen = idUen;
        this.marca = marca;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.usuarioCreacion = usuarioCreacion;
        this.usuarioActualizacion = usuarioActualizacion;
        this.activo = activo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getIdProvDirigido() != null ? getIdProvDirigido().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProveedorDirigido)) {
            return false;
        }
        ProveedorDirigido other = (ProveedorDirigido) object;
        if ((this.getIdProvDirigido() == null && other.getIdProvDirigido() != null) || (this.getIdProvDirigido() != null && !this.idProvDirigido.equals(other.idProvDirigido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.admin.model.ProveedorDirigido[ idProvDirigido=" + getIdProvDirigido() + " ]";
    }

    public Integer getIdProvDirigido() {
        return idProvDirigido;
    }

    public void setIdProvDirigido(Integer idProvDirigido) {
        this.idProvDirigido = idProvDirigido;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public Integer getIdUen() {
        return idUen;
    }

    public void setIdUen(Integer idUen) {
        this.idUen = idUen;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
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

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public List<ProveedorDirigidoPorTipo> getTiposProvDirigido() {
        return tiposProvDirigido;
    }

    public void setTiposProvDirigido(List<ProveedorDirigidoPorTipo> tiposProvDirigido) {
        this.tiposProvDirigido = tiposProvDirigido;
    }

    public List<NvcTblDocumento> getAdjuntos() {
        return adjuntos;
    }

    public void setAdjuntos(List<NvcTblDocumento> adjuntos) {
        this.adjuntos = adjuntos;
    }

    public List<NvcTblDocumento> getAdjuntosPreview() {
        return adjuntosPreview;
    }

    public void setAdjuntosPreview(List<NvcTblDocumento> adjuntosPreview) {
        this.adjuntosPreview = adjuntosPreview;
    }

    public String getFechaCreacionString() {
        return fechaCreacionString;
    }

    public void setFechaCreacionString(String fechaCreacionString) {
        this.fechaCreacionString = fechaCreacionString;
    }
    
    

}
