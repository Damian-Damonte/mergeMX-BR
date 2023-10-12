package com.metalsa.requisicion.pojo;

import java.io.Serializable;

/**
 *
 * @author APOMS7376
 */
public class NvcTblRazonUrgenciaPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idRazonUrgencia;
    private String idIdioma;
    private String razonUrgencia;

    public NvcTblRazonUrgenciaPojo() {
    }

    public NvcTblRazonUrgenciaPojo(Integer idRazonUrgencia, String idIdioma, String razonUrgencia) {
        this.idRazonUrgencia = idRazonUrgencia;
        this.idIdioma = idIdioma;
        this.razonUrgencia = razonUrgencia;
    }

    public Integer getIdRazonUrgencia() {
        return idRazonUrgencia;
    }

    public void setIdRazonUrgencia(Integer idRazonUrgencia) {
        this.idRazonUrgencia = idRazonUrgencia;
    }

    public String getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(String idIdioma) {
        this.idIdioma = idIdioma;
    }

    public String getRazonUrgencia() {
        return razonUrgencia;
    }

    public void setRazonUrgencia(String razonUrgencia) {
        this.razonUrgencia = razonUrgencia;
    }

}
