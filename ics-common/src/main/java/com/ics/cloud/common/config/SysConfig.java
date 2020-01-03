package com.ics.cloud.common.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "sys-config")
public class SysConfig {

    private String tokenExcepPattern;
    private String tokenkeyPrefex;

}
