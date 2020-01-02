package com.ics.cloud.icsuaa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAuthorizationServer
@ServletComponentScan(basePackages = {"com.inc.cloud"})
@MapperScan(basePackages = {"com.ics.cloud.common.dao"})
public class IcsUaaApplication {

    public static void main(String[] args) {
        SpringApplication.run(IcsUaaApplication.class, args);
    }

}
