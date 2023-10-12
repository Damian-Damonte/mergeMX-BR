package com.metalsa.reportes.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Oscar del Angel
 */
@Getter
@Setter
@Entity
@EqualsAndHashCode(of = {"id"})
public class Presupuesto implements Serializable {

    @Id
    protected Long id;

    protected double original;

    protected double disponible;

    protected double operativo;

    protected double comprometido;

    protected double erogado;

    protected double transferencias;

    protected double incrementosDecrementos;

    @Transient
    protected double diferencia;

    @Transient
    protected double diferenciaPorcentual;

    public double getDiferencia() {
        this.diferencia = this.operativo - this.original;
        return diferencia;
    }

    public double getDiferenciaPorcentual() {
        if (this.original > 0) {
            this.diferenciaPorcentual = (this.getDiferencia() / this.original) * 100;
        }
        return diferenciaPorcentual;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("original:").append(original).append(" ") 
        .append("disponible:").append(disponible).append(" ") 
        .append("operativo:").append(operativo).append(" ") 
        .append("comprometido:").append(comprometido).append(" ") 
        .append("erogado:").append(erogado).append(" ") 
        .append("transferencias:").append(transferencias).append(" ") 
        .append("incrementosDecrementos:").append(incrementosDecrementos).append(" ") 
                ;
        return sb.toString(); //To change body of generated methods, choose Tools | Templates.
    }
}
