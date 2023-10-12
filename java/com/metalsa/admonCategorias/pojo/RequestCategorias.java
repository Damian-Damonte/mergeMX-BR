/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.admonCategorias.pojo;

import java.util.List;

/**
 *
 * @author apojm20015
 * @date 8-Ago-2019
 */
public class RequestCategorias {
    private List<ConfFamPojo> confFamPojoList;
    private String idUsuario;

    /**
     * @return the confFamPojoList
     */
    public List<ConfFamPojo> getConfFamPojoList() {
        return confFamPojoList;
    }

    /**
     * @param confFamPojoList the confFamPojoList to set
     */
    public void setConfFamPojoList(List<ConfFamPojo> confFamPojoList) {
        this.confFamPojoList = confFamPojoList;
    }

    /**
     * @return the idUsuario
     */
    public String getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
    
}
