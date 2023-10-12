package com.metalsa.almacen.model;

import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author APOMR10051
 */
@Data
public class Despacho implements Serializable {

    private String nombreUen;
    private String codOrg;
    private String nombreOrg;
    private Integer idRequisicion;
    private Integer idPartida;
    private Integer idItem;
    private String nombreRequisitor;
    private String fechaRequerida;
    private String fechaAprobacion;
    private String descripcionItem;

}
