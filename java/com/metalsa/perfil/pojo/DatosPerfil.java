package com.metalsa.perfil.pojo;

import java.util.List;

/**
 *
 * @author APOMR10051
 */
public class DatosPerfil {

    //Valores requisitor
    private String idiomaSel;
    private List<UenWithCCDefault> uenDefault;
    private List<UenWithCCDefault> uenCcEditados;
    //Valores comprador
    private List<Moneda> monedaDefault;
    private boolean monedaDefaultActiva;
    private List<UnidadTiempo> unidadSel;
    
    
    private List<OpcionPerfil> opciones;

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

    public List<UenWithCCDefault> getUenCcEditados() {
        return uenCcEditados;
    }

    public void setUenCcEditados(List<UenWithCCDefault> uenCcEditados) {
        this.uenCcEditados = uenCcEditados;
    }

    public List<Moneda> getMonedaDefault() {
        return monedaDefault;
    }

    public void setMonedaDefault(List<Moneda> monedaDefault) {
        this.monedaDefault = monedaDefault;
    }

    public boolean isMonedaDefaultActiva() {
        return monedaDefaultActiva;
    }

    public void setMonedaDefaultActiva(boolean monedaDefaultActiva) {
        this.monedaDefaultActiva = monedaDefaultActiva;
    }

    public List<UnidadTiempo> getUnidadSel() {
        return unidadSel;
    }

    public void setUnidadSel(List<UnidadTiempo> unidadSel) {
        this.unidadSel = unidadSel;
    }

    public List<OpcionPerfil> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<OpcionPerfil> opciones) {
        this.opciones = opciones;
    }
}
