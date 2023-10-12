package com.metalsa.requisicion.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.metalsa.aprobacion.model.Usuario;
import com.metalsa.catalogo.model.NvcTblCatalogoItem;
import com.metalsa.catalogo.model.NvcVItemsAll;
import com.metalsa.core.model.OaUens;
import com.metalsa.requisicion.pojo.NvcTblCarroCompraPojo;
import com.metalsa.requisicion.pojo.NvcTblDocumentoPojo;
import com.metalsa.utils.entities.NvcTblCarroCompraDetalle;

/**
 *
 * @author APOMS7376
 */
@Entity
@Table(name = "NVC_TBL_CARRO_COMPRA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NvcTblCarroCompra.findAll", query = "SELECT n FROM NvcTblCarroCompra n ")
    ,
    @NamedQuery(name = "NvcTblCarroCompra.findByIdCarroCom", query = "SELECT n FROM com.metalsa.cart.model.NvcTblCarroCompra n WHERE n.idCarroCompra = :idCarroCompra")
})

@NamedNativeQueries({
    @NamedNativeQuery(name = "NvcTblCarroCompra.findByIdFad", query = "select * from nvc_tbl_carro_compra c\n"
            + "inner join detalle_de_requisicion d on d.id_requisicion =c.id_requisicion and d.id_partida = c.id_partida  \n"
            + "where id_fad = ?1 and id_estatus !=3",resultClass = NvcTblCarroCompra.class)
})
public class NvcTblCarroCompra implements Serializable {

    @Column(name = "COTIZA_EN_PAQUETE")
    private Integer cotizaEnPaquete;

    @JoinColumn(
            name = "ID_UEN",
            referencedColumnName = "ORGANIZATION_ID",
            insertable = false,
            updatable = false
    )
    @ManyToOne(optional = false)
    private OaUens uen;

    @Column(name = "ID_ITEM_ALMACEN")
    private Integer idItAlmacen;

    public Integer getIdItAlmacen() {
        return idItAlmacen;
    }

