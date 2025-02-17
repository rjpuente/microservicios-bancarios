package com.rdevelop.tech_test.adapters;

import com.rdevelop.tech_test.infrastructure.adapters.outbound.RabbitMQProducer;
import com.rdevelop.tech_test.infrastructure.config.RabbitMQConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.mockito.Mockito.*;

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
