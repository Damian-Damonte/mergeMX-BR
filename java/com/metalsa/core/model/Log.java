/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author edgar.leal
 */
@Entity
@XmlRootElement
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(name = "AGREGAR_LOG_REQUISICION",
            procedureName = "AGREGAR_LOG_REQUISICION",
            //resultSetMappings = {"ImprimeReciboConstructorResultSetMapping"},
            //resultClasses = ImprimeRecibo.class,
            parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ID_REQUISICION", type = Integer.class)
                ,
                 @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ID_PARTIDA", type = Integer.class)
                ,
                  @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_MODULO", type = String.class)
                ,
                   @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ID_USUARIO", type = String.class)
                ,
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ACCION", type = String.class)
                ,
                   @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_MENSAJE", type = String.class)
                ,
                  @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ORA_ERROR", type = String.class)        
    })
})

//@IdClass(ImprimeReciboPK.class)
public class Log implements Serializable{

    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
  
}

