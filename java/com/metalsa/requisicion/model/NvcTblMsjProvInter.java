package com.metalsa.requisicion.model;

import com.metalsa.core.model.NvcTblOaProveedoresH;
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
@Table(name = "NVC_TBL_MSJ_PROV_INTER", catalog = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NvcTblMsjProvInter.findAll", query = "SELECT n FROM NvcTblMsjProvInter n")
    ,
    @NamedQuery(name = "NvcTblMsjProvInter.findByIdMsjProvInt", query = "SELECT n FROM NvcTblMsjProvInter n WHERE n.idMsjProvInt = :idMsjProvInt")
})
public class NvcTblMsjProvInter implements Serializable, ComprasObject {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqMensajeProvInt")
    @SequenceGenerator(name = "seqMensajeProvInt", sequenceName = "SEQ_MSJ_PROV_INT", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_MSJ_PROV_INT")
    private Integer idMsjProvInt;
    @JoinColumn(name = "ID_RFQ", referencedColumnName = "ID_RFQ")
    @ManyToOne(optional = false)
    private NvcTblRfq idRfq;
    @JoinColumn(name = "ID_PROVEEDOR", referencedColumnName = "ID_PROVEEDOR")
    @ManyToOne(optional = false)
    private NvcTblOaProveedoresH idProveedor;
    @JoinColumn(name = "ID_MSJ_RFQ", referencedColumnName = "ID_MSJ_REQ")
    @ManyToOne(optional = false)
    private NvcTblMensajesRequi idMsjRfq;

    public NvcTblMsjProvInter() {
    }

    public NvcTblMsjProvInter(Integer idMsjProvInt) {
        this.idMsjProvInt = idMsjProvInt;
    }

    public Integer getIdMsjProvInt() {
        return idMsjProvInt;
    }

    public void setIdMsjProvInt(Integer idMsjProvInt) {
        this.idMsjProvInt = idMsjProvInt;
    }

    public NvcTblRfq getIdRfq() {
        return idRfq;
    }

    public void setIdRfq(NvcTblRfq idRfq) {
        this.idRfq = idRfq;
    }

    public NvcTblOaProveedoresH getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(NvcTblOaProveedoresH idProveedor) {
        this.idProveedor = idProveedor;
    }

    public NvcTblMensajesRequi getIdMsjRfq() {
        return idMsjRfq;
    }

    public void setIdMsjRfq(NvcTblMensajesRequi idMsjRfq) {
        this.idMsjRfq = idMsjRfq;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMsjProvInt != null ? idMsjProvInt.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NvcTblMsjProvInter)) {
            return false;
        }
        NvcTblMsjProvInter other = (NvcTblMsjProvInter) object;
        if ((this.idMsjProvInt == null && other.idMsjProvInt != null) || (this.idMsjProvInt != null && !this.idMsjProvInt.equals(other.idMsjProvInt))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.cotizaciones.NvcTblMsjProvInter[ idMsjProvInt=" + idMsjProvInt + " ]";
    }

    @Override
    public String objectToString() {
        StringBuilder toString = new StringBuilder();
        toString.append(this.toString());
        toString.append("\n");
        toString.append("idMsjProvInt: ");
        toString.append(idMsjProvInt);
        toString.append("\n");
        toString.append("idRfq: ");
        toString.append((null != idRfq) ? idRfq.getIdRfq() : "NULL");
        toString.append("\n");
        toString.append("idProveedor: ");
        toString.append((null != idProveedor) ? idProveedor.getIdProveedor() : "NULL");
        toString.append("\n");
        toString.append("idMsjRfq: ");
        toString.append((null != idMsjRfq) ? idMsjRfq.getIdMsjReq() : "NULL");
        return toString.toString();
    }

}
