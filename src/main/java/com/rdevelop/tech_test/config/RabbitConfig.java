package com.rdevelop.tech_test.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue movimientosQueue() {
        return new Queue("movimientosQueue", true);
    }
}