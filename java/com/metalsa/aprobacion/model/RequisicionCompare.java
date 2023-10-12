package com.metalsa.aprobacion.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
@Data
@Builder
public class RequisicionCompare implements Serializable {
    private Long id;
    private String nombreUsuario;
    private String monedaUen;

    private List<LineCompare> lines;
    private List<SuplierCompare> supliers;
}
