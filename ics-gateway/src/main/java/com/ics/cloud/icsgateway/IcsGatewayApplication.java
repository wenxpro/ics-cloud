package com.ics.cloud.icsgateway;

import com.ics.cloud.icsgateway.config.HostAddrKeyResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.ics.cloud"})
@ServletComponentScan(basePackages = {"com.ics.cloud"})
public class IcsGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(IcsGatewayApplication.class, args);
    }


    @Bean()
    public HostAddrKeyResolver hostAddrKeyResolver() {
        return new HostAddrKeyResolver();
    }
}
