package com.example.clienteservice.infraestructure.adapters.outbound;

import com.example.clienteservice.infraestructure.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQProducer {
    private RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendClienteEvent(String message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.CLIENT_EXCHANGE,
                RabbitMQConfig.CLIENT_ROUTING_KEY,
                message);
    }
}
