package com.metalsa.reportes.model;

import java.io.Serializable;
import javax.persistence.Basic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Diego Juarez
 */
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
            name = "EstadoCuentaTravel.getEstadoCuentaTravel",
            procedureName = "NVC_PKG_REPORTES_SPX.GET_ESTADOCUENTA_TRAVEL",
            resultClasses = EstadoCuentaTravel.class,
            parameters = {
                @StoredProcedureParameter(type = Integer.class, name = "P_ID_UEN", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = Integer.class, name = "P_FECHA_I", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = Integer.class, name = "P_FECHA_F", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = String.class, name = "P_COST_CENTER", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = String.class, name = "P_IDIOMA", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = Void.class, name = "p_ref_cursor", mode = ParameterMode.REF_CURSOR)
            }
    )
})
@Entity
@Getter
@Setter
public class EstadoCuentaTravel implements Serializable{
    
    @Id
    private Integer id;
    
    @Column(name="comprometido")
    private Double comprometido;
    
    @Basic(optional = true)
    @Column(name="erogado")
    private Double erogado;
    
    @Column(name="presupuesto")
    private Double presupuesto;
    
    @Column(name="actual")
    private Double actual;
    
    @Column(name="disponible")
    private Double disponible;
    
    @Column(name="cuenta")
    private String cuenta;
    
    @Basic(optional = true)
    @Column(name="detalle")
    private String detalle;

}
