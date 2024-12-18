package com.rdevelop.tech_test.integration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.org.awaitility.Awaitility;

import java.util.concurrent.TimeUnit;

@Testcontainers
@SpringBootTest(properties = "spring.profiles.active=test")
public abstract class IntegrationTestBase {
    protected static final RabbitMQContainer rabbitMQContainer = new RabbitMQContainer("rabbitmq:3-management")
            .withExposedPorts(5672, 15672);

    @BeforeAll
    static void startContainer() {
        rabbitMQContainer.start();
        System.setProperty("spring.rabbitmq.host", rabbitMQContainer.getHost());
        System.setProperty("spring.rabbitmq.port", rabbitMQContainer.getMappedPort(5672).toString());

        // Espera hasta que RabbitMQ esté listo
        Awaitility.await().atMost(10, TimeUnit.SECONDS).until(rabbitMQContainer::isRunning);
    }

    @AfterAll
    static void stopContainer() {
        rabbitMQContainer.stop();
    }
}
