package com.metalsa.requisicion.model;

import com.metalsa.catalogo.model.NvcTblCatalogoItem;
import java.io.Serializable;
import java.math.BigDecimal;
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
 * @author APOMS7376
 */
@Entity
@Table(name = "NVC_TBL_UDM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NvcTblUdm.findAll", query = "SELECT n FROM NvcTblUdm n")
    ,
    @NamedQuery(name = "NvcTblUdm.findByIdUdm", query = "SELECT n FROM NvcTblUdm n WHERE n.idUdm = :idUdm")
    ,
    @NamedQuery(name = "NvcTblUdm.findByIdUnidadDeMedida", query = "SELECT n FROM NvcTblUdm n WHERE n.idUnidadDeMedida = :idUnidadDeMedida")
    ,
    @NamedQuery(name = "NvcTblUdm.findByUnidadDeMedida", query = "SELECT n FROM NvcTblUdm n WHERE n.unidadDeMedida = :unidadDeMedida")
    ,
    @NamedQuery(name = "NvcTblUdm.findByLenguaje", query = "SELECT n FROM NvcTblUdm n WHERE n.lenguaje = :lenguaje")
    ,
    @NamedQuery(name = "NvcTblUdm.findByIdUnidadDeMedidaLenguaje", query = "SELECT n FROM NvcTblUdm n WHERE n.lenguaje = :lenguaje and n.idUnidadDeMedida = :idUnidadDeMedida ")})
public class NvcTblUdm implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUdm")
    private Collection<NvcTblCarroCompra> nvcTblCarroCompraCollection;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_UDM")
    private BigDecimal idUdm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "ID_UNIDAD_DE_MEDIDA")
    private String idUnidadDeMedida;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "UNIDAD_DE_MEDIDA")
    private String unidadDeMedida;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "LENGUAJE")
    private String lenguaje;
    @OneToMany(mappedBy = "udm")
    private Collection<NvcTblCatalogoItem> nvcTblCatalogoItemCollection;

    public NvcTblUdm() {
    }

    public NvcTblUdm(BigDecimal idUdm) {
        this.idUdm = idUdm;
    }

    public NvcTblUdm(BigDecimal idUdm, String idUnidadDeMedida, String unidadDeMedida, String lenguaje) {
        this.idUdm = idUdm;
        this.idUnidadDeMedida = idUnidadDeMedida;
        this.unidadDeMedida = unidadDeMedida;
        this.lenguaje = lenguaje;
    }

    public BigDecimal getIdUdm() {
        return idUdm;
    }

    public void setIdUdm(BigDecimal idUdm) {
        this.idUdm = idUdm;
    }

    public String getIdUnidadDeMedida() {
        return idUnidadDeMedida;
    }

    public void setIdUnidadDeMedida(String idUnidadDeMedida) {
        this.idUnidadDeMedida = idUnidadDeMedida;
    }

    public String getUnidadDeMedida() {
        return unidadDeMedida;
    }

    public void setUnidadDeMedida(String unidadDeMedida) {
        this.unidadDeMedida = unidadDeMedida;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    @XmlTransient
    public Collection<NvcTblCatalogoItem> getNvcTblCatalogoItemCollection() {
        return nvcTblCatalogoItemCollection;
    }

    public void setNvcTblCatalogoItemCollection(Collection<NvcTblCatalogoItem> nvcTblCatalogoItemCollection) {
        this.nvcTblCatalogoItemCollection = nvcTblCatalogoItemCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUdm != null ? idUdm.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NvcTblUdm)) {
            return false;
        }
        NvcTblUdm other = (NvcTblUdm) object;
        if ((this.idUdm == null && other.idUdm != null) || (this.idUdm != null && !this.idUdm.equals(other.idUdm))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.spx.entities.NvcTblUdm[ idUdm=" + idUdm + " ]";
    }

    @XmlTransient
    public Collection<NvcTblCarroCompra> getNvcTblCarroCompraCollection() {
        return nvcTblCarroCompraCollection;
    }

    public void setNvcTblCarroCompraCollection(Collection<NvcTblCarroCompra> nvcTblCarroCompraCollection) {
        this.nvcTblCarroCompraCollection = nvcTblCarroCompraCollection;
    }

}
