package com.metalsa.reportes.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
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
            name = "getReporteEstadoCuenta",
            procedureName = "NVC_PKG_REPORTES_SPX.GET_REPORTE_ESTADO_CUENTA",
            resultClasses = EstadoCuenta.class,
            parameters = {
                @StoredProcedureParameter(type = Integer.class, name = "p_uen", mode = ParameterMode.IN)
                ,
                @StoredProcedureParameter(type = Integer.class, name = "p_periodo_inicial", mode = ParameterMode.IN)
                ,
                @StoredProcedureParameter(type = Integer.class, name = "p_periodo_final", mode = ParameterMode.IN)
                ,
                @StoredProcedureParameter(type = String.class, name = "p_id_cc", mode = ParameterMode.IN)
                ,
                @StoredProcedureParameter(type = String.class, name = "p_idioma", mode = ParameterMode.IN)
                ,
                @StoredProcedureParameter(type = Void.class, name = "p_ref_cursor", mode = ParameterMode.REF_CURSOR)
            }
    )
})
@Entity
@Getter
@Setter
public class EstadoCuenta implements Serializable {

    @Id
    private Long id;

    @Column(name = "id_requisicion")
    private Long requisicion;

    @Column(name = "orden_compra")
    private Long ordenCompra;

    @Column(name = "id_partida")
    private Integer partida;

    @Column(name = "cantidad")
    private Double cantidad;

    @Column(name = "precio")
    private Double precio;

    @Column(name = "monto")
    private Double monto;

    @Column(name = "erogado_facturado")
    private Double erogadoFacturado;

    @Column(name = "erogado_recibido")
    private Double erogadoRecibido;

    @Column(name = "comprometido")
    private Double comprometido;

    @Column(name = "categoria_cuenta")
    private String categoriaCuenta;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "proveedor")
    private String proveedor;

    @Column(name = "estatus")
    private String estatus;

    @Column(name = "ora_requisicion")
    private String oraRequisicion;

    @Column(name = "fecha_solicitud")
    private String fechaSolicitud;

    @Column(name = "fecha_preaprobacion")
    private String fechaPreAprobacion;

    @Column(name = "fecha_aprobacion")
    private String fechaAprobacion;

    @Column(name = "folio")
    private String folio;

    @Column(name = "tipo_requisicion")
    private String tipoRequisicion;

    @Column(name = "moneda")
    private String moneda;

    @Column(name = "moneda_local")
    private String moneda_local;

    @Column(name = "pre_aprobador")
    private String preAprobador;

    @Column(name = "aprobador")
    private String aprobador;

    @Column(name = "solicitante")
    private String solicitante;

    @Column(name = "cuenta_contable")
    private String cuentaContable;

    @Column(name = "nombre_cuenta")
    private String nombreCuenta;

    @Column(name = "concaten")
    private String concaten;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "familia")
    private String familia;

    @Column(name = "subfamilia")
    private String subfamilia;

    @Transient
    private Double total;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.id);
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
        final EstadoCuenta other = (EstadoCuenta) obj;
        return Objects.equals(this.id, other.id);
    }

    public Double getTotal() {
        total = this.getComprometido() + this.getErogadoRecibido() + this.getErogadoFacturado();
        return this.total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("aprobador:").append(this.aprobador == null ? "" : this.aprobador).append(" ")
                .append("cantidad:").append(this.cantidad == null ? "" : this.cantidad).append(" ")
                .append("categoria:").append(this.categoria == null ? "" : this.categoria).append(" ")
                .append("categoriaCuenta:").append(this.categoriaCuenta == null ? "" : this.categoriaCuenta).append(" ")
                .append("comprometido:").append(this.comprometido == null ? "" : this.comprometido).append(" ")
                .append("concaten:").append(this.concaten == null ? "" : this.concaten).append(" ")
                .append("cuentaContable:").append(this.cuentaContable == null ? "" : this.cuentaContable).append(" ")
                .append("descripcion:").append(this.descripcion == null ? "" : this.descripcion).append(" ")
                .append("erogadoFacturado:").append(this.erogadoFacturado == null ? "" : this.erogadoFacturado).append(" ")
                .append("erogadoRecibido:").append(this.erogadoRecibido == null ? "" : this.erogadoRecibido).append(" ")
                .append("estatus:").append(this.estatus == null ? "" : this.estatus).append(" ")
                .append("familia:").append(this.familia == null ? "" : this.familia).append(" ")
                .append("fechaAprobacion:").append(this.fechaAprobacion == null ? "" : this.fechaAprobacion).append(" ")
                .append("fechaSolicitud:").append(this.fechaSolicitud == null ? "" : this.fechaSolicitud).append(" ")
                .append("folio:").append(this.folio == null ? "" : this.folio).append(" ")
                .append("moneda:").append(this.moneda == null ? "" : this.moneda).append(" ")
                .append("ordenCompra:").append(this.ordenCompra == null ? "" : this.ordenCompra).append(" ")
                .append("partida:").append(this.partida == null ? "" : this.partida).append(" ")
                .append("requisicion:").append(this.requisicion == null ? "" : this.requisicion).append(" ")
                .append("total:").append(this.total == null ? "" : this.total).append(" ");
        return sb.toString();
//        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

}
