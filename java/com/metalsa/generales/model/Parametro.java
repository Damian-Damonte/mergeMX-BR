package com.metalsa.generales.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *  Parámetro de configuracion
 */
@Entity
@Table(name = "parametros_configuracion")
@Data
public class Parametro {

    @Id
    private Long idParametro;

    @Column(name = "descripcion_parametro")
    private String descripcion;

    @Column(name = "nombre_parametro")
    private String nombre;
    private String tipo;
    private String idioma;
    private Integer activo;
    private String tipoValor;

}
