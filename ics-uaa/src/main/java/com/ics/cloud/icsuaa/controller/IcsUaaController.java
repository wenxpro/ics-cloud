package com.ics.cloud.icsuaa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class IcsUaaController {

    @GetMapping("/getPrincipal")
    public Principal user(Principal principal) {
        return principal;
    }

    @GetMapping("/test")
    public String test(){
        return "test ";
    }
}
