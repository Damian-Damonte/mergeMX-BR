package com.metalsa.requisicion.model;

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
@Table(name = "DCP_CATEGORIAS_IDIOMA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DcpCategoriasIdioma.findAll", query = "SELECT d FROM DcpCategoriasIdioma d")
    ,
    @NamedQuery(name = "DcpCategoriasIdioma.findByIdCat", query = "SELECT d FROM DcpCategoriasIdioma d WHERE d.dcpCategoriasIdiomaPK.idCat = :idCat")
    ,
    @NamedQuery(name = "DcpCategoriasIdioma.findByScId", query = "SELECT d FROM DcpCategoriasIdioma d WHERE d.dcpCategoriasIdiomaPK.scId = :scId")
    ,
    @NamedQuery(name = "DcpCategoriasIdioma.findByDescripcion", query = "SELECT d FROM DcpCategoriasIdioma d WHERE d.descripcion = :descripcion")})
public class DcpCategoriasIdioma implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DcpCategoriasIdiomaPK dcpCategoriasIdiomaPK;
    @Size(max = 50)
    @Column(name = "DESCRIPCION", length = 50)
    private String descripcion;
    @JoinColumn(name = "SC_ID", referencedColumnName = "SC_ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DcIdioma dcIdioma;
    @JoinColumn(name = "ID_CAT", referencedColumnName = "ID_CAT", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DcpCategorias dcpCategorias;

    public DcpCategoriasIdioma() {
    }

    public DcpCategoriasIdioma(DcpCategoriasIdiomaPK dcpCategoriasIdiomaPK) {
        this.dcpCategoriasIdiomaPK = dcpCategoriasIdiomaPK;
    }

    public DcpCategoriasIdioma(BigInteger idCat, String scId) {
        this.dcpCategoriasIdiomaPK = new DcpCategoriasIdiomaPK(idCat, scId);
    }

    public DcpCategoriasIdiomaPK getDcpCategoriasIdiomaPK() {
        return dcpCategoriasIdiomaPK;
    }

    public void setDcpCategoriasIdiomaPK(DcpCategoriasIdiomaPK dcpCategoriasIdiomaPK) {
        this.dcpCategoriasIdiomaPK = dcpCategoriasIdiomaPK;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public DcIdioma getDcIdioma() {
        return dcIdioma;
    }

    public void setDcIdioma(DcIdioma dcIdioma) {
        this.dcIdioma = dcIdioma;
    }

    public DcpCategorias getDcpCategorias() {
        return dcpCategorias;
    }

    public void setDcpCategorias(DcpCategorias dcpCategorias) {
        this.dcpCategorias = dcpCategorias;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dcpCategoriasIdiomaPK != null ? dcpCategoriasIdiomaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DcpCategoriasIdioma)) {
            return false;
        }
        DcpCategoriasIdioma other = (DcpCategoriasIdioma) object;
        if ((this.dcpCategoriasIdiomaPK == null && other.dcpCategoriasIdiomaPK != null) || (this.dcpCategoriasIdiomaPK != null && !this.dcpCategoriasIdiomaPK.equals(other.dcpCategoriasIdiomaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.admin.DcpCategoriasIdioma[ dcpCategoriasIdiomaPK=" + dcpCategoriasIdiomaPK + " ]";
    }

}
