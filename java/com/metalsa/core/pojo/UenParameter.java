/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.pojo;

import java.util.List;

/**
 *
 * @author jose.espindola03
 */
public class UenParameter {
    private Integer idUen;
    private Integer idParameterUen;
    private String condition;
    private List<UenParameterValue> values;
    
    public void setValues(List<UenParameterValue> values) {
        this.values = values;
    }
    
    public void setIdUen(Integer id) {
        this.idUen = id;
    }
    
    public void setIdParameterUen(Integer idParameterUen) {
        this.idParameterUen = idParameterUen;
    }
    
    public void setCondition(String condition) {
        this.condition = condition;
    }
    
    public List<UenParameterValue> getValues() {
        return this.values;
    }
    
    public String getrCondition() {
        return this.condition;
    }
    
    public boolean isCondition(String value) {
        return this.condition != null && this.condition.equals(value);
    }
}
