/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.model;

/**
 *
 * @author aayala
 */
public class RespDelRequisicion {
    public String codigoCC;
    public String nombreCC;
    public String aprobador;
    public String responsabilidad;
    public String nivelAprobacionRi;
    public String nivelAprobacionRe;

    public String getCodigoCC() {
        return codigoCC;
    }

    public void setCodigoCC(String codigoCC) {
        this.codigoCC = codigoCC;
    }

    public String getNombreCC() {
        return nombreCC;
    }

    public void setNombreCC(String nombreCC) {
        this.nombreCC = nombreCC;
    }

    public String getAprobador() {
        return aprobador;
    }

    public void setAprobador(String aprobador) {
        this.aprobador = aprobador;
    }

    public String getResponsabilidad() {
        return responsabilidad;
    }

    public void setResponsabilidad(String responsabilidad) {
        this.responsabilidad = responsabilidad;
    }

    public String getNivelAprobacionRi() {
        return nivelAprobacionRi;
    }

    public void setNivelAprobacionRi(String nivelAprobacionRi) {
        this.nivelAprobacionRi = nivelAprobacionRi;
    }

    public String getNivelAprobacionRe() {
        return nivelAprobacionRe;
    }

    public void setNivelAprobacionRe(String nivelAprobacionRe) {
        this.nivelAprobacionRe = nivelAprobacionRe;
    }

    public RespDelRequisicion() {
    }   
    
}
