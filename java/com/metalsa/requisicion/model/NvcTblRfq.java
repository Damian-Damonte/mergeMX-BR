/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.requisicion.model;

import com.metalsa.aprobacion.model.NvcTblOrganizacionesH;
import com.metalsa.aprobacion.model.Usuario;
import com.metalsa.core.model.DcEstatus;
import com.metalsa.portalProveedor.model.NvcTblIncoterm;
import com.metalsa.portalProveedor.model.NvcTblRfqLinea;
import com.metalsa.portalProveedor.model.NvcTblRfqProv;
import com.metalsa.requisicion.pojo.ComprasObject;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OrderBy;
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
@Table(name = "NVC_TBL_RFQ", catalog = "")
@XmlRo
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NvcTblRfq.findAll", query = "SELECT n FROM com.metalsa.requisicion.model.NvcTblRfq n")
    ,
    @NamedQuery(name = "NvcTblRfq.findByIdRfq", query = "SELECT n FROM com.metalsa.requisicion.model.NvcTblRfq n WHERE n.idRfq = ?1")
    ,
    @NamedQuery(name = "NvcTblRfq.findByFechaCreacion", query = "SELECT n FROM com.metalsa.requisicion.model.NvcTblRfq n WHERE n.fechaCreacion = :fechaCreacion")
    ,
    @NamedQuery(name = "NvcTblRfq.findByUsuario", query = "SELECT n FROM com.metalsa.requisicion.model.NvcTblRfq n WHERE n.usuario = :usuario")
    ,
    @NamedQuery(name = "NvcTblRfq.findByPesoArchivo", query = "SELECT n FROM com.metalsa.requisicion.model.NvcTblRfq n WHERE n.pesoArchivo = :pesoArchivo")
    ,
    @NamedQuery(name = "NvcTblRfq.findByIdIncoterm", query = "SELECT n FROM com.metalsa.requisicion.model.NvcTblRfq n WHERE n.idIncoterm = :idIncoterm")
    ,
    @NamedQuery(name = "NvcTblRfq.findByInicioVigencia", query = "SELECT n FROM com.metalsa.requisicion.model.NvcTblRfq n WHERE n.inicioVigencia = :inicioVigencia")
    ,
    @NamedQuery(name = "NvcTblRfq.findByFinVigencia", query = "SELECT n FROM com.metalsa.requisicion.model.NvcTblRfq n WHERE n.finVigencia = :finVigencia")
    ,
    @NamedQuery(name = "NvcTblRfq.findByShipTo", query = "SELECT n FROM com.metalsa.requisicion.model.NvcTblRfq n WHERE n.shipTo = :shipTo")
    ,
    @NamedQuery(name = "NvcTblRfq.findByBllTo", query = "SELECT n FROM com.metalsa.requisicion.model.NvcTblRfq n WHERE n.bllTo = :bllTo")
    ,
    @NamedQuery(name = "NvcTblRfq.findByEnviadoProveedor", query = "SELECT n FROM com.metalsa.requisicion.model.NvcTblRfq n WHERE n.enviadoProveedor = :enviadoProveedor")})
public class NvcTblRfq implements Serializable, ComprasObject {

