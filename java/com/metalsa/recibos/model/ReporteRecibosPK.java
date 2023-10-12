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
public class ReporteRecibosPK implements Serializable{
    @Column(name="ID_REQUISICION")
    private Integer idRequisicion;

    @Column(name="ID_PARTIDA")
    private Integer idPartida;
    
    @Column(name = "FOLIO")
    private Double folio;

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

    public Double getFolio() {
        return folio;
    }

    public void setFolio(Double folio) {
        this.folio = folio;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.idRequisicion);
        hash = 71 * hash + Objects.hashCode(this.idPartida);
        hash = 71 * hash + Objects.hashCode(this.folio);
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
        final ReporteRecibosPK other = (ReporteRecibosPK) obj;
        if (!Objects.equals(this.idRequisicion, other.idRequisicion)) {
            return false;
        }
        if (!Objects.equals(this.idPartida, other.idPartida)) {
            return false;
        }
        return Objects.equals(this.folio, other.folio);
    }
    
    
    
}
