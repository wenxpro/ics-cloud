package com.ics.cloud.common.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "sys")
public class SysConfig {

    private String tokenExcepPattern;
    private String tokenkeyPrefex;
    private String token;
    private String clientid;
    private String clientsecret;
    private String loginPrefix;

}
