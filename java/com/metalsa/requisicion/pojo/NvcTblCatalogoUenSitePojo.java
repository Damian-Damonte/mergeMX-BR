package com.metalsa.requisicion.pojo;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author APOMS7376
 */
public class NvcTblCatalogoUenSitePojo {

    private Integer idCatalogoUenSite;
    private Date fechaCreacion;
    private Date fechaActualizacion;
    private String usuarioCreacion;
    private String usuarioActualizacion;
    private String activo;
    private Integer idProveedor;
    private Integer idSucursalProveedor;
//    private Integer idCatalogoUen;
    private Integer idCatalogoLocalizacion;

    public NvcTblCatalogoUenSitePojo() {
    }
    
    public NvcTblCatalogoUenSitePojo(BigDecimal idCatalogoUenSite, Date fechaCreacion, Date fechaActualizacion, String usuarioCreacion,
            String usuarioActualizacion, Character activo, BigInteger idProveedor, BigInteger idSucursalProveedor, BigDecimal idCatalogoLocalizacion) {
        
        this.idCatalogoUenSite = idCatalogoUenSite == null ? 0 : idCatalogoUenSite.intValue();
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.usuarioCreacion = usuarioCreacion;
        this.usuarioActualizacion = usuarioActualizacion;
        this.activo = activo == null ? "0" : activo.toString();
        this.idProveedor = idProveedor == null ? 0 : idProveedor.intValue();
        this.idSucursalProveedor = idSucursalProveedor == null ? 0 : idSucursalProveedor.intValue();
        this.idCatalogoLocalizacion = idCatalogoLocalizacion == null ? 0 : idCatalogoLocalizacion.intValue();
    }

    public Integer getIdCatalogoUenSite() {
        return idCatalogoUenSite;
    }

    public void setIdCatalogoUenSite(Integer idCatalogoUenSite) {
        this.idCatalogoUenSite = idCatalogoUenSite;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public String getUsuarioActualizacion() {
        return usuarioActualizacion;
    }

    public void setUsuarioActualizacion(String usuarioActualizacion) {
        this.usuarioActualizacion = usuarioActualizacion;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Integer getIdSucursalProveedor() {
        return idSucursalProveedor;
    }

    public void setIdSucursalProveedor(Integer idSucursalProveedor) {
        this.idSucursalProveedor = idSucursalProveedor;
    }

//    public Integer getIdCatalogoUen() {
//        return idCatalogoUen;
//    }
//
//    public void setIdCatalogoUen(Integer idCatalogoUen) {
//        this.idCatalogoUen = idCatalogoUen;
//    }

    public Integer getIdCatalogoLocalizacion() {
        return idCatalogoLocalizacion;
    }

    public void setIdCatalogoLocalizacion(Integer idCatalogoLocalizacion) {
        this.idCatalogoLocalizacion = idCatalogoLocalizacion;
    }
}
