package com.metalsa.requisicion.model;

import com.metalsa.requisicion.model.NvcTblRazonUrgencia;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author APOMS7376
 */
@Entity
@Table(name = "NVC_TBL_RAZON_URGENCIA_IDIOMA")
@NamedQueries({
    @NamedQuery(name = "NvcTblRazonUrgenciaIdioma.findAll", query = "SELECT n FROM NvcTblRazonUrgenciaIdioma n ")
    ,
    @NamedQuery(name = "NvcTblRazonUrgencia.findByIdAndIdioma", query = "SELECT n FROM NvcTblRazonUrgenciaIdioma n WHERE n.idIdioma.scId = :scId and n.idRazonUrgenciaIdioma = :idRazonUrgenciaIdioma")
    ,
    @NamedQuery(name = "NvcTblRazonUrgencia.findByIdRazonIdioma", query = "SELECT n FROM NvcTblRazonUrgenciaIdioma n WHERE n.idRazonUrgenciaIdioma =:idRazonUrgenciaIdioma")
})
public class NvcTblRazonUrgenciaIdioma implements Serializable {

    private static final long serialVersionUID = 1L;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_RAZON_URGENCIA_IDIOMA")
    private BigDecimal idRazonUrgenciaIdioma;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 600)
    @Column(name = "RAZON_URGENCIA")
    private String razonUrgencia;
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
    @JoinColumn(name = "ID_IDIOMA", referencedColumnName = "SC_ID")
    @ManyToOne(optional = false)
    private DcIdioma idIdioma;
    @JoinColumn(name = "ID_RAZON_URGENCIA", referencedColumnName = "ID_RAZON_URGENCIA")
    @ManyToOne(optional = false)
    private NvcTblRazonUrgencia idRazonUrgencia;

    public NvcTblRazonUrgenciaIdioma() {
    }

    public NvcTblRazonUrgenciaIdioma(BigDecimal idRazonUrgenciaIdioma) {
        this.idRazonUrgenciaIdioma = idRazonUrgenciaIdioma;
    }

    public NvcTblRazonUrgenciaIdioma(BigDecimal idRazonUrgenciaIdioma, String razonUrgencia) {
        this.idRazonUrgenciaIdioma = idRazonUrgenciaIdioma;
        this.razonUrgencia = razonUrgencia;
    }

    public BigDecimal getIdRazonUrgenciaIdioma() {
        return idRazonUrgenciaIdioma;
    }

    public void setIdRazonUrgenciaIdioma(BigDecimal idRazonUrgenciaIdioma) {
        this.idRazonUrgenciaIdioma = idRazonUrgenciaIdioma;
    }

    public String getRazonUrgencia() {
        return razonUrgencia;
    }

    public void setRazonUrgencia(String razonUrgencia) {
        this.razonUrgencia = razonUrgencia;
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

    public DcIdioma getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(DcIdioma idIdioma) {
        this.idIdioma = idIdioma;
    }

    public NvcTblRazonUrgencia getIdRazonUrgencia() {
        return idRazonUrgencia;
    }

    public void setIdRazonUrgencia(NvcTblRazonUrgencia idRazonUrgencia) {
        this.idRazonUrgencia = idRazonUrgencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRazonUrgenciaIdioma != null ? idRazonUrgenciaIdioma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NvcTblRazonUrgenciaIdioma)) {
            return false;
        }
        NvcTblRazonUrgenciaIdioma other = (NvcTblRazonUrgenciaIdioma) object;
        if ((this.idRazonUrgenciaIdioma == null && other.idRazonUrgenciaIdioma != null) || (this.idRazonUrgenciaIdioma != null && !this.idRazonUrgenciaIdioma.equals(other.idRazonUrgenciaIdioma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.spx.entities.NvcTblRazonUrgenciaIdioma[ idRazonUrgenciaIdioma=" + idRazonUrgenciaIdioma + " ]";
    }

}
