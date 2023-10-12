
package com.metalsa.aprobacion.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;

/**
 *
 * @author APOOD9272
 */
public class ReqPorAprobarPK implements Serializable{

    @Column(name="id_requisicion")
    private Integer idRequisicion;

    @Column(name="id_partida")
    private Integer idPartida;

    
    
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.idRequisicion);
        hash = 37 * hash + Objects.hashCode(this.idPartida);
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
        final ReqPorAprobarPK other = (ReqPorAprobarPK) obj;
        if (!Objects.equals(this.idRequisicion, other.idRequisicion)) {
            return false;
        }
        return Objects.equals(this.idPartida, other.idPartida);
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

    

    
}
