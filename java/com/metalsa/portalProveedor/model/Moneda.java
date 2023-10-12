/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.portalProveedor.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

/**
 *
 * @author mlopez
 */

@Data
@Entity(name = "NVC_TBL_MONEDAS_H")
public class Moneda implements Serializable{
    
    @Id
    private String id_moneda;
    private String moneda;
    
    
    public Moneda(String id_moneda, String moneda) {
        this.id_moneda = id_moneda;
        this.moneda = moneda;
    }
    
    public Moneda(){
        
    }
}
