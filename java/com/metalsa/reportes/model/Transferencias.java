/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.reportes.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Oscar del Angel
 */
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
            name = "Transferencias.getReporteTransferencias",
            procedureName = "NVC_PKG_REPORTES_SPX.GET_REPORTE_TRANSFERENCIAS",
            resultClasses = Transferencias.class,
            parameters = {
                @StoredProcedureParameter(type = Long.class, name = "p_num_solicitud", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = String.class, name = "p_id_uens", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = String.class, name = "p_id_uens_origen", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = String.class, name = "p_id_uens_destino", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = String.class, name = "p_idioma", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = String.class, name = "p_show_all", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = String.class, name = "p_periodos", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = String.class, name = "p_creadores", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = String.class, name = "p_requisitores", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = String.class, name = "p_id_ccs", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = String.class, name = "p_id_ccs_origen", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = String.class, name = "p_id_ccs_destino", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = String.class, name = "p_categorias_origen", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = String.class, name = "p_categorias_destino", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = String.class, name = "p_fecha_inicial", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = String.class, name = "p_fecha_final", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = Void.class, name = "p_ref_cursor", mode = ParameterMode.REF_CURSOR)
            }
    )
    ,
    @NamedStoredProcedureQuery(
            name = "Transferencias.getReporteTransferenciasEdo",
            procedureName = "NVC_PKG_REPORTES_SPX.GET_REPORTE_TRANSFERENCIAS_EDO",
            resultSetMappings = "TransferenciasEdoConstructor",
            parameters = {
                @StoredProcedureParameter(type = Integer.class, name = "p_uen", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = Integer.class, name = "p_periodo_inicial", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = Integer.class, name = "p_periodo_final", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = String.class, name = "p_id_cc", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = String.class, name = "p_idioma", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = Void.class, name = "p_ref_cursor", mode = ParameterMode.REF_CURSOR)
            }
    )
})

@SqlResultSetMappings({
    @SqlResultSetMapping(
            name = "TransferenciasEdoConstructor",
            classes = @ConstructorResult(
                    targetClass = Transferencias.class,
                    columns = {
                        @ColumnResult(name = "trx_id_origen", type = Long.class),
                        @ColumnResult(name = "trx_id_destino", type = Long.class),
                        @ColumnResult(name = "num_solicitud", type = Long.class),
                        @ColumnResult(name = "partida", type = Integer.class),
                        @ColumnResult(name = "id_solicitud_linea", type = Integer.class),
                        @ColumnResult(name = "monto", type = Double.class),
                        @ColumnResult(name = "nombre_uen_origen", type = String.class),
                        @ColumnResult(name = "nombre_uen_destino", type = String.class),
                        @ColumnResult(name = "periodo_origen", type = String.class),
                        @ColumnResult(name = "periodo_destino", type = String.class),
                        @ColumnResult(name = "categoria_origen", type = String.class),
                        @ColumnResult(name = "categoria_destino", type = String.class),
                        @ColumnResult(name = "codigo_cc_origen", type = String.class),
                        @ColumnResult(name = "nombre_cc_origen", type = String.class),
                        @ColumnResult(name = "codigo_cc_destino", type = String.class),
                        @ColumnResult(name = "nombre_cc_destino", type = String.class),
                        @ColumnResult(name = "razon", type = String.class),
                        @ColumnResult(name = "usuario_creacion", type = String.class),
                        @ColumnResult(name = "solicitante", type = String.class),
                        @ColumnResult(name = "aprobador_cc", type = String.class),
                        @ColumnResult(name = "aprobador_finanzas", type = String.class),
                        @ColumnResult(name = "responsable_cc", type = String.class),
                        @ColumnResult(name = "fecha_solicitud", type = String.class),
                        @ColumnResult(name = "fecha_aprobacion", type = String.class),
                        @ColumnResult(name = "tipo_transaccion", type = String.class),
                        @ColumnResult(name = "total_adjuntos", type = Integer.class)
                    }
            ))
})
@Entity
@Getter
@Setter
public class Transferencias extends Movimientos implements Serializable {

    @Column(name = "trx_id_origen")
    private Long trxIdOrigen;    
    
    @Column(name = "periodo_origen")
    private String periodoOrigen;

    @Column(name = "nombre_uen_origen")
    private String nombreUenOrigen;

    @Column(name = "periodo_destino")
    private String periodoDestino;

    @Column(name = "categoria_origen")
    private String categoriaOrigen;

    @Column(name = "categoria_destino")
    private String categoriaDestino;

    @Column(name = "codigo_cc_origen")
    private String codigoCCOrigen;

    @Column(name = "nombre_cc_origen")
    private String nombreCCOrigen;

    @Column(name = "codigo_cc_destino")
    private String codigoCCDestino;

    @Column(name = "nombre_cc_destino")
    private String nombreCCDestino;

    @Column(name = "saldo_original_origen")
    private Double saldoOrigen;
    
    @Transient
    private  Double saldoFinalOrigen;

    @Transient
    private  Double porcentajeOrigen;


    public Transferencias() {
    }

    public Transferencias(
            Long trxIdOrigen, 
            Long trxIdDestino, 
            Long numSolicitud, 
            Integer partida, 
            Integer idSolicitudLinea,
            Double monto, 
            String nombreUenOrigen, 
            String nombreUenDestino, 
            String periodoOrigen, 
            String periodoDestino, 
            String categoriaOrigen, 
            String categoriaDestino, 
            String codigoCCOrigen, 
            String nombreCCOrigen, 
            String codigoCCDestino, 
            String nombreCCDestino, 
            String razon, 
            String creador, 
            String solicitante, 
            String aprobadorCC, 
            String aprobadorFinanzas, 
            String responsableCC, 
            String fechaSolicitud, 
            String fechaAprobacion, 
            String tipoTransaccion,
            Integer totalAdjuntos
    ) {
        super(
            trxIdDestino, 
            numSolicitud, 
            partida, 
            idSolicitudLinea, 
            razon, 
            creador,
            solicitante, 
            aprobadorCC, 
            aprobadorFinanzas,
            responsableCC,
            fechaSolicitud, 
            fechaAprobacion, 
            monto, 
            tipoTransaccion,
            totalAdjuntos,
            nombreUenDestino
        );
        this.nombreUenOrigen = nombreUenOrigen;
        this.trxIdOrigen = trxIdOrigen;
        this.periodoOrigen = periodoOrigen;
        this.periodoDestino = periodoDestino;
        this.categoriaOrigen = categoriaOrigen;
        this.categoriaDestino = categoriaDestino;
        this.codigoCCOrigen = codigoCCOrigen;
        this.nombreCCOrigen = nombreCCOrigen;
        this.codigoCCDestino = codigoCCDestino;
        this.nombreCCDestino = nombreCCDestino;
    }

//    @Override
//    public int hashCode() {
//        int hash = 3;
//        hash = 79 * hash + Objects.hashCode(this.trxIdOrigen);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final Transferencias other = (Transferencias) obj;
//        return Objects.equals(this.trxIdOrigen, other.trxIdOrigen);
//    }
    
    
    public Double getSaldoFinalOrigen() {
        if (this.saldoOrigen != null) {
            this.saldoFinalOrigen = this.saldoOrigen + this.getMonto();
        }
        return this.saldoFinalOrigen;
    }

    public Double getPorcentajeOrigen() {
        if (this.saldoOrigen != null) {
            this.porcentajeOrigen = (this.getMonto() * 100) / this.saldoOrigen;
        }
        return this.porcentajeOrigen;
    }    
}
