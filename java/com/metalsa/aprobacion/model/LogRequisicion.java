
package com.metalsa.aprobacion.model;

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
import lombok.Data;

/**
 *
 * @author APOOD9272
 */
@Entity(name = "NVC_TBL_LOGS_REQUISICIONES")
@Data
@NamedStoredProcedureQueries(
        @NamedStoredProcedureQuery(
                name = "agregarRegistroAprobacion",
                procedureName = "AGREGAR_LOG_REQUISICION",
                parameters = {
                    @StoredProcedureParameter(mode = ParameterMode.IN,name = "p_id_requisicion",type = Long.class),
                    @StoredProcedureParameter(mode = ParameterMode.IN,name = "p_id_partida",type = Long.class),
                    @StoredProcedureParameter(mode = ParameterMode.IN,name = "p_id_requisicion",type = Long.class),
                    @StoredProcedureParameter(mode = ParameterMode.IN,name = "p_id_usuario",type = String.class),
                    @StoredProcedureParameter(mode = ParameterMode.IN,name = "p_accion",type = String.class),
                    @StoredProcedureParameter(mode = ParameterMode.IN,name = "p_mensaje",type = String.class),
                    @StoredProcedureParameter(mode = ParameterMode.IN,name = "p_ora_error",type = String.class),
                    @StoredProcedureParameter(mode = ParameterMode.IN,name = "p_app_origen",type = String.class),
                    @StoredProcedureParameter(mode = ParameterMode.IN,name = "p_modulo",type = String.class)
                }
        )
)

public class LogRequisicion implements Serializable{
    
    @Id
    @Column(name = "id_registro")
    private Long idRegistro;
        
    @Column(name = "id_requisicion")
    private Long idRequisicion;

    @Column(name = "id_partida")
    private Long idPartida;
    
    @Column(name = "accion")
    private Long accion;

    @Column(name = "id_usuario")
    private String idUsuario;

    @Column(name = "mensaje")
    private String mensaje;
    
    @Column(name = "ora_error")
    private String oraError;
    
    @Column(name = "app_origen")
    private String appOrigen;

    @Column(name = "modulo")
    private String modulo;

    @Column(name = "fecha")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    
    
    public Date getFecha() {
        return fecha == null ? null : (Date) fecha.clone();
    }

    public void setFecha(Date fecha) {
        if (fecha == null)
            this.fecha = null;
        else
            this.fecha = (Date) fecha.clone();
    }
    
    
}
