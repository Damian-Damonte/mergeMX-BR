package com.metalsa.requisicion.model;

import com.metalsa.aprobacion.model.Usuario;
import com.metalsa.core.model.Rol;
import com.metalsa.requisicion.pojo.ComprasObject;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * @author APOJA5585
 */
@Entity
@Table(name = "NVC_TBL_MENSAJES_REQUI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NvcTblMensajesRequi.findAll", query = "SELECT n FROM NvcTblMensajesRequi n")
    ,
    @NamedQuery(name = "NvcTblMensajesRequi.findByFechaCreacion", query = "SELECT n FROM NvcTblMensajesRequi n WHERE n.fechaCreacion = :fechaCreacion")
    ,
    @NamedQuery(name = "NvcTblMensajesRequi.findByMensaje", query = "SELECT n FROM NvcTblMensajesRequi n WHERE n.mensaje = :mensaje")
    ,
    @NamedQuery(name = "NvcTblMensajesRequi.findByIdMsjReq", query = "SELECT n FROM NvcTblMensajesRequi n WHERE n.idMsjReq = :idMsjReq")
    ,
    @NamedQuery(name = "NvcTblMensajesRequi.findByLeido", query = "SELECT n FROM NvcTblMensajesRequi n WHERE n.leido = :leido")

})
public class NvcTblMensajesRequi implements Serializable, ComprasObject {

    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario idUsuario;
    @JoinColumn(name = "ID_ROL", referencedColumnName = "ID_ROL")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Rol idRol;
    @JoinColumn(name = "ID_ROL_DESTINATARIO", referencedColumnName = "ID_ROL")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Rol idRolDestinatario;
    @JoinColumn(name = "ID_REQUISICION", referencedColumnName = "ID_REQUISICION")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Requisicion idRequisicion;
    private static final long serialVersionUID = 1L;
    //@Basic(optional = false)
    //@NotNull
    @Column(name = "FECHA_CREACION", insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "MENSAJE")
    private String mensaje;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqMensajeRequi")
    @SequenceGenerator(name = "seqMensajeRequi", sequenceName = "SEQ_MSJ_REQUI", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_MSJ_REQ")
    private Integer idMsjReq;
    @Column(name = "LEIDO")
    private Character leido;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMsjRfq")
    private Collection<NvcTblMsjProvInter> nvcTblMsjProvInterCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMsjReq")
    private Collection<NvcTblMensajeProvExt> nvcTblMensajeProvExtCollection;

    public NvcTblMensajesRequi() {
    }

    public NvcTblMensajesRequi(Integer idMsjReq) {
        this.idMsjReq = idMsjReq;
    }

    public NvcTblMensajesRequi(Integer idMsjReq, Date fechaCreacion, String mensaje) {
        this.idMsjReq = idMsjReq;
        this.fechaCreacion = fechaCreacion;
        this.mensaje = mensaje;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Integer getIdMsjReq() {
        return idMsjReq;
    }

    public void setIdMsjReq(Integer idMsjReq) {
        this.idMsjReq = idMsjReq;
    }

    public Character getLeido() {
        return leido;
    }

    public void setLeido(Character leido) {
        this.leido = leido;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Rol getIdRol() {
        return idRol;
    }

    public void setIdRol(Rol idRol) {
        this.idRol = idRol;
    }

    public Rol getIdRolDestinatario() {
        return idRolDestinatario;
    }

    public void setIdRolDestinatario(Rol idRolDestinatario) {
        this.idRolDestinatario = idRolDestinatario;
    }

    public Requisicion getIdRequisicion() {
        return idRequisicion;
    }

    public void setIdRequisicion(Requisicion idRequisicion) {
        this.idRequisicion = idRequisicion;
    }

    @XmlTransient
    public Collection<NvcTblMsjProvInter> getNvcTblMsjProvInterCollection() {
        return nvcTblMsjProvInterCollection;
    }

    public void setNvcTblMsjProvInterCollection(Collection<NvcTblMsjProvInter> nvcTblMsjProvInterCollection) {
        this.nvcTblMsjProvInterCollection = nvcTblMsjProvInterCollection;
    }

    @XmlTransient
    public Collection<NvcTblMensajeProvExt> getNvcTblMensajeProvExtCollection() {
        return nvcTblMensajeProvExtCollection;
    }

    public void setNvcTblMensajeProvExtCollection(Collection<NvcTblMensajeProvExt> nvcTblMensajeProvExtCollection) {
        this.nvcTblMensajeProvExtCollection = nvcTblMensajeProvExtCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMsjReq != null ? idMsjReq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NvcTblMensajesRequi)) {
            return false;
        }
        NvcTblMensajesRequi other = (NvcTblMensajesRequi) object;
        if ((this.idMsjReq == null && other.idMsjReq != null) || (this.idMsjReq != null && !this.idMsjReq.equals(other.idMsjReq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.NvcTblMensajesRequi[ idMsjReq=" + idMsjReq + " ]";
    }

    @Override
    public String objectToString() {
        StringBuilder toString = new StringBuilder();
        toString.append(this.toString());
        toString.append("\nidMsjReq: ");
        toString.append(idMsjReq);
        toString.append("\nleido: ");
        toString.append(leido);
        toString.append("\nmensaje: ");
        toString.append(mensaje);
        toString.append("\nfechaCreacion: ");
        toString.append(fechaCreacion);
        toString.append("\nidUsuario: ");
        toString.append(idUsuario);
        toString.append("\nidRol: ");
        toString.append(idRol);
        toString.append("\nidRolDestinatario: ");
        toString.append(idRolDestinatario);
        toString.append("\nidRequisicion: ");
        toString.append(idRequisicion);
        return toString.toString();
    }

}
