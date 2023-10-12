/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.requisicion.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 *
 * @author jose.jimenez07
 */
@Entity
@Table(name = "NVC_TBL_DISTRIBUCION_REQUI")
public class DistribucionRequisicion implements Serializable {
    @Column(name = "ID_DISTRIBUCION_REQUI")
    @Id
    @SequenceGenerator(
            name = "NVC_TBL_DISTRIBUCION_REQUI_SEQ",
            sequenceName = "NVC_TBL_DISTRIBUCION_REQUI_SEQ",
            allocationSize = 1
    )
    @GeneratedValue(generator = "NVC_TBL_DISTRIBUCION_REQUI_SEQ")
    @Basic(optional = false)
    @NotNull
    private Integer idDistribucionRequi;

    @Column(name = "ID_REQUISICION")
    private Integer idRequisicion;

    @Column(name = "ID_PARTIDA")
    private Integer idPartida;

    @Column(name = "ID_ESTATUS")
    private Integer idEstatus;

    @Column(name = "ID_CUENTA")
    private Integer idCuenta;

    @Column(name = "MONTO")
    private Double monto;

    @Column(name = "PORCENTAJE")
    private Double porcentaje;

    @Column(name = "CREATED_BY")
    private String createBy;

    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.DATE)
    private Date lastUpdateDate;

    @Column(name = "APPROVED_BY")
    private String approvedBy;

    @Column(name = "APPROVED_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date approvedDate;

    @Column(name = "SELECCION")
    private String seleccion;

    @Column(name = "Id_cc")
    private Integer idCc;
    
    @Transient
    private String codigoCc;

    @JoinColumns({
        @JoinColumn(name = "ID_REQUISICION", referencedColumnName = "ID_REQUISICION", insertable = false, updatable = false)
        ,
        @JoinColumn(name = "ID_PARTIDA", referencedColumnName = "ID_PARTIDA", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DetalleDeRequisicion detalleDeRequisicion;

    public Integer getIdDistribucionRequi() {
        return idDistribucionRequi;
    }

    public void setIdDistribucionRequi(Integer idDistribucionRequi) {
        this.idDistribucionRequi = idDistribucionRequi;
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

    public Integer getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(Integer idEstatus) {
        this.idEstatus = idEstatus;
    }

    public Integer getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Date getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(Date approvedDate) {
        this.approvedDate = approvedDate;
    }

    public String getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(String seleccion) {
        this.seleccion = seleccion;
    }

    public DetalleDeRequisicion getDetalleDeRequisicion() {
        return detalleDeRequisicion;
    }

    public void setDetalleDeRequisicion(DetalleDeRequisicion detalleDeRequisicion) {
        this.detalleDeRequisicion = detalleDeRequisicion;
    }

    public Integer getIdCc() {
        return idCc;
    }

    public void setIdCc(Integer idCc) {
        this.idCc = idCc;
    }

    public String getCodigoCc() {
        return codigoCc;
    }

    public void setCodigoCc(String codigoCc) {
        this.codigoCc = codigoCc;
    }

}
