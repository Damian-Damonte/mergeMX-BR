package com.metalsa.generales.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Column;
import java.io.Serializable;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
@Entity
@Table(name = "parametros_por_uen_cia")
@IdClass(ParametroByUen.Pk.class)
@Data
public class ParametroByUen {
    @Id
    @Column(name = "ID_UEN")
    private Long idUen;
    @Id
    @Column(name = "ID_PARAMETRO")
    private Long idParametro;
    private String nombre;
    private String descripcion;
    private String valor;

    /**
     * Pk
     */
    @Data
    public static class Pk implements Serializable {
        @Id
        @Column(name = "ID_UEN")
        private Long idUen;
        @Id
        @Column(name = "ID_PARAMETRO")
        private Long idParametro;
    }
}
