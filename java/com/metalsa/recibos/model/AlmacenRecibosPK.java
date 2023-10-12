/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;

/**
 *
 * @author yair.nunez
 */
public class AlmacenRecibosPK implements Serializable{
    
    @Column(name="ID_REQUISICION")
    private Integer idRequisicion;

    @Column(name="ID_PARTIDA")
    private Integer idPartida;
    
    @Column(name="NUMERO_RECIBO")
    private Integer numeroRecibo;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.idRequisicion);
        hash = 37 * hash + Objects.hashCode(this.idPartida);
        hash = 37 * hash + Objects.hashCode(this.numeroRecibo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AlmacenRecibosPK other = (AlmacenRecibosPK) obj;
        if (!Objects.equals(this.idRequisicion, other.idRequisicion)) {
            return false;
        }
        if (!Objects.equals(this.idPartida, other.idPartida)) {
            return false;
        }
        return Objects.equals(this.numeroRecibo, other.numeroRecibo);
    }

    /**
     * @return the idRequisicion
     */
    public Integer getIdRequisicion() {
        return idRequisicion;
    }

    /**
     * @param idRequisicion the idRequisicion to set
     */
    public void setIdRequisicion(Integer idRequisicion) {
        this.idRequisicion = idRequisicion;
    }

    /**
     * @return the idPartida
     */
    public Integer getIdPartida() {
        return idPartida;
    }

    /**
     * @param idPartida the idPartida to set
     */
    public void setIdPartida(Integer idPartida) {
        this.idPartida = idPartida;
    }

    public Integer getNumeroRecibo() {
        return numeroRecibo;
    }

    public void setNumeroRecibo(Integer numeroRecibo) {
        this.numeroRecibo = numeroRecibo;
    }
    
    
}
