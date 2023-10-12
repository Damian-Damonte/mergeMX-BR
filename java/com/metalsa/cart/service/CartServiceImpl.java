/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.cart.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metalsa.aprobacion.model.CarroCompra;
import com.metalsa.aprobacion.model.UenWithDefault;
import com.metalsa.aprobacion.repository.OrganizacionesRepository;
import com.metalsa.cart.pojo.AddItemToCart;
import com.metalsa.cart.pojo.ReportZero;
import com.metalsa.catalogo.model.NvcTblOaLocalizacionesH;
import com.metalsa.recibos.model.Udm;
import com.metalsa.utils.Constants;
import com.metalsa.catalogo.model.NvcVItemsAll;
import com.metalsa.core.repository.ItemsRepository;
import com.metalsa.cart.repository.CartRepository;
import com.metalsa.catalogo.model.NvcVItemsAllPK;
import com.metalsa.requisicion.repository.CarroCompraRepository;
import com.metalsa.utils.entities.NvcVCarroCompra;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class CartServiceImpl implements CartService{    
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private OrganizacionesRepository uens;
    
    @Autowired
    private ItemsRepository itemsRepo;
    
    @Autowired
    private CartRepository cartRepo;
    
    @Autowired
    private CarroCompraRepository carrosRepo;
    
    @Override
    @Transactional 
    public CarroCompra addItemToCart(String userId, AddItemToCart addItemToCart) {
        CarroCompra carroCompra = null; 
        Long localizacion = null;
        Double newQuantity = addItemToCart.getQuantity();
        
        try {
            NvcVItemsAll item = itemsRepo.findOneByIdAndCodigo(
                addItemToCart.getIdItem().intValue(),
                addItemToCart.getCodigoItem()
            );

            System.out.println("UEN item: " + Long.valueOf(item.getNvcVItemsAllPK().getUen()));
            UenWithDefault uen = new UenWithDefault(Long.valueOf(item.getNvcVItemsAllPK().getUen()), userId, userId, false, userId, true);
//          Obtengo las uens del usuario, y me quedo solo con las que NO son interuen.
            List<UenWithDefault> uensUsuario = uens.getUensActivasByUser(userId)
                    .stream().filter(uenStream -> !uenStream.isInteruen()).collect(Collectors.toList());

            for(UenWithDefault uenFor : uensUsuario) {
                if (uenFor.getId().equals(Long.valueOf(item.getNvcVItemsAllPK().getUen()))) {
                    uen = uenFor;
                    break;
                }
            }
            System.out.println("UEN final: " + uen.getId() + " Es interuen: " + uen.isInteruen());


//            TypedQuery<NvcTblOaLocalizacionesH> queryLocalizacion = em
//                    .createNamedQuery(
//                            "NvcTblOaLocalizacionesH.getShipToByIdUen",
//                            NvcTblOaLocalizacionesH.class);
//            queryLocalizacion.setParameter("idUen", uen.getId());
//            List<NvcTblOaLocalizacionesH> locations = queryLocalizacion.getResultList();
//
//            if(locations.size() > 0){
//                localizacion = locations.get(0).getIdLocalizacion().longValue();
//            }

            String itemLocalizationXml = item.getLocalizacion();
            int startIdIndex = itemLocalizationXml.indexOf("<id>");
            int endIdIndex = itemLocalizationXml.indexOf("</id>");
            System.out.println("item localization xml: " + itemLocalizationXml);

            if (startIdIndex != -1 && endIdIndex != -1) {
                String idLocalizacion = itemLocalizationXml.substring(startIdIndex + 4, endIdIndex);
                System.out.println("Item localizacion: " + idLocalizacion);
                localizacion = Long.valueOf(idLocalizacion);
            }

            if(item.getTipoItem().equals(Constants.TIPO_ITEM.CATALOGO.getType())){
                if(item.getIdTipoPrecio() == Constants.PRECIO_FIJO){
                    if ((carroCompra = carrosRepo.getCarroCompra(item, userId)) != null) {
                        System.out.println("MERGE");
                        carroCompra.setCantidad(newQuantity + carroCompra.getCantidad());
                        carroCompra.setVfPrecioUnitario( item.getPrecioUnitario() );
                        em.merge(carroCompra);
                    }
                }
                if(carroCompra == null){
                    carroCompra = new CarroCompra();
                    carroCompra.setItem(Long.valueOf(item.getNvcVItemsAllPK().getIdItem()));
                    carroCompra.setUen(uen.getId());
                    carroCompra.setCantidad(newQuantity);
                    carroCompra.setFechaCreacion(new Date());
                    carroCompra.setUsuario(userId);
                    Udm udm = this.getUdmByIdUnidadMedida(item.getUdm());
                    carroCompra.setIdUdm(Long.valueOf(udm.getIdUdm()));
                    carroCompra.setLocalizacion(localizacion);
                    //carroCompra.setLocalizacion(Long.valueOf(item.getIdLocalizacion()));
                    carroCompra.setTipo_Recibo(Long.valueOf(item.getTipoRecibo()));
                    carroCompra.setDescripcion(item.getDescripcion());
                    carroCompra.setUdm(item.getUdm());
                    carroCompra.setUrlImg(item.getUrlftp());
                    carroCompra.setIdTipoPrecio(item.getIdTipoPrecio());
                    carroCompra.setIdTemplate(item.getIdTemplate());
                    carroCompra.setSubFamilia(Long.valueOf(item.getSubfamilia()));
                    carroCompra.setVfPrecioUnitario(item.getPrecio());//cv
                    carroCompra.setPrecioUnitarioBase(item.getPrecioUnitario());
                    if (item.getTipoItem() == 3) {
                        carroCompra.setPunchout(1);
                    }
                    em.persist(carroCompra);

                    System.out.println("Carro compra agregado - catalogo");
                }
             } else {
                carroCompra = cartRepo.findCarroByInterUen(item, userId, uen.isInteruen());
                if (carroCompra != null) {
                    if(carroCompra.getCantidad() + newQuantity < item.getCantidadDisponible().doubleValue()){
                        carroCompra.setCantidad(newQuantity + carroCompra.getCantidad());
                        em.persist(carroCompra);
                        System.out.println("Carro compra existente - almacen");
                    }
                    System.out.println("Carro: 118 No entro");
                } else {
                    carroCompra = new CarroCompra();
                    carroCompra.setUsuario(userId);

//                  mio nuevo
                    if(uen.isInteruen()) {
                        carroCompra.setItemAlmacen(item.getNvcVItemsAllPK().getIdItem().longValue());
                        carroCompra.setUenSurtidora(Long.valueOf(item.getNvcVItemsAllPK().getUen()));
                    }else {
                        carroCompra.setItemAlmacen(item.getNvcVItemsAllPK().getIdItem().longValue());
                    }
//                  fin mio

                    System.out.println("idUen " +uen.getId());
                    carroCompra.setUen(uen.getId());
                    carroCompra.setSubFamilia(Long.valueOf(item.getSubfamilia()));
                    carroCompra.setAlmacen(Long.valueOf(item.getNvcVItemsAllPK().getIdAlmacen()));
                    carroCompra.setCantidad(newQuantity);
                    carroCompra.setFechaCreacion(new Date());
                    carroCompra.setLocalizacion(localizacion);
                    carroCompra.setTipo_Recibo(Long.valueOf(item.getTipoRecibo()));
                    carroCompra.setDescripcion(item.getDescripcion());

                    Udm udm = this.getUdmByIdUnidadMedida(item.getUdm());
                    carroCompra.setIdUdm(Long.valueOf(udm.getIdUdm()));

                    carroCompra.setUdm(item.getUdm());
                    carroCompra.setUrlImg(item.getUrlftp());
                    //<JL_CATVAR>
                    carroCompra.setIdTipoPrecio(item.getIdTipoPrecio());
                    carroCompra.setIdTemplate(item.getIdTemplate());

                    em.persist(carroCompra);

                    System.out.println("Carro compra agregado - almacen");
                    System.out.println(carroCompra.getId());
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return carroCompra;
    }

    @Override
    @Transactional 
    public Object removeItemFromCart(String userId, Long id) {
        CarroCompra cart = new CarroCompra();
        TypedQuery<CarroCompra> queryCarro = em.createNamedQuery("CarroCompra.findById", CarroCompra.class);
                            queryCarro.setParameter("id", id);
                            
        try {
            cart = queryCarro.getSingleResult();
            
            em.remove(cart);            
        } catch (NoResultException e) {
            // TODO: handle exception
        }
        

        return cart;
    }

    @Override
    @Transactional
    public Object updateQuantityItem(String userId, Long cartId, double newQuantity) {
        TypedQuery<CarroCompra> queryCarro = em.createNamedQuery("CarroCompra.findById", CarroCompra.class);
        queryCarro.setParameter("id", cartId);                
        CarroCompra carroCompra = queryCarro.getSingleResult();

        if(! carroCompra.getUsuario().equals(userId)){
            return carroCompra;
        }

        carroCompra.setCantidad( newQuantity );        
        em.merge(carroCompra);
        
        return carroCompra;
    }

    private Udm getUdmByIdUnidadMedida(String idUnidadMedida){
        TypedQuery<Udm> queryUdm = em.createNamedQuery("Udm.getByIdUnidadMedida", Udm.class);
        queryUdm.setParameter("idUnidadDeMedida", idUnidadMedida);
        queryUdm.setMaxResults(1);
        return queryUdm.getSingleResult();
    }

    @Override
    public List<NvcVCarroCompra> getCartByUser(String userId) {
        System.out.println("getCartByUser: userId = " + userId);
        return cartRepo.findAllViewsByUserId(userId);
    }

    @Override
    @Transactional
    public Object updateUen(String userId, Long cartId, int idUen) {
        TypedQuery<CarroCompra> queryCarro = em.createNamedQuery("CarroCompra.findById", CarroCompra.class);
        queryCarro.setParameter("id", cartId);                
        CarroCompra carroCompra = queryCarro.getSingleResult();

        if(! carroCompra.getUsuario().equals(userId)){
            return carroCompra;
        }

        carroCompra.setUen( Long.valueOf(idUen));
        em.merge(carroCompra);
        
        return carroCompra;
    }

    @Override
    public Object getLocations(Long cartId) {
        TypedQuery<CarroCompra> queryCarro = em.createNamedQuery("CarroCompra.findById", CarroCompra.class);
        queryCarro.setParameter("id", cartId);                
        CarroCompra carroCompra = queryCarro.getSingleResult();
        
        TypedQuery<NvcTblOaLocalizacionesH> query = em.createNamedQuery("NvcTblOaLocalizacionesH.getShipToByIdUen", NvcTblOaLocalizacionesH.class);
        query.setParameter("idUen", carroCompra.getUen());                
        List<NvcTblOaLocalizacionesH> locations = query.getResultList();

        return locations;        
    }

    @Override
    @Transactional
    public Object updateLocation(String userId, Long cartId, Long idLocation) {
        TypedQuery<CarroCompra> queryCarro = em.createNamedQuery("CarroCompra.findById", CarroCompra.class);
        queryCarro.setParameter("id", cartId);                
        CarroCompra carroCompra = queryCarro.getSingleResult();

        if(! carroCompra.getUsuario().equals(userId)){
            return carroCompra;
        }

        carroCompra.setLocalizacion( idLocation );        
        em.merge(carroCompra);
        
        return carroCompra;
    }

    @Override
    @Transactional
    public Object updateComment(String userId, Long cartId, String comment) {
        TypedQuery<CarroCompra> queryCarro = em.createNamedQuery("CarroCompra.findById", CarroCompra.class);
        queryCarro.setParameter("id", cartId);
        CarroCompra carroCompra = queryCarro.getSingleResult();

        if(! carroCompra.getUsuario().equals(userId)){
            return carroCompra;
        }

        carroCompra.setObservacionRecibo(comment);
        em.merge(carroCompra);

        return carroCompra;
    }

    @Override
    @Transactional
    public Object reportZero(String userId, ReportZero reportZero) {
        NvcVItemsAll item = new NvcVItemsAll();
        try {
//            TypedQuery<NvcVItemsAll> query = em.createNamedQuery("NvcVItemsAll.findOne", NvcVItemsAll.class);
//            query.setParameter("idItem", reportZero.getIdItem());
//            query.setParameter("codigoItem", reportZero.getCodigoItem());
//
//            item = query.getSingleResult();
            item = itemsRepo.findOneByIdAndCodigo(reportZero.getIdItem().intValue(), reportZero.getCodigoItem());

            StoredProcedureQuery procedure = em.createStoredProcedureQuery("NVC_PKG_SPX_ITEMS_MOTOR.NOTIFICAR_ITEM_FALTANTE");

            procedure.registerStoredProcedureParameter("P_ALERTA_ID", Integer.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("P_PRODUCT_ID", Integer.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("P_DESCRIPTION", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("P_UEN_ID", Integer.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("P_LOCALIZACION_ID", Integer.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("P_ALMACEN_ID", Integer.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("P_USER_ID", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("P_IDIOMA", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("P_CODIGO", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("P_NOMBREUEN", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("P_APP", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("P_SALIDA", Integer.class, ParameterMode.OUT);

            procedure.setParameter("P_ALERTA_ID", 1);
            procedure.setParameter("P_PRODUCT_ID", item.getNvcVItemsAllPK().getIdItem().intValue());
            procedure.setParameter("P_DESCRIPTION", item.getDescripcion());
            procedure.setParameter("P_UEN_ID", item.getNvcVItemsAllPK().getUen());
            procedure.setParameter("P_LOCALIZACION_ID", item.getIdLocalizacion());
            procedure.setParameter("P_ALMACEN_ID", item.getNvcVItemsAllPK().getIdAlmacen());
            procedure.setParameter("P_USER_ID", userId);
            procedure.setParameter("P_IDIOMA", "PTB");
            procedure.setParameter("P_CODIGO", item.getCodigoItem());
            procedure.setParameter("P_NOMBREUEN", item.getNombreUen());
            procedure.setParameter("P_APP", "SPX");            
            procedure.execute();
            
            log.error("Ok !!");
        } catch (Exception e) {
            log.error("Error: " + e.getMessage());
        }


        return item;
    }
}
