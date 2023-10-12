/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.contralor.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lorena
 */
public class ReponseProcesos {
   private String message="";
   private List Data= new ArrayList<>();
   private String type="";

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the Data
     */
    public List getData() {
        return Data;
    }

    /**
     * @param Data the Data to set
     */
    public void setData(List Data) {
        this.Data = Data;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
}
