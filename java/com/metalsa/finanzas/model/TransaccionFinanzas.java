package com.metalsa.finanzas.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import static javax.persistence.ParameterMode.IN;
import static javax.persistence.ParameterMode.OUT;
import javax.persistence.StoredProcedureParameter;
import lombok.Data;

/**
 *
 * @author JL
 */
@Entity
@Data
@NamedStoredProcedureQueries({
@NamedStoredProcedureQuery(//MDA_REPORTES_FINANZAS
    name = "modificar_presupuesto",
    procedureName = "nvc_pkg_spx_budget.alter_budget_finanza",
    parameters = {
        @StoredProcedureParameter(mode = IN, name = "p_id_uen", type = Long.class),
        @StoredProcedureParameter(mode = IN, name = "p_str_ids_ccs", type = String.class),
        @StoredProcedureParameter(mode = IN, name = "p_str_ids_categorias", type = String.class),
        @StoredProcedureParameter(mode = IN, name = "p_periodo", type = String.class),
        @StoredProcedureParameter(mode = IN, name = "p_tipo_transaccion", type = String.class),
        @StoredProcedureParameter(mode = IN, name = "p_cantidad_pct", type = Double.class),
        @StoredProcedureParameter(mode = IN, name = "p_cantidad_pesos", type = Double.class),
        @StoredProcedureParameter(mode = IN, name = "p_moneda", type = String.class),
        @StoredProcedureParameter(mode = IN, name = "p_requester", type = String.class),
        @StoredProcedureParameter(mode = IN, name = "p_aprobador", type = String.class),
        @StoredProcedureParameter(mode = IN, name = "p_razon", type = String.class),
        @StoredProcedureParameter(mode = IN, name = "p_id_usuario", type = String.class),
        @StoredProcedureParameter(mode = IN, name = "p_id_transaccion", type = Long.class),
        @StoredProcedureParameter(mode = OUT, name = "out_message", type = String.class),
        @StoredProcedureParameter(mode = OUT, name = "out_value", type = Integer.class)
    })
})
public class TransaccionFinanzas implements Serializable {//MDA_REPORTES_FINANZAS
    
    @Id
    private Long id_transaccion;
    
}
