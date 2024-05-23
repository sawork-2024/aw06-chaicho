package com.micropos.cart.service;

import com.micropos.cart.model.Cart;
import com.micropos.cart.model.Item;

import java.util.List;
import java.util.Optional;

public interface CartService {

//    Cart add(Cart cart, Item item);
//
//    List<Cart> getAllCarts();
//
//    Optional<Cart> getCart(Integer cartId);
//
//    Integer test();
    public void initCart();
    public Cart addProductToCart(String productId);

    public Cart checkout();
}
