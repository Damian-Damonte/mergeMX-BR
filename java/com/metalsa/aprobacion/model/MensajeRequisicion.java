package com.metalsa.aprobacion.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

import static javax.persistence.ParameterMode.IN;
import static javax.persistence.ParameterMode.REF_CURSOR;

/**
 * Clase para el mapeo de los mensajes por requisicion entre Requisidor y otros roles
 */
@Data
@NoArgsConstructor
@Entity(name = "nvc_tbl_mensajes_requi")
@NamedStoredProcedureQuery(
        name = "Notificaciones.findMensajesByUsuarioAndIdioma",
        resultClasses = MensajeRequisicion.class,
        procedureName = "nv_notificaciones.proc_nuevo_header",
        parameters = {
                @StoredProcedureParameter(mode = REF_CURSOR, name = "cursor_out", type = void.class),
                @StoredProcedureParameter(mode = IN, name = "p_usuario", type = String.class),
                @StoredProcedureParameter(mode = IN, name = "p_idioma", type = String.class)
        }
)
@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "FullNameMapping",
                classes = {
                        @ConstructorResult(
                                targetClass = MensajeRequisicion.class,
                                columns = {
                                        @ColumnResult(name = "id_msj_req", type = Long.class),
                                        @ColumnResult(name = "id_requisicion", type = Long.class),
                                        @ColumnResult(name = "fecha_creacion", type = LocalDateTime.class),
                                        @ColumnResult(name = "id_usuario", type = String.class),
                                        @ColumnResult(name = "nombre_usuario", type = String.class),
                                        @ColumnResult(name = "mensaje", type = String.class),
                                        @ColumnResult(name = "id_rol", type = Long.class),
                                        @ColumnResult(name = "id_rol_destinatario", type = Long.class),
                                        @ColumnResult(name = "leido", type = String.class)
                                }
                        )
                }
        )
})
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "MensajeRequisicion.mensajesPorRequisicion",
                resultClass = MensajeRequisicion.class,
                resultSetMapping = "FullNameMapping",
                query = "SELECT msg.id_msj_req, msg.id_requisicion, msg.fecha_creacion, msg.id_usuario, " +
                        "   u.nombre_usuario, msg.id_rol, msg.id_rol_destinatario, " +
                        "   msg.mensaje, msg.leido\n" +
                        "FROM nvc_tbl_mensajes_requi msg\n" +
                        "   JOIN usuario u ON u.id_usuario = msg.id_usuario\n" +
                        "WHERE msg.id_requisicion = ?\n" +
                        "ORDER BY msg.fecha_creacion desc"
        )
})
public class MensajeRequisicion implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id_msj_req")
    private Long id;

    @Column(name = "id_requisicion")
    private Long requisicion;

    @Column(name = "fecha_creacion")
    private LocalDateTime fecha;

    @Column(name = "id_usuario")
    private String remitente;

    @Transient
    private String nombreCompleto;

    private String mensaje;

    @Column(name = "id_rol")
    private Long rolRemitente;

    @Column(name = "id_rol_destinatario")
    private Long rolDestinatario;

    private String leido;

    public MensajeRequisicion(Long id, Long requisicion, LocalDateTime fecha, String remitente, String nombreCompleto,
                              String mensaje, Long rolRemitente, Long rolDestinatario, String leido) {
        this.id = id;
        this.requisicion = requisicion;
        setFecha(fecha);
        this.remitente = remitente;
        this.nombreCompleto = nombreCompleto;
        this.mensaje = mensaje;
        this.rolRemitente = rolRemitente;
        this.rolDestinatario = rolDestinatario;
        this.leido = leido;
    }

//    public LocalDateTime getFecha() {
//        return fecha == null ? null : (Date) fecha.clone();
//    }
//
//    public void setFecha(Date fecha) {
//        if (fecha == null)
//            this.fecha = null;
//        else
//            this.fecha = (Date) fecha.clone();
//    }
}