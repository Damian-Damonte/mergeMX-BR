/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.cart.controller;

import com.metalsa.cart.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

import com.metalsa.cart.service.CartService;
import com.metalsa.utils.Constants;
import com.metalsa.utils.entities.NvcVCarroCompra;
import java.util.List;

@RestController
@Api(value = "Cart", tags = {"Cart Service API"})
@RequestMapping(Constants.URI_API_CART)
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add-item")
    public Object addToCart(@RequestHeader("X-SPX-UserId") String userId, @RequestBody AddItemToCart request){        
        return cartService.addItemToCart(userId, request);
    }

    @PostMapping("/report-item-0")
    public Object reportZero(@RequestHeader("X-SPX-UserId") String userId, @RequestBody ReportZero request){        
        return cartService.reportZero(userId, request);
    }

    @PostMapping("/delete-item/{id}")
    public Object deleteItem(@RequestHeader("X-SPX-UserId") String userId, @PathVariable("id") Long id ){                
        return cartService.removeItemFromCart(userId, id);
    }

    @PostMapping("/update-item/{id}")
    public Object deleteItem(@RequestHeader("X-SPX-UserId") String userId, @PathVariable("id") Long id, @RequestBody UpdateCartItem request){                        
        return cartService.updateQuantityItem(userId, id, request.getQuantity());
    }

    @PostMapping("/update-uen/{id}")
    public Object updateUenItem(@RequestHeader("X-SPX-UserId") String userId, @PathVariable("id") Long id, @RequestBody UpdateUen request){                        
        return cartService.updateUen(userId, id, request.getIdUen());
    }

    @GetMapping("/locations/{id}")
    public Object locations(@PathVariable("id") Long id){                        
        return cartService.getLocations( id);
    }

    @PostMapping("/update-location/{id}")
    public Object updateLocationItem(@RequestHeader("X-SPX-UserId") String userId, @PathVariable("id") Long id, @RequestBody UpdateLocation request){                        
        return cartService.updateLocation(userId, id, request.getLocationId());
    }

    @PostMapping("/update-comment/{id}")
    public Object updateComment(@RequestHeader("X-SPX-UserId") String userId, @PathVariable("id") Long id, @RequestBody UpdateComment updateComment) {
        return cartService.updateComment(userId, id, updateComment.getComment());
    }

    @GetMapping("/new")
    public Object index(@RequestHeader("X-SPX-UserId") String userId){    
         List<NvcVCarroCompra> carts = cartService.getCartByUser(userId);
         System.out.println("index: carts.length = " + carts.size());
        return carts;
    }
    

}
