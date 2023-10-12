package com.metalsa.almacen.pojo;

import java.io.Serializable;

/**
 *
 * @author APOMR10051
 */
public class Almacen implements Serializable {

    private Integer idAlmacen;
    private String almacen;
    private String nombreAlmacen;
    private String nombreAlmacenCod;

    public Integer getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(Integer idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public String getAlmacen() {
        return almacen;
    }

    public void setAlmacen(String almacen) {
        this.almacen = almacen;
    }

    public String getNombreAlmacen() {
        return nombreAlmacen;
    }

    public void setNombreAlmacen(String nombreAlmacen) {
        this.nombreAlmacen = nombreAlmacen;
    }

    public String getNombreAlmacenCod() {
        this.nombreAlmacenCod = this.almacen + "-" + this.nombreAlmacen;
        return nombreAlmacenCod;
    }

    public void setNombreAlmacenCod(String nombreAlmacenCod) {
        this.nombreAlmacenCod = nombreAlmacenCod;
    }

}
