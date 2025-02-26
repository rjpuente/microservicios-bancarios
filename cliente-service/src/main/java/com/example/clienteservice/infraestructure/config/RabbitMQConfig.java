package com.example.clienteservice.infraestructure.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Constantes para Cliente
    public static final String CLIENT_QUEUE = "ClientQueue";
    public static final String CLIENT_EXCHANGE = "clienteExchange";
    public static final String CLIENT_ROUTING_KEY = "ClienteKey";

    @Bean
    public Queue clientQueue() {
        return QueueBuilder.durable(CLIENT_QUEUE).build();
    }

    @Bean
    public TopicExchange clientExchange() {
        return ExchangeBuilder.topicExchange(CLIENT_EXCHANGE).durable(true).build();
    }

    @Bean
    public Binding clientBinding(Queue clientQueue, TopicExchange clientExchange) {
        return BindingBuilder.bind(clientQueue)
                .to(clientExchange)
                .with(CLIENT_ROUTING_KEY);
    }
}

