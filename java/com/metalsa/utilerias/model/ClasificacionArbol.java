/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.utilerias.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import lombok.Data;

/**
 *
 * @author jose.jimenez07
 */
@Entity
@Data
public class ClasificacionArbol implements Serializable {

    private Long idCategoria;
    private Long idFamilia;
    @Id
    private Long idSubFamilia;
    @Transient
    private String palabra;

}
