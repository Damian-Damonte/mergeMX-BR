/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.reportes.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Oscar del Angel
 */
@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Movimientos implements Serializable {

    @Id
    @Column(name = "trx_id_destino")
    protected Long trxIdDestino;

    @Column(name = "num_solicitud")
    protected Long numSolicitud;

    @Column(name = "partida")
    protected Integer partida;

    @Column(name = "id_solicitud_linea")
    protected Integer idSolicitudLinea;

    @Column(name = "razon")
    protected String razon;

    @Column(name = "solicitante")
    protected String solicitante;

    @Column(name = "aprobador_cc")
    protected String aprobadorCC;

    @Column(name = "aprobador_finanzas")
    protected String aprobadorFinanzas;

    @Column(name = "fecha_solicitud")
    protected String fechaSolicitud;

    @Column(name = "fecha_aprobacion")
    protected String fechaAprobacion;

    /*aqui empieza las propiedas de transacciones por solicitud*/
    @Column(name = "nombre_uen_destino")
    protected String nombreUenDestino;

    @Column(name = "estatus")
    protected String estatus;

    @Column(name = "razon_rechazo")
    protected String razonRechazo;

    @Column(name = "moneda")
    protected String moneda;

    @Column(name = "fecha_preaprobacion")
    protected String fechaPreaprobacion;

    @Column(name = "fecha_necesidad")
    protected String fechaNecesidad;

    @Column(name = "saldo_original_destino")
    protected Double saldoDestino;

    @Column(name = "responsable_cc")
    protected String responsableCC;

    @Column(name = "monto")
    protected Double monto;

    @Column(name = "tipo_transaccion")
    protected String tipoTransaccion;

    @Column(name = "total_adjuntos")
    protected Integer totalAdjuntos;

    @Column(name = "usuario_creacion")
    protected String creador;

    @Column(name = "origen")
    protected String origen;

    @Transient
    protected Double saldoFinalDestino;

    @Transient
    protected Double porcentajeDestino;

    public Movimientos() {

    }

    public Movimientos(
            Long trxIdDestino,
            Long numSolicitud,
            Integer partida,
            Integer idSolicitudLinea,
            String razon,
            String creador,
            String solicitante,
            String aprobadorCC,
            String aprobadorFinanzas,
            String responsableCC,
            String fechaSolicitud,
            String fechaAprobacion,
            Double monto,
            String tipoTransaccion,
            Integer totalAdjuntos,
            String nombreUenDestino
    ) {
        this.trxIdDestino = trxIdDestino;
        this.numSolicitud = numSolicitud;
        this.partida = partida;
        this.idSolicitudLinea = idSolicitudLinea;
        this.razon = razon;
        this.creador = creador;
        this.solicitante = solicitante;
        this.aprobadorCC = aprobadorCC;
        this.aprobadorFinanzas = aprobadorFinanzas;
        this.responsableCC = responsableCC;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaAprobacion = fechaAprobacion;
        this.monto = monto;
        this.tipoTransaccion = tipoTransaccion;
        this.totalAdjuntos = totalAdjuntos;
        this.nombreUenDestino = nombreUenDestino;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.trxIdDestino);
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
        if (!Objects.equals(getClass(), obj.getClass())) {
            return false;
        }
        final Movimientos other = (Movimientos) obj;
        return Objects.equals(this.trxIdDestino, other.trxIdDestino);
    }

    public Double getSaldoFinalDestino() {
        if (this.saldoDestino != null) {
            this.saldoFinalDestino = this.saldoDestino + this.getMonto() *("ADD BUDGET AMOUNT".equals(this.tipoTransaccion)?1:-1);
        }
        return this.saldoFinalDestino;
    }

    public Double getPorcentajeDestino() {
        if (this.saldoDestino != null && this.saldoDestino != 0) {
            this.porcentajeDestino = (this.getMonto() * 100) / this.saldoDestino;
        } else {
            this.porcentajeDestino = 0.0;
        }
        return this.porcentajeDestino;
    }
}
