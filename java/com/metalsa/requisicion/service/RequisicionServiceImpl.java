package com.metalsa.requisicion.service;

import com.metalsa.aprobacion.model.Usuario;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metalsa.core.repository.ItemsRepository;
import com.metalsa.requisicion.repository.CarroCompraRepository;
import com.metalsa.requisicion.model.Fuentes;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityTransaction;

// Model
import com.metalsa.cart.model.NvcTblCarroCompra;
import com.metalsa.cart.service.CartService;
import com.metalsa.catalogo.model.NvcVItemsAll;
import com.metalsa.catalogo.model.ItemEstatus;
import com.metalsa.core.model.NvcTblProvSitesH;
import com.metalsa.catalogo.model.NvcTblAlmacenH;
import com.metalsa.requisicion.model.DetalleDeRequisicion;
import com.metalsa.core.model.NvcTblOaTareasH;
import com.metalsa.catalogo.model.IcomAccCategories;
import com.metalsa.catalogo.model.NvcTblOaLocalizacionesH;
import com.metalsa.requisicion.model.TipoCargo;
import com.metalsa.requisicion.model.PartidaDocumentsMaximo;
import com.metalsa.requisicion.model.AprobacionController;
import com.metalsa.requisicion.model.ComentarioRequisicionPK;
import com.metalsa.requisicion.model.ComentarioRequisicion;
import com.metalsa.requisicion.model.DistribucionRequisicion;
import com.metalsa.core.model.DcEstatus;

// POJO
import com.metalsa.requisicion.pojo.RequisicionUens;
import com.metalsa.requisicion.pojo.RequisicionPojo;
import com.metalsa.utils.entities.NvcVCarroCompra;
import org.springframework.transaction.annotation.Transactional;

import com.metalsa.core.repository.ParameterRepository;
import com.metalsa.cart.repository.CartRepository;
import com.metalsa.catalogo.model.NvcTblCatalogoLocalizacion;
import com.metalsa.catalogo.model.NvcTblCatalogoUen;
import com.metalsa.catalogo.model.NvcTblCatalogoUenSite;
import com.metalsa.core.model.NvcTblProyectosH;
import com.metalsa.core.repository.DcEstatusRepository;
import com.metalsa.requisicion.repository.RequisicionRepository;
import com.metalsa.core.repository.BitacoreRepository;
import com.metalsa.core.repository.LineaControladaRepository;
import com.metalsa.requisicion.pojo.DetalleDeRequisicionPojo;
import com.metalsa.requisicion.model.FamiliasPorAprobador;
import com.metalsa.requisicion.model.ProyectosPorAprobadorUen;
import com.metalsa.requisicion.pojo.NvcTblCarroCompraDetallePojo;
import com.metalsa.requisicion.pojo.NvcTblCarroCompraPojo;
import com.metalsa.requisicion.pojo.NvcTblFadPojo;
import com.metalsa.requisicion.pojo.NvcTblFadRequest;
import com.metalsa.requisicion.pojo.NvcTblParameterUenPojo;
import com.metalsa.requisicion.pojo.NvcTblParameterValuePojo;
import com.metalsa.requisicion.pojo.RequisicionPreview;
import com.metalsa.requisicion.pojo.RequisicionRequest;
import com.metalsa.requisicion.pojo.RequisicionResponse;
import java.math.BigInteger;
import java.util.Date;
import java.util.Optional;
import com.metalsa.requisicion.repository.UserRepository;
import com.metalsa.requisicion.repository.CatalogoRepository;
import com.metalsa.requisicion.utils.ConstantsUtils;
import com.metalsa.utils.entities.NvcVmOaProyectoTareas;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import lombok.extern.log4j.Log4j;
import com.metalsa.catalogo.model.NvcTblCatalogoItem;
import com.metalsa.core.model.NvcTblOaCcH;
import com.metalsa.core.model.OaUens;
import com.metalsa.requisicion.responses.NvcTblOaProveedoresHResponse;
import com.metalsa.requisicion.pojo.NvcTblOaProveedoresHPojo;
import com.metalsa.core.model.NvcTblOaProveedoresH;
import com.metalsa.core.model.NvcTblUenUsuAprobacion;
import com.metalsa.requisicion.model.AlmacenComprador;
import com.metalsa.requisicion.model.CcPorComprador;
import com.metalsa.requisicion.model.DcCompradorFamilia;
import com.metalsa.requisicion.model.NvcTblCatalogoItemPunchout;
import com.metalsa.requisicion.model.NvcTblProcesosAprobacion;
import com.metalsa.requisicion.model.NvcVmOaExistencias;
import com.metalsa.requisicion.model.ParametrosPorUenCia;
import com.metalsa.requisicion.pojo.TipoRequisicion;
import com.metalsa.requisicion.repository.ProveedorRepository;
import com.metalsa.requisicion.model.Requisicion;

/**
 *
 * @author Gamaliel Espinoza M.
 */
@Service
@Log4j
public class RequisicionServiceImpl implements RequisicionService {
    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    CartService cartService;
    
    @Autowired
    private CartRepository cartRepo;
    
    @Autowired
    private CarroCompraRepository carros;
    
    @Autowired
    private ParameterRepository params;
    
    @Autowired
    private LineaControladaRepository lineas;
    
    @Autowired
    private DcEstatusRepository estatuses;
    
    @Autowired
    private RequisicionRepository repoReq;
    
    @Autowired
    private BitacoreRepository bitacore;
    
    @Autowired
    private ItemsRepository itemsRepo;
    
    @Autowired
    private UserRepository usersRepo;
    
    @Autowired
    private CatalogoRepository catalogRepo;
    
    @Autowired
    private ProveedorRepository proveedorRepo;
    
    @Override
    public Map<Integer, Map<Fuentes, List<NvcVCarroCompra>>> groupCarts(List<NvcVCarroCompra> items) {
        return null;
    }
    
    private String getTraduccion(String clave, String idioma) {
        System.out.println("getTraduccion clave: " + clave);
        System.out.println("getTraduccion idioma: " + idioma);
        return em
                .createNativeQuery("select get_traduccion(?1,?2) from dual")
                .setParameter(1, clave)
                .setParameter(2, idioma)
                .getSingleResult()
                .toString();
    }
    
    private String getTextElement(String clave, String idioma, Object value) {
        if (value == null || value.toString().trim().isEmpty()) {
            return "";
        }
        System.out.println("getTextElement value: " + value.toString());
        return " " + getTraduccion(clave, idioma) + ": " + value.toString();
    }
    
    private String getDescripcionRequisicionProveedor(NvcTblCarroCompraPojo carroPojo, String idioma, int max) {

        StringBuilder sbdesc = new StringBuilder(carroPojo.getDescripcion());
        sbdesc.append(this.getTextElement("LBL_NUMPARTEPROV", idioma, carroPojo.getNumeroParteProveedor()));
        sbdesc.append(this.getTextElement("LBL_MATERIAL", idioma, carroPojo.getMaterial()));
        sbdesc.append(this.getTextElement("LBL_COLOR", idioma, carroPojo.getColor()));
        sbdesc.append(this.getTextElement("LBL_MEDIDAS", idioma, carroPojo.getMedidas()));
        sbdesc.append(this.getTextElement("LBL_ITEM_GENERICO", idioma, carroPojo.getItemGenerico()));
        sbdesc.append(this.getTextElement("LBL_NUM_PARTE_MODELO", idioma, carroPojo.getNumeroParteModelo()));
        sbdesc.append(this.getTextElement("LBL_NOMBRE_GENERICO", idioma, carroPojo.getNombreGenerico()));
        sbdesc.append(this.getTextElement("LBL_MARCA", idioma, carroPojo.getMarca()));

        return sbdesc.toString().length() > max
                ? sbdesc.substring(0, max)
                : sbdesc.toString();

    }
    
    private java.util.Map<String, List<NvcTblCarroCompraPojo>> ordenaXUen(List<NvcTblCarroCompraPojo> entityList) {
        java.util.Map<String, List<NvcTblCarroCompraPojo>> uenMap = new java.util.HashMap<>();
        // OBTENEMOS TODOS LOS ITEMS DE CADA UNA DE LAS UENS
        for (NvcTblCarroCompraPojo carroCompraPojo : entityList) {
            if (!uenMap.containsKey(carroCompraPojo.getNombreUen())) {
                uenMap.put(carroCompraPojo.getNombreUen(), new ArrayList<>());
            }
            uenMap.get(carroCompraPojo.getNombreUen()).add(carroCompraPojo);
        }
        return uenMap;
    }
    
    private java.util.Map<ConstantsUtils.FUENTES, List<NvcTblCarroCompraPojo>> obtenRequisicionesPorTipo(List<NvcTblCarroCompraPojo> carroCompraxUenList) {
        log.debug("*** ordenaXTipo *** ");
        java.util.Map<ConstantsUtils.FUENTES, List<NvcTblCarroCompraPojo>> mapTipoxUen = new HashMap<>();

        // TODOS LOS CarroCompraPojo DE CADA UEN
        for (NvcTblCarroCompraPojo carroCompraPojo : carroCompraxUenList) {

            log.debug("*** ordenaXTipo *** IdCarroCompra > " + carroCompraPojo.getIdCarroCompra());

            NvcTblCarroCompra carroCompra = cartRepo.findCartById(
                    carroCompraPojo.getIdCarroCompra()
            );
            NvcTblCatalogoItem itemProveedor = carroCompra.getItem();
            NvcVItemsAll itemAlmacen = null;
            
            if (carroCompra.getIdUenSurtidora() == null) {
                itemAlmacen = carroCompra.getItemAlmacen();
            } else {
                itemAlmacen = itemsRepo.findViewByPk(
                        carroCompra.getIdItAlmacen(),
                        carroCompra.getIdUenSurtidora(),
                        carroCompra.getIdAlmacen()
                );
            }

            if (carroCompra.getPunchout() == 1) {
                carroCompraPojo.setPunchout(carroCompra.getPunchout());
                if (!mapTipoxUen.containsKey(ConstantsUtils.FUENTES.PUNCHOUT)) {
                    mapTipoxUen.put(ConstantsUtils.FUENTES.PUNCHOUT, new ArrayList<>());
                }
                mapTipoxUen.get(ConstantsUtils.FUENTES.PUNCHOUT).add(carroCompraPojo);
            }

            if (carroCompra.getPedidoEspecial() != null && carroCompra.getPedidoEspecial() == 1) {
                if (!mapTipoxUen.containsKey(ConstantsUtils.FUENTES.PEDIDO_ESPECIAL)) {
                    mapTipoxUen.put(ConstantsUtils.FUENTES.PEDIDO_ESPECIAL, new ArrayList<>());
                }
                log.debug("********carroCompra.getIdAlmacen():" + carroCompra.getIdAlmacen());
                carroCompraPojo.setIdAlmacen(carroCompra.getIdAlmacen()); //compxorg
                mapTipoxUen.get(ConstantsUtils.FUENTES.PEDIDO_ESPECIAL).add(carroCompraPojo);
            } else if (itemProveedor == null && itemAlmacen == null && carroCompraPojo.getFuente() == null || (carroCompraPojo.getFuente() != null && carroCompraPojo.getFuente().equals(ConstantsUtils.FUENTES.SPOT.getFuente()))) {
                if (!mapTipoxUen.containsKey(ConstantsUtils.FUENTES.SPOT)) {
                    mapTipoxUen.put(ConstantsUtils.FUENTES.SPOT, new ArrayList<>());
                }
                mapTipoxUen.get(ConstantsUtils.FUENTES.SPOT).add(carroCompraPojo);
            }
            // OBTENEMOS TODOS LOS QUE SEAN DE VALES FONDO
            if (itemProveedor == null && itemAlmacen == null && (carroCompraPojo.getFuente() != null && carroCompraPojo.getFuente().equals(ConstantsUtils.FUENTES.VALES_FONDO.getFuente()))) {
                if (!mapTipoxUen.containsKey(ConstantsUtils.FUENTES.VALES_FONDO)) {
                    mapTipoxUen.put(ConstantsUtils.FUENTES.VALES_FONDO, new ArrayList<>());
                }
                mapTipoxUen.get(ConstantsUtils.FUENTES.VALES_FONDO).add(carroCompraPojo);
            }/* MDA VALES FONDO  END*/

            // OBTENEMOS TODOS LOS QUE SEAN DE INTERUEN
            if (carroCompraPojo.getFuente() != null && carroCompraPojo.getFuente().equals(ConstantsUtils.FUENTES.INTERUEN.getFuente())) {
                if (!mapTipoxUen.containsKey(ConstantsUtils.FUENTES.INTERUEN)) {
                    mapTipoxUen.put(ConstantsUtils.FUENTES.INTERUEN, new ArrayList<>());
                }
                mapTipoxUen.get(ConstantsUtils.FUENTES.INTERUEN).add(carroCompraPojo);
            }/* MDA INTERUEN  END*/

            // OBTENEMOS TODOS LOS QUE SON DE CATALOGO_EXTERNO (Proveedor)
            if (itemProveedor != null && itemAlmacen == null && carroCompra.getPunchout() != 1) {
                if (!mapTipoxUen.containsKey(ConstantsUtils.FUENTES.PROVEEDOR)) {
                    mapTipoxUen.put(ConstantsUtils.FUENTES.PROVEEDOR, new ArrayList<>());
                }
                mapTipoxUen.get(ConstantsUtils.FUENTES.PROVEEDOR).add(carroCompraPojo);
            }

            // OBTENEMOS TODOS LOS QUE SON DE CATALOGO_ITERNO (Almacen)
            if (itemAlmacen != null && carroCompra.getIdUen() != null && itemProveedor == null) {
                if (!mapTipoxUen.containsKey(ConstantsUtils.FUENTES.ALMACEN)) {
                    mapTipoxUen.put(ConstantsUtils.FUENTES.ALMACEN, new ArrayList<>());
                }
                mapTipoxUen.get(ConstantsUtils.FUENTES.ALMACEN).add(carroCompraPojo);
            }

        }
        return mapTipoxUen;
    }
    
    public NvcTblOaProveedoresHResponse getProveedorById(Integer idProveedor) {
        NvcTblOaProveedoresHResponse response = new NvcTblOaProveedoresHResponse();
        NvcTblOaProveedoresH proveedor = proveedorRepo.findOneById(idProveedor);
        NvcTblOaProveedoresHPojo pojo = new NvcTblOaProveedoresHPojo();
        pojo.setValFromEntity(proveedor);
        response.setEntity(pojo);
        return response;
    }
    
    private String getDescripcionRequisicion(NvcTblCarroCompraPojo carroPojo, String idioma, int max) {

        StringBuilder sbdesc = new StringBuilder(carroPojo.getDescripcion());
        sbdesc.append(this.getTextElement("LBL_MATERIAL", idioma, carroPojo.getMaterial()));
        sbdesc.append(this.getTextElement("LBL_COLOR", idioma, carroPojo.getColor()));
        sbdesc.append(this.getTextElement("LBL_ITEM_GENERICO", idioma, carroPojo.getItemGenerico()));
        sbdesc.append(this.getTextElement("LBL_MEDIDAS", idioma, carroPojo.getMedidas()));
        sbdesc.append(this.getTextElement("LBL_NUM_PARTE_MODELO", idioma, carroPojo.getNumeroParteModelo()));
        sbdesc.append(this.getTextElement("LBL_NOMBRE_GENERICO", idioma, carroPojo.getNombreGenerico()));
        sbdesc.append(this.getTextElement("LBL_MARCA", idioma, carroPojo.getMarca()));

        return sbdesc.toString().length() > max
                ? sbdesc.substring(0, max)
                : sbdesc.toString();

    }
    
