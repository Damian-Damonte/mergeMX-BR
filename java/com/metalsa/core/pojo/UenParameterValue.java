/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.pojo;

/**
 *
 * @author jose.espindola03
 */
public class UenParameterValue {
    private Integer idValue;
    private Integer idUen;
    private String value;
    private String property;
    private String condition;
    
    public void setIdValue(Integer idValue) {
        this.idValue = idValue;
    }
    
    public void setIdUen(Integer idParameterUen) {
        this.idUen = idParameterUen;
    }
    
    public void setValue(String parameterValue) {
        this.value = parameterValue;
    }
    
    public void setProperty(String property) {
        this.property = property;
    }
    
    public void setParameterCondition(String parameterCondition) {
        this.condition = parameterCondition;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public String getCondition() {
        return this.condition;
    }
    
    public String getProperty() {
        return this.property;
    }
    
    public boolean isCondition(String condition) {
        return this.condition != null && this.condition.equals(condition);
    }
}
