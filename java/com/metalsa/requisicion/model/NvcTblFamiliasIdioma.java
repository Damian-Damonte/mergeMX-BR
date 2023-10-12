package com.metalsa.requisicion.model;

import com.metalsa.core.model.NvcTblFamilias;
import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author APOPH5586
 */
@Entity
@Table(name = "NVC_TBL_FAMILIAS_IDIOMA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NvcTblFamiliasIdioma.findAll", query = "SELECT n FROM NvcTblFamiliasIdioma n")
    ,
    @NamedQuery(name = "NvcTblFamiliasIdioma.findByIdFamiliaByIdioma", query = "SELECT n FROM NvcTblFamiliasIdioma n "
            + "WHERE n.nvcTblFamiliasIdiomaPK.idFamilia = ?1 AND n.dcIdioma.scId = ?2")
    ,
    @NamedQuery(name = "NvcTblFamiliasIdioma.findByIdIdioma", query = "SELECT n FROM NvcTblFamiliasIdioma n WHERE n.nvcTblFamiliasIdiomaPK.idIdioma = :idIdioma")
    ,
    @NamedQuery(name = "NvcTblFamiliasIdioma.findByNombreFamiliaIdioma", query = "SELECT n FROM NvcTblFamiliasIdioma n WHERE n.nombreFamiliaIdioma = :nombreFamiliaIdioma")})
public class NvcTblFamiliasIdioma implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NvcTblFamiliasIdiomaPK nvcTblFamiliasIdiomaPK;
    @Size(max = 241)
    @Column(name = "NOMBRE_FAMILIA_IDIOMA", length = 241)
    private String nombreFamiliaIdioma;
    @JoinColumn(name = "ID_FAMILIA", referencedColumnName = "ID_FAMILIA", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private NvcTblFamilias nvcTblFamilias;
    @JoinColumn(name = "ID_IDIOMA", referencedColumnName = "SC_ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DcIdioma dcIdioma;
    @Transient
    private boolean activo;
    @Transient
    private String descripcion2;

    public NvcTblFamiliasIdioma() {
    }

    public NvcTblFamiliasIdioma(NvcTblFamiliasIdiomaPK nvcTblFamiliasIdiomaPK) {
        this.nvcTblFamiliasIdiomaPK = nvcTblFamiliasIdiomaPK;
    }

    public NvcTblFamiliasIdioma(BigInteger idFamilia, String idIdioma) {
        this.nvcTblFamiliasIdiomaPK = new NvcTblFamiliasIdiomaPK(idFamilia, idIdioma);
    }

    public NvcTblFamiliasIdiomaPK getNvcTblFamiliasIdiomaPK() {
        return nvcTblFamiliasIdiomaPK;
    }

    public void setNvcTblFamiliasIdiomaPK(NvcTblFamiliasIdiomaPK nvcTblFamiliasIdiomaPK) {
        this.nvcTblFamiliasIdiomaPK = nvcTblFamiliasIdiomaPK;
    }

    public String getNombreFamiliaIdioma() {
        return nombreFamiliaIdioma;
    }

    public void setNombreFamiliaIdioma(String nombreFamiliaIdioma) {
        this.nombreFamiliaIdioma = nombreFamiliaIdioma;
    }

    public NvcTblFamilias getNvcTblFamilias() {
        return nvcTblFamilias;
    }

    public void setNvcTblFamilias(NvcTblFamilias nvcTblFamilias) {
        this.nvcTblFamilias = nvcTblFamilias;
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
        hash += (nvcTblFamiliasIdiomaPK != null ? nvcTblFamiliasIdiomaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NvcTblFamiliasIdioma)) {
            return false;
        }
        NvcTblFamiliasIdioma other = (NvcTblFamiliasIdioma) object;
        if ((this.nvcTblFamiliasIdiomaPK == null && other.nvcTblFamiliasIdiomaPK != null) || (this.nvcTblFamiliasIdiomaPK != null && !this.nvcTblFamiliasIdiomaPK.equals(other.nvcTblFamiliasIdiomaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.NvcTblFamiliasIdioma[ nvcTblFamiliasIdiomaPK=" + nvcTblFamiliasIdiomaPK + " ]";
    }

    /**
     * @return the activo
     */
    public boolean isActivo() {
        if (nvcTblFamilias.getNivel().intValue() != 3) {
            return true;
        }
        return false;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    /**
     * @return the descripcion2
     */
    public String getDescripcion2() {
//        if (nvcTblFamilias.getNivel().intValue() == 3) {
////     return   String.format("%" + 10+ "s", " v");
//            return "--------------";
//
//        } else if (nvcTblFamilias.getNivel().intValue() == 2) {
//            return "------- &nbsp;";
//        } else {
//            return "- ";
//        }
        return "";
    }

    /**
     * @param descripcion2 the descripcion2 to set
     */
    public void setDescripcion2(String descripcion2) {
        this.descripcion2 = descripcion2;
    }

}
