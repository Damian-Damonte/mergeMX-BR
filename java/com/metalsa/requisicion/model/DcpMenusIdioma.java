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
@Table(name = "DCP_MENUS_IDIOMA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DcpMenusIdioma.findAll", query = "SELECT d FROM DcpMenusIdioma d")
    ,
    @NamedQuery(name = "DcpMenusIdioma.findByIdMenu", query = "SELECT d FROM DcpMenusIdioma d WHERE d.dcpMenusIdiomaPK.idMenu = :idMenu")
    ,
    @NamedQuery(name = "DcpMenusIdioma.findByScId", query = "SELECT d FROM DcpMenusIdioma d WHERE d.dcpMenusIdiomaPK.scId = :scId")
    ,
    @NamedQuery(name = "DcpMenusIdioma.findByDescripcion", query = "SELECT d FROM DcpMenusIdioma d WHERE d.descripcion = :descripcion")})
public class DcpMenusIdioma implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DcpMenusIdiomaPK dcpMenusIdiomaPK;
    @Size(max = 50)
    @Column(name = "DESCRIPCION", length = 50)
    private String descripcion;
    @JoinColumn(name = "SC_ID", referencedColumnName = "SC_ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DcIdioma dcIdioma;
    @JoinColumn(name = "ID_MENU", referencedColumnName = "ID_MENU", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DcpMenus dcpMenus;

    public DcpMenusIdioma() {
    }

    public DcpMenusIdioma(DcpMenusIdiomaPK dcpMenusIdiomaPK) {
        this.dcpMenusIdiomaPK = dcpMenusIdiomaPK;
    }

    public DcpMenusIdioma(BigInteger idMenu, String scId) {
        this.dcpMenusIdiomaPK = new DcpMenusIdiomaPK(idMenu, scId);
    }

    public DcpMenusIdiomaPK getDcpMenusIdiomaPK() {
        return dcpMenusIdiomaPK;
    }

    public void setDcpMenusIdiomaPK(DcpMenusIdiomaPK dcpMenusIdiomaPK) {
        this.dcpMenusIdiomaPK = dcpMenusIdiomaPK;
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

    public DcpMenus getDcpMenus() {
        return dcpMenus;
    }

    public void setDcpMenus(DcpMenus dcpMenus) {
        this.dcpMenus = dcpMenus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dcpMenusIdiomaPK != null ? dcpMenusIdiomaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DcpMenusIdioma)) {
            return false;
        }
        DcpMenusIdioma other = (DcpMenusIdioma) object;
        if ((this.dcpMenusIdiomaPK == null && other.dcpMenusIdiomaPK != null) || (this.dcpMenusIdiomaPK != null && !this.dcpMenusIdiomaPK.equals(other.dcpMenusIdiomaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.admin.DcpMenusIdioma[ dcpMenusIdiomaPK=" + dcpMenusIdiomaPK + " ]";
    }

}
