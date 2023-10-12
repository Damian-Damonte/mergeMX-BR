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
public class RequisicionCreateRequest {
     private List<RequisicionCreateRequestUEN> uens;
     
     public List<RequisicionCreateRequestUEN> getUens() {
         return uens;
     }
}