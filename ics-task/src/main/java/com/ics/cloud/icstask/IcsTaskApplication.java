package com.ics.cloud.icstask;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ics.cloud"})
@ServletComponentScan(basePackages = {"com.ics.cloud"})
@MapperScan(basePackages = {"com.ics.cloud.common.dao"})
public class IcsTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(IcsTaskApplication.class, args);
    }

}
