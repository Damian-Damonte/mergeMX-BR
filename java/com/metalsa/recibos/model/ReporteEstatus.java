/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import static javax.persistence.ParameterMode.IN;
import static javax.persistence.ParameterMode.REF_CURSOR;
import javax.persistence.StoredProcedureParameter;

/**
 *
 * @author yair.nunez
 */
@Entity
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
            name = "obtenerResumenReportes",
            resultClasses = ReporteEstatus.class,
            procedureName = "NVC_RECIBOS_SPX_PKG.getReporteMaestro",
            parameters = {
                @StoredProcedureParameter(mode = REF_CURSOR, name = "V_CURSOR", type = void.class),
                @StoredProcedureParameter(mode = IN, name = "p_id_usuario", type = String.class)
            }
    )
})
@IdClass(ReporteEstatus.class)
public class ReporteEstatus implements Serializable {
    @Id
    @Column(name = "ID_REPORT_RECIBO_MASTER")
    private Integer idReporte;
    
    @Column(name = "FECHA_CREACION")
    private String fechaReporte;
    
    @Column(name = "NOMBRE_REPORTE")
    private String nombreReporte;
    
    @Column(name = "PROCESADO")
    private String procesado;

    public ReporteEstatus() {
    }

    public Integer getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(Integer idReporte) {
        this.idReporte = idReporte;
    }

    public String getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(String fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public String getNombreReporte() {
        return nombreReporte;
    }

    public void setNombreReporte(String nombreReporte) {
        this.nombreReporte = nombreReporte;
    }

    public String getProcesado() {
        return procesado;
    }

    public void setProcesado(String procesado) {
        this.procesado = procesado;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.idReporte);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ReporteEstatus other = (ReporteEstatus) obj;
        return Objects.equals(this.idReporte, other.idReporte);
    }
    
    

    @Override
    public String toString() {
        return "ReporteEstatus{" + "idReporte=" + idReporte + ", fechaReporte=" + fechaReporte + ", nombreReporte=" + nombreReporte + ", procesado=" + procesado + '}';
    }
    
    
}
