package com.metalsa.requisicion.model;

import com.metalsa.catalogo.model.NvcTblCatalogo;
import com.metalsa.core.model.DcEstatus;
import com.metalsa.core.model.NvcTblOaProveedoresH;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
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
 * @author APODR7218
 */
@Entity
@Table(name = "NVC_TBL_V_CATALOGO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NvcTblVCatalogo.findAll", query = "SELECT n FROM NvcTblVCatalogo n")
    ,
    @NamedQuery(name = "NvcTblVCatalogo.findByIdCatalogoV", query = "SELECT n FROM NvcTblVCatalogo n WHERE n.idCatalogoV = :idCatalogoV")
    ,
    @NamedQuery(name = "NvcTblVCatalogo.findByIdCatalogo", query = "SELECT n FROM NvcTblVCatalogo n WHERE n.idCatalogo = :idCatalogo")
    ,
    @NamedQuery(name = "NvcTblVCatalogo.findByIdCatalogoIdUen",
            query = "SELECT n FROM NvcTblVCatalogo n "
            + " JOIN n.nvcTblVCatalogoUenCollection u "
            + "WHERE n.idCatalogo.idCatalogo = :idCatalogo "
            + " AND u.idUen.organizationId = :idUen ORDER BY n.fechaCreacion ASC")
    ,
    @NamedQuery(name = "NvcTblVCatalogo.findByIdProveedor", query = "SELECT n FROM NvcTblVCatalogo n WHERE n.idProveedor = :idProveedor")
    ,
    @NamedQuery(name = "NvcTblVCatalogo.findByNombreCatalogo", query = "SELECT n FROM NvcTblVCatalogo n WHERE n.nombreCatalogo = :nombreCatalogo")
    ,
    @NamedQuery(name = "NvcTblVCatalogo.findByFechaInicioVigencia", query = "SELECT n FROM NvcTblVCatalogo n WHERE n.fechaInicioVigencia = :fechaInicioVigencia")
    ,
    @NamedQuery(name = "NvcTblVCatalogo.findByFechaFinVigencia", query = "SELECT n FROM NvcTblVCatalogo n WHERE n.fechaFinVigencia = :fechaFinVigencia")
    ,
    @NamedQuery(name = "NvcTblVCatalogo.findByPublicado", query = "SELECT n FROM NvcTblVCatalogo n WHERE n.publicado = :publicado")
    ,
    @NamedQuery(name = "NvcTblVCatalogo.findByFechaCreacion", query = "SELECT n FROM NvcTblVCatalogo n WHERE n.fechaCreacion = :fechaCreacion")
    ,
    @NamedQuery(name = "NvcTblVCatalogo.findByUsuarioCreacion", query = "SELECT n FROM NvcTblVCatalogo n WHERE n.usuarioCreacion = :usuarioCreacion")})
