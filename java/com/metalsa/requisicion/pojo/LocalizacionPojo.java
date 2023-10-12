/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.requisicion.pojo;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author jose.rivera02
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class LocalizacionPojo implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String direccion;

    public LocalizacionPojo() {
    }

    public LocalizacionPojo(Integer id, String direccion) {
        super();
        this.id = id;
        this.direccion = direccion;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
