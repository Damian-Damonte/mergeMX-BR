package com.metalsa.aprobacion.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
@Data
@AllArgsConstructor
public class ApprovalResponse {
    private Long requisicion;
    private Long linea;
    private boolean aprobado;
    private String mensaje;
    private int result;
}
