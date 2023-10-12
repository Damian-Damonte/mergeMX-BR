package com.metalsa.requisicion.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author APOMS7376
 */
public class NvcTblCatalogoLocalizacionPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idCatalogoLocalizacion;
    private Integer idCatalogoUen;
    private Integer idLocalizacion;
    private String direccion;

    private List<SitesPojo> sitePojoList;

    public NvcTblCatalogoLocalizacionPojo() {
    }

    public NvcTblCatalogoLocalizacionPojo(Integer idCatalogoLocalizacion, Integer idCatalogoUen, Integer idLocalizacion, String direccion) {
        this.idCatalogoLocalizacion = idCatalogoLocalizacion;
        this.idCatalogoUen = idCatalogoUen;
        this.idLocalizacion = idLocalizacion;
        this.direccion = direccion;
    }

    public Integer getIdCatalogoLocalizacion() {
        return idCatalogoLocalizacion;
    }

    public void setIdCatalogoLocalizacion(Integer idCatalogoLocalizacion) {
        this.idCatalogoLocalizacion = idCatalogoLocalizacion;
    }

    public Integer getIdCatalogoUen() {
        return idCatalogoUen;
    }

    public void setIdCatalogoUen(Integer idCatalogoUen) {
        this.idCatalogoUen = idCatalogoUen;
    }

    public Integer getIdLocalizacion() {
        return idLocalizacion;
    }

    public void setIdLocalizacion(Integer idLocalizacion) {
        this.idLocalizacion = idLocalizacion;
    }

    public List<SitesPojo> getSitePojoList() {
        if (sitePojoList == null) {
            sitePojoList = new ArrayList<>();
        }
        return sitePojoList;
    }

    public void setSitePojoList(List<SitesPojo> sitePojoList) {
        this.sitePojoList = sitePojoList;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
//    public Integer getBillTo() {
//        return billTo;
//    }
//
//    public void setBillTo(Integer billTo) {
//        this.billTo = billTo;
//    }
}
