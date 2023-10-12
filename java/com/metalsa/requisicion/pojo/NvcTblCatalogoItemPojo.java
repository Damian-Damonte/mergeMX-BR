package com.metalsa.requisicion.pojo;

import com.metalsa.catalogo.model.NvcTblCatalogoItem;
import com.metalsa.requisicion.model.NvcTblMotivoRegresoItem;
import com.metalsa.requisicion.utils.ConstantsUtils;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author APODR7218
 */
public class NvcTblCatalogoItemPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    private ConstantsUtils.EVENTOS evento;
    private final String VALUE_TRUE = "true";
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(NvcTblCatalogoItemPojo.class);

    private Integer idItem;
    private String codigoItem;
    private String descripcion;
    private String descripcionLarga;
    private Integer idCatalogoUen;
    private String nombreCatalogo;
    private String numeroParteFabricante;
    private String numeroParteProveedor;
    private String fabricante;
    private String material;
    private String color;
    private String medidas;
    private String udm;
    private String nombreUdm;
    private Double precioUnitario;
    private Double precioMercado;
    private String muestraPrecioMercado;
    private boolean muestraPrecioMercadoBool;
    private Integer tiempoEntrega;
    private String idMoneda;
    private String nombreMoneda;
    private Integer idIva;
    private String nombreIva;
    private Integer uen;
    private Integer categoria;
    private String nombreCategoria;
    private Integer familia;
    private String nombreFamilia;
    private Integer subfamilia;
    private String nombreSubfamilia;
    private Integer idRazonUrgencia;
//    private String nombreRazonSugerencia;
    private Integer idLinea;
    private Integer idEstatus;
    private Integer activo;
