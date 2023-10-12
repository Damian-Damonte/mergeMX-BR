/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.model;

import java.util.List;

/**
 *
 * @author APOJO9952
 */
public class QuotationDetailedByRequisition {

    private Integer idRequisition;
    private Integer tieneChat;   
    private Integer idUen;
    private String nombreUen;
    private Integer cantidadRequisiciones;
    private Iterable<QuotationDetailed> quotationDetailed;

    public QuotationDetailedByRequisition(Integer idRequisition, Integer tieneChat, 
            Integer id_uen, String nombreUen,Integer cantidadRequisiciones,
            List<QuotationDetailed> quotationDetailed) {
        this.idRequisition = idRequisition;
        this.tieneChat = tieneChat;
        this.idUen = id_uen;
        this.nombreUen = nombreUen;
        this.cantidadRequisiciones = cantidadRequisiciones;
        this.quotationDetailed = quotationDetailed;
        
    }

    public Iterable<QuotationDetailed> getQuotationDetailed() {
        return quotationDetailed;
    }

    public void setQuotationDetailed(Iterable<QuotationDetailed> quotationDetailed) {
        this.quotationDetailed = quotationDetailed;
    }

    public Integer getIdRequisition() {
        return idRequisition;
    }

    public void setIdRequisition(Integer idRequisition) {
        this.idRequisition = idRequisition;
    }

    public Integer getTieneChat() {
        return tieneChat;
    }

    public void setTieneChat(Integer tieneChat) {
        this.tieneChat = tieneChat;
    }

    public Integer getIdUen() {
        return idUen;
    }

    public void setIdUen(Integer idUen) {
        this.idUen = idUen;
    }

    public String getNombreUen() {
        return nombreUen;
    }

    public void setNombreUen(String nombreUen) {
        this.nombreUen = nombreUen;
    }

    public Integer getCantidadRequisiciones() {
        return cantidadRequisiciones;
    }

    public void setCantidadRequisiciones(Integer cantidadRequisiciones) {
        this.cantidadRequisiciones = cantidadRequisiciones;
    }
    
    

}
