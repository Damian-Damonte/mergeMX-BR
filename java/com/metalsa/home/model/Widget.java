package com.metalsa.home.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Clase para mapeo de detalle de requisicion
 */
@Data
@Entity
public class Widget implements Serializable {

    @Id
    @Column(name = "id_widget")
    private Long idWidget;

    @Column(name = "widget")
    private String widget;

    @Column(name = "width")
    private Long width;

    @Column(name = "height")
    private Long height;

    @Column(name = "max_width")
    private Long maxWidth;

    @Column(name = "max_height")
    private Long maxHeight;

    @Column(name = "min_width")
    private Long minWidth;

    @Column(name = "min_height")
    private Long minHeight;

    @Column(name = "predeterminado")
    private Long predeterminado;

}
