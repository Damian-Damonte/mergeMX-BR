/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.requisicion.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Basic;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Gamaliel Espinoza M.
 */
@Entity
@Table(name = "LINEAS_CONTROLADAS", catalog = "")
public class LineaControlada {
    @Id
    @GeneratedValue(generator="lineaControladaSecuencia") 
    @SequenceGenerator(
            name="lineaControladaSecuencia",
            sequenceName="CONTROLADO_SEQ",
            allocationSize=1
    )
    @Column(name = "ID_REGISTRO")
    public Long id;
    
    @Column(name = "ID_REQUISICION")
    public Integer idRequisicion;
    
    @Column(name = "ID_CARRO_COMPRA")
    public Integer idCarroCompra;
    
    @Column(name = "ID_LINEA")
    public Integer idLinea;
    
    @Column(name = "ID_MOTIVO_SOL")
    public Integer idMotivoSol;
    
    @Column(name = "ID_PERSON")
    public Integer idPerson;
    
    public Long getId() {
        return this.id;
    }
    
    public Integer getIdRequisicion() {
        return this.idRequisicion;
    }
    
    public void setIdRequisicion(Integer id) {
        this.idRequisicion = id;
    }
    
    public Integer getIdCarroCompra() {
        return this.idCarroCompra;
    }
    
    public void setIdCarroCompra(Integer id) {
        this.idCarroCompra = id;
    }
    
    public Integer getIdLinea() {
        return this.idLinea;
    }
    
    public void setIdLinea(Integer id) {
        this.idLinea = id;
    }
    
    public Integer getIdMotivoSol() {
        return this.idMotivoSol;
    }
    
     public void setIdMotivoSol(Integer id) {
         this.idMotivoSol = id;
     }
     
     public Integer getIdPerson() {
         return this.idPerson;
     }
     
     public void setIdPerson(Integer id) {
         this.idPerson = id;
     }
}
