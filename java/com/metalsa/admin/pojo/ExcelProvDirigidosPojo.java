/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.admin.pojo;

import java.util.List;
import lombok.Data;

/**
 *
 * @author jose.jimenez07
 */
@Data
public class ExcelProvDirigidosPojo {
    
    private List<String> columnas;
    private List<String> columnasPart2;
    private String idioma;
    private Integer idUen;
    
}
