package com.metalsa.aprobacion.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.metalsa.utils.entities.NvcTblCarroCompraDetalle;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

import javax.persistence.*;
import java.util.Date;

/**i
 * Modelo de los items del carro de compra
 *
 * @author ruben.bresler
 */
@Entity
@Table(name = "NVC_TBL_CARRO_COMPRA")
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
                        "from CarroCmopra cc " +
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
                        "from CarrpCompra cc " +
                        "where usuario_creacion = ?1 " +
                        "  and id_requisicion is null " +
                        "  and id_partida is null " +
                        "  and id_uen_surtidora is null" +
                        "  and id_uen in ?2 " +
                        "order by id_carro_compra desc"
    )
})
@NamedQueries({
    @NamedQuery(name = "CarroCompra.findById", query = "SELECT n FROM CarroCompra n WHERE n.id=:id"),
    @NamedQuery(name = "CarroCompra.findByIdItem", query = "SELECT n FROM CarroCompra n WHERE n.item=:idItem and n.usuario=:idUsuario and n.idTipoPrecio=:idTipoPrecio and n.requisicion is null"),
    @NamedQuery(
            name = "CarroCompra.findByInterUen",
            query = "SELECT n FROM CarroCompra n " +
                    "WHERE n.itemAlmacen=:itemAlmacen " +
                    "AND n.almacen=:idAlmacen " +
                    "AND n.uenSurtidora=:uenSurtidora " +
                    "AND n.usuario=:idUsuario "
    )
})
public class CarroCompra {

    @Id
    @SequenceGenerator(name = "SEQ_NVC_TBL_CARRO_COMPRA_GEN", sequenceName = "SEQ_NVC_TBL_CARRO_COMPRA", allocationSize = 1)
    @GeneratedValue(generator = "SEQ_NVC_TBL_CARRO_COMPRA_GEN")
    @Basic(optional = false)    
    @Column(name = "id_carro_compra")
    private Long id;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "COLOR")
    private String color;
    

    @Column(name = "MEDIDAS")
    private String medidas;

    @Column(name = "VF_PRECIO_UNITARIO")
    private Double vfPrecioUnitario;

    @Column(name = "MATERIAL")
    private String material;

    @Column(name = "MODELO")
    private String modelo;
    
    @Column(name = "NUMERO_PARTE_MODELO")
    private String numeroParteModelo;
    
    @Column(name = "ITEM_GENERICO")
    private String itemGenerico;
    
    @Column(name = "NOMBRE_GENERICO")
    private String nombreGenerico;
    
    
    private String marca;

    @Column(name = "id_subfamilia")
    private Long subFamilia;

    @Column(name = "CANTIDAD")
    private double cantidad;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_NECESIDAD")
    private Date fechaNecesidad;

    @Column(name = "ID_TEMPLATE")
    private Integer idTemplate;

    @Column(name = "precio_unitario_base")
    private Double precioUnitarioBase;

    @Column(name = "PUNCHOUT")
    private Integer punchout;

    @Column(name = "ID_RAZON_URGENCIA")
    private Long razonUrgencia;
    
    @Column(name = "COMENTARIOS_COMPRADOR")
    private String comentariosComprador;
    
    @Column(name = "FECHA_ACTUALIZACION")
    private Date fechaActualizacion;

    @Column(name = "usuario_creacion")
    private String usuario;
    
    @Column(name = "USUARIO_ACTUALIZACION")
    private String usuarioActualizacion;
    
    @Column(name = "ID_UDM")
    private Long idUdm;

    @Column(name = "FECHA_CREACION")
    private Date fechaCreacion;

    @Column(name = "ID_ITEM")
    private Long item;

    @Column(name = "ID_UEN")
    private Long uen;

    @Column(name = "ID_ITEM_ALMACEN")
    private Long itemAlmacen;

    @Column(name = "COMPRADOR")
    private String comprador;
    
    @Column(name = "SELECCION")
    private String seleccion;
     @Column(name = "COTIZA_EN_PAQUETE")
    private Integer cotizaEnPaquete;

    @JsonBackReference
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "idCarroCompra")    
    private NvcTblCarroCompraDetalle detalle;

    @Column(name = "id_almacen")
    private Long almacen;
    
    @Column(name = "TOTAL_SUGERENCIAS")
    private Integer totalSugerencias;
    
    @Column(name = "RAZON_SPOT")
    private String razonSpot;

    @Column(name = "id_uen_surtidora")
    private Long uenSurtidora;

    @Column(name = "ID_TIPO_PRECIO")
    private Integer idTipoPrecio;

    @Column(name = "id_localizacion")
    private Long localizacion;

    @Column(name = "TIPO_RECIBO")
    private Long tipo_Recibo;

    @Column(name = "OBSERVACION_RECIBO")
    private String observacionRecibo;

    @Column(name = "id_requisicion")
    private Long requisicion;

    @Column(name = "id_partida")
    private String partida;
    
    @Column(name = "UDM")
    private String udm;
    
    @Column(name = "URL_IMG")
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
    
    public Long getUenSurtidora() {
        return uenSurtidora;
    }
    
    public void setUenSurtidora(Long uen) {
        this.uenSurtidora = uen;
    }
    
    public Long getItemAlmacen() {
        return this.itemAlmacen;
    }

}
