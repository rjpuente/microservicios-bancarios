package com.rdevelop.tech_test.infrastructure.adapters.inbound;

import com.rdevelop.tech_test.infrastructure.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {
    @RabbitListener(queues = RabbitMQConfig.CLIENT_QUEUE)
    public void receiveMessage(String message) {
        System.out.println("Mensaje recibido para cliente de RabbitMQ: " + message);
    }

    @RabbitListener(queues = RabbitMQConfig.MOVIMIENTOS_QUEUE)
    public void receiveMessageMoviment(String message) {
        System.out.println("Mensaje recibido de movimiento desde RabbitMQ: " + message);
    }
}
