package com.metalsa.aprobacion.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.ParameterMode.IN;
import static javax.persistence.ParameterMode.REF_CURSOR;

/**
 * Created by ruben.bresler on 06/07/2017.
 */
@Data
@Entity
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "Notificaciones.procNuevoHeader",
                resultClasses = NotificacionHeader.class,
                procedureName = "nvc_notificaciones.proc_nuevo_header_1",
                parameters = {
                        @StoredProcedureParameter(mode=REF_CURSOR, name="cursor_out", type=void.class),
                        @StoredProcedureParameter(mode=IN, name="p_usuario", type=String.class),
                        @StoredProcedureParameter(mode=IN, name="p_idioma", type=String.class)
                }
        )
})
public class NotificacionHeader {
    @Id
    private Long idRequisicion;
    @Column(name="id_usuario")
    private String usuario;
    private String nombreUsuario;
    private Date fechaRequisicion;

    @Column(name="fecha_requerida")
    private Date fechaNecesidad;
    private String nombreComprador;
    private boolean urgente;
    private String estatus;
    private String descEstatus;
    private boolean leido;

    public Date getFechaRequisicion() {
        return fechaRequisicion == null ? null : (Date) fechaRequisicion.clone();
    }

    public void setFechaRequisicion(Date fechaRequisicion) {
        if (fechaRequisicion == null)
            this.fechaRequisicion = null;
        else
            this.fechaRequisicion = (Date) fechaRequisicion.clone();
    }

    public Date getFechaNecesidad() {
        return fechaNecesidad == null ? null : (Date) fechaNecesidad.clone();
    }

    public void setFechaNecesidad(Date fechaNecesidad) {
        if (fechaNecesidad == null)
            this.fechaNecesidad = null;
        else
            this.fechaNecesidad = (Date) fechaNecesidad.clone();
    }
}