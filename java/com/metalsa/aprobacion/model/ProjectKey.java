/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.aprobacion.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 *
 * @author jose.rivera02
 */
@AllArgsConstructor
@EqualsAndHashCode(of = {"proyecto", "tarea", "tipoGasto"})
@Getter
public class ProjectKey {

    private Long proyecto;
    private Long tarea;
    private String tipoGasto;
    private Long cuenta;

}

