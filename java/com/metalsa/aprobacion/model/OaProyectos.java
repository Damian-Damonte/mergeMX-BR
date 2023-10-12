/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.aprobacion.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 *
 * @author APOOD9272
 */
@Entity(name = "nvc_vm_oa_proyectos")
@lombok.Data
public class OaProyectos implements Serializable{
   
    @Id
    @Column(name="id_proyecto")
    @JsonProperty("id_proyecto")
    private Long idProyecto;
    
    @Column(name="id_uen")
    private Integer idUen;
    
    @JsonProperty("cod_proyecto")
    private String codProyecto;

    @JsonProperty("nombre_proyecto")
    private String nombreProyecto;
    
    @JsonProperty("start_date")
    private String startDate;
    
    @JsonProperty("completion_date")
    private String completionDate;

    @JsonProperty("pre_aprobador")
    @Transient
    private String preAprobador;
    
}
