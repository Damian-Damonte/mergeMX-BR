package com.metalsa.catalogo.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.type.StringType;

/**
 *
 * @author APOMS7376
 */
@Entity
@Table(name = "NVC_TBL_CATALOGO_ITEM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NvcTblCatalogoItem.findAll", query = "SELECT n FROM NvcTblCatalogoItem n"),
    @NamedQuery(name = "NvcTblCatalogoItem.findByIdCatUen", query = "SELECT n FROM NvcTblCatalogoItem n where n.idCatalogoUen = :idCatUen and n.activo = 1"),
    @NamedQuery(name = "NvcTblCatalogoItem.findByIdCatUenQuery", query = "SELECT n FROM NvcTblCatalogoItem n where n.idCatalogoUen = :idCatUen and (upper(n.codigoItem) like :query or upper(n.descripcion) like :query) and n.activo = 1"),
    @NamedQuery(name = "NvcTblCatalogoItem.findByIdItem", query = "SELECT n FROM NvcTblCatalogoItem n WHERE n.idItem = :idItem")
})
@SqlResultSetMappings({
    @SqlResultSetMapping(name = "CatItemMapping.count", columns = @ColumnResult(name = "cnt")),
    @SqlResultSetMapping(
            name = "CatItemMapping",
            classes = {
                @ConstructorResult(
                        targetClass = NvcTblCatalogoItem.class,
                        columns = {
                            @ColumnResult(name = "ID_ITEM", type = Integer.class),
                            @ColumnResult(name = "ID_ESTATUS", type = Integer.class),
                            @ColumnResult(name = "DESC_ESTATUS", type = StringType.class),
                            @ColumnResult(name = "ITEM_PUBLICADO", type = Integer.class),
                            @ColumnResult(name = "ID_TEMPLATE", type = Integer.class),
                            @ColumnResult(name = "CODIGO_ITEM", type = StringType.class),
                            @ColumnResult(name = "DESCRIPCION", type = StringType.class),
                            @ColumnResult(name = "NUMERO_PARTE_PROVEEDOR", type = StringType.class),
                            @ColumnResult(name = "FABRICANTE", type = StringType.class),
                            @ColumnResult(name = "NUMERO_PARTE_FABRICANTE", type = StringType.class),
                            @ColumnResult(name = "MATERIAL", type = StringType.class),
                            @ColumnResult(name = "COLOR", type = StringType.class),
                            @ColumnResult(name = "MEDIDAS", type = StringType.class),
                            @ColumnResult(name = "UDM", type = Integer.class),
                            @ColumnResult(name = "DESC_UDM", type = StringType.class),
                            @ColumnResult(name = "PRECIO_UNITARIO", type = Double.class),
                            @ColumnResult(name = "PRECIO_MERCADO", type = Double.class),
                            @ColumnResult(name = "TIEMPO_ENTREGA", type = Integer.class),
                            @ColumnResult(name = "MUESTRA_PRECIO_MERCADO", type = StringType.class),
                            @ColumnResult(name = "ID_MONEDA", type = StringType.class),
                            @ColumnResult(name = "ID_IVA", type = Integer.class),
                            @ColumnResult(name = "IVA", type = StringType.class),
                            @ColumnResult(name = "ID_CATEGORIA", type = Integer.class),
                            @ColumnResult(name = "NOMBRE_CATEGORIA", type = StringType.class),
                            @ColumnResult(name = "ID_FAMILIA", type = Integer.class),
                            @ColumnResult(name = "NOMBRE_FAMILIA", type = StringType.class),
                            @ColumnResult(name = "ID_SUBFAMILIA", type = Integer.class),
                            @ColumnResult(name = "NOMBRE_SUBFAMILIA", type = StringType.class),
                            @ColumnResult(name = "ADMINISTRADOR", type = StringType.class),
                            @ColumnResult(name = "NOMBRE_ADMINISTRADOR", type = StringType.class)
                        }
                )}
    ),
    @SqlResultSetMapping(name = "CatItemFiltroMapping.count", columns = @ColumnResult(name = "cnt")),
    @SqlResultSetMapping(
            name = "CatItemFiltroMapping",
            classes = {
                @ConstructorResult(
                        targetClass = NvcTblCatalogoItem.class,
                        columns = {
                            @ColumnResult(name = "ID_ITEM", type = Integer.class),
                            @ColumnResult(name = "ID_ESTATUS", type = Integer.class),
                            @ColumnResult(name = "DESC_ESTATUS", type = StringType.class),
                            @ColumnResult(name = "ITEM_PUBLICADO", type = Integer.class),
                            @ColumnResult(name = "ID_TEMPLATE", type = Integer.class),
                            @ColumnResult(name = "CODIGO_ITEM", type = StringType.class),
                            @ColumnResult(name = "DESCRIPCION", type = StringType.class),
                            @ColumnResult(name = "NUMERO_PARTE_PROVEEDOR", type = StringType.class),
                            @ColumnResult(name = "FABRICANTE", type = StringType.class),
                            @ColumnResult(name = "NUMERO_PARTE_FABRICANTE", type = StringType.class),
                            @ColumnResult(name = "MATERIAL", type = StringType.class),
                            @ColumnResult(name = "COLOR", type = StringType.class),
                            @ColumnResult(name = "MEDIDAS", type = StringType.class),
                            @ColumnResult(name = "UDM", type = Integer.class),
                            @ColumnResult(name = "DESC_UDM", type = StringType.class),
                            @ColumnResult(name = "PRECIO_UNITARIO", type = Double.class),
                            @ColumnResult(name = "PRECIO_MERCADO", type = Double.class),
                            @ColumnResult(name = "TIEMPO_ENTREGA", type = Integer.class),
                            @ColumnResult(name = "MUESTRA_PRECIO_MERCADO", type = StringType.class),
                            @ColumnResult(name = "ID_MONEDA", type = StringType.class),
                            @ColumnResult(name = "ID_IVA", type = Integer.class),
                            @ColumnResult(name = "IVA", type = StringType.class),
                            @ColumnResult(name = "ID_CATEGORIA", type = Integer.class),
                            @ColumnResult(name = "NOMBRE_CATEGORIA", type = StringType.class),
                            @ColumnResult(name = "ID_FAMILIA", type = Integer.class),
                            @ColumnResult(name = "NOMBRE_FAMILIA", type = StringType.class),
                            @ColumnResult(name = "ID_SUBFAMILIA", type = Integer.class),
                            @ColumnResult(name = "NOMBRE_SUBFAMILIA", type = StringType.class),
                            @ColumnResult(name = "ADMINISTRADOR", type = StringType.class),
                            @ColumnResult(name = "NOMBRE_ADMINISTRADOR", type = StringType.class)
                        }
                )}
    )
})
@NamedNativeQueries({
    @NamedNativeQuery(
            name = "NvcTblCatalogoItem.getByIdCatalogoUen",
            resultClass = NvcTblCatalogoItem.class,
            resultSetMapping = "CatItemMapping",
            query = "select ci.id_item, ci.id_estatus, est.desc_estatus, ci.item_publicado, ci.id_template, ci.codigo_item, ci.descripcion, ci.numero_parte_proveedor, "
            + "ci.fabricante, ci.numero_parte_fabricante, ci.material, ci.color, ci.medidas, ci.udm, udm.id_unidad_de_medida as desc_udm, ci.precio_unitario, ci.precio_mercado, "
            + "ci.tiempo_entrega, ci.muestra_precio_mercado, ci.id_moneda, ci.id_iva, iv.iva, cat.id_familia id_categoria, cati.nombre_familia_idioma nombre_categoria, "
            + "fam.id_familia, fami.nombre_familia_idioma nombre_familia, ci.id_subfamilia, subfi.nombre_familia_idioma nombre_subfamilia, ci.administrador, adm.nombre_usuario nombre_administrador "
            + "from nvc_tbl_catalogo_item ci "
            + "join nvc_tbl_familias subf on ci.id_subfamilia = subf.id_familia "
            + "join nvc_tbl_familias_idioma subfi on subf.id_familia = subfi.id_familia "
            + "join nvc_tbl_familias fam on subf.id_familia_padre = fam.id_familia "
            + "join nvc_tbl_familias_idioma fami on fam.id_familia = fami.id_familia "
            + "join nvc_tbl_familias cat on fam.id_familia_padre = cat.id_familia "
            + "join nvc_tbl_familias_idioma cati on cat.id_familia = cati.id_familia "
            + "join usuario adm on ci.administrador = adm.id_usuario "
            + "join dc_estatus est on ci.id_estatus = est.sc_id  "
            + "join nvc_tbl_udm udm on ci.udm = udm.id_udm "
            + "left outer join nvc_vm_oa_iva iv on iv.tax_id = ci.id_iva "
            + "where subfi.id_idioma = fami.id_idioma and fami.id_idioma = cati.id_idioma  "
            + "and subfi.id_idioma = :idioma and ci.id_catalogo_uen = :idCatalogoUen and ci.activo = 1 "
            + "order by ci.id_item asc/*#pageable*/"
    )
    ,
    @NamedNativeQuery(
            name = "NvcTblCatalogoItem.getByIdCatalogoUenFiltroItem",
            resultClass = NvcTblCatalogoItem.class,
            resultSetMapping = "CatItemMapping",
            query = "select ci.id_item, ci.id_estatus, est.desc_estatus, ci.item_publicado, ci.id_template, ci.codigo_item, ci.descripcion, ci.numero_parte_proveedor, "
            + "ci.fabricante, ci.numero_parte_fabricante, ci.material, ci.color, ci.medidas, ci.udm, udm.id_unidad_de_medida as desc_udm, ci.precio_unitario, ci.precio_mercado, "
            + "ci.tiempo_entrega, ci.muestra_precio_mercado, ci.id_moneda, ci.id_iva, iv.iva, cat.id_familia id_categoria, cati.nombre_familia_idioma nombre_categoria, "
            + "fam.id_familia, fami.nombre_familia_idioma nombre_familia, ci.id_subfamilia, subfi.nombre_familia_idioma nombre_subfamilia, ci.administrador, adm.nombre_usuario nombre_administrador "
            + "from nvc_tbl_catalogo_item ci "
            + "join nvc_tbl_familias subf on ci.id_subfamilia = subf.id_familia "
            + "join nvc_tbl_familias_idioma subfi on subf.id_familia = subfi.id_familia "
            + "join nvc_tbl_familias fam on subf.id_familia_padre = fam.id_familia "
            + "join nvc_tbl_familias_idioma fami on fam.id_familia = fami.id_familia "
            + "join nvc_tbl_familias cat on fam.id_familia_padre = cat.id_familia "
            + "join nvc_tbl_familias_idioma cati on cat.id_familia = cati.id_familia "
            + "join usuario adm on ci.administrador = adm.id_usuario "
            + "join dc_estatus est on ci.id_estatus = est.sc_id  "
            + "join nvc_tbl_udm udm on ci.udm = udm.id_udm "
            + "left outer join nvc_vm_oa_iva iv on iv.tax_id = ci.id_iva "
            + "where subfi.id_idioma = fami.id_idioma and fami.id_idioma = cati.id_idioma  "
            + "and subfi.id_idioma = ?2 and ci.id_catalogo_uen = ?1 and ci.activo = 1 "
            + "and (0 in (?3) or ci.id_item in (?3)) "
            + "and (0 in (?4) or ci.id_estatus in (?4) or ci.item_publicado in (?5) and ci.id_estatus = 1) "
            + "order by id_item asc/*#pageable*/"
    )
    ,
    @NamedNativeQuery(
            name = "NvcTblCatalogoItem.getAllByIdCatalogoUenFiltro",
            resultClass = NvcTblCatalogoItem.class,
            resultSetMapping = "CatItemMapping",
            query = "select ci.id_item, ci.id_estatus, est.desc_estatus, ci.item_publicado, ci.id_template, ci.codigo_item, ci.descripcion, ci.numero_parte_proveedor, "
            + "ci.fabricante, ci.numero_parte_fabricante, ci.material, ci.color, ci.medidas, ci.udm, udm.id_unidad_de_medida as desc_udm, ci.precio_unitario, ci.precio_mercado, "
            + "ci.tiempo_entrega, ci.muestra_precio_mercado, ci.id_moneda, ci.id_iva, iv.iva, cat.id_familia id_categoria, cati.nombre_familia_idioma nombre_categoria, "
            + "fam.id_familia, fami.nombre_familia_idioma nombre_familia, ci.id_subfamilia, subfi.nombre_familia_idioma nombre_subfamilia, ci.administrador, adm.nombre_usuario nombre_administrador "
            + "from nvc_tbl_catalogo_item ci "
            + "join nvc_tbl_familias subf on ci.id_subfamilia = subf.id_familia "
            + "join nvc_tbl_familias_idioma subfi on subf.id_familia = subfi.id_familia "
            + "join nvc_tbl_familias fam on subf.id_familia_padre = fam.id_familia "
            + "join nvc_tbl_familias_idioma fami on fam.id_familia = fami.id_familia "
            + "join nvc_tbl_familias cat on fam.id_familia_padre = cat.id_familia "
            + "join nvc_tbl_familias_idioma cati on cat.id_familia = cati.id_familia "
            + "join usuario adm on ci.administrador = adm.id_usuario "
            + "join dc_estatus est on ci.id_estatus = est.sc_id  "
            + "join nvc_tbl_udm udm on ci.udm = udm.id_udm "
            + "left outer join nvc_vm_oa_iva iv on iv.tax_id = ci.id_iva "
            + "where subfi.id_idioma = fami.id_idioma and fami.id_idioma = cati.id_idioma  "
            + "and subfi.id_idioma = ?2 and ci.id_catalogo_uen = ?1 and ci.activo = 1 "
            + "and (0 in (?3) or ci.id_item in (?3)) "
            + "and (0 in (?4) or ci.id_estatus in (?4) or ci.item_publicado in (?5) and ci.id_estatus = 1) "
            + "order by ci.id_item asc"
    )
    ,
    @NamedNativeQuery(
            name = "NvcTblCatalogoItem.getAllByIdCatalogoUen",
            resultClass = NvcTblCatalogoItem.class,
            resultSetMapping = "CatItemMapping",
            query = "select ci.* "
            + "from nvc_tbl_catalogo_item ci "
            + "where ci.id_catalogo_uen = :idCatalogoUen and ci.activo = 1 "
    )
    ,
    @NamedNativeQuery(
            name = "NvcTblCatalogoItem.activaItemsAprobados",
            query = "update nvc_tbl_catalogo_item set "
            + "item_publicado = ?1, "
            + "fecha_actualizacion = sysdate, "
            + "usuario_actualizacion = ?2 "
            + "where id_catalogo_uen = ?3 and item_publicado = ?4 and id_estatus = 1 and activo = 1 "
    )
    ,
    @NamedNativeQuery(
            name = "NvcTblCatalogoItem.activaItemsEnEdicion",
            query = "update nvc_tbl_catalogo_item set "
            + "id_estatus = ?1, "
            + "num_aprobaciones = num_aprobaciones + 1, "
            + "fecha_actualizacion = sysdate, "
            + "usuario_actualizacion = ?2 "
            + "where id_catalogo_uen = ?3 and id_estatus = 64 and activo = 1 "
    )
    ,
    @NamedNativeQuery(
            name = "NvcTblCatalogoItem.getByIdCatalogoUen.count",
            resultSetMapping = "CatItemMapping.count",
            query = "select count(1) as cnt "
            + "from nvc_tbl_catalogo_item ci  "
            + "join nvc_tbl_familias subf on ci.id_subfamilia = subf.id_familia "
            + "join nvc_tbl_familias_idioma subfi on subf.id_familia = subfi.id_familia "
            + "join nvc_tbl_familias fam on subf.id_familia_padre = fam.id_familia "
            + "join nvc_tbl_familias_idioma fami on fam.id_familia = fami.id_familia "
            + "join nvc_tbl_familias cat on fam.id_familia_padre = cat.id_familia "
            + "join nvc_tbl_familias_idioma cati on cat.id_familia = cati.id_familia "
            + "join usuario adm on ci.administrador = adm.id_usuario "
            + "join dc_estatus est on ci.id_estatus = est.sc_id  "
            + "where subfi.id_idioma = fami.id_idioma and fami.id_idioma = cati.id_idioma  "
            + "and subfi.id_idioma = :idioma and ci.id_catalogo_uen = :idCatalogoUen and ci.activo = 1 "
    )
    ,
    @NamedNativeQuery(
            name = "NvcTblCatalogoItem.getByIdCatalogoUenFiltroItem.count",
            resultSetMapping = "CatItemFiltroMapping.count",
            query = "select count(1) as cnt from "
            + "(select * from nvc_tbl_catalogo_item ci  "
            + "join nvc_tbl_familias subf on ci.id_subfamilia = subf.id_familia "
            + "join nvc_tbl_familias_idioma subfi on subf.id_familia = subfi.id_familia "
            + "join nvc_tbl_familias fam on subf.id_familia_padre = fam.id_familia "
            + "join nvc_tbl_familias_idioma fami on fam.id_familia = fami.id_familia "
            + "join nvc_tbl_familias cat on fam.id_familia_padre = cat.id_familia "
            + "join nvc_tbl_familias_idioma cati on cat.id_familia = cati.id_familia "
            + "join usuario adm on ci.administrador = adm.id_usuario "
            + "join dc_estatus est on ci.id_estatus = est.sc_id  "
            + "where subfi.id_idioma = fami.id_idioma and fami.id_idioma = cati.id_idioma  "
            + "and subfi.id_idioma = ?2 and ci.id_catalogo_uen = ?1 and ci.activo = 1 "
            + "and (0 in (?3) or ci.id_item in (?3)) "
            + "and (0 in (?4) or ci.id_estatus in (?4) or ci.item_publicado in (?5) and ci.id_estatus = 1)) "
    )
    ,
    @NamedNativeQuery(
            name = "NvcTblCatalogoItem.getAllByIdCatalogoUenFiltro.count",
            resultSetMapping = "CatItemFiltroMapping.count",
            query = "select count(1) as cnt from "
            + "(select * from nvc_tbl_catalogo_item ci  "
            + "join nvc_tbl_familias subf on ci.id_subfamilia = subf.id_familia "
            + "join nvc_tbl_familias_idioma subfi on subf.id_familia = subfi.id_familia "
            + "join nvc_tbl_familias fam on subf.id_familia_padre = fam.id_familia "
            + "join nvc_tbl_familias_idioma fami on fam.id_familia = fami.id_familia "
            + "join nvc_tbl_familias cat on fam.id_familia_padre = cat.id_familia "
            + "join nvc_tbl_familias_idioma cati on cat.id_familia = cati.id_familia "
            + "join usuario adm on ci.administrador = adm.id_usuario "
            + "join dc_estatus est on ci.id_estatus = est.sc_id  "
            + "where subfi.id_idioma = fami.id_idioma and fami.id_idioma = cati.id_idioma  "
            + "and subfi.id_idioma = ?2 and ci.id_catalogo_uen = ?1 and ci.activo = 1 "
            + "and (0 in (?3) or ci.id_item in (?3)) "
            + "and (0 in (?4) or ci.id_estatus in (?4) or ci.item_publicado in (?5) and ci.id_estatus = 1)) "
    )
})
public class NvcTblCatalogoItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ITEM")
    private Integer idItem;
    @Size(max = 50)
    @Column(name = "CODIGO_ITEM")
    private String codigoItem;
    @Size(max = 200)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Size(max = 50)
    @Column(name = "NUMERO_PARTE_FABRICANTE")
    private String numeroParteFabricante;
    @Size(max = 50)
    @Column(name = "NUMERO_PARTE_PROVEEDOR")
    private String numeroParteProveedor;
    @Size(max = 100)
    @Column(name = "FABRICANTE")
    private String fabricante;
    @Size(max = 100)
    @Column(name = "MATERIAL")
    private String material;
    @Size(max = 100)
    @Column(name = "COLOR")
    private String color;
    @Size(max = 100)
    @Column(name = "MEDIDAS")
    private String medidas;
    @Column(name = "UDM")
    private Integer udm;
    @Column(name = "PRECIO_UNITARIO")
    private Double precioUnitario;
    @Column(name = "PRECIO_MERCADO")
    private Double precioMercado;
    @Column(name = "MUESTRA_PRECIO_MERCADO")
    private String muestraPrecioMercado;
    @Column(name = "TIEMPO_ENTREGA")
    private Integer tiempoEntrega;
    @Column(name = "ID_IVA")
    private Integer idIva;
    @Column(name = "ID_SUBFAMILIA")
    private Integer idSubfamilia;
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FECHA_ACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @Size(max = 256)
    @Column(name = "USUARIO_CREACION")
    private String usuarioCreacion;
    @Size(max = 256)
    @Column(name = "USUARIO_ACTUALIZACION")
    private String usuarioActualizacion;
    @Column(name = "ACTIVO")
    private Integer activo;
    @Column(name = "NUM_APROBACIONES")
    private Integer numAprobaciones;
    @Column(name = "ADMINISTRADOR")
    private String administrador;
    @Size(max = 200)
    @Column(name = "SPOT_MARCA")
    private String spotMarca;
    @Size(max = 500)
    @Column(name = "SPOT_NOMBRE_GENERICO")
    private String spotNombreGenerico;
    @Column(name = "SPOT_FECHA_NECESIDAD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date spotFechaNecesidad;
    @Column(name = "SPOT_URGENTE")
    private Character spotUrgente;
    @Column(name = "ID_LINEA")
    private Integer idLinea;
    @Size(max = 500)
    @Column(name = "SPOT_COMENTARIOS_COMPRADOR")
    private String spotComentariosComprador;

    // <CAT_VAR>
    @Column(name = "ID_TEMPLATE")
    private Integer idTemplate;

    @Column(name = "MAX_PRECIO_UNITARIO")
    private Double maxPrecioUnitario;

    @Column(name = "DESCUENTO")
    private Integer descuento;

    @Column(name = "ID_MONEDA")
    private String idMoneda;

    @Column(name = "ID_ESTATUS")
    private Integer idEstatus;

    @Column(name = "ITEM_PUBLICADO")
    private Integer itemPublicado;

    @Column(name = "ID_CATALOGO_UEN")
    private Integer idCatalogoUen;

    @Transient
    @Column(name = "id_categoria")
    private Integer idCategoria;

    @Transient
    @Column(name = "desc_udm")
    private String descUdm;

    @Transient
    @Column(name = "iva")
    private String iva;

    @Transient
    @Column(name = "nombre_categoria")
    private String nombreCategoria;

    @Transient
    @Column(name = "id_familia")
    private Integer idFamilia;

    @Transient
    @Column(name = "nombre_familia")
    private String nombreFamilia;

    @Transient
    @Column(name = "nombre_subfamilia")
    private String nombreSubfamilia;

    @Transient
    @Column(name = "nombre_administrador")
    private String nombreAdministrador;

    @Transient
    private String imgEstatus;

    @Transient
    private boolean checked;

    @Column(name = "desc_estatus")
    @Transient
    private String descEstatus;

    public NvcTblCatalogoItem() {
    }

    public NvcTblCatalogoItem(Integer idItem, Integer idEstatus, String imgEstatus, Integer itemPublicado, Integer idTemplate,
            String codigoItem, String descripcion, String numeroParteProveedor, String fabricante, String numeroParteFabricante,
            String material, String color, String medidas, Integer udm, String descUdm, Double precioUnitario, Double precioMercado,
            Integer tiempoEntrega, String muestraPrecioMercado, String idMoneda, Integer idIva, String iva, Integer idCategoria,
            String nombreCategoria, Integer idFamilia, String nombreFamilia, Integer idSubfamilia, String nombreSubfamilia,
            String administrador, String nombreAdministrador) {
        this.idItem = idItem;
        this.idEstatus = idEstatus;
        if (imgEstatus.equals("APROBADA") && itemPublicado == 45) {
            this.imgEstatus = "status_aa.png";
        } else if (imgEstatus.equals("APROBADA") && itemPublicado == 46) {
            this.imgEstatus = "status_ai-01.png";
        } else if (imgEstatus.equals("POR APROBAR")) {
            this.imgEstatus = "status_pendiente.png";
        } else if (imgEstatus.equals("REGRESADA")) {
            this.imgEstatus = "status_rechazado.png";
        } else {
            this.imgEstatus = "status_edicion.png";
        }
        this.itemPublicado = itemPublicado;
        this.idTemplate = idTemplate;
        this.codigoItem = codigoItem;
        this.descripcion = descripcion;
        this.numeroParteProveedor = numeroParteProveedor;
        this.fabricante = fabricante;
        this.numeroParteFabricante = numeroParteFabricante;
        this.material = material;
        this.color = color;
        this.medidas = medidas;
        this.udm = udm;
        this.descUdm = descUdm;
        this.precioUnitario = precioUnitario;
        this.precioMercado = precioMercado;
        this.tiempoEntrega = tiempoEntrega;
        this.muestraPrecioMercado = muestraPrecioMercado;
        this.idMoneda = idMoneda;
        this.idIva = idIva;
        this.iva = iva;
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
        this.idFamilia = idFamilia;
        this.nombreFamilia = nombreFamilia;
        this.idSubfamilia = idSubfamilia;
        this.nombreSubfamilia = nombreSubfamilia;
        this.administrador = administrador;
        this.nombreAdministrador = nombreAdministrador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idItem != null ? idItem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NvcTblCatalogoItem)) {
            return false;
        }
        NvcTblCatalogoItem other = (NvcTblCatalogoItem) object;
        if ((this.idItem == null && other.idItem != null) || (this.idItem != null && !this.idItem.equals(other.idItem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.spx.entities.NvcTblCatalogoItem[ idItem=" + idItem + " ]";
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public String getCodigoItem() {
        return codigoItem;
    }

    public void setCodigoItem(String codigoItem) {
        this.codigoItem = codigoItem;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNumeroParteFabricante() {
        return numeroParteFabricante;
    }

    public void setNumeroParteFabricante(String numeroParteFabricante) {
        this.numeroParteFabricante = numeroParteFabricante;
    }

    public String getNumeroParteProveedor() {
        return numeroParteProveedor;
    }

    public void setNumeroParteProveedor(String numeroParteProveedor) {
        this.numeroParteProveedor = numeroParteProveedor;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMedidas() {
        return medidas;
    }

    public void setMedidas(String medidas) {
        this.medidas = medidas;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getPrecioMercado() {
        return precioMercado;
    }

    public void setPrecioMercado(Double precioMercado) {
        this.precioMercado = precioMercado;
    }

    public String getMuestraPrecioMercado() {
        return muestraPrecioMercado;
    }

    public void setMuestraPrecioMercado(String muestraPrecioMercado) {
        this.muestraPrecioMercado = muestraPrecioMercado;
    }

    public Integer getTiempoEntrega() {
        return tiempoEntrega;
    }

    public void setTiempoEntrega(Integer tiempoEntrega) {
        this.tiempoEntrega = tiempoEntrega;
    }

    public Integer getIdIva() {
        return idIva;
    }

    public void setIdIva(Integer idIva) {
        this.idIva = idIva;
    }

    public Integer getIdSubfamilia() {
        return idSubfamilia;
    }

    public void setIdSubfamilia(Integer idSubfamilia) {
        this.idSubfamilia = idSubfamilia;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getSpotMarca() {
        return spotMarca;
    }

    public void setSpotMarca(String spotMarca) {
        this.spotMarca = spotMarca;
    }

    public String getSpotNombreGenerico() {
        return spotNombreGenerico;
    }

    public void setSpotNombreGenerico(String spotNombreGenerico) {
        this.spotNombreGenerico = spotNombreGenerico;
    }

    public Date getSpotFechaNecesidad() {
        return spotFechaNecesidad;
    }

    public void setSpotFechaNecesidad(Date spotFechaNecesidad) {
        this.spotFechaNecesidad = spotFechaNecesidad;
    }

    public Character getSpotUrgente() {
        return spotUrgente;
    }

    public void setSpotUrgente(Character spotUrgente) {
        this.spotUrgente = spotUrgente;
    }

    public Integer getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(Integer idLinea) {
        this.idLinea = idLinea;
    }

    public String getSpotComentariosComprador() {
        return spotComentariosComprador;
    }

    public void setSpotComentariosComprador(String spotComentariosComprador) {
        this.spotComentariosComprador = spotComentariosComprador;
    }

    public String getIdMoneda() {
        return idMoneda;
    }

    public void setIdMoneda(String idMoneda) {
        this.idMoneda = idMoneda;
    }

    public Integer getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(Integer idEstatus) {
        this.idEstatus = idEstatus;
    }


    public Integer getIdCatalogoUen() {
        return idCatalogoUen;
    }

    public void setIdCatalogoUen(Integer idCatalogoUen) {
        this.idCatalogoUen = idCatalogoUen;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public String getUsuarioActualizacion() {
        return usuarioActualizacion;
    }

    public void setUsuarioActualizacion(String usuarioActualizacion) {
        this.usuarioActualizacion = usuarioActualizacion;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public String getAdministrador() {
        return administrador;
    }

    public void setAdministrador(String administrador) {
        this.administrador = administrador;
    }

    public Integer getItemPublicado() {
        return itemPublicado;
    }

    public void setItemPublicado(Integer itemPublicado) {
        this.itemPublicado = itemPublicado;
    }

    public Integer getUdm() {
        return udm;
    }

    public void setUdm(Integer udm) {
        this.udm = udm;
    }

    public Integer getNumAprobaciones() {
        return numAprobaciones;
    }

    public void setNumAprobaciones(Integer numAprobaciones) {
        this.numAprobaciones = numAprobaciones;
    }

    public Integer getIdTemplate() {
        return idTemplate;
    }

    public void setIdTemplate(Integer idTemplate) {
        this.idTemplate = idTemplate;
    }

    public Double getMaxPrecioUnitario() {
        return maxPrecioUnitario;
    }

    public void setMaxPrecioUnitario(Double maxPrecioUnitario) {
        this.maxPrecioUnitario = maxPrecioUnitario;
    }

    public Integer getDescuento() {
        return descuento;
    }

    public void setDescuento(Integer descuento) {
        this.descuento = descuento;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDescUdm() {
        return descUdm;
    }

    public void setDescUdm(String descUdm) {
        this.descUdm = descUdm;
    }

    public String getIva() {
        return iva;
    }

    public void setIva(String iva) {
        this.iva = iva;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public Integer getIdFamilia() {
        return idFamilia;
    }

    public void setIdFamilia(Integer idFamilia) {
        this.idFamilia = idFamilia;
    }

    public String getNombreFamilia() {
        return nombreFamilia;
    }

    public void setNombreFamilia(String nombreFamilia) {
        this.nombreFamilia = nombreFamilia;
    }

    public String getNombreSubfamilia() {
        return nombreSubfamilia;
    }

    public void setNombreSubfamilia(String nombreSubfamilia) {
        this.nombreSubfamilia = nombreSubfamilia;
    }

    public String getNombreAdministrador() {
        return nombreAdministrador;
    }

    public void setNombreAdministrador(String nombreAdministrador) {
        this.nombreAdministrador = nombreAdministrador;
    }

    public String getImgEstatus() {
        return imgEstatus;
    }

    public void setImgEstatus(String imgEstatus) {
        this.imgEstatus = imgEstatus;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getDescEstatus() {
        return descEstatus;
    }

    public void setDescEstatus(String descEstatus) {
        this.descEstatus = descEstatus;
    }
}
