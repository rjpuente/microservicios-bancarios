package com.rdevelop.tech_test.integration;

import com.rdevelop.tech_test.infrastructure.adapters.outbound.RabbitMQProducer;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
public class RabbitMQProducerIntegrationTest extends IntegrationTestBase {

    @Autowired
    private RabbitMQProducer rabbitMQProducer;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void sendMessageShouldSendMessageToQueue() {
        String testMessage = "testMessage";

        assertDoesNotThrow(() -> rabbitMQProducer.sendMessage(testMessage));
    }
}
