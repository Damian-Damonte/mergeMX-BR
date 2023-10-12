package com.metalsa.contralor.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Temporal;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Oscar del Angel
 */
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
            name = "CentroCostoProceso.findCostsCenterProcess",
            procedureName = "NVC_PKG_PROCESOS_SPX.find_costs_center_process",
            resultClasses = CentroCostoProceso.class,
            parameters = {
                @StoredProcedureParameter(name = "p_id_uen", type = Integer.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_id_ccs", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_id_idioma", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_cursor_out", type = Void.class, mode = ParameterMode.REF_CURSOR)
            }
    )
})
@Getter
@Setter
@Entity(name = "CentroCostoProcesos")
@EqualsAndHashCode(of = "rownum")
public class CentroCostoProcesos implements Serializable {

    @Id
    @Column(name = "id")
    private Long rownum;

    @Column(name = "id_cc")
    private Long idCC;

    @Column(name = "codigo_cc")
    private String codigoCC;

    @Column(name = "nombre_cc")
    private String nombreCC;

    @Column(name = "id_proceso")
    private Integer idProceso;

    @Column(name = "id_proceso_uen")
    private Integer idProcesoUen;

    @Column(name = "nombre_proceso")
    private String nombreProceso;

    @Column(name = "clave_proceso")
    private String claveProceso;

    @Column(name = "id_responsable_cc")
    private String idResponsableCC;

    @Column(name = "nombre_responsable_cc")
    private String nombreResponsableCC;

    @Column(name = "id_responsable_grupo")
    private String idResponsableGrupo;

    @Column(name = "responsable_grupo")
    private String responsableGrupo;

    @Column(name = "id_responsable_grupo_anterior")
    private String idResponsableGrupoAnterior;

    @Column(name = "responsable_grupo_anterior")
    private String nombreResponsableGrupoAnterior;

    @Column(name = "nombre_grupo")
    private String nombreGrupo;

    @Column(name = "id_usuario_cc")
    private String idUsuarioCC;

    @Column(name = "nombre_usuario_cc")
    private String nombreUsuarioCC;

    @Column(name = "tipo_de_relacion")
    private String relacion;

    @Column(name = "nivel_aprobacion")
    private String nivelAprobacion;

    @Column(name = "id_usuario_actualizacion")
    private String idUsuarioActualizacion;

    @Column(name = "fecha_actualizacion")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaActualizacion;

    @Column(name = "usuario_actualizacion")
    private String usuarioActualizacion;

    @Column(name = "fecha_ultima_actualizacion")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaUltimaActualizacion;

    @Column(name = "usuario_ultima_actualizacion")
    private String usuarioUltimaActualizacion;

    @Column(name = "fecha_actualizacion_grupo")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaActualizacionGrupo;

    @Column(name = "usuario_actualizacion_grupo")
    private String usuarioActualizacionGrupo;

    
    public Date getFechaActualizacion() {
        return fechaActualizacion == null ? null : new Date(fechaActualizacion.getTime());
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion == null ? null : new Date(fechaActualizacion.getTime());
    }

    public Date getFechaUltimaActualizacion() {
        return fechaUltimaActualizacion == null ? null : new Date(fechaUltimaActualizacion.getTime());
    }

    public void setFechaUltimaActualizacion(Date fechaUltimaActualizacion) {
        this.fechaUltimaActualizacion = fechaUltimaActualizacion == null ? null : new Date(fechaUltimaActualizacion.getTime());
    }

    public Date getFechaActualizacionGrupo() {
        return fechaActualizacionGrupo == null ? null : new Date(fechaActualizacionGrupo.getTime());
    }

    public void setFechaActualizacionGrupo(Date fechaActualizacionGrupo) {
        this.fechaActualizacionGrupo = fechaActualizacionGrupo == null ? null : new Date(fechaActualizacionGrupo.getTime());
    }

}
