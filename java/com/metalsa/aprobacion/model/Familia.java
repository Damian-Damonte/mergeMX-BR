/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.aprobacion.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author Oscar del Angel
 */
@Data
@Entity
@Table(name = "FAMILIAS")
@NamedNativeQueries({
    @NamedNativeQuery(
            name = "Familia.findAllByIdioma",
            resultClass = Familia.class,
            query = "SELECT\n"
            + " ID_FAMILIA,\n"
            + " CONCATEN ||' - ' || NOMBRE_FAMILIA || ' ( ' || ID_FAMILIA || ' )'  NOMBRE_FAMILIA,\n"
            + " CONCATEN\n"
            + "FROM FAMILIAS\n"
            + "WHERE ESTATUS = 'A'\n"
            + "AND LENGUAJE = :idioma\n"
            + "AND (SEGMENTO_1 >= 98 AND SEGMENTO_1 <= 130)\n"
            + "AND NVL(STOCK, 'N') != 'Yes'\n"
            + "ORDER BY CONCATEN"
    )
})

public class Familia implements Serializable {

    @Id
    @Column(name = "ID_FAMILIA")
    private Integer idFamilia;

    @Column(name = "NOMBRE_FAMILIA")
    private String nombreFamilia;

    @Column(name = "CONCATEN")
    private String concaten;

    @Override
    public String toString() {
        return "Familia{" + "idFamilia=" + idFamilia + ", nombreFamilia=" + nombreFamilia + ", concaten=" + concaten + '}';
    }

}
