package com.metalsa.admin.model;

import com.metalsa.home.model.NoticiaHome;

/**
 *
 * @author APOMR10051
 */
public class NoticiaRequest {

    private String idUsuario;
    private String idioma;
    private NoticiaHome noticia;
    private Integer oldOrder;
    private Integer newOrder;
    private String outMessage;

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

    public NoticiaHome getNoticia() {
        return noticia;
    }

    public void setNoticia(NoticiaHome noticia) {
        this.noticia = noticia;
    }

    public Integer getOldOrder() {
        return oldOrder;
    }

    public void setOldOrder(Integer oldOrder) {
        this.oldOrder = oldOrder;
    }

    public Integer getNewOrder() {
        return newOrder;
    }

    public void setNewOrder(Integer newOrder) {
        this.newOrder = newOrder;
    }

    public String getOutMessage() {
        return outMessage;
    }

    public void setOutMessage(String outMessage) {
        this.outMessage = outMessage;
    }
}