    @JoinColumn(name = "ESTATUS", referencedColumnName = "SC_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private DcEstatus estatus;
    @Column(name = "FECHA_EDICION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEdicion;
    @JoinColumn(name = "ID_USUARIO_EDICION", referencedColumnName = "ID_USUARIO")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario idUsuarioEdicion;
    @JoinColumn(name = "ID_UEN", referencedColumnName = "ID_UEN")
    @ManyToOne(fetch = FetchType.LAZY)
    private NvcTblOrganizacionesH idUen;
    @JoinColumn(name = "ID_MEDIO", referencedColumnName = "ID_MEDIO")
    @ManyToOne(fetch = FetchType.LAZY)
    private NvcTblMedio idMedio;
    @JoinColumn(name = "ID_TERM", referencedColumnName = "ID_TERM")
    @ManyToOne(fetch = FetchType.LAZY)
    private NvcTblTermTransporte idTerm;
    @JoinColumn(name = "ID_INCOTERM", referencedColumnName = "ID_INCOTERM")
    @ManyToOne(fetch = FetchType.LAZY)
    private NvcTblIncoterm idIncoterm;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRfq", fetch = FetchType.LAZY)
    private List<NvcTblRfqProv> nvcTblRfqProvList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRfq", fetch = FetchType.LAZY)
    private List<NvcTblMsjProvInter> nvcTblMsjProvInterList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqRfq")
    @SequenceGenerator(name = "seqRfq", sequenceName = "SEQ_RFQ", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_RFQ")
    private Integer idRfq;
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Size(max = 50)
    @Column(name = "USUARIO")
    private String usuario;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PESO_ARCHIVO")
    private BigDecimal pesoArchivo;
    @Column(name = "INICIO_VIGENCIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inicioVigencia;
    @Column(name = "FIN_VIGENCIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date finVigencia;
    @Size(max = 100)
    @Column(name = "SHIP_TO")
    private String shipTo;
    @Size(max = 100)
    @Column(name = "BLL_TO")
    private String bllTo;
    @Column(name = "ENVIADO_PROVEEDOR")
    private Character enviadoProveedor;
    @OrderBy("detalleDeRequisicion.detalleDeRequisicionPK.idRequisicion, detalleDeRequisicion.detalleDeRequisicionPK.idPartida ASC")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "llaveId")
    private List<NvcTblRfqLinea> nvcTblRfqLineaList;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idUsuario;

    public NvcTblRfq() {
    }

    public NvcTblRfq(Integer idRfq) {
        this.idRfq = idRfq;
    }

    public Integer getIdRfq() {
        return idRfq;
    }

    public void setIdRfq(Integer idRfq) {
        this.idRfq = idRfq;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public BigDecimal getPesoArchivo() {
        return pesoArchivo;
    }

    public void setPesoArchivo(BigDecimal pesoArchivo) {
        this.pesoArchivo = pesoArchivo;
    }

    public Date getInicioVigencia() {
        return inicioVigencia;
    }

    public void setInicioVigencia(Date inicioVigencia) {
        this.inicioVigencia = inicioVigencia;
    }

    public Date getFinVigencia() {
        return finVigencia;
    }

    public void setFinVigencia(Date finVigencia) {
        this.finVigencia = finVigencia;
    }

    public String getShipTo() {
        return shipTo;
    }

    public void setShipTo(String shipTo) {
        this.shipTo = shipTo;
    }

    public String getBllTo() {
        return bllTo;
    }

    public void setBllTo(String bllTo) {
        this.bllTo = bllTo;
    }

    public Character getEnviadoProveedor() {
        return enviadoProveedor;
    }

    public void setEnviadoProveedor(Character enviadoProveedor) {
        this.enviadoProveedor = enviadoProveedor;
    }

    @XmlTransient
    public List<NvcTblRfqLinea> getNvcTblRfqLineaList() {
        return nvcTblRfqLineaList;
    }

    public void setNvcTblRfqLineaList(List<NvcTblRfqLinea> nvcTblRfqLineaList) {
        this.nvcTblRfqLineaList = nvcTblRfqLineaList;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRfq != null ? idRfq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NvcTblRfq)) {
            return false;
        }
        NvcTblRfq other = (NvcTblRfq) object;
        if ((this.idRfq == null && other.idRfq != null) || (this.idRfq != null && !this.idRfq.equals(other.idRfq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.cotizaciones.NvcTblRfq[ idRfq=" + idRfq + " ]";
    }

    @XmlTransient
    public List<NvcTblMsjProvInter> getNvcTblMsjProvInterList() {
        return nvcTblMsjProvInterList;
    }

    public void setNvcTblMsjProvInterList(List<NvcTblMsjProvInter> nvcTblMsjProvInterList) {
        this.nvcTblMsjProvInterList = nvcTblMsjProvInterList;
    }

    @XmlTransient
    public List<NvcTblRfqProv> getNvcTblRfqProvList() {
        return nvcTblRfqProvList;
    }

    public void setNvcTblRfqProvList(List<NvcTblRfqProv> nvcTblRfqProvList) {
        this.nvcTblRfqProvList = nvcTblRfqProvList;
    }

    public NvcTblTermTransporte getIdTerm() {
        return idTerm;
    }

    public void setIdTerm(NvcTblTermTransporte idTerm) {
        this.idTerm = idTerm;
    }

    public NvcTblIncoterm getIdIncoterm() {
        return idIncoterm;
    }

    public void setIdIncoterm(NvcTblIncoterm idIncoterm) {
        this.idIncoterm = idIncoterm;
    }

    @Override
    public String objectToString() {
        StringBuilder toString = new StringBuilder();
        toString.append(this.toString());
        toString.append("\n");
        toString.append("idRfq: ");
        toString.append(idRfq);
        toString.append("\n");
        toString.append("fechaCreacion: ");
        toString.append(fechaCreacion);
        toString.append("\n");
        toString.append("usuario: ");
        toString.append(usuario);
        toString.append("\n");
        toString.append("pesoArchivo: ");
        toString.append(pesoArchivo);
        toString.append("\n");
        toString.append("idIncoterm: ");
        toString.append(idIncoterm);
        toString.append("\n");
        toString.append("inicioVigencia: ");
        toString.append(inicioVigencia);
        toString.append("\n");
        toString.append("finVigencia: ");
        toString.append(finVigencia);
        toString.append("\n");
        toString.append("shipTo: ");
        toString.append(shipTo);
        toString.append("\n");
        toString.append("bllTo: ");
        toString.append(bllTo);
        toString.append("\n");
        toString.append("enviadoProveedor: ");
        toString.append(enviadoProveedor);
        toString.append("\n");
        toString.append("idUsuario: ");
        toString.append((null != idUsuario) ? idUsuario.getId(): "NULL");
        return toString.toString();
    }

    public NvcTblMedio getIdMedio() {
        return idMedio;
    }

    public void setIdMedio(NvcTblMedio idMedio) {
        this.idMedio = idMedio;
    }

    public Date getFechaEdicion() {
        return fechaEdicion;
    }

    public void setFechaEdicion(Date fechaEdicion) {
        this.fechaEdicion = fechaEdicion;
    }

    public Usuario getIdUsuarioEdicion() {
        return idUsuarioEdicion;
    }

    public void setIdUsuarioEdicion(Usuario idUsuarioEdicion) {
        this.idUsuarioEdicion = idUsuarioEdicion;
    }

    public NvcTblOrganizacionesH getIdUen() {
        return idUen;
    }

    public void setIdUen(NvcTblOrganizacionesH idUen) {
        this.idUen = idUen;
    }

    public DcEstatus getEstatus() {
        return estatus;
    }

    public void setEstatus(DcEstatus estatus) {
        this.estatus = estatus;
    }

}
