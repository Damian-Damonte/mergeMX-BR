
package com.metalsa.reportes.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Oscar del Angel
 */

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Variacion extends CCHeader implements Serializable {
    
    private List<VariacionLinea> lineas;
    
    private Presupuesto  total;

}
