package com.metalsa.requisicion.pojo;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author APOMS7376
 */
public class NvcTblParameterUenPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idParameterUen;
    private Integer idParameter;
    private Integer idUen;
    private String parameterCondition;
    private List<NvcTblParameterValuePojo> parameterValuePojos;

    public Integer getIdParameterUen() {
        return idParameterUen;
    }

    public void setIdParameterUen(Integer idParameterUen) {
        this.idParameterUen = idParameterUen;
    }

    public Integer getIdParameter() {
        return idParameter;
    }

    public void setIdParameter(Integer idParameter) {
        this.idParameter = idParameter;
    }

    public Integer getIdUen() {
        return idUen;
    }

    public void setIdUen(Integer idUen) {
        this.idUen = idUen;
    }

    public List<NvcTblParameterValuePojo> getParameterValuePojos() {
        return parameterValuePojos;
    }

    public void setParameterValuePojos(List<NvcTblParameterValuePojo> parameterValuePojos) {
        this.parameterValuePojos = parameterValuePojos;
    }

    public String getParameterCondition() {
        return parameterCondition;
    }

    public void setParameterCondition(String parameterCondition) {
        this.parameterCondition = parameterCondition;
    }  
}