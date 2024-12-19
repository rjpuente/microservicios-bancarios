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
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.org.awaitility.Awaitility;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringBootTest(properties = "spring.profiles.active=test")
public class RabbitMQProducerIntegrationTest extends IntegrationTestBase {

    @Container
    private static final RabbitMQContainer rabbitMQContainer = new RabbitMQContainer("rabbitmq:3-management")
            .withExposedPorts(5672, 15672);

    @Autowired
    private RabbitMQProducer rabbitMQProducer;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpAdmin amqpAdmin;

    private Queue temporaryQueue;

    @DynamicPropertySource
    static void registerRabbitMQProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.rabbitmq.host", rabbitMQContainer::getHost);
        registry.add("spring.rabbitmq.port", rabbitMQContainer::getAmqpPort);
    }

    @BeforeEach
    void setup() {
        // Espera a que el contenedor esté listo antes de configurar la cola
        Awaitility.await().atMost(10, TimeUnit.SECONDS).until(() -> {
            try {
                rabbitTemplate.execute(channel -> true);
                return true;
            } catch (Exception e) {
                return false;
            }
        });

        temporaryQueue = new Queue("test.temp.queue", false, true, true);
        amqpAdmin.declareQueue(temporaryQueue);
    }

    @AfterEach
    void cleanup() {
        if (temporaryQueue != null) {
            amqpAdmin.deleteQueue(temporaryQueue.getName());
        }
    }

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
}
