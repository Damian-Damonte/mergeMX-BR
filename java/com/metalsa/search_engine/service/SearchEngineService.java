/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.search_engine.service;

import com.metalsa.search_engine.model.PendingOrder;
import com.metalsa.search_engine.pojo.SearchEngineItem;
import com.metalsa.search_engine.requests.AddFavoriteRequest;
import com.metalsa.search_engine.requests.GetPendingOrdersRequest;

import java.util.List;

/**
 *
 * @author jose.espindola03
 */
public interface SearchEngineService {
        List<SearchEngineItem> getItemsByTextMatch(String userId, Integer uen, String text);

        List<SearchEngineItem> getFavoriteItems(String userId);

        Object addToFavorite(String userId, AddFavoriteRequest AddFavoriteRequest);

        Object removeFromFavorite(String userId, AddFavoriteRequest AddFavoriteRequest);     
        
        List<PendingOrder> getPendingOrders(GetPendingOrdersRequest getPendingOrdersRequest);
}
