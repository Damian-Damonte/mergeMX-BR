/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.pojo;

/**
 *
 * @author edgar.leal
 */
public class RequiFolio {
    
    private String folio;
    private String Requi;
    private String oc;
    private String mensajes[];
    private Integer tieneMensaje;

    public RequiFolio() {
    }
    
    

    public RequiFolio(String folio, String Requi, String[] mensajes, Integer tieneMensaje,String oc) {
        this.folio = folio;
        this.Requi = Requi;
        this.mensajes = mensajes;
        this.tieneMensaje = tieneMensaje;
        this.oc = oc;
    }

    public String getOc() {
        return oc;
    }

    public void setOc(String oc) {
        this.oc = oc;
    }
    
    public Integer getTieneMensaje() {
        return tieneMensaje;
    }

    public void setTieneMensaje(Integer tieneMensaje) {
        this.tieneMensaje = tieneMensaje;
    }
    
    

    public String[] getMensajes() {
        return mensajes;
    }

    public void setMensajes(String[] mensajes) {
        this.mensajes = mensajes;
    }
    
    
    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getRequi() {
        return Requi;
    }

    public void setRequi(String Requi) {
        this.Requi = Requi;
    }
    
    
    
}
