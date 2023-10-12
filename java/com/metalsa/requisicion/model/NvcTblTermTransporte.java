package com.metalsa.requisicion.model;

import com.metalsa.portalProveedor.model.NvcTblCotizaciones;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author APOPH5586
 */
@Entity
@Table(name = "NVC_TBL_TERM_TRANSPORTE", catalog = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NvcTblTermTransporte.findAll", query = "SELECT n FROM NvcTblTermTransporte n")
    ,
    @NamedQuery(name = "NvcTblTermTransporte.findByIdTerm", query = "SELECT n FROM NvcTblTermTransporte n WHERE n.idTerm = ?1")
    ,
    @NamedQuery(name = "NvcTblTermTransporte.findByDescripcion", query = "SELECT n FROM NvcTblTermTransporte n WHERE n.descripcion = :descripcion")
    ,
    @NamedQuery(name = "NvcTblTermTransporte.findByActive", query = "SELECT n FROM NvcTblTermTransporte n WHERE n.active = :active")
    ,
    @NamedQuery(name = "NvcTblTermTransporte.findByIdioma", query = "SELECT n FROM NvcTblTermTransporte n WHERE n.idIdioma.scId = ?1")
})
public class NvcTblTermTransporte implements Serializable {

    @OneToMany(mappedBy = "idTipoTransp")
    private Collection<NvcTblCotizaciones> nvcTblCotizacionesCollection1;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TERM", nullable = false, precision = 0, scale = -127)
    private BigDecimal idTerm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DESCRIPCION", nullable = false, length = 100)
    private String descripcion;
    @Column(name = "ACTIVE")
    private BigInteger active;
    @OneToMany(mappedBy = "idTerm", fetch = FetchType.LAZY)
    private List<NvcTblRfq> nvcTblRfqList;
    @JoinColumn(name = "ID_IDIOMA", referencedColumnName = "SC_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DcIdioma idIdioma;

    public NvcTblTermTransporte() {
    }

    public NvcTblTermTransporte(BigDecimal idTerm) {
        this.idTerm = idTerm;
    }

    public NvcTblTermTransporte(BigDecimal idTerm, String descripcion) {
        this.idTerm = idTerm;
        this.descripcion = descripcion;
    }

    public BigDecimal getIdTerm() {
        return idTerm;
    }

    public void setIdTerm(BigDecimal idTerm) {
        this.idTerm = idTerm;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigInteger getActive() {
        return active;
    }

    public void setActive(BigInteger active) {
        this.active = active;
    }

    @XmlTransient
    public List<NvcTblRfq> getNvcTblRfqList() {
        return nvcTblRfqList;
    }

    public void setNvcTblRfqList(List<NvcTblRfq> nvcTblRfqList) {
        this.nvcTblRfqList = nvcTblRfqList;
    }

    public DcIdioma getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(DcIdioma idIdioma) {
        this.idIdioma = idIdioma;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTerm != null ? idTerm.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NvcTblTermTransporte)) {
            return false;
        }
        NvcTblTermTransporte other = (NvcTblTermTransporte) object;
        if ((this.idTerm == null && other.idTerm != null) || (this.idTerm != null && !this.idTerm.equals(other.idTerm))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.cotizaciones.NvcTblTermTransporte[ idTerm=" + idTerm + " ]";
    }

    @XmlTransient
    public Collection<NvcTblCotizaciones> getNvcTblCotizacionesCollection1() {
        return nvcTblCotizacionesCollection1;
    }

    public void setNvcTblCotizacionesCollection1(Collection<NvcTblCotizaciones> nvcTblCotizacionesCollection1) {
        this.nvcTblCotizacionesCollection1 = nvcTblCotizacionesCollection1;
    }
}
