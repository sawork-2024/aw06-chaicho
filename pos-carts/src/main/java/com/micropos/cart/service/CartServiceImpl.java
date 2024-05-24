package com.micropos.cart.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.micropos.cart.mapper.CartMapper;
import com.micropos.cart.model.Cart;
import com.micropos.cart.model.Item;
import com.micropos.cart.repository.CartRepository;
import com.micropos.dto.CartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.data.util.Streamable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private CartMapper cartMapper;

    @Autowired
    public void setCartMapper(CartMapper cartMapper) {
        this.cartMapper = cartMapper;
    }
    private CartRepository cartRepository;

    private Cart cart = null;

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    public void setCartRepository(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public void initCart() {
        if (cart == null) {
            cart = new Cart();
            cartRepository.save(cart);
        }
    }
    @Override
    public Cart addProductToCart(String productId) {
        if (cart == null) {
            initCart();
        }
        Item item = new Item(productId, 1);
        item.productId = productId;
        cart.addItem(item);
        cartRepository.save(cart);

//         Sleep for 0.5 second to simulate a slow service
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return cart;
    }

    @Override
    public Cart checkout() {
        if (cart == null) {
            initCart();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        CartDto cartDto = new CartDto();
        cartDto.setItems(cartMapper.toItemsDto(cart.getItems()));
        HttpEntity<CartDto> request = new HttpEntity<>(cartDto, headers);
        Double total = restTemplate.postForObject("lb://pos-counter/api/counter/checkout", request, Double.class);
        cart.setTotal(total);
        cartRepository.save(cart);
        Cart ret = cart;
        cart = null;
        return ret;
    }
}
