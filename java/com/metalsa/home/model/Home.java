package com.metalsa.home.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;

/**
 *
 * @author edgar.leal
 */
@Entity
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(name = "CLEAR_USER_WIDGETS",
            procedureName = "NVC_PKG_HOME_SPX.CLEAR_USER_WIDGETS",
            parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ID_USUARIO", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "P_OUT", type = String.class)
                
    }),
    
    @NamedStoredProcedureQuery(name = "INSERT_USER_WIDGET",
            procedureName = "NVC_PKG_HOME_SPX.INSERT_USER_WIDGET",
            parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ID_WIDGET", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ID_USUARIO", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_POS_X", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_POS_Y", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_WIDTH", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_HEIGHT", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_FIJO", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "P_OUT", type = String.class)
                
    }) 
})
public class Home implements Serializable {

    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Home() {
    }

}
