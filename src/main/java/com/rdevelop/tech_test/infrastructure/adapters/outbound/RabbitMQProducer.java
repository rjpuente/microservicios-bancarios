package com.rdevelop.tech_test.infrastructure.adapters.outbound;

import com.rdevelop.tech_test.infrastructure.config.RabbitMQConfig;
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

    public void sendMovimientoEvent(Object payload) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.MOVIMIENTOS_EXCHANGE,
                RabbitMQConfig.MOVIMIENTOS_ROUTING_KEY,
                payload);
    }
}
