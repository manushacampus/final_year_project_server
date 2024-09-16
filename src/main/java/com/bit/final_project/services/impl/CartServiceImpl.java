package com.bit.final_project.services.impl;

import com.bit.final_project.commons.Generator;
import com.bit.final_project.exceptions.http.EntityExistsException;
import com.bit.final_project.models.Cart;
import com.bit.final_project.models.Customer;
import com.bit.final_project.models.StockItem;
import com.bit.final_project.repositories.Cart.CartRepository;
import com.bit.final_project.repositories.Customer.CustomerRepository;
import com.bit.final_project.security.filters.CurrentUser;
import com.bit.final_project.services.CartService;
import com.bit.final_project.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    StockService stockService;


    @Override
    public Cart getCartById(String id) {
        return cartRepository.findById(id).orElseThrow(() -> new EntityExistsException("Cart not found with id: " + id));
    }

    @Override
    public Cart deleteCartById(String id) {
       Cart cart = getCartById(id);
       cartRepository.delete(cart);
       return cart;
    }

    @Override
    public Cart addToCart(String productId,int qty) {
        Customer customer = customerRepository.findByUser(CurrentUser.getUser());
        StockItem stockItem = stockService.getStockItemById(productId);
        Cart cart = new Cart();
        cart.setId(Generator.getUUID());
        cart.setQty(qty);
        cart.setCustomer(customer);
        cart.setStockItem(stockItem);

        return cartRepository.save(cart);
    }

    @Override
    public List<Cart> getAllCartByCustomer() {
        Customer customer = customerRepository.findByUser(CurrentUser.getUser());
        return cartRepository.findAllByCustomer(customer);
    }

    @Override
    public Cart changeQty(String cartId, int qty) {
        Cart cart = getCartById(cartId);
        if (qty <= 0) {
            throw new IllegalArgumentException("Quantity must be a positive number greater than 0.");
        }
        cart.setQty(qty);
        return cartRepository.save(cart);
    }

    @Override
    public Cart removeCart(String id) {
        Cart cart = getCartById(id);
        cartRepository.delete(cart);
        return cart;
    }
}
