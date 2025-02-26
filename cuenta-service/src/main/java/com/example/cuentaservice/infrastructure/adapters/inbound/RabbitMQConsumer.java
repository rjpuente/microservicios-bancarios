package com.example.cuentaservice.infrastructure.adapters.inbound;

import com.example.cuentaservice.infrastructure.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {
    @RabbitListener(queues = RabbitMQConfig.MOVIMIENTOS_QUEUE)
    public void receiveMessageMoviment(String message) {
        System.out.println("Mensaje recibido de movimiento desde RabbitMQ: " + message);
    }
}
