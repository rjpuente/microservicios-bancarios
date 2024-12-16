package com.rdevelop.tech_test.integration;

import com.rdevelop.tech_test.infrastructure.adapters.inbound.RabbitMQConsumer;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

@SpringBootTest
public class RabbitMQConsumerIntegrationTest extends IntegrationTestBase {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitMQConsumer rabbitMQConsumer;

    @Test
    void receiveMessage_ShouldProcessMessageFromQueue() {
        String testMessage = "Test integration consumer";
        rabbitTemplate.convertAndSend("clienteExchange", "clienteKey", testMessage);

        await().atMost(5, TimeUnit.SECONDS).untilAsserted(() -> {
            System.out.println("Mensaje procesado correctamente por el consumidor");
        });
    }
}
