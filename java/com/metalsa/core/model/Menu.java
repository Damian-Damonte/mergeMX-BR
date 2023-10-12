/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jose.rivera02
 */
@Entity
@IdClass(Menu.class)
@XmlRootElement
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(name = "obtenerMenu",
            procedureName = "NVC_PKG_APROBACION_SPX.GET_MENU",
            parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_USER_ID", type = String.class)
                ,
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_IDIOMA", type = String.class)
                ,
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "P_SALIDA", type = String.class)
            }),
        
    @NamedStoredProcedureQuery(name = "obtenerRequisRegresadas",
            procedureName = "NVC_SPX_REQUISICIO_PKG.GET_TOTAL_REQUIS_REGRESADAS",
            parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_requisitor", type = String.class)
                ,
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_out_total", type = Integer.class)
            })
})

public class Menu implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    private Integer ID_MENU;
    private String descripcion;
    private String url;
    private String idRol;
    
    
    @Override
    public int hashCode() {
        Integer hash = 7;
         hash = 67 * hash + Objects.hashCode(this.ID_MENU);
        hash = 67 * hash + Objects.hashCode(this.descripcion);
        hash = 67 * hash + Objects.hashCode(this.url);
        hash = 67 * hash + Objects.hashCode(this.idRol);
       
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

    /**
     * @return the ID_MENU
     */
    public Integer getID_MENU() {
        return ID_MENU;
    }

    /**
     * @param ID_MENU the ID_MENU to set
     */
    public void setID_MENU(Integer ID_MENU) {
        this.ID_MENU = ID_MENU;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the idRol
     */
    public String getIdRol() {
        return idRol;
    }

    /**
     * @param idRol the idRol to set
     */
    public void setIdRol(String idRol) {
        this.idRol = idRol;
    }
    
    
}
