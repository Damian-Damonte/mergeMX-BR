package com.metalsa.requisicion.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.metalsa.requisicion.utils.ConstantsUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author APOMS7376
 */
public class NvcTblCarroCompraPojo implements Serializable {

    private static final long serialVersionUID = 1L;

//    private static final java.text.SimpleDateFormat SDF = new java.text.SimpleDateFormat(ConstantsUtils.DATE_PATTERN_DD_MM_YYYY);
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(NvcTblCarroCompraPojo.class);
    private Integer idCarroCompra;
    private String idUsuarioCreacion;
    private String descripcion;
    private String color;
    private String medidas;
    private String modelo;
    private String numeroParteModelo;
    private String itemGenerico;
    private String nombreGenerico;
    private String marca;
    private String idUdm;
    private String material;
    private Integer idSubfamilia;
    private Double cantidad;
    private Integer cantidadDisponible;
    private String fechaNecesidad;
    private NvcTblRazonUrgenciaPojo razonUrgencia;
    private Integer idRazonUrgencia;
    private String comentariosComprador;
    private List<NvcTblDocumentoPojo> documentos;
    private List<NvcTblDocumentoPojo> documentoOriginal;
    private List<NvcTblDocumentoPojo> docsAgregar;
    private List<NvcTblDocumentoPojo> docsEliminar;
    private String comprador;
    private String nombreUen;
    private Integer idUen;
    private Integer idEstatus;
    private Integer publicado;
//    private NvcTblCatalogoItem item;
//    private Integer idItem;
    private Integer idItemALmacen;
    private Date fechaActualizacionItem;
    private Date fechaInsercionCarro;
    private String urlFtp;

    private String fuente;
    private String estatus;
    private String tipoRequisicion;
    private String VTipo;

    private String razonUrgenciaDes;
    private String udm;
    private String proveedor;
    private Integer idProveedor;
    private Double precioUnitario;
    private Integer tipoItem;
    private Integer idItem;
    private Integer idAlmacen;
    private List<NvcTblCarroCompraDetallePojo> listDetalleCompra;
    
    private NvcTblCarroCompraDetallePojo detalle;
    private boolean itemEditado;
    private Integer tiempoEntrega;
    private String numeroParteProveedor;
    private String razonSpot;
    private Integer totalSugerencias;
    @JsonProperty(value = "id_localizacion")
    private Integer idlocalizacion;
    private String observacionRecibo;
    private LocalizacionesPojo localizaciones;
    private String localizacion;
    private Integer idTipoRecibo;
    private Integer tipoRecibo;
    private Integer idUenSurtidora;
    private Integer idUenRequisitora;
    private Integer idRequisicion;
    private Integer idPartida;
    private boolean activoMotor;
    //<R17548>
    private boolean controlado;
    private Integer idMotivoSol;
    private Integer perIdUsuarioSol;
    //</R17548>

    private String idMoneda;

    // MDA vales fondo //
    private Integer vfIvaId;// vf -> vales fondo
    private String vfIdRequisitor;
    private boolean vfWithholding;
    private boolean vfSendDoc;
    private Integer vfIdFacturacion;
    private Integer vfIdEnviarA;
    private boolean vfImprimirDoc;
    private List<String> vfContactosProveedor;
    private String vfContactosAdicionales;
    private Integer vfVendorSiteId;
    //<R40578>
    private String siteCode;
    //</R40578>
    //***************//

    //** MDA FAD-INICIO**//
    private boolean fad;
    private Integer idFad;
    private NvcTblFadPojo formatoAsignacionDirecta;
    //** MDA FAD-FIN**//

    // <CAT_VAR>
    private Integer idTipoPrecio;
    private Integer idTemplate;
    private Double montoTotal;
    private String variables;
    private Double descuento;
    private Double subtotal;
    private Double precioUnitarioBase;      // se reutiliza la variable vales fondo y cv
    private String itemNumMaximo;      // se utilza usarios maximo
    // <CAT_VAR>
    private String responsableMultiCc;
    private Integer punchout;
    private Integer idRf;
    private Integer rfNumber;
    private String commodity;
    private Integer idPartNumber;

    private Integer pedidoEspecial;
    private Integer pedidoCantMax;
    private Long blanketPO;
    private String pedidoOrganizacion;

    public NvcTblCarroCompraPojo() {
    }

    public NvcTblCarroCompraPojo(
            Integer idCarroCompra,
            String descripcion,
            Integer idItem,
            Integer idItemAlmacen,
            Double cantidad,
            String udm
    ) {
        try {
            log.debug("idCarro:" + idCarroCompra);
            this.idCarroCompra = idCarroCompra;

            if (idItem == null) {
                this.idItem = idItemAlmacen;
            } else {
                this.idItem = idItem;
            }

            this.cantidad = cantidad;
            this.descripcion = descripcion;
            this.udm = udm;
        } catch (Exception e) {

            log.error(e);
        }
    }

    public NvcTblCarroCompraPojo(
            Integer idCarroCompra,
            String descripcion,
            Integer idItem,
            String idUsuarioCreacion
    ) {
        try {
            this.idCarroCompra = idCarroCompra;
            this.descripcion = descripcion;
            this.idItem = idItem;
            this.idUsuarioCreacion = idUsuarioCreacion;
        } catch (Exception e) {
            log.error(e);
        }
    }

