package com.metalsa.aprobacion.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
@Entity
@Data
@AllArgsConstructor
@SqlResultSetMappings({
    @SqlResultSetMapping(
            name = "SuplierCompareConstructor",
            classes = {
                @ConstructorResult(
                        targetClass = SuplierCompareDto.class,
                        columns = {
                                        @ColumnResult(name = "id_linea", type = Long.class),
                                        @ColumnResult(name = "id_proveedor", type = Long.class),
                                        @ColumnResult(name = "nombre_proveedor", type = String.class),
                                        @ColumnResult(name = "terminos_especiales_pago", type = String.class),
                                        @ColumnResult(name = "precio_unitario", type = Double.class),
                                        @ColumnResult(name = "cantidad_requerida", type = Double.class),
                                        @ColumnResult(name = "gasto_adicional", type = Double.class),
                                        @ColumnResult(name = "importe", type = Double.class),
                                        @ColumnResult(name = "moneda", type = String.class),
                                        @ColumnResult(name = "cambio_moneda", type = Double.class),
                                        @ColumnResult(name = "incoterm", type = String.class),
                                        @ColumnResult(name = "tiempo_entrega", type = Integer.class),
                                        @ColumnResult(name = "seleccion_comprador", type = Boolean.class),
                                        @ColumnResult(name = "seleccion_requisitor", type = Boolean.class)
                        }
                )
            }
    )
})
//<ERM#17475>
@NamedNativeQueries({
    @NamedNativeQuery(
            name = "SuplierCompareDto.getAllByRequisicion",
            resultSetMapping = "SuplierCompareConstructor",
            query = "select "
            + "	rfql.id_linea,  "
            + "	nvl(s.id_proveedor,pt.id_proveedor) id_proveedor,  "
            + "	decode(s.id_proveedor, null, pt.nombre, s.nombre_proveedor) nombre_proveedor,  "
            + "	nvl(rfqp.terminos_especiales_pago, s.terminos) terminos_especiales_pago,  "
            + "	rlp.precio_unitario,  "
            + "	d.cantidad_requerida,  "
            + "	get_adds_expenses_by_suplier(rfql.id_requisicion, rfql.id_linea, rlp.id_req_linea_prov) gasto_adicional,  "
            + "	d.cantidad_requerida * rlp.precio_unitario + get_adds_expenses_by_suplier(rfql.id_requisicion, rfql.id_linea, rlp.id_req_linea_prov) importe,  "
            + "	rlp.moneda,  "
            + "	tipo_cambio_f(rlp.moneda, d.id_uen) cambio_moneda,  "
            + "	( "
            + "		select i.descripcion  "
            + "		from nvc_tbl_incoterm i  "
            + "		where i.id_incoterm = rfq.id_incoterm "
            + "	) incoterm,  "
            + "	rlp.tiempo_entrega,  "
            + "	decode(rlp.id_razon_sel_cotizacion, null, 0, 1) seleccion_comprador,  "
            + "	decode(rlp.razon_cotiza, null, 0, 1) seleccion_requisitor  "
            + "from nvc_tbl_rfq_linea rfql  "
            + "	join detalle_de_requisicion d  "
            + "		on rfql.id_requisicion = d.id_requisicion  "
            + "		and rfql.id_linea = d.id_partida  "
            + "		and d.id_estatus = ?2  "
            + "	join nvc_tbl_rfq rfq  "
            + "		on rfql.id_rfq = rfq.id_rfq  "
            + "	join nvc_tbl_req_linea_prov rlp  "
            + "		on rfql.llave_id = rlp.llave_id  "
            + "		and rlp.id_estatus in (28, 48)  "
            + "	join nvc_tbl_rfq_prov rfqp  "
            + "		on rlp.id_rfq_prov = rfqp.llave_id  "
            + "	left join nvc_vm_oa_proveedores_sites s  "
            + "		on s.id_proveedor = rfqp.id_prov_interno  "
            + "		and s.id_sucursal_proveedor = rfqp.id_site "
            + " left join nvc_tbl_prov_temporal pt  "
            + "     on rfqp.id_prov_externo = pt.id_proveedor "
            + "where rfql.id_requisicion = ?1 "
//            + "	and rfql.id_linea in (?2) "
            + "order by id_proveedor, rfql.id_linea asc"
    )
    //</ERM#17475>
})
public class SuplierCompareDto implements Serializable {
    @Id
    private Long idLinea;
    private Long idProveedor;
    private String nombreProveedor;
    private String terminosEspecialesPago;
    private Double precioUnitario;
    private Double cantidadRequerida;
    private Double gastoAdicional;
    private Double importe;
    private String moneda;
    private Double cambioMoneda;
    private String incoterm;
    private Integer tiempoEntrega;
    private boolean seleccionComprador;
    private boolean seleccionRequisitor;
}
