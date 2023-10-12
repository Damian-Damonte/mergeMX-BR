/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.pojo;

import java.util.List;

/**
 *
 * @author edgar.leal
 */
public class ClassOne {
    private String id_usuario;
    private List<QuotationRequest> requi;
    private String comentario;
    private String status; 

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public ClassOne() {
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public List<QuotationRequest> getRequi() {
        return requi;
    }

    public void setRequi(List<QuotationRequest> requi) {
        this.requi = requi;
    }

    @Override
    public String toString() {
        return "RecibePeticion{" + "idUsuario=" + id_usuario + ", requi=" + requi + '}';
    }

}
