package com.ics.cloud.icsapi.feign.client;

import com.ics.cloud.icsapi.config.FeignOAuth2RequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(value = "ics-uaa",configuration = FeignOAuth2RequestInterceptor.class)
public interface UaaFeignService {

    @GetMapping("/test")
    String test();
}
