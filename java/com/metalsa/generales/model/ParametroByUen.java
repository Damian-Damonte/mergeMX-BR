package com.metalsa.generales.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
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
    private Long idUen;
    @Id
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
        private Long idUen;
        @Id
        private Long idParametro;
    }
}
