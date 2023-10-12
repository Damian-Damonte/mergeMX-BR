package com.metalsa.generales.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@IdClass(ParametroUenCia.Pk.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParametroUenCia {
    @Id
    private Long idUen;
    @Id
    private Long idParametro;
    private String valor;

    /**
     * Pk
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Pk implements Serializable {
        @Id
        private Long idUen;
        @Id
        private Long idParametro;
    }
}
