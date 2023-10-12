package com.metalsa.requisicion.model;

import com.metalsa.core.model.NvcTblOaCcH;
import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author APOPH5586
 */
@Entity
@Table(name = "NVC_TBL_OA_CC_LNG_H")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NvcTblOaCcLngH.findAll", query = "SELECT n FROM NvcTblOaCcLngH n")
    ,
    @NamedQuery(name = "NvcTblOaCcLngH.findByIdUen", query = "SELECT n FROM NvcTblOaCcLngH n WHERE n.nvcTblOaCcLngHPK.idUen = :idUen")
    ,
    @NamedQuery(name = "NvcTblOaCcLngH.findByIdCc", query = "SELECT n FROM NvcTblOaCcLngH n WHERE n.nvcTblOaCcLngHPK.idCc = :idCc")
    ,
    @NamedQuery(name = "NvcTblOaCcLngH.findByIdLang", query = "SELECT n FROM NvcTblOaCcLngH n WHERE n.nvcTblOaCcLngHPK.idLang = :idLang")
    ,
    @NamedQuery(name = "NvcTblOaCcLngH.findByNombreCc", query = "SELECT n FROM NvcTblOaCcLngH n WHERE n.nombreCc = :nombreCc")
    ,
    @NamedQuery(name = "NvcTblOaCcLngH.findByIdUenIdLang", query = "SELECT n FROM NvcTblOaCcLngH n WHERE n.nvcTblOaCcLngHPK.idUen = ?1 AND n.dcIdioma.scId = ?2")
})
public class NvcTblOaCcLngH implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NvcTblOaCcLngHPK nvcTblOaCcLngHPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 720)
    @Column(name = "NOMBRE_CC", nullable = false, length = 720)
    private String nombreCc;
    @JoinColumns({
        @JoinColumn(name = "ID_UEN", referencedColumnName = "ID_UEN", nullable = false, insertable = false, updatable = false)
        ,
        @JoinColumn(name = "ID_CC", referencedColumnName = "ID_CC", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private NvcTblOaCcH nvcTblOaCcH;
    @JoinColumn(name = "ID_LANG", referencedColumnName = "SC_ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DcIdioma dcIdioma;
    @Transient
    private String codigoCC;

    public NvcTblOaCcLngH() {
    }

    public NvcTblOaCcLngH(NvcTblOaCcLngHPK nvcTblOaCcLngHPK) {
        this.nvcTblOaCcLngHPK = nvcTblOaCcLngHPK;
    }

    public NvcTblOaCcLngH(NvcTblOaCcLngHPK nvcTblOaCcLngHPK, String nombreCc) {
        this.nvcTblOaCcLngHPK = nvcTblOaCcLngHPK;
        this.nombreCc = nombreCc;
    }

    public NvcTblOaCcLngH(NvcTblOaCcLngHPK nvcTblOaCcLngHPK, String nombreCc, String codigoCC) {
        this.nvcTblOaCcLngHPK = nvcTblOaCcLngHPK;
        this.nombreCc = nombreCc;
        this.codigoCC = codigoCC;
    }

    public NvcTblOaCcLngH(BigInteger idUen, BigInteger idCc, String idLang) {
        this.nvcTblOaCcLngHPK = new NvcTblOaCcLngHPK(idUen, idCc, idLang);
    }

    public NvcTblOaCcLngHPK getNvcTblOaCcLngHPK() {
        return nvcTblOaCcLngHPK;
    }

    public void setNvcTblOaCcLngHPK(NvcTblOaCcLngHPK nvcTblOaCcLngHPK) {
        this.nvcTblOaCcLngHPK = nvcTblOaCcLngHPK;
    }

    public String getNombreCc() {
        return nombreCc;
    }

    public void setNombreCc(String nombreCc) {
        this.nombreCc = nombreCc;
    }

    public NvcTblOaCcH getNvcTblOaCcH() {
        return nvcTblOaCcH;
    }

    public void setNvcTblOaCcH(NvcTblOaCcH nvcTblOaCcH) {
        this.nvcTblOaCcH = nvcTblOaCcH;
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
        hash += (nvcTblOaCcLngHPK != null ? nvcTblOaCcLngHPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NvcTblOaCcLngH)) {
            return false;
        }
        NvcTblOaCcLngH other = (NvcTblOaCcLngH) object;
        if ((this.nvcTblOaCcLngHPK == null && other.nvcTblOaCcLngHPK != null) || (this.nvcTblOaCcLngHPK != null && !this.nvcTblOaCcLngHPK.equals(other.nvcTblOaCcLngHPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.suite.NvcTblOaCcLngH[ nvcTblOaCcLngHPK=" + nvcTblOaCcLngHPK + " ]";
    }

    public String getCodigoCC() {
        return codigoCC;
    }

    public void setCodigoCC(String codigoCC) {
        this.codigoCC = codigoCC;
    }

}