    public void setIdItAlmacen(Integer idItAlmacen) {
        this.idItAlmacen = idItAlmacen;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCarroCompra", fetch = FetchType.LAZY)
    private List<NvcTblCarroCompraDetalle> nvcTblCarroCompraDetalleCollection;

    @JoinColumn(name = "ID_ITEM", referencedColumnName = "ID_ITEM")
    @ManyToOne
    private NvcTblCatalogoItem item;
    
    @JoinColumns({
        @JoinColumn(
                name = "ID_ITEM_ALMACEN",
                referencedColumnName = "IDITEM",
                nullable = false, insertable = false, updatable = false),
        @JoinColumn(
                name = "ID_UEN",
                referencedColumnName = "UEN",
                nullable = false, insertable = false, updatable = false),
        @JoinColumn(
                name = "ID_ALMACEN",
                referencedColumnName = "IDALMACEN",
                nullable = false, insertable = false, updatable = false)})
    @ManyToOne
    private NvcVItemsAll itemAlmacen;

    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.MERGE}, mappedBy = "idCarroCompra", fetch = FetchType.LAZY)
    private List<NvcTblDocumento> nvcTblDocumentoCollection;
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "SEQ_NVC_TBL_CARRO_COMPRA_GEN", sequenceName = "SEQ_NVC_TBL_CARRO_COMPRA", allocationSize = 1)
    @GeneratedValue(generator = "SEQ_NVC_TBL_CARRO_COMPRA_GEN")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CARRO_COMPRA")
    private Integer idCarroCompra;
    
    @Column(name = "ID_ITEM", insertable = false, updatable = false)
    private Integer idItem;
    
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "COLOR")
    private String color;
    @Column(name = "MEDIDAS")
    private String medidas;
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
    @Column(name = "MARCA")
    private String marca;
    @Column(name = "ID_SUBFAMILIA")
    private Integer idSubfamilia;
    @Column(name = "CANTIDAD")
    private Double cantidad;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_NECESIDAD")
    private Date fechaNecesidad;
    
    @JoinColumn(
            name = "ID_RAZON_URGENCIA",
            referencedColumnName = "ID_RAZON_URGENCIA"
    )
    @ManyToOne
    private NvcTblRazonUrgencia idRazonUrgencia;
    
    @Column(name = "COMENTARIOS_COMPRADOR")
    private String comentariosComprador;
    
    @JoinColumn(name = "ID_UDM", referencedColumnName = "ID_UDM")
    @ManyToOne
    private NvcTblUdm idUdm;
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FECHA_ACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @JoinColumn(name = "USUARIO_CREACION", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario usuarioCreacion;
    @Column(name = "USUARIO_ACTUALIZACION")
    private String usuarioActualizacion;
    @Column(name = "SELECCION")
    private Integer seleccion;
    @Column(name = "ID_ALMACEN")
    private Integer idAlmacen;
    @Column(name = "ID_REQUISICION")
    private Integer idRequisicion;
    @Column(name = "ID_PARTIDA")
    private Integer idPartida;

    @JoinColumn(name = "COMPRADOR", referencedColumnName = "ID_USUARIO")
    private Usuario comprador;

    @Column(name = "RAZON_SPOT")
    private String razonSpot;

    @Column(name = "TOTAL_SUGERENCIAS")
    private Integer totalSugerencias;

    @Column(name = "ID_LOCALIZACION")
    private Integer idLocalizacion;
    @Column(name = "TIPO_RECIBO")
    private Integer tipoRecibo;
    @Column(name = "OBSERVACION_RECIBO")
    private String observacionRecibo;

    @Column(name = "ID_UEN_SURTIDORA")
    private Integer idUenSurtidora;
    
    @Column(name = "ID_UEN")
    private Integer idUen;

    @Column(name = "UDM")
    private String udm;

    @Column(name = "URL_IMG")
    private String urlImg;
    //<R17548>
    @Column(name = "ID_MOTIVO_SOL")
    private Integer idMotivoSol;

    @Column(name = "PER_ID_USUARIO_SOL")
    private Integer perIdUsuarioSol;
    //</R17548>
    @Transient
    private NvcTblCarroCompraPojo carroCompraPojo;

    //****** MDA VALES FONDO*****************//
    @Column(name = "FUENTE")
    private String fuente; //(vf)

    @Column(name = "VF_ID_PROVEEDOR")
    private Integer vfIdProveedor;//vf -> vales fondo

    @Column(name = "VF_ID_IVA")
    private Integer vfIvaId;// vf -> vales fondo

    @Column(name = "VF_ID_REQUISITOR")
    private String vfIdRequisitor;

    @Column(name = "VF_WITHHOLDING")
    private Boolean vfWithholding;

    @Column(name = "VF_SEND_DOC")
    private Boolean vfSendDoc;

    @Column(name = "VF_ID_FACTURACION")
    private Integer vfIdFacturacion;

    @Column(name = "VF_ID_ENVIARA")
    private Integer vfIdEnviarA;

    @Column(name = "VF_IMPRIMIR_DOC")
    private Boolean vfImprimirDoc;

    @Column(name = "VF_ID_MONEDA")
    private String vfIdMoneda;

    @Column(name = "VF_PRECIO_UNITARIO")
    private Double vfPrecioUnitario;

    @Column(name = "VF_CONTACTOS_PROVEEDOR")
    private String vfContactosProveedor;

    @Column(name = "VF_CONTACTOS_ADICIONALES")
    private String vfContactosAdicionales;

    @Column(name = "VF_VENDOR_SITE_ID")
    private Integer vfVendorSiteId;
    //****** MDA VALES FONDO*****************//

    /*<FAD>*/
    @Column(name = "ID_FAD")
    private Integer idFad;

    @Column(name = "ID_RF")
    private Integer idRf;

    @Column(name = "RF_NUMBER")
    private Integer rfNumber;

    @Column(name = "COMMODITY")
    private String commodity;

    @Column(name = "ID_PART_NUMBER")
    private Integer idPartNumber;

    @Column(name = "PEDIDO_ESPECIAL")
    private Integer pedidoEspecial;

    @Column(name = "PEDIDO_CANT_MAX")
    private Integer pedidoCantMax;
    @Column(name = "PEDIDO_BLANKET_PO")
    private Long blanketPO;
    @Column(name = "PEDIDO_ORGANIZACION")
    private String pedidoOrganizacion;

    public Integer getIdFad() {
        return idFad;
    }

    public void setIdFad(Integer idFad) {
        this.idFad = idFad;
    }
    /*</FAD>*/

    // <CAT_VAR>
    @Column(name = "ID_TIPO_PRECIO")
    private Integer idTipoPrecio;

    @Column(name = "ID_TEMPLATE")
    private Integer idTemplate;

    @Column(name = "precio_unitario_base")
    private Double precioUnitarioBase;      // se reutiliza la variable vales fondo y cv
    // <CAT_VAR>

    @Column(name = "RESPONSABLE_MULTI_CC")
    private String responsableMultiCc;

    @Column(name = "PUNCHOUT")
    private Integer punchout;

    // columna item spot maximo --
    @Column(name = "ITEM_SPOT_MAXIMO")
    private String itemSpotMaximo;

    public NvcTblCarroCompra(Integer idCarroCompra) {
        this.idCarroCompra = idCarroCompra;
    }

    public Integer getIdCarroCompra() {
        return idCarroCompra;
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

    public Date getFechaNecesidad() {
        return fechaNecesidad;
    }

    public void setFechaNecesidad(Date fechaNecesidad) {
        this.fechaNecesidad = fechaNecesidad;
    }

    public NvcTblRazonUrgencia getIdRazonUrgencia() {
        return idRazonUrgencia;
    }

    public void setIdRazonUrgencia(NvcTblRazonUrgencia idRazonUrgencia) {
        this.idRazonUrgencia = idRazonUrgencia;
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

    public Usuario getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(Usuario usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public String getUsuarioActualizacion() {
        return usuarioActualizacion;
    }

    public void setUsuarioActualizacion(String usuarioActualizacion) {
        this.usuarioActualizacion = usuarioActualizacion;
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

    public NvcTblUdm getIdUdm() {
        return idUdm;
    }

    public void setIdUdm(NvcTblUdm idUdm) {
        this.idUdm = idUdm;
    }

    @XmlTransient
    public List<NvcTblDocumento> getNvcTblDocumentoCollection() {
        return nvcTblDocumentoCollection;
    }

    public void setNvcTblDocumentoCollection(List<NvcTblDocumento> nvcTblDocumentoCollection) {
        this.nvcTblDocumentoCollection = nvcTblDocumentoCollection;
    }

    public OaUens getUen() {
        return this.uen;
    }

    @XmlTransient
    public List<NvcTblCarroCompraDetalle> getNvcTblCarroCompraDetalleCollection() {
        return nvcTblCarroCompraDetalleCollection;
    }

    public void setNvcTblCarroCompraDetalleCollection(List<NvcTblCarroCompraDetalle> nvcTblCarroCompraDetalleCollection) {
        this.nvcTblCarroCompraDetalleCollection = nvcTblCarroCompraDetalleCollection;
    }
    
    public String getPedidoOrganizacion() {
        return pedidoOrganizacion;
    }

    public void setPedidoOrganizacion(String pedidoOrganizacion) {
        this.pedidoOrganizacion = pedidoOrganizacion;
    }

    public NvcTblCatalogoItem getItem() {
        return item;
    }

    public void setItem(NvcTblCatalogoItem item) {
        this.item = item;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public void setComprador(Usuario comprador) {
        this.comprador = comprador;
    }

    public NvcTblCarroCompra() {
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    //<R17548>
    public NvcTblCarroCompraPojo getCarroCompraPojo() {
        if (carroCompraPojo == null) {
            carroCompraPojo = new NvcTblCarroCompraPojo(
                    this.idCarroCompra,
                    this.descripcion,
                    this.color,
                    this.medidas,
                    this.modelo,
                    this.numeroParteModelo,
                    this.itemGenerico,
                    this.nombreGenerico,
                    this.marca,
                    this.idUdm == null ? null : this.idUdm.getIdUnidadDeMedida(),
                    this.material,
                    this.idSubfamilia,
                    this.cantidad,
                    this.fechaNecesidad,
                    null,
                    this.comentariosComprador,
                    this.comprador == null ? null : this.comprador.getId(),
                    this.idUen == null ? null : this.uen.getOrganizationName(),
                    this.idUen == null ? null : this.uen.getOrganizationId(),
                    this.idAlmacen,
                    this.razonSpot,
                    this.totalSugerencias,
                    this.idUenSurtidora,
                    this.idMotivoSol,
                    this.perIdUsuarioSol
            );
            carroCompraPojo.setIdPartida(this.idPartida);
            carroCompraPojo.setIdRequisicion(this.idRequisicion);
            carroCompraPojo.setPedidoEspecial(this.pedidoEspecial);
            carroCompraPojo.setPedidoCantMax(this.pedidoCantMax);
            carroCompraPojo.setPedidoOrganizacion(this.pedidoOrganizacion);
            carroCompraPojo.setBlanketPO(this.blanketPO);
            DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
            if (fechaNecesidad != null) {
                carroCompraPojo.setFechaNecesidad(df.format(getFechaNecesidad()));
            }
            carroCompraPojo.setIdSubfamilia(getIdSubfamilia());
            if (getIdRazonUrgencia() != null) {
                carroCompraPojo.setRazonUrgenciaDes(getIdRazonUrgencia().getDescripcionUrgencia());
                carroCompraPojo.setIdRazonUrgencia(getIdRazonUrgencia().getIdRazonUrgencia());
            }

            List<NvcTblDocumentoPojo> listDocPojo = new ArrayList<>();

            if (getNvcTblDocumentoCollection() != null) {
                for (NvcTblDocumento doc : getNvcTblDocumentoCollection()) {
                    NvcTblDocumentoPojo p = new NvcTblDocumentoPojo(
                            doc.getIdDocumento(),
                            doc.getNombreArchivo(),
                            doc.getNombreArchivoFtp(),
                            doc.getExtensionArchivo(),
                            doc.getTamanioArchivo(),
                            doc.getUbicacionFtp()
                    );
                    listDocPojo.add(p);
                }
            }
//            carroCompraPojo.setDocumentoOriginal(listDocPojo);
            carroCompraPojo.setDocumentos(listDocPojo);
        }
        /**
         * * MDA VALES FONDO
         */
        carroCompraPojo.setFuente(fuente);
        carroCompraPojo.setIdProveedor(vfIdProveedor);
        carroCompraPojo.setVfIvaId(vfIvaId);
        carroCompraPojo.setVfIdRequisitor(vfIdRequisitor);
        carroCompraPojo.setVfWithholding(vfWithholding);
        carroCompraPojo.setVfSendDoc(vfSendDoc);
        carroCompraPojo.setVfIdFacturacion(vfIdFacturacion);
        carroCompraPojo.setVfIdEnviarA(vfIdEnviarA);
        carroCompraPojo.setVfImprimirDoc(vfImprimirDoc);
        carroCompraPojo.setIdMoneda(vfIdMoneda);
        carroCompraPojo.setPrecioUnitario(vfPrecioUnitario);
        carroCompraPojo.setVfVendorSiteId(vfVendorSiteId);
        if (vfContactosProveedor != null) {
            carroCompraPojo.setVfContactosProveedor(Arrays.asList(vfContactosProveedor.split(";")));
        }

        carroCompraPojo.setVfContactosAdicionales(vfContactosAdicionales);

        return carroCompraPojo;
    }
    //</R17548>

    public Integer getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(Integer seleccion) {
        this.seleccion = seleccion;
    }

    public Integer getCotizaEnPaquete() {
        return cotizaEnPaquete;
    }

    public void setCotizaEnPaquete(Integer cotizaEnPaquete) {
        this.cotizaEnPaquete = cotizaEnPaquete;
    }

    public Integer getIdAlmacen() {
        return idAlmacen;
    }

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

    public Integer getIdUenSurtidora() {
        return idUenSurtidora;
    }

    public void setIdUenSurtidora(Integer idUenSurtidora) {
        this.idUenSurtidora = idUenSurtidora;
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

    public String getUdm() {
        return udm;
    }

    public void setUdm(String udm) {
        this.udm = udm;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    @PreUpdate
    public void preUpdate() {
//        System.out.println("com.metalsa.spx.entities.NvcTblCarroCompra.preUpdate() " + this.getNvcTblDocumentoCollection());
    }

    @PreRemove
    public void preRemove() {
//        System.out.println("com.metalsa.spx.entities.NvcTblCarroCompra.preRemove() " + this.getNvcTblDocumentoCollection());
    }

    //<R17548>
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
    public Integer getVfIdProveedor() {
        return vfIdProveedor;
    }

    public void setVfIdProveedor(Integer vfIdProveedor) {
        this.vfIdProveedor = vfIdProveedor;
    }

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

    public Boolean isVfWithholding() {
        return vfWithholding;
    }

    public void setVfWithholding(Boolean vfWithholding) {
        this.vfWithholding = vfWithholding;
    }

    public Boolean isVfSendDoc() {
        return vfSendDoc;
    }

    public void setVfSendDoc(Boolean vfSendDoc) {
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

    public Boolean isVfImprimirDoc() {
        return vfImprimirDoc;
    }

    public void setVfImprimirDoc(Boolean vfImprimirDoc) {
        this.vfImprimirDoc = vfImprimirDoc;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public String getVfIdMoneda() {
        return vfIdMoneda;
    }

    public void setVfIdMoneda(String vfIdMoneda) {
        this.vfIdMoneda = vfIdMoneda;
    }

    public Double getVfPrecioUnitario() {
        return vfPrecioUnitario;
    }

    public void setVfPrecioUnitario(Double vfPrecioUnitario) {
        this.vfPrecioUnitario = vfPrecioUnitario;
    }

    public String getVfContactosProveedor() {
        return vfContactosProveedor;
    }

    public void setVfContactosProveedor(String vfContactosProveedor) {
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

    // ************************ MDA Vales fondo*********************************** //
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
        return punchout != null ? punchout : 0;
    }

    public void setPunchout(Integer punchout) {
        this.punchout = punchout;
    }

    public String getItemSpotMaximo() {
        return itemSpotMaximo;
    }

    public void setItemSpotMaximo(String itemSpotMaximo) {
        this.itemSpotMaximo = itemSpotMaximo;
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
    
    public NvcVItemsAll getItemAlmacen() {
        return itemAlmacen;
    }

    public void setIdItemAlmacen(NvcVItemsAll idItemAlmacen) {
        this.itemAlmacen = idItemAlmacen;
    }
    
    public boolean hasObservacionRecibo() {
        return this.observacionRecibo != null && !this.observacionRecibo.isEmpty();
    }
    
    public String trimmedObservacionRecibo() {
        int length = observacionRecibo.length();
        if (length > 150) {
            return observacionRecibo.substring(0, 150);
        } else {
            return observacionRecibo;
        }
    }
    
    public Integer getIdItem() {
        return this.idItem;
    }
    
    public void setIdItem(Integer id) {
        this.idItem = id;
    }

    public String getComentariosComprador() {
        return this.comentariosComprador;
    }

    public void setComentariosComprador(String comentarios) {
        this.comentariosComprador = comentarios;
    }
}
