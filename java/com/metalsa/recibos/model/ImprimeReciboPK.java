/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author edgar.leal
 */
public class ImprimeReciboPK implements Serializable{
    private static final long serialVersionUID = 1L;

    private Integer folio;

    private Integer idRequisicion;
    
    private Integer idPartida;

    public Integer getFolio() {
        return folio;
    }

    public void setFolio(Integer folio) {
        this.folio = folio;
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
    
    

    

    public ImprimeReciboPK() {
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.folio);
        hash = 97 * hash + Objects.hashCode(this.idRequisicion);
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
        final ImprimeReciboPK other = (ImprimeReciboPK) obj;
        if (!Objects.equals(this.folio, other.folio)) {
            return false;
        }
        if (!Objects.equals(this.idRequisicion, other.idRequisicion)) {
            return false;
        }
        return Objects.equals(this.idPartida, other.idPartida);
    }

    
    
    
    
}
