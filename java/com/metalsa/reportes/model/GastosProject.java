package com.metalsa.reportes.model;

import java.io.Serializable;
import java.util.Date;

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
 * @author diego.juarez
 */
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
            name = "getGastosProject",
            procedureName = "NVC_PKG_REPORTES_SPX.GET_GASTOS_PROJECT",
            resultClasses = GastosProject.class,
            parameters = {
                @StoredProcedureParameter(type = Integer.class, name = "p_uen", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = Integer.class, name = "p_periodo_inicial", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = Integer.class, name = "p_periodo_final", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = String.class, name = "p_id_proyecto", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = String.class, name = "p_idioma", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = Void.class, name = "p_ref_cursor", mode = ParameterMode.REF_CURSOR)
            }
    ),
    @NamedStoredProcedureQuery(
            name = "getGastosProyecto",
            procedureName = "NVC_PKG_REPORTES_SPX.GET_GASTOS_PROYECTO",
            resultClasses = GastosProject.class,
            parameters = {
                @StoredProcedureParameter(type = Integer.class, name = "p_uen", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = Integer.class, name = "p_periodo_inicial", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = Integer.class, name = "p_periodo_final", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = Integer.class, name = "p_id_proyecto", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = String.class, name = "p_idioma", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = String.class, name = "p_tareas", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = String.class, name = "p_tipos_gasto", mode = ParameterMode.IN),
                @StoredProcedureParameter(type = Void.class, name = "p_ref_cursor", mode = ParameterMode.REF_CURSOR)
            }
    )
})
@Entity
@Getter
@Setter
public class GastosProject implements Serializable{
    
    @Id
    private Long id;
    
    @Column(name="proyecto_tarea")
    private String proyectoTarea;
    
    @Column(name="fecha_solicitud")
    private String fechaSolicitud;
    
    @Column(name="fecha_preaprobacion")
    private String fechaPreaprobacion;
    
    @Column(name="fecha_aprobacion")
    private String fechaAprobacion;
    
    @Column(name="folio")
    private Long folio;
    
    @Column(name="fuente")
    private String fuente;
    
    @Column(name="tipo_requisicion")
    private String tipoRequisicion;
    
    @Column(name="ora_requisicion")
    private String oraRequisicion;
    
    @Column(name="id_requisicion")
    private Long idRequisicion;
    
    @Column(name="id_partida")
    private Long id_partida;
    
    @Column(name="proveedor")
    private String proveedor;
    
    @Column(name="orden_compra")
    private Long orden_compra;
    
    @Column(name="descripcion")
    private String descripcion;
    
    @Column(name="cantidad")
    private Double cantidad;
    
    @Column(name="precio")
    private Double precio;
    
    @Column(name="monto")
    private Double monto;
    
    @Column(name="moneda")
    private String moneda;
    
    @Column(name="moneda_local")
    private String monedaLocal;
     
    @Column(name="pre_aprobador")
    private String preAprobador;
    
    @Column(name="aprobador")
    private String aprobador;
    
    @Column(name="solicitante")
    private String solicitante;
    
    @Column(name="tipo_gasto")
    private String tipoGasto;
    
    @Column(name="concaten")
    private String concaten;
    
    @Column(name="estatus")
    private String estatus;
    
    @Column(name = "categoria")
    private String categoria;
    
    @Column(name = "familia")
    private String familia;
    
    @Column(name = "subfamilia")
    private String subfamilia;

    @Column(name = "comprometido")
    private Double comprometido;

    @Column(name = "erogado_recibido")
    private Double erogadoRecibido;
    
    @Column(name = "erogado_facturado")
    private Double erogadoFacturado;
    
    @Column(name = "orden_compra_linea")
    private String ordenCompraLinea;
    
    @Column(name = "factura")
    private String factura;
    
    @Transient
    private Double total;
    
     public Double getTotal(){
        total = this.getComprometido() + this.getErogadoRecibido() + this.erogadoFacturado;
        return this.total;
    }
   
}
