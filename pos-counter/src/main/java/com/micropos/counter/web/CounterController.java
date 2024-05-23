package com.micropos.counter.web;

import com.micropos.api.CounterApi;
import com.micropos.counter.service.CounterService;
import com.micropos.dto.CartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("api")
public class CounterController implements CounterApi {

    private CounterService counterService;

    @Autowired
    public void setCounterService(CounterService counterService) {
        this.counterService = counterService;
    }

    @Override
    public ResponseEntity<Double> checkoutOrder(CartDto cartDto) {
        System.out.println("checkoutOrder");
        Double finalPrice =  counterService.checkout(cartDto);
        System.out.println(finalPrice);
        return ResponseEntity.ok(finalPrice);
    }

}
