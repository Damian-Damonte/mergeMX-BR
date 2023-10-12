/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.pojo;

/**
 *
 * @author yair.nunez
 */
public class FolioCorreo {
    private Integer idRequisicion;
    private Integer idPartida;
    private Integer idFolio;

    public FolioCorreo() {
    }

    public Integer getIdRequisicion() {
        return idRequisicion;
    }

    public void setIdRequisicion(Integer idRequisicion) {
        this.idRequisicion = idRequisicion;
    }

    public Integer getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(Integer idPartida) {
        this.idPartida = idPartida;
    }

    public Integer getIdFolio() {
        return idFolio;
    }

    public void setIdFolio(Integer idFolio) {
        this.idFolio = idFolio;
    }

    @Override
    public String toString() {
        return "FolioCorreo{" + "idRequisicion=" + idRequisicion + ", idPartida=" + idPartida + ", idFolio=" + idFolio + '}';
    }
    
    
    
}
