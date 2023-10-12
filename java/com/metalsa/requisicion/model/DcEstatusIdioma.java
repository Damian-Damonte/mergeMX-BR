package com.metalsa.requisicion.model;

import com.metalsa.core.model.DcEstatus;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author APOPH5586
 */
@Entity
@Table(name = "DC_ESTATUS_IDIOMA", catalog = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DcEstatusIdioma.findAll", query = "SELECT d FROM DcEstatusIdioma d")
    ,
    @NamedQuery(name = "DcEstatusIdioma.findByIdEstatus", query = "SELECT d FROM DcEstatusIdioma d WHERE d.dcEstatusIdiomaPK.idEstatus = :idEstatus")
    ,
    @NamedQuery(name = "DcEstatusIdioma.findByPK", query = "SELECT d FROM DcEstatusIdioma d WHERE d.dcEstatusIdiomaPK.idEstatus = :idEstatus AND d.dcEstatusIdiomaPK.idIdioma = :idIdioma")
    ,
    @NamedQuery(name = "DcEstatusIdioma.findByIdIdioma", query = "SELECT d FROM DcEstatusIdioma d WHERE d.dcEstatusIdiomaPK.idIdioma = :idIdioma")
    ,
    @NamedQuery(name = "DcEstatusIdioma.findByDescEstatus", query = "SELECT d FROM DcEstatusIdioma d WHERE d.descEstatus = :descEstatus")})
public class DcEstatusIdioma implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DcEstatusIdiomaPK dcEstatusIdiomaPK;
    @Size(max = 80)
    @Column(name = "DESC_ESTATUS", length = 80)
    private String descEstatus;
    @JoinColumn(name = "ID_IDIOMA", referencedColumnName = "SC_ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DcIdioma dcIdioma;
    @JoinColumn(name = "ID_ESTATUS", referencedColumnName = "SC_ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DcEstatus dcEstatus;

    public DcEstatusIdioma() {
    }

    public DcEstatusIdioma(DcEstatusIdiomaPK dcEstatusIdiomaPK) {
        this.dcEstatusIdiomaPK = dcEstatusIdiomaPK;
    }

    public DcEstatusIdioma(BigInteger idEstatus, String idIdioma) {
        this.dcEstatusIdiomaPK = new DcEstatusIdiomaPK(idEstatus, idIdioma);
    }

    public DcEstatusIdiomaPK getDcEstatusIdiomaPK() {
        return dcEstatusIdiomaPK;
    }

    public void setDcEstatusIdiomaPK(DcEstatusIdiomaPK dcEstatusIdiomaPK) {
        this.dcEstatusIdiomaPK = dcEstatusIdiomaPK;
    }

    public String getDescEstatus() {
        return descEstatus;
    }

    public void setDescEstatus(String descEstatus) {
        this.descEstatus = descEstatus;
    }

    public DcIdioma getDcIdioma() {
        return dcIdioma;
    }

    public void setDcIdioma(DcIdioma dcIdioma) {
        this.dcIdioma = dcIdioma;
    }

    public DcEstatus getDcEstatus() {
        return dcEstatus;
    }

    public void setDcEstatus(DcEstatus dcEstatus) {
        this.dcEstatus = dcEstatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dcEstatusIdiomaPK != null ? dcEstatusIdiomaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DcEstatusIdioma)) {
            return false;
        }
        DcEstatusIdioma other = (DcEstatusIdioma) object;
        if ((this.dcEstatusIdiomaPK == null && other.dcEstatusIdiomaPK != null) || (this.dcEstatusIdiomaPK != null && !this.dcEstatusIdiomaPK.equals(other.dcEstatusIdiomaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.admin.DcEstatusIdioma[ dcEstatusIdiomaPK=" + dcEstatusIdiomaPK + " ]";
    }

}
