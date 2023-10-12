/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author yair.nunez
 */
@Entity
@Table(name = "nvc_tbl_udm")
@XmlRootElement

@NamedNativeQuery(name = "Udm.findUdmByLanguage",
    query="select * from nvc_tbl_udm where lenguaje = ?1",
    resultClass = Udm.class
)
@NamedQueries({
    @NamedQuery(name="Udm.getByIdUnidadMedida", query="SELECT u FROM Udm u WHERE udm=:idUnidadDeMedida"),
})
public class Udm implements Serializable {
    
    @Id
    @NotNull
    @Column(name = "ID_UDM")
    private Integer idUdm;
    
    @Column(name = "ID_UNIDAD_DE_MEDIDA")
    private String udm;
    
    @Column(name = "UNIDAD_DE_MEDIDA")
    private String udmDesc;
    
    @Column(name = "LENGUAJE")
    private String idioma;
    
    @Override
    public int hashCode() {
        Integer hash = 7;
        hash = 67 * hash + Objects.hashCode(this.idUdm);
        hash = 67 * hash + Objects.hashCode(this.udm);
        hash = 67 * hash + Objects.hashCode(this.udmDesc);
        hash = 67 * hash + Objects.hashCode(this.idioma);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        return getClass() == obj.getClass();
    }


    public Integer getIdUdm() {
        return idUdm;
    }

    public void setIdUdm(Integer idUdm) {
        this.idUdm = idUdm;
    }

    public String getUdm() {
        return udm;
    }

    public void setUdm(String udm) {
        this.udm = udm;
    }

    public String getUdmDesc() {
        return udmDesc;
    }

    public void setUdmDesc(String udmDesc) {
        this.udmDesc = udmDesc;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
    
    
}
