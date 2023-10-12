/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.matriz.pagos.dto;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author jose.jimenez07
 */
@Data
public class PeriodosDTO implements Serializable{

    private Integer id;
    private Integer anio;
    @SerializedName(value = "num_mes")
    private Integer numMes;
    private String mes;
    @SerializedName(value = "mes_abreviado")
    private String mesAbreviado;
    private String nombre;
    @SerializedName(value = "nombre_original")
    private String nombreOriginal;
    private String idioma;
    @SerializedName(value = "fech_inicio_format")
    private String fechInicioFormat;
    @SerializedName(value = "fech_fin_format")
    private String fechFinFormat;

}