public class NvcTblVCatalogo implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
//    @Basic(optional = false)
//    @NotNull
    @Column(name = "ID_CATALOGO_V")
    @SequenceGenerator(name = "CATALOGO_V_SEQ_GEN", sequenceName = "CATALOGO_V_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "CATALOGO_V_SEQ_GEN")
    private Integer idCatalogoV;
    @JoinColumn(name = "ID_CATALOGO", referencedColumnName = "ID_CATALOGO")
    @ManyToOne(optional = false)
    private NvcTblCatalogo idCatalogo;

    @JoinColumn(name = "ID_PROVEEDOR", referencedColumnName = "ID_PROVEEDOR")
    @ManyToOne(optional = false)
    private NvcTblOaProveedoresH idProveedor;

    @Column(name = "TIPO_AVISO_TERMINACION")
    private Integer tipoAvisoTerminacion;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "NOMBRE_CATALOGO")
    private String nombreCatalogo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INICIO_VIGENCIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicioVigencia;

    @Basic(optional = false)
    @Column(name = "FECHA_FIN_VIGENCIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinVigencia;

    @JoinColumn(name = "PUBLICADO", referencedColumnName = "SC_ID")
    @ManyToOne
    private DcEstatus publicado;

    @Column(name = "ACTIVO")
    private Integer activo;

    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Column(name = "USUARIO_CREACION")
    private Integer usuarioCreacion;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCatalogoV", fetch = FetchType.EAGER)
    private List<NvcTblVCatalogoUen> nvcTblVCatalogoUenCollection;

    public NvcTblVCatalogo() {
    }

    public NvcTblVCatalogo(NvcTblCatalogo catalogo) {
        if (catalogo != null) {
            Calendar c1 = Calendar.getInstance();
            this.idCatalogoV = null;
            this.idCatalogo = catalogo;
            this.idProveedor = catalogo.getProveedor();
            this.nombreCatalogo = catalogo.getNombreCatalogo() != null ? catalogo.getNombreCatalogo() : null;
            //this.idComprador = catalogo.getIdComprador(); // <CAT_VAR>
            this.publicado = catalogo.getEstatusPublicado();
            this.fechaInicioVigencia = catalogo.getFechaInicioVigencia() != null ? catalogo.getFechaInicioVigencia() : null;
            this.fechaFinVigencia = catalogo.getFechaFinVigencia() != null ? catalogo.getFechaFinVigencia() : null;
            this.fechaCreacion = c1.getTime();
            this.activo = 1;
        }
    }

    public NvcTblVCatalogo(Integer idCatalogoV) {
        this.idCatalogoV = idCatalogoV;
    }

    public NvcTblVCatalogo(Integer idCatalogoV, NvcTblCatalogo idCatalogo, NvcTblOaProveedoresH idProveedor, String nombreCatalogo, Date fechaInicioVigencia, Date fechaFinVigencia) {
        this.idCatalogoV = idCatalogoV;
        this.idCatalogo = idCatalogo;
        this.idProveedor = idProveedor;
        this.nombreCatalogo = nombreCatalogo;
        this.fechaInicioVigencia = fechaInicioVigencia;
        this.fechaFinVigencia = fechaFinVigencia;
    }

    public Integer getIdCatalogoV() {
        return idCatalogoV;
    }

    public void setIdCatalogoV(Integer idCatalogoV) {
        this.idCatalogoV = idCatalogoV;
    }

    public NvcTblCatalogo getIdCatalogo() {
        return idCatalogo;
    }

    public void setIdCatalogo(NvcTblCatalogo idCatalogo) {
        this.idCatalogo = idCatalogo;
    }

    public NvcTblOaProveedoresH getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(NvcTblOaProveedoresH idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombreCatalogo() {
        return nombreCatalogo;
    }

    public void setNombreCatalogo(String nombreCatalogo) {
        this.nombreCatalogo = nombreCatalogo;
    }

    public Date getFechaInicioVigencia() {
        return fechaInicioVigencia;
    }

    public void setFechaInicioVigencia(Date fechaInicioVigencia) {
        this.fechaInicioVigencia = fechaInicioVigencia;
    }

    public Date getFechaFinVigencia() {
        return fechaFinVigencia;
    }

    public void setFechaFinVigencia(Date fechaFinVigencia) {
        this.fechaFinVigencia = fechaFinVigencia;
    }

    public DcEstatus getPublicado() {
        return publicado;
    }

    public void setPublicado(DcEstatus publicado) {
        this.publicado = publicado;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(Integer usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public Integer getTipoAvisoTerminacion() {
        return tipoAvisoTerminacion;
    }

    public void setTipoAvisoTerminacion(Integer tipoAvisoTerminacion) {
        this.tipoAvisoTerminacion = tipoAvisoTerminacion;
    }

    @XmlTransient
    public List<NvcTblVCatalogoUen> getNvcTblVCatalogoUenCollection() {
        if (nvcTblVCatalogoUenCollection == null) {
            setNvcTblVCatalogoUenCollection(new ArrayList<NvcTblVCatalogoUen>());
        }
        return nvcTblVCatalogoUenCollection;
    }

    public void setNvcTblVCatalogoUenCollection(List<NvcTblVCatalogoUen> nvcTblVCatalogoUenCollection) {
        this.nvcTblVCatalogoUenCollection = nvcTblVCatalogoUenCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCatalogoV != null ? idCatalogoV.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NvcTblVCatalogo)) {
            return false;
        }
        NvcTblVCatalogo other = (NvcTblVCatalogo) object;
        if ((this.idCatalogoV == null && other.idCatalogoV != null) || (this.idCatalogoV != null && !this.idCatalogoV.equals(other.idCatalogoV))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.spx.entities.NvcTblVCatalogo[ idCatalogoV=" + idCatalogoV + " ]";
    }

}
