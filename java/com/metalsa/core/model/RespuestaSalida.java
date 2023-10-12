/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.model;

import com.metalsa.recibos.pojo.RequiFolio;
import java.util.List;


/**
 *
 * @author edgar.leal
 */
public class RespuestaSalida {

    public String mensaje;
    public String tipo;
    public String servicio;
    public String mensajeRespuesta;
    public List<RequiFolio> requiFolio;

    public List<RequiFolio> getRequiFolio() {
        return requiFolio;
    }

    public void setRequiFolio(List<RequiFolio> requiFolio) {
        this.requiFolio = requiFolio;
    }
    
    
    
    public String getMensajeRespuesta() {
        return mensajeRespuesta;
    }

    public void setMensajeRespuesta(String mensajeRespuesta) {
        this.mensajeRespuesta = mensajeRespuesta;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public RespuestaSalida() {
    }
}
