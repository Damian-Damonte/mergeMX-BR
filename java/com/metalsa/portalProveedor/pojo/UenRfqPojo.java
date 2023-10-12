/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.portalProveedor.pojo;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author mlopez
 */
public class UenRfqPojo {
    private BigDecimal idUen;
    private String nombreUen;
    private List<RfqPojo> rfqs;

    public BigDecimal getIdUen() {
        return idUen;
    }

    public void setIdUen(BigDecimal idUen) {
        this.idUen = idUen;
    }

    public String getNombreUen() {
        return nombreUen;
    }

    public void setNombreUen(String nombreUen) {
        this.nombreUen = nombreUen;
    }

    public List<RfqPojo> getRfqs() {
        return rfqs;
    }

    public void setRfqs(List<RfqPojo> rfqs) {
        this.rfqs = rfqs;
    }
    
    
}
