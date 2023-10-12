package com.metalsa.admin.model;

import com.metalsa.admin.pojo.FiltrosNoticias;
import com.metalsa.home.model.NoticiaHome;

/**
 *
 * @author APOMR10051
 */
public class NoticiaResponse {

    private Iterable<NoticiaHome> noticias;
    private String outMessage;
    private Integer outValue;
    private FiltrosNoticias filtros;

    public Iterable<NoticiaHome> getNoticias() {
        return noticias;
    }

    public void setNoticias(Iterable<NoticiaHome> noticias) {
        this.noticias = noticias;
    }

    public String getOutMessage() {
        return outMessage;
    }

    public void setOutMessage(String outMessage) {
        this.outMessage = outMessage;
    }

    public Integer getOutValue() {
        return outValue;
    }

    public void setOutValue(Integer outValue) {
        this.outValue = outValue;
    }

    public FiltrosNoticias getFiltros() {
        return filtros;
    }

    public void setFiltros(FiltrosNoticias filtros) {
        this.filtros = filtros;
    }
}
