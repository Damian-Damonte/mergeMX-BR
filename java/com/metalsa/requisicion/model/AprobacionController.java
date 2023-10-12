/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.requisicion.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author APOMS7376
 */
@Entity
@Table(name = "NVC_TBL_APROBACION_CONTROLLER")
public class AprobacionController implements Serializable {

    @Id
    @Column(name = "id_aprobacion_controller")
    @SequenceGenerator(
            name = "seq_aprobacion_controller",
            sequenceName = "seq_aprobacion_controller",
            allocationSize = 1)
    @GeneratedValue(generator = "seq_aprobacion_controller")
    private Integer idAprobacionController;

    @Column(name = "id_requisicion")
    private Integer idRequisicion;
    @Column(name = "id_partida")
    private Integer idPartida;
    @Column(name = "estatus_requisicion")
    private String estatusRequisicion;
    @Column(name = "fecha_creacion")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaCreacion;

    public Integer getIdAprobacionController() {
        return idAprobacionController;
    }

    public void setIdAprobacionController(Integer idAprobacionController) {
        this.idAprobacionController = idAprobacionController;
    }

    public Integer getIdRequisicion() {
        return idRequisicion;
    }

    public void setIdRequisicion(Integer idRequisicion) {
        this.idRequisicion = idRequisicion;
    }

    public Integer getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(Integer idPartida) {
        this.idPartida = idPartida;
    }

    public String getEstatusRequisicion() {
        return estatusRequisicion;
    }

    public void setEstatusRequisicion(String estatusRequisicion) {
        this.estatusRequisicion = estatusRequisicion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

}
