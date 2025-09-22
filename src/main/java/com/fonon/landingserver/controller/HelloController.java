package com.fonon.landingserver.controller;

import com.fonon.landingserver.web.dto.HelloResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public HelloResponse hello() {
        return new HelloResponse("Hello World!");
    }
}
