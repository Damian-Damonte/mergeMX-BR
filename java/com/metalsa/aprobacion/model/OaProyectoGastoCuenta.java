package com.metalsa.aprobacion.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 *
 * @author APOOD9272
 */
public class OaProyectoGastoCuenta implements Serializable{

    @JsonProperty("exp_type")
    private String expType;

    @JsonProperty("tipo_gasto")
    private String tipoGasto;

    @JsonProperty("req_alm")
    private String reqAlm;

    private String tipo;

    public String getExpType() {
        return expType;
    }

    public void setExpType(String expType) {
        this.expType = expType;
    }

    public String getTipoGasto() {
        return tipoGasto;
    }

    public void setTipoGasto(String tipoGasto) {
        this.tipoGasto = tipoGasto;
    }

    public String getReqAlm() {
        return reqAlm;
    }

    public void setReqAlm(String reqAlm) {
        this.reqAlm = reqAlm;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
