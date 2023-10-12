package com.metalsa.requisicion.model;

import com.metalsa.aprobacion.model.NvcTblOrganizacionesH;
import com.metalsa.aprobacion.model.Usuario;
import com.metalsa.core.model.NvcTblOaCcH;
import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author APOJA5585
 */
@Entity
@Table(name = "CC_POR_COMPRADOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CcPorComprador.findAll", query = "SELECT c FROM CcPorComprador c")
    ,
    @NamedQuery(name = "CcPorComprador.findByIdCCUen", query = "SELECT c FROM CcPorComprador c WHERE c.ccPorCompradorPK.idCc = :idCc AND c.ccPorCompradorPK.idUen = :idUen ")
    ,
    @NamedQuery(name = "CcPorComprador.findByIdUen", query = "SELECT c FROM CcPorComprador c WHERE c.ccPorCompradorPK.idUen = :idUen")
    ,    
    @NamedQuery(name = "CcPorComprador.findByIdCc", query = "SELECT c FROM CcPorComprador c WHERE c.ccPorCompradorPK.idCc = :idCc")})

public class CcPorComprador implements Serializable {

    private static final long serialVersionUID = 1L;
    @JoinColumns({
        @JoinColumn(name = "ID_UEN", referencedColumnName = "ID_UEN", nullable = false, insertable = false, updatable = false)
        ,
        @JoinColumn(name = "ID_CC", referencedColumnName = "ID_CC", nullable = false, insertable = false, updatable = false)})
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private NvcTblOaCcH nvcTblOaCcH;
    @EmbeddedId
    protected CcPorCompradorPK ccPorCompradorPK;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false)
    private Usuario idUsuario;
    @JoinColumn(name = "ID_UEN", referencedColumnName = "ID_UEN", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private NvcTblOrganizacionesH nvcTblOrganizacionesH;

    public CcPorComprador() {
    }

    //public CcPorComprador(long idUen, long idCc, String idUsuario ){
    //    this.ccPorCompradorPK = new CcPorCompradorPK(idUen, idCc);
    //    this.idUsuario = new Usuario(idUsuario);
    //}
    public CcPorComprador(CcPorCompradorPK ccPorCompradorPK) {
        this.ccPorCompradorPK = ccPorCompradorPK;
    }

    public CcPorComprador(long idUen, long idCc) {
        this.ccPorCompradorPK = new CcPorCompradorPK(idUen, idCc);
    }

    public CcPorCompradorPK getCcPorCompradorPK() {
        return ccPorCompradorPK;
    }

    public void setCcPorCompradorPK(CcPorCompradorPK ccPorCompradorPK) {
        this.ccPorCompradorPK = ccPorCompradorPK;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public NvcTblOrganizacionesH getNvcTblOrganizacionesH() {
        return nvcTblOrganizacionesH;
    }

    public void setNvcTblOrganizacionesH(NvcTblOrganizacionesH nvcTblOrganizacionesH) {
        this.nvcTblOrganizacionesH = nvcTblOrganizacionesH;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ccPorCompradorPK != null ? ccPorCompradorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CcPorComprador)) {
            return false;
        }
        CcPorComprador other = (CcPorComprador) object;
        if ((this.ccPorCompradorPK == null && other.ccPorCompradorPK != null) || (this.ccPorCompradorPK != null && !this.ccPorCompradorPK.equals(other.ccPorCompradorPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.CcPorComprador[ ccPorCompradorPK=" + ccPorCompradorPK + " ]";
    }

    public NvcTblOaCcH getNvcTblOaCcH() {
        return nvcTblOaCcH;
    }

    public void setNvcTblOaCcH(NvcTblOaCcH nvcTblOaCcH) {
        this.nvcTblOaCcH = nvcTblOaCcH;
    }

}
