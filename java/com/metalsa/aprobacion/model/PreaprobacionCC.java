package com.metalsa.aprobacion.model;

import lombok.Data;

/**
 *
 * @author Oscar del Angel
 */
@Data
public class PreaprobacionCC {

    private Integer idRequisicion;
    private Integer idPartida;
    private Integer idFamilia;
    private Integer idCuenta;
    private String usuario;


    @Override
    public String toString() {
        return "PreaprobacionCC{" + "idRequisicion=" + idRequisicion + ", idPartida=" + idPartida + ", idCuenta=" + idCuenta + ", idFamilia=" + idFamilia +" ,usuario="+usuario+ '}';
    }

}
