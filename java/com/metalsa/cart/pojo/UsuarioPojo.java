/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.cart.pojo;


import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 *
 * @author APOMS7376
 */
public class UsuarioPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer businessGroupId;
    private Integer conteoDeIntentosFallidos;
    private String datosDeAuditoria;
    private String email;
    private String estatus;
    private Date fechaExpiraPassword;
    private Date fechaExpiracionCuenta;
    private Integer idEmpleado;
    private String idUsuario;
    private String idioma;
    private String nombreUsuario;
    private Integer owner;
    private String password;
    private String perfilDeBusqueda;
    private Date ultimaFechaDeEntrada;    
    private Long idCCDefault;

    public UsuarioPojo() {
    }

    public UsuarioPojo(String idUsuario,String nombreUsuario) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
    }

    public UsuarioPojo(BigInteger businessGroupId, Short conteoDeIntentosFallidos, String datosDeAuditoria, String email, String estatus,
            Date fechaExpiraPassword, Date fechaExpiracionCuenta, Long idEmpleado, String idUsuario, String idioma, String nombreUsuario,
            BigInteger owner, String password, String perfilDeBusqueda, Date ultimaFechaDeEntrada) {
        this.businessGroupId = businessGroupId == null ? 0 : businessGroupId.intValue();
        this.conteoDeIntentosFallidos = conteoDeIntentosFallidos == null ? 0 : conteoDeIntentosFallidos.intValue();
        this.datosDeAuditoria = datosDeAuditoria;
        this.email = email;
        this.estatus = estatus;
        this.fechaExpiraPassword = fechaExpiraPassword;
        this.fechaExpiracionCuenta = fechaExpiracionCuenta;
        this.idEmpleado = idEmpleado == null ? 0 : idEmpleado.intValue();
        this.idUsuario = idUsuario;
        this.idioma = idioma;
        this.nombreUsuario = nombreUsuario;
        this.owner = owner == null ? 0 : owner.intValue();
        this.password = password;
        this.perfilDeBusqueda = perfilDeBusqueda;
        this.ultimaFechaDeEntrada = ultimaFechaDeEntrada;
    }

    private void setValues(BigInteger businessGroupId, Short conteoDeIntentosFallidos, String datosDeAuditoria, String email, String estatus,
            Date fechaExpiraPassword, Date fechaExpiracionCuenta, Long idEmpleado, String idUsuario, String idioma, String nombreUsuario,
            BigInteger owner, String password, String perfilDeBusqueda, Date ultimaFechaDeEntrada) {
        this.businessGroupId = businessGroupId == null ? 0 : businessGroupId.intValue();
        this.conteoDeIntentosFallidos = conteoDeIntentosFallidos == null ? 0 : conteoDeIntentosFallidos.intValue();
        this.datosDeAuditoria = datosDeAuditoria;
        this.email = email;
        this.estatus = estatus;
        this.fechaExpiraPassword = fechaExpiraPassword;
        this.fechaExpiracionCuenta = fechaExpiracionCuenta;
        this.idEmpleado = idEmpleado == null ? 0 : idEmpleado.intValue();
        this.idUsuario = idUsuario;
        this.idioma = idioma;
        this.nombreUsuario = nombreUsuario;
        this.owner = owner == null ? 0 : owner.intValue();
        this.password = password;
        this.perfilDeBusqueda = perfilDeBusqueda;
        this.ultimaFechaDeEntrada = ultimaFechaDeEntrada;
    }
    

    public Integer getBusinessGroupId() {
        return businessGroupId;
    }

    public void setBusinessGroupId(Integer businessGroupId) {
        this.businessGroupId = businessGroupId;
    }

    public Integer getConteoDeIntentosFallidos() {
        return conteoDeIntentosFallidos;
    }

    public void setConteoDeIntentosFallidos(Integer conteoDeIntentosFallidos) {
        this.conteoDeIntentosFallidos = conteoDeIntentosFallidos;
    }

    public String getDatosDeAuditoria() {
        return datosDeAuditoria;
    }

    public void setDatosDeAuditoria(String datosDeAuditoria) {
        this.datosDeAuditoria = datosDeAuditoria;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Date getFechaExpiraPassword() {
        return fechaExpiraPassword;
    }

    public void setFechaExpiraPassword(Date fechaExpiraPassword) {
        this.fechaExpiraPassword = fechaExpiraPassword;
    }

    public Date getFechaExpiracionCuenta() {
        return fechaExpiracionCuenta;
    }

    public void setFechaExpiracionCuenta(Date fechaExpiracionCuenta) {
        this.fechaExpiracionCuenta = fechaExpiracionCuenta;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPerfilDeBusqueda() {
        return perfilDeBusqueda;
    }

    public void setPerfilDeBusqueda(String perfilDeBusqueda) {
        this.perfilDeBusqueda = perfilDeBusqueda;
    }

    public Date getUltimaFechaDeEntrada() {
        return ultimaFechaDeEntrada;
    }

    public void setUltimaFechaDeEntrada(Date ultimaFechaDeEntrada) {
        this.ultimaFechaDeEntrada = ultimaFechaDeEntrada;
    }


    public Long getIdCCDefault() {
        return idCCDefault;
    }

    public void setIdCCDefault(Long idCCDefault) {
        this.idCCDefault = idCCDefault;
    }

}
