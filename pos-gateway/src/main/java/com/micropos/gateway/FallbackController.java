package com.micropos.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @GetMapping("/fallback/nothing")
    public String fallbackNothing() {
        return "Nothing to see here.";
    }
}