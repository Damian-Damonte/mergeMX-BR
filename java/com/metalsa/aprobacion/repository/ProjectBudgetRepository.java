package com.metalsa.aprobacion.repository;

import com.metalsa.aprobacion.model.ProjectBudget;
import com.metalsa.aprobacion.model.ProjectBudgetSemaphore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
public interface ProjectBudgetRepository extends JpaRepository<ProjectBudget, ProjectBudget.Pk> {

    List<ProjectBudget> findAllByUenAndProyectoOrderByTareaAscTipoGastoAsc(Long uen, Long proyecto);

    ProjectBudget getByProyectoAndTareaAndTipoGasto(Long proyecto, Long tarea, String tipoGasto);

    ProjectBudgetSemaphore getBudgetSimplified(
            @Param("prj") Long proyecto,
            @Param("task") Long tarea,
            @Param("type") String tipoGasto);
}
