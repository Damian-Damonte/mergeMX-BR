/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.matriz.pagos.dto;

/**
 *
 * @author jose.jimenez07
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author APOOD9272
 */
@Data
public class OaProyectosDTO implements Serializable {

    private Long id_proyecto;
    private Integer id_uen;
    private String cod_proyecto;
    private String nombre_proyecto;
    private String start_date;
    private String completion_date;
    private String pre_aprobador;

}
