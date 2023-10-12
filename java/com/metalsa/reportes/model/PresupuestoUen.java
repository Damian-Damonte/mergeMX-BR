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
public class PresupuestoUen implements Serializable{

    private String nombreUen;
    
    private Presupuesto totales;
    
    private List<PresupuestoUenLinea> lineas;
    
    private List<PresupuestoUen> presupuestos;

    public PresupuestoUen() {
    }

    public PresupuestoUen(String nombreUen,  List<PresupuestoUenLinea> lineas) {
        this.nombreUen = nombreUen;
        this.lineas = lineas;
    }
    
}
