package com.metalsa.aprobacion.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Clase para mapeo de detalle de requisicion
 */
@Data
@Entity(name = "detalle_de_requisicion")
@IdClass(DetalleRequisicion.Key.class)
@SqlResultSetMapping(name = "updateResult", columns = {
    @ColumnResult(name = "count")})
@NamedNativeQueries({
    @NamedNativeQuery(
            name = "DetalleRequisicion.getDetalleRequisicion",
            resultClass = DetalleRequisicion.class,
            query = "SELECT "
            + "  detalle.* "
            + "FROM detalle_de_requisicion detalle "
            + "WHERE detalle.id_requisicion = ?1 "
            + "AND detalle.id_partida = ?2"
    )
    ,
        @NamedNativeQuery(
            name = "DetalleRequisicion.updateCuenta",
            query = "UPDATE detalle_de_requisicion "
            + "SET "
            + "    segmento_1 = ?3, "
            + "    segmento_2 = ?4, "
            + "    segmento_3 = ?5, "
            + "    segmento_4 = ?6, "
            + "    segmento_5 = ?7, "
            + "    segmento_6 = ?8, "
            + "    segmento_7 = ?9, "
            + "    segmento_8 = ?10, "
            + "    id_cuenta = ?11 "
            + "WHERE id_requisicion = ?1 "
            + "AND id_partida = ?2"
    )
})
public class DetalleRequisicion implements Serializable {

    private static final long serialVersionUID = 42L;
    @Id
    @Column(name = "id_requisicion")
    private Long requisicion;

    @Id
    @Column(name = "id_partida")
    private Long linea;

    @Column(name = "id_producto")
    private String producto;

    @Column(name = "DESCRIPCION_PRODUCTO")
    private String descripcionProducto;

    @Column(name = "MONTO_EXTENDIDO")
    private Double montoExtendido;
    
    @Column(name = "PRECIO")
    private Double precio;
    
    @Column(name = "CANTIDAD_ENTREGADA")
    private Integer cantidadEntregada;

    @Column(name = "id_unidad_de_medida")
    private String unidadMedida;

    @Column(name = "id_familia")
    private Long familia;

    @Column(name = "ATENDIDO_POR")
    private String atendidoPor;

    @Column(name = "FECHA_REQUERIDA")
    private Date fechaNecesidad;

    @Column(name = "SIGUIENTE_APROBADOR")
    private String siguienteAprobador;
    
    @Column(name = "COMPRADOR")
    private String comprador;

    @Column(name = "id_moneda")
    private String moneda;

    @Column(name = "estatus")
    private String estatusDescripcion;

    private Date fechaLimiteCotizacion;

    @Column(name = "id_localizacion")
    private Long localizacion;

    @Column(name = "id_uen")
    private Long uen;

    @Column(name = "fecha_de_aprobacion")
    private Date fechaAprobacion;

    @Column(name = "URGENTE")
    private String urgente;
    
    @Column(name = "PASO_PRESUPUESTO")
    private String pasoPresupuesto;
    
    @Column(name = "RAZON_URGENCIA")
    private String razonUrgencia;
    
    @Column(name = "FUENTE")
    private String fuente;
    
    @Column(name = "USUARIO_ULTIMA_APROBACION")
    private String usuarioUltimaAprobacion;

    @Column(name = "aprobador_delegado_o_responsab")
    private String aprobador;
    
    @Column(name = "PRESUPUESTO_RESERVADO")
    private Double presupuestoReservado;

    @Column(name = "razon_de_rechazo_de_aprobacion")
    private String razonRechazoAprobacion;

    @Column(name = "CANTIDAD_REQUERIDA")
    private Float cantidadRequerida;
    @Column(name = "USUARIO_OCURRE")
    private String usuarioOcurre;

    @Column(name = "id_orden_de_compra")
    private Long ordenCompra;
    @Column(name = "id_partida_oc")
    private String partidaOc;

    @Column(name = "id_cuenta")
    private Long cuenta;
    private String segmento_1;
    private String segmento_2;
    private String segmento_3;
    private String segmento_4;
    private String segmento_5;
    private String segmento_6;
    private String segmento_7;
    private String segmento_8;

    private String estatusOc;
    private String seleccion;
    private String enviadaOa;
    private String comentariosAprobador;
    private String comentariosComprador;
    private String comentariosUsuario;
    @Column(name = "datos_de_auditoria")
    private String datosAuditoria;
    @Column(name = "id_contrato")
    private String contrato;
    @Column(name = "LEAD_TIME")
    private Integer leadTime;
    @Column(name = "razon_de_rechazo_en_almacen")
    private String razonRechazoAlmacen;
    private String valida;
    @Column(name = "DESCUENTO")
    private Double descuento;
    @Column(name = "PRECIO_BRUTO")
    private Double precioBruto;
    private String iva;

