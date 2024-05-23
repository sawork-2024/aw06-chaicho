package com.micropos.cart.rest;

import com.micropos.api.CartApi;
import com.micropos.cart.mapper.CartMapper;
import com.micropos.cart.model.Cart;
import com.micropos.cart.model.Item;
import com.micropos.cart.service.CartService;
import com.micropos.dto.CartDto;
import com.micropos.dto.CartItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class CartController implements CartApi {

    private CartMapper cartMapper;

    @Autowired
    public void setCartMapper(CartMapper cartMapper) {
        this.cartMapper = cartMapper;
    }
    private CartService cartService;

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }
    @Override
    public ResponseEntity<List<CartItemDto>> addProductToCart(String productId) {
        System.out.println("addProductToCart");
        Cart curCart = cartService.addProductToCart(productId);
        List<Item> items = curCart.getItems();
        List<CartItemDto> cartItems = cartMapper.toItemsDto(items);
        return new ResponseEntity<>(cartItems, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> createCart() {
        System.out.println("createCart");
        cartService.initCart();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CartItemDto>> checkout() {
        System.out.println("checkout");
        Cart curCart = cartService.checkout();
        List<CartItemDto> cartItems = cartMapper.toItemsDto(curCart.getItems());
        return new ResponseEntity<>(cartItems, HttpStatus.OK);
    }
}
