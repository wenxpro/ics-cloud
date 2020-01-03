package com.ics.cloud.icsapi.feign.client;


import com.ics.cloud.common.bean.JWT;
import com.ics.cloud.icsapi.feign.fullback.UaaFeignServiceFullback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "ics-uaa", fallback = UaaFeignServiceFullback.class)
public interface UaaFeignService {

    @PostMapping("/oauth/token")
    JWT getToken(@RequestParam(value = "client_id", required = true) String cilentid,
                 @RequestParam(value = "client_secret", required = true) String clientsecret,
                 @RequestParam(value = "grant_type", required = true) String type,
                 @RequestParam(value = "username", required = true) String username,
                 @RequestParam(value = "password", required = true) String password);

}
