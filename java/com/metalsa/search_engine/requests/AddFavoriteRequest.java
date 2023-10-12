/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.search_engine.requests;

/**
 *
 * @author jose.espindola03
 */
public class AddFavoriteRequest {
    AddFavoriteRequest(){}
            
    private Integer idItem;    

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }
        
    private Integer idUen;        

    public void setIdUen(Integer idUen) {
        this.idUen = idUen;
    }
    
    private Integer idTipoItem;     
    
    
    public void setIdTipoItem(Integer idTipoItem) {
        this.idTipoItem = idTipoItem;
    }

    public Integer getIdItem() {
        return idItem;
    }
    
    public Integer getIdUen() {
        return idUen;
    }

    public Integer getIdTipoItem() {
        return idTipoItem;
    }
}
