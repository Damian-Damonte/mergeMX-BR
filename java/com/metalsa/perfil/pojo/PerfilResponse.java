package com.metalsa.perfil.pojo;

import java.util.List;

/**
 *
 * @author APOMR10051
 */
public class PerfilResponse {

    //Listas
    private List<UenWithCCDefault> uens;
    private List<DcIdioma> idiomas;
    private List<Moneda> monedas;
    private List<UnidadTiempo> unidadesTiempo;
    private List<OpcionPerfil> opciones;
    //Valores seleccionados
    private String idiomaSel;
    private List<UenWithCCDefault> uenDefault;
    private List<Moneda> monedaDefault;
    private List<CentroCosto> ccResponsable;
    private List<CentroCosto> ccDelegado;
    private List<Error> errores;
    private String emailUser;

    public List<UenWithCCDefault> getUens() {
        return uens;
    }

    public void setUens(List<UenWithCCDefault> uens) {
        this.uens = uens;
    }

    public List<DcIdioma> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<DcIdioma> idiomas) {
        this.idiomas = idiomas;
    }

    public List<Moneda> getMonedas() {
        return monedas;
    }

    public void setMonedas(List<Moneda> monedas) {
        this.monedas = monedas;
    }

    public List<UnidadTiempo> getUnidadesTiempo() {
        return unidadesTiempo;
    }

    public void setUnidadesTiempo(List<UnidadTiempo> unidadesTiempo) {
        this.unidadesTiempo = unidadesTiempo;
    }

    public List<OpcionPerfil> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<OpcionPerfil> opciones) {
        this.opciones = opciones;
    }

    public String getIdiomaSel() {
        return idiomaSel;
    }

    public void setIdiomaSel(String idiomaSel) {
        this.idiomaSel = idiomaSel;
    }

    public List<UenWithCCDefault> getUenDefault() {
        return uenDefault;
    }

    public void setUenDefault(List<UenWithCCDefault> uenDefault) {
        this.uenDefault = uenDefault;
    }

    public List<Moneda> getMonedaDefault() {
        return monedaDefault;
    }

    public void setMonedaDefault(List<Moneda> monedaDefault) {
        this.monedaDefault = monedaDefault;
    }

    public List<CentroCosto> getCcResponsable() {
        return ccResponsable;
    }

    public void setCcResponsable(List<CentroCosto> ccResponsable) {
        this.ccResponsable = ccResponsable;
    }

    public List<CentroCosto> getCcDelegado() {
        return ccDelegado;
    }

    public void setCcDelegado(List<CentroCosto> ccDelegado) {
        this.ccDelegado = ccDelegado;
    }

    public List<Error> getErrores() {
        return errores;
    }

    public void setErrores(List<Error> errores) {
        this.errores = errores;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }
    
}
