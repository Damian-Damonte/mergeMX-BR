package com.metalsa.finanzas.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import static javax.persistence.ParameterMode.IN;
import static javax.persistence.ParameterMode.REF_CURSOR;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import lombok.Data;

/**
 *
 * @author JL
 */
@Entity
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
            name = "get_solicitudes_usuario",
            resultClasses = SeguimientoSolicitud.class,
            procedureName = "nvc_pkg_request_manager_spx.get_solicitudes_usuario",
            parameters = {
                        @StoredProcedureParameter(mode = REF_CURSOR, name = "CURSOR_OUT", type = void.class),
                        @StoredProcedureParameter(mode = IN, name = "P_USUARIO", type = String.class)
            }),
    @NamedStoredProcedureQuery(
            name = "get_reporte_usuario",
            resultClasses = SeguimientoSolicitud.class,
            procedureName = "nvc_pkg_request_manager_spx.get_reporte_usuario",
            parameters = {
                        @StoredProcedureParameter(mode = REF_CURSOR, name = "CURSOR_OUT", type = void.class),
                        @StoredProcedureParameter(mode = IN, name = "P_USUARIO", type = String.class),
                        @StoredProcedureParameter(mode = IN, name = "P_FECHA_INICIO", type = Date.class),
                        @StoredProcedureParameter(mode = IN, name = "P_FECHA_FIN", type = Date.class)
            })
})


@Data
public class SeguimientoSolicitud implements Serializable{

    @Id
    @Column(name = "id_detalle")
    private Long idDetalle;

    @Column(name = "id_solicitud_presupuesto")
    private Long idSolicitud;

    @Column(name = "id_partida")
    private Integer idPartida;

    @Column(name = "id_uen")
    private Long idUen;

    @Column(name = "desc_estatus")
    private String estatus;

    @Column(name = "cc_destino")
    private String ccDestino;

    @Column(name = "cc_origen")
    private String ccOrigen;

    @Column(name = "periodo_destino")
    private String periodoDestino;

    @Column(name = "periodo_origen")
    private String periodoOrigen;

    @Column(name = "categoria_origen")
    private String categoriaOrigen;

    @Column(name = "categoria_destino")
    private String categoriaDestino;

    @Column(name = "monto")
    private Double monto;

    @Column(name = "moneda")
    private String moneda;

    @Column(name = "fecha_necesidad")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNecesidad;

    @Column(name = "razon")
    private String razon;
    
    @Column(name = "comentarios")
    private String comentarios;

    @Column(name = "aprobador_finanzas")
    private String aprobadorFinanzas;

    @Column(name = "usuario_creacion")
    private String usuarioCreacion;

    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "aprobador_cc")
    private String aprobadorCC;

    @Column(name = "razon_rechazo")
    private String razonRechazo;

    @Column(name = "period_onh")
    private Double saldoDisponible;

    @Transient
    private String transaccion;

    @Transient
    private String nombreUen;
    
    @Transient
    private Double porcentaje;

    @Transient
    private String cc_id;
     
    @Transient
    private Integer idCategoria;
    
    @Column(name = "delegados_cc")
    private String delegadosCc;

    public Date getFechaNecesidad() {
        return fechaNecesidad == null ? null : new Date(fechaNecesidad.getTime());
    }

    public void setFechaNecesidad(Date fechaNecesidad) {
        if (fechaNecesidad == null) {
            this.fechaNecesidad = null;
        } else {
            this.fechaNecesidad = new Date(fechaNecesidad.getTime());
        }
    }

    public Date getFechaCreacion() {
        return fechaCreacion == null ? null : new Date(fechaCreacion.getTime());
    }

    public void setFechaCreacion(Date fechaCreacion) {
        if (fechaCreacion == null) {
            this.fechaCreacion = null;
        } else {
            this.fechaCreacion = new Date(fechaCreacion.getTime());
        }
    }
}
