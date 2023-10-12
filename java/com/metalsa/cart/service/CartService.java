/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.cart.service;

import java.util.List;

import com.metalsa.cart.pojo.AddItemToCart;
import com.metalsa.cart.pojo.ReportZero;
import com.metalsa.utils.entities.NvcVCarroCompra;

public interface CartService {

    public List<NvcVCarroCompra> getCartByUser(String userId);
    public Object addItemToCart(String userId, AddItemToCart addItemToCart);
    public Object removeItemFromCart(String userId, Long itemId);
    public Object updateQuantityItem(String userId, Long cartId, double newQuantity);
    public Object updateUen(String userId, Long cartId, int idUen);    
    public Object reportZero(String userId, ReportZero reportZero);
    public Object updateComment(String userId, Long cartId ,String comment);
    public Object getLocations(Long cartId);
    public Object updateLocation(String userId, Long cartId, Long idLocation);

}