    private String id_Requisicion_Dest;
    @Column(name = "id_proyecto")
    private Long proyecto;
    @Column(name = "cod_proyecto")
    private String codigoProyecto;
    @Column(name = "id_tarea")
    private Long tarea;
    @Column(name = "cod_tarea")
    private String codigoTarea;
    @Column(name = "TIPO_GASTO")
    private String tipoGasto;
    @Column(name = "id_uen_surtidora")
    private Long uenSurtidora;
    private String numCotizacion;
    @Column(name = "id_request_impresion_oc")
    private String requestImpresionOc;
    private String vendorQuoteNumber;
    @Column(name = "VENDOR_PART_NUMBER")
    private String vendorPartNumber;
    @Column(name = "WORK_ORDER_NUMBER")
    private String workOrderNumber;
    
    @Column(name = "NOTES_TO_BUYER")
    private String notesToBuyer;
    private Long vmIdEmpleado;
    private String vmUsuarioCons;
    private String oraIdRequisicion;
    @Column(name = "cod_producto")
    private String codigoProducto;
    private String tipoLinea;
    private Long workOrderId;
    private Date fechaPromesaOc;
    private String enn;
    @Column(name = "id_subtarea")
    private String subtarea;
    @Column(name = "cod_subtarea")
    private String codigoSubtarea;
    @Column(name = "CATEGORY_ID")
    private Long categoryId;
    @Column(name = "USUARIO_APROBACION_CRITICA")
    private String usuarioAprobacionCritica;
    @Column(name = "RAZON_APROBACION_CRITICA")
    private String razonAprobacionCritica;
    @Column(name = "APPROVE_CODE")
    private Long approveCode;
    @Column(name = "USUARIO_RECIBE")
    private String usuarioRecibe;
    @Column(name = "RAZON_EXCESO_PPTO")
    private String razonExcesoPpto;
    @Column(name = "SOLUCION_EXCESO_PPTO")
    private String solucionExcesoPpto;

    private String aprobacionConExceso;
    @Column(name = "last_update_date")
    private Date lastUpdate;
    private String referencia_1;
    @Column(name = "gastos_de_importacion")
    private Double gastosImportacion;
    private Long razonSeleccionCotizacion;
    private String manufacturerpart;
    private String manufacturer;
    private String pCard;
    private String pCardSupplier;
    private String cardProgram;
    private String mccId;
    private String idItemgen;
    @Column(name = "id_estatus")
    private String estatus;
    @Column(name = "ID_CC")
    private String centroCosto;
    private String mejorOpcion;
    
    @Column(name = "ID_TIPO_CARGO")
    private Integer idTipoCargo;

    /**
     * Llave embebida para JPA
     */
    @Data
    public static class Key implements Serializable {

        @Id
        @Column(name = "id_requisicion")
        private Long requisicion;

        @Id
        @Column(name = "id_partida")
        private Long linea;
    }

    public Date getFechaNecesidad() {
        return fechaNecesidad == null ? null : (Date) fechaNecesidad.clone();
    }

    public void setFechaNecesidad(Date fechaNecesidad) {
        if (fechaNecesidad == null) {
            this.fechaNecesidad = null;
        } else {
            this.fechaNecesidad = (Date) fechaNecesidad.clone();
        }
    }

    public Date getFechaLimiteCotizacion() {
        return fechaLimiteCotizacion == null ? null : (Date) fechaLimiteCotizacion.clone();
    }

    public void setFechaLimiteCotizacion(Date fechaLimiteCotizacion) {
        if (fechaLimiteCotizacion == null) {
            this.fechaLimiteCotizacion = null;
        } else {
            this.fechaLimiteCotizacion = (Date) fechaLimiteCotizacion.clone();
        }
    }

    public Date getFechaAprobacion() {
        return fechaAprobacion == null ? null : (Date) fechaAprobacion.clone();
    }

    public void setFechaAprobacion(Date fechaAprobacion) {
        if (fechaAprobacion == null) {
            this.fechaAprobacion = null;
        } else {
            this.fechaAprobacion = (Date) fechaAprobacion.clone();
        }
    }

    public Date getFechaPromesaOc() {
        return fechaPromesaOc == null ? null : (Date) fechaPromesaOc.clone();
    }

    public void setFechaPromesaOc(Date fechaPromesaOc) {
        if (fechaPromesaOc == null) {
            this.fechaPromesaOc = null;
        } else {
            this.fechaPromesaOc = (Date) fechaPromesaOc.clone();
        }
    }

    public Date getLastUpdate() {
        return lastUpdate == null ? null : (Date) lastUpdate.clone();
    }

    public void setLastUpdate(Date lastUpdate) {
        if (lastUpdate == null) {
            this.lastUpdate = null;
        } else {
            this.lastUpdate = (Date) lastUpdate.clone();
        }
    }
}
