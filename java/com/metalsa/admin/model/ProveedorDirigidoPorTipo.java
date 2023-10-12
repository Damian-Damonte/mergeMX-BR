package com.metalsa.admin.model;

import java.io.Serializable;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.type.StringType;

/**
 *
 * @author APOMR10051
 */
@Entity
@Table(name = "PROVEEDOR_DIRIGIDO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProveedorDirigidoPorTipo.findAll", query = "SELECT n FROM ProveedorDirigidoPorTipo n")
})
@SqlResultSetMappings({
    @SqlResultSetMapping(
            name = "ProvDirMapping",
            classes = {
                @ConstructorResult(
                        targetClass = ProveedorDirigidoPorTipo.class,
                        columns = {
                            @ColumnResult(name = "ID_TIPO_PROVEEDOR", type = Integer.class),
                            @ColumnResult(name = "DESCRIPCION", type = StringType.class)}
                )}
    )
})
@NamedNativeQueries({
    @NamedNativeQuery(
            name = "ProveedorDirigidoPorTipo.getByIdProvDirigido",
            resultClass = ProveedorDirigidoPorTipo.class,
            resultSetMapping = "ProvDirMapping",
            query = "select tpd.id_tipo_proveedor, tpi.descripcion from PROVEEDOR_DIRIGIDO_POR_TIPO ppt "
            + "join TIPO_PROVEEDOR_DIRIGIDO tpd on ppt.id_tipo_proveedor = tpd.id_tipo_proveedor  "
            + "join TIPO_PROVEEDOR_DIRIGIDO_IDIOMA tpi on tpd.id_tipo_proveedor = tpi.id_tipo_proveedor and tpi.id_idioma = :idIdioma "
            + "where id_prov_dirigido = :idProvDirigido "
    ),
    @NamedNativeQuery(
            name = "ProveedorDirigidoPorTipo.getAllTipos",
            resultClass = ProveedorDirigidoPorTipo.class,
            resultSetMapping = "ProvDirMapping",
            query = "select tpd.id_tipo_proveedor, tpi.descripcion from TIPO_PROVEEDOR_DIRIGIDO tpd "
            + "join TIPO_PROVEEDOR_DIRIGIDO_IDIOMA tpi on tpd.id_tipo_proveedor = tpi.id_tipo_proveedor and tpi.id_idioma = :idIdioma "
    )
})
public class ProveedorDirigidoPorTipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PROV_DIRIGIDO")
    private Integer idProvDirigido;
    @Column(name = "ID_TIPO_PROVEEDOR")
    private Integer idTipoProveedor;
    @Transient
    private String descripcion;

    public ProveedorDirigidoPorTipo() {
    }

    public ProveedorDirigidoPorTipo(Integer idTipoProveedor, String descripcion) {
        this.idTipoProveedor = idTipoProveedor;
        this.descripcion = descripcion;
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
        if (!(object instanceof ProveedorDirigidoPorTipo)) {
            return false;
        }
        ProveedorDirigidoPorTipo other = (ProveedorDirigidoPorTipo) object;
        if ((this.getIdProvDirigido() == null && other.getIdProvDirigido() != null) || (this.getIdProvDirigido() != null && !this.idProvDirigido.equals(other.idProvDirigido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.admin.model.ProveedorDirigidoPorTipo[ idProvDirigido=" + getIdProvDirigido() + " ]";
    }

    public Integer getIdProvDirigido() {
        return idProvDirigido;
    }

    public void setIdProvDirigido(Integer idProvDirigido) {
        this.idProvDirigido = idProvDirigido;
    }

    public Integer getIdTipoProveedor() {
        return idTipoProveedor;
    }

    public void setIdTipoProveedor(Integer idTipoProveedor) {
        this.idTipoProveedor = idTipoProveedor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
