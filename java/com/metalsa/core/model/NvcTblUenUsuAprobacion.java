package com.metalsa.core.model;

import com.metalsa.requisicion.model.NvcTblProcesosAprobacion;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.hibernate.type.StringType;

/**
 *
 * @author APOMR10051
 */
@Entity
@Table(name = "NVC_TBL_UEN_USU_APROBACION")
@XmlRootElement
@SqlResultSetMappings({
    @SqlResultSetMapping(
            name = "usuAprobMapping",
            classes = {
                @ConstructorResult(
                        targetClass = NvcTblUenUsuAprobacion.class,
                        columns = {
                            @ColumnResult(name = "ID_USUARIO", type = StringType.class),
                            @ColumnResult(name = "NOMBRE_USUARIO", type = StringType.class),
                            @ColumnResult(name = "DELEGADO", type = Integer.class)
                        }
                )
            }
    )
})
@NamedQueries({
    @NamedQuery(name = "NvcTblUenUsuAprobacion.findAll", query = "SELECT n FROM NvcTblUenUsuAprobacion n"),
    @NamedQuery(name = "NvcTblUenUsuAprobacion.findByUenProceso",
            query = "SELECT n FROM NvcTblUenUsuAprobacion n "
            + "WHERE n.uen.organizationId = ?1 "
            + "AND n.procesoAprobacion.idProcesoAprobacion = ?2 AND n.delegado = ?3")
})
@NamedNativeQueries({
        @NamedNativeQuery(
        name = "NvcTblUenUsuAprobacion.getByUenProceso",
        resultClass = NvcTblUenUsuAprobacion.class,
        resultSetMapping = "usuAprobMapping",
        query = "select ua.id_usuario, u.nombre_usuario, ua.delegado "
        + "from nvc_tbl_uen_usu_aprobacion ua join usuario u on ua.id_usuario = u.id_usuario "
        + "where ua.id_uen = :idUen and ua.id_proceso_aprobacion = :idProceso and ua.delegado = :isDelegado"
    )
})
public class NvcTblUenUsuAprobacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_UEN_USU_APROBACION")
    private Integer idUenUsuAprobacion;

    @Column(name = "ID_UEN")
    private Long idUen;

    @Column(name = "ID_USUARIO")
    private String idUsuario;

    @Column(name = "ID_PROCESO_APROBACION")
    private Integer idProcesoAprobacion;

    @Column(name = "DELEGADO")
    private Integer delegado;
    
    @JoinColumn(name = "ID_UEN", referencedColumnName = "ORGANIZATION_ID",
        insertable = false,
        updatable = false
    )
    @ManyToOne(optional = false)
    private OaUens uen;
    
    @JoinColumn(
            name = "ID_PROCESO_APROBACION",
            referencedColumnName = "ID_PROCESO_APROBACION",
            insertable = false,
            updatable = false
    )
    @ManyToOne(optional = false)
    private NvcTblProcesosAprobacion procesoAprobacion;

    @Transient
    private String nombreUsuario;

    public NvcTblUenUsuAprobacion(String idUsuario, String nombreUsuario, Integer delegado) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.delegado = delegado;
    }

    public Integer getIdUenUsuAprobacion() {
        return idUenUsuAprobacion;
    }

    public void setIdUenUsuAprobacion(Integer idUenUsuAprobacion) {
        this.idUenUsuAprobacion = idUenUsuAprobacion;
    }

    public Long getIdUen() {
        return idUen;
    }

    public void setIdUen(Long idUen) {
        this.idUen = idUen;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdProcesoAprobacion() {
        return idProcesoAprobacion;
    }

    public void setIdProcesoAprobacion(Integer idProcesoAprobacion) {
        this.idProcesoAprobacion = idProcesoAprobacion;
    }

    public Integer getDelegado() {
        return delegado;
    }

    public void setDelegado(Integer delegado) {
        this.delegado = delegado;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
    public OaUens getUen() {
        return this.uen;
    }

    public NvcTblProcesosAprobacion getProcesoAprobacion() {
        return this.procesoAprobacion;
    }
}
