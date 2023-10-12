package com.metalsa.utils.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.metalsa.aprobacion.model.Usuario;
import com.metalsa.catalogo.model.NvcTblCatalogoItem;
import com.metalsa.core.model.DcEstatus;
import com.metalsa.utils.Constants;
import com.metalsa.catalogo.model.NvcTblAlmacenH;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


/**
 *
 * @author APOOD9272
 */
//<R17548>
@Table(name = "NVC_V_CARRO_COMPRA")
@Entity
@NamedQueries({
    @NamedQuery(
            name = "NvcVCarroCompra.findByUsuario",
            query = "SELECT n FROM NvcVCarroCompra n WHERE idUsuarioCreacion=:idUsuarioCreacion"),    
})
@Data
public class NvcVCarroCompra implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID_CARRO_COMPRA")
    private Long idCarroCompra;
    
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "idCarroCompra")    
    private NvcTblCarroCompraDetalle detalle;
    
    @Column(name = "ID_ITEM")
    private Integer idItem;
    
    /*@ManyToOne()
    @JoinColumn(
            name = "ID_ITEM",
            referencedColumnName = "ID_ITEM",
            insertable = false,
            updatable =  false
    )
    private NvcTblCatalogoItem item;*/

    @Column(name = "ID_ALMACEN")
    private Integer idAlmacen;
    
    @JoinColumn(
            name = "ID_ALMACEN",
            referencedColumnName = "ID_ALMACEN",
            insertable = false,
            updatable = false
    )
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private NvcTblAlmacenH almacen;

    @Column(name = "CANTIDAD")
    private Double cantidad;

    @Column(name = "ID_USUARIO_CREACION")
    private String idUsuarioCreacion;
    
    @JoinColumn(
            name = "ID_USUARIO_CREACION",
            referencedColumnName = "ID_USUARIO",
            insertable = false,
            updatable = false
    )
    @ManyToOne()
    private Usuario usuarioCreacion;

    @Column(name = "Tipoitem")
    private Integer tipoItem;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "MATERIAL")
    private String material;

    @Column(name = "COLOR")
    private String color;

    @Column(name = "ITEM_GENERICO")
    private String itemGenerico;

    @Column(name = "MEDIDAS")
    private String medidas;

    @Column(name = "NUMERO_PARTE_MODELO")
    private String numeroParteModelo;

    @Column(name = "NOMBRE_GENERICO")
    private String nombreGenerico;

    @Column(name = "MARCA")
    private String marca;

    @Column(name = "udm")
    private String udm;

    @Column(name = "id_udm")
    private String idUdm;

    @Column(name = "id_uen")
    private Integer idUen;

    @Column(name = "uendes")
    private String uenDes;

    @Column(name = "ID_RAZON_URGENCIA")
    private Integer idRazonUrgencia;

    @Column(name = "RAZON_URGENCIA_DES")
    private String razonUrgenciaDes;

    @Column(name = "preciounitario")
    private Double precioUnitario;

    @Column(name = "Id_proveedor")
    private Integer idProveedor;

    @Column(name = "NOMBREPROVEEDOR")
    private String proveedor;

    @JsonProperty
    @Column(name = "ID_ESTATUS")
    private Integer idEstatus;
    
    @JoinColumn(
            name = "ID_ESTATUS",
            referencedColumnName = "SC_ID",
            nullable = false,
            insertable = false,
            updatable = false
    )
    @ManyToOne
    private DcEstatus estatus;

    @Column(name = "ITEM_PUBLICADO")
    private Integer publicado;

    @Column(name = "FECHA_ACTUALIZACION_ITEM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacionItem;

    @Column(name = "FECHA_NECESIDAD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNecesidad;

    @Column(name = "FECHA_INSERCION_CARRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInsercionCarro;

    @Column(name = "URLFTP")
    private String urlFtp;

    @Column(name = "IDSUBFAMILIA")
    private Integer idSubfamilia;

    @Column(name = "TIEMPOENTREGA")
    private Integer tiempoEntrega;

    @Column(name = "CANTIDADDISPONIBLE")
    private Integer cantidadDisponible;

    @Column(name = "NUMEROPARTEPROVEEDOR")
    private String numeroParteProveedor;

    @Column(name = "ID_LOCALIZACION")
    private Integer idLocalizacion;

    @Column(name = "OBSERVACION_RECIBO")
    private String observacionRecibo;

    @Column(name = "LOCALIZACION")
    private String localizacion;

    @Column(name = "TIPO_RECIBO")
    private Integer tipoRecibo;

    @Column(name = "ID_TIPO_RECIBO")
    private Integer idTipoRecibo;

    @Column(name = "ID_UEN_SURTIDORA")
    private Integer idUenSurtidora;

    @Column(name = "IDMONEDA")
    private String idMoneda;

    @Column(name = "ID_REQUISICION")
    private Integer idRequisicion;

    @Column(name = "ID_PARTIDA")
    private Integer idPartida;

    @Column(name = "FUENTE")
    private String fuente;

    @Column(name = "ACTIVO_MOTOR")
    private Integer activoMotor;

    @Column(name = "CONTROLADO")
    private Integer controlado;

    @Column(name = "ID_MOTIVO_SOL")
    private Integer idMotivoSol;

    @Column(name = "PER_ID_USUARIO_SOL")
    private Integer perIdUsuarioSol;

    @Column(name = "VF_ID_IVA")
    private Integer vfIvaId;// vf -> vales fondo

    @Column(name = "VF_ID_REQUISITOR")
    private String vfIdRequisitor;

    @Column(name = "VF_WITHHOLDING")
    private boolean vfWithholding;

    @Column(name = "VF_SEND_DOC")
    private boolean vfSendDoc;

    @Column(name = "VF_ID_FACTURACION")
    private Integer vfIdFacturacion;

    @Column(name = "VF_ID_ENVIARA")
    private Integer vfIdEnviarA;

    @Column(name = "VF_IMPRIMIR_DOC")
    private boolean vfImprimirDoc;
    

    @Column(name = "COMENTARIOS_COMPRADOR")
    private String comentariosComprador;

    @Column(name = "VF_VENDOR_SITE_ID")
    private Integer vfVendorSiteId;

    // <CAT_VAR>
    @Column(name = "ID_TIPO_PRECIO")
    private Integer idTipoPrecio;

    @Column(name = "ID_TEMPLATE")
    private Integer idTemplate;

    @Column(name = "PRECIO_UNITARIO_BASE")
    private Double precioUnitarioBase;
    // <CAT_VAR>
	@Column(name = "RESPONSABLE_MULTI_CC")
    private String responsableMultiCc; 
	
    @Column(name = "PUNCHOUT")
    private Integer punchout;
    
    @Column(name = "ID_RF")
    private Integer idRf;
    
    @Column(name = "RF_NUMBER")
    private Integer rfNumber;
    
    @Column(name = "COMMODITY")
    private String commodity;
    
    @Column(name = "PEDIDO_ESPECIAL")
    private Integer pedidoEspecial;
    
    @Column(name = "PEDIDO_CANT_MAX")
    private Integer pedidoCantMax;

    public NvcVCarroCompra() {
    }

    public NvcVCarroCompra(
            Long idCarroCompra,
            Integer idItem,
            Integer idAlmacen,
            Double cantidad,
            String idUsuarioCreacion,
            Integer tipoItem,
            String descripcion,
            String material,
            String color,
            String medidas,
            String udm,
            String idUdm,
            String uenDes,
            Integer idUen,
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
            Integer idUenSurtidora
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
        this.medidas = medidas;
        this.udm = udm;
        this.idUdm = idUdm;
        this.uenDes = uenDes;
        this.idUen = idUen;
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
        this.tiempoEntrega = tiempoEntrega;
        this.cantidad = cantidad;
        this.numeroParteProveedor = numeroParteProveedor;
        this.idUenSurtidora = idUenSurtidora;
    }

    public NvcVCarroCompra(Long idCarroCompra,
            Integer idItem,
            Integer idAlmacen,
            Double cantidad,
            String idUsuarioCreacion,
            Integer tipoItem,
            String descripcion,
            String material,
            String color,
            String medidas,
            String udm,
            String idUdm,
            String uenDes,
            Integer idUen,
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
            Integer perIdUsuarioSol
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
        this.medidas = medidas;
        this.udm = udm;
        this.idUdm = idUdm;
        this.uenDes = uenDes;
        this.idUen = idUen;
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
        this.tiempoEntrega = tiempoEntrega;
        this.cantidad = cantidad;
        this.numeroParteProveedor = numeroParteProveedor;
        this.idLocalizacion = idLocalizacion;
        this.observacionRecibo = observacionRecibo;
        this.localizacion = localizacion;
        this.idTipoRecibo = idTipoRecibo;
        this.tipoRecibo = tipoRecibo;
        this.idMoneda = idMoneda;
        this.fuente = fuente;
        this.activoMotor = activoMotor;
        this.controlado = controlado;
        this.idMotivoSol = idMotivoSol;
        this.perIdUsuarioSol = perIdUsuarioSol;
    }

    public Long getIdCarroCompra() {
        return idCarroCompra;
    }

    public void setIdCarroCompra(Long idCarroCompra) {
        this.idCarroCompra = idCarroCompra;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getTipoItem() {
        return tipoItem;
    }

    public void setTipoItem(Integer tipoItem) {
        this.tipoItem = tipoItem;
    }

    public String getIdUsuarioCreacion() {
        return idUsuarioCreacion;
    }

    public void setIdUsuarioCreacion(String idUsuarioCreacion) {
        this.idUsuarioCreacion = idUsuarioCreacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public String getIdUdm() {
        return idUdm;
    }

    public void setIdUdm(String idUdm) {
        this.idUdm = idUdm;
    }

    public Integer getIdUen() {
        return idUen;
    }

    public void setIdUen(Integer idUen) {
        this.idUen = idUen;
    }

    public Integer getIdRazonUrgencia() {
        return idRazonUrgencia;
    }

    public void setIdRazonUrgencia(Integer idRazonUrgencia) {
        this.idRazonUrgencia = idRazonUrgencia;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getUrlFtp() {
        return urlFtp;
    }

    public void setUrlFtp(String urlFtp) {
        this.urlFtp = urlFtp;
    }

    public String getRazonUrgenciaDes() {
        return razonUrgenciaDes;
    }

    public void setRazonUrgenciaDes(String razonUrgenciaDes) {
        this.razonUrgenciaDes = razonUrgenciaDes;
    }

    public String getUenDes() {
        return uenDes;
    }

    public void setUenDes(String uenDes) {
        this.uenDes = uenDes;
    }

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
     * @return the idSubfamilia
     */
    public Integer getIdSubfamilia() {
        return idSubfamilia;
    }

    /**
     * @param idSubfamilia the idSubfamilia to set
     */
    public void setIdSubfamilia(Integer idSubfamilia) {
        this.idSubfamilia = idSubfamilia;
    }

    public Integer getTiempoEntrega() {
        return tiempoEntrega;
    }

    public void setTiempoEntrega(Integer tiempoEntrega) {
        this.tiempoEntrega = tiempoEntrega;
    }

    public Integer getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(Integer cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public String getNumeroParteProveedor() {
        return numeroParteProveedor;
    }

    public void setNumeroParteProveedor(String numeroParteProveedor) {
        this.numeroParteProveedor = numeroParteProveedor;
    }

    public Date getFechaNecesidad() {
        return fechaNecesidad;
    }

    public void setFechaNecesidad(Date fechaNecesidad) {
        this.fechaNecesidad = fechaNecesidad;
    }

    @Transient
    private String strFechaNecesidad;

    public String getStrFechaNecesidad() {
        
        return strFechaNecesidad;
    }

    public void setStrFechaNecesidad(String strFechaNecesidad) {
        this.strFechaNecesidad = strFechaNecesidad;
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

    /**
     * @return the idLocalizacion
     */
    public Integer getIdLocalizacion() {
        return idLocalizacion;
    }

    /**
     * @param idLocalizacion the idLocalizacion to set
     */
    public void setIdLocalizacion(Integer idLocalizacion) {
        this.idLocalizacion = idLocalizacion;
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

    public Integer getIdUenSurtidora() {
        return idUenSurtidora;
    }

    public void setIdUenSurtidora(Integer idUenSurtidora) {
        this.idUenSurtidora = idUenSurtidora;
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

    public Integer getIdRequisicion() {
        return idRequisicion;
    }

    public void setIdRequisicion(Integer idRequisicion) {
        this.idRequisicion = idRequisicion;
    }

    public Integer getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(Integer idPartida) {
        this.idPartida = idPartida;
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

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public Integer getActivoMotor() {
        return activoMotor;
    }

    public void setActivoMotor(Integer activoMotor) {
        this.activoMotor = activoMotor;
    }

    public Integer getControlado() {
        return controlado;
    }

    public void setControlado(Integer controlado) {
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

    //*** VALES FONDO **///
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
    

    public String getComentariosComprador() {
        return comentariosComprador;
    }

    public void setComentariosComprador(String comentariosComprador) {
        this.comentariosComprador = comentariosComprador;
    }

    public Integer getVfVendorSiteId() {
        return vfVendorSiteId;
    }

    public void setVfVendorSiteId(Integer vfVendorSiteId) {
        this.vfVendorSiteId = vfVendorSiteId;
    }

    @Column(name = "id_fad")
    private Integer idFad;

    public Integer getIdFad() {
        return idFad;
    }

    public void setIdFad(Integer idFad) {
        this.idFad = idFad;
    }

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

    public void setPrecioUnitarioBase(Integer punchout) {
        this.punchout = punchout;
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

    public String getFullUrlFtp(){
        if(this.urlFtp == null || this.urlFtp.length() == 0){
            return Constants.URL_IMAGES_FTP_NOT_FOUND;
        }

        return Constants.URL_IMAGES_FTP + this.urlFtp;        
    }
    
    @JsonIgnore
    public DcEstatus getEstatus() {
        return this.estatus;
    }
    
    public void setEstatus(DcEstatus estatus) {
        this.estatus = estatus;
    }
    
    /*@JsonIgnore
    public NvcTblCatalogoItem getItem() {
        return this.item;
    }*/
    
    @JsonIgnore
    public Usuario getUsuarioCreacion() {
        return this.usuarioCreacion;
    }
    
    @JsonIgnore
    public NvcTblAlmacenH getAlmacen() {
        return this.almacen;
    }
    
    public String getNombreAlmacen() {
        if (this.getAlmacen() != null && !this.getAlmacen().getName().isEmpty()) {
            return this.getAlmacen().getName();
        }
        return "";
    }
}