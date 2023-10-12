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
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Oscar del Angel
 */

@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
            name = "Incrementos.getReporteIncremento",
            procedureName = "NVC_PKG_REPORTES_SPX.GET_REPORTE_INCREMENTOS",
            resultClasses = Incrementos.class,
            parameters = {
                @StoredProcedureParameter(type = Long.class,name = "p_num_solicitud",mode = ParameterMode.IN),
                @StoredProcedureParameter(type = String.class,name = "p_id_uens",mode = ParameterMode.IN),
                @StoredProcedureParameter(type = String.class,name = "p_idioma",mode = ParameterMode.IN),
                @StoredProcedureParameter(type = String.class,name = "p_id_ccs",mode = ParameterMode.IN),
                @StoredProcedureParameter(type = String.class,name = "p_requisitores",mode = ParameterMode.IN),
                @StoredProcedureParameter(type = String.class,name = "p_usuarios",mode = ParameterMode.IN),
                @StoredProcedureParameter(type = String.class,name = "p_show_all",mode = ParameterMode.IN),
                @StoredProcedureParameter(type = String.class,name = "p_categorias",mode = ParameterMode.IN),
                @StoredProcedureParameter(type = String.class,name = "p_fecha_inicial",mode = ParameterMode.IN),
                @StoredProcedureParameter(type = String.class,name = "p_fecha_final",mode = ParameterMode.IN),
                @StoredProcedureParameter(type = Integer.class,name = "p_periodo_inicial",mode = ParameterMode.IN),
                @StoredProcedureParameter(type = Integer.class,name = "p_periodo_final",mode = ParameterMode.IN),
                @StoredProcedureParameter(type = Void.class,name = "p_ref_cursor",mode = ParameterMode.REF_CURSOR)
            }
    ),
    @NamedStoredProcedureQuery(
            name = "Incrementos.getReporteIncrementoEdo",
            procedureName = "NVC_PKG_REPORTES_SPX.GET_REPORTE_INCREMENTOS_EDO",
            resultSetMappings = "IncrementosEdoConstructor",
            parameters = {
                @StoredProcedureParameter(type = Integer.class,name = "p_uen",mode = ParameterMode.IN),
                @StoredProcedureParameter(type = Integer.class,name = "p_periodo_inicial",mode = ParameterMode.IN),
                @StoredProcedureParameter(type = Integer.class,name = "p_periodo_final",mode = ParameterMode.IN),
                @StoredProcedureParameter(type = String.class,name = "p_id_cc",mode = ParameterMode.IN),
                @StoredProcedureParameter(type = String.class,name = "p_idioma",mode = ParameterMode.IN),
                @StoredProcedureParameter(type = Void.class,name = "p_ref_cursor",mode = ParameterMode.REF_CURSOR)
            }
    )
})

@SqlResultSetMappings({
        @SqlResultSetMapping(
            name = "IncrementosEdoConstructor",
            classes = @ConstructorResult(
                targetClass = Incrementos.class,
                columns = {
                    @ColumnResult(name = "trx_id_destino", type = Long.class),
                    @ColumnResult(name = "num_solicitud", type = Long.class),
                    @ColumnResult(name = "partida", type = Integer.class),
                    @ColumnResult(name = "id_solicitud_linea", type = Integer.class),
                    @ColumnResult(name = "monto", type = Double.class),
                    @ColumnResult(name = "nombre_uen", type = String.class),
                    @ColumnResult(name = "periodo", type = String.class),
                    @ColumnResult(name = "categoria", type = String.class),
                    @ColumnResult(name = "tipo_transaccion", type = String.class),
                    @ColumnResult(name = "razon", type = String.class),
                    @ColumnResult(name = "usuario_creacion", type = String.class),
                    @ColumnResult(name = "solicitante", type = String.class),
                    @ColumnResult(name = "aprobador_cc", type = String.class),
                    @ColumnResult(name = "aprobador_finanzas", type = String.class),
                    @ColumnResult(name = "responsable_cc", type = String.class),
                    @ColumnResult(name = "fecha_solicitud", type = String.class),
                    @ColumnResult(name = "fecha_aprobacion", type = String.class),
                    @ColumnResult(name = "total_adjuntos", type = Integer.class)
                }
        ))
})
@Entity
@Getter
@Setter
public class Incrementos extends Movimientos implements Serializable{

   
    @Column(name = "periodo")
    private String periodo;

    @Column(name = "categoria")
    private String categoria;

    /*aqui empieza las propiedas de transacciones por solicitud*/
 
    @Column(name = "codigo_cc")
    private String codigoCC;

    @Column(name = "nombre_cc")
    private String nombre_cc;

    public Incrementos() {
        super();
    }

    public Incrementos(
            Long trxIdDestino, 
            Long numSolicitud, 
            Integer partida, 
            Integer idSolicitudLinea, 
            Double monto, 
            String nombreUen,
            String periodo, 
            String categoria, 
            String tipoTransaccion, 
            String razon, 
            String creador, 
            String solicitante, 
            String aprobadorCC, 
            String aprobadorFinanzas, 
            String responsableCC, 
            String fechaSolicitud, 
            String fechaAprobacion,
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
            nombreUen
        );
        this.periodo = periodo;
        this.categoria = categoria;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.trxIdDestino);
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
        final Incrementos other = (Incrementos) obj;
        return Objects.equals(this.trxIdDestino, other.trxIdDestino);
    }
 
    
    
    
    
}
