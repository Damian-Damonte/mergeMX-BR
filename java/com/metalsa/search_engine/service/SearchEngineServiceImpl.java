/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.search_engine.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metalsa.aprobacion.model.UenWithDefault;
import com.metalsa.aprobacion.repository.OrganizacionesRepository;
import com.metalsa.search_engine.model.PendingOrder;
import com.metalsa.search_engine.pojo.SearchEngineItem;
import com.metalsa.search_engine.requests.AddFavoriteRequest;
import com.metalsa.search_engine.requests.GetPendingOrdersRequest;
import com.metalsa.utils.Constants;
import com.metalsa.utils.entities.NvcTblItemFavorito;

import lombok.extern.log4j.Log4j;

/**
 *
 * @author jose.espindola03
 */
@Log4j
@Service
public class SearchEngineServiceImpl implements SearchEngineService {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private OrganizacionesRepository uens;
    
    @Override
    public List<SearchEngineItem> getItemsByTextMatch(String userId, Integer uen, String text) {
        List<SearchEngineItem> items = new ArrayList<>();
        SearchEngineItem item;        
        
        try {
            int isInternUen = 1;

            List<UenWithDefault> _uens = uens.getUensActivasByUser(userId);
            for(UenWithDefault _uen:  _uens){
                if(_uen.getId() == uen.intValue()){
                    isInternUen = _uen.isInteruen() ? 1 : 0;
                }
            }

            StoredProcedureQuery procedure = em.createStoredProcedureQuery("NVC_PKG_SPX_ITEMS_MOTOR.SP_GET_ITEMS");
                                
            procedure.registerStoredProcedureParameter("p_usuario", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_uen", Integer.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_campo_busqueda", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_codigo_item", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_descripcion", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_idproveedor", Integer.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_numparteprov", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_numpartefab", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_idcategoria", Integer.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_idfamilia", Integer.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_idsubfamilia", Integer.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_esInterUen", Integer.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_monedaDefault", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_color", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("cursor_out", void.class, ParameterMode.REF_CURSOR);
            
            procedure.setParameter("p_usuario", userId);
            procedure.setParameter("p_uen", uen);
            procedure.setParameter("p_campo_busqueda", text);
            
            procedure.setParameter("p_codigo_item", null);
            procedure.setParameter("p_descripcion", null);
            procedure.setParameter("p_idproveedor", null);
            procedure.setParameter("p_numparteprov", null);
            procedure.setParameter("p_numpartefab", null);
            procedure.setParameter("p_idcategoria", null);
            procedure.setParameter("p_idfamilia", null);
            procedure.setParameter("p_idsubfamilia", null);
            procedure.setParameter("p_esInterUen", isInternUen);
            procedure.setParameter("p_monedaDefault", "MXP");
            procedure.setParameter("p_color", null);
            
            boolean isOK = procedure.execute();            
            if (isOK) {
                List<Object[]> cursor = (List<Object[]>) procedure.getResultList();                
                for (Object[] row : cursor) {

                    item = SearchEngineItem.createItemBySP_GET_ITEMS(row);                                        

                    items.add(item);
                }                
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return items;                
    }

    @Override
    public List<SearchEngineItem> getFavoriteItems(String userId) {
        List<SearchEngineItem> items = new ArrayList<>();
        SearchEngineItem item;   

        try {
            StoredProcedureQuery procedure = em.createStoredProcedureQuery("NVC_PKG_SPX_ITEMS_MOTOR.SP_GET_ITEMS_FAVORITOS");

            procedure.registerStoredProcedureParameter("p_usuario", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_monedaDefault", String.class, ParameterMode.IN);
            //procedure.registerStoredProcedureParameter("p_uen", Integer.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("cursor_out", void.class, ParameterMode.REF_CURSOR);

            procedure.setParameter("p_usuario", userId);
            //procedure.setParameter("p_uen", 90);
            procedure.setParameter("p_monedaDefault", null);

            boolean isOK = procedure.execute();            
            if (isOK) {
                List<Object[]> cursor = (List<Object[]>) procedure.getResultList();                
                for (Object[] row : cursor) {
                    item = SearchEngineItem.createItemBySP_GET_ITEMS_FAVORITOS(row);                                        

                    items.add(item);
                }                
            }

        }catch(Exception e){
            log.error(e.getMessage());
        }
        return items;
    }

    @Override
    @Transactional 
    public Object addToFavorite(String userId, AddFavoriteRequest addFavoriteRequest){
        List<NvcTblItemFavorito> favoritos = new ArrayList<>();
        HashMap<String, String> response = new HashMap<String, String>();        
        response.put("ok", "1");
        
        if (addFavoriteRequest.getIdTipoItem().equals(Constants.TIPO_ITEM.ALMACEN.getType())) {            
            favoritos = em.createNativeQuery("SELECT * FROM NVC_V_ITEMS_ALL n JOIN NVC_TBL_ITEM_FAVORITO f ON f.ID_ITEM_ALMACEN  = n.IDITEM  WHERE f.ID_ITEM  IS NULL AND f.ID_ITEM_ALMACEN = :idItem AND f.ID_USUARIO = :idUsuario AND f.ID_UEN = :idUen")
                        .setParameter("idUsuario", userId)
                        .setParameter("idItem", addFavoriteRequest.getIdItem())
                        .setParameter("idUen", addFavoriteRequest.getIdUen())
                        .getResultList();                                    
        } else {
            favoritos = em.createNativeQuery("SELECT * FROM NVC_V_ITEMS_ALL n JOIN NVC_TBL_ITEM_FAVORITO f ON f.ID_ITEM  = n.IDITEM  WHERE f.ID_ITEM  IS NOT NULL AND n.IDITEM  = :idItem AND f.ID_USUARIO = :idUsuario")
                    .setParameter("idUsuario", userId)
                    .setParameter("idItem", addFavoriteRequest.getIdItem())
                    .getResultList();            
        }
        
        if (favoritos.isEmpty()) {              
            try {
                if (addFavoriteRequest.getIdTipoItem().equals(Constants.TIPO_ITEM.ALMACEN.getType())) {                    
                    int i  = em.createNativeQuery("INSERT INTO NVC_TBL_ITEM_FAVORITO (ID_FAVORITO, ID_USUARIO, ID_ITEM_ALMACEN, ID_UEN) VALUES ((select max(ID_FAVORITO)+1 from NVC_TBL_ITEM_FAVORITO), ?, ?, ?)")
                        .setParameter(1, userId)
                        .setParameter(2, addFavoriteRequest.getIdItem())
                        .setParameter(3, addFavoriteRequest.getIdUen())
                        .executeUpdate();                        
                } else {                    
                    em.createNativeQuery("INSERT INTO NVC_TBL_ITEM_FAVORITO (ID_FAVORITO, ID_USUARIO,ID_ITEM) VALUES ((select max(ID_FAVORITO)+1 from NVC_TBL_ITEM_FAVORITO), ?, ?)")
                        .setParameter(1, userId)
                        .setParameter(2, addFavoriteRequest.getIdItem())
                        .executeUpdate();                                                                     
                }                           
                             
            } catch (Exception ex) {                
                log.error(ex.getMessage(), ex);  
                response.put("ok", "0");
            }
        } 
        
        return response;
    }

    @Override
    @Transactional 
    public Object removeFromFavorite(String userId, AddFavoriteRequest addFavoriteRequest) {
        List<NvcTblItemFavorito> favoritos = new ArrayList<NvcTblItemFavorito>();
        HashMap<String, String> response = new HashMap<String, String>();        
        response.put("ok", "1");
        favoritos = em.createNativeQuery("SELECT * FROM  NVC_TBL_ITEM_FAVORITO f WHERE (f.ID_ITEM = :idItem1 OR f.ID_ITEM_ALMACEN = :idItem2 AND f.ID_UEN = :idUen ) AND f.ID_USUARIO = :idUsuario", NvcTblItemFavorito.class)
                        .setParameter("idUsuario", userId)
                        .setParameter("idItem1", addFavoriteRequest.getIdItem())
                        .setParameter("idItem2", addFavoriteRequest.getIdItem())
                        .setParameter("idUen", addFavoriteRequest.getIdUen())
                        .getResultList();

        if(favoritos.isEmpty()){
            return response;
        }

        NvcTblItemFavorito favorito = favoritos.get(0);
        
        em.createNativeQuery("DELETE FROM NVC_TBL_ITEM_FAVORITO WHERE ID_FAVORITO = ?")
                        .setParameter(1, favorito.getIdFavorito())                        
                        .executeUpdate();   

        return response;                        
    }

    @Override
    public List<PendingOrder> getPendingOrders(GetPendingOrdersRequest getPendingOrdersRequest) {
        List<PendingOrder> pendingOrders = new ArrayList<PendingOrder>();
        
        try {

            StoredProcedureQuery procedure = em.createStoredProcedureQuery("NVC_PKG_SPX_ITEMS_MOTOR.GET_OC_ITEM_DETALLE");
            procedure.registerStoredProcedureParameter("P_UEN", Integer.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("P_ITEM", Integer.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("P_ALMACEN", Integer.class, ParameterMode.IN);//<T411828>
            procedure.registerStoredProcedureParameter("CURSOR_OUT", Void.class, ParameterMode.REF_CURSOR);
            procedure.setParameter("P_UEN", getPendingOrdersRequest.getUen());
            procedure.setParameter("P_ITEM", getPendingOrdersRequest.getItemId());
            procedure.setParameter("P_ALMACEN", getPendingOrdersRequest.getAlmacenId());//<T411828>
            boolean isOK = procedure.execute();
            
            if (isOK) {
                //procedure.getResultList()
                //List<Object[]> result = (List<Object[]>) procedure.getOutputParameterValue("CURSOR_OUT");
                List<Object[]> result = (List<Object[]>) procedure.getResultList();
                for (Object[] row : result) {

                    PendingOrder pendingOrder = new PendingOrder();
                    pendingOrder.setFechaPromesa((String) row[4]);
                    pendingOrder.setNumOrdenCompra((String) row[0]);
                    
                    pendingOrders.add(pendingOrder);                       
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return pendingOrders;
    }
    
}
