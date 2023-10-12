package com.metalsa.core.model;

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
import static javax.persistence.ParameterMode.REF_CURSOR;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.Temporal;
import lombok.Data;

/**
 *
 * @author Oscar del Angel
 */
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
            name = "Periodos.findPeriodByProyectFrom",//<R39943>
            procedureName = "nvc_pkg_periodos.find_from_by_proyect",
            resultClasses = Periodos.class,
            parameters = {
                @StoredProcedureParameter(type = String.class, name = "p_id_idioma", mode = ParameterMode.IN)
                ,
                @StoredProcedureParameter(type = Integer.class, name = "p_id_proyecto", mode = ParameterMode.IN)
                ,
                @StoredProcedureParameter(mode = REF_CURSOR, name = "out_cursor", type = void.class),}
    )
    ,
        
    @NamedStoredProcedureQuery(
            name = "Periodos.findByPorRangoAnioMes",//<R39943>
            procedureName = "nvc_pkg_procesos_spx.find_By_Por_RangoAnioMes",
            resultClasses = Periodos.class,
            parameters = {
                @StoredProcedureParameter(mode = REF_CURSOR, name = "p_cursor_out", type = void.class)
                ,
                @StoredProcedureParameter(type = Integer.class, name = "p_anioinicial", mode = ParameterMode.IN)
                ,
                @StoredProcedureParameter(type = Integer.class, name = "p_aniofinal", mode = ParameterMode.IN)
                ,
                @StoredProcedureParameter(type = Integer.class, name = "p_mesinicial", mode = ParameterMode.IN)
                ,
                @StoredProcedureParameter(type = Integer.class, name = "p_mesfinal", mode = ParameterMode.IN)
                ,
                @StoredProcedureParameter(type = String.class, name = "p_idioma", mode = ParameterMode.IN),}
    )
    //RDM54073
    ,
    @NamedStoredProcedureQuery(
            name = "Periodos.getPeriodosContablesFuturos",//<R39943>
            procedureName = "UTILERIAS_PKG.getPeriodosContablesFuturos",
            resultClasses = Periodos.class,
            parameters = {
                @StoredProcedureParameter(mode = REF_CURSOR, name = "V_CURSOR", type = void.class)
                ,
                @StoredProcedureParameter(type = Integer.class, name = "p_id_uen", mode = ParameterMode.IN)
                ,
                @StoredProcedureParameter(type = String.class, name = "p_idioma", mode = ParameterMode.IN)
            }
    )
    ,
    @NamedStoredProcedureQuery(
            name = "Periodos.getPeriodosContablesPasado",//<R39943>
            procedureName = "UTILERIAS_PKG.getPeriodosContablesPasado",
            resultClasses = Periodos.class,
            parameters = {
                @StoredProcedureParameter(mode = REF_CURSOR, name = "V_CURSOR", type = void.class)
                ,
            @StoredProcedureParameter(type = Integer.class, name = "p_id_uen", mode = ParameterMode.IN)
                ,
            @StoredProcedureParameter(type = String.class, name = "p_idioma", mode = ParameterMode.IN)
            }
    )
//RDM54073    
})

@NamedNativeQueries({
    @NamedNativeQuery(
            name = "Periodos.getPeriodoActual",
            resultClass = Periodos.class,
            query = "select\n"
            + "        periodo.*\n"
            + "    from NVC_VM_PERIODOS_CONTABLES periodo\n"
            + "    where periodo.anio  = extract(year from sysdate)\n"
            + "    and periodo.num_mes = extract(month from sysdate)"
            + "    and periodo.idioma = :idioma"
    )//<R39943>
    ,
    @NamedNativeQuery(
            name = "Periodos.getPeriodoDesde",
            resultClass = Periodos.class,
            query = "select\n"
            + "    periodo.*\n"
            + " from NVC_VM_PERIODOS_CONTABLES periodo\n"
            + " where idioma = :idioma "
            + " and periodo.anio between :anioInicial and :currentYear "
            + " and 1 = (\n"
            + "    case \n"
            + "        when periodo.anio  <> :currentYear then 1\n"
            + "        when  periodo.num_mes <= :mes then 1\n"
            + "        else  0\n"
            + "    end\n"
            + " )"
    )
    ,
    @NamedNativeQuery(
            name = "Periodos.getAllByAnioActualDisponible",
            resultClass = Periodos.class,
            query = " select\n"
            + "        periodo.*\n"
            + "    from NVC_VM_PERIODOS_CONTABLES periodo\n"
            + "    where periodo.anio  = extract(year from sysdate)\n"
            + "    --and periodo.num_mes >= extract(month from sysdate)\n"
            + "    and periodo.idioma  = :idioma"
    )})
@Entity
@Data
@Table(name = "NVC_VM_PERIODOS_CONTABLES")
public class Periodos implements Serializable {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "anio")
    private Integer anio;

    @Column(name = "num_mes")
    private Integer numMes;

    @Column(name = "mes")
    private String mes;

    @Column(name = "mes_abreviado")
    private String mesAbreviado;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "nombre_original")
    private String nombreOriginal;

    @Column(name = "idioma")
    private String idioma;

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "fecha_inicio")
    private Date fechaInicio;

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "fecha_fin")
    private Date fechFin;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final Periodos other = (Periodos) obj;
        return Objects.equals(this.id, other.id);
    }

    public Date getFechFin() {
        return this.fechFin == null ? null : new Date(this.fechFin.getTime());
    }

    public void setFechFin(Date fechFin) {
        if (fechFin == null) {
            this.fechFin = null;
        } else {
            this.fechFin = new Date(fechFin.getTime());
        }
    }

    public void setFechaInicio(Date fechaInicio) {
        if (fechaInicio == null) {
            this.fechaInicio = null;
        } else {
            this.fechaInicio = new Date(fechaInicio.getTime());
        }
    }

    public Date getFechaInicio() {
        return this.fechaInicio == null ? null : new Date(this.fechaInicio.getTime());
    }

}
