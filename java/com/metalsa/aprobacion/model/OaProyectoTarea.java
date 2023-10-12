package com.metalsa.aprobacion.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author APOOD9272
 */
public class OaProyectoTarea {

    @JsonProperty("id_proyecto")
    private Long idProyecto;

    @JsonProperty("id_tarea")
    private Long idTarea;

    @JsonProperty("cod_tarea")
    private String codTarea;
    
    @JsonProperty("nombre_tarea")
    private String nombreTarea;
    
    @JsonProperty("desc_tarea")
    private String descTarea;

    public Long getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Long idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Long getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Long idTarea) {
        this.idTarea = idTarea;
    }

    public String getCodTarea() {
        return codTarea;
    }

    public void setCodTarea(String codTarea) {
        this.codTarea = codTarea;
    }

    public String getNombreTarea() {
        return nombreTarea;
    }

    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    public String getDescTarea() {
        return descTarea;
    }

    public void setDescTarea(String descTarea) {
        this.descTarea = descTarea;
    }


}
