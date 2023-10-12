/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.requisicion.model;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author APOPH5586
 */
@Entity
@Table(name = "NVC_TBL_CATEGORIES_LNG")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NvcTblCategoriesLng.findAll", query = "SELECT n FROM NvcTblCategoriesLng n")
    ,
    @NamedQuery(name = "NvcTblCategoriesLng.findByIdCat", query = "SELECT n FROM NvcTblCategoriesLng n WHERE n.nvcTblCategoriesLngPK.idCat = :idCat")
    ,
    @NamedQuery(name = "NvcTblCategoriesLng.findByIdIdioma", query = "SELECT n FROM NvcTblCategoriesLng n WHERE n.nvcTblCategoriesLngPK.idIdioma = :idIdioma")
    ,
    @NamedQuery(name = "NvcTblCategoriesLng.findByDescripcionIdioma", query = "SELECT n FROM NvcTblCategoriesLng n WHERE n.descripcionIdioma = :descripcionIdioma")})
public class NvcTblCategoriesLng implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NvcTblCategoriesLngPK nvcTblCategoriesLngPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "DESCRIPCION_IDIOMA", nullable = false, length = 500)
    private String descripcionIdioma;
    @JoinColumn(name = "ID_CAT", referencedColumnName = "ID_CAT", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private NvcTblCategories nvcTblCategories;
    @JoinColumn(name = "ID_IDIOMA", referencedColumnName = "SC_ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DcIdioma dcIdioma;

    public NvcTblCategoriesLng() {
    }

    public NvcTblCategoriesLng(NvcTblCategoriesLngPK nvcTblCategoriesLngPK) {
        this.nvcTblCategoriesLngPK = nvcTblCategoriesLngPK;
    }

    public NvcTblCategoriesLng(NvcTblCategoriesLngPK nvcTblCategoriesLngPK, String descripcionIdioma) {
        this.nvcTblCategoriesLngPK = nvcTblCategoriesLngPK;
        this.descripcionIdioma = descripcionIdioma;
    }

    public NvcTblCategoriesLng(BigInteger idCat, String idIdioma) {
        this.nvcTblCategoriesLngPK = new NvcTblCategoriesLngPK(idCat, idIdioma);
    }

    public NvcTblCategoriesLngPK getNvcTblCategoriesLngPK() {
        return nvcTblCategoriesLngPK;
    }

    public void setNvcTblCategoriesLngPK(NvcTblCategoriesLngPK nvcTblCategoriesLngPK) {
        this.nvcTblCategoriesLngPK = nvcTblCategoriesLngPK;
    }

    public String getDescripcionIdioma() {
        return descripcionIdioma;
    }

    public void setDescripcionIdioma(String descripcionIdioma) {
        this.descripcionIdioma = descripcionIdioma;
    }

    public NvcTblCategories getNvcTblCategories() {
        return nvcTblCategories;
    }

    public void setNvcTblCategories(NvcTblCategories nvcTblCategories) {
        this.nvcTblCategories = nvcTblCategories;
    }

    public DcIdioma getDcIdioma() {
        return dcIdioma;
    }

    public void setDcIdioma(DcIdioma dcIdioma) {
        this.dcIdioma = dcIdioma;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nvcTblCategoriesLngPK != null ? nvcTblCategoriesLngPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NvcTblCategoriesLng)) {
            return false;
        }
        NvcTblCategoriesLng other = (NvcTblCategoriesLng) object;
        if ((this.nvcTblCategoriesLngPK == null && other.nvcTblCategoriesLngPK != null) || (this.nvcTblCategoriesLngPK != null && !this.nvcTblCategoriesLngPK.equals(other.nvcTblCategoriesLngPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.presupuesto.NvcTblCategoriesLng[ nvcTblCategoriesLngPK=" + nvcTblCategoriesLngPK + " ]";
    }

}