//    private Integer tipoRecibo;
    private String administrador;
    private String nombreAdministrador;
    private Integer publicado;
    private String nombreEstatus;
    private Integer idAlmacen;
    private boolean editado;
    ///////////////////////------------------ J. Alfred
    private Integer idTemplate;
    private NvcTblCatalogoVariablePojo template;
    ///////////////////////----------------------
    private List<NvcTblCatalogoItemDocPojo> imagenes;
    private List<NvcTblCatalogoItemDocPojo> documentos;
    private List<NvcTblMotivoRegresoItem> motivoRegresoList;
    private String imgUrl; //punchout

    private String codProducto;
    private Integer idUen;
    private Timestamp fecha_actualizacion;
    // Inicio Omar Estrella Se agregaron atributos
    private Double maxPrecioUnitario;
    private Integer descuento;
    private boolean descuentoBool;
    // Fin Omar Estrella Se agregaron atributos

    //  Omar Estrella Se agregaron informacion para Rango
    private List<NvcTblItemRangoInfoPojo> rangoInfo;
    private List<NvcTblItemRangoInfoPojo> rangoInfoDeleted;

    //  Omar Estrella Se agregaron informacion para Mes
    private List<NvcTblIMonthItemInfoPojo> mesInfo;
    private List<NvcTblIMonthItemInfoPojo> mesInfoDeleted;

    public NvcTblCatalogoItemPojo() {
    }

    private NvcTblCatalogoItemPojo(NvcTblCatalogoItem idItem) {
        this.idItem = idItem.getIdItem();
        this.nombreAdministrador = idItem.getAdministrador();
        this.descripcion = idItem.getDescripcion();
        this.codigoItem = idItem.getCodigoItem();
        this.idCatalogoUen = idItem.getIdCatalogoUen();
        this.idUen = idItem.getCatalogoUen().getIdUen();
        this.idIva = idItem.getIdIva();
        this.precioUnitario = idItem.getPrecioUnitario();
        this.subfamilia = idItem.getIdSubfamilia();
        this.idMoneda = idItem.getIdMoneda();
        this.tiempoEntrega = idItem.getTiempoEntrega();
    }

    public NvcTblCatalogoItemPojo(
            Integer idItem,
            String codigoItem,
            String descripcion,
            Integer idCatalogoUen,
            String nombreCatalogo,
            String numeroParteFabricante,
            String numeroParteProveedor,
            String fabricante,
            String material,
            String color,
            String medidas,
            String udm,
            Double precioUnitario,
            Double precioMercado,
            String muestraPrecioMercado,
            Integer tiempoEntrega,
            String idMoneda,
            Integer subfamilia,
            Integer idIva,
            Integer activo,
            String administrador,
            String nombreAdministrador,
            BigDecimal idEstatus,
            BigDecimal publicado,
            String nombreUdm,
            String nombreMoneda,
            String nombreIva,
            String nombreCategoria,
            String nombreFamilia,
            String nombreSubfamilia,
            Double maxPrecioUnitario,
            Integer descuento,
            Integer idTemplate,
            List<NvcTblItemRangoInfoPojo> rangoInfo,
            List<NvcTblIMonthItemInfoPojo> mesInfo//<T455581>
    ) {
        crearPojo(idItem, codigoItem, descripcion, idCatalogoUen, nombreCatalogo, numeroParteFabricante, numeroParteProveedor, fabricante, material, color, medidas, udm, precioUnitario, precioMercado, muestraPrecioMercado, tiempoEntrega, idMoneda, subfamilia, idIva, activo, administrador, nombreAdministrador, idEstatus, publicado, nombreUdm, nombreMoneda, nombreIva, nombreCategoria, nombreFamilia, nombreSubfamilia, maxPrecioUnitario, descuento, idTemplate,rangoInfo,mesInfo);
    }

    public NvcTblCatalogoItemPojo(
            Integer idItem,
            String codigoItem,
            String descripcion,
            Integer idCatalogoUen,
            String nombreCatalogo,
            String numeroParteFabricante,
            String numeroParteProveedor,
            String fabricante,
            String material,
            String color,
            String medidas,
            String udm,
            Double precioUnitario,
            Double precioMercado,
            String muestraPrecioMercado,
            Integer tiempoEntrega,
            String idMoneda,
            Integer subfamilia,
            Integer idIva,
            Integer activo,
            String administrador,
            String nombreAdministrador,
            BigDecimal idEstatus,
            BigDecimal publicado,
            String nombreUdm,
            String nombreMoneda,
            String nombreIva,
            String nombreCategoria,
            String nombreFamilia,
            String nombreSubfamilia) {
        crearPojo(idItem, codigoItem, descripcion, idCatalogoUen, nombreCatalogo, numeroParteFabricante, numeroParteProveedor, fabricante, material, color, medidas, udm, precioUnitario, precioMercado, muestraPrecioMercado, tiempoEntrega, idMoneda, subfamilia, idIva, activo, administrador, nombreAdministrador, idEstatus, publicado, nombreUdm, nombreMoneda, nombreIva, nombreCategoria, nombreFamilia, nombreSubfamilia, null, null, null, null, null);
    }

    public void crearPojo(
            Integer idItem,
            String codigoItem,
            String descripcion,
            Integer idCatalogoUen,
            String nombreCatalogo,
            String numeroParteFabricante,
            String numeroParteProveedor,
            String fabricante,
            String material,
            String color,
            String medidas,
            String udm,
            Double precioUnitario,
            Double precioMercado,
            String muestraPrecioMercado,
            Integer tiempoEntrega,
            String idMoneda,
            Integer subfamilia,
            Integer idIva,
            Integer activo,
            String administrador,
            String nombreAdministrador,
            BigDecimal idEstatus,
            BigDecimal publicado,
            String nombreUdm,
            String nombreMoneda,
            String nombreIva,
            String nombreCategoria,
            String nombreFamilia,
            String nombreSubfamilia,
            Double maxPrecioUnitario,
            Integer descuento,
            Integer idTemplate,
            List<NvcTblItemRangoInfoPojo> rangoInfo,
            List<NvcTblIMonthItemInfoPojo> mesInfo
    ) {
        try {

            this.idItem = idItem == null ? 0 : idItem;
            this.codigoItem = codigoItem;
            this.descripcion = descripcion;
            this.idCatalogoUen = idCatalogoUen == null ? 0 : idCatalogoUen;
            this.nombreCatalogo = nombreCatalogo;
            this.numeroParteFabricante = numeroParteFabricante;
            this.numeroParteProveedor = numeroParteProveedor;
            this.fabricante = fabricante;
            this.material = material;
            this.color = color;
            this.medidas = medidas;
            this.udm = udm;
            this.precioUnitario = precioUnitario;
            this.precioMercado = precioMercado;
            this.muestraPrecioMercado = muestraPrecioMercado == null ? "" : muestraPrecioMercado;
            this.tiempoEntrega = tiempoEntrega == null ? 0 : tiempoEntrega;
            this.idMoneda = idMoneda;
            this.idIva = idIva == null ? 0 : idIva;
            this.subfamilia = subfamilia == null ? 0 : subfamilia;
            this.activo = activo == null ? 0 : activo;
//            this.tipoRecibo = tipoRecibo == null ? 0 : tipoRecibo;
            this.administrador = administrador == null ? "" : administrador;
            this.nombreAdministrador = nombreAdministrador == null ? "" : nombreAdministrador;
            this.idEstatus = idEstatus == null ? 0 : idEstatus.intValue();
            this.publicado = publicado == null ? 0 : publicado.intValue();

            this.nombreIva = nombreIva == null ? "" : nombreIva;
            this.nombreCategoria = nombreCategoria == null ? "" : nombreCategoria;
            this.nombreFamilia = nombreFamilia == null ? "" : nombreFamilia;
            this.nombreSubfamilia = nombreSubfamilia == null ? "" : nombreSubfamilia;
            this.maxPrecioUnitario = maxPrecioUnitario == null ? 0 : maxPrecioUnitario;
            this.descuento = descuento == null ? 0 : descuento;
            this.idTemplate = idTemplate;
            this.rangoInfo=rangoInfo;//<T455581>
            this.mesInfo=mesInfo;//<T455581>

        } catch (Exception e) {
            log.error(e);
        }
    }

    public ConstantsUtils.EVENTOS getEvento() {
        return evento;
    }

    public void setEvento(ConstantsUtils.EVENTOS evento) {
        this.evento = evento;
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

    public Integer getIdCatalogoUen() {
        return idCatalogoUen;
    }

    public void setIdCatalogoUen(Integer idCatalogoUen) {
        this.idCatalogoUen = idCatalogoUen;
    }

    public String getNombreCatalogo() {
        return nombreCatalogo;
    }

    public void setNombreCatalogo(String nombreCatalogo) {
        this.nombreCatalogo = nombreCatalogo;
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

    public String getUdm() {
        return udm;
    }

    public void setUdm(String udm) {
        this.udm = udm;
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

    public String getIdMoneda() {
        return idMoneda;
    }

    public void setIdMoneda(String idMoneda) {
        this.idMoneda = idMoneda;
    }

    public Integer getIdIva() {
        return idIva;
    }

    public void setIdIva(Integer idIva) {
        this.idIva = idIva;
    }

    public String getNombreIva() {
        return nombreIva;
    }

    public void setNombreIva(String nombreIva) {
        this.nombreIva = nombreIva;
    }

    public Integer getUen() {
        return uen;
    }

    public void setUen(Integer uen) {
        this.uen = uen;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    public Integer getFamilia() {
        return familia;
    }

    public void setFamilia(Integer familia) {
        this.familia = familia;
    }

    public Integer getSubfamilia() {
        return subfamilia;
    }

    public void setSubfamilia(Integer subfamilia) {
        this.subfamilia = subfamilia;
    }

    public Integer getIdRazonUrgencia() {
        return idRazonUrgencia;
    }

    public void setIdRazonUrgencia(Integer idRazonUrgencia) {
        this.idRazonUrgencia = idRazonUrgencia;
    }

    public Integer getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(Integer idLinea) {
        this.idLinea = idLinea;
    }

    public Integer getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(Integer idEstatus) {
        this.idEstatus = idEstatus;
    }

    public List<NvcTblCatalogoItemDocPojo> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<NvcTblCatalogoItemDocPojo> imagenes) {
        this.imagenes = imagenes;
    }

    public List<NvcTblCatalogoItemDocPojo> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<NvcTblCatalogoItemDocPojo> documentos) {
        this.documentos = documentos;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

//    public Integer getTipoRecibo() {
//        return tipoRecibo;
//    }
//
//    public void setTipoRecibo(Integer tipoRecibo) {
//        this.tipoRecibo = tipoRecibo;
//    }
    public String getAdministrador() {
        return administrador;
    }

    public void setAdministrador(String administrador) {
        this.administrador = administrador;
    }

    public Integer getPublicado() {
        return publicado;
    }

    public void setPublicado(Integer publicado) {
        this.publicado = publicado;
    }

    public Integer getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(Integer idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    @Override
    public boolean equals(Object obj) {
        boolean equals;
        if (obj instanceof NvcTblCatalogoItemPojo) {
            NvcTblCatalogoItemPojo catalogoItem = (NvcTblCatalogoItemPojo) obj;
            //<RM16773>
            equals = isEquals(catalogoItem.getActivo(), this.activo)
                    && isEquals(catalogoItem.getCategoria(), this.categoria)
                    && isEquals(catalogoItem.getCodigoItem(), this.codigoItem)
                    && isEquals(catalogoItem.getColor(), this.color)
                    && isEquals(catalogoItem.getDescripcion(), this.descripcion)
                    && isEquals(catalogoItem.getFabricante(), this.fabricante)
                    && isEquals(catalogoItem.getFamilia(), this.familia)
                    && isEquals(catalogoItem.getIdEstatus(), this.idEstatus)
                    && isEquals(catalogoItem.getIdCatalogoUen(), this.idCatalogoUen)
                    && isEquals(catalogoItem.getIdItem(), this.idItem)
                    && isEquals(catalogoItem.getIdIva(), this.idIva)
                    && isEquals(catalogoItem.getIdLinea(), this.idLinea)
                    && isEquals(catalogoItem.getIdMoneda(), this.idMoneda)
                    && isEquals(catalogoItem.getIdRazonUrgencia(), this.idRazonUrgencia)
                    && isEquals(catalogoItem.getMaterial(), this.material)
                    && isEquals(catalogoItem.getMedidas(), this.medidas)
                    && isEquals(catalogoItem.getMuestraPrecioMercado(), this.muestraPrecioMercado)
                    && isEquals(catalogoItem.getNombreCatalogo(), this.nombreCatalogo)
                    && isEquals(catalogoItem.getNombreIva(), this.nombreIva)
                    && isEquals(catalogoItem.getNumeroParteFabricante(), this.numeroParteFabricante)
                    && isEquals(catalogoItem.getNumeroParteProveedor(), this.numeroParteProveedor)
                    && isEquals(catalogoItem.getPrecioMercado(), this.precioMercado)
                    && isEquals(catalogoItem.getPrecioUnitario(), this.precioUnitario)
                    && isEquals(catalogoItem.getSubfamilia(), this.subfamilia)
                    && isEquals(catalogoItem.getTiempoEntrega(), this.tiempoEntrega)
                    && isEquals(catalogoItem.getUen(), this.idUen);
            //</RM16773>
            return equals;
        } else {
            return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
        }
    }

    public List<String> equalsWithDifferences(Object obj) {
        List<String> differences = new ArrayList<>();
        if (obj instanceof NvcTblCatalogoItemPojo) {
            NvcTblCatalogoItemPojo catalogoItem = (NvcTblCatalogoItemPojo) obj;

            if (catalogoItem.getActivo() != null && this.activo != null) {
                if (!catalogoItem.getActivo().equals(this.activo)) {
                    differences.add("");
                }
            }
            // <RELEASE ARG>
//            if (catalogoItem.getCategoria() != null && this.categoria != null) {
//                if (!catalogoItem.getCategoria().equals(this.categoria)) {
//                    differences.add("catalogo_categoria");
//                }
//            }
            // <RELEASE ARG />
            if (catalogoItem.getCodigoItem() != null && this.codigoItem != null) {
                if (!catalogoItem.getCodigoItem().equals(this.codigoItem)) {
                    differences.add("catalogo_item_id");
                }
            }
            if (catalogoItem.getColor() != null) {
                if (this.color != null) {
                    if (!catalogoItem.getColor().equals(this.color)) {
                        differences.add("catalogo_color");
                    }
                } else {
                    differences.add("catalogo_color");
                }
            }
            if (catalogoItem.getDescripcion() != null) {
                if (this.descripcion != null) {
                    if (!catalogoItem.getDescripcion().equals(this.descripcion)) {
                        differences.add("catalogo_descripcion");
                    }
                } else {
                    differences.add("catalogo_descripcion");
                }
            }
            if (catalogoItem.getFabricante() != null) {
                if (this.fabricante != null) {
                    if (!catalogoItem.getFabricante().equals(this.fabricante)) {
                        differences.add("catalogo_fabricante");
                    }
                } else {
                    differences.add("catalogo_fabricante");
                }
            }
            // <RELEASE ARG >
//            if (catalogoItem.getFamilia() != null && this.familia != null) {
//                if (!catalogoItem.getFamilia().equals(this.familia)) {
//                    differences.add("catalogo_familia");
//                }
//            }
            // <RELEASE ARG />
            if (catalogoItem.getIdCatalogoUen() != null && this.idCatalogoUen != null) {
                if (!catalogoItem.getIdCatalogoUen().equals(this.idCatalogoUen)) {
                    differences.add("");
                }
            }
            //<R48776>
            //if (catalogoItem.getIdEstatus() != null && this.idEstatus != null) {
            //    if (!catalogoItem.getIdEstatus().equals(this.idEstatus)) {
            //        differences.add("");
            //    }
            //}
            //</R48776>
            if (catalogoItem.getIdItem() != null && this.idItem != null) {
                if (!catalogoItem.getIdItem().equals(this.idItem)) {
                    differences.add("");
                }
            }
            if (catalogoItem.getIdIva() != null && this.idIva != null) {
                if (!catalogoItem.getIdIva().equals(this.idIva)) {
                    differences.add("catalogo_iva");
                }
            }
            if (catalogoItem.getIdLinea() != null && this.idLinea != null) {
                if (!catalogoItem.getIdLinea().equals(this.idLinea)) {
                    differences.add("");
                }
            }
            if (catalogoItem.getIdMoneda() != null && this.idMoneda != null) {
                if (!catalogoItem.getIdMoneda().equals(this.idMoneda)) {
                    differences.add("catalogo_moneda");
                }
            }
            if (catalogoItem.getIdRazonUrgencia() != null && this.idRazonUrgencia != null) {
                if (!catalogoItem.getIdRazonUrgencia().equals(this.idRazonUrgencia)) {
                    differences.add("");
                }
            }
            if (catalogoItem.getMaterial() != null) {
                if (this.material != null) {
                    if (!catalogoItem.getMaterial().equals(this.material)) {
                        differences.add("catalogo_material");
                    }
                } else {
                    differences.add("catalogo_material");
                }
            }
            if (catalogoItem.getMedidas() != null) {
                if (this.medidas != null) {
                    if (!catalogoItem.getMedidas().equals(this.medidas)) {
                        differences.add("catalogo_medidas");
                    }
                } else {
                    differences.add("catalogo_medidas");
                }
            }
            if (catalogoItem.getMuestraPrecioMercado() != null && this.muestraPrecioMercado != null) {
                if (!catalogoItem.getMuestraPrecioMercado().equals(this.muestraPrecioMercado)) {

                    log.debug("*** equalsWithDifferences *** catalogoItem >  " + catalogoItem.getMuestraPrecioMercado());
                    log.debug("*** equalsWithDifferences *** this         > " + this.muestraPrecioMercado);

                    differences.add("catalogo_mostrar_precio_merc");
                }
            }
            if (catalogoItem.getNombreCatalogo() != null && this.nombreCatalogo != null) {
                if (!catalogoItem.getNombreCatalogo().equals(this.nombreCatalogo)) {
                    differences.add("");
                }
            }
            if (catalogoItem.getNombreIva() != null && this.nombreIva != null) {
                if (!catalogoItem.getNombreIva().equals(this.nombreIva)) {
                    differences.add("");
                }
            }
            if (catalogoItem.getNumeroParteFabricante() != null) {
                if (this.numeroParteFabricante != null) {
                    if (!catalogoItem.getNumeroParteFabricante().equals(this.numeroParteFabricante)) {
                        differences.add("catalogo_num_part_fabr");
                    }
                } else {
                    differences.add("catalogo_num_part_fabr");
                }
            }
            if (catalogoItem.getNumeroParteProveedor() != null) {
                if (this.numeroParteProveedor != null) {
                    if (!catalogoItem.getNumeroParteProveedor().equals(this.numeroParteProveedor)) {
                        differences.add("catalogo_num_part_prov");
                    }
                } else {
                    differences.add("catalogo_num_part_prov");
                }
            }
            if (catalogoItem.getPrecioMercado() != null && this.precioMercado != null) {
                if (!catalogoItem.getPrecioMercado().equals(this.precioMercado)) {
                    differences.add("catalogo_precio_merc");
                }
            }
            if (catalogoItem.getPrecioUnitario() != null && this.precioUnitario != null) {
                if (!catalogoItem.getPrecioUnitario().equals(this.precioUnitario)) {
                    differences.add("catalogo_precio_unit");
                }
            }
            // <RELEASE ARG >
            if (catalogoItem.getSubfamilia() != null && this.subfamilia != null) {
                if (!catalogoItem.getSubfamilia().equals(this.subfamilia)) {
                    differences.add("catalogo_sub_familia");
                }
            }
            // <RELEASE ARG />
            if (catalogoItem.getTiempoEntrega() != null && this.tiempoEntrega != null) {
                if (!catalogoItem.getTiempoEntrega().equals(this.tiempoEntrega)) {
                    differences.add("catalogo_tiempo_entrega");
                }
            }
            if (catalogoItem.getUdm() != null && this.udm != null) {
                if (!catalogoItem.getUdm().equals(this.udm)) {
                    differences.add("catalogo_udm");
                }
            }
            if (catalogoItem.getUen() != null && this.uen != null) {
                if (!catalogoItem.getUen().equals(this.uen)) {
                    differences.add("");
                }
            }
            if (catalogoItem.getMaxPrecioUnitario() != null && this.maxPrecioUnitario != null) {
                if (!catalogoItem.getMaxPrecioUnitario().equals(this.maxPrecioUnitario)) {
                    differences.add("max_precion_unitario");
                }
            }

            if (catalogoItem.getDescuento() != null && this.descuento != null) {
                if (!catalogoItem.getDescuento().equals(this.descuento)) {
                    differences.add("descuento");
                }
            }

            if (catalogoItem.getMesInfo() != null && this.mesInfo != null) {
                if (catalogoItem.getMesInfo().size() == this.mesInfo.size()) {
                    if (!catalogoItem.getMesInfo().containsAll(this.mesInfo)) {
                        differences.add("item_mes");
                    }
                } else {
                    differences.add("item_mes");
                }
            }

            if (catalogoItem.getRangoInfo() != null && this.rangoInfo != null) {
                if (catalogoItem.getRangoInfo().size() == this.rangoInfo.size()) {
                    if (!catalogoItem.getRangoInfo().containsAll(this.rangoInfo)) {
                        differences.add("item_rango");
                    }
                } else {
                    differences.add("item_rango");
                }
            }

            if (catalogoItem.getIdTemplate() != null && !catalogoItem.getIdTemplate().equals(this.idTemplate)) {
                differences.add("");
            }

        }
        log.debug(differences);
        return differences;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder().append(" ")
                .append("categoria=").append(this.categoria == null ? "null" : categoria).append("\n")
                .append("codigoItem=").append(this.codigoItem == null ? "null" : codigoItem).append("\n")
                .append("color=").append(this.color == null ? "null" : color).append("\n")
                .append("descripcion=").append(this.descripcion == null ? "null" : descripcion).append("\n")
                .append("fabricante=").append(this.fabricante == null ? "null" : fabricante).append("\n")
                .append("familia=").append(this.familia == null ? "null" : familia).append("\n")
                .append("idCatalogoUen=").append(this.idCatalogoUen == null ? "null" : idCatalogoUen).append("\n")
                .append("idEstatus=").append(this.idEstatus == null ? "null" : idEstatus).append("\n")
                .append("idIva=").append(this.idIva == null ? "null" : idIva).append("\n")
                .append("idLinea=").append(this.idLinea == null ? "null" : idLinea).append("\n")
                .append("idMoneda=").append(this.idMoneda == null ? "null" : idMoneda).append("\n")
                .append("idRazonUrgencia=").append(this.idRazonUrgencia == null ? "null" : idRazonUrgencia).append("\n")
                .append("material=").append(this.material == null ? "null" : material).append("\n")
                .append("medidas=").append(this.medidas == null ? "null" : medidas).append("\n")
                .append("muestraPrecioMercado=").append(this.muestraPrecioMercado == null ? "null" : muestraPrecioMercado).append("\n")
                .append("nombreCatalogo=").append(this.nombreCatalogo == null ? "null" : nombreCatalogo).append("\n")
                .append("numeroParteFabricante=").append(this.numeroParteFabricante == null ? "null" : numeroParteFabricante).append("\n")
                .append("numeroParteProveedor=").append(this.numeroParteProveedor == null ? "null" : numeroParteProveedor).append("\n")
                .append("precioMercado=").append(this.precioMercado == null ? "null" : precioMercado).append("\n")
                .append("precioUnitario=").append(this.precioUnitario == null ? "null" : precioUnitario).append("\n")
                .append("subfamilia=").append(this.subfamilia == null ? "null" : subfamilia).append("\n")
                .append("tiempoEntrega=").append(this.tiempoEntrega == null ? "null" : tiempoEntrega).append("\n")
                .append("udm=").append(this.udm == null ? "null" : udm).append("\n")
                .append("uen=").append(this.uen == null ? "null" : uen).append("\n")
                .append("codProducto=").append(this.codProducto == null ? "null" : codProducto).append("\n");
        return sb.toString();
    }

    /**
     * @return the motivoRegresoList
     */
    public List<NvcTblMotivoRegresoItem> getMotivoRegresoList() {
        return motivoRegresoList;
    }

    /**
     * @param motivoRegresoList the motivoRegresoList to set
     */
    public void setMotivoRegresoList(List<NvcTblMotivoRegresoItem> motivoRegresoList) {
        this.motivoRegresoList = motivoRegresoList;
    }

    public String getNombreUdm() {
        return nombreUdm;
    }

    public void setNombreUdm(String nombreUdm) {
        this.nombreUdm = nombreUdm;
    }

    public String getNombreMoneda() {
        return nombreMoneda;
    }

    public void setNombreMoneda(String nombreMoneda) {
        this.nombreMoneda = nombreMoneda;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
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

    public String getNombreEstatus() {
        return nombreEstatus;
    }

    public void setNombreEstatus(String nombreEstatus) {
        this.nombreEstatus = nombreEstatus;
    }

    public String getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(String codProducto) {
        this.codProducto = codProducto;
    }

    public Integer getIdUen() {
        return idUen;
    }

    public void setIdUen(Integer idUen) {
        this.idUen = idUen;
    }

    /**
     * @return the muestraPrecioMercadoBool
     */
    public boolean isMuestraPrecioMercadoBool() {
        return muestraPrecioMercado == null ? false : muestraPrecioMercado.equals(String.valueOf(ConstantsUtils.BIN_TRUE));
    }

    /**
     * @param muestraPrecioMercadoBool the muestraPrecioMercadoBool to set
     */
    public void setMuestraPrecioMercadoBool(boolean muestraPrecioMercadoBool) {
        this.muestraPrecioMercadoBool = muestraPrecioMercadoBool;
        this.muestraPrecioMercado = muestraPrecioMercadoBool == true ? String.valueOf(ConstantsUtils.BIN_TRUE) : String.valueOf(ConstantsUtils.BIN_FALSE);
    }

    /**
     * @return the descripcionLarga
     */
    public String getDescripcionLarga() {
        StringBuilder desc = new StringBuilder();
        desc.append(descripcion != null ? descripcion : "")
                .append(material != null ? " " + material : "")
                .append(color != null ? " " + color : "")
                .append(medidas != null ? " " + medidas : "")
                .append(udm != null ? " " + udm : "");
        return desc.toString();
    }

    /**
     * @param descripcionLarga the descripcionLarga to set
     */
    public void setDescripcionLarga(String descripcionLarga) {
        this.descripcionLarga = descripcionLarga;
    }

    /**
     * @return the editado
     */
    public boolean isEditado() {
        return editado;
    }

    /**
     * @param editado the editado to set
     */
    public void setEditado(boolean editado) {
        this.editado = editado;
    }

    /**
     * @return the nombreAdministrador
     */
    public String getNombreAdministrador() {
        return nombreAdministrador;
    }

    /**
     * @param nombreAdministrador the nombreAdministrador to set
     */
    public void setNombreAdministrador(String nombreAdministrador) {
        this.nombreAdministrador = nombreAdministrador;
    }
    //<RM16773>

    private boolean isEquals(Object input1, Object input2) {
        if (input1 == null) {
            input1 = "null";
        }

        if (input2 == null) {
            input2 = "null";
        }

        return input1.equals(input2);

    }
    //</RM16773>

    public Timestamp getFecha_actualizacion() {
        return fecha_actualizacion;
    }

    public void setFecha_actualizacion(Timestamp fecha_actualizacion) {
        this.fecha_actualizacion = fecha_actualizacion;
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

    public int getDescuentoInt() {
        return VALUE_TRUE.equals(descuento) ? 1 : 0;
    }

    // Inicio Omar Estrella Se agregaron informacion para Rango
    public List<NvcTblItemRangoInfoPojo> getRangoInfo() {
        return rangoInfo;
    }

    public void setRangoInfo(List<NvcTblItemRangoInfoPojo> rangoInfo) {
        this.rangoInfo = rangoInfo;
    }

    public List<NvcTblItemRangoInfoPojo> getRangoInfoDeleted() {
        return rangoInfoDeleted;
    }

    public void setRangoInfoDeleted(List<NvcTblItemRangoInfoPojo> rangoInfoDeleted) {
        this.rangoInfoDeleted = rangoInfoDeleted;
    }

    public List<NvcTblIMonthItemInfoPojo> getMesInfo() {
        return mesInfo;
    }

    public void setMesInfo(List<NvcTblIMonthItemInfoPojo> mesInfo) {
        this.mesInfo = mesInfo;
    }

    public List<NvcTblIMonthItemInfoPojo> getMesInfoDeleted() {
        return mesInfoDeleted;
    }

    public void setMesInfoDeleted(List<NvcTblIMonthItemInfoPojo> mesInfoDeleted) {
        this.mesInfoDeleted = mesInfoDeleted;
    }
    // Fin Omar Estrella Se agregaron informacion para Rango

    ///////////////----- J. Alfred
    public Integer getIdTemplate() {
        return idTemplate;
    }

    public void setIdTemplate(Integer idTemplate) {
        this.idTemplate = idTemplate;
    }

    public NvcTblCatalogoVariablePojo getTemplate() {
        return template;
    }

    public void setTemplate(NvcTblCatalogoVariablePojo template) {
        this.template = template;
    }
    ///////////////----- J. Alfred

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
