/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.requisicion.model;

import com.metalsa.requisicion.pojo.ComprasObject;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author APOJA5585
 */
@Entity
@Table(name = "NVC_TBL_MENSAJE_PROV_EXT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NvcTblMensajeProvExt.findAll", query = "SELECT n FROM NvcTblMensajeProvExt n")
    ,
    @NamedQuery(name = "NvcTblMensajeProvExt.findByIdMsjProvExt", query = "SELECT n FROM NvcTblMensajeProvExt n WHERE n.idMsjProvExt = :idMsjProvExt")
})
public class NvcTblMensajeProvExt implements Serializable, ComprasObject {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqMensajeProvExt")
    @SequenceGenerator(name = "seqMensajeProvExt", sequenceName = "SEQ_MSJ_PROV_EXT", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_MSJ_PROV_EXT")
    private Integer idMsjProvExt;
    @JoinColumn(name = "ID_RFQ", referencedColumnName = "ID_RFQ")
    @ManyToOne(optional = false)
    private NvcTblRfq idRfq;
    @JoinColumn(name = "ID_PROV_EXT", referencedColumnName = "ID_PROVEEDOR")
    @ManyToOne(optional = false)
    private NvcTblProvTemporal idProvExt;
    @JoinColumn(name = "ID_MSJ_REQ", referencedColumnName = "ID_MSJ_REQ")
    @ManyToOne(optional = false)
    private NvcTblMensajesRequi idMsjReq;

    public NvcTblMensajeProvExt() {
    }

    public NvcTblMensajeProvExt(Integer idMsjProvExt) {
        this.idMsjProvExt = idMsjProvExt;
    }

    public Integer getIdMsjProvExt() {
        return idMsjProvExt;
    }

    public void setIdMsjProvExt(Integer idMsjProvExt) {
        this.idMsjProvExt = idMsjProvExt;
    }

    public NvcTblRfq getIdRfq() {
        return idRfq;
    }

    public void setIdRfq(NvcTblRfq idRfq) {
        this.idRfq = idRfq;
    }

    public NvcTblProvTemporal getIdProvExt() {
        return idProvExt;
    }

    public void setIdProvExt(NvcTblProvTemporal idProvExt) {
        this.idProvExt = idProvExt;
    }

    public NvcTblMensajesRequi getIdMsjReq() {
        return idMsjReq;
    }

    public void setIdMsjReq(NvcTblMensajesRequi idMsjReq) {
        this.idMsjReq = idMsjReq;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMsjProvExt != null ? idMsjProvExt.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NvcTblMensajeProvExt)) {
            return false;
        }
        NvcTblMensajeProvExt other = (NvcTblMensajeProvExt) object;
        if ((this.idMsjProvExt == null && other.idMsjProvExt != null) || (this.idMsjProvExt != null && !this.idMsjProvExt.equals(other.idMsjProvExt))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.NvcTblMensajeProvExt[ idMsjProvExt=" + idMsjProvExt + " ]";
    }

    @Override
    public String objectToString() {
        StringBuilder toString = new StringBuilder();
        toString.append(this.toString());
        toString.append("\n");
        toString.append("idMsjProvExt: ");
        toString.append(idMsjProvExt);
        toString.append("\n");
        toString.append("idRfq: ");
        toString.append((null != idRfq) ? idRfq.getIdRfq() : "NULL");
        toString.append("\n");
        toString.append("idProvExt: ");
        toString.append((null != idProvExt) ? idProvExt.getIdProveedor() : "NULL");
        toString.append("\n");
        toString.append("idMsjReq: ");
        toString.append((null != idMsjReq) ? idMsjReq.getIdMsjReq() : "NULL");
        return toString.toString();
    }
}
