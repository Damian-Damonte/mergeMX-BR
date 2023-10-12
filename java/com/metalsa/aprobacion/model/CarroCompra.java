package com.metalsa.aprobacion.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

import javax.persistence.*;
import java.util.Date;

/**
 * Modelo de los items del carro de compra
 *
 * @author ruben.bresler
 */
@Entity(name = "nvc_tbl_carro_compra")
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@SqlResultSetMappings({
    @SqlResultSetMapping(
                name="PreviewMapping",
                classes={
                @ConstructorResult(
                                targetClass=CarroCompra.class,
                                columns={
                                        @ColumnResult(name="id_carro_compra", type = LongType.class),
                                        @ColumnResult(name="descripcion", type = StringType.class),
                                        @ColumnResult(name="cantidad", type = DoubleType.class),
                                        @ColumnResult(name="item", type = LongType.class),
                                        @ColumnResult(name="udm", type = StringType.class),
                                        @ColumnResult(name="tipo_item", type = LongType.class),
                                        @ColumnResult(name="url_img", type = StringType.class)
                        }
                )
            }
    )
})
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "getPreviewCarroCompraInterUen",
                resultClass = CarroCompra.class,
                resultSetMapping = "PreviewMapping",
                query = "select id_carro_compra, descripcion, cantidad, " +
                        "  case " +
                        "    when id_item is null then id_item_almacen " +
                        "    else id_item " +
                        "  end as item, " +
                        "  udm, " +
                        "  case when id_item is not null then " +
                        "    case when cc.punchout = 1 then 8 else 1 end " +
                        "    when id_item_almacen is not null AND NVL(CC.PEDIDO_ESPECIAL,0)=0 then 2 " +
                        "    when (id_item is null and id_item_almacen is null and (fuente is null or fuente = 'C'))  AND NVL(CC.PEDIDO_ESPECIAL,0)=0 then 3 " +
                        "    when fuente = 'V' then 4 " +
                        "    when fuente = 'I' then 5 "+
                        "    WHEN FUENTE = 'C' AND NVL(CC.PEDIDO_ESPECIAL,0)=1 THEN 9"+
                        "    else null " +
                        "  end as tipo_item, " +
                        "  case " +
                        "    when id_item is null then " +
                        "       (select idoc.url_ftp || 'thumb.' || idoc.nombre_archivo_ftp || idoc.extension_archivo " +
                        "       from nvc_tbl_catalogo_item_doc idoc, nvc_vm_oa_existencias ex " +
                        "       where 1 = 1 " +
                        "       and upper (idoc.cod_producto) = upper (ex.cod_producto) " +
                        "       and idoc.id_uen = ex.id_uen and idoc.id_uen=cc.id_uen " +
                        "       and upper (ex.id_producto) = upper (cc.id_item_almacen) " +
                        "       and idoc.posicion = 1 " +
                        "       and idoc.id_tipo_documento = 1 and rownum = 1) " + //<LOOKNFEEL>
                        "    when cc.punchout = 1 then " +
                        "       (select idoc.url_ftp " +
                        "       from nvc_tbl_catalogo_item_doc idoc " +
                        "       where 1 = 1 " +
                        "       and idoc.id_item = cc.id_item " +
                        "       and idoc.cod_producto is null " +
                        "       and idoc.posicion = 1 " +
                        "       and idoc.id_tipo_documento = 1 and rownum = 1) " + //<LOOKNFEEL>
                        "    else " +
                        "       (select idoc.url_ftp || 'thumb.' || idoc.nombre_archivo_ftp || idoc.extension_archivo " +
                        "       from nvc_tbl_catalogo_item_doc idoc " +
                        "       where 1 = 1 " +
                        "       and idoc.id_item = cc.id_item " +
                        "       and idoc.cod_producto is null " +
                        "       and idoc.posicion = 1 " +
                        "       and idoc.id_tipo_documento = 1 and rownum = 1) " + //<LOOKNFEEL>
                        "  end as url_img " +
                        "from nvc_tbl_carro_compra cc " +
                        "where usuario_creacion = ?1 " +
                        "  and id_requisicion is null " +
                        "  and id_partida is null " +
                        "  and ((id_uen_surtidora is null and id_uen in ?2) " +
                        "       or id_uen_surtidora is not null) " +
                        "order by id_carro_compra desc"
    ),
        @NamedNativeQuery(
                name = "getPreviewCarroCompra",
                resultClass = CarroCompra.class,
                resultSetMapping = "PreviewMapping",
                query = "select id_carro_compra, descripcion, cantidad, " +
                        "  case " +
                        "    when id_item is null then id_item_almacen " +
                        "    else id_item " +
                        "  end as item, " +
                        "  udm, " +
                        "  case when id_item is not null then " +
                        "    case when cc.punchout = 1 then 8 else 1 end " +
                        "    when id_item_almacen is not null AND NVL(CC.PEDIDO_ESPECIAL,0)=0 then 2 " +
                        "    when (id_item is null and id_item_almacen is null and (fuente is null or fuente = 'C'))  AND NVL(CC.PEDIDO_ESPECIAL,0)=0 then 3 " +
                        "    when fuente = 'V' then 4 " +
                        "    when fuente = 'I' then 5 "+
                        "    WHEN FUENTE = 'C' AND NVL(CC.PEDIDO_ESPECIAL,0)=1 THEN 9"+
                        "    else null " +
                        "  end as tipo_item, " +
                        "  case " +
                        "    when id_item is null then " +
                        "       (select idoc.url_ftp || 'thumb.' || idoc.nombre_archivo_ftp || idoc.extension_archivo " +
                        "       from nvc_tbl_catalogo_item_doc idoc, nvc_vm_oa_existencias ex " +
                        "       where 1 = 1 " +
                        "       and upper (idoc.cod_producto) = upper (ex.cod_producto) " +
                        "       and idoc.id_uen = ex.id_uen and idoc.id_uen=cc.id_uen " +
                        "       and upper (ex.id_producto) = upper (cc.id_item_almacen) " +
                        "       and idoc.posicion = 1 " +
                        "       and idoc.id_tipo_documento = 1 and rownum = 1) " + //<LOOKNFEEL>
                        "    when cc.punchout = 1 then " +
                        "       (select idoc.url_ftp " +
                        "       from nvc_tbl_catalogo_item_doc idoc " +
                        "       where 1 = 1 " +
                        "       and idoc.id_item = cc.id_item " +
                        "       and idoc.cod_producto is null " +
                        "       and idoc.posicion = 1 " +
                        "       and idoc.id_tipo_documento = 1 and rownum = 1) " + //<LOOKNFEEL>
                        "    else " +
                        "       (select idoc.url_ftp || 'thumb.' || idoc.nombre_archivo_ftp || idoc.extension_archivo  " +
                        "       from nvc_tbl_catalogo_item_doc idoc " +
                        "       where 1 = 1 " +
                        "       and idoc.id_item = cc.id_item and idoc.id_uen=cc.id_uen " +
                        "       and idoc.cod_producto is null " +
                        "       and idoc.posicion = 1 " +
                        "       and idoc.id_tipo_documento = 1 and rownum = 1) " + //<LOOKNFEEL>
                        "  end as url_img " +
                        "from nvc_tbl_carro_compra cc " +
                        "where usuario_creacion = ?1 " +
                        "  and id_requisicion is null " +
                        "  and id_partida is null " +
                        "  and id_uen_surtidora is null" +
                        "  and id_uen in ?2 " +
                        "order by id_carro_compra desc"
    )
})
public class CarroCompra {

