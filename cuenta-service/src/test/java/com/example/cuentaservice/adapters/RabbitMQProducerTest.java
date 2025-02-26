package com.example.cuentaservice.adapters;

import com.example.cuentaservice.infrastructure.adapters.outbound.RabbitMQProducer;
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
}
