/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.reportes.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
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
public class Meses implements Serializable {

    @Id
    protected Long id;
    protected double enero;
    protected double febrero;
    protected double marzo;
    protected double abril;
    protected double mayo;
    protected double junio;
    protected double julio;
    protected double agosto;
    protected double septiembre;
    protected double octubre;
    protected double noviembre;
    protected double diciembre;

    public Meses() {
    }

}
