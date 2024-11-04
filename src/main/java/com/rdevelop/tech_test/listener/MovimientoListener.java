package com.rdevelop.tech_test.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rdevelop.tech_test.events.MovimientoEvento;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component
public class MovimientoListener {

    private static final Logger logger = LoggerFactory.getLogger(MovimientoListener.class);
    private static final int MAX_RETRY_ATTEMPTS = 3;

    @RabbitListener(queues = "movimientosQueue")
    @Retryable(
            value = {Exception.class},
            maxAttempts = MAX_RETRY_ATTEMPTS,
            backoff = @Backoff(delay = 2000, multiplier = 2)
    )
    public void handleMovimiento(String message) {
        try {
            logger.info("Procesando mensaje: {}", message);
            ObjectMapper objectMapper = new ObjectMapper();
            MovimientoEvento evento = objectMapper.readValue(message, MovimientoEvento.class);
            processEvento(evento);
        } catch (Exception e) {
            logger.error("Error al procesar el mensaje: {}", e.getMessage(), e);
            throw new AmqpRejectAndDontRequeueException("Error crítico, no reintentar", e);
        }
    }

    private void processEvento(MovimientoEvento evento) {
        logger.info("Evento procesado correctamente: ID={}, Descripción={}", evento.getMovimientoId(), evento.getDescripcion());
    }
}