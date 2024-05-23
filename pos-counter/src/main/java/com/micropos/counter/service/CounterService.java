package com.micropos.counter.service;

import com.micropos.dto.CartDto;
import com.micropos.dto.CartItemDto;
import com.micropos.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CounterService {

    private RestTemplate restTemplate;
    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public double checkout(CartDto cart) {
        double total = 0;
        for (CartItemDto item : cart.getItems()) {
           String id = item.getProductId();
//           Get ProductDto using rest template
           ProductDto product = restTemplate.getForObject("lb://pos-products/api/products/" + id, ProductDto.class);
           total += product.getPrice() * item.getQuantity();
        }
        return total;
    }
}
