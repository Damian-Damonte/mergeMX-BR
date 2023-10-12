package com.metalsa.cart.pojo;

import lombok.Data;

@Data
public class AddItemToCart {
    
    private Long idItem;
    private String codigoItem;
    private Double quantity;

}