    public String getNombreSiteById(Integer idSiteProveedor) {
        String siteCode=(String)em.createNativeQuery("select vendor_site_code from ap_supplier_sites_all where VENDOR_SITE_ID =?1").setParameter(1, idSiteProveedor).getSingleResult();
        return siteCode;
    }
    /* MDA VALES FONDO */
    private List<RequisicionPojo> generaPreviewValesFondo(
            List<NvcTblCarroCompraPojo> carroCompraPojoList,
            RequisicionRequest request
    ) {
        log.debug("*** generaPreviewRequisiciones *** carroCompraPojoList > ");
        java.util.Map<String, RequisicionPojo> mapRequis = new java.util.HashMap<>();
        String key;
        NvcTblOaProveedoresHResponse responseProv;
        try {

            int numPartida;
            Usuario requisitor;
            Usuario requisitorDefault = em.createNamedQuery("Usuario.findByIdUsuario", Usuario.class)
                    .setParameter("1", request.getUser().getIdUsuario())
                    .getSingleResult();

            Integer idProyecto;
            Integer idCentroCosto;
            Integer idUen;
            Integer idSubfamilia;
            DetalleDeRequisicionPojo detalleDeRequisicionPojo = null;
            /* <FAD> */
            Integer idFad;
            /* </FAD> */

            for (NvcTblCarroCompraPojo carroCompraPojo : carroCompraPojoList) {
                if (carroCompraPojo.getVfIdRequisitor() == null) {
                    requisitor = requisitorDefault;
                } else {
                    requisitor = em.createNamedQuery("Usuario.findByIdUsuario", Usuario.class)
                            .setParameter("1", carroCompraPojo.getVfIdRequisitor())
                            .getSingleResult();

                }
                RequisicionPojo requisicionPojo = new RequisicionPojo();
                idUen = carroCompraPojo.getIdUen();

                /*<FAD>*/
                idFad = carroCompraPojo.getIdFad() == null ? 0 : carroCompraPojo.getIdFad();
                /*</FAD>*/

                OaUens organization = em.createNamedQuery("OaUens.findByOrganizationId", OaUens.class)
                        .setParameter("organizationId", idUen)
                        .getSingleResult();

                requisicionPojo.setFechaRequisicion(new Date());
                requisicionPojo.setRequisitor(requisitor.getId());
                requisicionPojo.setNombreRequisitor(requisitor.getNombreUsuario());
                requisicionPojo.setAppOrigen(request.getAppOrigen());
                requisicionPojo.setFuente(ConstantsUtils.FUENTES.VALES_FONDO.getFuente());
                requisicionPojo.setIdProveedor(carroCompraPojo.getIdProveedor());
                requisicionPojo.setIdSiteProveedor(carroCompraPojo.getVfVendorSiteId());
                responseProv = getProveedorById(requisicionPojo.getIdProveedor());
                requisicionPojo.setNombreSite(getNombreSiteById(requisicionPojo.getIdSiteProveedor()));
                requisicionPojo.setNombreProveedor(responseProv.getEntity().getNombreProveedor());
                requisicionPojo.setTipo(ConstantsUtils.TIPO_REQUISICIONES.EXTERNA.getTipo());
                requisicionPojo.setEstatus(ConstantsUtils.ESTATUS.ESTATUS_POR_APROBAR.getDescEstatus());
                requisicionPojo.setIdUen(idUen);
                String datosDeAuditoria
                        = ConstantsUtils.formattDate_DD_MM_YYYY(new Date())
                                .concat("-")
                                .concat(request.getUser().getIdUsuario())
                                .concat("-")
                                .concat("Generacion");
                requisicionPojo.setDatosDeAuditoria(datosDeAuditoria);

                ParametrosPorUenCia vSpx = em.createNamedQuery(
                        "ParametrosPorUenCia.findByIdUenIdParametro", ParametrosPorUenCia.class)
                        .setParameter("idUen", idUen)
                        .setParameter("idParametro", 47).getSingleResult();
                requisicionPojo.setSpx(vSpx.getValor().equals("1") ? "Y" : null);

                requisicionPojo.setDetalleDeRequisicionPojos(new ArrayList<>());

                NvcTblCarroCompra eCarroCompra = em.createNamedQuery("NvcTblCarroCompra.findByIdCarroCom", NvcTblCarroCompra.class)
                        .setParameter("idCarroCompra", carroCompraPojo.getIdCarroCompra())
                        .getSingleResult();

                //<R41223>
                //<R41223>
                NvcTblCarroCompraDetallePojo carroCompraDetalle = carroCompraPojo.getListDetalleCompra().isEmpty() ? null : carroCompraPojo.getListDetalleCompra().get(0);
                if (carroCompraDetalle != null) {
                    idProyecto = carroCompraDetalle.getIdProyecto() == null ? 0 : carroCompraDetalle.getIdProyecto();
                    idCentroCosto = carroCompraDetalle.getIdCentroCosto() == null ? 0 : carroCompraDetalle.getIdCentroCosto();
                    idSubfamilia = eCarroCompra.getIdSubfamilia();
//                    if(idProyecto == 0 && idCentroCosto == 0) {
//                        throw new Exception(" Error: Proyecto / Centro Costos requerido");
//                    }
                    String processOK = "OK";
                    try {

                        detalleDeRequisicionPojo = new DetalleDeRequisicionPojo();
                        String idCentroCostoMultCc = aplicarMultiCc(carroCompraPojo, detalleDeRequisicionPojo, carroCompraDetalle, idSubfamilia, idFad, ConstantsUtils.FUENTE_REQUISICION.FUENTE_VALES_FONDO.getFuente());
                        detalleDeRequisicionPojo.setIdCarroCompra(eCarroCompra.getIdCarroCompra());
                        detalleDeRequisicionPojo.setEstatus(ConstantsUtils.ESTATUS.ESTATUS_POR_APROBAR.getDescEstatus());

                        //<R41223>
                        siguienteAprobador(detalleDeRequisicionPojo, carroCompraDetalle, carroCompraPojo, idProyecto, idUen, idSubfamilia, idFad, ConstantsUtils.FUENTE_REQUISICION.FUENTE_VALES_FONDO.getFuente(), requisicionPojo, false);
                        List<ParametrosPorUenCia> parametros = em.createNamedQuery("ParametrosPorUenCia.findByIdUenNombreParametro", ParametrosPorUenCia.class)
                                .setParameter("nombreParametro", ConstantsUtils.PARAMETROS_CONFIGURACION.ASIGNACION_COMPRADOR.getNombreParametro())
                                .setParameter("idUen", idUen)
                                .getResultList();
                        String vTipo = parametros != null && !parametros.isEmpty() ? parametros.get(0).getValor() : "CARGA";

                        // RMN 13677 APOJR11028 //
                        Usuario comprador;
                        if (idProyecto > 0) {
//                            comprador = em.createNamedQuery("Usuario.findByIdUsuario", Usuario.class)
//                                    .setParameter("1", carroCompraDetalle.getComprador())
//                                    .getSingleResult();/// eprocura 
                            comprador = requisitorDefault;
                        } else {
                            comprador = requisitorDefault;
                        }
                        detalleDeRequisicionPojo.setIdCentroCosto(idCentroCosto);
                        detalleDeRequisicionPojo.setComprador(comprador.getId());
                        detalleDeRequisicionPojo.setNombreComprador(comprador.getNombreUsuario());
                        //<R20442>
                        detalleDeRequisicionPojo.setDescripcion(getDescripcionRequisicion(carroCompraPojo, request.getUser().getIdioma(), 250));
                        //</R20442>
                        detalleDeRequisicionPojo.setUom(eCarroCompra.getUdm());
                        detalleDeRequisicionPojo.setIdSubfamilia(carroCompraPojo.getIdSubfamilia());
                        detalleDeRequisicionPojo.setFuente(ConstantsUtils.FUENTES.VALES_FONDO.getFuente());
                        Date fechaRequerida = ConstantsUtils.sdf_dd_mm_yyyy.parse(carroCompraPojo.getFechaNecesidad());
                        detalleDeRequisicionPojo.setStrFechaNecesidad(carroCompraPojo.getFechaNecesidad());
                        detalleDeRequisicionPojo.setFechaNecesidad(fechaRequerida);
                        detalleDeRequisicionPojo.setIdMoneda(carroCompraPojo.getIdMoneda());
                        detalleDeRequisicionPojo.setMontoExtendido(carroCompraPojo.getPrecioUnitario() * carroCompraPojo.getCantidad());
                        detalleDeRequisicionPojo.setPrecio(carroCompraPojo.getPrecioUnitario());
                        detalleDeRequisicionPojo.setCantidad(carroCompraPojo.getCantidad());
                        detalleDeRequisicionPojo.setFechaNecesidad(fechaRequerida);
                        detalleDeRequisicionPojo.setTipoRecibo(carroCompraPojo.getIdTipoRecibo());
                        detalleDeRequisicionPojo.setIdLocalizacion(carroCompraPojo.getVfIdEnviarA());
                        detalleDeRequisicionPojo.setUrgente(carroCompraPojo.getRazonUrgenciaDes() == null || carroCompraPojo.getRazonUrgenciaDes().isEmpty() ? null : "S");
                        detalleDeRequisicionPojo.setRazonUrgencia(carroCompraPojo.getRazonUrgenciaDes());
                        detalleDeRequisicionPojo.setCantidad(carroCompraPojo.getCantidad());
                        if (!(detalleDeRequisicionPojo.getIdTipoCargo() != null && detalleDeRequisicionPojo.getIdTipoCargo().equals(ConstantsUtils.CUENTA_MULTI_CENTRO_COSTO))) {
                            detalleDeRequisicionPojo.setIdCuenta(carroCompraDetalle.getIdCuenta());
                            detalleDeRequisicionPojo.setSegmento1(carroCompraDetalle.getSegmento1());
                            detalleDeRequisicionPojo.setSegmento2(carroCompraDetalle.getSegmento2());
                            detalleDeRequisicionPojo.setSegmento3(carroCompraDetalle.getSegmento3());
                            detalleDeRequisicionPojo.setSegmento4(carroCompraDetalle.getSegmento4());
                            detalleDeRequisicionPojo.setSegmento5(carroCompraDetalle.getSegmento5());
                            detalleDeRequisicionPojo.setSegmento6(carroCompraDetalle.getSegmento6());
                            detalleDeRequisicionPojo.setSegmento7(carroCompraDetalle.getSegmento7());
                            detalleDeRequisicionPojo.setSegmento8(carroCompraDetalle.getSegmento8());
                            detalleDeRequisicionPojo.setIdIva(carroCompraPojo.getVfIvaId());
                        }
                        String notasAlComprador = eCarroCompra.getComentariosComprador() == null
                                ? "" : eCarroCompra.getComentariosComprador();
                        notasAlComprador = eCarroCompra.getRazonSpot() == null ? notasAlComprador.concat("") : notasAlComprador.concat("-").concat(eCarroCompra.getRazonSpot());
                        log.debug("*** generaPreviewValesfondo *** notasComprador > " + notasAlComprador);
                        detalleDeRequisicionPojo.setNotasComprador(notasAlComprador);
                        detalleDeRequisicionPojo.setTipoGasto(carroCompraDetalle.getTipoGasto());

                        if (idProyecto > 0) {
                            key = idProyecto.toString().concat(requisicionPojo.getRequisitor())
                                    .concat(requisicionPojo.getIdProveedor().toString())
                                    .concat(requisicionPojo.getIdSiteProveedor().toString())
                                    .concat(detalleDeRequisicionPojo.getIdMoneda())
                                    .concat(detalleDeRequisicionPojo.getIdLocalizacion().toString())
                                    .concat(carroCompraPojo.getVfIdFacturacion().toString());
                            if (mapRequis.containsKey(key)) {
                                numPartida = mapRequis.get(key).getDetalles().size();
                            } else {
                                mapRequis.put(key, requisicionPojo);
                                numPartida = 0;
                            }
                            detalleDeRequisicionPojo.setIdPartida(++numPartida);
                            mapRequis.get(key).getDetalles().add(detalleDeRequisicionPojo);
                        }

                        if (idCentroCosto > 0) {
                            // <RELEASE ARGENTINA>
                            //<RM16773>
                            boolean compradorPorFamilia = 
                                    organization.getCompradorFamilia() != null
                                    && organization.getCompradorFamilia().intValue() == 1;
                            key = compradorPorFamilia
                                    ? idCentroCostoMultCc.concat(comprador.getId())
                                            .concat(requisicionPojo.getRequisitor())
                                            .concat(requisicionPojo.getIdProveedor().toString())
                                            .concat(requisicionPojo.getIdSiteProveedor().toString())
                                            .concat(detalleDeRequisicionPojo.getIdMoneda())
                                            .concat(detalleDeRequisicionPojo.getIdLocalizacion().toString())
                                            .concat(carroCompraPojo.getVfIdFacturacion().toString())
                                    : idCentroCostoMultCc
                                            .concat(requisicionPojo.getRequisitor())
                                            .concat(requisicionPojo.getIdProveedor().toString())
                                            .concat(requisicionPojo.getIdSiteProveedor().toString())
                                            .concat(detalleDeRequisicionPojo.getIdMoneda())
                                            .concat(detalleDeRequisicionPojo.getIdLocalizacion().toString())
                                            .concat(carroCompraPojo.getVfIdFacturacion().toString());
                            //</RM16773>
                            requisicionPojo.setIdCentroCostos(idCentroCosto);
                            detalleDeRequisicionPojo.setIdCentroCosto(idCentroCosto);
                            if (mapRequis.containsKey(key)) {
                                numPartida = mapRequis.get(key).getDetalles().size();
                            } else {
                                mapRequis.put(key, requisicionPojo);
                                numPartida = 0;
                            }
                            detalleDeRequisicionPojo.setIdPartida(++numPartida);
                            mapRequis.get(key).getDetalles().add(detalleDeRequisicionPojo);
                        }

                    } catch (Exception e) {
                        log.error(e.getMessage(), e);
                        processOK = e.getMessage();
                    }
                    if (!processOK.equals("OK")) {
                        if (detalleDeRequisicionPojo == null) {
                            detalleDeRequisicionPojo = new DetalleDeRequisicionPojo();
                        }
                        detalleDeRequisicionPojo.setMensajeAprobacion(processOK);
                        requisicionPojo.getDetalles().add(detalleDeRequisicionPojo);
                        log.debug(" **** generaPreviewSpot *** idCarroCompraDetalle:" + carroCompraDetalle.getIdCarroCompraDetalle());
                        mapRequis.put("e_" + carroCompraDetalle.getIdCarroCompraDetalle(), requisicionPojo);
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        List<RequisicionPojo> requisicionPojos = new ArrayList<>();
        RequisicionPojo requiPojo;
        for (java.util.Iterator<String> itKeys = mapRequis.keySet().iterator(); itKeys.hasNext();) {
            String mapKey = itKeys.next();
            requiPojo = mapRequis.get(mapKey);
            requisicionPojos.add(requiPojo);
        }

//        for (java.util.Iterator<Integer> iteraSpot = mapProyectos.keySet().iterator(); iteraSpot.hasNext();) {
//            Integer idProyecto = iteraSpot.next();
//            requiPojo = mapProyectos.get(idProyecto);
//            requisicionPojos.add(requiPojo);
//        }
//
//        for (java.util.Iterator<String> iteraCC = mapCentroCostos.keySet().iterator(); iteraCC.hasNext();) {
//            String keyCC = iteraCC.next();
//            requiPojo = mapCentroCostos.get(keyCC);
//            requisicionPojos.add(requiPojo);
//        }
        return requisicionPojos;
    }
    
    private List<RequisicionPojo> previewRequisicionesAlmacen(List<NvcTblCarroCompraPojo> carroCompraPojoList, RequisicionRequest request) {
        log.debug("*** previewRequisicionesAlmacen ***");
        System.out.println("Ejecutando metodo previewRequisicionesAlmacen");

        java.util.Map<String, RequisicionPojo> proyectoMap = new java.util.HashMap<>();
        java.util.Map<String, RequisicionPojo> centroCostoMap = new java.util.HashMap<>();
        List<RequisicionPojo> requisicionPojoList = new ArrayList<>();
        try {
            Integer idProyecto;
            Integer idCentroCosto;
//            DetalleDeRequisicionPojo detalleDeRequisicionPojo;

            Usuario requisitor = usersRepo.getOneById(request.getUser().getIdUsuario());

            for (NvcTblCarroCompraPojo carroCompraPojo : carroCompraPojoList) {

                System.out.println(" ");
                System.out.println("CarroCompraPojo metodo previewRequisicionesAlmacen linea 570");
                System.out.println(carroCompraPojo);
                System.out.println(" ");

                Integer laUen;
                boolean isInterUen = false;
                RequisicionPojo requisicionPojo = new RequisicionPojo();
                requisicionPojo.setIdUen(carroCompraPojo.getIdUen());
                requisicionPojo.setEstatus(ConstantsUtils.ESTATUS.ESTATUS_POR_APROBAR.getDescEstatus());
                requisicionPojo.setRequisitor(carroCompraPojo.getIdUsuarioCreacion());
                requisicionPojo.setNombreRequisitor(requisitor.getNombreUsuario());
                requisicionPojo.setFechaRequisicion(new Date());
                requisicionPojo.setTipo(ConstantsUtils.TIPO_REQUISICIONES.INTERNA.getTipo());
                requisicionPojo.setDetalleDeRequisicionPojos(new ArrayList<>());
                requisicionPojo.setAppOrigen(request.getAppOrigen());
                
                DetalleDeRequisicionPojo detalleDeRequisicionPojo; 
                NvcTblCarroCompraDetallePojo carroCompraDetalle = carroCompraPojo.getAnyDetalle();
                System.out.println("carroCompraDetalle lina 588 " + carroCompraDetalle);

                if (carroCompraDetalle != null) {
                    detalleDeRequisicionPojo = new DetalleDeRequisicionPojo();
                    detalleDeRequisicionPojo.setIdCarroCompra(carroCompraPojo.getIdCarroCompra());

                    idProyecto = carroCompraDetalle.getIdProyecto() == null ? 0 : carroCompraDetalle.getIdProyecto();
                    idCentroCosto = carroCompraDetalle.getIdCentroCosto() == null ? 0 : carroCompraDetalle.getIdCentroCosto();
                    requisicionPojo.setIdCentroCostos(idCentroCosto);

                    int numPartida = 0;
                    detalleDeRequisicionPojo.setEstatus(ConstantsUtils.ESTATUS.ESTATUS_POR_APROBAR.getDescEstatus());
                    List<ProyectosPorAprobadorUen> proyectosPorAprobarUen = em.createNamedQuery("ProyectosPorAprobadorUen.ParaAprobacionProyecto")
                            .setParameter("idUen", carroCompraPojo.getIdUen())
                            .setParameter("idProyecto", idProyecto)
                            .getResultList();
                    if (!proyectosPorAprobarUen.isEmpty()) {
                        requisicionPojo.setEstatus(ConstantsUtils.ESTATUS.ESTATUS_POR_APROBAR.getDescEstatus());
                        detalleDeRequisicionPojo.setEstatus(ConstantsUtils.ESTATUS.ESTATUS_POR_APROBAR.getDescEstatus());
                    }
                    log.debug("UEN:::::::::" + carroCompraPojo.getIdUen());
                    if (null == carroCompraPojo.getIdUenSurtidora()) {
                        laUen = carroCompraPojo.getIdUen();  
                    } else {
                        isInterUen = true;
                        laUen = carroCompraPojo.getIdUenSurtidora();
//                        carroCompraPojo.setIdUen(carroCompraPojo.getIdUenRequisitora());
                        detalleDeRequisicionPojo.setIdUenSurtidora(laUen);
                    }
                    
                    NvcVItemsAll item = itemsRepo.findViewByPk(
                            carroCompraPojo.getIdItem(), laUen, carroCompraPojo.getIdAlmacen());

                    NvcVmOaExistencias vItemAll = itemsRepo.getExistencia(
                            carroCompraPojo.getIdItem(),
                            laUen,
                            item.getNvcVItemsAllPK().getIdAlmacen()
                    );

                    Integer idItem = vItemAll.getNvcVmOaExistenciasPK().getIdProducto();
                    Integer idFad = 0;
                    String idCentroCostoMultCc = aplicarMultiCc(
                            carroCompraPojo,
                            detalleDeRequisicionPojo,
                            carroCompraDetalle,
                            item.getSubfamilia(),
                            idFad,
                            item.getFuente()
                    );
                    requisicionPojo.setIdAlmacen(item.getNvcVItemsAllPK().getIdAlmacen());
                    log.debug("*** getRequisicionesCatExteno *** IdItem      > " + idItem);
//                    log.debug("*** getRequisicionesCatExteno *** IdProveedor > " + idProveedor);
                    log.debug("*** getRequisicionesCatExteno *** Uen         > " + vItemAll.getNvcVmOaExistenciasPK().getIdUen());
                    log.debug("*** getRequisicionesCatExteno *** Uen         > " + vItemAll.getNvcVmOaExistenciasPK().getIdAlmacen());

                    String datosDeAuditoria
                            = ConstantsUtils.formattDate_DD_MM_YYYY(new Date())
                                    .concat("-")
                                    .concat(request.getUser().getIdUsuario())
                                    .concat("-")
                                    .concat("Generacion");
                    requisicionPojo.setDatosDeAuditoria(datosDeAuditoria);

                    siguienteAprobador(
                            detalleDeRequisicionPojo,
                            carroCompraDetalle,
                            carroCompraPojo,
                            idProyecto,
                            laUen,
                            item.getSubfamilia(),
                            idFad,
                            item.getFuente(),
                            requisicionPojo,
                            isInterUen
                    );
                    if(detalleDeRequisicionPojo.getIdEstatus() == null) {
                        detalleDeRequisicionPojo.setIdEstatus(19);
                    }
                    detalleDeRequisicionPojo.setIdProducto(idItem);
                    detalleDeRequisicionPojo.setCodProducto(item.getCodigoItem());
                    detalleDeRequisicionPojo.setDescripcion(carroCompraPojo.getDescripcion());
                    double precio = item.getPrecio();
                    log.debug("*** previewRequisicionesAlmacen *** precio > " + precio);
                    double montoExtendido = item.getPrecio() * carroCompraPojo.getCantidad();
                    log.debug("*** previewRequisicionesAlmacen *** montoExtendido > " + montoExtendido);
                    detalleDeRequisicionPojo.setMontoExtendido(montoExtendido);
                    detalleDeRequisicionPojo.setPrecio(precio);
                    detalleDeRequisicionPojo.setCantidad(carroCompraPojo.getCantidad());
                    detalleDeRequisicionPojo.setUom(item.getUdm());
                    detalleDeRequisicionPojo.setIdSubfamilia(item.getSubfamilia());
                    Date fechaRequerida = ConstantsUtils.sdf_dd_mm_yyyy.parse(carroCompraPojo.getFechaNecesidad());
                    log.debug("*** previewRequisicionesAlmacen *** fechaRequerida > " + fechaRequerida);
                    detalleDeRequisicionPojo.setFechaNecesidad(fechaRequerida);
                    detalleDeRequisicionPojo.setComprador(null); // MANDAMOS NULL EL COMPRADOR 
                    detalleDeRequisicionPojo.setIdMoneda(item.getIdMoneda());
                    detalleDeRequisicionPojo.setIdLocalizacion(carroCompraPojo.getIdlocalizacion());
                    detalleDeRequisicionPojo.setIdUen(carroCompraPojo.getIdUen());
                    detalleDeRequisicionPojo.setUrgente((carroCompraPojo.getRazonUrgenciaDes() == null || carroCompraPojo.getRazonUrgenciaDes().isEmpty()) ? null : "S");
                    detalleDeRequisicionPojo.setRazonUrgencia(carroCompraPojo.getRazonUrgenciaDes());
                    detalleDeRequisicionPojo.setCantidad(carroCompraPojo.getCantidad());
                    System.out.println("Se setean los segmentos: " + !(detalleDeRequisicionPojo.getIdTipoCargo() != null && detalleDeRequisicionPojo.getIdTipoCargo().equals(ConstantsUtils.CUENTA_MULTI_CENTRO_COSTO)));
                    System.out.println("IdTipoCargo: " + detalleDeRequisicionPojo.getIdTipoCargo());
                    if (!(detalleDeRequisicionPojo.getIdTipoCargo() != null && detalleDeRequisicionPojo.getIdTipoCargo().equals(ConstantsUtils.CUENTA_MULTI_CENTRO_COSTO))) {
                        detalleDeRequisicionPojo.setIdCuenta(carroCompraDetalle.getIdCuenta());
                        detalleDeRequisicionPojo.setSegmento1(carroCompraDetalle.getSegmento1());
                        detalleDeRequisicionPojo.setSegmento2(carroCompraDetalle.getSegmento2());
                        detalleDeRequisicionPojo.setSegmento3(carroCompraDetalle.getSegmento3());
                        detalleDeRequisicionPojo.setSegmento4(carroCompraDetalle.getSegmento4());
                        detalleDeRequisicionPojo.setSegmento5(carroCompraDetalle.getSegmento5());
                        detalleDeRequisicionPojo.setSegmento6(carroCompraDetalle.getSegmento6());
                        detalleDeRequisicionPojo.setSegmento7(carroCompraDetalle.getSegmento7());
                        detalleDeRequisicionPojo.setSegmento8(carroCompraDetalle.getSegmento8());
                        detalleDeRequisicionPojo.setDatosDeAuditoria(datosDeAuditoria);
                    }
                    // DESCUENTO
                    // PRECIO BRUTO

                    detalleDeRequisicionPojo.setIva("");
                    detalleDeRequisicionPojo.setTipoGasto(carroCompraDetalle.getTipoGasto());
                    detalleDeRequisicionPojo.setNotasComprador(carroCompraPojo.getComentariosComprador());
                    detalleDeRequisicionPojo.setTiempoEntrega(item.getTiempoEntrega());
                    detalleDeRequisicionPojo.setIdUenSurtidora(carroCompraPojo.getIdUenSurtidora());
                    detalleDeRequisicionPojo.setIdProyecto(idProyecto == 0 ? null : idProyecto);
                    detalleDeRequisicionPojo.setFuente(vItemAll.getFuente());

                    if (idProyecto > 0) {

                        NvcTblProyectosH proyecto = em.createNamedQuery("NvcTblProyectosH.findByIdProyecto", NvcTblProyectosH.class)
                                .setParameter("id", Long.valueOf(idProyecto)).getSingleResult();

                        detalleDeRequisicionPojo.setProyecto(proyecto.getCodProyecto().concat("-").concat(proyecto.getNombreProyecto()));
                        detalleDeRequisicionPojo.setCodProyecto(proyecto.getCodProyecto());

                        NvcVmOaProyectoTareas idTarea = em.createNamedQuery("NvcVmOaProyectoTareas.findByIdTarea", NvcVmOaProyectoTareas.class)
                                .setParameter("idTarea", Long.valueOf(carroCompraDetalle.getIdTarea())).getSingleResult();
                        detalleDeRequisicionPojo.setIdTarea((int) (idTarea.getNvcVmOaProyectoTareasPK().getIdTarea()));
                        detalleDeRequisicionPojo.setTarea(idTarea.getCodTarea());

                        String key = idProyecto.toString().concat(vItemAll.getFuente()).concat("" + vItemAll.getNvcVmOaExistenciasPK().getIdAlmacen());
                        if (proyectoMap.containsKey(key)) {
                            numPartida = proyectoMap.get(key).getDetalles().size();
                        } else {
                            proyectoMap.put(key, requisicionPojo);
                            numPartida = 0;
                        }
                        detalleDeRequisicionPojo.setIdPartida(++numPartida);
                        proyectoMap.get(key).getDetalles().add(detalleDeRequisicionPojo);

                    }
                    log.debug("*** previewRequisicionesAlmacen *** proyectoMap > " + proyectoMap);

                    detalleDeRequisicionPojo.setIdCentroCosto(idCentroCosto == 0 ? null : idCentroCosto);
                    if (idCentroCosto > 0) {
                        detalleDeRequisicionPojo.setIdCentroCosto(idCentroCosto);
                        NvcTblOaCcH centroCosto = em.createNamedQuery(
                                "NvcTblOaCcH.findByPK", NvcTblOaCcH.class)
                                .setParameter(1, BigInteger.valueOf(carroCompraPojo.getIdUen()))
                                .setParameter(2, BigInteger.valueOf(idCentroCosto))
                                .getSingleResult();
                        detalleDeRequisicionPojo.setCentroCosto(centroCosto.getNombreCc());

                        String key = idCentroCostoMultCc.concat(vItemAll.getFuente()).concat("" + vItemAll.getNvcVmOaExistenciasPK().getIdAlmacen());
                        if (centroCostoMap.containsKey(key)) {
                            numPartida = centroCostoMap.get(key).getDetalles().size();
                        } else {
                            centroCostoMap.put(key, requisicionPojo);
                            numPartida = 0;
                        }
                        detalleDeRequisicionPojo.setIdPartida(++numPartida);
                        centroCostoMap.get(key).getDetalles().add(detalleDeRequisicionPojo);
                    }
                    log.debug("*** previewRequisicionesAlmacen *** centroCostoMap > " + centroCostoMap);
//                    requisicionPojoList.add(requisicionPojo);
                }
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }

        for (String key : proyectoMap.keySet()) {
            RequisicionPojo requiPojo = proyectoMap.get(key);
            requisicionPojoList.add(requiPojo);
        }

        for (String key : centroCostoMap.keySet()) {
            RequisicionPojo requiPojo = centroCostoMap.get(key);
            requisicionPojoList.add(requiPojo);
        }

        return requisicionPojoList;
    }
    
    public RequisicionResponse previewRequisicion(RequisicionRequest request) {
        log.debug("___________________________previewRequisicion{" + request.getEntityList() + "}");
        RequisicionResponse response = new RequisicionResponse();
        response.setRequisicionPreviewList(new ArrayList<>());

        java.util.Map<String, List<NvcTblCarroCompraPojo>> uenMap = ordenaXUen(request.getEntityList());
        // ORDENO TODAS LAS LINES QUE LLEGAN POR UEN

        for (java.util.Iterator<String> iteraUen = uenMap.keySet().iterator(); iteraUen.hasNext();) {

            String nombreUen = iteraUen.next();
            List<NvcTblCarroCompraPojo> carroCompraxUenList = uenMap.get(nombreUen);
            log.debug("*** previewRequisicion *** uenMap > " + uenMap);

            // ORDENAMOS LAS LINEAS DE LA UEN POR TIPO (Spot, Almacen, Proveedor)
            java.util.Map<ConstantsUtils.FUENTES, List<NvcTblCarroCompraPojo>> requisicionMapPorTipo = obtenRequisicionesPorTipo(carroCompraxUenList);
            log.debug("*** previewRequisicion *** requisicionMapPorTipo > " + requisicionMapPorTipo);

            RequisicionPreview requisicionPreview;
            List<TipoRequisicion> tiposRequisiciones = new ArrayList<>();

            List<RequisicionPojo> listaRequisiciones;
            for (Map.Entry<ConstantsUtils.FUENTES, List<NvcTblCarroCompraPojo>> entry : requisicionMapPorTipo.entrySet()) {
                System.out.println("Ciclo entrando");
                ConstantsUtils.FUENTES tipo = entry.getKey();
                List<NvcTblCarroCompraPojo> carroCompraPojoList = entry.getValue();
                System.out.println("tipo: " + tipo);

                listaRequisiciones = new ArrayList<>();

                if (tipo.compareTo(ConstantsUtils.FUENTES.VALES_FONDO) == 0) {
                    listaRequisiciones = generaPreviewValesFondo(carroCompraPojoList, request);
                }

                if (tipo.compareTo(ConstantsUtils.FUENTES.INTERUEN) == 0) {
                    listaRequisiciones = generaPreviewInterUen(carroCompraPojoList, request);
                }

                if (tipo.compareTo(ConstantsUtils.FUENTES.SPOT) == 0) {
                    listaRequisiciones = generaPreviewSpot(carroCompraPojoList, request);
                }

                if (tipo.compareTo(ConstantsUtils.FUENTES.PROVEEDOR) == 0) {
                    listaRequisiciones = previewRequisicionesProveedor(carroCompraPojoList, request);
                }

                if (tipo.compareTo(ConstantsUtils.FUENTES.PUNCHOUT) == 0) {
                    listaRequisiciones = generaPreviewSpot(carroCompraPojoList, request);
                }

                if (tipo.compareTo(ConstantsUtils.FUENTES.PEDIDO_ESPECIAL) == 0) {
                    listaRequisiciones = generaPreviewSpot(carroCompraPojoList, request);
                }
                if (tipo.compareTo(ConstantsUtils.FUENTES.ALMACEN) == 0) {
                    listaRequisiciones = previewRequisicionesAlmacen(carroCompraPojoList, request);
                }

                if (!listaRequisiciones.isEmpty()) {
                    tiposRequisiciones.add(new TipoRequisicion(tipo, listaRequisiciones));
                }

            }
            requisicionPreview = new RequisicionPreview(nombreUen, tiposRequisiciones);
            response.getRequisicionPreviewList().add(requisicionPreview);
        }

        return response;
    }
    
    private List<RequisicionPojo> previewRequisicionesProveedor(List<NvcTblCarroCompraPojo> carroCompraPojoList, RequisicionRequest request) {
        java.util.Map<String, RequisicionPojo> mapRequis = new java.util.HashMap<>();
        List<RequisicionPojo> requisicionPojoList = new ArrayList<>();
        try {
            Integer idProyecto;
            Integer idProveedor;
            Integer idCentroCosto;
            DetalleDeRequisicionPojo detalleDeRequisicionPojo = null;
            Double precio; 	// <CAT_VAR>
            Usuario requisitor = em.createNamedQuery("Usuario.findByIdUsuario", Usuario.class)
                    .setParameter("1", request.getUser().getIdUsuario())
                    .getSingleResult();

            /* <FAD> */
            Integer idFad;
            /* </FAD> */

            for (NvcTblCarroCompraPojo carroCompraPojo : carroCompraPojoList) {
                System.out.println("linea 869, carroCompraPojo");
                System.out.println(carroCompraPojo);

//                NvcTblCarroCompraDetallePojo carroCompraDetallePojo = carroCompraPojo.getListDetalleCompra().get(0);
                NvcTblCarroCompraDetallePojo carroCompraDetallePojo = carroCompraPojo.getAnyDetalle();
                // A EXCEPCION DE MULTICC, SOLO DEBERIA DE TENER UN DETALLE CON LA CUENTA A DONDE SE V

                NvcTblCatalogoItem catalogoItem = em.createNamedQuery("NvcTblCatalogoItem.findByIdItem", NvcTblCatalogoItem.class)
                        .setParameter("idItem", carroCompraPojo.getIdItem()).getSingleResult();

                /*<FAD>*/
                idFad = carroCompraPojo.getIdFad() == null ? 0 : carroCompraPojo.getIdFad();
                /*</FAD>*/

                String processOK;
                RequisicionPojo requisicionPojo = new RequisicionPojo();
                requisicionPojo.setDetalleDeRequisicionPojos(new ArrayList<>());
                String mapKey = null;
                try {
                    processOK = "OK";
                    if (catalogoItem.getEstatus().getDescEstatus().equals(ConstantsUtils.ESTATUS.ESTATUS_APROBADA.getDescEstatus())
                            && catalogoItem.getItemPublicadoEstatus().getDescEstatus().equals(ConstantsUtils.ESTATUS.ESTATUS_ACTIVA.getDescEstatus())) {

                        idProyecto = carroCompraDetallePojo.getIdProyecto() == null ? 0 : carroCompraDetallePojo.getIdProyecto();
                        idCentroCosto = carroCompraDetallePojo.getIdCentroCosto() == null ? 0 : carroCompraDetallePojo.getIdCentroCosto();
                        if (idProyecto == 0 && idCentroCosto == 0) {
//                            throw new Exception("Error: Centro de costo o Proyecto necesarios ");
                            throw new Exception("E001");
                        }

                        requisicionPojo.setIdUen(carroCompraPojo.getIdUen());
                        requisicionPojo.setEstatus(ConstantsUtils.ESTATUS.ESTATUS_POR_APROBAR.getDescEstatus());
                        requisicionPojo.setAppOrigen(request.getAppOrigen());
                        requisicionPojo.setRequisitor(carroCompraPojo.getIdUsuarioCreacion());
                        requisicionPojo.setNombreRequisitor(requisitor.getNombreUsuario());
                        requisicionPojo.setFechaRequisicion(new Date());
                        requisicionPojo.setTipo(ConstantsUtils.TIPO_REQUISICIONES.EXTERNA.getTipo());
                        requisicionPojo.setIdCentroCostos(idCentroCosto);
                        requisicionPojo.setTipoRecibo(carroCompraPojo.getIdTipoRecibo());
                        requisicionPojo.setIdProyecto(idProyecto);

                        NvcTblCatalogoUen catUen = catalogoItem.getCatalogoUen();
                        
                        //<R18321>
                        NvcVItemsAll vItemAll = itemsRepo.findViewByItemUenFuente(
                            catUen.getUen().getOrganizationId().intValue(),
                            catalogoItem.getIdItem(),
                            ConstantsUtils.SRC_SUPPLIER
                        );
                        //<R18321>
                        // <CAT_VAR>
                        if (carroCompraPojo.getPrecioUnitario() != null) {
                            precio = carroCompraPojo.getPrecioUnitario();
                        } else {
                            precio = vItemAll.getPrecio();
                        }
                        // <CAT_VAR>

                        NvcTblCatalogoLocalizacion catLocalizacion = catalogRepo
                                .findByCatUenLocalizacion(
                                        catalogoItem.getIdCatalogoUen(),
                                        carroCompraPojo.getIdlocalizacion());
                        List<NvcTblCatalogoUenSite> sites = catLocalizacion.getSites();
                        NvcTblCatalogoUenSite firstSite = sites.get(0);
                        
                        idProveedor = catalogoItem.getCatalogoUen().getCatalogo().getIdProveedor();
                        requisicionPojo.setIdProveedor(idProveedor);
                        requisicionPojo.setIdSiteProveedor(firstSite
                                .getProveedorSites()
                                .getNvcTblProvSitesHPK()
                                .getIdSucursalProveedor()
                                .intValue());
                        String datosDeAuditoria
                                = ConstantsUtils.formattDate_DD_MM_YYYY(new Date())
                                        .concat("-")
                                        .concat(request.getUser().getIdUsuario())
                                        .concat("-")
                                        .concat("Generacion");
                        requisicionPojo.setDatosDeAuditoria(datosDeAuditoria);

                        int numPartida = 0;
                        detalleDeRequisicionPojo = new DetalleDeRequisicionPojo();
                        String idCentroCostoMultCc = aplicarMultiCc(carroCompraPojo, detalleDeRequisicionPojo, carroCompraDetallePojo, vItemAll.getSubfamilia(), idFad, ConstantsUtils.FUENTE_REQUISICION.FUENTE_PROVEEDOR.getFuente());
                        detalleDeRequisicionPojo.setEstatus(ConstantsUtils.ESTATUS.ESTATUS_POR_APROBAR.getDescEstatus());
                        List<ProyectosPorAprobadorUen> proyectosPorAprobarUen = em.createNamedQuery("ProyectosPorAprobadorUen.ParaAprobacionProyecto")
                                .setParameter("idUen", carroCompraPojo.getIdUen())
                                .setParameter("idProyecto", idProyecto)
                                .getResultList();
                        if (!proyectosPorAprobarUen.isEmpty()) {
                            requisicionPojo.setEstatus(ConstantsUtils.ESTATUS.ESTATUS_POR_APROBAR.getDescEstatus());
                            detalleDeRequisicionPojo.setEstatus(ConstantsUtils.ESTATUS.ESTATUS_POR_APROBAR.getDescEstatus());
                        }

                        if(detalleDeRequisicionPojo.getIdEstatus() == null) {
                            detalleDeRequisicionPojo.setIdEstatus(19);
                        }
                        detalleDeRequisicionPojo.setVendorPartNumber(catalogoItem.getCodigoItem());
                        detalleDeRequisicionPojo.setCodProducto(catalogoItem.getCodigoItem());
                        log.debug(" **** previewRequisicionesProveedor *** CodigoItem > " + catalogoItem.getCodigoItem());
                        detalleDeRequisicionPojo.setIdContrato(catUen.getIdCatalogo());

                        detalleDeRequisicionPojo.setIdCarroCompra(carroCompraPojo.getIdCarroCompra());
                        detalleDeRequisicionPojo.setIdProducto(catalogoItem.getIdItem());
                        //<T412131>
                        detalleDeRequisicionPojo.setDescripcion(getDescripcionRequisicionProveedor(carroCompraPojo, request.getUser().getIdioma(), 250));
                        //</T412131>
                        // <CAT_VAR>
                        NvcTblCarroCompra carroCompra = em.createNamedQuery("NvcTblCarroCompra.findByIdCarroCom", NvcTblCarroCompra.class)
                                .setParameter("idCarroCompra", carroCompraPojo.getIdCarroCompra())
                                .getSingleResult();
                        boolean precioOpen = catUen.getTipoPrecio().equals(ConstantsUtils.PRECIO_ABIERTO);
                        boolean precioFactor = catUen.getTipoPrecio().equals(ConstantsUtils.PRECIO_FACTOR);

                        if (precioOpen || precioFactor) {
                            detalleDeRequisicionPojo.setMontoExtendido(carroCompra.getVfPrecioUnitario() * carroCompraPojo.getCantidad());
                        } else {
                            detalleDeRequisicionPojo.setMontoExtendido(vItemAll.getPrecio() * carroCompraPojo.getCantidad());
                        }
                        detalleDeRequisicionPojo.setPrecio(precio);
                        // <CAT_VAR>

                        detalleDeRequisicionPojo.setCantidad(carroCompraPojo.getCantidad());
                        detalleDeRequisicionPojo.setUom(vItemAll.getUdm());
                        detalleDeRequisicionPojo.setIdSubfamilia(vItemAll.getSubfamilia());
                        Date fechaRequerida = ConstantsUtils.sdf_dd_mm_yyyy.parse(carroCompraPojo.getFechaNecesidad());
                        detalleDeRequisicionPojo.setFechaNecesidad(fechaRequerida);
                        detalleDeRequisicionPojo.setTipoRecibo(carroCompraPojo.getIdTipoRecibo());
//
                        Usuario comprador = catUen.getComprador();  // <CAT_VAR>
                        siguienteAprobador(detalleDeRequisicionPojo, carroCompraDetallePojo, carroCompraPojo, idProyecto, carroCompraPojo.getIdUen(), vItemAll.getSubfamilia(), idFad, ConstantsUtils.FUENTE_REQUISICION.FUENTE_PROVEEDOR.getFuente(), requisicionPojo, false);
                        detalleDeRequisicionPojo.setComprador(comprador.getId());
                        detalleDeRequisicionPojo.setNombreComprador(comprador.getNombreUsuario());

                        detalleDeRequisicionPojo.setIdMoneda(vItemAll.getIdMoneda());
                        detalleDeRequisicionPojo.setIdLocalizacion(carroCompraPojo.getIdlocalizacion());
                        detalleDeRequisicionPojo.setIdUen(carroCompraPojo.getIdUen());
                        detalleDeRequisicionPojo.setUrgente((carroCompraPojo.getRazonUrgenciaDes() == null || carroCompraPojo.getRazonUrgenciaDes().isEmpty()) ? null : "S");
                        detalleDeRequisicionPojo.setRazonUrgencia(carroCompraPojo.getRazonUrgenciaDes());
                        detalleDeRequisicionPojo.setFuente(ConstantsUtils.FUENTE_REQUISICION.FUENTE_PROVEEDOR.getFuente());
                        detalleDeRequisicionPojo.setCantidad(carroCompraPojo.getCantidad());
                        detalleDeRequisicionPojo.setIdSubfamilia(carroCompraPojo.getIdSubfamilia());
                        if (!(detalleDeRequisicionPojo.getIdTipoCargo() != null && detalleDeRequisicionPojo.getIdTipoCargo().equals(ConstantsUtils.CUENTA_MULTI_CENTRO_COSTO))) {
                            detalleDeRequisicionPojo.setIdCuenta(carroCompraDetallePojo.getIdCuenta());
                            detalleDeRequisicionPojo.setSegmento1(carroCompraDetallePojo.getSegmento1());
                            detalleDeRequisicionPojo.setSegmento2(carroCompraDetallePojo.getSegmento2());
                            detalleDeRequisicionPojo.setSegmento3(carroCompraDetallePojo.getSegmento3());
                            detalleDeRequisicionPojo.setSegmento4(carroCompraDetallePojo.getSegmento4());
                            detalleDeRequisicionPojo.setSegmento5(carroCompraDetallePojo.getSegmento5());
                            detalleDeRequisicionPojo.setSegmento6(carroCompraDetallePojo.getSegmento6());
                            detalleDeRequisicionPojo.setSegmento7(carroCompraDetallePojo.getSegmento7());
                            detalleDeRequisicionPojo.setSegmento8(carroCompraDetallePojo.getSegmento8());
                        }
                        detalleDeRequisicionPojo.setIdIva(catalogoItem.getIdIva());

                        detalleDeRequisicionPojo.setNotasComprador(carroCompraPojo.getComentariosComprador());
                        detalleDeRequisicionPojo.setTiempoEntrega(catalogoItem.getTiempoEntrega());
                        detalleDeRequisicionPojo.setTipoGasto(carroCompraDetallePojo.getTipoGasto());

                        if (idProyecto > 0) { //
                            NvcTblProyectosH proyecto = em.createNamedQuery("NvcTblProyectosH.findByIdProyecto", NvcTblProyectosH.class)
                                    .setParameter("id", Long.valueOf(idProyecto)).getSingleResult();
                            detalleDeRequisicionPojo.setIdProyecto(proyecto.getIdProyecto().intValue());
                            detalleDeRequisicionPojo.setProyecto(proyecto.getCodProyecto().concat("-").concat(proyecto.getNombreProyecto()));
                            detalleDeRequisicionPojo.setCodProyecto(proyecto.getCodProyecto());

                            NvcVmOaProyectoTareas idTarea = em.createNamedQuery("NvcVmOaProyectoTareas.findByIdTarea", NvcVmOaProyectoTareas.class)
                                    .setParameter("idTarea", carroCompraDetallePojo.getIdTarea().longValue()).getSingleResult();
                            detalleDeRequisicionPojo.setIdTarea((int) (idTarea.getNvcVmOaProyectoTareasPK().getIdTarea()));
                            detalleDeRequisicionPojo.setTarea(idTarea.getCodTarea());

                        }

                        if (idProyecto > 0) {
                            detalleDeRequisicionPojo.setIdProyecto(idProyecto);
                            mapKey = idProveedor.toString().concat("_").concat(idProyecto.toString());
                        }

                        if (idCentroCosto > 0) {
                            detalleDeRequisicionPojo.setIdCentroCosto(idCentroCosto);
                            mapKey = idProveedor.toString().concat("_").concat(idCentroCostoMultCc);
                        }

                        if (mapKey != null) {
                            if (mapRequis.containsKey(mapKey)) {
                                numPartida = mapRequis.get(mapKey).getDetalles().size();
                            } else {
                                mapRequis.put(mapKey, requisicionPojo);
                                numPartida = 0;
                            }
                            detalleDeRequisicionPojo.setIdPartida(++numPartida);
                        }
                        mapRequis.get(mapKey).getDetalles().add(detalleDeRequisicionPojo);
                    }
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                    processOK = e.getMessage();
                }
                if (!processOK.equals("OK")) {
                    if (detalleDeRequisicionPojo == null) {
                        detalleDeRequisicionPojo = new DetalleDeRequisicionPojo();
                    }
                    detalleDeRequisicionPojo.setMensajeAprobacion(processOK);
                    requisicionPojo.getDetalles().add(detalleDeRequisicionPojo);
                    log.debug(" **** previewRequisicionesProveedor *** idCarroCompraDetalle:" + carroCompraDetallePojo.getIdCarroCompraDetalle());
                    mapRequis.put("e_" + carroCompraDetallePojo.getIdCarroCompraDetalle(), requisicionPojo);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        RequisicionPojo requiPojo;
        for (String key : mapRequis.keySet()) {
            requiPojo = mapRequis.get(key);
            requisicionPojoList.add(requiPojo);
        }
        return requisicionPojoList;
    }
    
    public String aplicarMultiCc(
            NvcTblCarroCompraPojo carroCompraPojo,
            DetalleDeRequisicionPojo detalleDeRequisicionPojo,
            NvcTblCarroCompraDetallePojo carroCompraDetalle,
            Integer idSubfamilia, Integer idFad, String fuente
    ) {
        String idCentroCostoMultCc = "";
        Integer cc = 0;
        if (carroCompraPojo.getListDetalleCompra() != null && carroCompraPojo.getListDetalleCompra().size() > 1) {
            detalleDeRequisicionPojo.setIdTipoCargo(ConstantsUtils.CUENTA_MULTI_CENTRO_COSTO);
            List<String> listCc = new ArrayList();
            detalleDeRequisicionPojo.setDetalleMultiCc(new ArrayList());
            for (NvcTblCarroCompraDetallePojo temp : carroCompraPojo.getListDetalleCompra()) {
                // se crea una variable temporal para que el detalle se quede con el prime cc
                DetalleDeRequisicionPojo detalleReqTem = new DetalleDeRequisicionPojo();
                listCc.add("" + temp.getIdCentroCosto());
                siguienteAprobador(
                        detalleReqTem,
                        temp,
                        carroCompraPojo,
                        cc,
                        carroCompraPojo.getIdUen(),
                        idSubfamilia,
                        idFad,
                        fuente,
                        null,
                        false
                );
                temp.setSiguienteAprobador(detalleReqTem.getNombreAprobador());
            }
            detalleDeRequisicionPojo.getDetalleMultiCc().addAll(carroCompraPojo.getListDetalleCompra());
            Collections.sort(listCc);
            for (String temp : listCc) {
                idCentroCostoMultCc += temp;
            }
        } else {
            idCentroCostoMultCc += carroCompraDetalle.getIdCentroCosto();
        }
        return idCentroCostoMultCc;
    }
    
    public NvcTblParameterUenPojo findParameterUenByUenName(Integer idUen, String parameter_name) {

        NvcTblParameterUenPojo response = new NvcTblParameterUenPojo();
        StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("nvc_pkg_parameters_spx.find_parameter_uen_value");
        storedProcedure.registerStoredProcedureParameter("p_parameter_name", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("p_id_uen", Integer.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("message_out", String.class, ParameterMode.OUT);
        storedProcedure.registerStoredProcedureParameter("cursor_out", void.class, ParameterMode.REF_CURSOR);

        storedProcedure.setParameter("p_parameter_name", parameter_name);
        storedProcedure.setParameter("p_id_uen", idUen);
        storedProcedure.execute();

        // List<Object[]> cursor = (List<Object[]>) storedProcedure.getOutputParameterValue("cursor_out");
        List<Object[]> items = storedProcedure.getResultList();
         
        //if (cursor != null && !cursor.isEmpty()) {
            response.setParameterValuePojos(new ArrayList<>());
            for (Object[] object : items) {

                int pos = -1;
                String parameterConditionUen = (String) object[++pos];
                Integer idParameterUen = Integer.valueOf(object[++pos].toString());
                Integer idParameterValue = Integer.valueOf(object[++pos].toString());
                String parameterValue = (String) object[++pos];
                String parameterProperty = (String) object[++pos];
                String parameterCondition = (String) object[++pos];

                response.setIdUen(idUen);
                response.setIdParameterUen(idParameterUen);
                response.setParameterCondition(parameterConditionUen);

                NvcTblParameterValuePojo value = new NvcTblParameterValuePojo();
                value.setIdParameterValue(idParameterValue);
                value.setIdParameterUen(idParameterUen);
                value.setParameterValue(parameterValue);
                value.setParameterProperty(parameterProperty);
                value.setParameterCondition(parameterCondition);
                response.getParameterValuePojos().add(value);
            }
        // }

        return response;
    }
    
    public NvcTblParameterValuePojo findParameterValueByName(Integer idUen, String parameter_name) {
        // LOG.debug(" **** findParameterByName *** idUen:" + idUen + " parameter_name:" + parameter_name);
        System.out.println("idUen: " +  idUen);
        System.out.println("parameter_name: " + parameter_name);
        NvcTblParameterValuePojo parameter = new NvcTblParameterValuePojo();
        try {
//            Puesto que el SP que se encuentra debajo produce error, ya que devuelve 2 columnas que se llaman igual, agrego directamente la query y no utilizo el SP
//            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("nvc_pkg_parameters_spx.find_values_by_parameter");
//            storedProcedure.registerStoredProcedureParameter("p_parameter_name", String.class, ParameterMode.IN);
//            storedProcedure.registerStoredProcedureParameter("p_id_uen", Integer.class, ParameterMode.IN);
//            storedProcedure.registerStoredProcedureParameter("message_out", String.class, ParameterMode.OUT);
//            storedProcedure.registerStoredProcedureParameter("cursor_out", void.class, ParameterMode.REF_CURSOR);
//
//            storedProcedure.setParameter("p_parameter_name", parameter_name);
//            storedProcedure.setParameter("p_id_uen", idUen);
//            storedProcedure.execute();
//
//            List<Object[]> cursor = (List<Object[]>) storedProcedure.getOutputParameterValue("cursor_out");

            String query = "select " +
                    "pv.ID_PARAMETER_VALUE, " +
                    "pv.ID_PARAMETER_UEN, " +
                    "pv.PARAMETER_VALUE, " +
                    "pv.PARAMETER_PROPERTY, " +
                    "pv.PARAMETER_CONDITION " +
                    "from nvc_tbl_parameter p, " +
                    "    nvc_tbl_parameter_uen pu, " +
                    "    nvc_tbl_parameter_value pv " +
                    "where p.id_parameter = pu.id_parameter " +
                    "and pu.id_uen = :idUen "+
                    "and p.parameter_name = :parameterName " +
                    "and pu.id_parameter_uen = pv.id_parameter_uen";

            List<Object[]> cursor = (List<Object[]>) em.createNativeQuery(query)
                    .setParameter("idUen", idUen)
                    .setParameter("parameterName", parameter_name)
                    .getResultList();

            if (cursor != null && !cursor.isEmpty()) {
                for (Object[] object : cursor) {
                    int pos = -1;
                    Integer idParameterValue = Integer.valueOf(object[++pos].toString());
                    Integer idParameterUen = Integer.valueOf(object[++pos].toString());
                    String parameterValue = (String) object[++pos];
                    String parameterProperty = (String) object[++pos];
                    String parameterCondition = (String) object[++pos];
                    parameter.setIdParameterValue(idParameterValue);
                    parameter.setIdParameterUen(idParameterUen);
                    parameter.setParameterValue(parameterValue);
                    parameter.setParameterProperty(parameterProperty);
                    parameter.setParameterCondition(parameterCondition);
                }
            }
            System.out.println("linea 1216, parameter value: " + parameter.getParameterValue());
        } catch (Exception e) {
            // LOG.debug("error: " + e.getMessage());
            System.out.println("error en findParameterValueByName()");
            e.printStackTrace();
        }

        return parameter;
    }
    
    public Boolean checkParametroPorFuente(
            Integer idUen,
            ConstantsUtils.PARAMETROS_CONFIGURACION parameter,
            String fuente,
            Integer idFamilia
    ) {
        // LOG.debug(" **** checkParametroPorFuente *** idUen:" + idUen + " parameter:" + parameter.getNombreParametro() + " fuente:" + fuente + " idFamilia:" + idFamilia );
        NvcTblParameterUenPojo parameterUenPojo = findParameterUenByUenName(idUen, parameter.getNombreParametro());
        boolean parameterCondition = false;
        if (parameterUenPojo != null && parameterUenPojo.getParameterCondition() !=null && parameterUenPojo.getParameterCondition().equals("Y")) {
            for (NvcTblParameterValuePojo parameterValuePojo : parameterUenPojo.getParameterValuePojos()) {
                parameterCondition = (parameterValuePojo.getParameterValue() != null && parameterValuePojo.getParameterValue().equals("Y")
                        && parameterValuePojo.getParameterProperty() != null
                        && parameterValuePojo.getParameterProperty().equals(fuente));
                if (parameterCondition) {

                    List<FamiliasPorAprobador> aprobadores
                            = em.createNamedQuery("FamiliasPorAprobador.findByIdFamilia", FamiliasPorAprobador.class)
                                    .setParameter("idFamilia", idFamilia)
                                    .setParameter("idUen", idUen)
                                    .setParameter("tipoAprobacion",
                                            parameter.getNombreParametro()
                                                    .equals(ConstantsUtils.PARAMETROS_CONFIGURACION.SPX_PREAPROBACION_FINANZAS.getNombreParametro())
                                            ? "FINANZAS" : "FAMILIAS")
                                    .getResultList();
                    parameterCondition = aprobadores != null && !aprobadores.isEmpty();
                    break;
                }
            }
        }
        // LOG.debug(" **** checkParametroPorFuente *** parameterCondition:" + parameterCondition);
        return parameterCondition;
    }
    
    private String get_Family_Aproval(Integer idFamilia, Integer idUen, String fuente, String parametro) throws Exception {
        log.debug(" **** get_Family_Aproval *** idFamilia:" + idFamilia + " idUen:" + idUen + " fuente:" + fuente + " parametro:" + parametro);
        String userFam = null;
//        try {
        StoredProcedureQuery procedure = em.createStoredProcedureQuery("get_approval_by_family");
        procedure.registerStoredProcedureParameter("p_id_uen", Integer.class, ParameterMode.IN);
        procedure.registerStoredProcedureParameter("p_id_familia", Integer.class, ParameterMode.IN);
        procedure.registerStoredProcedureParameter("p_fuente", String.class, ParameterMode.IN);
        procedure.registerStoredProcedureParameter("p_parametro", String.class, ParameterMode.IN);
        procedure.registerStoredProcedureParameter("approval_out", String.class, ParameterMode.OUT);
        procedure.registerStoredProcedureParameter("message_out", String.class, ParameterMode.OUT);

        procedure.setParameter("p_id_uen", idUen);
        procedure.setParameter("p_id_familia", idFamilia);
        procedure.setParameter("p_parametro", parametro);
        procedure.setParameter("p_fuente", fuente);
        boolean isOK = procedure.execute();

        String message = (String) procedure.getOutputParameterValue("message_out");
        if (message.equals(ConstantsUtils.SPX_RESPONSE_CODE.EXECUTED_OK.getResponseCode())) {
            userFam = (String) procedure.getOutputParameterValue("approval_out");
            userFam = userFam != null && !userFam.trim().isEmpty() ? userFam : null;
        } else {
            throw new Exception(message);
        }
        if (false) {
            userFam = (String) em.createNativeQuery("SELECT fn_getFamilyAproval(?1, ?2) FROM DUAL")
                    .setParameter("1", idFamilia)
                    .setParameter("2", idUen)
                    .getSingleResult();
            userFam = userFam != null && !userFam.trim().isEmpty() ? userFam : null;
        }
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//        }
//        log.debug("*** get_Family_Aproval *** idFamilia > " + idFamilia + " idUen " + idUen + " userFam > " + userFam);
        return userFam;
    }
    
    private List<RequisicionPojo> generaPreviewInterUen(List<NvcTblCarroCompraPojo> carroCompraPojoList, RequisicionRequest request) {
        log.debug("*** generapreviewRequisiciones *** carroCompraPojoList > ");
        java.util.Map<String, RequisicionPojo> mapRequis = new java.util.HashMap<>();
        String key;
        NvcTblOaProveedoresHResponse responseProv;
        /* <RELEASE ARGENTINA> */
        try {

            int numPartida;
            Usuario requisitor;
            Usuario requisitorDefault = em.createNamedQuery("Usuario.findByIdUsuario", Usuario.class)
                    .setParameter("1", request.getUser().getIdUsuario())
                    .getSingleResult();

            Integer idProyecto;
            Integer idCentroCosto;
            Integer idUen;
            Integer idSubfamilia;
            DetalleDeRequisicionPojo detalleDeRequisicionPojo = null;
            /* <FAD> */
            Integer idFad;
            /* </FAD> */

            for (NvcTblCarroCompraPojo carroCompraPojo : carroCompraPojoList) {
                if (carroCompraPojo.getVfIdRequisitor() == null) {
                    requisitor = requisitorDefault;
                } else {
                    requisitor = em.createNamedQuery("Usuario.findByIdUsuario", Usuario.class)
                            .setParameter("1", carroCompraPojo.getVfIdRequisitor())
                            .getSingleResult();

                }
                RequisicionPojo requisicionPojo = new RequisicionPojo();
                idUen = carroCompraPojo.getIdUen();

                /*<FAD>*/
                idFad = carroCompraPojo.getIdFad() == null ? 0 : carroCompraPojo.getIdFad();
                /*</FAD>*/

                OaUens organization = em.createNamedQuery("OaUens.findByOrganizationId", OaUens.class)
                        .setParameter("organizationId", idUen)
                        .getSingleResult();

                requisicionPojo.setFechaRequisicion(new Date());
                requisicionPojo.setRequisitor(requisitor.getId());
                requisicionPojo.setNombreRequisitor(requisitor.getNombreUsuario());
                requisicionPojo.setAppOrigen(request.getAppOrigen());
                requisicionPojo.setFuente(ConstantsUtils.FUENTES.INTERUEN.getFuente());
                requisicionPojo.setIdProveedor(carroCompraPojo.getIdProveedor());
                requisicionPojo.setIdSiteProveedor(carroCompraPojo.getVfVendorSiteId());
                responseProv = getProveedorById(requisicionPojo.getIdProveedor());
                requisicionPojo.setNombreProveedor(responseProv.getEntity().getNombreProveedor());
                requisicionPojo.setNombreSite(getNombreSiteById(requisicionPojo.getIdSiteProveedor()));
                requisicionPojo.setTipo(ConstantsUtils.TIPO_REQUISICIONES.EXTERNA.getTipo());
                requisicionPojo.setEstatus(ConstantsUtils.ESTATUS.ESTATUS_POR_APROBAR.getDescEstatus());
                requisicionPojo.setIdUen(idUen);
                String datosDeAuditoria
                        = ConstantsUtils.formattDate_DD_MM_YYYY(new Date())
                                .concat("-")
                                .concat(request.getUser().getIdUsuario())
                                .concat("-")
                                .concat("Generacion");
                requisicionPojo.setDatosDeAuditoria(datosDeAuditoria);

                ParametrosPorUenCia vSpx = em.createNamedQuery(
                        "ParametrosPorUenCia.findByIdUenIdParametro", ParametrosPorUenCia.class)
                        .setParameter("idUen", idUen)
                        .setParameter("idParametro", 47).getSingleResult();
                requisicionPojo.setSpx(vSpx.getValor().equals("1") ? "Y" : null);

                requisicionPojo.setDetalleDeRequisicionPojos(new ArrayList<>());

                NvcTblCarroCompra eCarroCompra = em.createNamedQuery(
                        "NvcTblCarroCompra.findByIdCarroCom", NvcTblCarroCompra.class)
                        .setParameter("idCarroCompra", carroCompraPojo.getIdCarroCompra())
                        .getSingleResult();

                //<R41223>
                //<R41223>
                NvcTblCarroCompraDetallePojo carroCompraDetalle = carroCompraPojo.getListDetalleCompra().isEmpty() ? null : carroCompraPojo.getListDetalleCompra().get(0);
                if (carroCompraDetalle != null) {
                    idProyecto = carroCompraDetalle.getIdProyecto() == null ? 0 : carroCompraDetalle.getIdProyecto();
                    idCentroCosto = carroCompraDetalle.getIdCentroCosto() == null ? 0 : carroCompraDetalle.getIdCentroCosto();
                    idSubfamilia = eCarroCompra.getIdSubfamilia();
//                    if(idProyecto == 0 && idCentroCosto == 0) {
//                        throw new Exception(" Error: Proyecto / Centro Costos requerido");
//                    }
                    String processOK = "OK";
                    try {
                        detalleDeRequisicionPojo = new DetalleDeRequisicionPojo();
                        String idCentroCostoMultCc = aplicarMultiCc(carroCompraPojo, detalleDeRequisicionPojo, carroCompraDetalle, idSubfamilia, idFad, ConstantsUtils.FUENTE_REQUISICION.FUENTE_INTERUEN.getFuente());
                        detalleDeRequisicionPojo.setIdCarroCompra(eCarroCompra.getIdCarroCompra());
                        detalleDeRequisicionPojo.setEstatus(ConstantsUtils.ESTATUS.ESTATUS_POR_APROBAR.getDescEstatus());

                        //<R41223>
                        siguienteAprobador(detalleDeRequisicionPojo, carroCompraDetalle, carroCompraPojo, idProyecto, idUen, idSubfamilia, idFad, ConstantsUtils.FUENTE_REQUISICION.FUENTE_INTERUEN.getFuente(), requisicionPojo, false);
                        List<ParametrosPorUenCia> parametros = em.createNamedQuery("ParametrosPorUenCia.findByIdUenNombreParametro", ParametrosPorUenCia.class)
                                .setParameter("nombreParametro", ConstantsUtils.PARAMETROS_CONFIGURACION.ASIGNACION_COMPRADOR.getNombreParametro())
                                .setParameter("idUen", idUen)
                                .getResultList();
                        String vTipo = parametros != null && !parametros.isEmpty() ? parametros.get(0).getValor() : "CARGA";
                        // RMN 13677 APOJR11028 //
                        Usuario comprador;
                        if (idProyecto > 0) {

                            comprador = em.createNamedQuery("Usuario.findByIdUsuario", Usuario.class)
                                    .setParameter("1", carroCompraDetalle.getComprador())
                                    .getSingleResult();/// eprocura 
                        } else {
                            comprador = requisitorDefault;
                        }
                        detalleDeRequisicionPojo.setIdCentroCosto(idCentroCosto);
                        detalleDeRequisicionPojo.setComprador(comprador.getId());
                        detalleDeRequisicionPojo.setNombreComprador(comprador.getNombreUsuario());
                        //<R20442>
                        detalleDeRequisicionPojo.setDescripcion(getDescripcionRequisicion(carroCompraPojo, request.getUser().getIdioma(), 250));
                        //</R20442>
                        detalleDeRequisicionPojo.setUom(eCarroCompra.getUdm());
                        detalleDeRequisicionPojo.setIdSubfamilia(carroCompraPojo.getIdSubfamilia());
                        detalleDeRequisicionPojo.setFuente(ConstantsUtils.FUENTES.INTERUEN.getFuente());
                        Date fechaRequerida = ConstantsUtils.sdf_dd_mm_yyyy.parse(carroCompraPojo.getFechaNecesidad());
                        detalleDeRequisicionPojo.setStrFechaNecesidad(carroCompraPojo.getFechaNecesidad());
                        detalleDeRequisicionPojo.setFechaNecesidad(fechaRequerida);
                        detalleDeRequisicionPojo.setIdMoneda(carroCompraPojo.getIdMoneda());
                        detalleDeRequisicionPojo.setMontoExtendido(carroCompraPojo.getPrecioUnitario() * carroCompraPojo.getCantidad());
                        detalleDeRequisicionPojo.setPrecio(carroCompraPojo.getPrecioUnitario());
                        detalleDeRequisicionPojo.setCantidad(carroCompraPojo.getCantidad());
                        detalleDeRequisicionPojo.setFechaNecesidad(fechaRequerida);
                        detalleDeRequisicionPojo.setTipoRecibo(carroCompraPojo.getIdTipoRecibo());
                        detalleDeRequisicionPojo.setIdLocalizacion(carroCompraPojo.getVfIdEnviarA());
                        detalleDeRequisicionPojo.setUrgente(carroCompraPojo.getRazonUrgenciaDes() == null || carroCompraPojo.getRazonUrgenciaDes().isEmpty() ? null : "S");
                        detalleDeRequisicionPojo.setRazonUrgencia(carroCompraPojo.getRazonUrgenciaDes());
                        detalleDeRequisicionPojo.setCantidad(carroCompraPojo.getCantidad());
                        if (!(detalleDeRequisicionPojo.getIdTipoCargo() != null && detalleDeRequisicionPojo.getIdTipoCargo().equals(ConstantsUtils.CUENTA_MULTI_CENTRO_COSTO))) {
                            detalleDeRequisicionPojo.setIdCuenta(carroCompraDetalle.getIdCuenta());
                            detalleDeRequisicionPojo.setSegmento1(carroCompraDetalle.getSegmento1());
                            detalleDeRequisicionPojo.setSegmento2(carroCompraDetalle.getSegmento2());
                            detalleDeRequisicionPojo.setSegmento3(carroCompraDetalle.getSegmento3());
                            detalleDeRequisicionPojo.setSegmento4(carroCompraDetalle.getSegmento4());
                            detalleDeRequisicionPojo.setSegmento5(carroCompraDetalle.getSegmento5());
                            detalleDeRequisicionPojo.setSegmento6(carroCompraDetalle.getSegmento6());
                            detalleDeRequisicionPojo.setSegmento7(carroCompraDetalle.getSegmento7());
                            detalleDeRequisicionPojo.setSegmento8(carroCompraDetalle.getSegmento8());
                            detalleDeRequisicionPojo.setIdIva(carroCompraPojo.getVfIvaId());
                        }

                        String notasAlComprador = eCarroCompra.getComentariosComprador() == null ? "" : eCarroCompra.getComentariosComprador();
                        notasAlComprador = eCarroCompra.getRazonSpot() == null ? notasAlComprador.concat("") : notasAlComprador.concat("-").concat(eCarroCompra.getRazonSpot());
                        log.debug("*** generaPreviewInterUen *** notasComprador > " + notasAlComprador);
                        detalleDeRequisicionPojo.setNotasComprador(notasAlComprador);
                        detalleDeRequisicionPojo.setTipoGasto(carroCompraDetalle.getTipoGasto());

                        if (idProyecto > 0) {
                            key = idProyecto.toString().concat(requisicionPojo.getRequisitor())
                                    .concat(requisicionPojo.getIdProveedor().toString())
                                    .concat(requisicionPojo.getIdSiteProveedor().toString())
                                    .concat(detalleDeRequisicionPojo.getIdMoneda())
                                    .concat(detalleDeRequisicionPojo.getIdLocalizacion().toString())
                                    .concat(carroCompraPojo.getVfIdFacturacion().toString());
                            if (mapRequis.containsKey(key)) {
                                numPartida = mapRequis.get(key).getDetalles().size();
                            } else {
                                mapRequis.put(key, requisicionPojo);
                                numPartida = 0;
                            }
                            detalleDeRequisicionPojo.setIdPartida(++numPartida);
                            mapRequis.get(key).getDetalles().add(detalleDeRequisicionPojo);
                        }

                        if (idCentroCosto > 0) {
                            // <RELEASE ARGENTINA>
                            //<RM16773>
                            boolean compradorPorFamilia = (
                                    organization.getCompradorFamilia() != null
                                    && organization.getCompradorFamilia().intValue() == 1);
                            key = compradorPorFamilia
                                    ? idCentroCostoMultCc.concat(comprador.getId())
                                            .concat(requisicionPojo.getRequisitor())
                                            .concat(requisicionPojo.getIdProveedor().toString())
                                            .concat(requisicionPojo.getIdSiteProveedor().toString())
                                            .concat(detalleDeRequisicionPojo.getIdMoneda())
                                            .concat(detalleDeRequisicionPojo.getIdLocalizacion().toString())
                                            .concat(carroCompraPojo.getVfIdFacturacion().toString())
                                    : idCentroCostoMultCc
                                            .concat(requisicionPojo.getRequisitor())
                                            .concat(requisicionPojo.getIdProveedor().toString())
                                            .concat(requisicionPojo.getIdSiteProveedor().toString())
                                            .concat(detalleDeRequisicionPojo.getIdMoneda())
                                            .concat(detalleDeRequisicionPojo.getIdLocalizacion().toString())
                                            .concat(carroCompraPojo.getVfIdFacturacion().toString());
                            //</RM16773>
                            requisicionPojo.setIdCentroCostos(idCentroCosto);
                            detalleDeRequisicionPojo.setIdCentroCosto(idCentroCosto);
                            if (mapRequis.containsKey(key)) {
                                numPartida = mapRequis.get(key).getDetalles().size();
                            } else {
                                mapRequis.put(key, requisicionPojo);
                                numPartida = 0;
                            }
                            detalleDeRequisicionPojo.setIdPartida(++numPartida);
                            mapRequis.get(key).getDetalles().add(detalleDeRequisicionPojo);
                        }

                    } catch (Exception e) {
                        log.error(e.getMessage(), e);
                        processOK = e.getMessage();
                    }
                    if (!processOK.equals("OK")) {
                        if (detalleDeRequisicionPojo == null) {
                            detalleDeRequisicionPojo = new DetalleDeRequisicionPojo();
                        }
                        detalleDeRequisicionPojo.setMensajeAprobacion(processOK);
                        requisicionPojo.getDetalles().add(detalleDeRequisicionPojo);
                        log.debug(" **** generaPreviewSpot *** idCarroCompraDetalle:"
                                + carroCompraDetalle.getIdCarroCompraDetalle());
                        mapRequis.put("e_" + carroCompraDetalle.getIdCarroCompraDetalle(), requisicionPojo);
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        List<RequisicionPojo> requisicionPojos = new ArrayList<>();
        RequisicionPojo requiPojo;
        for (java.util.Iterator<String> itKeys = mapRequis.keySet().iterator(); itKeys.hasNext();) {
            String mapKey = itKeys.next();
            requiPojo = mapRequis.get(mapKey);
            requisicionPojos.add(requiPojo);
        }

//        for (java.util.Iterator<Integer> iteraSpot = mapProyectos.keySet().iterator(); iteraSpot.hasNext();) {
//            Integer idProyecto = iteraSpot.next();
//            requiPojo = mapProyectos.get(idProyecto);
//            requisicionPojos.add(requiPojo);
//        }
//
//        for (java.util.Iterator<String> iteraCC = mapCentroCostos.keySet().iterator(); iteraCC.hasNext();) {
//            String keyCC = iteraCC.next();
//            requiPojo = mapCentroCostos.get(keyCC);
//            requisicionPojos.add(requiPojo);
//        }
        return requisicionPojos;
    }
    
    private List<RequisicionPojo> generaPreviewSpot(List<NvcTblCarroCompraPojo> carroCompraPojoList, RequisicionRequest request) {
        log.debug("*** generaPreviewRequisiciones *** carroCompraPojoList > ");
//        java.util.Map<Integer, RequisicionPojo> mapProyectos = new java.util.HashMap<>();
//        java.util.Map<String, RequisicionPojo> mapCentroCostos = new java.util.HashMap<>();
        java.util.Map<String, RequisicionPojo> mapRequis = new java.util.HashMap<>();
        String key;
        /* <RELEASE ARGENTINA> */
        try {

            int numPartida = 0;
            Usuario requisitor = em.createNamedQuery("Usuario.findByIdUsuario", Usuario.class)
                    .setParameter("1", request.getUser().getIdUsuario())
                    .getSingleResult();

            Integer idProyecto;
            Integer idProveedor;
            Integer idCentroCosto;
            Integer idUen;
            Integer idSubfamilia;
            DetalleDeRequisicionPojo detalleDeRequisicionPojo = null;
            /* <FAD> */
            Integer idFad;
            /* </FAD> */

            for (NvcTblCarroCompraPojo carroCompraPojo : carroCompraPojoList) {
                RequisicionPojo requisicionPojo = new RequisicionPojo();

                if (carroCompraPojo.getPunchout() != null && carroCompraPojo.getPunchout() == 1) {
                    NvcTblCatalogoItem catalogoItem = em.createNamedQuery("NvcTblCatalogoItem.findByIdItem", NvcTblCatalogoItem.class)
                            .setParameter("idItem", carroCompraPojo.getIdItem()).getSingleResult();
                    requisicionPojo.setTipoRecibo(carroCompraPojo.getIdTipoRecibo());
                    NvcTblCatalogoLocalizacion catLocalizacion = em.createNamedQuery("NvcTblCatalogoLocalizacion.findByCatUenLocalizacion", NvcTblCatalogoLocalizacion.class)
                            .setParameter("idCatalogoUen", catalogoItem.getIdCatalogoUen())
                            .setParameter("idLocalizacion", carroCompraPojo.getIdlocalizacion())
                            .getSingleResult();

                    List<NvcTblCatalogoUenSite> catalogoUenSite = em.createNamedQuery("NvcTblCatalogoUenSite.findByIdLocalizacion", NvcTblCatalogoUenSite.class)
                            .setParameter("idCatalogoLocalizacion", catLocalizacion.getIdCatalogoLocalizacion())
                            .getResultList();

                    idProveedor = catalogoItem.getCatalogoUen().getCatalogo().getIdProveedor();
                    requisicionPojo.setIdProveedor(idProveedor);
                    requisicionPojo.setIdSiteProveedor(catalogoUenSite.get(0)
                            .getProveedorSites()
                            .getNvcTblProvSitesHPK()
                            .getIdSucursalProveedor()
                            .intValue()
                    );
                }

//                requisicionPojo.setIdCarroCompra(carroCompraPojo.getIdCarroCompra());
                /*<FAD>*/
                idFad = carroCompraPojo.getIdFad() == null ? 0 : carroCompraPojo.getIdFad();
                /*</FAD>*/
                idUen = carroCompraPojo.getIdUen();

                OaUens organization = em.createNamedQuery("OaUens.findByOrganizationId", OaUens.class)
                        .setParameter("organizationId", idUen)
                        .getSingleResult();

                requisicionPojo.setFechaRequisicion(new Date());
                requisicionPojo.setRequisitor(requisitor.getId());
                requisicionPojo.setNombreRequisitor(requisitor.getNombreUsuario());
                requisicionPojo.setAppOrigen(request.getAppOrigen());
                requisicionPojo.setFuente(ConstantsUtils.FUENTE_REQUISICION.FUENTE_SPOT.getFuente());
                requisicionPojo.setTipo(ConstantsUtils.TIPO_REQUISICIONES.EXTERNA.getTipo());
                requisicionPojo.setEstatus(ConstantsUtils.ESTATUS.ESTATUS_POR_COTIZAR.getDescEstatus());
                requisicionPojo.setIdUen(idUen);

                String datosDeAuditoria
                        = ConstantsUtils.formattDate_DD_MM_YYYY(new Date())
                                .concat("-")
                                .concat(request.getUser().getIdUsuario())
                                .concat("-")
                                .concat("Generacion");
                requisicionPojo.setDatosDeAuditoria(datosDeAuditoria);

                ParametrosPorUenCia vSpx = em.createNamedQuery(
                        "ParametrosPorUenCia.findByIdUenIdParametro", ParametrosPorUenCia.class)
                        .setParameter("idUen", idUen)
                        .setParameter("idParametro", 47).getSingleResult();
                requisicionPojo.setSpx(vSpx.getValor().equals("1") ? "Y" : null);

                requisicionPojo.setDetalleDeRequisicionPojos(new ArrayList<>());

                NvcTblCarroCompra eCarroCompra = em.createNamedQuery(
                        "NvcTblCarroCompra.findByIdCarroCom", NvcTblCarroCompra.class)
                        .setParameter("idCarroCompra", carroCompraPojo.getIdCarroCompra())
                        .getSingleResult();

                //<R41223>
                NvcTblCarroCompraDetallePojo carroCompraDetalle = carroCompraPojo.getListDetalleCompra().isEmpty() ? null : carroCompraPojo.getListDetalleCompra().get(0);
                if (carroCompraDetalle != null) {
                    idProyecto = carroCompraDetalle.getIdProyecto() == null ? 0 : carroCompraDetalle.getIdProyecto();
                    idCentroCosto = carroCompraDetalle.getIdCentroCosto() == null ? 0 : carroCompraDetalle.getIdCentroCosto();
                    idSubfamilia = eCarroCompra.getIdSubfamilia();
//                    if(idProyecto == 0 && idCentroCosto == 0) {
//                        throw new Exception(" Error: Proyecto / Centro Costos requerido");
//                    }
                    String processOK = "OK";
                    try {
                        detalleDeRequisicionPojo = new DetalleDeRequisicionPojo();
                        if (eCarroCompra.getPunchout() == 1) {
                            NvcTblCatalogoItemPunchout catalogoItemPo = em.createNamedQuery(
                                    "NvcTblCatalogoItemPunchout.findByIdItem", NvcTblCatalogoItemPunchout.class)
                                    .setParameter("idItem", carroCompraPojo.getIdItem()).getSingleResult();
                            detalleDeRequisicionPojo.setIdProducto(catalogoItemPo
                                    .getNvcTblCatalogoItemPunchoutPK()
                                    .getIdItemPunchout()
                                    .intValue()
                            );
                        }
                        String idCentroCostoMultCc = aplicarMultiCc(carroCompraPojo, detalleDeRequisicionPojo, carroCompraDetalle, idSubfamilia, idFad, ConstantsUtils.FUENTE_REQUISICION.FUENTE_SPOT.getFuente());
                        detalleDeRequisicionPojo.setIdCarroCompra(eCarroCompra.getIdCarroCompra());
                        detalleDeRequisicionPojo.setEstatus(ConstantsUtils.ESTATUS.ESTATUS_POR_COTIZAR.getDescEstatus());

                        //<R41223>
                        //Siguiente aprobador
                        siguienteAprobador(detalleDeRequisicionPojo, carroCompraDetalle, carroCompraPojo, idProyecto, idUen, idSubfamilia, idFad, ConstantsUtils.FUENTE_REQUISICION.FUENTE_SPOT.getFuente(), requisicionPojo, false);
                        List<ParametrosPorUenCia> parametros = em.createNamedQuery("ParametrosPorUenCia.findByIdUenNombreParametro", ParametrosPorUenCia.class)
                                .setParameter("nombreParametro", ConstantsUtils.PARAMETROS_CONFIGURACION.ASIGNACION_COMPRADOR.getNombreParametro())
                                .setParameter("idUen", idUen)
                                .getResultList();
                        String vTipo = parametros != null && !parametros.isEmpty() ? parametros.get(0).getValor() : "CARGA";

                        log.debug("=============carroCompraPojo.getPedidoEspecial():" + carroCompraPojo.getPedidoEspecial());
                        // RMN 13677 APOJR11028 //
                        Usuario comprador;
                        if (carroCompraPojo.getPedidoEspecial() != null && carroCompraPojo.getPedidoEspecial().equals(1)) {
                            log.debug("===========el almacen: " + carroCompraPojo.getIdAlmacen() ); //compxorg
                            //compxorg
                            comprador = calculaCompradorDeAlmacen(carroCompraPojo.getIdAlmacen(), idUen);
                            
                            if(comprador == null){
                                comprador = em.createNamedQuery("Usuario.findByIdUsuario", Usuario.class)
                                        .setParameter("1", "JMART1")
                                        .getSingleResult();
                            }
                        } else {
                            if (idProyecto > 0) {
                                comprador = em.createNamedQuery("Usuario.findByIdUsuario", Usuario.class)
                                        .setParameter("1", carroCompraDetalle.getComprador())
                                        .getSingleResult();
                            } else {
                                // CALCULAMOS EL COMPRADOR
                                if (carroCompraPojo.getListDetalleCompra().size() > 1) {
                                    comprador = em.createNamedQuery("Usuario.findByIdUsuario", Usuario.class)
                                            .setParameter("1", carroCompraPojo.getResponsableMultiCc())
                                            .getSingleResult();
                                } else {
                                    comprador = calculaComprador(organization, idSubfamilia, vTipo, idCentroCosto, idProyecto);
                                }

                                if (comprador == null) {
                                    throw new Exception(
                                            "Error: No existe un comprador configurado para la familia " + idSubfamilia
                                            + " en la Uen " + organization.getOrganizationName()
                                    );
                                }
                            }
                        }
                        // FIN RMN 13677 //

                        detalleDeRequisicionPojo.setIdCentroCosto(idCentroCosto);
                        detalleDeRequisicionPojo.setComprador(comprador.getId());
                        detalleDeRequisicionPojo.setNombreComprador(comprador.getNombreUsuario());
                        //<R20442>
                        if (eCarroCompra.getPunchout() == 1) {
                            detalleDeRequisicionPojo.setDescripcion(carroCompraPojo.getNumeroParteProveedor().concat(" - ").concat(getDescripcionRequisicion(carroCompraPojo, request.getUser().getIdioma(), 230)));
                        } else {
                            detalleDeRequisicionPojo.setDescripcion(getDescripcionRequisicion(carroCompraPojo, request.getUser().getIdioma(), 250));
                        }
                        //</R20442>
                        detalleDeRequisicionPojo.setUom(eCarroCompra.getUdm());
                        detalleDeRequisicionPojo.setIdSubfamilia(carroCompraPojo.getIdSubfamilia());
                        detalleDeRequisicionPojo.setFuente(ConstantsUtils.FUENTE_REQUISICION.FUENTE_SPOT.getFuente());
                        Date fechaRequerida = ConstantsUtils.sdf_dd_mm_yyyy.parse(carroCompraPojo.getFechaNecesidad());
                        detalleDeRequisicionPojo.setStrFechaNecesidad(carroCompraPojo.getFechaNecesidad());
                        detalleDeRequisicionPojo.setFechaNecesidad(fechaRequerida);

                        detalleDeRequisicionPojo.setUrgente(carroCompraPojo.getRazonUrgenciaDes() == null || carroCompraPojo.getRazonUrgenciaDes().isEmpty() ? null : "S");
                        detalleDeRequisicionPojo.setRazonUrgencia(carroCompraPojo.getRazonUrgenciaDes());
                        detalleDeRequisicionPojo.setCantidad(carroCompraPojo.getCantidad());

                        if (!(detalleDeRequisicionPojo.getIdTipoCargo() != null && detalleDeRequisicionPojo.getIdTipoCargo().equals(ConstantsUtils.CUENTA_MULTI_CENTRO_COSTO))) {
                            detalleDeRequisicionPojo.setIdCuenta(carroCompraDetalle.getIdCuenta());
                            detalleDeRequisicionPojo.setSegmento1(carroCompraDetalle.getSegmento1());
                            detalleDeRequisicionPojo.setSegmento2(carroCompraDetalle.getSegmento2());
                            detalleDeRequisicionPojo.setSegmento3(carroCompraDetalle.getSegmento3());
                            detalleDeRequisicionPojo.setSegmento4(carroCompraDetalle.getSegmento4());
                            detalleDeRequisicionPojo.setSegmento5(carroCompraDetalle.getSegmento5());
                            detalleDeRequisicionPojo.setSegmento6(carroCompraDetalle.getSegmento6());
                            detalleDeRequisicionPojo.setSegmento7(carroCompraDetalle.getSegmento7());
                            detalleDeRequisicionPojo.setSegmento8(carroCompraDetalle.getSegmento8());
                        }

                        String notasAlComprador = eCarroCompra.getComentariosComprador() == null ? "" : eCarroCompra.getComentariosComprador();
                        notasAlComprador = eCarroCompra.getRazonSpot() == null ? notasAlComprador.concat("") : notasAlComprador.concat("-").concat(eCarroCompra.getRazonSpot());
                        log.debug("*** generaPreviewSpot *** notasComprador > " + notasAlComprador);
                        detalleDeRequisicionPojo.setNotasComprador(notasAlComprador);
                        detalleDeRequisicionPojo.setTipoGasto(carroCompraDetalle.getTipoGasto());
                        /* <FAD> /*/
                        if (idProyecto > 0) {
                            boolean isFad = (idFad > 0);
                            key = isFad ? idProyecto.toString().concat(idFad.toString()) : idProyecto.toString().concat(comprador.getId());
                            
                            //compxorg
                            if (carroCompraPojo.getPedidoEspecial() != null && carroCompraPojo.getPedidoEspecial().equals(1)) {
                                key = idProyecto.toString().concat(comprador.getId());
                            }
                            
                            if (mapRequis.containsKey(key)) {
                                numPartida = mapRequis.get(key).getDetalles().size();
                            } else {
                                mapRequis.put(key, requisicionPojo);
                                numPartida = 0;
                            }
                            detalleDeRequisicionPojo.setIdPartida(++numPartida);
                            mapRequis.get(key).getDetalles().add(detalleDeRequisicionPojo);
                        }

                        if (idCentroCosto > 0) {
                            // <RELEASE ARGENTINA>
                            //<RM16773>
                            boolean compradorPorFamilia = (
                                    organization.getCompradorFamilia() != null
                                    && organization.getCompradorFamilia().intValue() == 1);
                            boolean isFad = (idFad > 0);
                            key = compradorPorFamilia
                                    ? (isFad
                                        ? idCentroCostoMultCc.concat(comprador.getId().concat(idFad.toString()))
                                        : idCentroCostoMultCc.concat(comprador.getId()))
                                    : (isFad
                                        ? idCentroCostoMultCc.concat(idFad.toString())
                                        : idCentroCostoMultCc);

                            //compxorg
                            if (carroCompraPojo.getPedidoEspecial() != null && carroCompraPojo.getPedidoEspecial().equals(1)) {
                                key = idCentroCostoMultCc.concat(comprador.getId());
                            }
                            
                            //</RM16773>
                            requisicionPojo.setIdCentroCostos(idCentroCosto);
                            detalleDeRequisicionPojo.setIdCentroCosto(idCentroCosto);
                            if (mapRequis.containsKey(key)) {
                                numPartida = mapRequis.get(key).getDetalles().size();
                            } else {
                                mapRequis.put(key, requisicionPojo);
                                numPartida = 0;
                            }
                            detalleDeRequisicionPojo.setIdPartida(++numPartida);
                            mapRequis.get(key).getDetalles().add(detalleDeRequisicionPojo);
                        }
                    } catch (Exception e) {
                        log.error(e.getMessage(), e);
                        processOK = e.getMessage();
                    }
                    if (!processOK.equals("OK")) {
                        if (detalleDeRequisicionPojo == null) {
                            detalleDeRequisicionPojo = new DetalleDeRequisicionPojo();
                        }
                        detalleDeRequisicionPojo.setMensajeAprobacion(processOK);
                        requisicionPojo.getDetalles().add(detalleDeRequisicionPojo);
                        log.debug(" **** generaPreviewSpot *** idCarroCompraDetalle:" + carroCompraDetalle.getIdCarroCompraDetalle());
                        mapRequis.put("e_" + carroCompraDetalle.getIdCarroCompraDetalle(), requisicionPojo);
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        List<RequisicionPojo> requisicionPojos = new ArrayList<>();
        RequisicionPojo requiPojo;
        for (java.util.Iterator<String> itKeys = mapRequis.keySet().iterator(); itKeys.hasNext();) {
            String mapKey = itKeys.next();
            requiPojo = mapRequis.get(mapKey);
            requisicionPojos.add(requiPojo);
        }
        return requisicionPojos;
    }
    
    private Usuario calculaComprador(OaUens organization, Integer idSubfamilia, String vTipo, Integer idCentroCosto, Integer idProyecto) {
        Usuario comprador = null;
        Integer idUen = organization.getOrganizationId().intValue();
        try {
            /*REVISAMOS SI LA UEN ESTA CONFIGURADA PARA COMPRADOR POR FAMILIA Y ES DE CC*/

            //<RELEASE ARG>
            String strComprador = null;
            if (organization.getCompradorFamilia() != null && organization.getCompradorFamilia().intValue() == 1
                    && (idCentroCosto != null && idCentroCosto > 0)) {
                //</RELEASE ARG>    

                List<DcCompradorFamilia> compradores = em.createNamedQuery("DcCompradorFamilia.findByUenFamilia", DcCompradorFamilia.class)
                        .setParameter("idUen", idUen)
                        .setParameter("idFamilia", idSubfamilia)
                        .getResultList();

                if (compradores.isEmpty()) {
                    NvcTblProcesosAprobacion procesoAprobacion = em.createNamedQuery("NvcTblProcesosAprobacion.findByDescripcion", NvcTblProcesosAprobacion.class)
                            .setParameter("descripcion", "SPX_ADMIN_COMPRADORES")
                            .getSingleResult();

                    NvcTblUenUsuAprobacion usuarioAprobacion = em.createNamedQuery(
                            "NvcTblUenUsuAprobacion.findByUenProceso", NvcTblUenUsuAprobacion.class)
                            .setParameter("1", idUen)
                            .setParameter("2", procesoAprobacion.getIdProcesoAprobacion())
                            .setParameter("3", 0)
                            .getSingleResult();
                    strComprador = usuarioAprobacion.getIdUsuario();
                } else {
                    // OBTENEMOS EL 1er COMPRADOR ASIGNADO A LA FAMILIA
                    strComprador = compradores.get(0).getIdUsuario();
                }
            } else {
                /* CALCULAMOS EL COMPRADOR POR CC*/
                List<CcPorComprador> compradores = em.createNamedQuery(
                        "CcPorComprador.findByIdCCUen", CcPorComprador.class)
                        .setParameter("idUen", idUen)
                        .setParameter("idCc", idCentroCosto)
                        .getResultList();
                if (compradores.isEmpty()) {
                    NvcTblProcesosAprobacion procesoAprobacion = em.createNamedQuery(
                            "NvcTblProcesosAprobacion.findByDescripcion", NvcTblProcesosAprobacion.class)
                            .setParameter("descripcion", "SPX_ADMIN_COMPRADORES")
                            .getSingleResult();
                    NvcTblUenUsuAprobacion usuarioAprobacion = em.createNamedQuery(
                            "NvcTblUenUsuAprobacion.findByUenProceso", NvcTblUenUsuAprobacion.class)
                            .setParameter("1", idUen)
                            .setParameter("2", procesoAprobacion.getIdProcesoAprobacion())
                            .setParameter("3", 0)
                            .getSingleResult();
                    strComprador = usuarioAprobacion.getIdUsuario();
                } else {
                    strComprador = compradores.get(0).getIdUsuario().getId();
                }

            }
            comprador = em.createNamedQuery("Usuario.findByIdUsuario", Usuario.class)
                    .setParameter("1", strComprador)
                    .getSingleResult();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return comprador;
    }
    
    private Usuario calculaCompradorDeAlmacen(Integer idAlmacen, Integer idUen){
        Usuario comprador = null;
        String strComprador;
        try{
            AlmacenComprador almacenComprador = em.createNamedQuery(
                    "AlmacenComprador.findByIdAlmacen", AlmacenComprador.class)
                        .setParameter("idAlmacen", idAlmacen)
                        .getSingleResult();
                if (almacenComprador == null) {
                    NvcTblProcesosAprobacion procesoAprobacion = em.createNamedQuery(
                            "NvcTblProcesosAprobacion.findByDescripcion", NvcTblProcesosAprobacion.class)
                            .setParameter("descripcion", "SPX_ADMIN_COMPRADORES")
                            .getSingleResult();
                    NvcTblUenUsuAprobacion usuarioAprobacion = em.createNamedQuery(
                            "NvcTblUenUsuAprobacion.findByUenProceso", NvcTblUenUsuAprobacion.class)
                            .setParameter("1", idUen)
                            .setParameter("2", procesoAprobacion.getIdProcesoAprobacion())
                            .setParameter("3", 0)
                            .getSingleResult();
                    strComprador = usuarioAprobacion.getIdUsuario();
   
                }else{
                    strComprador = almacenComprador.getIdUsuario();
                }
                
               comprador = em.createNamedQuery("Usuario.findByIdUsuario", Usuario.class)
                    .setParameter("1", strComprador)
                    .getSingleResult();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
                
        return comprador;
    }
    
    private Usuario get_proyectowner(Integer idProyecto) {
        Usuario approver = null;
        try {
            String slApprover = (String) em.createNativeQuery(
                    "SELECT get_proyectowner(?1) FROM DUAL")
                    .setParameter("1", idProyecto)
                    .getSingleResult();
            
            if (slApprover != null)//<T378733>
            {
                approver = usersRepo.getOneById(slApprover);
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return approver;
    }
    
    public String getProcessResponsible(NvcTblFadRequest request) {
        log.debug(" **** getProcessResponsible *** ");
        String responsable = null;
        String messageOut = "OK";
        try {
            StoredProcedureQuery stored = em.createStoredProcedureQuery("nvc_pkg_spx_fad.get_admin_process_by_id_carro");
            stored.registerStoredProcedureParameter("p_id_carro_compra", Integer.class, ParameterMode.IN);
            stored.registerStoredProcedureParameter("responsable", String.class, ParameterMode.OUT);
            stored.registerStoredProcedureParameter("message_out", String.class, ParameterMode.OUT);
            stored.registerStoredProcedureParameter("value_out", Integer.class, ParameterMode.OUT);
            stored.setParameter("p_id_carro_compra", request.getEntity().getIdCarroCompra());
            stored.execute();
            messageOut = (String) stored.getOutputParameterValue("message_out");
            responsable = (String) stored.getOutputParameterValue("responsable");
            log.debug(" **** getProcessResponsible *** messageOut:" + messageOut + " responsable:" + responsable);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        log.debug(" **** getProcessResponsible *** " + responsable);
        return responsable;
    }
    
    public Usuario get_Respcc(Integer idUen, Integer idCentroCosto) {
        log.debug("*** get_Respcc *** idUen > " + idUen + " idCentroCosto > " + idCentroCosto);
        Usuario approver = null;
        try {
            String strApprover = (String) em.createNativeQuery("SELECT fn_get_Respcc(?1, ?2) FROM DUAL")
                    .setParameter("1", idUen)
                    .setParameter("2", idCentroCosto)
                    .getSingleResult();
            approver = usersRepo.getOneById(strApprover);
        } catch (Exception e) {
            log.error("Sin responsable");
        }
        return approver;
    }
    
    private void siguienteAprobador(
            DetalleDeRequisicionPojo detalleDeRequisicionPojo,
            NvcTblCarroCompraDetallePojo carroCompraDetalle,
            NvcTblCarroCompraPojo carroCompraPojo,
            Integer idProyecto,
            Integer idUen,
            Integer idSubfamilia,
            Integer idFad,
            String fuente,
            RequisicionPojo requisicionPojo,
            boolean isInterUen
    ) {
        Usuario siguientAprobador = null;
//                        String strAprobador = null;
        String aprobacionEspecial = null;
        log.debug("Inicia siguienteAprobador");
        try {
            if (isInterUen) {
                idUen = carroCompraPojo.getIdUenRequisitora();
                aprobacionEspecial = "I1";
            }
            if (idProyecto > 0) {
                //obetener parametro de PREAPROBACION PROYECTO        
                NvcTblParameterUenPojo parameter = findParameterUenByUenName(
                        idUen,
                        ConstantsUtils.PARAMETROS_CONFIGURACION.PREAPROBACION_PROYECTO.getNombreParametro()
                );
                              
                boolean isPreAprobacion = false;
                if (parameter != null && parameter.getParameterCondition() != null && parameter.getParameterCondition().equals("Y")) {
                    Optional optional = parameter.getParameterValuePojos().stream().filter(pv -> fuente.equals(pv.getParameterProperty())).findFirst();
                    isPreAprobacion = optional.isPresent() && "Y".equals(((NvcTblParameterValuePojo) optional.get()).getParameterValue());
                }
                if (!isInterUen) {
                    aprobacionEspecial = "P";
                } else {
                    aprobacionEspecial = "I2";
                }
                log.debug("PREAPROBACION: " + isPreAprobacion);
                if (isPreAprobacion) {
                    if (!ConstantsUtils.FUENTES.SPOT.getFuente().equals(fuente) && requisicionPojo != null) {//requisicionPojo!=null --> cuando es multi cc
                        requisicionPojo.setEstatus(ConstantsUtils.ESTATUS.ESTATUS_APROBACION_PROYECTO.getDescEstatus());
                        detalleDeRequisicionPojo.setEstatus(ConstantsUtils.ESTATUS.ESTATUS_APROBACION_PROYECTO.getDescEstatus());
                        detalleDeRequisicionPojo.setIdEstatus(58);

                        aprobacionEspecial = "PP"; //autoaprobacion
                    }
                    List<ProyectosPorAprobadorUen> aprobadoresProyecto = em.createNamedQuery("ProyectosPorAprobadorUen.ParaAprobacionProyecto")
                            .setParameter("idUen", idUen)
                            .setParameter("idProyecto", idProyecto)
                            .getResultList();

                    if (aprobadoresProyecto.isEmpty()) {
                        // BUSCAMOS EL PREAPROBADOR DEFAULT 
                        String preAprobadorDefault = null;
                        NvcTblParameterValuePojo paramv = findParameterValueByName(
                                idUen,
                                ConstantsUtils.PARAMETROS_CONFIGURACION
                                        .PREAPROBADOR_PROYECTO_DEFAULT
                                        .getNombreParametro()
                        );
                        System.out.println("null != paramv.getParameterValue() && !paramv.getParameterValue().isEmpty(): " + (null != paramv.getParameterValue() && !paramv.getParameterValue().isEmpty()));
                        if (null != paramv.getParameterValue() && !paramv.getParameterValue().isEmpty()) {
                            preAprobadorDefault = paramv.getParameterValue();
                        }
                        System.out.println("preAprobadorDefault: " + preAprobadorDefault);
                        siguientAprobador = em.createNamedQuery("Usuario.findByIdUsuario", Usuario.class)
                                .setParameter("1", preAprobadorDefault).getSingleResult();
                    } else {
                        siguientAprobador = em.createNamedQuery("Usuario.findByIdUsuario", Usuario.class).setParameter("1", aprobadoresProyecto.get(0).getIdUsuario()).getSingleResult();
//                                    strAprobador = siguientAprobador.getIdUsuario();//<T436410>
                    }
                } else {
                    //autoaprobacion
                     boolean preAprobacionFinanzas = checkParametroPorFuente(idUen,
                            ConstantsUtils.PARAMETROS_CONFIGURACION.SPX_PREAPROBACION_FINANZAS,
                            fuente,
                            idSubfamilia
                    );

                    boolean preAprobacionFamilias = checkParametroPorFuente(idUen,
                            ConstantsUtils.PARAMETROS_CONFIGURACION.SPX_PREAPROBACION_POR_FAMILIAS,
                            fuente,
                            idSubfamilia
                    );

                    if (preAprobacionFinanzas) {
                        /* hay preaprobacion finanzas encendido para este tipo de requisiciones */
                        String preAprFinanzas = get_Family_Aproval(idSubfamilia, idUen, fuente,
                                ConstantsUtils.PARAMETROS_CONFIGURACION.SPX_PREAPROBACION_FINANZAS.getNombreParametro());
//                                    strAprobador = preAprFinanzas;
                        aprobacionEspecial = "F1";
                        System.out.println("preAprFinanzas: " + preAprFinanzas);
                        siguientAprobador = em.createNamedQuery("Usuario.findByIdUsuario", Usuario.class).setParameter("1", preAprFinanzas).getSingleResult();
                    } else if (preAprobacionFamilias) {
                        /* hay preaprobacion por familia encendido para este tipo de requisiciones */
                        String preAprFamilias = get_Family_Aproval(idSubfamilia, idUen, fuente,
                                ConstantsUtils.PARAMETROS_CONFIGURACION.SPX_PREAPROBACION_POR_FAMILIAS.getNombreParametro());
//                                    strAprobador = preAprFamilias;mail

                        aprobacionEspecial = "E1";
                        siguientAprobador = em.createNamedQuery("Usuario.findByIdUsuario", Usuario.class).setParameter("1", preAprFamilias).getSingleResult();
                    }

                    if (preAprobacionFamilias && preAprobacionFinanzas) {
                        aprobacionEspecial = "F2";
                        /* va a pasar por las dos pre-aprobaciones */
                    } else if (!preAprobacionFamilias && !preAprobacionFinanzas) {
                        siguientAprobador = get_proyectowner(idProyecto);
                    }
                    
                    
                }
                // <RELEASE ARG />

                NvcTblProyectosH proyecto = em.createNamedQuery("NvcTblProyectosH.findByIdProyecto", NvcTblProyectosH.class)
                        .setParameter("id", Long.valueOf(idProyecto)).getSingleResult();
                detalleDeRequisicionPojo.setIdProyecto(idProyecto);
                detalleDeRequisicionPojo.setProyecto(proyecto.getCodProyecto().concat("-").concat(proyecto.getNombreProyecto()));

                NvcVmOaProyectoTareas idTarea = em.createNamedQuery("NvcVmOaProyectoTareas.findByIdTarea", NvcVmOaProyectoTareas.class)
                        .setParameter("idTarea", carroCompraDetalle.getIdTarea().longValue()).getSingleResult();
                detalleDeRequisicionPojo.setIdTarea((int) (idTarea.getNvcVmOaProyectoTareasPK().getIdTarea()));
                detalleDeRequisicionPojo.setTarea(idTarea.getCodTarea());
                //<R41223>
            } else {
                // <MDA CONTRALOR>

                if (idFad != 0) {
                    NvcTblFadRequest fReq = new NvcTblFadRequest();
                    NvcTblFadPojo fad = new NvcTblFadPojo();
                    fad.setIdCarroCompra(carroCompraPojo.getIdCarroCompra());
                    fReq.setEntity(fad);
                    String strAprobador = getProcessResponsible(fReq);
                    aprobacionEspecial = "FADPR";
                    siguientAprobador = em.createNamedQuery("Usuario.findByIdUsuario", Usuario.class).setParameter("1", strAprobador).getSingleResult();
                } else {

                    boolean preAprobacionFinanzas = checkParametroPorFuente(idUen,
                            ConstantsUtils.PARAMETROS_CONFIGURACION.SPX_PREAPROBACION_FINANZAS,
                            fuente,
                            idSubfamilia
                    );

                    boolean preAprobacionFamilias = checkParametroPorFuente(idUen,
                            ConstantsUtils.PARAMETROS_CONFIGURACION.SPX_PREAPROBACION_POR_FAMILIAS,
                            fuente,
                            idSubfamilia
                    );

                    if (preAprobacionFinanzas) {
                        /* hay preaprobacion finanzas encendido para este tipo de requisiciones */
                        String preAprFinanzas = get_Family_Aproval(idSubfamilia, idUen, fuente,
                                ConstantsUtils.PARAMETROS_CONFIGURACION.SPX_PREAPROBACION_FINANZAS.getNombreParametro());
//                                    strAprobador = preAprFinanzas;
                        aprobacionEspecial = "F1";
                        siguientAprobador = usersRepo.getOneById(preAprFinanzas);
                    } else if (preAprobacionFamilias) {
                        /* hay preaprobacion por familia encendido para este tipo de requisiciones */
                        String preAprFamilias = get_Family_Aproval(idSubfamilia, idUen, fuente,
                                ConstantsUtils.PARAMETROS_CONFIGURACION.SPX_PREAPROBACION_POR_FAMILIAS.getNombreParametro());
//                                    strAprobador = preAprFamilias;
                        aprobacionEspecial = "E1";
                        siguientAprobador = em.createNamedQuery("Usuario.findByIdUsuario", Usuario.class).setParameter("1", preAprFamilias).getSingleResult();
                    }

                    if (preAprobacionFamilias && preAprobacionFinanzas) {
                        aprobacionEspecial = "F2";
                        /* va a pasar por las dos pre-aprobaciones */
                    } else if (!preAprobacionFamilias && !preAprobacionFinanzas) {
                        siguientAprobador = get_Respcc(idUen, carroCompraDetalle.getIdCentroCosto());
//                                    strAprobador = (siguientAprobador != null) ? siguientAprobador.getIdUsuario() : null;
                        aprobacionEspecial = "N";
                    }
                }
                // <MDA CONTRALOR />
            }
        } catch (Exception e) {
            log.error("Error", e);
        }
        log.debug("aprobacionEspecial:" + aprobacionEspecial);
        detalleDeRequisicionPojo.setAprobacionEspecial(aprobacionEspecial);

        detalleDeRequisicionPojo.setSiguienteAprobador(siguientAprobador.getId());
        detalleDeRequisicionPojo.setNombreAprobador(siguientAprobador.getNombreUsuario());//<T378733
    }

    @Override
    public RequisicionUens convertToUens(Map<Integer, Map<Fuentes, List<NvcVCarroCompra>>> items) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Usuario get_Delcc(Integer idUen, Integer idCentroCosto) {
        log.debug("*** get_Delcc *** idUen > " + idUen + " idCentroCosto > " + idCentroCosto);
        Usuario delegado = null;
        try {
            String strApprover = (String) em
                    .createNativeQuery("SELECT Fn_Get_DelCC(?1, ?2) FROM DUAL")
                    .setParameter("1", idUen)
                    .setParameter("2", idCentroCosto)
                    .getSingleResult();
            delegado = em
                    .createNamedQuery("Usuario.findByIdUsuario", Usuario.class)
                    .setParameter("1", strApprover)
                    .getSingleResult();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return delegado;
    }
    
    private java.math.BigDecimal get_Category(Integer idCuenta) {
        java.math.BigDecimal category = null;
        try {
            category = (java.math.BigDecimal) em
                    .createNativeQuery("SELECT icom_budget_trx_pkg.GET_CATEGORY(?1) FROM DUAL")
                    .setParameter("1", idCuenta)
                    .getSingleResult();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return category;
    }
    
    @Transactional
    @Override
    public Object createRequisicion(RequisicionRequest request, String userId) {
        RequisicionResponse requisicionResponse = previewRequisicion(request);
        
        request.setRequisicionPreviewList(requisicionResponse.getRequisicionPreviewList());
        RequisicionResponse response = new RequisicionResponse();
        Usuario defaultUser = usersRepo.getOneById(userId);
        
        // EntityTransaction transaction = em.getTransaction();

        try {
            Requisicion requisicion = null;
            // transaction.begin();

            for (RequisicionPreview preview : requisicionResponse.getRequisicionPreviewList()) {
                // List<RequisicionTipo> tipos = uen.getTipos();
                
                for (TipoRequisicion tipo : preview.getTipoRequisicion()) {
                    // 1. Filtrar los detalles de las requsiciones por los que
                    //      - Son de tipo de fuente P y estan ARPOBADAS o ACTIVAS.
                    //      - O que no cumplan ninguna de las anteriores condiciones.
                    
                    tipo.getRequisicionPojoList().forEach((req) -> {
                        req.setDetalles(
                            req.getDetalles().stream().filter((detail) -> {
                                if (detail.isFuente("P")) { // Es proveedor
                                    Integer idProducto = detail.getIdProducto();
                                    NvcTblCatalogoItem item = NvcTblCatalogoItem.byId(idProducto, em);
                                    return ItemEstatus.APPROVED.equals(item.getIdEstatus())
                                            && ItemEstatus.ACTIVE.equals(item.getItemPublicado());
                                } else { // Es almacen o spot
                                    return true;
                                }
                            }).collect(Collectors.toList())
                        );
                    });
                    
                    for (RequisicionPojo requi : tipo.getRequisicionPojoList()) {
                        Usuario user;
                        if (requi.isFuente("V") || requi.isFuente("I")) {
                            user = usersRepo.getOneById(requi.getRequisitor());
                        } else {
                            user = defaultUser;
                        }
                        
                        requisicion = new Requisicion();
                        requisicion.setAppOrigen(requi.getAppOrigen());
                        requisicion.setFecha(new Date());
                        requisicion.setUsuario(user);
                        requisicion.setTipo(requi.getTipo());
                        requisicion.setEstatus(requi.getEstatus());
                        requisicion.setDatosDeAuditoria(requi.getDatosDeAuditoria());
                        requisicion.setIdUen(requi.getIdUen());
                        requisicion.setSpx(requi.getSpx());
                        
                        if (requi.getIdProveedor() != null) {
                            boolean result = true;
                            NvcTblProvSitesH proveedor = null;

                            try {
                                proveedor = NvcTblProvSitesH.byId(
                                        requi.getIdProveedor(),
                                        requi.getIdSiteProveedor(),
                                        em
                                );
                                if (proveedor.getActive().intValue() == 0) {
                                    result = false;
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                log.debug("proveedor no valido: " + ex.getMessage());
                                result = false;
                            }

                            if (!result) {
                                if (proveedor != null) {
                                    throw new Exception(String.format("msj_proveedor_site_invalido|%s|%s",
                                            proveedor.getVendorName(),
                                            proveedor.getVendorSiteCode()
                                    ));
                                } else {
                                    throw new Exception("msj_proveedor_site_invalido");
                                }
                            }

                            requisicion.setProveedorSite(proveedor);
                        }
                        
                        requisicion.setDetalles(new ArrayList<>());
                        
                        if (requi.getIdAlmacen() != null) {
                            NvcTblAlmacenH almacen = NvcTblAlmacenH
                                    .getOneByIdAlmacen(requi.getIdAlmacen(), em);
                            requisicion.setAlmacen(almacen);
                        }

                        System.out.println("!requi.getDetalles().isEmpty(): " + !requi.getDetalles().isEmpty());
                        if (!requi.getDetalles().isEmpty()) {
                            em.persist(requisicion);
                            
                            for (DetalleDeRequisicionPojo det : requi.getDetalles()) {
                                DetalleDeRequisicion detalle = new DetalleDeRequisicion(
                                    det.getIdPartida().longValue(),
                                    requisicion.getId()
                                );
                                System.out.println(" ");
                                System.out.println("det toString: ");
                                System.out.println(det.toString());

                                detalle.setDatosDeAuditoria(requi.getDatosDeAuditoria());
                                detalle.setIdUen(requi.getIdUen());
                                
                                Integer idUenSurtidora = det.getIdUenSurtidora();
                                Integer idUen = det.getIdUen();
                                
                                if (idUenSurtidora != null) {
                                    if (!idUenSurtidora.equals(idUen)) {
                                        detalle.setIdUenSurtidora(idUenSurtidora);
                                    } else {
                                        det.setIdUenSurtidora(null);
                                        detalle.setIdUenSurtidora(null);
                                    }
                                }

                                detalle.setEstatus(det.getEstatus());
                                detalle.setDescripcionProducto(det.getDescripcion());
                                detalle.setIdUnidadDeMedida(det.getUom());  
                                detalle.setIdFamilia(det.getIdSubfamilia());
                                detalle.setFechaRequerida(det.getFechaNecesidad());
                                
                                Usuario approver = null;
                                if (det.getSiguienteAprobador() == null
                                        && det.getIdCentroCosto() != null
                                        && det.getIdCentroCosto() != 0
                                ) {
                                    // Parece que esto no hace nada.
                                    get_Delcc(det.getIdUen(), det.getIdCentroCosto());
                                } else {
                                    detalle.setSiguienteAprobador(det.getSiguienteAprobador());
                                }

                                if (det.getComprador() != null) {
                                    detalle.setComprador(usersRepo.getOneById(det.getComprador()));
                                }

                                detalle.setVendorPartNumber(det.getVendorPartNumber());
                                detalle.setUrgente(det.getUrgente() == null ? "N" : "S");
                                detalle.setRazonUrgencia(det.getRazonUrgencia());
                                detalle.setFuente(det.getFuente());
                                detalle.setCantidadRequerida(BigDecimal.valueOf(det.getCantidad()));

                                System.out.println("Multicc: " + (det.getDetalleMultiCc() == null || det.getDetalleMultiCc().isEmpty()));
                                if (det.getDetalleMultiCc() == null || det.getDetalleMultiCc().isEmpty()) {
                                    
                                    detalle.setIdCuenta(Long.valueOf(det.getIdCuenta()));
                                    java.math.BigDecimal categoryId = get_Category(det.getIdCuenta());
                                    
                                    if (categoryId != null) {
                                        detalle.setCategory(IcomAccCategories.byId(categoryId, em));
                                    }

                                    detalle.setSegmento1(det.getSegmento1());
                                    detalle.setSegmento2(det.getSegmento2());
                                    detalle.setSegmento3(det.getSegmento3());
                                    detalle.setSegmento4(det.getSegmento4());
                                    detalle.setSegmento5(det.getSegmento5());
                                    detalle.setSegmento6(det.getSegmento6());
                                    detalle.setSegmento7(det.getSegmento7());
                                    detalle.setSegmento8(det.getSegmento8());
                                }
                                
                                if (det.getIdLocalizacion() != null) {
                                    NvcTblOaLocalizacionesH localizacion = NvcTblOaLocalizacionesH
                                            .byId(BigDecimal.valueOf(det.getIdLocalizacion()), em);
                                    detalle.setLocalizacion(localizacion);
                                }

                                if (det.getIva() != null && !det.getIva().isEmpty()) {
                                    detalle.setIva(det.getIva());
                                }

                                if (det.getIdContrato() != null) {
                                    detalle.setIdContrato(det.getIdContrato().longValue());
                                }

                                if (det.getIdProyecto() != null && det.getIdProyecto() > 0) {
                                    NvcTblProyectosH proyecto = NvcTblProyectosH
                                            .byId(Long.valueOf(det.getIdProyecto()), em);
                                    detalle.setProyecto(proyecto);
                                    detalle.setCodProyecto(proyecto.getCodProyecto());
                                    
                                    NvcTblOaTareasH tarea = NvcTblOaTareasH
                                            .byId(Long.valueOf(det.getIdTarea()), em);
                                    detalle.setIdTarea(tarea.getId().intValue());
                                    detalle.setCodTarea(tarea.getCodTarea());
                                    detalle.setTipoGasto(det.getTipoGasto());
                                }
                                
                                if (det.getDetalleMultiCc() == null || det.getDetalleMultiCc().isEmpty()) {
                                    if (det.getIdCentroCosto() != null && det.getIdCentroCosto() > 0) {
                                        detalle.setIdCentroCosto(det.getIdCentroCosto());
                                        // TODO: volver a agregarlo para evitar futuros errores
                                        // requisicion.setIdCentroCosto(det.getIdCentroCosto());
                                    }
                                }
                                em.merge(requisicion);
                                
                                // TODO: probablemente este estatus no sea necesario especificarlo
                                // ya que puede que se use alguno especifico para la creacion del
                                // mismo, si se crea es porque esta en un estatus inicial
                                System.out.println("de.getIdEstatus: " + det.getIdEstatus());
                                detalle.setIdEstatus(det.getIdEstatus());
                                detalle.setNotesToBuyer(det.trimmedNotasComprador());
                                detalle.setAprobacionEspecial(det.getAprobacionEspecial());
                                
                                if (det.getIdMoneda() != null) {
                                    detalle.setIdMoneda(det.getIdMoneda());
                                }
                                
                                NvcTblCarroCompra carro = carros.getCarroById(det.getIdCarroCompra());
  
                                if (null != carro.getRfNumber()) {
                                    requisicion.setRfNumber(carro.getRfNumber().longValue());
                                    requisicion.setIdRf(carro.getIdRf().longValue());
                                    requisicion.setCommodity(carro.getCommodity());
                                    em.merge(requisicion);
                                    detalle.setIdPartNumber(carro.getIdPartNumber());
                                }
                                
                                detalle.setCodProducto(det.getCodProducto());
                                detalle.setLeadTime(det.getTiempoEntrega() == null
                                        ? null : det.getTiempoEntrega().shortValue());
                                detalle.setMontoExtendido(det.getMontoExtendido());
                                detalle.setPrecio(det.getPrecio());
                                detalle.setTipoRecibo(carro.getTipoRecibo());
                               
                                String idProduct = det.hasProduct() ? det.getIdProducto().toString() : null;
                                
                                if (carro.getPunchout() == 1) {
                                    detalle.setIdProducto(idProduct);
                                } else {
                                    detalle.setIdProducto(det.isAnyFuente("A", "K", "L", "P")
                                            ? idProduct
                                            : carro.getItemSpotMaximo()
                                    );
                                }
                                
                                if (det.hasMultiCc()) {
                                    detalle.setIdTipoCargo(TipoCargo.MULTI_CC);
                                } else {
                                    if (detalle.getIdTarea() == null) {
                                        detalle.setIdTipoCargo(TipoCargo.CC);
                                    } else {
                                        detalle.setIdTipoCargo(TipoCargo.PROYECTO);
                                    }
                                }
                                
                                if (det.getIdUenSurtidora() != null) {
                                    if (!det.areBothUensTheSame()) {
                                        detalle.setIdUenSurtidora(det.getIdUenSurtidora());
                                    } else {
                                        det.setIdUenSurtidora(null);
                                        detalle.setIdUenSurtidora(null);
                                    }
                                }
                                
                                em.persist(detalle);
                                
                                if (det.hasTypeDocumentoMaximo() && det.hasDocumentoValueMaximo()) {
                                    PartidaDocumentsMaximo pdm = new PartidaDocumentsMaximo();
                                    pdm.setItemSpotNum(carro.getItemSpotMaximo());
                                    pdm.setTypeDocument(det.getTypeDocumentoMaximo());
                                    pdm.setValueDocument(det.getDocumentoValueMaximo());
                                    pdm.setIdSiteMaximo(det.getSiteIdMaximo());
                                    pdm.setAttemptsNum(0);
                                    pdm.setStatus("NEW");
                                    pdm.setIdPartida(det.getIdPartida().longValue());
                                    pdm.setIdRequisicion(detalle.getPK().getIdRequisicion());

                                    em.persist(pdm);
                                }
                                
                                boolean aprueba = params.checkUenAndFuente(
                                        "APROBACION_FINAL_POR_TIPO",
                                        detalle.getIdUen(),
                                        detalle.getFuente()
                                );

                                if (aprueba) {
                                    AprobacionController acont = new AprobacionController();
                                    acont.setEstatusRequisicion("POR PROBAR");
                                    acont.setFechaCreacion(new Date());
                                    acont.setIdRequisicion(detalle.getPK().getIdRequisicion().intValue());
                                    acont.setIdPartida(detalle.getPK().getIdPartida().intValue());
                                    em.persist(acont);
                                }
                                
                                det.setIdRequisicion(detalle.getPK().getIdRequisicion().intValue());

                                if (carro.hasObservacionRecibo()) {
                                    ComentarioRequisicionPK comPk = new ComentarioRequisicionPK(
                                            BigInteger.valueOf(detalle.getPK().getIdRequisicion()),
                                            BigInteger.valueOf(detalle.getPK().getIdPartida())
                                    );
                                    ComentarioRequisicion com = new ComentarioRequisicion();
                                    com.setPK(comPk);
                                    com.setComentario(carro.trimmedObservacionRecibo());
                                    com.setParaOc(BigInteger.ONE);
                                    com.setDetalleDeRequisicion(detalle);
                                    em.persist(com);
                                }
                                
                                // TODO: Revisar una manera de sacar el OK sin tener que asignar.
                                response.setMessageOut("OK");
                                
                                if (carro.getIdMotivoSol() != null && carro.getPerIdUsuarioSol() != null) {
                                    try {
                                        lineas.register(carro, det.getIdRequisicion(), det.getIdUen());
                                    } catch (Exception ex) {
                                        System.out.println("creaRequisicion: " + ex.getMessage());
                                    }
                                }
                                
                                det.setIdRequisicion(requisicion.getId().intValue());

                                if (det.hasMultiCc()) {
                                    det.getDetalleMultiCc().forEach((multiCc) -> {
                                        DistribucionRequisicion distReq = new DistribucionRequisicion();
                                        distReq.setIdPartida(det.getIdPartida());
                                        
                                        DcEstatus status = estatuses.findActiveByDesc(det.getEstatus());
                                        distReq.setIdEstatus(status.getId().intValue());
                                        distReq.setIdCuenta(multiCc.getIdCuenta());
                                        distReq.setIdCc(multiCc.getIdCentroCosto());
                                        
                                        if (det.isFuente("C")) {
                                            distReq.setMonto(null);
                                            Double porcentaje = multiCc.getPorcentaje(0.0);
                                            distReq.setPorcentaje(porcentaje / 100);
                                        } else {
                                            distReq.setMonto(multiCc.getMonto());
                                            distReq.setPorcentaje(null);
                                        }
                                        
                                        distReq.setCreateBy(userId);
                                        distReq.setCreationDate(new Date());
                                        distReq.setUpdatedBy(userId);
                                        distReq.setIdRequisicion(det.getIdRequisicion());
                                        distReq.setLastUpdateDate(new Date());
                                        em.persist(distReq);
                                    });
                                }
                            }
                            requi.setIdRequisicion(requisicion.getId().intValue());
                        }
                    }
                }
            }
            
            Map<Integer, Boolean> mapAutoaprobacion = new HashMap();
            request.getRequisicionPreviewList().forEach((uen) -> {
                uen.getTipoRequisicion().forEach((tipo) -> {
                    // TODO: remove hardcoded numbers
                    boolean tipoSpot = tipo.isAnyTipo(3, 6, 7, 8, 9);
                    boolean tipoAlmacen = tipo.isAnyTipo(2, 4, 6);
                    
                    tipo.getRequisicionPojoList().forEach((requi) -> {
                        requi.getDetalles().forEach((det) -> {
                            try {
                                NvcTblCarroCompra carro = carros.getCarroById(det.getIdCarroCompra());
                                requi.setIdCarroCompra(det.getIdCarroCompra());

                                if (tipoSpot) {
                                    carro.setIdRequisicion(det.getIdRequisicion());
                                    carro.setIdPartida(det.getIdPartida());
                                    em.merge(carro);
                                    
                                    try {
                                        carros.editSpot(carro, det.getIdPartida(), det.getIdRequisicion());
                                    } catch (Exception ex) {
                                        log.error(ex.getMessage(), ex);
                                    }
                                } else {
                                    em.remove(carro);
                                }
                                
                                if (tipoAlmacen) {
                                    if (!mapAutoaprobacion.containsKey(requi.getIdUen())) {
                                        boolean autoAprueba = repoReq.validarAutoaprobacion(requi.getIdRequisicion());
                                        mapAutoaprobacion.put(requi.getIdUen(), autoAprueba);
                                    }
                                    if (mapAutoaprobacion.get(requi.getIdUen())) {
                                        Integer toReturn = repoReq.autoAprueba(det.getIdRequisicion(), det.getIdPartida());
                                        if (toReturn == 0) {
                                            det.setEstatus("APROBADA");
                                        } else {
                                            det.setMensajeAprobacion("");
                                        }
                                    }
                                }
                                
                                bitacore.registerSafe(
                                        det.getIdRequisicion(),
                                        det.getIdPartida(),
                                        null,
                                        userId,
                                        1,
                                        estatuses.findActiveByDesc(det.getEstatus()).getId().intValue(),
                                        det.getComprador(),
                                        15
                                );
                            } catch (javax.validation.ConstraintViolationException e) {
                                log.error(e.getMessage(), e);
                            } catch (Exception e) {
                                log.error(e.getMessage(), e);
                            } catch (Throwable e) {
                                log.error(e.getMessage(), e);
                            }
                        });
                    });
                });
            });
            
            if (requisicion != null) {
                if (!repoReq.exist(requisicion.getId())) {
                    request.getRequisicionPreviewList().forEach((uen) -> {
                        uen.getTipoRequisicion().forEach((tipo) -> {
                            tipo.getRequisicionPojoList().forEach((requi) -> {
                                requi.setIdRequisicion(requi.getIdRequisicion());
                            });
                        });
                    });
                    throw new Exception("msj_err_create_req");
                }
            }
            // transaction.commit();
        } catch (java.sql.SQLSyntaxErrorException ex) {
            log.error(ex.getMessage(), ex);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            response.setMessageOut(ex.getMessage());
            // transaction.rollback();
        }

        response.setRequisicionPreviewList(new ArrayList<>());
        response.getRequisicionPreviewList().addAll(request.getRequisicionPreviewList());
        
        return response;
    }
}
