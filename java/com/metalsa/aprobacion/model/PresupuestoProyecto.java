package com.metalsa.aprobacion.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
@Data
@EqualsAndHashCode(of = {"proyecto", "tarea", "idTipoGasto"}, callSuper = false)
@NoArgsConstructor
public class PresupuestoProyecto extends Presupuesto {
    private Long proyecto;
    private String nombreProyecto;
    private Long tarea;
    private String nombreTarea;
    private Long idTipoGasto;
    private String tipoGasto;
    private String nombreUen;
    
    public PresupuestoProyecto(Double disponible, Double inicial, Double actual, Double comprometido,
                               Long proyecto, String nombreProyecto, Long tarea, String nombreTarea,
                               Long idTipoGasto, String tipoGasto) {
        super(disponible, inicial, actual, comprometido);
        this.proyecto = proyecto;
        this.nombreProyecto = nombreProyecto;
        this.tarea = tarea;
        this.nombreTarea = nombreTarea;
        this.idTipoGasto = idTipoGasto;
        this.tipoGasto = tipoGasto;
    }

    public static PresupuestoProyecto of(ProjectBudget budget) {
        return new PresupuestoProyecto(budget.getSaldoDisponible(), budget.getSaldoInicial(),
                budget.getSaldoActual(), budget.getSaldoComprometido(),
                budget.getProyecto(), budget.getNombreProyecto(), budget.getTarea(), budget.getNombreTarea(),
                budget.getIdTipoGasto(), budget.getTipoGasto());
    }

    public static PresupuestoProyecto of(ProjectBudgetSemaphore budget) {
        return new PresupuestoProyecto(budget.getAvailable(), null,
                null, null, budget.getProyecto(), null,
                budget.getTarea(), null, null, budget.getTipoGasto());
    }
}
