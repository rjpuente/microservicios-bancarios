package com.example.clienteservice.infraestructure.adapters.inbound;

import com.example.clienteservice.infraestructure.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {
    @RabbitListener(queues = RabbitMQConfig.CLIENT_QUEUE)
    public void receiveMessage(String message) {
        System.out.println("Mensaje recibido para cliente de RabbitMQ: " + message);
    }
}
