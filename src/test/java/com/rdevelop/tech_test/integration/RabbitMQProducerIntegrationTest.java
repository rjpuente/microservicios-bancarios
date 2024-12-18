package com.rdevelop.tech_test.integration;

import com.rdevelop.tech_test.infrastructure.adapters.outbound.RabbitMQProducer;
import com.rdevelop.tech_test.infrastructure.config.RabbitMQConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.shaded.org.awaitility.Awaitility;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = "spring.profiles.active=test")
public class RabbitMQProducerIntegrationTest extends IntegrationTestBase {

    @Autowired
    private RabbitMQProducer rabbitMQProducer;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpAdmin amqpAdmin;

    private Queue temporaryQueue;

    @BeforeEach
    void setup() {
        temporaryQueue = new Queue("test.temp.queue", false, true, true);
        amqpAdmin.declareQueue(temporaryQueue);
    }

    @AfterEach
    void cleanup() {
        if (temporaryQueue != null) {
            amqpAdmin.deleteQueue(temporaryQueue.getName());
        }
    }
/*
    @Test
    void sendMessageShouldSendMessageToTemporaryQueue() {
        // Arrange
        String testMessage = "testMessage";

        // Act
        rabbitTemplate.convertAndSend(temporaryQueue.getName(), testMessage);

        // Assert
        Awaitility.await().atMost(5, TimeUnit.SECONDS).untilAsserted(() -> {
            Message receivedMessage = rabbitTemplate.receive(temporaryQueue.getName());
            assertNotNull(receivedMessage, "No se recibió ningún mensaje en la cola temporal.");
            String body = new String(receivedMessage.getBody());
            assertEquals(testMessage, body, "El mensaje recibido no coincide con el mensaje enviado.");
        });
    }

 */
}
