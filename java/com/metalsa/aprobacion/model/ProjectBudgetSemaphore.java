package com.metalsa.aprobacion.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
@Entity
@IdClass(ProjectBudgetSemaphore.Pk.class)
@Data
public class ProjectBudgetSemaphore {

    @Id
    private Long proyecto;

    @Id
    private Long tarea;

    @Id
    private String tipoGasto;

    private Double available;

    /**
     * Pk
     */
    @Data
    public static class Pk implements Serializable {
        @Id
        private Long proyecto;

        @Id
        private Long tarea;

        @Id
        private String tipoGasto;
    }

}
