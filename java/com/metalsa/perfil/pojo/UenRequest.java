package com.metalsa.perfil.pojo;

import java.util.List;

/**
 *
 * @author APOMR10051
 */
public class UenRequest {

    private List<Uen> uenSolicitada;
    private String razon;

    public List<Uen> getUenSolicitada() {
        return uenSolicitada;
    }

    public void setUenSolicitada(List<Uen> uenSolicitada) {
        this.uenSolicitada = uenSolicitada;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }
}