    //<R17548>
    public NvcTblCarroCompraPojo(
            Integer idCarroCompra,
            String descripcion,
            String color,
            String medidas,
            String modelo,
            String numeroParteModelo,
            String itemGenerico,
            String nombreGenerico,
            String marca,
            String idUdm,
            String material,
            Integer idSubfamilia,
            Double cantidad,
            Date fechaNecesidad,
            Integer idRazonUrgencia,
            String comentariosComprador,
            String comprador,
            String nombreUen,
            Long organizationId,
            Integer idItemALmacen,
            String razonSpot,
            Integer totalSugerencias,
            Integer idUenSurtidora,
            Integer idMotivoSol,
            Integer perIdUsuarioSol
    ) {
        try {
            this.idCarroCompra = idCarroCompra;
            this.descripcion = descripcion;
            this.color = color;
            this.medidas = medidas;
            this.modelo = modelo;
            this.numeroParteModelo = numeroParteModelo;
            this.itemGenerico = itemGenerico;
            this.nombreGenerico = nombreGenerico;
            this.marca = marca;
            this.idUdm = idUdm;
            this.material = material;
            this.idSubfamilia = idSubfamilia;
            this.cantidad = cantidad;
            this.fechaNecesidad = fechaNecesidad == null ? "" : ConstantsUtils.formattDate_DD_MM_YYYY(fechaNecesidad);
            this.idRazonUrgencia = idRazonUrgencia;
            this.comentariosComprador = comentariosComprador;
            this.comprador = comprador;
            this.nombreUen = nombreUen;
            this.idUen = organizationId == null ? 0 : organizationId.intValue();
            this.intercambiarUen(idUen, idUenSurtidora);
            this.idItemALmacen = idItemALmacen;
            this.razonSpot = razonSpot;
            this.totalSugerencias = totalSugerencias;
            this.idMotivoSol = idMotivoSol;
            this.perIdUsuarioSol = perIdUsuarioSol;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
    //</R17548>

    public NvcTblCarroCompraPojo(Integer idCarroCompra, String descripcion, String color, String medidas,
            String modelo, String numeroParteModelo, String itemGenerico, String nombreGenerico, String marca,
            String idUdm, String material, Integer idSubfamilia, Double cantidad, String fechaNecesidad,
            NvcTblRazonUrgenciaPojo razonUrgencia,
            Integer idRazonUrgencia,
            String comentariosComprador,
            List<NvcTblDocumentoPojo> documentos,
            String comprador) {
        this.idCarroCompra = idCarroCompra;
        this.descripcion = descripcion;
        this.color = color;
        this.medidas = medidas;
        this.modelo = modelo;
        this.numeroParteModelo = numeroParteModelo;
        this.itemGenerico = itemGenerico;
        this.nombreGenerico = nombreGenerico;
        this.marca = marca;
        this.idUdm = idUdm;
        this.material = material;
        this.idSubfamilia = idSubfamilia;
        this.cantidad = cantidad;
        this.fechaNecesidad = fechaNecesidad;
        this.razonUrgencia = razonUrgencia;
        this.idRazonUrgencia = idRazonUrgencia;
        this.comentariosComprador = comentariosComprador;
        this.documentos = documentos;
        this.comprador = comprador;
    }

    public Integer getIdCarroCompra() {
        return idCarroCompra;
    }

    //<R17548>
    public NvcTblCarroCompraPojo(
            Integer idCarroCompra,
            Integer idItem,
            Integer idAlmacen,
            Double cantidad,
            String idUsuarioCreacion,
            Integer tipoItem,
            String descripcion,
            String material,
            String color,
            String itemGenerico,
            String medidas,
            String numeroParteModelo,
            String nombreGenerico,
            String marca,
            String udm,
            String idUdm,
            Integer idUen,
            String uenDes,
            Integer idRazonUrgencia,
            String razonUrgenciaDes,
            Double precioUnitario,
            Integer idProveedor,
            String proveedor,
            Integer idEstatus,
            Integer publicado,
            Date fechaActualizacionItem,
            Date fechaInsercionCarro,
            String urlFtp,
            Integer idSubfamilia,
            Integer tiempoEntrega,
            Integer cantidadDisponible,
            String numeroParteProveedor,
            Date fechaNecesidad,
            Integer idUenSurtidora,
            Integer idLocalizacion,
            String observacionRecibo,
            String localizacion,
            Integer idTipoRecibo,
            Integer tipoRecibo,
            String idMoneda,
            String fuente,
            Integer activoMotor,
            Integer controlado,
            Integer idMotivoSol,
            Integer perIdUsuarioSol,
            Integer vfIvaId,// vf -> vales fondo
            String vfIdRequisitor,
            boolean vfWithholding,
            boolean vfSendDoc,
            Integer vfIdFacturacion,
            Integer vfIdEnviarA,
            boolean vfImprimirDoc,
            String comentariosComprador,
            Integer vfVendorSiteId,
            Integer idFad,
            Integer idTipoPrecio, // <CAT_VAR>
            Integer idTemplate, // <CAT_VAR>
            Double precioUnitarioBase, // <CAT_VAR>
            String responsableMultiCc,
            Integer punchout,
            Integer idRf,
            Integer rfNumber,
            String commodity,
            Integer pedidoCantMax,
            Integer pedidoEspecial
    ) {
        this.idCarroCompra = idCarroCompra;
        this.idItem = idItem;
        this.idAlmacen = idAlmacen;
        this.cantidad = cantidad;
        this.idUsuarioCreacion = idUsuarioCreacion;
        this.tipoItem = tipoItem;
        this.descripcion = descripcion;
        this.material = material;
        this.color = color;
        this.itemGenerico = itemGenerico;
        this.medidas = medidas;
        this.numeroParteModelo = numeroParteModelo;
        this.nombreGenerico = nombreGenerico;
        this.marca = marca;
        this.modelo = numeroParteModelo;
        this.udm = udm;
        this.idUdm = idUdm;
//        this.idUen = idUen;
        this.nombreUen = uenDes;
        this.idRazonUrgencia = idRazonUrgencia;
        this.razonUrgenciaDes = razonUrgenciaDes;
        this.precioUnitario = precioUnitario;
        this.idProveedor = idProveedor;
        this.proveedor = proveedor;
        this.idEstatus = idEstatus;
        this.publicado = publicado;
        this.fechaActualizacionItem = fechaActualizacionItem;
        this.fechaInsercionCarro = fechaInsercionCarro;
        this.urlFtp = urlFtp;
        this.idSubfamilia = idSubfamilia;
        if (fechaActualizacionItem != null && fechaInsercionCarro != null) {
            this.itemEditado = fechaInsercionCarro.before(fechaActualizacionItem);
        } else {
            this.itemEditado = false;
        }
        this.tiempoEntrega = tiempoEntrega;
        this.cantidadDisponible = cantidadDisponible;
        this.numeroParteProveedor = numeroParteProveedor;
        this.fechaNecesidad = fechaNecesidad == null ? "" : ConstantsUtils.formattDate_DD_MM_YYYY(fechaNecesidad);
        this.intercambiarUen(idUen, idUenSurtidora);
        this.idlocalizacion = idLocalizacion;
        this.observacionRecibo = observacionRecibo;
        this.localizacion = localizacion;
        // RDM 13553 APOMR10051 //
        this.tipoRecibo = tipoRecibo;
        if (this.tipoRecibo != null) {
            if (this.tipoRecibo == 1 || this.tipoRecibo == 2) {
                this.idTipoRecibo = tipoRecibo;
            } else {
                this.idTipoRecibo = idTipoRecibo;
            }
        } else {
            this.idTipoRecibo = idTipoRecibo;
        }
        this.idMoneda = idMoneda;
        this.fuente = fuente;
        if (activoMotor != null) {
            this.activoMotor = (activoMotor == 1);
        }
        if (controlado != null) {
            this.controlado = (controlado > 0);
        }
        //MDA Vales fondo 
        this.idMotivoSol = idMotivoSol;
        this.perIdUsuarioSol = perIdUsuarioSol;
        this.vfIvaId = vfIvaId;// vf -> vales fondo
        this.vfIdRequisitor = vfIdRequisitor;
        this.vfWithholding = vfWithholding;
        this.vfSendDoc = vfSendDoc;
        this.vfIdFacturacion = vfIdFacturacion;
        this.vfIdEnviarA = vfIdEnviarA;
        this.vfImprimirDoc = vfImprimirDoc;
        this.comentariosComprador = comentariosComprador;
        this.vfVendorSiteId = vfVendorSiteId;
        /* MDA VALES FONDO */
        this.idFad = idFad;
        // <CAT_VAR>
        this.idTipoPrecio = idTipoPrecio;
        this.idTemplate = idTemplate;
        this.precioUnitarioBase = precioUnitarioBase;
        this.responsableMultiCc = responsableMultiCc;
        // <CAT_VAR>
        this.punchout = punchout;
        this.idRf = idRf;
        this.rfNumber = rfNumber;
        this.commodity = commodity;
        this.pedidoCantMax = pedidoCantMax;
        this.pedidoEspecial = pedidoEspecial;
    }

    //<R40578>
    public NvcTblCarroCompraPojo(
            Integer idCarroCompra,
            Integer idItem,
            Integer idAlmacen,
            Double cantidad,
            String idUsuarioCreacion,
            Integer tipoItem,
            String descripcion,
            String material,
            String color,
            String itemGenerico,
            String medidas,
            String numeroParteModelo,
            String nombreGenerico,
            String marca,
            String udm,
            String idUdm,
            Integer idUen,
            String uenDes,
            Integer idRazonUrgencia,
            String razonUrgenciaDes,
            Double precioUnitario,
            Integer idProveedor,
            String proveedor,
            Integer idEstatus,
            Integer publicado,
            Date fechaActualizacionItem,
            Date fechaInsercionCarro,
            String urlFtp,
            Integer idSubfamilia,
            Integer tiempoEntrega,
            Integer cantidadDisponible,
            String numeroParteProveedor,
            Date fechaNecesidad,
            Integer idUenSurtidora,
            Integer idLocalizacion,
            String observacionRecibo,
            String localizacion,
            Integer idTipoRecibo,
            Integer tipoRecibo,
            String idMoneda,
            String fuente,
            Integer activoMotor,
            Integer controlado,
            Integer idMotivoSol,
            Integer perIdUsuarioSol,
            Integer vfIvaId,// vf -> vales fondo
            String vfIdRequisitor,
            boolean vfWithholding,
            boolean vfSendDoc,
            Integer vfIdFacturacion,
            Integer vfIdEnviarA,
            boolean vfImprimirDoc,
            String comentariosComprador,
            Integer vfVendorSiteId,
            String siteCode,
            Integer idFad,
            Integer idTipoPrecio, // <CAT_VAR>
            Integer idTemplate, // <CAT_VAR>
            Double precioUnitarioBase, // <CAT_VAR>
            String responsableMultiCc,
            Integer punchout,
            Integer idRf,
            Integer rfNumber,
            String commodity,
            Integer pedidoCantMax,
            Integer pedidoEspecial
    ) {
        this.idCarroCompra = idCarroCompra;
        this.idItem = idItem;
        this.idAlmacen = idAlmacen;
        this.cantidad = cantidad;
        this.idUsuarioCreacion = idUsuarioCreacion;
        this.tipoItem = tipoItem;
        this.descripcion = descripcion;
        this.material = material;
        this.color = color;
        this.itemGenerico = itemGenerico;
        this.medidas = medidas;
        this.numeroParteModelo = numeroParteModelo;
        this.nombreGenerico = nombreGenerico;
        this.marca = marca;
        this.modelo = numeroParteModelo;
        this.udm = udm;
        this.idUdm = idUdm;
//        this.idUen = idUen;
        this.nombreUen = uenDes;
        this.idRazonUrgencia = idRazonUrgencia;
        this.razonUrgenciaDes = razonUrgenciaDes;
        this.precioUnitario = precioUnitario;
        this.idProveedor = idProveedor;
        this.proveedor = proveedor;
        this.idEstatus = idEstatus;
        this.publicado = publicado;
        this.fechaActualizacionItem = fechaActualizacionItem;
        this.fechaInsercionCarro = fechaInsercionCarro;
        this.urlFtp = urlFtp;
        this.idSubfamilia = idSubfamilia;
        if (fechaActualizacionItem != null && fechaInsercionCarro != null) {
            this.itemEditado = fechaInsercionCarro.before(fechaActualizacionItem);
        } else {
            this.itemEditado = false;
        }
        this.tiempoEntrega = tiempoEntrega;
        this.cantidadDisponible = cantidadDisponible;
        this.numeroParteProveedor = numeroParteProveedor;
        this.fechaNecesidad = fechaNecesidad == null ? "" : ConstantsUtils.formattDate_DD_MM_YYYY(fechaNecesidad);
        this.intercambiarUen(idUen, idUenSurtidora);
        this.idlocalizacion = idLocalizacion;
        this.observacionRecibo = observacionRecibo;
        this.localizacion = localizacion;
        // RDM 13553 APOMR10051 //
        this.tipoRecibo = tipoRecibo;
        if (this.tipoRecibo != null) {
            if (this.tipoRecibo == 1 || this.tipoRecibo == 2) {
                this.idTipoRecibo = tipoRecibo;
            } else {
                this.idTipoRecibo = idTipoRecibo;
            }
        } else {
            this.idTipoRecibo = idTipoRecibo;
        }
        this.idMoneda = idMoneda;
        this.fuente = fuente;
        if (activoMotor != null) {
            this.activoMotor = (activoMotor == 1);
        }
        if (controlado != null) {
            this.controlado = (controlado > 0);
        }
        //MDA Vales fondo 
        this.idMotivoSol = idMotivoSol;
        this.perIdUsuarioSol = perIdUsuarioSol;
        this.vfIvaId = vfIvaId;// vf -> vales fondo
        this.vfIdRequisitor = vfIdRequisitor;
        this.vfWithholding = vfWithholding;
        this.vfSendDoc = vfSendDoc;
        this.vfIdFacturacion = vfIdFacturacion;
        this.vfIdEnviarA = vfIdEnviarA;
        this.vfImprimirDoc = vfImprimirDoc;
        this.comentariosComprador = comentariosComprador;
        this.vfVendorSiteId = vfVendorSiteId;
        this.siteCode = siteCode;
        /* MDA VALES FONDO */
        this.idFad = idFad;
        // <CAT_VAR>
        this.idTipoPrecio = idTipoPrecio;
        this.idTemplate = idTemplate;
        this.precioUnitarioBase = precioUnitarioBase;
        // <CAT_VAR>
        this.responsableMultiCc = responsableMultiCc;
        this.punchout = punchout;
        this.idRf = idRf;
        this.rfNumber = rfNumber;
        this.commodity = commodity;
        this.pedidoCantMax = pedidoCantMax;
        this.pedidoEspecial = pedidoEspecial;
    }
    //</R40578>
    //</R17548>

//    public NvcTblCarroCompraPojo(
//          Integer idCarroCompra,
//          Integer idItem,
//          Integer idAlmacen,
//          Double cantidad,
//          String idUsuarioCreacion,
//          Integer tipoItem,
//          String descripcion,
//          String material,
//          String color,
//          String medidas,
//          String udm,
//          String idUdm,
//          Integer idUen,
//          String uenDes,
//          Integer idRazonUrgencia,
//          String razonUrgenciaDes,
//          Double precioUnitario,
//          Integer idProveedor,
//          String proveedor,
//          Integer idEstatus,
//          Integer publicado,
//          Date fechaActualizacionItem,
//          Date fechaInsercionCarro,
//          String urlFtp,
//          Integer idSubfamilia,
//          Integer tiempoEntrega,
//          Integer cantidadDisponible,
//          String numeroParteProveedor,
//          Date fechaNecesidad,
//          Integer idLocalizacion,
//          String observacionRecibo,
//          String localizacion,
//          Integer tipoRecibo
//    ) {
//        this.idCarroCompra = idCarroCompra;
//        this.idItem = idItem;
//        this.idAlmacen = idAlmacen;
//        this.cantidad = cantidad;
//        this.idUsuarioCreacion = idUsuarioCreacion;
//        this.tipoItem = tipoItem;
//        this.descripcion = descripcion;
//        this.material = material;
//        this.color = color;
//        this.medidas = medidas;
//        this.udm = udm;
//        this.idUdm = idUdm;
//        this.idUen = idUen;
//        this.nombreUen = uenDes;
//        this.idRazonUrgencia = idRazonUrgencia;
//        this.razonUrgenciaDes = razonUrgenciaDes;
//        this.precioUnitario = precioUnitario;
//        this.idProveedor = idProveedor;
//        this.proveedor = proveedor;
//        this.idEstatus = idEstatus;
//        this.publicado = publicado;
//        this.fechaActualizacionItem = fechaActualizacionItem;
//        this.fechaInsercionCarro = fechaInsercionCarro;
//        this.urlFtp = urlFtp;
//        this.idSubfamilia = idSubfamilia;
//        if (fechaActualizacionItem != null && fechaInsercionCarro != null) {
//            this.itemEditado = fechaInsercionCarro.before(fechaActualizacionItem);
//        } else {
//            this.itemEditado = false;
//        }
//        this.tiempoEntrega = tiempoEntrega;
//        this.cantidadDisponible = cantidadDisponible;
//        this.numeroParteProveedor = numeroParteProveedor;
//        this.fechaNecesidad = fechaNecesidad == null ? "" : ConstantsUtils.formattDate_DD_MM_YYYY(fechaNecesidad);
//        this.idlocalizacion = idLocalizacion;
//        this.observacionRecibo = observacionRecibo;
//        this.localizacion = localizacion;
//        this.tipoRecibo = tipoRecibo;
//        this.intercambiarUen(idUen, idUenSurtidora);
//
//    }
//    public NvcTblCarroCompraPojo(Integer idCarroCompra, Integer idItem, Double cantidad, String comprador, Integer tipoItem, String descripcion, String material, String color, String medidas, String udm, String idUdm, Integer idUen, String uenDes, Integer idRazonUrgencia, String razonUrgenciaDes, Double precioUnitario, Integer idProveedor, String proveedor, String urlFtp) {
//        this.idCarroCompra = idCarroCompra;
//        this.idItem = idItem;
//        this.cantidad = cantidad;
//        this.comprador = comprador;
//        this.tipoItem = tipoItem;
//        this.descripcion = descripcion;
//        this.material = material;
//        this.color = color;
//        this.medidas = medidas;
//        this.udm = udm;
//        this.idUdm = idUdm;
//        this.idUen = idUen;
//        this.nombreUen = uenDes;
//        this.idRazonUrgencia = idRazonUrgencia;
//        this.razonUrgenciaDes = razonUrgenciaDes;
//        this.precioUnitario = precioUnitario;
//        this.idProveedor = idProveedor;
//        this.proveedor = proveedor;
//        this.urlFtp = urlFtp;
//    }
    public NvcTblCarroCompraPojo(Integer idCarroCompra, String descripcion, String color, String medidas, String modelo, String numeroParteModelo, String itemGenerico, String nombreGenerico, String material, String marca) {
        this.idCarroCompra = idCarroCompra;
        this.descripcion = descripcion;
        this.color = color;
        this.medidas = medidas;
        this.modelo = modelo;
        this.numeroParteModelo = numeroParteModelo;
        this.itemGenerico = itemGenerico;
        this.nombreGenerico = nombreGenerico;
        this.material = material;
        this.marca = marca;
    }

    public void setIdCarroCompra(Integer idCarroCompra) {
        this.idCarroCompra = idCarroCompra;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNombreGenerico() {
        return nombreGenerico;
    }

    public void setNombreGenerico(String nombreGenerico) {
        this.nombreGenerico = nombreGenerico;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getIdSubfamilia() {
        return idSubfamilia;
    }

    public void setIdSubfamilia(Integer idSubfamilia) {
        this.idSubfamilia = idSubfamilia;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public String getFechaNecesidad() {
        return fechaNecesidad;
    }

    public void setFechaNecesidad(String fechaNecesidad) {
        this.fechaNecesidad = fechaNecesidad;
    }

    public Integer getIdRazonUrgencia() {
        return idRazonUrgencia;
    }

    public void setIdRazonUrgencia(Integer idRazonUrgencia) {
        this.idRazonUrgencia = idRazonUrgencia;
    }

    public String getComentariosComprador() {
        return comentariosComprador;
    }

    public void setComentariosComprador(String comentariosComprador) {
        this.comentariosComprador = comentariosComprador;
    }

    public List<NvcTblDocumentoPojo> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<NvcTblDocumentoPojo> documentos) {
        this.documentos = documentos;
    }

    public NvcTblRazonUrgenciaPojo getRazonUrgencia() {
        return razonUrgencia;
    }

    public void setRazonUrgencia(NvcTblRazonUrgenciaPojo razonUrgencia) {
        this.razonUrgencia = razonUrgencia;
    }

    public String getItemGenerico() {
        return itemGenerico;
    }

    public void setItemGenerico(String itemGenerico) {
        this.itemGenerico = itemGenerico;
    }

    public String getNumeroParteModelo() {
        return numeroParteModelo;
    }

    public void setNumeroParteModelo(String numeroParteModelo) {
        this.numeroParteModelo = numeroParteModelo;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getIdUdm() {
        return idUdm;
    }

    public void setIdUdm(String idUdm) {
        this.idUdm = idUdm;
    }

//    public NvcVItemsAll getNvcVitem() {
//        return nvcVitem;
//    }
//
//    public void setNvcVitem(NvcVItemsAll nvcVitem) {
//        this.nvcVitem = nvcVitem;
//    }
//
//    public NvcTblCatalogoItem getItem() {
//        return item;
//    }
//
//    public void setItem(NvcTblCatalogoItem item) {
//        this.item = item;
//    }
    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    public String getNombreUen() {
        return nombreUen;
    }

    public void setNombreUen(String nombreUen) {
        this.nombreUen = nombreUen;
    }

    public Integer getIdUen() {
        return idUen;
    }

    public void setIdUen(Integer idUen) {
        this.idUen = idUen;
    }

//    public Integer getIdItemAlmacen() {
//        return idItemAlmacen;
//    }
//
//    public void setIdItemAlmacen(Integer idItemAlmacen) {
//        this.idItemAlmacen = idItemAlmacen;
//    }
    public String getUrlFtp() {
        return urlFtp;
    }

    public void setUrlFtp(String urlFtp) {
        this.urlFtp = urlFtp;
    }

    public String getUdm() {
        return udm;
    }

    public void setUdm(String udm) {
        this.udm = udm;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Integer getTipoItem() {
        return tipoItem;
    }

    public void setTipoItem(Integer tipoItem) {
        this.tipoItem = tipoItem;
    }

    public String getRazonUrgenciaDes() {
        return razonUrgenciaDes;
    }

    public void setRazonUrgenciaDes(String razonUrgenciaDes) {
        this.razonUrgenciaDes = razonUrgenciaDes;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public List<NvcTblCarroCompraDetallePojo> getListDetalleCompra() {
        return listDetalleCompra;
    }
    
    @JsonIgnore
    public NvcTblCarroCompraDetallePojo getAnyDetalle() {
        if (listDetalleCompra != null && !listDetalleCompra.isEmpty()) {
            return listDetalleCompra.get(0);
        }
        return detalle;
    }

    public void setListDetalleCompra(List<NvcTblCarroCompraDetallePojo> listDetalleCompra) {
        this.listDetalleCompra = listDetalleCompra;
    }

    public List<NvcTblDocumentoPojo> getDocumentoOriginal() {
        return documentoOriginal;
    }

    public void setDocumentoOriginal(List<NvcTblDocumentoPojo> documentoOriginal) {
        this.documentoOriginal = documentoOriginal;
    }

    public List<NvcTblDocumentoPojo> getDocsAgregar() {
        return docsAgregar;
    }

    public void setDocsAgregar(List<NvcTblDocumentoPojo> docsAgregar) {
        this.docsAgregar = docsAgregar;
    }

    public List<NvcTblDocumentoPojo> getDocsEliminar() {
        return docsEliminar;
    }

    public void setDocsEliminar(List<NvcTblDocumentoPojo> docsEliminar) {
        this.docsEliminar = docsEliminar;
    }

    public Integer getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(Integer cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public void setIdUsuarioCreacion(String idUsuarioCreacion) {
        this.idUsuarioCreacion = idUsuarioCreacion;
    }

    public String getIdUsuarioCreacion() {
        return idUsuarioCreacion;
    }

    /**
     * @return the fechaActualizacionItem
     */
    public Date getFechaActualizacionItem() {
        return fechaActualizacionItem;
    }

    /**
     * @param fechaActualizacionItem the fechaActualizacionItem to set
     */
    public void setFechaActualizacionItem(Date fechaActualizacionItem) {
        this.fechaActualizacionItem = fechaActualizacionItem;
    }

    /**
     * @return the fechaInsercionCarro
     */
    public Date getFechaInsercionCarro() {
        return fechaInsercionCarro;
    }

    /**
     * @param fechaInsercionCarro the fechaInsercionCarro to set
     */
    public void setFechaInsercionCarro(Date fechaInsercionCarro) {
        this.fechaInsercionCarro = fechaInsercionCarro;
    }

    /**
     * @return the idEstatus
     */
    public Integer getIdEstatus() {
        return idEstatus;
    }

    /**
     * @param idEstatus the idEstatus to set
     */
    public void setIdEstatus(Integer idEstatus) {
        this.idEstatus = idEstatus;
    }

    /**
     * @return the itemEditado
     */
    public boolean isItemEditado() {
        return itemEditado;
    }

    /**
     * @param itemEditado the itemEditado to set
     */
    public void setItemEditado(boolean itemEditado) {
        this.itemEditado = itemEditado;
    }

    public String getNumeroParteProveedor() {
        return numeroParteProveedor;
    }

    public void setNumeroParteProveedor(String numeroParteProveedor) {
        this.numeroParteProveedor = numeroParteProveedor;
    }

    public Integer getTiempoEntrega() {
        return tiempoEntrega;
    }

    public void setTiempoEntrega(Integer tiempoEntrega) {
        this.tiempoEntrega = tiempoEntrega;
    }

    /**
     * @return the publicado
     */
    public Integer getPublicado() {
        return publicado;
    }

    /**
     * @param publicado the publicado to set
     */
    public void setPublicado(Integer publicado) {
        this.publicado = publicado;
    }

    /**
     * @return the idAlmacen
     */
    public Integer getIdAlmacen() {
        return idAlmacen;
    }

    /**
     * @param idAlmacen the idAlmacen to set
     */
    public void setIdAlmacen(Integer idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public String getRazonSpot() {
        return razonSpot;
    }

    public void setRazonSpot(String razonSpot) {
        this.razonSpot = razonSpot;
    }

    public Integer getTotalSugerencias() {
        return totalSugerencias;
    }

    public void setTotalSugerencias(Integer totalSugerencias) {
        this.totalSugerencias = totalSugerencias;
    }

    public Integer getIdUenSurtidora() {
        return idUenSurtidora;
    }

    public void setIdUenSurtidora(Integer idUenSurtidora) {
        this.idUenSurtidora = idUenSurtidora;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    private void intercambiarUen(Integer idUen, Integer idUenSurtidora) {
        //si contiene uen surtidora, entonces 
        if (idUenSurtidora != null) {
            //la uen requisitora sera la uen original
            this.idUenRequisitora = idUen;
            //la uen id_uen se le asigna la uen surtidora para agrupar
            this.idUen = idUenSurtidora;
            this.idUenSurtidora = idUenSurtidora;
        } else {
            this.idUen = idUen;
        }
    }

    public Integer getIdUenRequisitora() {
        return idUenRequisitora;
    }

    public void setIdUenRequisitora(Integer idUenRequisitora) {
        this.idUenRequisitora = idUenRequisitora;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getTipoRequisicion() {
        return tipoRequisicion;
    }

    public void setTipoRequisicion(String tipoRequisicion) {
        this.tipoRequisicion = tipoRequisicion;
    }

    public String getVTipo() {
        return VTipo;
    }

    public void setVTipo(String VTipo) {
        this.VTipo = VTipo;
    }

    /**
     * @return the idlocalizacion
     */
    public Integer getIdlocalizacion() {
        return idlocalizacion;
    }

    /**
     * @param idlocalizacion the idlocalizacion to set
     */
    public void setIdlocalizacion(Integer idlocalizacion) {
        this.idlocalizacion = idlocalizacion;
    }

    /**
     * @return the observacionRecibo
     */
    public String getObservacionRecibo() {
        return observacionRecibo;
    }

    /**
     * @param observacionRecibo the observacionRecibo to set
     */
    public void setObservacionRecibo(String observacionRecibo) {
        this.observacionRecibo = observacionRecibo;
    }

    @JsonIgnore
    public LocalizacionesPojo getLocalizaciones() {
        return localizaciones;
    }

    public void setLocalizaciones(LocalizacionesPojo localizaciones) {
        this.localizaciones = localizaciones;
    }

    /**
     * @return the localizacion
     */
    public String getLocalizacion() {
        return localizacion;
    }

    /**
     * @param localizacion the localizacion to set
     */
    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    /**
     * @return the tipoRecibo
     */
    public Integer getTipoRecibo() {
        return tipoRecibo;
    }

    /**
     * @param tipoRecibo the tipoRecibo to set
     */
    public void setTipoRecibo(Integer tipoRecibo) {
        this.tipoRecibo = tipoRecibo;
    }

    public Integer getIdTipoRecibo() {
        return idTipoRecibo;
    }

    public void setIdTipoRecibo(Integer idTipoRecibo) {
        this.idTipoRecibo = idTipoRecibo;
    }

    public String getIdMoneda() {
        return idMoneda;
    }

    public void setIdMoneda(String idMoneda) {
        this.idMoneda = idMoneda;
    }

    public Integer getIdPartida() {
        return idPartida;
    }

    public Integer getIdRequisicion() {
        return idRequisicion;
    }

    public void setIdPartida(Integer idPartida) {
        this.idPartida = idPartida;
    }

    public void setIdRequisicion(Integer idRequisicion) {
        this.idRequisicion = idRequisicion;
    }

    public boolean isActivoMotor() {
        return activoMotor;
    }

    public void setActivoMotor(boolean activoMotor) {
        this.activoMotor = activoMotor;
    }

    //<R17548>
    public boolean isControlado() {
        return controlado;
    }

    public void setControlado(boolean controlado) {
        this.controlado = controlado;
    }

    public Integer getIdMotivoSol() {
        return idMotivoSol;
    }

    public void setIdMotivoSol(Integer idMotivoSol) {
        this.idMotivoSol = idMotivoSol;
    }

    public Integer getPerIdUsuarioSol() {
        return perIdUsuarioSol;
    }

    public void setPerIdUsuarioSol(Integer perIdUsuarioSol) {
        this.perIdUsuarioSol = perIdUsuarioSol;
    }
    //</R17548>

    // ************************ MDA Vales fondo*********************************** //
    public Integer getVfIvaId() {
        return vfIvaId;
    }

    public void setVfIvaId(Integer vfIvaId) {
        this.vfIvaId = vfIvaId;
    }

    public String getVfIdRequisitor() {
        return vfIdRequisitor;
    }

    public void setVfIdRequisitor(String vfIdRequisitor) {
        this.vfIdRequisitor = vfIdRequisitor;
    }

    public boolean isVfWithholding() {
        return vfWithholding;
    }

    public void setVfWithholding(boolean vfWithholding) {
        this.vfWithholding = vfWithholding;
    }

    public boolean isVfSendDoc() {
        return vfSendDoc;
    }

    public void setVfSendDoc(boolean vfSendDoc) {
        this.vfSendDoc = vfSendDoc;
    }

    public Integer getVfIdFacturacion() {
        return vfIdFacturacion;
    }

    public void setVfIdFacturacion(Integer vfIdFacturacion) {
        this.vfIdFacturacion = vfIdFacturacion;
    }

    public Integer getVfIdEnviarA() {
        return vfIdEnviarA;
    }

    public void setVfIdEnviarA(Integer vfIdEnviarA) {
        this.vfIdEnviarA = vfIdEnviarA;
    }

    public boolean isVfImprimirDoc() {
        return vfImprimirDoc;
    }

    public void setVfImprimirDoc(boolean vfImprimirDoc) {
        this.vfImprimirDoc = vfImprimirDoc;
    }

    public List<String> getVfContactosProveedor() {
        return vfContactosProveedor;
    }

    public void setVfContactosProveedor(List<String> vfContactosProveedor) {
        this.vfContactosProveedor = vfContactosProveedor;
    }

    public String getVfContactosAdicionales() {
        return vfContactosAdicionales;
    }

    public void setVfContactosAdicionales(String vfContactosAdicionales) {
        this.vfContactosAdicionales = vfContactosAdicionales;
    }

    public Integer getVfVendorSiteId() {
        return vfVendorSiteId;
    }

    public void setVfVendorSiteId(Integer vfVendorSiteId) {
        this.vfVendorSiteId = vfVendorSiteId;
    }

    //<R40578>
    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }
    //</R40578>

    // ************************ MDA Vales fondo*********************************** //
    //** MDA FAD-INICIO**//
    public boolean isFad() {
        return fad;
    }

    public void setFad(boolean fad) {
        this.fad = fad;
    }

    public Integer getIdFad() {
        return idFad;
    }

    public void setIdFad(Integer idFad) {
        this.idFad = idFad;
    }

    public void setFormatoAsignacionDirecta(NvcTblFadPojo formatoAsignacionDirecta) {
        this.formatoAsignacionDirecta = formatoAsignacionDirecta;
    }

    @JsonIgnore
    public NvcTblFadPojo getFormatoAsignacionDirecta() {
        return formatoAsignacionDirecta;
    }

    //** MDA FAD-FIN**//
    // <CAT_VAR>
    public Integer getIdTipoPrecio() {
        return idTipoPrecio;
    }

    public void setIdTipoPrecio(Integer idTipoPrecio) {
        this.idTipoPrecio = idTipoPrecio;
    }

    public Integer getIdTemplate() {
        return idTemplate;
    }

    public void setIdTemplate(Integer idTemplate) {
        this.idTemplate = idTemplate;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getVariables() {
        return variables;
    }

    public void setVariables(String variables) {
        this.variables = variables;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getPrecioUnitarioBase() {
        return precioUnitarioBase;
    }

    public void setPrecioUnitarioBase(Double precioUnitarioBase) {
        this.precioUnitarioBase = precioUnitarioBase;
    }
    // <CAT_VAR>

    public String getResponsableMultiCc() {
        return responsableMultiCc;
    }

    public void setResponsableMultiCc(String responsableMultiCc) {
        this.responsableMultiCc = responsableMultiCc;
    }

    public Integer getPunchout() {
        return punchout;
    }

    public void setPunchout(Integer punchout) {
        this.punchout = punchout;
    }

    public String getItemNumMaximo() {
        return itemNumMaximo;
    }

    public void setItemNumMaximo(String itemNumMaximo) {
        this.itemNumMaximo = itemNumMaximo;
    }

    /**
     * @return the idRf
     */
    public Integer getIdRf() {
        return idRf;
    }

    /**
     * @param idRf the idRf to set
     */
    public void setIdRf(Integer idRf) {
        this.idRf = idRf;
    }

    /**
     * @return the rfNumber
     */
    public Integer getRfNumber() {
        return rfNumber;
    }

    /**
     * @param rfNumber the rfNumber to set
     */
    public void setRfNumber(Integer rfNumber) {
        this.rfNumber = rfNumber;
    }

    /**
     * @return the commodity
     */
    public String getCommodity() {
        return commodity;
    }

    /**
     * @param commodity the commodity to set
     */
    public void setCommodity(String commodity) {
        this.commodity = commodity;
    }

    /**
     * @return the idPartNumber
     */
    public Integer getIdPartNumber() {
        return idPartNumber;
    }

    /**
     * @param idPartNumber the idPartNumber to set
     */
    public void setIdPartNumber(Integer idPartNumber) {
        this.idPartNumber = idPartNumber;
    }

    public Integer getPedidoEspecial() {
        return pedidoEspecial;
    }

    public void setPedidoEspecial(Integer pedidoEspecial) {
        this.pedidoEspecial = pedidoEspecial;
    }

    public Integer getPedidoCantMax() {
        return pedidoCantMax;
    }

    public void setPedidoCantMax(Integer pedidoCantMax) {
        this.pedidoCantMax = pedidoCantMax;
    }

    public Long getBlanketPO() {
        return blanketPO;
    }

    public void setBlanketPO(Long blanketPO) {
        this.blanketPO = blanketPO;
    }

    public String getPedidoOrganizacion() {
        return pedidoOrganizacion;
    }

    public void setPedidoOrganizacion(String pedidoOrganizacion) {
        this.pedidoOrganizacion = pedidoOrganizacion;
    }
    
    public NvcTblCarroCompraDetallePojo getDetalle() {
        return this.detalle;
    }
    
    public void setDetalle(NvcTblCarroCompraDetallePojo detalle) {
        this.detalle = detalle;
    }

    @Override
    public String toString() {
        return "NvcTblCarroCompraPojo{" +
                "idCarroCompra=" + idCarroCompra +
                ", idUsuarioCreacion='" + idUsuarioCreacion + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", color='" + color + '\'' +
                ", cantidad=" + cantidad +
                ", idUen=" + idUen +
                ", idEstatus=" + idEstatus +
                ", idItemALmacen=" + idItemALmacen +
                ", fuente='" + fuente + '\'' +
                ", estatus='" + estatus + '\'' +
                ", tipoRequisicion='" + tipoRequisicion + '\'' +
                ", VTipo='" + VTipo + '\'' +
                ", tipoItem=" + tipoItem +
                ", idItem=" + idItem +
                ", idAlmacen=" + idAlmacen +
                ", listDetalleCompra=" + listDetalleCompra +
                ", detalle=" + detalle +
                ", idUenSurtidora=" + idUenSurtidora +
                ", idUenRequisitora=" + idUenRequisitora +
                ", idRequisicion=" + idRequisicion +
                ", idPartida=" + idPartida +
                ", idTipoPrecio=" + idTipoPrecio +
                ", idlocalizacion=" + idlocalizacion +
                ", localizacion=" + localizacion +
                '}';
    }
}
