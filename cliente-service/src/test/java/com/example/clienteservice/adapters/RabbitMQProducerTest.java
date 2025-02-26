package com.example.clienteservice.adapters;

import com.example.clienteservice.infraestructure.config.RabbitMQConfig;
import com.example.clienteservice.infraestructure.adapters.outbound.RabbitMQProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RabbitMQProducerTest {
    private RabbitMQProducer rabbitMQProducer;
    private RabbitTemplate rabbitTemplate;

    @BeforeEach
    void setUp() {
        rabbitTemplate = mock(RabbitTemplate.class);
        rabbitMQProducer = new RabbitMQProducer(rabbitTemplate);
    }

    @Test
    void sendMessage_ShouldSendMessageToRabbitMQ() {
        String message = "Test message";

        rabbitMQProducer.sendClienteEvent(message);

        verify(rabbitTemplate).convertAndSend(RabbitMQConfig.CLIENT_EXCHANGE, RabbitMQConfig.CLIENT_ROUTING_KEY, message);
    }
}
