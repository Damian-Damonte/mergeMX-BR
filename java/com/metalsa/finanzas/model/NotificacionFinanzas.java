package com.metalsa.finanzas.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import static javax.persistence.ParameterMode.IN;
import static javax.persistence.ParameterMode.OUT;
import javax.persistence.StoredProcedureParameter;

/**
 *
 * @author JL
 */
@Entity
@NamedStoredProcedureQueries({

    @NamedStoredProcedureQuery(
            name = "enviar_notificacion",
            procedureName = "nvc_pkg_request_manager_spx.NOTIFICACION_SOLICITUD",
            parameters = {

                        @StoredProcedureParameter(mode = IN, name = "P_IDS", type = String.class),
                        @StoredProcedureParameter(mode = IN, name = "P_IDIOMA", type = String.class),
                        @StoredProcedureParameter(mode = IN, name = "P_TIPO_CORREO", type = String.class),
                        @StoredProcedureParameter(mode = IN, name = "P_ETAPA", type = String.class),
                        @StoredProcedureParameter(mode = OUT, name = "P_MESSAGE_OUT", type = String.class),
                        @StoredProcedureParameter(mode = OUT, name = "P_VALUE_OUT", type = Integer.class),
            })
})
public class NotificacionFinanzas implements Serializable {

    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
