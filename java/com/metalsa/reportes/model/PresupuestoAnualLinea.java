/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.reportes.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Oscar del Angel
 */
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
        name = "getReportePresupuestoAnual",
        procedureName = "NVC_PKG_REPORTES_SPX.GET_REPORTE_PRESUPUESTO_ANUAL",
        resultClasses = PresupuestoAnualLinea.class,
        parameters = {
            @StoredProcedureParameter(type = Integer.class, name = "p_uen", mode = ParameterMode.IN),
            @StoredProcedureParameter(type = Integer.class, name = "p_periodo_inicial", mode = ParameterMode.IN),
            @StoredProcedureParameter(type = Integer.class, name = "p_periodo_final", mode = ParameterMode.IN),
            @StoredProcedureParameter(type = String.class, name = "p_cc_inicial", mode = ParameterMode.IN),
            @StoredProcedureParameter(type = String.class, name = "p_cc_final", mode = ParameterMode.IN),
            @StoredProcedureParameter(type = String.class, name = "p_id_ccs", mode = ParameterMode.IN),
            @StoredProcedureParameter(type = String.class, name = "p_idioma", mode = ParameterMode.IN),
            @StoredProcedureParameter(type = Void.class, name = "p_ref_cursor", mode = ParameterMode.REF_CURSOR)
        }
    )
})
@Entity
@Getter
@Setter
@JsonIgnoreProperties({"nombre_cc", "responsable", "id","codigo_cc"})
public class PresupuestoAnualLinea extends Meses {

    @Column(name = "tipo")
    String tipo;

    @Column(name = "nombre_cc")
    private String nombreCC;

    @Column(name = "codigo_cc")
    private String codigoCC;

    @Column(name = "responsable")
    private String responsable;

    @Transient
    private double total;

    public double getTotal() {
        this.total = this.enero + this.febrero + this.marzo + this.abril + this.mayo + this.junio
                + this.julio + this.agosto + this.septiembre + this.octubre + this.noviembre + this.diciembre;
        return this.total;
    }

}
