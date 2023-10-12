/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.aprobacion.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author APOOD9272
 */
public class RedicionRequisicionVO {

    @JsonProperty("id_requisicion")
    private Integer idRequisicion;

    @JsonProperty("id_partida")
    private Integer idPartida;

    @JsonProperty("id_proyecto")
    private Long idProyecto;

    @JsonProperty("id_tarea")
    private Long idTarea;

    @JsonProperty("tipo_gasto")
    private String tipoGasto;

    @JsonProperty("id_cuenta")
    private Long idCuenta;

    @JsonProperty("siguiente_aprobador")
    private String siguienteAprobador;

    @JsonProperty("cod_proyecto")
    private String codProyecto;

    @JsonProperty("cod_tarea")
    private String codTarea;

    @JsonProperty("segmento_3")
    private String segmento3;

    @JsonProperty("segmento_5")
    private String segmento5;

    /**
     * @return the idRequisicion
     */
    public Integer getIdRequisicion() {
        return idRequisicion;
    }

    /**
     * @param idRequisicion the idRequisicion to set
     */
    public void setIdRequisicion(Integer idRequisicion) {
        this.idRequisicion = idRequisicion;
    }

    /**
     * @return the idPartida
     */
    public Integer getIdPartida() {
        return idPartida;
    }

    /**
     * @param idPartida the idPartida to set
     */
    public void setIdPartida(Integer idPartida) {
        this.idPartida = idPartida;
    }

    /**
     * @return the idProyecto
     */
    public Long getIdProyecto() {
        return idProyecto;
    }

    /**
     * @param idProyecto the idProyecto to set
     */
    public void setIdProyecto(Long idProyecto) {
        this.idProyecto = idProyecto;
    }

    /**
     * @return the idTarea
     */
    public Long getIdTarea() {
        return idTarea;
    }

    /**
     * @param idTarea the idTarea to set
     */
    public void setIdTarea(Long idTarea) {
        this.idTarea = idTarea;
    }

    /**
     * @return the tipoGasto
     */
    public String getTipoGasto() {
        return tipoGasto;
    }

    /**
     * @param tipoGasto the tipoGasto to set
     */
    public void setTipoGasto(String tipoGasto) {
        this.tipoGasto = tipoGasto;
    }

    /**
     * @return the idCuenta
     */
    public Long getIdCuenta() {
        return idCuenta;
    }

    /**
     * @param idCuenta the idCuenta to set
     */
    public void setIdCuenta(Long idCuenta) {
        this.idCuenta = idCuenta;
    }

    /**
     * @return the siguienteAprobador
     */
    public String getSiguienteAprobador() {
        return siguienteAprobador;
    }

    /**
     * @param siguienteAprobador the siguienteAprobador to set
     */
    public void setSiguienteAprobador(String siguienteAprobador) {
        this.siguienteAprobador = siguienteAprobador;
    }

    /**
     * @return the codProyecto
     */
    public String getCodProyecto() {
        return codProyecto;
    }

    /**
     * @param codProyecto the codProyecto to set
     */
    public void setCodProyecto(String codProyecto) {
        this.codProyecto = codProyecto;
    }

    /**
     * @return the codTarea
     */
    public String getCodTarea() {
        return codTarea;
    }

    /**
     * @param codTarea the codTarea to set
     */
    public void setCodTarea(String codTarea) {
        this.codTarea = codTarea;
    }

    /**
     * @return the segmento3
     */
    public String getSegmento3() {
        return segmento3;
    }

    /**
     * @param segmento3 the segmento3 to set
     */
    public void setSegmento3(String segmento3) {
        this.segmento3 = segmento3;
    }

    /**
     * @return the segmento5
     */
    public String getSegmento5() {
        return segmento5;
    }

    /**
     * @param segmento5 the segmento5 to set
     */
    public void setSegmento5(String segmento5) {
        this.segmento5 = segmento5;
    }

    @Override
    public String toString() {
        return "RedicionRequisicionVO{" + "idRequisicion=" + idRequisicion + ", idPartida=" + idPartida + ", idProyecto="
                + idProyecto + ", idTarea=" + idTarea + ", tipoGasto=" + tipoGasto + ", idCuenta=" + idCuenta + ", siguienteAprobador=" + siguienteAprobador + ", codProyecto=" + codProyecto + ", codTarea=" + codTarea + ", segmento3=" + segmento3 + ", segmento5=" + segmento5 + '}';
    }

}
