package com.metalsa.reportes.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Objects;
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
        name = "getReportePresupuestoUen",
        procedureName = "NVC_PKG_REPORTES_SPX.GET_REPORTE_ESTADOCUENTA_UEN",
        resultClasses = PresupuestoUenLinea.class,
        parameters = {
            @StoredProcedureParameter(type = Integer.class, name = "p_periodo_inicial", mode = ParameterMode.IN),
            @StoredProcedureParameter(type = Integer.class, name = "p_periodo_final", mode = ParameterMode.IN),
            @StoredProcedureParameter(type = Integer.class, name = "p_anio", mode = ParameterMode.IN),
            @StoredProcedureParameter(type = Integer.class, name = "p_tipo_vista", mode = ParameterMode.IN),
            @StoredProcedureParameter(type = String.class, name = "p_id_uens", mode = ParameterMode.IN),
            @StoredProcedureParameter(type = String.class, name = "p_id_ccs", mode = ParameterMode.IN),
            @StoredProcedureParameter(type = String.class, name = "p_idioma", mode = ParameterMode.IN),
            @StoredProcedureParameter(type = Void.class, name = "p_ref_cursor", mode = ParameterMode.REF_CURSOR)
        }
    )
})
@Entity
@Getter
@Setter
public class PresupuestoUenLinea extends Presupuesto implements Serializable {

    @Column(name = "id_categoria")
    private Integer idCategoria;

    @Column(name = "categoria")
    private String nombreCategoria;

    @Column(name = "nombre_uen")
    private String nombreUen;

    @Column(name = "company")
    private String company;

    @Column(name = "codigo_cc")
    private String codigoCC;

    @Column(name = "nombre_cc")
    private String nombreCC;

    @Transient
    @JsonProperty("custom_name")
    private String customName;

    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final PresupuestoUenLinea other = (PresupuestoUenLinea) obj;
        return Objects.equals(this.id, other.id);
    }

    public String getCustonName() {
        if (this.codigoCC != null) {
            this.setCustomName(this.codigoCC + "-" + this.nombreCC);
        } else {
            this.setCustomName(this.nombreCategoria);
        }
        return this.customName;
    }

    public void setCustomName(String customName) {
        if (this.customName == null && customName != null) {
            this.customName = customName;
        }
    }

}
