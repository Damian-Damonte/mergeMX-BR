package com.metalsa.home.model;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import static javax.persistence.ParameterMode.IN;
import static javax.persistence.ParameterMode.OUT;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Clase para mapeo de detalle de requisicion
 */
@Data
@Entity
@IdClass(WidgetPorUsuario.Pk.class)
@NamedStoredProcedureQuery(
        name = "WidgetPorUsuario.creaWidgetsDefault",
        procedureName = "widgets_default",
        parameters = {
                        @StoredProcedureParameter(mode=IN, name="p_id_usuario", type=String.class),
                        @StoredProcedureParameter(mode=OUT, name="out_message", type=String.class)
        }
)
public class WidgetPorUsuario implements Serializable {

    @Id
    @Column(name = "id_widget")
    private Long idWidget;

    @Id
    @Column(name = "id_usuario")
    private String idUsuario;

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

    @Column(name = "pos_x")
    private Long posX;

    @Column(name = "pos_y")
    private Long posY;

    @Column(name = "fijo")
    private Long fijo;

    /**
     * primary key
     */
    @Getter
    @EqualsAndHashCode
    public static class Pk implements Serializable {

        @Id
        @Column(name = "id_widget")
        private Long idWidget;

        @Id
        @Column(name = "id_usuario")
        private String idUsuario;
    }
}
