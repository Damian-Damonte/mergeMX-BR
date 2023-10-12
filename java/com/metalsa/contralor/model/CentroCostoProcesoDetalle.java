package com.metalsa.contralor.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
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
            name = "CentroCostoProcesoDetalle.findDetailCC",
            procedureName = "NVC_PKG_PROCESOS_SPX.find_detail_cc",
            resultClasses = CentroCostoProcesoDetalle.class,
            parameters = {
                @StoredProcedureParameter(name = "p_id_uen", type = Integer.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_id_cc", type = Long.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_cursor_out", type = Void.class, mode = ParameterMode.REF_CURSOR)
            }
    ),
    @NamedStoredProcedureQuery(
            name = "CentroCostoProcesoDetalle.findDetailCC_UEN",
            procedureName = "NVC_PKG_PROCESOS_SPX.find_detail_cc_by_uen",
            resultClasses = CentroCostoProcesoDetalle.class,
            parameters = {
                @StoredProcedureParameter(name = "p_id_uen", type = Integer.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_cursor_out", type = Void.class, mode = ParameterMode.REF_CURSOR)
            }
    )
})
@NamedNativeQueries({
    @NamedNativeQuery(
            name = "CentroCostoProcesoDetalle.findCCSUsers",
            query = "select\n"
            + "	rownum                                      id,\n"
            + "	ccu.id_cc                                   id_cc,\n"
            + "	ccu.id_uen                                  id_uen,\n"
            + "	ccu.id_usuario                              id_usuario,\n"
            + "	get_nombre_usuario(ccu.id_usuario)          nombre_usuario,\n"
            + "	ccu.asignado_por                            id_asignado_por,\n"
            + "	get_nombre_usuario(ccu.asignado_por)        asignado_por,\n"
            + "	ccu.tipo_de_relacion                        tipo_de_relacion,\n"
            + "	to_char(ccu.fecha_actualizacion)            fecha_actualizacion,\n"
            + "	(\n"
            + "     select na.nombre_nivel\n"
            + "     from niveles_aprobacion na\n"
            + "     where na.id_nivel =  ccu.nivel_rqi\n"
            + "	)                                           nivel_aprobacion\n"
            + "from cc_por_usuario ccu\n"
            + "where id_uen = ?1 \n"
            + "and id_cc = ?2 \n"
            + "and ccu.tipo_de_relacion in ('Del','Resp')",
            resultClass = CentroCostoProcesoDetalle.class
    )

})

//@Getter
//@Setter
@Entity
public class CentroCostoProcesoDetalle implements Serializable {
    @Id
    @Column(name = "id")
    private Long rowNum;

    @Column(name = "id_cc")
    private Long idCC;

    @Column(name = "id_usuario")
    private String idUsuario;

    @Column(name = "nombre_usuario")
    private String nombreUsuario;

    @Column(name = "relacion")
    private String relacion;

    @Column(name = "nivel_aprobacion")
    private String nivelAprobacion;

    @Column(name = "id_asignado_por")
    private String idAsignadoPor;

    @Column(name = "asignado_por")
    private String asignadoPor;

    @Column(name = "id_usuario_anterior")
    private String idUsuarioAnterior;

    @Column(name = "usuario_anterior")
    private String usuarioAnterior;

    @Column(name = "tipo_de_relacion")
    private String tipoDeRelacion;    
    
    @Column(name = "fecha_actualizacion")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaActualizacion;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.rowNum);
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
        final CentroCostoProcesoDetalle other = (CentroCostoProcesoDetalle) obj;
        if (!Objects.equals(this.rowNum, other.rowNum)) {
            return false;
        }
        return Objects.equals(this.rowNum, other.rowNum);
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getRelacion() {
        return relacion;
    }

    public void setRelacion(String relacion) {
        this.relacion = relacion;
    }

    public String getNivelAprobacion() {
        return nivelAprobacion;
    }

    public void setNivelAprobacion(String nivelAprobacion) {
        this.nivelAprobacion = nivelAprobacion;
    }

    public String getIdAsignadoPor() {
        return idAsignadoPor;
    }

    public void setIdAsignadoPor(String idAsignadoPor) {
        this.idAsignadoPor = idAsignadoPor;
    }

    public String getAsignadoPor() {
        return asignadoPor;
    }

    public void setAsignadoPor(String asignadoPor) {
        this.asignadoPor = asignadoPor;
    }

    public Date getFechaActualizacion() {
        return this.fechaActualizacion == null ? null : new Date(this.fechaActualizacion.getTime());
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion == null ? null : new Date(fechaActualizacion.getTime());
    }

    public String getTipoDeRelacion() {
        return tipoDeRelacion;
    }

    public void setTipoDeRelacion(String tipoDeRelacion) {
        this.tipoDeRelacion = tipoDeRelacion;
    }

    public String getIdUsuarioAnterior() {
        return idUsuarioAnterior;
    }

    public void setIdUsuarioAnterior(String idUsuarioAnterior) {
        this.idUsuarioAnterior = idUsuarioAnterior;
    }

    public String getUsuarioAnterior() {
        return usuarioAnterior;
    }

    public void setUsuarioAnterior(String usuarioAnterior) {
        this.usuarioAnterior = usuarioAnterior;
    }

    /**
     * @return the rowNum
     */
    public Long getRowNum() {
        return rowNum;
    }

    /**
     * @param rowNum the rowNum to set
     */
    public void setRowNum(Long rowNum) {
        this.rowNum = rowNum;
    }

    /**
     * @return the idCC
     */
    public Long getIdCC() {
        return idCC;
    }

    /**
     * @param idCC the idCC to set
     */
    public void setIdCC(Long idCC) {
        this.idCC = idCC;
    }

}
