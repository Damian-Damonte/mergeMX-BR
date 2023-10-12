/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.pojo;

import java.util.List;

/**
 *
 * @author yair.nunez
 */
public class TipoRequisicion {
    private String claveRequisicion;
    private String tipoRequisicion;
    private List<Requisicion> requisiciones;
    
    /*MDA ADMINISTRACION*/
    private boolean spot;
    private boolean almacen;
    private boolean proveedor;
    private boolean valesFondo;
    private boolean interCompania;
    private Integer spotCont = 0;
    private Integer almacenCont = 0;
    private Integer proveedorCont = 0;
    private Integer valesFondoCont = 0;
    private Integer interCompaniaCont = 0;

    public TipoRequisicion() {
    }

    public String getClaveRequisicion() {
        return claveRequisicion;
    }

    public void setClaveRequisicion(String claveRequisicion) {
        this.claveRequisicion = claveRequisicion;
    }
    
    public String getTipoRequisicion() {
        return tipoRequisicion;
    }

    public void setTipoRequisicion(String tipoRequisicion) {
        this.tipoRequisicion = tipoRequisicion;
    }

    public List<Requisicion> getRequisiciones() {
        return requisiciones;
    }

    public void setRequisiciones(List<Requisicion> requisiciones) {
        this.requisiciones = requisiciones;
    }

    public boolean isSpot() {
        return spot;
    }

    public void setSpot(boolean spot) {
        this.spot = spot;
    }

    public boolean isAlmacen() {
        return almacen;
    }

    public void setAlmacen(boolean almacen) {
        this.almacen = almacen;
    }

    public boolean isProveedor() {
        return proveedor;
    }

    public void setProveedor(boolean proveedor) {
        this.proveedor = proveedor;
    }

    public boolean isValesFondo() {
        return valesFondo;
    }

    public void setValesFondo(boolean valesFondo) {
        this.valesFondo = valesFondo;
    }

    public boolean isInterCompania() {
        return interCompania;
    }

    public void setInterCompania(boolean interCompania) {
        this.interCompania = interCompania;
    }

    public Integer getSpotCont() {
        return spotCont;
    }

    public void setSpotCont(Integer spotCont) {
        this.spotCont = spotCont;
    }

    /**
     * @return the almacenCont
     */
    public Integer getAlmacenCont() {
        return almacenCont;
    }

    /**
     * @param almacenCont the almacenCont to set
     */
    public void setAlmacenCont(Integer almacenCont) {
        this.almacenCont = almacenCont;
    }

    /**
     * @return the proveedorCont
     */
    public Integer getProveedorCont() {
        return proveedorCont;
    }

    /**
     * @param proveedorCont the proveedorCont to set
     */
    public void setProveedorCont(Integer proveedorCont) {
        this.proveedorCont = proveedorCont;
    }

    /**
     * @return the valesFondoCont
     */
    public Integer getValesFondoCont() {
        return valesFondoCont;
    }

    /**
     * @param valesFondoCont the valesFondoCont to set
     */
    public void setValesFondoCont(Integer valesFondoCont) {
        this.valesFondoCont = valesFondoCont;
    }

    /**
     * @return the interCompaniaCont
     */
    public Integer getInterCompaniaCont() {
        return interCompaniaCont;
    }

    /**
     * @param interCompaniaCont the interCompaniaCont to set
     */
    public void setInterCompaniaCont(Integer interCompaniaCont) {
        this.interCompaniaCont = interCompaniaCont;
    }
    
    
}
