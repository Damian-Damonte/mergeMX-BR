package com.metalsa.requisicion.model;

import com.metalsa.catalogo.model.NvcTblCatalogoUen;
import com.metalsa.core.model.OaUens;
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
import org.eclipse.persistence.annotations.AdditionalCriteria;

/**
 *
 * @author APODR7218
 */
@Entity
@Table(name = "NVC_TBL_V_CATALOGO_UEN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NvcTblVCatalogoUen.findAll", query = "SELECT n FROM NvcTblVCatalogoUen n")
    ,
    @NamedQuery(name = "NvcTblVCatalogoUen.findByIdCatalogoUenV", query = "SELECT n FROM NvcTblVCatalogoUen n WHERE n.idCatalogoUenV = :idCatalogoUenV")
    ,
    @NamedQuery(name = "NvcTblVCatalogoUen.findByIdCatalogoUen", query = "SELECT n FROM NvcTblVCatalogoUen n WHERE n.idCatalogoUen = :idCatalogoUen")
    ,
    @NamedQuery(name = "NvcTblVCatalogoUen.findByIdCatalogo", query = "SELECT n FROM NvcTblVCatalogoUen n WHERE n.idCatalogo = :idCatalogo and n.idUen.organizationId = :idUen ")
    ,
    @NamedQuery(name = "NvcTblVCatalogoUen.findByIdUen", query = "SELECT n FROM NvcTblVCatalogoUen n WHERE n.idUen = :idUen")
    ,
    @NamedQuery(name = "NvcTblVCatalogoUen.findByFechaCreacion", query = "SELECT n FROM NvcTblVCatalogoUen n WHERE n.fechaCreacion = :fechaCreacion")
    ,
    @NamedQuery(name = "NvcTblVCatalogoUen.findByUsuarioCreacion", query = "SELECT n FROM NvcTblVCatalogoUen n WHERE n.usuarioCreacion = :usuarioCreacion")})
@AdditionalCriteria("this.activo = 1")
public class NvcTblVCatalogoUen implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CATALOGO_UEN_V")
    @SequenceGenerator(name = "CATALOGO_UEN_V_SEQ_GEN", sequenceName = "CATALOGO_UEN_V_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "CATALOGO_UEN_V_SEQ_GEN")
    private Integer idCatalogoUenV;
    @JoinColumn(name = "ID_CATALOGO_V", referencedColumnName = "ID_CATALOGO_V")
    @ManyToOne(optional = false)
    private NvcTblVCatalogo idCatalogoV;
    @Column(name = "ACTIVO")
    private Integer activo;
    ///// ORIGINAL
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CATALOGO_UEN")
    private Integer idCatalogoUen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CATALOGO")
    private Integer idCatalogo;
    @JoinColumn(name = "ID_UEN", referencedColumnName = "ORGANIZATION_ID")
    @ManyToOne(optional = false)
    private OaUens idUen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "USUARIO_CREACION")
    private String usuarioCreacion;
    @Column(name = "TIPO_RECIBO")
    private Integer tipoRecibo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCatalogoUenV", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<NvcTblVCatalogoItem> nvcTblVCatalogoItemCollection;
	
	// <R17736>
    @Column(name = "OC_MANUAL")
    private String ocManual;
    // <R17736/>

    // <CAT_VAR>       
    @Column(name = "ID_COMPRADOR")
    private String idCompradorUen;
    // <CAT_VAR>

    public NvcTblVCatalogoUen() {
    }

    public NvcTblVCatalogoUen(NvcTblCatalogoUen uen) {
        if (uen != null) {
            Calendar c1 = Calendar.getInstance();
            this.idCatalogoUenV = null;
            this.idCatalogoUen = uen.getIdCatalogoUen() != null ? uen.getIdCatalogoUen() : null;
            this.idCatalogo = uen.getIdCatalogo() != null ? uen.getIdCatalogo() : null;
            this.idUen = uen.getUen() != null ? uen.getUen() : null;
            this.usuarioCreacion = uen.getUsuarioActualizacion() != null ? uen.getUsuarioActualizacion() : null;
            this.fechaCreacion = c1.getTime();
            this.activo = 1;
            this.idCompradorUen=uen.getIdComprador(); // <CAT_VAR>
        }
    }

    public NvcTblVCatalogoUen(Integer idCatalogoUenV) {
        this.idCatalogoUenV = idCatalogoUenV;
    }

    public NvcTblVCatalogoUen(Integer idCatalogoUenV, Integer idCatalogoUen, Integer idCatalogo, OaUens idUen, Date fechaCreacion, String usuarioCreacion) {
        this.idCatalogoUenV = idCatalogoUenV;
        this.idCatalogoUen = idCatalogoUen;
        this.idCatalogo = idCatalogo;
        this.idUen = idUen;
        this.fechaCreacion = fechaCreacion;
        this.usuarioCreacion = usuarioCreacion;
    }

    public Integer getIdCatalogoUenV() {
        return idCatalogoUenV;
    }

    public void setIdCatalogoUenV(Integer idCatalogoUenV) {
        this.idCatalogoUenV = idCatalogoUenV;
    }

    public Integer getIdCatalogoUen() {
        return idCatalogoUen;
    }

    public void setIdCatalogoUen(Integer idCatalogoUen) {
        this.idCatalogoUen = idCatalogoUen;
    }

    public Integer getIdCatalogo() {
        return idCatalogo;
    }

    public void setIdCatalogo(Integer idCatalogo) {
        this.idCatalogo = idCatalogo;
    }

    public OaUens getIdUen() {
        return idUen;
    }

    public void setIdUen(OaUens idUen) {
        this.idUen = idUen;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    @XmlTransient
    public List<NvcTblVCatalogoItem> getNvcTblVCatalogoItemCollection() {
        if (nvcTblVCatalogoItemCollection == null) {
            setNvcTblVCatalogoItemCollection(new ArrayList<NvcTblVCatalogoItem>());
        }
        return nvcTblVCatalogoItemCollection;
    }

    public void setNvcTblVCatalogoItemCollection(List<NvcTblVCatalogoItem> nvcTblVCatalogoItemCollection) {
        this.nvcTblVCatalogoItemCollection = nvcTblVCatalogoItemCollection;
    }

    public NvcTblVCatalogo getIdCatalogoV() {
        return idCatalogoV;
    }

    public void setIdCatalogoV(NvcTblVCatalogo idCatalogoV) {
        this.idCatalogoV = idCatalogoV;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public Integer getTipoRecibo() {
        return tipoRecibo;
    }

    public void setTipoRecibo(Integer tipoRecibo) {
        this.tipoRecibo = tipoRecibo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCatalogoUenV != null ? idCatalogoUenV.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NvcTblVCatalogoUen)) {
            return false;
        }
        NvcTblVCatalogoUen other = (NvcTblVCatalogoUen) object;
        if ((this.idCatalogoUenV == null && other.idCatalogoUenV != null) || (this.idCatalogoUenV != null && !this.idCatalogoUenV.equals(other.idCatalogoUenV))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.spx.entities.NvcTblVCatalogoUen[ idCatalogoUenV=" + idCatalogoUenV + " ]";
    }
    
    // <R17736>
    public String getOcManual() {
        return ocManual;
    }

    public void setOcManual(String ocManual) {
        this.ocManual = ocManual;
    }
    // <R17736>

    // <CAT_VAR>
    public String getIdCompradorUen() {
        return idCompradorUen;
    }

    public void setIdCompradorUen(String idCompradorUen) {
        this.idCompradorUen = idCompradorUen;
    }
    // <CAT_VAR>

}
