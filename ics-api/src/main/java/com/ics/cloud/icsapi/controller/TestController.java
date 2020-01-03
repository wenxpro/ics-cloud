package com.ics.cloud.icsapi.controller;

import com.ics.cloud.icsapi.feign.client.UaaFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;

@RestController
public class TestController {

    @Autowired
    private UaaFeignService uaaFeignService;

    @GetMapping("/test")
    public String test(){
        return uaaFeignService.test();
    }
}
