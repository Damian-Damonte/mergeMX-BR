package com.metalsa.requisicion.model;

import com.metalsa.aprobacion.model.Usuario;
import com.metalsa.requisicion.pojo.ComprasObject;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author APOPH5586
 */
@Entity
@Table(name = "NVC_TBL_CATEGORIES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NvcTblCategories.findAll", query = "SELECT n FROM NvcTblCategories n")
    ,
    @NamedQuery(name = "NvcTblCategories.findByIdCat", query = "SELECT n FROM NvcTblCategories n WHERE n.idCat = :idCat")
    ,
    @NamedQuery(name = "NvcTblCategories.findByDescripcion", query = "SELECT n FROM NvcTblCategories n WHERE n.descripcion = :descripcion")
    ,
    @NamedQuery(name = "NvcTblCategories.findByActivo", query = "SELECT n FROM NvcTblCategories n WHERE n.activo = :activo")
    ,
    @NamedQuery(name = "NvcTblCategories.findByCreationDate", query = "SELECT n FROM NvcTblCategories n WHERE n.creationDate = :creationDate")})
public class NvcTblCategories implements Serializable, ComprasObject {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "nvcSeqCategories")
    @SequenceGenerator(name = "nvcSeqCategories",
            sequenceName = "NVC_SEQ_CATEGORIES",
            allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CAT", nullable = false, precision = 22)
    private BigDecimal idCat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "DESCRIPCION", nullable = false, length = 500)
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACTIVO", nullable = false)
    private long activo;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nvcTblCategories")
    private List<NvcTblCategoriesLng> nvcTblCategoriesLngList;
    @JoinColumn(name = "UPDATE_USER", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario updateUser;

    public NvcTblCategories() {
    }

    public NvcTblCategories(BigDecimal idCat) {
        this.idCat = idCat;
    }

    public NvcTblCategories(BigDecimal idCat, String descripcion, long activo) {
        this.idCat = idCat;
        this.descripcion = descripcion;
        this.activo = activo;
    }

    public BigDecimal getIdCat() {
        return idCat;
    }

    public void setIdCat(BigDecimal idCat) {
        this.idCat = idCat;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public long getActivo() {
        return activo;
    }

    public void setActivo(long activo) {
        this.activo = activo;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @XmlTransient
    public List<NvcTblCategoriesLng> getNvcTblCategoriesLngList() {
        return nvcTblCategoriesLngList;
    }

    public void setNvcTblCategoriesLngList(List<NvcTblCategoriesLng> nvcTblCategoriesLngList) {
        this.nvcTblCategoriesLngList = nvcTblCategoriesLngList;
    }

    public Usuario getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Usuario updateUser) {
        this.updateUser = updateUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCat != null ? idCat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NvcTblCategories)) {
            return false;
        }
        NvcTblCategories other = (NvcTblCategories) object;
        if ((this.idCat == null && other.idCat != null) || (this.idCat != null && !this.idCat.equals(other.idCat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.presupuesto.NvcTblCategories[ idCat=" + idCat + " ]";
    }

    @Override
    public String objectToString() {
        return this.toString()
                + "\n descripcion: " + descripcion
                + "\n activo: " + activo
                + "\n creationDate: " + creationDate
                + "\n updateUser: " + updateUser;
    }

}
