/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.repository;

/**
 *
 * @author Gamaliel Espinoza M.
 */
public interface BitacoreRepository {
    public boolean registerSafe(
            Integer idRequisicion,
            Integer idPartida,
            Integer idComentarioRegresado,
            String idRequisitor,
            Integer idAccion,
            Integer idEstatus,
            String idComprador,
            Integer idRol
    );
}
