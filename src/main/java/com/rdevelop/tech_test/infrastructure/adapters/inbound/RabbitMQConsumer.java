package com.rdevelop.tech_test.infrastructure.adapters.inbound;

import com.rdevelop.tech_test.infrastructure.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {
    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void receiveMessage(String message) {
        System.out.println("Mensaje recibido de RabbitMQ: " + message);
    }
}
