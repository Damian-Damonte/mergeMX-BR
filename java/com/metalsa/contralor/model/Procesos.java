package com.metalsa.contralor.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Temporal;
import javax.persistence.Transient;
//import lombok.Getter;
//import lombok.Setter;

/**
 *
 * @author Oscar del Angel
 */
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
            name = "Procesos.findAllProcessUen",
            procedureName = "nvc_pkg_procesos_spx.find_all_process_uen",
            resultClasses = Procesos.class,
            parameters = {
                @StoredProcedureParameter(name = "p_id_uen", type = Integer.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_id_process_parent", type = Integer.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_cursor_out", type = void.class, mode = ParameterMode.REF_CURSOR)
            }
    ),
    @NamedStoredProcedureQuery(
            name = "Procesos.findAllProcessLang",
            procedureName = "nvc_pkg_procesos_spx.find_all_process_lang",
            resultSetMappings = "Procesos.ProcessLang",
            parameters = {
                @StoredProcedureParameter(name = "p_cursor_out", type = void.class, mode = ParameterMode.REF_CURSOR)
            }
    ),
    @NamedStoredProcedureQuery(
            name = "Procesos.findAllProcessUenLangLevel",
            procedureName = "nvc_pkg_procesos_spx.find_all_process_uen_lang",
            resultSetMappings = "Procesos.ProcessLang",
            parameters = {
                @StoredProcedureParameter(name = "p_id_uen", type = Integer.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_level", type = Integer.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_cursor_out", type = void.class, mode = ParameterMode.REF_CURSOR)
            }
    ),
    @NamedStoredProcedureQuery(
            name = "Procesos.removeProcess",
            procedureName = "nvc_pkg_procesos_spx.remove_process_uen",
            resultSetMappings = "Procesos.ProcessLang",
            parameters = {
                @StoredProcedureParameter(name = "p_id_uen", type = Integer.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_id_user", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_process_ids", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_out_result", type = String.class, mode = ParameterMode.OUT)
            }
    ),
    @NamedStoredProcedureQuery(
            name = "Procesos.addProcess",
            procedureName = "nvc_pkg_procesos_spx.add_update_process_uen",
            parameters = {
                @StoredProcedureParameter(name = "p_id_uen", type = Integer.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_id_proceso_uen", type = Integer.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_id_process_uen_parent", type = Integer.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_id_user", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_id_process", type = Integer.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_id_responsible", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_name", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_name_esa", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_name_us", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_name_ptb", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_action", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_out_result", type = String.class, mode = ParameterMode.OUT)
            }
    ),
    @NamedStoredProcedureQuery(
            name = "Procesos.addUpdateProcessUenCc",
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
            name = "Procesos.findProcessByName",
            procedureName = "nvc_pkg_procesos_spx.find_process_by_name",
            resultClasses = Procesos.class,
            parameters = {
                @StoredProcedureParameter(name = "p_process_name", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_cursor_out", type = void.class, mode = ParameterMode.REF_CURSOR)
            }
    ),
})

@SqlResultSetMappings({
    @SqlResultSetMapping(
            name = "Procesos.ProcessLang",
            classes = @ConstructorResult(
                    targetClass = Procesos.class,
                    columns = {
                        @ColumnResult(name = "id_proceso_uen", type = Integer.class),
                        @ColumnResult(name = "id_proceso", type = Integer.class),
                        @ColumnResult(name = "nombre_proceso", type = String.class),
                        @ColumnResult(name = "nombre_proceso_ptb", type = String.class),
                        @ColumnResult(name = "nombre_proceso_us", type = String.class),
                        @ColumnResult(name = "nombre_proceso_esa", type = String.class)
                    }
            )
    )
})

@Entity
//@Getter
//@Setter
public class Procesos implements Serializable {

    @Id
    @Column(name = "id_proceso_uen")
    private Integer idProcesoUen;

    @Column(name = "id_proceso_padre")
    private Integer idProcesoUenPadre;

    @Column(name = "id_proceso")
    private Integer idProceso;

    @Column(name = "nombre_proceso")
    private String nombreProceso;

    @Column(name = "id_responsable")
    private String idResponsable;

    @Column(name = "nombre_responsable")
    private String nombreResponsable;

    @Column(name = "nombre_proceso_ptb")
    private String nombreProcesoPtb;

    @Column(name = "nombre_proceso_us")
    private String nombreProcesoUs;

    @Column(name = "nombre_proceso_esa")
    private String nombreProcesoEsa;

    @Column(name = "id_usuario_creacion_pu")
    private String idUsuarioCreacionPu;

    @Column(name = "usuario_creacion_pu")
    private String suarioCreacionPu;

    @Column(name = "id_usuario_actualizacion_pu")
    private String idUsuarioActualizacionPu;

    @Column(name = "usuario_actualizacion_pu")
    private String usuarioActualizacionPu;

    @Column(name = "id_usuario_creacion_pro")
    private String idUsuarioCreacionPro;

    @Column(name = "usuario_creacion_pro")
    private String suarioCreacionPro;

    @Column(name = "id_usuario_actualizacion_pro")
    private String idUsuarioActualizacionPro;

    @Column(name = "usuario_actualizacion_pro")
    private String usuarioActualizacionPro;

    @Column(name = "fecha_creacion_pu")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaCreacionPu;

    @Column(name = "fecha_actualizacion_pu")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaActualizacionPu;

    @Column(name = "fecha_creacion_pro")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaCreacionPro;

    @Column(name = "fecha_actualizacion_pro")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaActualizacionPro;

    @Transient
    private List<Procesos> subprocesos= new ArrayList<Procesos>();
    
    @Transient
    private List<String> cc= new  ArrayList<String>();

    public Procesos() {
    }

    public Procesos(Integer idProcesoUen, Integer idProceso, String nombreProceso, String nombreProcesoPtb, String nombreProcesoUs, String nombreProcesoEsa) {
        this.idProcesoUen = idProcesoUen;
        this.idProceso = idProceso;
        this.nombreProceso = nombreProceso;
        this.nombreProcesoPtb = nombreProcesoPtb;
        this.nombreProcesoUs = nombreProcesoUs;
        this.nombreProcesoEsa = nombreProcesoEsa;
    }

    @Override
    public String toString() {
        return "Procesos{" + "idProcesoUen=" + idProcesoUen + ", idProceso=" + idProceso + ", nombreProceso=" + nombreProceso + ", nombreProcesoPtb=" + nombreProcesoPtb + ", nombreProcesoUs=" + nombreProcesoUs + ", nombreProcesoEsa=" + nombreProcesoEsa + '}';
    }

    public Integer getIdProcesoUen() {
        return idProcesoUen;
    }

    public void setIdProcesoUen(Integer idProcesoUen) {
        this.idProcesoUen = idProcesoUen;
    }

    public Integer getIdProcesoUenPadre() {
        return idProcesoUenPadre;
    }

    public void setIdProcesoUenPadre(Integer idProcesoUenPadre) {
        this.idProcesoUenPadre = idProcesoUenPadre;
    }

    public Integer getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(Integer idProceso) {
        this.idProceso = idProceso;
    }

    public String getNombreProceso() {
        return nombreProceso;
    }

    public void setNombreProceso(String nombreProceso) {
        this.nombreProceso = nombreProceso;
    }

    public String getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(String idResponsable) {
        this.idResponsable = idResponsable;
    }

    public String getNombreResponsable() {
        return nombreResponsable;
    }

    public void setNombreResponsable(String nombreResponsable) {
        this.nombreResponsable = nombreResponsable;
    }

    public String getNombreProcesoPtb() {
        return nombreProcesoPtb;
    }

    public void setNombreProcesoPtb(String nombreProcesoPtb) {
        this.nombreProcesoPtb = nombreProcesoPtb;
    }

    public String getNombreProcesoUs() {
        return nombreProcesoUs;
    }

    public void setNombreProcesoUs(String nombreProcesoUs) {
        this.nombreProcesoUs = nombreProcesoUs;
    }

    public String getNombreProcesoEsa() {
        return nombreProcesoEsa;
    }

    public void setNombreProcesoEsa(String nombreProcesoEsa) {
        this.nombreProcesoEsa = nombreProcesoEsa;
    }

    public String getIdUsuarioCreacionPu() {
        return idUsuarioCreacionPu;
    }

    public void setIdUsuarioCreacionPu(String idUsuarioCreacionPu) {
        this.idUsuarioCreacionPu = idUsuarioCreacionPu;
    }

    public String getSuarioCreacionPu() {
        return suarioCreacionPu;
    }

    public void setSuarioCreacionPu(String suarioCreacionPu) {
        this.suarioCreacionPu = suarioCreacionPu;
    }

    public String getIdUsuarioActualizacionPu() {
        return idUsuarioActualizacionPu;
    }

    public void setIdUsuarioActualizacionPu(String idUsuarioActualizacionPu) {
        this.idUsuarioActualizacionPu = idUsuarioActualizacionPu;
    }

    public String getUsuarioActualizacionPu() {
        return usuarioActualizacionPu;
    }

    public void setUsuarioActualizacionPu(String usuarioActualizacionPu) {
        this.usuarioActualizacionPu = usuarioActualizacionPu;
    }

    public String getIdUsuarioCreacionPro() {
        return idUsuarioCreacionPro;
    }

    public void setIdUsuarioCreacionPro(String idUsuarioCreacionPro) {
        this.idUsuarioCreacionPro = idUsuarioCreacionPro;
    }

    public String getSuarioCreacionPro() {
        return suarioCreacionPro;
    }

    public void setSuarioCreacionPro(String suarioCreacionPro) {
        this.suarioCreacionPro = suarioCreacionPro;
    }

    public String getIdUsuarioActualizacionPro() {
        return idUsuarioActualizacionPro;
    }

    public void setIdUsuarioActualizacionPro(String idUsuarioActualizacionPro) {
        this.idUsuarioActualizacionPro = idUsuarioActualizacionPro;
    }

    public String getUsuarioActualizacionPro() {
        return usuarioActualizacionPro;
    }

    public void setUsuarioActualizacionPro(String usuarioActualizacionPro) {
        this.usuarioActualizacionPro = usuarioActualizacionPro;
    }

    public Date getFechaCreacionPu() {
        if (this.fechaCreacionPu != null) {
            return new Date(this.fechaCreacionPu.getTime());
        }
        return null;

    }

    public void setFechaCreacionPu(Date fechaCreacionPu) {
        if (fechaCreacionPu != null) {
            this.fechaCreacionPu = new Date(fechaCreacionPu.getTime());
        } else {
            this.fechaCreacionPu = null;
        }

    }

    public Date getFechaActualizacionPu() {
        if (this.fechaActualizacionPu != null) {
            return new Date(this.fechaActualizacionPu.getTime());
        }
        return null;
    }

    public void setFechaActualizacionPu(Date fechaActualizacionPu) {
        if (fechaActualizacionPu != null) {
            this.fechaActualizacionPu = new Date(fechaActualizacionPu.getTime());
        } else {
            this.fechaActualizacionPu = null;
        }
    }

    public Date getFechaCreacionPro() {
        if (this.fechaCreacionPro != null) {
            return new Date(this.fechaCreacionPro.getTime());
        }
        return null;
    }

    public void setFechaCreacionPro(Date fechaCreacionPro) {
        if (fechaCreacionPro != null) {
            this.fechaCreacionPro = new Date(fechaCreacionPro.getTime());
        } else {
            this.fechaCreacionPro = null;
        }
    }

    public Date getFechaActualizacionPro() {
        if (this.fechaActualizacionPro != null) {
            return new Date(this.fechaActualizacionPro.getTime());
        }
        return null;
    }

    public void setFechaActualizacionPro(Date fechaActualizacionPro) {
        if (fechaActualizacionPro != null) {
            this.fechaActualizacionPro = new Date(fechaActualizacionPro.getTime());
        }else{
            this.fechaActualizacionPro = null;
        }

    }

    public List<Procesos> getSubprocesos() {
        return subprocesos;
    }

    public void setSubprocesos(List<Procesos> subprocesos) {
        this.subprocesos = subprocesos;
    }

    /**
     * @return the cc
     */
    public List<String> getCc() {
        return cc;
    }

    /**
     * @param cc the cc to set
     */
    public void setCc(List<String> cc) {
        this.cc = cc;
    }


}
