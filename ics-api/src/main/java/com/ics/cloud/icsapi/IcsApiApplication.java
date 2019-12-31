package com.ics.cloud.icsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class IcsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(IcsApiApplication.class, args);
    }

}
