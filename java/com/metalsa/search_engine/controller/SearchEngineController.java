/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.search_engine.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.metalsa.utils.Constants;
import com.metalsa.search_engine.model.PendingOrder;
import com.metalsa.search_engine.pojo.PendingOrdersResponse;
import com.metalsa.search_engine.pojo.SearchEngineItem;
import com.metalsa.search_engine.requests.AddFavoriteRequest;
import com.metalsa.search_engine.requests.GetPendingOrdersRequest;
import com.metalsa.search_engine.service.SearchEngineService;


import io.swagger.annotations.Api;
/**
 *
 * @author jose.espindola03
 * public static final String URI_API_ALMACEN = "/api/v1/almacen";
 */
@RestController
@Api(value = "Search engine", tags = {"Search Engine Service API"})
@RequestMapping(Constants.URI_API_SEARCH_ENGINE)
public class SearchEngineController {
    @Autowired
    private SearchEngineService searchEngineService;

    @GetMapping("")
    public Iterable<SearchEngineItem> search(@RequestHeader("X-SPX-UserId") String userId, @RequestParam String uen, @RequestParam String q) {        
        List<SearchEngineItem> items = searchEngineService.getItemsByTextMatch(userId, Integer.parseInt(uen), q);

        return items;        
    }

    @GetMapping("/item/gallery")
    public Iterable<SearchEngineItem> gallery(@RequestBody Long idItem, @RequestBody Long uen) {        
        //List<SearchEngineItem> items = searchEngineService.getItemsByTextMatch(userId, Integer.parseInt(uen), q);

        return null;        
    }
    
    @GetMapping("/favorites")
    public Iterable<SearchEngineItem> favorites(@RequestHeader("X-SPX-UserId") String userId) {         
        List<SearchEngineItem> items = searchEngineService.getFavoriteItems(userId);

        return items;        
    }

    @PostMapping("/favorites")
    public Object add_favorites(@RequestHeader("X-SPX-UserId") String userId, @RequestBody AddFavoriteRequest request) {                                                 
        Object obj = searchEngineService.addToFavorite(userId, request);

        return obj;        
    }

    @PostMapping("/favorites/delete")
    public Object remove_favorites( @RequestHeader("X-SPX-UserId") String userId, @RequestBody AddFavoriteRequest request) {         
        //String idUsuario = "APOJM24136"; //Id from token 23 - 118077                                        
        Object obj = searchEngineService.removeFromFavorite(userId, request);

        return obj;        
    }

    @PostMapping("/oc-pendings")
    public PendingOrdersResponse ocPendings(@RequestBody GetPendingOrdersRequest getPendingOrdersRequest){        
        List<PendingOrder> pendingOrders = new ArrayList<>(); 
        
        pendingOrders = searchEngineService.getPendingOrders(getPendingOrdersRequest);        

        PendingOrdersResponse pendingOrdersResponse = new PendingOrdersResponse();
        pendingOrdersResponse.setPendingOrders(pendingOrders);

        return pendingOrdersResponse;        
    }
}
