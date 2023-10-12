/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author jose.rivera02
 */
@Entity
@IdClass(Uens.class)
@XmlRootElement
@NamedStoredProcedureQueries({
     @NamedStoredProcedureQuery(name = "obtenerUensUsuario", procedureName = "NVC_PKG_APROBACION_SPX.GET_UENS_BY_USER_REST",
                        resultClasses = Uens.class, parameters = { //
                         @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ID_USER", type = String.class)
                        ,@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "CURSOR_OUT", type = void.class) 
                 })
})

public class Uens implements Serializable{
    private final static long serialVersionUID = 1L;
    
    @Id
    @NotNull
    @Column(name = "ID_UEN")
    private Integer idUen;
    
    @Column(name = "UEN_DEFAULT")
    private String uenDefault;
    
    @Column(name = "NOMBRE_UEN")
    private String nombreUen;
    
    @Basic(optional = false)
    @Column(name = "DESC_UEN")
    private String descUen;
    
    @Basic(optional = false)
    @Column(name = "CURRENCY_CODE")
    private String currencyCode;
    
    @Column(name = "INTERUEN")
    private Integer interUen;

    //<R16788>
    public Uens() {
    }
    //</R16788>
    
    public Uens(Integer idUen, String nombreUen) {
        this.idUen = idUen;
        this.nombreUen = nombreUen;
    }
    
    //<R16788>
    @Override
    public int hashCode() {
        Integer hash = 7;
        hash = 67 * hash + Objects.hashCode(this.idUen);
        hash = 67 * hash + Objects.hashCode(this.uenDefault);
        hash = 67 * hash + Objects.hashCode(this.nombreUen);
        hash = 67 * hash + Objects.hashCode(this.descUen);
        hash = 67 * hash + Objects.hashCode(this.currencyCode);
        hash = 67 * hash + Objects.hashCode(this.interUen);
       
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
     * @return the idUen
     */
    public Integer getIdUen() {
        return idUen;
    }

    /**
     * @param idUen the idUen to set
     */
    public void setIdUen(Integer idUen) {
        this.idUen = idUen;
    }

    /**
     * @return the uenDefault
     */
    public String getUenDefault() {
        return uenDefault;
    }

    /**
     * @param uenDefault the uenDefault to set
     */
    public void setUenDefault(String uenDefault) {
        this.uenDefault = uenDefault;
    }

    /**
     * @return the nombreUen
     */
    public String getNombreUen() {
        return nombreUen;
    }

    /**
     * @param nombreUen the nombreUen to set
     */
    public void setNombreUen(String nombreUen) {
        this.nombreUen = nombreUen;
    }

    /**
     * @return the descUen
     */
    public String getDescUen() {
        return descUen;
    }

    /**
     * @param descUen the descUen to set
     */
    public void setDescUen(String descUen) {
        this.descUen = descUen;
    }

    /**
     * @return the currencyCode
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * @param currencyCode the currencyCode to set
     */
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    /**
     * @return the interUen
     */
    public Integer getInterUen() {
        return interUen;
    }

    /**
     * @param interUen the interUen to set
     */
    public void setInterUen(Integer interUen) {
        this.interUen = interUen;
    }

    
}
