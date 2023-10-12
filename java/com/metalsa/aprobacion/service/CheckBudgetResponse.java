package com.metalsa.aprobacion.service;

import com.metalsa.aprobacion.model.Presupuesto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
@Data
@AllArgsConstructor
public class CheckBudgetResponse {
    private List<ApprovedItem> items;
    private List<Presupuesto> presupuestos;
}
