package com.metalsa.requisicion.pojo;

import java.io.Serializable;

/**
 *
 * @author omar.estrella
 */
public class NvcTblCatalogoMesPojo implements Serializable{
    private Integer idMes;
    private String  descripcion;

    public NvcTblCatalogoMesPojo() {
    }
    
    public NvcTblCatalogoMesPojo(Integer idMes, String descripcion) {
        this.idMes = idMes;
        this.descripcion = descripcion;
    }

    public Integer getIdMes() {
        return idMes;
    }

    public void setIdMes(Integer idMes) {
        this.idMes = idMes;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
