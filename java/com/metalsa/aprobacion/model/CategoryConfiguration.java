package com.metalsa.aprobacion.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
@Entity
@Table(name = "icom_category_parameters")
@IdClass(CategoryConfiguration.Pk.class)
@Data
public class CategoryConfiguration {
    @Id
    @Column(name = "uen_id")
    private Long uen;

    @Id
    @Column(name = "acc_cat_id")
    private Long categoria;

    @Column(name = "sol_aprueba_exceso")
    private boolean aprobacionExceso;

    /**
     * primary key
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Pk implements Serializable {
        @Id
        @Column(name = "uen_id")
        private Long uen;

        @Id
        @Column(name = "acc_cat_id")
        private Long categoria;
    }
}
