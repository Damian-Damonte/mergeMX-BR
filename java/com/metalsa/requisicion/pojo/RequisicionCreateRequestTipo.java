/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.requisicion.pojo;

import java.util.List;

/**
 *
 * @author jose.espindola03
 */
public class RequisicionCreateRequestTipo {
    private String name;
    private String tipo;
    private List<RequisicionCreateRequestRequisicion> requisiciones;
    
    public List<RequisicionCreateRequestRequisicion> getRequisiciones() {
        return this.requisiciones;
    }
}
