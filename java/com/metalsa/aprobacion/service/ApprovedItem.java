package com.metalsa.aprobacion.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApprovedItem {
    private Long cuenta;
    private boolean aprobado;
    private boolean exceso;
}
