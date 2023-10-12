package com.metalsa.aprobacion.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 *
 * @author APOOD9272
 */
public class RegresoRequisicionVO {

    @JsonProperty("id_aprobador")
    private String idAprobador;

    @JsonProperty("razon_regreso")
    private String razonRegreso;

    private List<RequisicionVO> requisiciones;

    /**
     * @return the idAprobador
     */
    public String getIdAprobador() {
        return idAprobador;
    }

    /**
     * @param idAprobador the idAprobador to set
     */
    public void setIdAprobador(String idAprobador) {
        this.idAprobador = idAprobador;
    }

    /**
     * @return the razonRegreso
     */
    public String getRazonRegreso() {
        return razonRegreso;
    }

    /**
     * @param razonRegreso the razonRegreso to set
     */
    public void setRazonRegreso(String razonRegreso) {
        this.razonRegreso = razonRegreso;
    }

    /**
     * @return the requisiciones
     */
    public List<RequisicionVO> getRequisiciones() {
        return requisiciones;
    }

    /**
     * @param requisiciones the requisiciones to set
     */
    public void setRequisiciones(List<RequisicionVO> requisiciones) {
        this.requisiciones = requisiciones;
    }

    @Override
    public String toString() {
        return "RegresoRequisicionVO{" + "idAprobador=" + idAprobador + ", razonRegreso=" + razonRegreso + ", requisiciones=" + requisiciones + '}';
    }

}
