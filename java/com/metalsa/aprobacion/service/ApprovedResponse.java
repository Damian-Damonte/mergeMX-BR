package com.metalsa.aprobacion.service;

import com.metalsa.aprobacion.model.Presupuesto;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
@Data
@AllArgsConstructor
public class ApprovedResponse {
    private Iterable<ApprovedItem> items;
    private Iterable<Presupuesto> presupuestos;
}
