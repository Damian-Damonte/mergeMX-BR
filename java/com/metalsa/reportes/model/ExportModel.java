package com.metalsa.reportes.model;

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
public class ExportModel implements Serializable{

    private String reporte;
    private String tipo;
    private Integer vista; //{1: categoria, 2: centro costo}
    private List<String> uens;
    private List<String> ccs;
    private String periodoInicial;
    private String periodoFinal;
    private String idioma;
    
    private List<Balanza> balanza;
    private List<PresupuestoAnual> presupuestoAnual;
    private PresupuestoUen presupuestoUenCC;
    private PresupuestoUen presupuestoUenCat;
    
    private List<Variacion> variaciones;
    private List<EstadoCuenta> gastos;
    private List<EstadoCuentaProject> edoCuentaProject;
    private Presupuesto totalesProy;
    private List<GastosProject> gastosProject;
    private List<Transferencias> transferencias;
    private List<Incrementos> movimientos;
    
}
