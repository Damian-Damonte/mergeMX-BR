package com.metalsa.contralor.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Oscar del Angel
 */
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
            name = "CentroCostoProceso.findCostsCenter",
            procedureName = "NVC_PKG_PROCESOS_SPX.find_costs_center",
            resultClasses = CentroCostoProceso.class,
            parameters = {
                @StoredProcedureParameter(name = "p_id_uen", type = Integer.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_id_ccs", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_id_process", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_id_resp_ccs", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_id_resp_grs", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_id_dels", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_witout_resp", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_last_update_i", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_last_update_e", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_id_idioma", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_page_start", type = Integer.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_page_end", type = Integer.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_cursor_out", type = Void.class, mode = ParameterMode.REF_CURSOR)
            }
    )
    ,
    @NamedStoredProcedureQuery(
            name = "CentroCostoProceso.findCostsCenterCount",
            procedureName = "NVC_PKG_PROCESOS_SPX.find_costs_center_count",
            resultClasses = CentroCostoProceso.class,
            parameters = {
                @StoredProcedureParameter(name = "p_id_uen", type = Integer.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_id_ccs", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_id_process", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_id_resp_ccs", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_id_resp_grs", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_id_dels", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_witout_resp", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_last_update_i", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_last_update_e", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_id_idioma", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_out_result", type = Long.class, mode = ParameterMode.OUT)
            }
    )
    ,
    @NamedStoredProcedureQuery(
            name = "CentroCostoProceso.updateCCPorUsuario",
            procedureName = "NVC_PKG_PROCESOS_SPX.update_users_cost_center",
            parameters = {
                @StoredProcedureParameter(name = "p_id_cc", type = Long.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_id_proceso_uen", type = Long.class, mode = ParameterMode.IN),//R39943
                @StoredProcedureParameter(name = "p_id_uen", type = Integer.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_nivel", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_relacion", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_id_usuario", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_id_usuarios", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_tipo_accion", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_out_result", type = String.class, mode = ParameterMode.OUT)
            }
    )
})

@Getter
@Setter
@Entity(name = "CentroCostoProceso")
public class CentroCostoProceso implements Serializable {

    @Id
    @Column(name = "id_cc")
    private Long idCC;

    @Column(name = "codigo_cc")
    private String codigoCC;

    @Column(name = "nombre_cc")
    private String nombreCC;

    @Column(name = "id_proceso")
    private Integer idProceso;
	
	//R41223
    @Column(name = "id_proceso_uen")
    private Long idProcesoUen;

    @Column(name = "nombre_proceso")
    private String nombreProceso;

    @Column(name = "id_sub_proceso")
    private Integer idSubProceso;
	
	//R41223
    @Column(name = "id_sub_proceso_uen")
    private Long idSubProcesoUen;

    @Column(name = "nombre_sub_proceso")
    private String nombreSubProceso;
    
    @Column(name = "id_responsable_cc")
    private String idResponsableCC;

    @Column(name = "responsable_cc")
    private String ResponsableCC;
    
    @Column(name = "id_responsable_proceso")
    private String idResponsableProceso;

    @Column(name = "responsable_proceso")
    private String responsableProceso;

    @Column(name = "id_responsable_sub_proceso")
    private String idResponsableSubProceso;

    @Column(name = "responsable_sub_proceso")
    private String responsableSubProceso;
    
    @Column(name = "responsable_sub_proceso_ant")
    private String responsableSubProcesoAnt;
  
    @Column(name = "actualizacion_min")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date ActualizacionMin;

    @Column(name = "actualizacion_max")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date ActualizacionMax;

    @Column(name = "usuario_actualizacion")
    private String usuarioActualizacion;
  
	//<R39943>
    @Transient
    private List<CentroCostoProcesoDetalle> lineas;//lines_to_update
    
    @Transient
    private List<CentroCostoProcesoDetalle> lineasToAdd;

    @Transient
    private List<CentroCostoProcesoDetalle> linesToDelete;
	//<R39943>

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.idCC);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CentroCostoProceso other = (CentroCostoProceso) obj;
        return Objects.equals(this.idCC, other.idCC);
    }

    public Date getActualizacionMin() {
        return (ActualizacionMin = ActualizacionMin == null ? null : new Date(ActualizacionMin.getTime()));
    }

    public void setActualizacionMin(Date ActualizacionMin) {
        this.ActualizacionMin = ActualizacionMin == null ? null : new Date(ActualizacionMin.getTime());
    }

    public Date getActualizacionMax() {
        return (ActualizacionMax = ActualizacionMax == null ? null : new Date(ActualizacionMax.getTime()));
    }

    public void setActualizacionMax(Date ActualizacionMax) {
        this.ActualizacionMax = ActualizacionMax == null ? null : new Date(ActualizacionMax.getTime());
        
    }

}
