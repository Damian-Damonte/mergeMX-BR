package com.metalsa.catalogo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.metalsa.core.model.DcEstatus;
import com.metalsa.core.model.NvcTblOaProveedoresH;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.type.StringType;

/**
 *
 * @author APOMR10051
 */
@Entity
@Table(name = "NVC_TBL_CATALOGO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NvcTblCatalogo.findAll", query = "SELECT n FROM NvcTblCatalogo n"),
    @NamedQuery(name = "NvcTblCatalogo.findByIdCatalogo", query = "SELECT n FROM NvcTblCatalogo n where n.idCatalogo = :idCatalogo and n.activo = 1")
})
@SqlResultSetMappings({
    @SqlResultSetMapping(
            name = "CatMapping",
            classes = {
                @ConstructorResult(
                        targetClass = NvcTblCatalogo.class,
                        columns = {
                            @ColumnResult(name = "ID_CATALOGO", type = Integer.class)
                            ,
                            @ColumnResult(name = "ID_PROVEEDOR", type = Integer.class)
                            ,
                            @ColumnResult(name = "NOMBRE_PROVEEDOR", type = StringType.class)
                            ,
                            @ColumnResult(name = "NOMBRE_CATALOGO", type = StringType.class)
                            ,
                            @ColumnResult(name = "FECHA_INICIO_VIGENCIA", type = Date.class)
                            ,
                            @ColumnResult(name = "FECHA_FIN_VIGENCIA", type = Date.class)
                            ,
                            @ColumnResult(name = "PUBLICADO", type = Integer.class)
                            ,
                            @ColumnResult(name = "USUARIO_CREACION", type = StringType.class)
                            ,
                            @ColumnResult(name = "USUARIO_ACTUALIZACION", type = StringType.class)
                            ,
                            @ColumnResult(name = "NOMBRE_USUARIO_ACT", type = StringType.class)
                            ,
                            @ColumnResult(name = "TIPO_AVISO_TERMINACION", type = Integer.class)
                            ,
                            @ColumnResult(name = "ID_PROV_PUNCHOUT", type = Integer.class)}
                )}
    )
})
@NamedNativeQueries({
    @NamedNativeQuery(
            name = "NvcTblCatalogo.getByIdCatalogo",
            resultClass = NvcTblCatalogo.class,
            resultSetMapping = "CatMapping",
            query = "select c.id_catalogo, c.id_proveedor, p.nombre_proveedor, c.nombre_catalogo, "
            + "c.fecha_inicio_vigencia, c.fecha_fin_vigencia, c.publicado, c.usuario_creacion, "
            + "c.usuario_actualizacion, u.nombre_usuario nombre_usuario_act, c.tipo_aviso_terminacion, "
            + "c.id_prov_punchout from nvc_tbl_catalogo c "
            + "join nvc_tbl_oa_proveedores_h p on p.id_proveedor = c.id_proveedor "
            + "join usuario u on u.id_usuario = c.usuario_actualizacion "
            + "where c.id_catalogo = :idCatalogo and c.activo = 1 "
    )
})
public class NvcTblCatalogo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CATALOGO")
    private Integer idCatalogo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "NOMBRE_CATALOGO")
    private String nombreCatalogo;
    @Column(name = "TIPO_AVISO_TERMINACION")
    private Integer tipoAvisoTerminacion;
    @Column(name = "ID_PROV_PUNCHOUT")
    private Integer idProvPunchout;
    @Column(name = "FECHA_INICIO_VIGENCIA")
    @Temporal(javax.persistence.TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy")
    private Date fechaInicioVigencia;
    @Column(name = "FECHA_FIN_VIGENCIA")
    @Temporal(javax.persistence.TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy")
    private Date fechaFinVigencia;
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FECHA_ACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @Column(name = "ACTIVO")
    private Integer activo;
    @Column(name = "ID_PROVEEDOR")
    private Integer idProveedor;
    
    @JoinColumn(
            name = "ID_PROVEEDOR",
            insertable = false,
            updatable = false
    )
    @ManyToOne()
    private NvcTblOaProveedoresH proveedor;
    
    @JoinColumn(
            name = "PUBLICADO",
            referencedColumnName = "SC_ID",
            insertable = false,
            updatable = false
    )
    @ManyToOne()
    private DcEstatus estatusPublicado;
    
    @Transient
    private String nombreProveedor;
    @Column(name = "PUBLICADO")
    private Integer publicado;
    @Size(max = 256)
    @Column(name = "USUARIO_CREACION")
    private String usuarioCreacion;
    @Size(max = 256)
    @Column(name = "USUARIO_ACTUALIZACION")
    private String usuarioActualizacion;
    @Transient
    private String nombreUsuarioAct;

    public NvcTblCatalogo() {
    }

    public NvcTblCatalogo(Integer idCatalogo) {
        this.idCatalogo = idCatalogo;
    }

    public NvcTblCatalogo(Integer idCatalogo, Integer idProveedor, String nombreProveedor,
            String nombreCatalogo, Date fechaInicioVigencia, Date fechaFinVigencia,
            Integer publicado, String usuarioCreacion, String usuarioActualizacion,
            String nombreUsuarioAct, Integer tipoAvisoTerminacion, Integer idProvPunchout) {
        this.idCatalogo = idCatalogo;
        this.idProveedor = idProveedor;
        this.nombreProveedor = nombreProveedor;
        this.nombreCatalogo = nombreCatalogo;
        this.fechaInicioVigencia = fechaInicioVigencia;
        this.fechaFinVigencia = fechaFinVigencia;
        this.publicado = publicado;
        this.usuarioCreacion = usuarioCreacion;
        this.usuarioActualizacion = usuarioActualizacion;
        this.nombreUsuarioAct = nombreUsuarioAct;
        this.tipoAvisoTerminacion = tipoAvisoTerminacion;
        this.idProvPunchout = idProvPunchout;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCatalogo != null ? idCatalogo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NvcTblCatalogo)) {
            return false;
        }
        NvcTblCatalogo other = (NvcTblCatalogo) object;
        if ((this.idCatalogo == null && other.idCatalogo != null) || (this.idCatalogo != null && !this.idCatalogo.equals(other.idCatalogo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.spx.entities.NvcTblCatalogo[ idCatalogo=" + idCatalogo + " ]";
    }

    public Integer getIdCatalogo() {
        return idCatalogo;
    }

    public void setIdCatalogo(Integer idCatalogo) {
        this.idCatalogo = idCatalogo;
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

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public String getUsuarioActualizacion() {
        return usuarioActualizacion;
    }

    public void setUsuarioActualizacion(String usuarioActualizacion) {
        this.usuarioActualizacion = usuarioActualizacion;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public Integer getPublicado() {
        return publicado;
    }

    public void setPublicado(Integer publicado) {
        this.publicado = publicado;
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

    public Integer getIdProvPunchout() {
        return idProvPunchout;
    }

    public void setIdProvPunchout(Integer idProvPunchout) {
        this.idProvPunchout = idProvPunchout;
    }

    public String getNombreUsuarioAct() {
        return nombreUsuarioAct;
    }

    public void setNombreUsuarioAct(String nombreUsuarioAct) {
        this.nombreUsuarioAct = nombreUsuarioAct;
    }
    
    public NvcTblOaProveedoresH getProveedor() {
        return this.proveedor;
    }
    
    public DcEstatus getEstatusPublicado() {
        return this.estatusPublicado;
    }
}
