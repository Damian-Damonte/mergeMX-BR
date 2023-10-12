package com.metalsa.requisicion.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author APOJA5585
 */
@Entity
@Table(name = "NVC_TBL_CAT_MEDIO", catalog = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NvcTblCatMedio.findAll", query = "SELECT n FROM NvcTblCatMedio n")
    ,
    @NamedQuery(name = "NvcTblCatMedio.findByIdCatMedio", query = "SELECT n FROM NvcTblCatMedio n WHERE n.idCatMedio = :idCatMedio")
    ,
    @NamedQuery(name = "NvcTblCatMedio.findByDescripcion", query = "SELECT n FROM NvcTblCatMedio n WHERE n.descripcion = :descripcion")})
public class NvcTblCatMedio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CAT_MEDIO")
    private Integer idCatMedio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCatMedio")
    private Collection<NvcTblMedio> nvcTblMedioCollection;

    public NvcTblCatMedio() {
    }

    public NvcTblCatMedio(Integer idCatMedio) {
        this.idCatMedio = idCatMedio;
    }

    public NvcTblCatMedio(Integer idCatMedio, String descripcion) {
        this.idCatMedio = idCatMedio;
        this.descripcion = descripcion;
    }

    public Integer getIdCatMedio() {
        return idCatMedio;
    }

    public void setIdCatMedio(Integer idCatMedio) {
        this.idCatMedio = idCatMedio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<NvcTblMedio> getNvcTblMedioCollection() {
        return nvcTblMedioCollection;
    }

    public void setNvcTblMedioCollection(Collection<NvcTblMedio> nvcTblMedioCollection) {
        this.nvcTblMedioCollection = nvcTblMedioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCatMedio != null ? idCatMedio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NvcTblCatMedio)) {
            return false;
        }
        NvcTblCatMedio other = (NvcTblCatMedio) object;
        if ((this.idCatMedio == null && other.idCatMedio != null) || (this.idCatMedio != null && !this.idCatMedio.equals(other.idCatMedio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.cotizaciones.NvcTblCatMedio[ idCatMedio=" + idCatMedio + " ]";
    }

}
