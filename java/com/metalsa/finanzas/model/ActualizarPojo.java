/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.finanzas.model;

import java.util.List;
import lombok.Data;

/**
 *
 * @author JL
 */
@Data
public class ActualizarPojo {
    
    private String accion;
    private String usuario;
    private String razon;
    private List<CategoriaPojo> categorias;
    
}
