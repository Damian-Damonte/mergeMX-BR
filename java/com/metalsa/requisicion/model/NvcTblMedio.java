package com.metalsa.requisicion.model;

import com.metalsa.aprobacion.model.NvcTblOrganizacionesH;
import com.metalsa.portalProveedor.model.NvcTblCotizaciones;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author APOJA5585
 */
@Entity
@Table(name = "NVC_TBL_MEDIO", catalog = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NvcTblMedio.findAll", query = "SELECT n FROM NvcTblMedio n")
    ,
    @NamedQuery(name = "NvcTblMedio.findByIdMedio", query = "SELECT n FROM NvcTblMedio n WHERE n.idMedio = ?1")
    ,
    @NamedQuery(name = "NvcTblMedio.findByActivo", query = "SELECT n FROM NvcTblMedio n WHERE n.activo = ?1")
    ,
    @NamedQuery(name = "NvcTblMedio.findByDescCatMedio", query = "SELECT n FROM NvcTblMedio n WHERE n.idCatMedio.descripcion = ?1")
})
public class NvcTblMedio implements Serializable {

    @OneToMany(mappedBy = "idMedio", fetch = FetchType.LAZY)
    private List<NvcTblRfq> nvcTblRfqList;
    @OneToMany(mappedBy = "idMedio")
    private Collection<NvcTblCotizaciones> nvcTblCotizacionesCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_MEDIO")
    private Integer idMedio;
    @Column(name = "ACTIVO")
    private Short activo;
    @JoinColumn(name = "ID_UEN", referencedColumnName = "ID_UEN")
    @ManyToOne
    private NvcTblOrganizacionesH idUen;
    @JoinColumn(name = "ID_CAT_MEDIO", referencedColumnName = "ID_CAT_MEDIO")
    @ManyToOne(optional = false)
    private NvcTblCatMedio idCatMedio;

    public NvcTblMedio() {
    }

    public NvcTblMedio(Integer idMedio) {
        this.idMedio = idMedio;
    }

    public Integer getIdMedio() {
        return idMedio;
    }

    public void setIdMedio(Integer idMedio) {
        this.idMedio = idMedio;
    }

    public Short getActivo() {
        return activo;
    }

    public void setActivo(Short activo) {
        this.activo = activo;
    }

    public NvcTblOrganizacionesH getIdUen() {
        return idUen;
    }

    public void setIdUen(NvcTblOrganizacionesH idUen) {
        this.idUen = idUen;
    }

    public NvcTblCatMedio getIdCatMedio() {
        return idCatMedio;
    }

    public void setIdCatMedio(NvcTblCatMedio idCatMedio) {
        this.idCatMedio = idCatMedio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMedio != null ? idMedio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NvcTblMedio)) {
            return false;
        }
        NvcTblMedio other = (NvcTblMedio) object;
        if ((this.idMedio == null && other.idMedio != null) || (this.idMedio != null && !this.idMedio.equals(other.idMedio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.cotizaciones.NvcTblMedio[ idMedio=" + idMedio + " ]";
    }

    @XmlTransient
    public Collection<NvcTblCotizaciones> getNvcTblCotizacionesCollection() {
        return nvcTblCotizacionesCollection;
    }

    public void setNvcTblCotizacionesCollection(Collection<NvcTblCotizaciones> nvcTblCotizacionesCollection) {
        this.nvcTblCotizacionesCollection = nvcTblCotizacionesCollection;
    }

    @XmlTransient
    public List<NvcTblRfq> getNvcTblRfqList() {
        return nvcTblRfqList;
    }

    public void setNvcTblRfqList(List<NvcTblRfq> nvcTblRfqList) {
        this.nvcTblRfqList = nvcTblRfqList;
    }

}
