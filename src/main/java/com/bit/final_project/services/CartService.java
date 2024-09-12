package com.bit.final_project.services;

import com.bit.final_project.models.Cart;
import com.bit.final_project.models.Design;

import java.util.List;

public interface CartService {
    Cart getCartById(String id);
    Cart deleteCartById(String id);
    Cart addToCart(String productId,int qty);
    List<Cart> getAllCartByCustomer();

    Cart changeQty(String cartId,int qty);
}
