package com.metalsa.aprobacion.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
@Entity
@Data
public class BudgetHistory {
    @Id
    private Long numeroPeriodo;
    private String periodo;
    private Double inicial;
    private Double actual;
    private Double comprometido;
    private Double disponible;
}
