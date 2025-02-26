package com.example.cuentaservice.infrastructure.adapters.outbound;

import com.example.cuentaservice.infrastructure.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQProducer {
    private RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMovimientoEvent(Object payload) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.MOVIMIENTOS_EXCHANGE,
                RabbitMQConfig.MOVIMIENTOS_ROUTING_KEY,
                payload);
    }
}
