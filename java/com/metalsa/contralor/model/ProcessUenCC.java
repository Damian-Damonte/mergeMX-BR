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
//import lombok.Getter;
//import lombok.Setter;

/**
 *
 * @author Oscar del Angel
 */
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
            name = "ProcessUenCC.findAllProcessUenCC",
            procedureName = "nvc_pkg_procesos_spx.find_all_process_uen_cc",
            resultClasses = ProcessUenCC.class,
            parameters = {
                @StoredProcedureParameter(name = "p_id_uen", type = Integer.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_idioma", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_cursor_out", type = void.class, mode = ParameterMode.REF_CURSOR)
            }
    ),
    @NamedStoredProcedureQuery(
            name = "ProcessUenCC.addUpdateProcessUenCc",
            procedureName = "nvc_pkg_procesos_spx.add_update_process_uen_cc",
            parameters = {
                @StoredProcedureParameter(name = "p_id_uen", type = Integer.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_id_user", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_id_process_uen", type = Integer.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_id_ccs", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_out_result", type = String.class, mode = ParameterMode.OUT)
            }
    ),
    @NamedStoredProcedureQuery(
        name = "ProcessUenCC.getResponsiblesByLevel",
        procedureName = "nvc_pkg_procesos_spx.find_responsibles_by_level",
        resultClasses = ResponsableProceso.class,
        parameters = {
            @StoredProcedureParameter(name = "p_id_uen", type = Integer.class, mode = ParameterMode.IN),
            @StoredProcedureParameter(name = "p_level", type = Integer.class, mode = ParameterMode.IN),
            @StoredProcedureParameter(name = "p_cursor_out", type = void.class, mode = ParameterMode.REF_CURSOR)
        }
    )
})

//@Getter
//@Setter
@Entity
public class ProcessUenCC implements Serializable {

    @Id
    @Column(name = "id_cc")
    private Integer idCC;

    @Column(name = "id_proceso_uen")
    private Integer idProcesoUen;

    @Column(name = "id_proceso_uen_cc")
    private Integer idProcesoUenCC;

    @Column(name = "codigo_cc")
    private String codigoCC;

    @Column(name = "nombre_cc")
    private String nombreCC;

    @Column(name = "nombre_proceso")
    private String nombreProceso;

    @Column(name = "usuario_actualizacion")
    private String usuarioActualizacion;

    @Column(name = "id_usuario_actualizacion")
    private String idUsuarioActualizacion;

    @Column(name = "fecha_actualizacion")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaActualizacion;

    @Column(name = "fecha_creacion")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaCreacion;

    public Integer getIdCC() {
        return idCC;
    }

    public void setIdCC(Integer idCC) {
        this.idCC = idCC;
    }

    public Integer getIdProcesoUen() {
        return idProcesoUen;
    }

    public void setIdProcesoUen(Integer idProcesoUen) {
        this.idProcesoUen = idProcesoUen;
    }

    public Integer getIdProcesoUenCC() {
        return idProcesoUenCC;
    }

    public void setIdProcesoUenCC(Integer idProcesoUenCC) {
        this.idProcesoUenCC = idProcesoUenCC;
    }

    public String getCodigoCC() {
        return codigoCC;
    }

    public void setCodigoCC(String codigoCC) {
        this.codigoCC = codigoCC;
    }

    public String getNombreCC() {
        return nombreCC;
    }

    public void setNombreCC(String nombreCC) {
        this.nombreCC = nombreCC;
    }

    public String getNombreProceso() {
        return nombreProceso;
    }

    public void setNombreProceso(String nombreProceso) {
        this.nombreProceso = nombreProceso;
    }

    public String getUsuarioActualizacion() {
        return usuarioActualizacion;
    }

    public void setUsuarioActualizacion(String usuarioActualizacion) {
        this.usuarioActualizacion = usuarioActualizacion;
    }

    public String getIdUsuarioActualizacion() {
        return idUsuarioActualizacion;
    }

    public void setIdUsuarioActualizacion(String idUsuarioActualizacion) {
        this.idUsuarioActualizacion = idUsuarioActualizacion;
    }

    public Date getFechaActualizacion() {
        return this.fechaActualizacion == null ? null : new Date(this.fechaActualizacion.getTime());
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion == null ? null : new Date(fechaActualizacion.getTime());
    }

    public Date getFechaCreacion() {
        return this.fechaCreacion == null ? null : new Date(this.fechaCreacion.getTime());
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion == null ? null : new Date(fechaCreacion.getTime());
    }
}
