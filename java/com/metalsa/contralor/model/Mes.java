package com.metalsa.contralor.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Oscar del Angel
 */



@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "NVC_TBL_CATALOGO_MESES")
public class Mes implements Serializable{
    
    @Id
    @Column(name="ID_CATALOGO_MESES")
    private Long id;
    
    @Column(name="id_mes")
    private Long numMes;
    
    @Column(name="mes")
    private String nombre;
    
    @Column(name="abreviacion")
    private String abreviacion;
    
    @Column(name="lenguaje")
    private String idioma;
    
     
    
}
