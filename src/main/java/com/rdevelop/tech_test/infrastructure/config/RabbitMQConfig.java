package com.rdevelop.tech_test.infrastructure.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Constantes para Cliente
    public static final String CLIENT_QUEUE = "ClientQueue";
    public static final String CLIENT_EXCHANGE = "clienteExchange";
    public static final String CLIENT_ROUTING_KEY = "ClienteKey";

    // Constantes para Movimientos
    public static final String MOVIMIENTOS_QUEUE = "movimientosQueue";
    public static final String MOVIMIENTOS_EXCHANGE = "movimientosExchange";
    public static final String MOVIMIENTOS_ROUTING_KEY = "movimientos.rk";

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

    @Bean
    public Queue movimientosQueue() {
        return QueueBuilder.durable(MOVIMIENTOS_QUEUE).build();
    }

    @Bean
    public TopicExchange movimientosExchange() {
        return ExchangeBuilder.topicExchange(MOVIMIENTOS_EXCHANGE).durable(true).build();
    }

    @Bean
    public Binding movimientosBinding(Queue movimientosQueue, TopicExchange movimientosExchange) {
        return BindingBuilder.bind(movimientosQueue)
                .to(movimientosExchange)
                .with(MOVIMIENTOS_ROUTING_KEY);
    }
}

