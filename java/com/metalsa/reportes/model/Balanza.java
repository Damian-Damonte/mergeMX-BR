/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.reportes.model;

import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Oscar del Angel
 */

@Getter
@Setter
public class Balanza extends  CCHeader implements Serializable {

    private List<BalanzaLinea> lineas;

    
}
