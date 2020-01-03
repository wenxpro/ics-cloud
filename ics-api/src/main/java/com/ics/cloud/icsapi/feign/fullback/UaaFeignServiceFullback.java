package com.ics.cloud.icsapi.feign.fullback;

import com.ics.cloud.common.bean.JWT;
import com.ics.cloud.icsapi.feign.client.UaaFeignService;
import org.springframework.stereotype.Component;

@Component
public class UaaFeignServiceFullback implements UaaFeignService {

    @Override
    public JWT getToken(String type, String username, String password) {
        return null;
    }
}
