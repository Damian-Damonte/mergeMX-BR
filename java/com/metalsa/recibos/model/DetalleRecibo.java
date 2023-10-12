/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.model;

import java.io.Serializable;
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
            name = "getDetalleRecibo",
            resultClasses = DetalleRecibo.class,
            procedureName = "UTILERIAS_PKG.getDetalleFolios",
            parameters = {
                @StoredProcedureParameter(mode = REF_CURSOR, name = "V_CURSOR", type = void.class),
                @StoredProcedureParameter(mode = IN, name = "p_id_requisicion", type = Integer.class),
                @StoredProcedureParameter(mode = IN, name = "p_id_partida", type = Integer.class)
            }
    )
})
@IdClass(DetalleReciboPK.class)
public class DetalleRecibo implements Serializable {
    @Id
    @Column(name = "FOLIO")
    private Integer numeroRecibo;
    
    @Column(name = "FECHA_RECIBO")
    private String fechaRecibo;
    
    @Column(name = "CANTIDAD")
    private Double cantidadRecibida;
    
    @Column(name = "RECIBIDO_EN")
    private Integer recibidoEn;

    public DetalleRecibo() {
    }

    public Integer getNumeroRecibo() {
        return numeroRecibo;
    }

    public void setNumeroRecibo(Integer numeroRecibo) {
        this.numeroRecibo = numeroRecibo;
    }

    public String getFechaRecibo() {
        return fechaRecibo;
    }

    public void setFechaRecibo(String fechaRecibo) {
        this.fechaRecibo = fechaRecibo;
    }

    public Double getCantidadRecibida() {
        return cantidadRecibida;
    }

    public void setCantidadRecibida(Double cantidadRecibida) {
        this.cantidadRecibida = cantidadRecibida;
    }

    public Integer getRecibidoEn() {
        return recibidoEn;
    }

    public void setRecibidoEn(Integer recibidoEn) {
        this.recibidoEn = recibidoEn;
    }

    @Override
    public String toString() {
        return "DetalleRecibo{" + "numeroRecibo=" + numeroRecibo + ", fechaRecibo=" + fechaRecibo + ", cantidadRecibida=" + cantidadRecibida + ", recibidoEn=" + recibidoEn + '}';
    }
    
    
}
