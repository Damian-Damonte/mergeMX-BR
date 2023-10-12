package com.metalsa.reportes.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Diego Juarez
 */
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
            name = "getEstadoCuentaProject",
            procedureName = "NVC_PKG_REPORTES_SPX.Get_Proyecto_Travel",
            resultClasses = EstadoCuentaProject.class,
            parameters = {
                @StoredProcedureParameter(type = String.class, name = "P_ID_USUARIO", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = Integer.class, name = "P_ID_UEN", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = Integer.class, name = "P_ID_PROYECTO", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = Integer.class, name = "P_ID_TAREA", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = String.class, name = "P_TIPO_GASTO", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = Void.class, name = "CURSOR_OUT", mode = ParameterMode.REF_CURSOR)
            }
    ),
    @NamedStoredProcedureQuery(
            name = "getEstadoCuentaProyecto",
            procedureName = "NVC_PKG_REPORTES_SPX.GET_ESTADO_CUENTA_PROYECTO",
            resultClasses = EstadoCuentaProject.class,
            parameters = {
                //@StoredProcedureParameter(type = String.class, name = "P_ID_USUARIO", mode = ParameterMode.IN),
                //@StoredProcedureParameter(type = Integer.class, name = "P_ID_UEN", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = Integer.class, name = "P_ID_PROYECTO", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = String.class, name = "P_ID_TAREA", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = String.class, name = "P_TIPO_GASTO", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = Void.class, name = "CURSOR_OUT", mode = ParameterMode.REF_CURSOR)
            }
    )
})
@Entity
@Getter
@Setter
public class EstadoCuentaProject implements Serializable{
    
    @Id
    private Long id;
    
    @Column(name="proyecto")
    private String proyecto;
    
    @Column(name="tarea")
    private String tarea;
    
    @Column(name="tipo_gasto")
    private String tipoGasto;
    
    @Column(name="budget")
    private Double budget;
    
    @Column(name="actual")
    private Double actual;
    
    @Column(name="encumbrances")
    private Double encumbrances;
    
    @Column(name="available")
    private Double available;
    
    @Transient
    private Presupuesto total;
    
   
}
