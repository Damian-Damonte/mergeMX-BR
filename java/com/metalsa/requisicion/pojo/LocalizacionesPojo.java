/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.requisicion.pojo;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jose.rivera02
 */
@XmlRootElement(name = "LocalizacionesPojo")
@XmlAccessorType(XmlAccessType.FIELD)
public class LocalizacionesPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement(name = "LocalizacionPojo")
    private List<LocalizacionPojo> localizacionPojo;

    public LocalizacionesPojo() {
    }

    public LocalizacionesPojo(List<LocalizacionPojo> localizacionPojo) {
        super();
        this.localizacionPojo = localizacionPojo;
    }

    /**
     * @return the localizacionPojo
     */
    public List<LocalizacionPojo> getLocalizacionPojo() {
        return localizacionPojo;
    }

    /**
     * @param localizacionPojo the localizacionPojo to set
     */
    public void setLocalizacionPojo(List<LocalizacionPojo> localizacionPojo) {
        this.localizacionPojo = localizacionPojo;
    }

}
