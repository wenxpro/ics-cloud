package com.ics.cloud.icsapi.controller;

import com.ics.cloud.icsapi.feign.client.IcsUaaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    IcsUaaService icsUaaService;

    @GetMapping("/sayHi")
    public String sayHi(){
        return icsUaaService.hi("wenx");
    }
}
