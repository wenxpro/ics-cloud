package com.ics.cloud.common.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MpQueueConfig {

    @Bean
    public Queue queue() {
        return new Queue("log-queue");
    }
}
