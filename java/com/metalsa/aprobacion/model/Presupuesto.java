package com.metalsa.aprobacion.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Presupuesto {
    private Double disponible;
    private Double inicial;
    private Double actual;
    private Double comprometido;
}

