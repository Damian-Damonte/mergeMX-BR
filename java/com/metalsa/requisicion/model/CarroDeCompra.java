package com.metalsa.requisicion.model;

import com.metalsa.aprobacion.model.NvcTblOrganizacionesH;
import com.metalsa.aprobacion.model.Usuario;
import com.metalsa.catalogo.model.IcomAccCategories;
import com.metalsa.catalogo.model.NvcTblOaLocalizacionesH;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import com.metalsa.catalogo.model.NvcVmOaIvaH;
import com.metalsa.core.model.NvcTblFamilias;
import com.metalsa.core.model.NvcTblOaCcH;
import com.metalsa.core.model.NvcTblProvSitesH;
import com.metalsa.core.model.NvcTblProyectosH;

/**
 *
 * @author juliocisneros
 */
@Entity
@Table(name = "CARRO_DE_COMPRA", catalog = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CarroDeCompra.findAll", query = "SELECT c FROM CarroDeCompra c")
    ,
    @NamedQuery(name = "CarroDeCompra.findByConsecutivo", query = "SELECT c FROM CarroDeCompra c WHERE c.consecutivo = :consecutivo")
    ,
    @NamedQuery(name = "CarroDeCompra.findByIdContrato", query = "SELECT c FROM CarroDeCompra c WHERE c.idContrato = :idContrato")
    ,
    @NamedQuery(name = "CarroDeCompra.findByIdProducto", query = "SELECT c FROM CarroDeCompra c WHERE c.idProducto = :idProducto")
    ,
    @NamedQuery(name = "CarroDeCompra.findByDescripcion", query = "SELECT c FROM CarroDeCompra c WHERE c.descripcion = :descripcion")
    ,
    @NamedQuery(name = "CarroDeCompra.findByPrecio", query = "SELECT c FROM CarroDeCompra c WHERE c.precio = :precio")
    ,
    @NamedQuery(name = "CarroDeCompra.findByLeadTime", query = "SELECT c FROM CarroDeCompra c WHERE c.leadTime = :leadTime")
    ,
    @NamedQuery(name = "CarroDeCompra.findByIdUnidadDeMedida", query = "SELECT c FROM CarroDeCompra c WHERE c.idUnidadDeMedida = :idUnidadDeMedida")
    ,
    @NamedQuery(name = "CarroDeCompra.findByCantidadRequerida", query = "SELECT c FROM CarroDeCompra c WHERE c.cantidadRequerida = :cantidadRequerida")
    ,
    @NamedQuery(name = "CarroDeCompra.findByFechaRequerida", query = "SELECT c FROM CarroDeCompra c WHERE c.fechaRequerida = :fechaRequerida")
    ,
    @NamedQuery(name = "CarroDeCompra.findByIdCuenta", query = "SELECT c FROM CarroDeCompra c WHERE c.idCuenta = :idCuenta")
    ,
    @NamedQuery(name = "CarroDeCompra.findByIdAlmacen", query = "SELECT c FROM CarroDeCompra c WHERE c.idAlmacen = :idAlmacen")
    ,
    @NamedQuery(name = "CarroDeCompra.findByMontoExtendido", query = "SELECT c FROM CarroDeCompra c WHERE c.montoExtendido = :montoExtendido")
    ,
    @NamedQuery(name = "CarroDeCompra.findByUrgente", query = "SELECT c FROM CarroDeCompra c WHERE c.urgente = :urgente")
    ,
    @NamedQuery(name = "CarroDeCompra.findByRazonUrgencia", query = "SELECT c FROM CarroDeCompra c WHERE c.razonUrgencia = :razonUrgencia")
    ,
    @NamedQuery(name = "CarroDeCompra.findByAtiende", query = "SELECT c FROM CarroDeCompra c WHERE c.atiende = :atiende")
    ,
    @NamedQuery(name = "CarroDeCompra.findByFuente", query = "SELECT c FROM CarroDeCompra c WHERE c.fuente = :fuente")
    ,
    @NamedQuery(name = "CarroDeCompra.findBySegmento1", query = "SELECT c FROM CarroDeCompra c WHERE c.segmento1 = :segmento1")
    ,
    @NamedQuery(name = "CarroDeCompra.findBySegmento2", query = "SELECT c FROM CarroDeCompra c WHERE c.segmento2 = :segmento2")
    ,
    @NamedQuery(name = "CarroDeCompra.findBySegmento3", query = "SELECT c FROM CarroDeCompra c WHERE c.segmento3 = :segmento3")
    ,
    @NamedQuery(name = "CarroDeCompra.findBySegmento4", query = "SELECT c FROM CarroDeCompra c WHERE c.segmento4 = :segmento4")
    ,
    @NamedQuery(name = "CarroDeCompra.findBySegmento5", query = "SELECT c FROM CarroDeCompra c WHERE c.segmento5 = :segmento5")
    ,
    @NamedQuery(name = "CarroDeCompra.findBySegmento6", query = "SELECT c FROM CarroDeCompra c WHERE c.segmento6 = :segmento6")
    ,
    @NamedQuery(name = "CarroDeCompra.findBySegmento7", query = "SELECT c FROM CarroDeCompra c WHERE c.segmento7 = :segmento7")
    ,
    @NamedQuery(name = "CarroDeCompra.findBySegmento8", query = "SELECT c FROM CarroDeCompra c WHERE c.segmento8 = :segmento8")
    ,
    @NamedQuery(name = "CarroDeCompra.findBySeleccion", query = "SELECT c FROM CarroDeCompra c WHERE c.seleccion = :seleccion")
    ,
    @NamedQuery(name = "CarroDeCompra.findByValida", query = "SELECT c FROM CarroDeCompra c WHERE c.valida = :valida")
    ,
    @NamedQuery(name = "CarroDeCompra.findByDescuento", query = "SELECT c FROM CarroDeCompra c WHERE c.descuento = :descuento")
    ,
    @NamedQuery(name = "CarroDeCompra.findByPrecioBruto", query = "SELECT c FROM CarroDeCompra c WHERE c.precioBruto = :precioBruto")
    ,
    @NamedQuery(name = "CarroDeCompra.findByCodProyecto", query = "SELECT c FROM CarroDeCompra c WHERE c.codProyecto = :codProyecto")
    ,
    @NamedQuery(name = "CarroDeCompra.findByIdTarea", query = "SELECT c FROM CarroDeCompra c WHERE c.idTarea = :idTarea")
    ,
    @NamedQuery(name = "CarroDeCompra.findByCodTarea", query = "SELECT c FROM CarroDeCompra c WHERE c.codTarea = :codTarea")
    ,
    @NamedQuery(name = "CarroDeCompra.findByTipoGasto", query = "SELECT c FROM CarroDeCompra c WHERE c.tipoGasto = :tipoGasto")
    ,
    @NamedQuery(name = "CarroDeCompra.findByQuoteNumber", query = "SELECT c FROM CarroDeCompra c WHERE c.quoteNumber = :quoteNumber")
    ,
    @NamedQuery(name = "CarroDeCompra.findByVendorPartNumber", query = "SELECT c FROM CarroDeCompra c WHERE c.vendorPartNumber = :vendorPartNumber")
    ,
    @NamedQuery(name = "CarroDeCompra.findByWorkOrderNumber", query = "SELECT c FROM CarroDeCompra c WHERE c.workOrderNumber = :workOrderNumber")
    ,
    @NamedQuery(name = "CarroDeCompra.findByNotesToBuyer", query = "SELECT c FROM CarroDeCompra c WHERE c.notesToBuyer = :notesToBuyer")
    ,
    @NamedQuery(name = "CarroDeCompra.findByCodProducto", query = "SELECT c FROM CarroDeCompra c WHERE c.codProducto = :codProducto")
    ,
    @NamedQuery(name = "CarroDeCompra.findByTipoLinea", query = "SELECT c FROM CarroDeCompra c WHERE c.tipoLinea = :tipoLinea")
    ,
    @NamedQuery(name = "CarroDeCompra.findByFechaDeInsercion", query = "SELECT c FROM CarroDeCompra c WHERE c.fechaDeInsercion = :fechaDeInsercion")
    ,
    @NamedQuery(name = "CarroDeCompra.findByWorkOrderId", query = "SELECT c FROM CarroDeCompra c WHERE c.workOrderId = :workOrderId")
    ,
    @NamedQuery(name = "CarroDeCompra.findByEnn", query = "SELECT c FROM CarroDeCompra c WHERE c.enn = :enn")
    ,
    @NamedQuery(name = "CarroDeCompra.findByIdSubtarea", query = "SELECT c FROM CarroDeCompra c WHERE c.idSubtarea = :idSubtarea")
    ,
    @NamedQuery(name = "CarroDeCompra.findByCodSubtarea", query = "SELECT c FROM CarroDeCompra c WHERE c.codSubtarea = :codSubtarea")
    ,
    @NamedQuery(name = "CarroDeCompra.findByReferencia1", query = "SELECT c FROM CarroDeCompra c WHERE c.referencia1 = :referencia1")
    ,
    @NamedQuery(name = "CarroDeCompra.findByManufacturerpart", query = "SELECT c FROM CarroDeCompra c WHERE c.manufacturerpart = :manufacturerpart")
    ,
    @NamedQuery(name = "CarroDeCompra.findByManufacturer", query = "SELECT c FROM CarroDeCompra c WHERE c.manufacturer = :manufacturer")
    ,
    @NamedQuery(name = "CarroDeCompra.findByCodItemgen", query = "SELECT c FROM CarroDeCompra c WHERE c.codItemgen = :codItemgen")
    ,
    @NamedQuery(name = "CarroDeCompra.findByIdCategoriaGto", query = "SELECT c FROM CarroDeCompra c WHERE c.idCategoriaGto = :idCategoriaGto")
    ,
    @NamedQuery(name = "CarroDeCompra.findByIdSubcategoriaGto", query = "SELECT c FROM CarroDeCompra c WHERE c.idSubcategoriaGto = :idSubcategoriaGto")
    ,
    @NamedQuery(name = "CarroDeCompra.findByIdEnnGto", query = "SELECT c FROM CarroDeCompra c WHERE c.idEnnGto = :idEnnGto")
    ,
    @NamedQuery(name = "CarroDeCompra.findByIdEstacionGto", query = "SELECT c FROM CarroDeCompra c WHERE c.idEstacionGto = :idEstacionGto")
    ,
    @NamedQuery(name = "CarroDeCompra.findByIdMaquinaGto", query = "SELECT c FROM CarroDeCompra c WHERE c.idMaquinaGto = :idMaquinaGto")
    ,
    @NamedQuery(name = "CarroDeCompra.findByIdPlantaGto", query = "SELECT c FROM CarroDeCompra c WHERE c.idPlantaGto = :idPlantaGto")
    ,
    @NamedQuery(name = "CarroDeCompra.findByIdAreaGto", query = "SELECT c FROM CarroDeCompra c WHERE c.idAreaGto = :idAreaGto")
    ,
    @NamedQuery(name = "CarroDeCompra.findByIdLineaGto", query = "SELECT c FROM CarroDeCompra c WHERE c.idLineaGto = :idLineaGto")
    ,
    @NamedQuery(name = "CarroDeCompra.findByIdAsigCcGtoMtto", query = "SELECT c FROM CarroDeCompra c WHERE c.idAsigCcGtoMtto = :idAsigCcGtoMtto")
    ,
    @NamedQuery(name = "CarroDeCompra.findByComentarioRequi", query = "SELECT c FROM CarroDeCompra c WHERE c.comentarioRequi = :comentarioRequi")
    ,
    @NamedQuery(name = "CarroDeCompra.findByParaOc", query = "SELECT c FROM CarroDeCompra c WHERE c.paraOc = :paraOc")
    ,
    @NamedQuery(name = "CarroDeCompra.findByIdItemgen", query = "SELECT c FROM CarroDeCompra c WHERE c.idItemgen = :idItemgen")
    ,
    @NamedQuery(name = "CarroDeCompra.findByPdti", query = "SELECT c FROM CarroDeCompra c WHERE c.pdti = :pdti")
    ,
    @NamedQuery(name = "CarroDeCompra.findByRefpdti", query = "SELECT c FROM CarroDeCompra c WHERE c.refpdti = :refpdti")})