    @Id
    @Column(name = "id_carro_compra")
    private Long id;

    private String descripcion;
    private String color;
    private String medidas;
    private String material;
    private String modelo;
    private String numeroParteModelo;
    private String itemGenerico;
    private String nombreGenerico;
    private String marca;

    @Column(name = "id_subfamilia")
    private Long subFamilia;
    private double cantidad;
    private Date fechaNecesidad;

    @Column(name = "id_razon_urgencia")
    private Long razonUrgencia;
    private String comentariosComprador;
    private Date fechaActualizacion;

    @Column(name = "usuario_creacion")
    private String usuario;

    private String usuarioActualizacion;
    private Long idUdm;
    private Date fechaCreacion;

    @Column(name = "id_item")
    private Long item;

    @Column(name = "id_uen")
    private Long uen;

    @Column(name = "id_item_almacen")
    private Long itemAlmacen;
    private String comprador;
    private String seleccion;
    private Long cotizaEnPaquete;

    @Column(name = "id_almacen")
    private Long almacen;
    private Integer totalSugerencias;
    private String razonSpot;

    @Column(name = "id_uen_surtidora")
    private Long uenSurtidora;

    @Column(name = "id_localizacion")
    private Long localizacion;

    private Long tipo_Recibo;
    private String observacionRecibo;

    @Column(name = "id_requisicion")
    private Long requisicion;

    @Column(name = "id_partida")
    private String partida;
    private String udm;
    private String urlImg;

    @Transient
    private Long tipoItem;

    public CarroCompra(Long id, String descripcion, Double cantidad, Long item, String udm, Long tipoItem, String
            urlImg) {
        this.id = id;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.item = item;
        this.udm = udm;
        this.urlImg = urlImg;
        this.tipoItem = tipoItem;
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

    public Date getFechaActualizacion() {
        return fechaActualizacion == null ? null : (Date) fechaActualizacion.clone();
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        if (fechaActualizacion == null)
            this.fechaActualizacion = null;
        else
            this.fechaActualizacion = (Date) fechaActualizacion.clone();
    }

    public Date getFechaCreacion() {
        return fechaCreacion == null ? null : (Date) fechaCreacion.clone();
    }

    public void setFechaCreacion(Date fechaCreacion) {
        if (fechaCreacion == null)
            this.fechaCreacion = null;
        else
            this.fechaCreacion = (Date) fechaCreacion.clone();
    }
}
