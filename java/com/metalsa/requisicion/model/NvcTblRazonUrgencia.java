package com.metalsa.requisicion.model;

import com.metalsa.catalogo.model.NvcTblCatalogoItem;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author APOMS7376
 */
@Entity
@Table(name = "NVC_TBL_RAZON_URGENCIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NvcTblRazonUrgencia.findAll", query = "SELECT n FROM NvcTblRazonUrgencia n")
    ,
    @NamedQuery(name = "NvcTblRazonUrgencia.findByIdRazonUrgencia", query = "SELECT n FROM NvcTblRazonUrgencia n WHERE n.idRazonUrgencia = :idRazonUrgencia")
    ,
    @NamedQuery(name = "NvcTblRazonUrgencia.findByRazonUrgencia", query = "SELECT n FROM NvcTblRazonUrgencia n WHERE n.descripcionUrgencia = :descripcionUrgencia")
    ,
    @NamedQuery(name = "NvcTblRazonUrgencia.findByFechaCreacion", query = "SELECT n FROM NvcTblRazonUrgencia n WHERE n.fechaCreacion = :fechaCreacion")
    ,
    @NamedQuery(name = "NvcTblRazonUrgencia.findByFechaActualizacion", query = "SELECT n FROM NvcTblRazonUrgencia n WHERE n.fechaActualizacion = :fechaActualizacion")
    ,
    @NamedQuery(name = "NvcTblRazonUrgencia.findByUsuarioCreacion", query = "SELECT n FROM NvcTblRazonUrgencia n WHERE n.usuarioCreacion = :usuarioCreacion")
    ,
    @NamedQuery(name = "NvcTblRazonUrgencia.findByUsuarioModificacion", query = "SELECT n FROM NvcTblRazonUrgencia n WHERE n.usuarioModificacion = :usuarioModificacion")})
public class NvcTblRazonUrgencia implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRazonUrgencia")
    private Collection<NvcTblCarroCompra> nvcTblCarroCompraCollection;

    @Basic(optional = false)
    @Column(name = "DESCRIPCION_URGENCIA")
    private String descripcionUrgencia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRazonUrgencia")
    private Collection<NvcTblRazonUrgenciaIdioma> nvcTblRazonUrgenciaIdiomaCollection;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_RAZON_URGENCIA")
    private Integer idRazonUrgencia;
    @Basic(optional = false)
    @NotNull
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
    @Column(name = "USUARIO_MODIFICACION")
    private String usuarioModificacion;
    @OneToMany(mappedBy = "idRazonUrgencia")
    private List<NvcTblCatalogoItem> nvcTblCatalogoItemCollection;

    public NvcTblRazonUrgencia() {
    }

    public NvcTblRazonUrgencia(Integer idRazonUrgencia) {
        this.idRazonUrgencia = idRazonUrgencia;
    }

    public Integer getIdRazonUrgencia() {
        return idRazonUrgencia;
    }

    public void setIdRazonUrgencia(Integer idRazonUrgencia) {
        this.idRazonUrgencia = idRazonUrgencia;
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

    public String getUsuarioModificacion() {
        return usuarioModificacion;
    }

    public void setUsuarioModificacion(String usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    @XmlTransient
    public List<NvcTblCatalogoItem> getNvcTblCatalogoItemCollection() {
        return nvcTblCatalogoItemCollection;
    }

    public void setNvcTblCatalogoItemCollection(List<NvcTblCatalogoItem> nvcTblCatalogoItemCollection) {
        this.nvcTblCatalogoItemCollection = nvcTblCatalogoItemCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRazonUrgencia != null ? idRazonUrgencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NvcTblRazonUrgencia)) {
            return false;
        }
        NvcTblRazonUrgencia other = (NvcTblRazonUrgencia) object;
        if ((this.idRazonUrgencia == null && other.idRazonUrgencia != null) || (this.idRazonUrgencia != null && !this.idRazonUrgencia.equals(other.idRazonUrgencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.spx.entities.NvcTblRazonUrgencia[ idRazonUrgencia=" + idRazonUrgencia + " ]";
    }

    public String getDescripcionUrgencia() {
        return descripcionUrgencia;
    }

    public void setDescripcionUrgencia(String descripcionUrgencia) {
        this.descripcionUrgencia = descripcionUrgencia;
    }

    @XmlTransient
    public Collection<NvcTblRazonUrgenciaIdioma> getNvcTblRazonUrgenciaIdiomaCollection() {
        return nvcTblRazonUrgenciaIdiomaCollection;
    }

    public void setNvcTblRazonUrgenciaIdiomaCollection(Collection<NvcTblRazonUrgenciaIdioma> nvcTblRazonUrgenciaIdiomaCollection) {
        this.nvcTblRazonUrgenciaIdiomaCollection = nvcTblRazonUrgenciaIdiomaCollection;
    }

    @XmlTransient
    public Collection<NvcTblCarroCompra> getNvcTblCarroCompraCollection() {
        return nvcTblCarroCompraCollection;
    }

    public void setNvcTblCarroCompraCollection(Collection<NvcTblCarroCompra> nvcTblCarroCompraCollection) {
        this.nvcTblCarroCompraCollection = nvcTblCarroCompraCollection;
    }

}
