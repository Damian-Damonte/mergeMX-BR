package com.metalsa.aprobacion.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
@Entity
@Data
@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "LineaPorConstructor",
                classes = {
                        @ConstructorResult(
                                targetClass = LineCompare.class,
                                columns = {
                                        @ColumnResult(name = "linea", type = Long.class),
                                        @ColumnResult(name = "descripcion_producto", type = String.class),
                                        @ColumnResult(name = "fecha_necesidad", type = Date.class),
                                        @ColumnResult(name = "cantidad", type = Float.class),
                                        @ColumnResult(name = "uom"),
                                        @ColumnResult(name = "razon_seleccion_comprador"),
                                        @ColumnResult(name = "razon_seleccion_requisitor"),
                                        @ColumnResult(name = "sitio_proveedor"),
                                        @ColumnResult(name = "estatus_linea_rfq"),
                                        @ColumnResult(name = "capturado_por"),
                                        @ColumnResult(name = "enviar_para"),
                                        @ColumnResult(name = "facturar_en"),
                                        @ColumnResult(name = "medio_transporte"),
                                        @ColumnResult(name = "num_cotizacion", type = Long.class),
                                        @ColumnResult(name = "terminos_especiales_pago")
                                }
                        )
                }
        )
})
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "LineCompare.getAllByRequisicion",
                resultSetMapping = "LineaPorConstructor",
                query = "select" +
                        "  d.id_partida as linea," +
                        "  d.descripcion_producto," +
                        "  d.fecha_requerida as fecha_necesidad," +
                        "  d.cantidad_requerida as cantidad," +
                        "  d.id_unidad_de_medida as uom," +
                        "  get_seleccion_comprador(d.id_requisicion, d.id_partida, ?2) as razon_seleccion_comprador," +
                        "  get_razon_seleccion(d.razon_seleccion_cotizacion, ?2) as razon_seleccion_requisitor," +
                        "  ps.vendor_site_code as sitio_proveedor," +
                        "  get_estatus(rfql.id_estatus, ?2) as estatus_linea_rfq," +
                        "  rlp.capturado_por," +
                        "  rfq.ship_to as enviar_para," +
                        "  rfq.bll_to as facturar_en, " +
                        "  (select cm.descripcion" +
                        "   from nvc_tbl_medio m, nvc_tbl_cat_medio cm" +
                        "   where m.id_medio = rfq.id_medio" +
                        "         and cm.id_cat_medio = m.id_cat_medio" +
                        "         and m.id_uen = d.id_uen) as medio_transporte," +
                        "  rfqp.id_cotizacion num_cotizacion, " +
                        "  rfqp.terminos_especiales_pago " +
                        "from detalle_de_requisicion d" +
                        "  join nvc_tbl_rfq_linea rfql" +
                        "    on rfql.id_requisicion = d.id_requisicion" +
                        "       and rfql.id_linea = d.id_partida" +
                        "  join nvc_tbl_rfq rfq on rfql.id_rfq = rfq.id_rfq" +
                        "  join nvc_tbl_req_linea_prov rlp" +
                        "    on rfql.llave_id = rlp.llave_id" +
                        "  join nvc_tbl_rfq_prov rfqp" +
                        "    on rlp.id_rfq_prov = rfqp.llave_id" +
                        "  join nvc_tbl_prov_sites_h ps" +
                        "    on rfqp.id_site = ps.id_sucursal_proveedor " +
                        "where d.id_requisicion = ?1 " +
                        "  and d.id_estatus = ?3 " + //<ERM#17475/>
                        "  and rlp.id_estatus = 28 " +
                        "order by d.id_partida asc"
        )
})
public class LineCompare implements Serializable {
    @Id
    private Long linea;
    private String descripcionProducto;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaNecesidad;
    private Float cantidad;
    private String uom;
    @Column(name = "razon_seleccion_comprador")
    private String razonSeleccionComprador;
    private String razonSeleccionRequisitor;
    private String sitioProveedor;
    private String estatusLineaRfq;
    private String capturadoPor;
    private String enviarPara;
    private String facturarEn;
    private String medioTransporte;
    private Long numeroCotizacion;
    private boolean terminosEspecialesPago;
    private String especificador;


    public LineCompare() {
    }

    public LineCompare(Long linea, String descripcionProducto, Date fechaNecesidad, Float cantidad,
                       String uom, String razonSeleccionComprador, String razonSeleccionRequisitor,
                       String sitioProveedor, String estatusLineaRfq, String capturadoPor, String enviarPara,
                       String facturarEn, String medioTransporte, Long numeroCotizacion, String terminosEspecialesPago) {
        this.linea = linea;
        this.descripcionProducto = descripcionProducto;
        this.fechaNecesidad = new Date(fechaNecesidad.getTime());
        this.cantidad = cantidad;
        this.uom = uom;
        this.razonSeleccionComprador = razonSeleccionComprador;
        this.razonSeleccionRequisitor = razonSeleccionRequisitor;
        this.sitioProveedor = sitioProveedor;
        this.estatusLineaRfq = estatusLineaRfq;
        this.capturadoPor = capturadoPor;
        this.enviarPara = enviarPara;
        this.facturarEn = facturarEn;
        this.medioTransporte = medioTransporte;
        this.numeroCotizacion = numeroCotizacion;
        this.terminosEspecialesPago = StringUtils.isNotBlank(terminosEspecialesPago);
        this.especificador = terminosEspecialesPago;
    }

    public Date getFechaNecesidad() {
        return fechaNecesidad == null ? null : (Date) fechaNecesidad.clone();
    }

    public void setFechaNecesidad(Date fechaNecesidad) {
        if (fechaNecesidad == null)
            this.fechaNecesidad = null;
        else
            this.fechaNecesidad = (Date) fechaNecesidad.clone();
    }
}
