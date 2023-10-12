package com.metalsa.requisicion.pojo;

import java.util.List;

/**
 *
 * @author APOMS7376
 */
public class RequisicionPreview {
    
//    private Integer idUen;
    private String nombreUen;
    private List<TipoRequisicion> tipoRequisicion;

    public RequisicionPreview() {
        
    }

    public RequisicionPreview(String nombreUen, List<TipoRequisicion> tipoRequisicion) {
        this.nombreUen = nombreUen;
        this.tipoRequisicion = tipoRequisicion;
    }

    public List<TipoRequisicion> getTipoRequisicion() {
        return tipoRequisicion;
    }

    public void setTipoRequisicion(List<TipoRequisicion> tipoRequisicion) {
        this.tipoRequisicion = tipoRequisicion;
    }

//    public Integer getIdUen() {
//        return idUen;
//    }
//
//    public void setIdUen(Integer idUen) {
//        this.idUen = idUen;
//    }

    public String getNombreUen() {
        return nombreUen;
    }

    public void setNombreUen(String nombreUen) {
        this.nombreUen = nombreUen;
    }
}
