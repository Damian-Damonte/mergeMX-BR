package com.metalsa.aprobacion.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import lombok.EqualsAndHashCode;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
@Entity(name = "nvc_v_presupuesto_proyecto")
@IdClass(ProjectBudget.Pk.class)
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "ProjectBudget.getBudgetSimplified",
                query = "select :prj proyecto, :task tarea, :type tipo_gasto, " +
                        "   nvc_pkg_spx_budget.project_budget(:prj, :task, :type) available " +
                        "from dual",
                resultClass = ProjectBudgetSemaphore.class
        )
})
@Data
@EqualsAndHashCode
public class ProjectBudget extends Budget {
    @Id
    @Column(name = "id_uen")
    private Long uen;

    @Id
    @Column(name = "id_proyecto")
    private Long proyecto;

    @Column(name = "proyecto")
    private String nombreProyecto;

    @Id
    @Column(name = "id_tarea")
    private Long tarea;

    @Column(name = "tarea")
    private String nombreTarea;

    @Id
    @Column(name = "id_tipo_gasto")
    private Long idTipoGasto;

    private String tipoGasto;

    @Column(name = "budget")
    private Double saldoInicial;

    @Column(name = "actual")
    private Double saldoActual;

    @Column(name = "encumbrances")
    private Double saldoComprometido;

    @Column(name = "available")
    private Double saldoDisponible;

    /**
     * primary key
     */
    @Data
    public static class Pk implements Serializable {
        @Column(name = "id_uen")
        private Long uen;

        @Column(name = "id_proyecto")
        private Long proyecto;

        @Column(name = "id_tarea")
        private Long tarea;

        @Column(name = "id_tipo_gasto")
        private Long idTipoGasto;
    }
}