public class CarroDeCompra implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "carroComprasS")
    @SequenceGenerator(name = "carroComprasS",
            sequenceName = "CARRO_COMPRAS_S",
            allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "CONSECUTIVO", nullable = false, precision = 22)
    private BigDecimal consecutivo;
    @Column(name = "ID_CONTRATO")
    private Long idContrato;
    @Size(max = 30)
    @Column(name = "ID_PRODUCTO", length = 30)
    private String idProducto;
    @Size(max = 250)
    @Column(name = "DESCRIPCION", length = 250)
    private String descripcion;
    @Column(name = "PRECIO")
    private Double precio;
    @Column(name = "LEAD_TIME")
    private Short leadTime;
    @Size(max = 3)
    @Column(name = "ID_UNIDAD_DE_MEDIDA", length = 3)
    private String idUnidadDeMedida;
    @Column(name = "CANTIDAD_REQUERIDA")
    private BigDecimal cantidadRequerida;
    @Column(name = "FECHA_REQUERIDA")
    @Temporal(TemporalType.DATE)
    private Date fechaRequerida;
    @Column(name = "ID_CUENTA")
    private Long idCuenta;
    @Column(name = "ID_ALMACEN")
    private BigInteger idAlmacen;
    @Column(name = "MONTO_EXTENDIDO")
    private BigInteger montoExtendido;
    @Size(max = 1)
    @Column(name = "URGENTE", length = 1)
    private String urgente;
    @Size(max = 230)
    @Column(name = "RAZON_URGENCIA", length = 230)
    private String razonUrgencia;
    @Size(max = 10)
    @Column(name = "ATIENDE", length = 10)
    private String atiende;
    @Size(max = 10)
    @Column(name = "FUENTE", length = 10)
    private String fuente;
    @Size(max = 150)
    @Column(name = "SEGMENTO_1", length = 150)
    private String segmento1;
    @Size(max = 150)
    @Column(name = "SEGMENTO_2", length = 150)
    private String segmento2;
    @Size(max = 150)
    @Column(name = "SEGMENTO_3", length = 150)
    private String segmento3;
    @Size(max = 150)
    @Column(name = "SEGMENTO_4", length = 150)
    private String segmento4;
    @Size(max = 150)
    @Column(name = "SEGMENTO_5", length = 150)
    private String segmento5;
    @Size(max = 150)
    @Column(name = "SEGMENTO_6", length = 150)
    private String segmento6;
    @Size(max = 150)
    @Column(name = "SEGMENTO_7", length = 150)
    private String segmento7;
    @Size(max = 150)
    @Column(name = "SEGMENTO_8", length = 150)
    private String segmento8;
    @Size(max = 1)
    @Column(name = "SELECCION", length = 1)
    private String seleccion;
    @Size(max = 1)
    @Column(name = "VALIDA", length = 1)
    private String valida;
    @Column(name = "DESCUENTO")
    private BigInteger descuento;
    @Column(name = "PRECIO_BRUTO")
    private BigInteger precioBruto;
    @Size(max = 25)
    @Column(name = "COD_PROYECTO", length = 25)
    private String codProyecto;
    @Column(name = "ID_TAREA")
    private BigInteger idTarea;
    @Size(max = 25)
    @Column(name = "COD_TAREA", length = 25)
    private String codTarea;
    @Size(max = 50)
    @Column(name = "TIPO_GASTO", length = 50)
    private String tipoGasto;
    @Size(max = 150)
    @Column(name = "QUOTE_NUMBER", length = 150)
    private String quoteNumber;
    @Size(max = 150)
    @Column(name = "VENDOR_PART_NUMBER", length = 150)
    private String vendorPartNumber;
    @Size(max = 150)
    @Column(name = "WORK_ORDER_NUMBER", length = 150)
    private String workOrderNumber;
    @Size(max = 240)
    @Column(name = "NOTES_TO_BUYER", length = 240)
    private String notesToBuyer;
    @Size(max = 50)
    @Column(name = "COD_PRODUCTO", length = 50)
    private String codProducto;
    @Size(max = 50)
    @Column(name = "TIPO_LINEA", length = 50)
    private String tipoLinea;
    @Column(name = "FECHA_DE_INSERCION")
    @Temporal(TemporalType.DATE)
    private Date fechaDeInsercion;
    @Column(name = "WORK_ORDER_ID")
    private BigInteger workOrderId;
    @Size(max = 10)
    @Column(name = "ENN", length = 10)
    private String enn;
    @Column(name = "ID_SUBTAREA")
    private BigInteger idSubtarea;
    @Size(max = 25)
    @Column(name = "COD_SUBTAREA", length = 25)
    private String codSubtarea;
    @Size(max = 50)
    @Column(name = "REFERENCIA_1", length = 50)
    private String referencia1;
    @Size(max = 30)
    @Column(name = "MANUFACTURERPART", length = 30)
    private String manufacturerpart;
    @Size(max = 240)
    @Column(name = "MANUFACTURER", length = 240)
    private String manufacturer;
    @Size(max = 40)
    @Column(name = "COD_ITEMGEN", length = 40)
    private String codItemgen;
    @Column(name = "ID_CATEGORIA_GTO")
    private BigInteger idCategoriaGto;
    @Column(name = "ID_SUBCATEGORIA_GTO")
    private BigInteger idSubcategoriaGto;
    @Column(name = "ID_ENN_GTO")
    private BigInteger idEnnGto;
    @Column(name = "ID_ESTACION_GTO")
    private BigInteger idEstacionGto;
    @Column(name = "ID_MAQUINA_GTO")
    private BigInteger idMaquinaGto;
    @Column(name = "ID_PLANTA_GTO")
    private BigInteger idPlantaGto;
    @Column(name = "ID_AREA_GTO")
    private BigInteger idAreaGto;
    @Column(name = "ID_LINEA_GTO")
    private BigInteger idLineaGto;
    @Column(name = "ID_ASIG_CC_GTO_MTTO")
    private BigInteger idAsigCcGtoMtto;
    @Size(max = 200)
    @Column(name = "COMENTARIO_REQUI", length = 200)
    private String comentarioRequi;
    @Column(name = "PARA_OC")
    private BigInteger paraOc;
    @Size(max = 30)
    @Column(name = "ID_ITEMGEN", length = 30)
    private String idItemgen;
    @Size(max = 2)
    @Column(name = "PDTI", length = 2)
    private String pdti;
    @Size(max = 60)
    @Column(name = "REFPDTI", length = 60)
    private String refpdti;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario idUsuario;
    @JoinColumn(name = "COMPRADOR", referencedColumnName = "ID_USUARIO")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario comprador;
    @JoinColumns({
        @JoinColumn(name = "ID_UEN", referencedColumnName = "ID_UEN", insertable = false, updatable = false)
        ,
        @JoinColumn(name = "IVA", referencedColumnName = "IVA", insertable = false, updatable = false)})
    @ManyToOne(fetch = FetchType.LAZY)
    private NvcVmOaIvaH nvcVmOaIvaH;
    @JoinColumn(name = "ID_PROYECTO", referencedColumnName = "ID_PROYECTO")
    @ManyToOne(fetch = FetchType.LAZY)
    private NvcTblProyectosH idProyecto;
    @JoinColumns({
        @JoinColumn(name = "ID_PROVEEDOR", referencedColumnName = "ID_PROVEEDOR")
        ,
        @JoinColumn(name = "ID_SUCURSAL_PROVEEDOR", referencedColumnName = "ID_SUCURSAL_PROVEEDOR")})
    @ManyToOne(fetch = FetchType.LAZY)
    private NvcTblProvSitesH nvcTblProvSitesH;
    @JoinColumn(name = "ID_UEN_SURTIDORA", referencedColumnName = "ID_UEN")
    @ManyToOne(fetch = FetchType.LAZY)
    private NvcTblOrganizacionesH idUenSurtidora;
    @JoinColumn(name = "ID_UEN", referencedColumnName = "ID_UEN")
    @ManyToOne(fetch = FetchType.LAZY)
    private NvcTblOrganizacionesH idUen;
    @JoinColumn(name = "ID_LOCALIZACION", referencedColumnName = "ID_LOCALIZACION")
    @ManyToOne(fetch = FetchType.LAZY)
    private NvcTblOaLocalizacionesH idLocalizacion;
    @JoinColumns({
        @JoinColumn(name = "ID_UEN", referencedColumnName = "ID_UEN", insertable = false, updatable = false)
        ,
        @JoinColumn(name = "ID_CENTRO_DE_COSTOS", referencedColumnName = "ID_CC", insertable = false, updatable = false)})
    @ManyToOne(fetch = FetchType.LAZY)
    private NvcTblOaCcH nvcTblOaCcH;
    @JoinColumn(name = "ID_MONEDA", referencedColumnName = "ID_MONEDA")
    @ManyToOne(fetch = FetchType.LAZY)
    private NvcTblMonedasH idMoneda;
    @JoinColumn(name = "ID_FAMILIA", referencedColumnName = "ID_FAMILIA")
    @ManyToOne(fetch = FetchType.LAZY)
    private NvcTblFamilias idFamilia;
    @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ACC_CAT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private IcomAccCategories categoryId;

    public CarroDeCompra() {
    }

    public CarroDeCompra(BigDecimal consecutivo) {
        this.consecutivo = consecutivo;
    }

    public BigDecimal getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(BigDecimal consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Long getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Long idContrato) {
        this.idContrato = idContrato;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Short getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(Short leadTime) {
        this.leadTime = leadTime;
    }

    public String getIdUnidadDeMedida() {
        return idUnidadDeMedida;
    }

    public void setIdUnidadDeMedida(String idUnidadDeMedida) {
        this.idUnidadDeMedida = idUnidadDeMedida;
    }

    public BigDecimal getCantidadRequerida() {
        return cantidadRequerida;
    }

    public void setCantidadRequerida(BigDecimal cantidadRequerida) {
        this.cantidadRequerida = cantidadRequerida;
    }

    public Date getFechaRequerida() {
        return fechaRequerida;
    }

    public void setFechaRequerida(Date fechaRequerida) {
        this.fechaRequerida = fechaRequerida;
    }

    public Long getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Long idCuenta) {
        this.idCuenta = idCuenta;
    }

    public BigInteger getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(BigInteger idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public BigInteger getMontoExtendido() {
        return montoExtendido;
    }

    public void setMontoExtendido(BigInteger montoExtendido) {
        this.montoExtendido = montoExtendido;
    }

    public String getUrgente() {
        return urgente;
    }

    public void setUrgente(String urgente) {
        this.urgente = urgente;
    }

    public String getRazonUrgencia() {
        return razonUrgencia;
    }

    public void setRazonUrgencia(String razonUrgencia) {
        this.razonUrgencia = razonUrgencia;
    }

    public String getAtiende() {
        return atiende;
    }

    public void setAtiende(String atiende) {
        this.atiende = atiende;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public String getSegmento1() {
        return segmento1;
    }

    public void setSegmento1(String segmento1) {
        this.segmento1 = segmento1;
    }

    public String getSegmento2() {
        return segmento2;
    }

    public void setSegmento2(String segmento2) {
        this.segmento2 = segmento2;
    }

    public String getSegmento3() {
        return segmento3;
    }

    public void setSegmento3(String segmento3) {
        this.segmento3 = segmento3;
    }

    public String getSegmento4() {
        return segmento4;
    }

    public void setSegmento4(String segmento4) {
        this.segmento4 = segmento4;
    }

    public String getSegmento5() {
        return segmento5;
    }

    public void setSegmento5(String segmento5) {
        this.segmento5 = segmento5;
    }

    public String getSegmento6() {
        return segmento6;
    }

    public void setSegmento6(String segmento6) {
        this.segmento6 = segmento6;
    }

    public String getSegmento7() {
        return segmento7;
    }

    public void setSegmento7(String segmento7) {
        this.segmento7 = segmento7;
    }

    public String getSegmento8() {
        return segmento8;
    }

    public void setSegmento8(String segmento8) {
        this.segmento8 = segmento8;
    }

    public String getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(String seleccion) {
        this.seleccion = seleccion;
    }

    public String getValida() {
        return valida;
    }

    public void setValida(String valida) {
        this.valida = valida;
    }

    public BigInteger getDescuento() {
        return descuento;
    }

    public void setDescuento(BigInteger descuento) {
        this.descuento = descuento;
    }

    public BigInteger getPrecioBruto() {
        return precioBruto;
    }

    public void setPrecioBruto(BigInteger precioBruto) {
        this.precioBruto = precioBruto;
    }

    public String getCodProyecto() {
        return codProyecto;
    }

    public void setCodProyecto(String codProyecto) {
        this.codProyecto = codProyecto;
    }

    public BigInteger getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(BigInteger idTarea) {
        this.idTarea = idTarea;
    }

    public String getCodTarea() {
        return codTarea;
    }

    public void setCodTarea(String codTarea) {
        this.codTarea = codTarea;
    }

    public String getTipoGasto() {
        return tipoGasto;
    }

    public void setTipoGasto(String tipoGasto) {
        this.tipoGasto = tipoGasto;
    }

    public String getQuoteNumber() {
        return quoteNumber;
    }

    public void setQuoteNumber(String quoteNumber) {
        this.quoteNumber = quoteNumber;
    }

    public String getVendorPartNumber() {
        return vendorPartNumber;
    }

    public void setVendorPartNumber(String vendorPartNumber) {
        this.vendorPartNumber = vendorPartNumber;
    }

    public String getWorkOrderNumber() {
        return workOrderNumber;
    }

    public void setWorkOrderNumber(String workOrderNumber) {
        this.workOrderNumber = workOrderNumber;
    }

    public String getNotesToBuyer() {
        return notesToBuyer;
    }

    public void setNotesToBuyer(String notesToBuyer) {
        this.notesToBuyer = notesToBuyer;
    }

    public String getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(String codProducto) {
        this.codProducto = codProducto;
    }

    public String getTipoLinea() {
        return tipoLinea;
    }

    public void setTipoLinea(String tipoLinea) {
        this.tipoLinea = tipoLinea;
    }

    public Date getFechaDeInsercion() {
        return fechaDeInsercion;
    }

    public void setFechaDeInsercion(Date fechaDeInsercion) {
        this.fechaDeInsercion = fechaDeInsercion;
    }

    public BigInteger getWorkOrderId() {
        return workOrderId;
    }

    public void setWorkOrderId(BigInteger workOrderId) {
        this.workOrderId = workOrderId;
    }

    public String getEnn() {
        return enn;
    }

    public void setEnn(String enn) {
        this.enn = enn;
    }

    public BigInteger getIdSubtarea() {
        return idSubtarea;
    }

    public void setIdSubtarea(BigInteger idSubtarea) {
        this.idSubtarea = idSubtarea;
    }

    public String getCodSubtarea() {
        return codSubtarea;
    }

    public void setCodSubtarea(String codSubtarea) {
        this.codSubtarea = codSubtarea;
    }

    public String getReferencia1() {
        return referencia1;
    }

    public void setReferencia1(String referencia1) {
        this.referencia1 = referencia1;
    }

    public String getManufacturerpart() {
        return manufacturerpart;
    }

    public void setManufacturerpart(String manufacturerpart) {
        this.manufacturerpart = manufacturerpart;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCodItemgen() {
        return codItemgen;
    }

    public void setCodItemgen(String codItemgen) {
        this.codItemgen = codItemgen;
    }

    public BigInteger getIdCategoriaGto() {
        return idCategoriaGto;
    }

    public void setIdCategoriaGto(BigInteger idCategoriaGto) {
        this.idCategoriaGto = idCategoriaGto;
    }

    public BigInteger getIdSubcategoriaGto() {
        return idSubcategoriaGto;
    }

    public void setIdSubcategoriaGto(BigInteger idSubcategoriaGto) {
        this.idSubcategoriaGto = idSubcategoriaGto;
    }

    public BigInteger getIdEnnGto() {
        return idEnnGto;
    }

    public void setIdEnnGto(BigInteger idEnnGto) {
        this.idEnnGto = idEnnGto;
    }

    public BigInteger getIdEstacionGto() {
        return idEstacionGto;
    }

    public void setIdEstacionGto(BigInteger idEstacionGto) {
        this.idEstacionGto = idEstacionGto;
    }

    public BigInteger getIdMaquinaGto() {
        return idMaquinaGto;
    }

    public void setIdMaquinaGto(BigInteger idMaquinaGto) {
        this.idMaquinaGto = idMaquinaGto;
    }

    public BigInteger getIdPlantaGto() {
        return idPlantaGto;
    }

    public void setIdPlantaGto(BigInteger idPlantaGto) {
        this.idPlantaGto = idPlantaGto;
    }

    public BigInteger getIdAreaGto() {
        return idAreaGto;
    }

    public void setIdAreaGto(BigInteger idAreaGto) {
        this.idAreaGto = idAreaGto;
    }

    public BigInteger getIdLineaGto() {
        return idLineaGto;
    }

    public void setIdLineaGto(BigInteger idLineaGto) {
        this.idLineaGto = idLineaGto;
    }

    public BigInteger getIdAsigCcGtoMtto() {
        return idAsigCcGtoMtto;
    }

    public void setIdAsigCcGtoMtto(BigInteger idAsigCcGtoMtto) {
        this.idAsigCcGtoMtto = idAsigCcGtoMtto;
    }

    public String getComentarioRequi() {
        return comentarioRequi;
    }

    public void setComentarioRequi(String comentarioRequi) {
        this.comentarioRequi = comentarioRequi;
    }

    public BigInteger getParaOc() {
        return paraOc;
    }

    public void setParaOc(BigInteger paraOc) {
        this.paraOc = paraOc;
    }

    public String getIdItemgen() {
        return idItemgen;
    }

    public void setIdItemgen(String idItemgen) {
        this.idItemgen = idItemgen;
    }

    public String getPdti() {
        return pdti;
    }

    public void setPdti(String pdti) {
        this.pdti = pdti;
    }

    public String getRefpdti() {
        return refpdti;
    }

    public void setRefpdti(String refpdti) {
        this.refpdti = refpdti;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public void setComprador(Usuario comprador) {
        this.comprador = comprador;
    }

    public NvcVmOaIvaH getNvcVmOaIvaH() {
        return nvcVmOaIvaH;
    }

    public void setNvcVmOaIvaH(NvcVmOaIvaH nvcVmOaIvaH) {
        this.nvcVmOaIvaH = nvcVmOaIvaH;
    }

    public NvcTblProyectosH getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(NvcTblProyectosH idProyecto) {
        this.idProyecto = idProyecto;
    }

    public NvcTblProvSitesH getNvcTblProvSitesH() {
        return nvcTblProvSitesH;
    }

    public void setNvcTblProvSitesH(NvcTblProvSitesH nvcTblProvSitesH) {
        this.nvcTblProvSitesH = nvcTblProvSitesH;
    }

    public NvcTblOrganizacionesH getIdUenSurtidora() {
        return idUenSurtidora;
    }

    public void setIdUenSurtidora(NvcTblOrganizacionesH idUenSurtidora) {
        this.idUenSurtidora = idUenSurtidora;
    }

    public NvcTblOrganizacionesH getIdUen() {
        return idUen;
    }

    public void setIdUen(NvcTblOrganizacionesH idUen) {
        this.idUen = idUen;
    }

    public NvcTblOaLocalizacionesH getIdLocalizacion() {
        return idLocalizacion;
    }

    public void setIdLocalizacion(NvcTblOaLocalizacionesH idLocalizacion) {
        this.idLocalizacion = idLocalizacion;
    }

    public NvcTblOaCcH getNvcTblOaCcH() {
        return nvcTblOaCcH;
    }

    public void setNvcTblOaCcH(NvcTblOaCcH nvcTblOaCcH) {
        this.nvcTblOaCcH = nvcTblOaCcH;
    }

    public NvcTblMonedasH getIdMoneda() {
        return idMoneda;
    }

    public void setIdMoneda(NvcTblMonedasH idMoneda) {
        this.idMoneda = idMoneda;
    }

    public NvcTblFamilias getIdFamilia() {
        return idFamilia;
    }

    public void setIdFamilia(NvcTblFamilias idFamilia) {
        this.idFamilia = idFamilia;
    }

    public IcomAccCategories getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(IcomAccCategories categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (consecutivo != null ? consecutivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CarroDeCompra)) {
            return false;
        }
        CarroDeCompra other = (CarroDeCompra) object;
        if ((this.consecutivo == null && other.consecutivo != null) || (this.consecutivo != null && !this.consecutivo.equals(other.consecutivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.CarroDeCompra[ consecutivo=" + consecutivo + " ]";
    }

}
