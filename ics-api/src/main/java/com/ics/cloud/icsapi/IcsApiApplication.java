package com.ics.cloud.icsapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"com.ics.cloud"})
@ServletComponentScan(basePackages = {"com.ics.cloud"})
@MapperScan(basePackages = {"com.ics.cloud.common.dao"})
public class IcsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(IcsApiApplication.class, args);
    }

}
