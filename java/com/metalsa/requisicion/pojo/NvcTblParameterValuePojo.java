package com.metalsa.requisicion.pojo;

import java.io.Serializable;

/**
 *
 * @author APOMS7376
 */
public class NvcTblParameterValuePojo implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idParameterValue;
    private Integer idParameterUen;
    private String parameterValue;
    private String parameterProperty;
    private String parameterCondition;
    private String parameterAttribute3;
    private String parameterAttribute4;
    private String parameterAttribute5;

    public Integer getIdParameterValue() {
        return idParameterValue;
    }

    public void setIdParameterValue(Integer idParameterValue) {
        this.idParameterValue = idParameterValue;
    }

    public Integer getIdParameterUen() {
        return idParameterUen;
    }

    public void setIdParameterUen(Integer idParameterUen) {
        this.idParameterUen = idParameterUen;
    }

    public String getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }

    public String getParameterCondition() {
        return parameterCondition;
    }

    public void setParameterCondition(String parameterCondition) {
        this.parameterCondition = parameterCondition;
    }

    public String getParameterProperty() {
        return parameterProperty;
    }

    public void setParameterProperty(String parameterProperty) {
        this.parameterProperty = parameterProperty;
    }

    public String getParameterAttribute3() {
        return parameterAttribute3;
    }

    public void setParameterAttribute3(String parameterAttribute3) {
        this.parameterAttribute3 = parameterAttribute3;
    }

    public String getParameterAttribute4() {
        return parameterAttribute4;
    }

    public void setParameterAttribute4(String parameterAttribute4) {
        this.parameterAttribute4 = parameterAttribute4;
    }

    public String getParameterAttribute5() {
        return parameterAttribute5;
    }

    public void setParameterAttribute5(String parameterAttribute5) {
        this.parameterAttribute5 = parameterAttribute5;
    }

}
