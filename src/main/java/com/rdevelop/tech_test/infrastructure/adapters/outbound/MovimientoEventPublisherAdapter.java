package com.rdevelop.tech_test.infrastructure.adapters.outbound;

import com.rdevelop.tech_test.application.DTO.MovimientoEvento;
import com.rdevelop.tech_test.core.domain.Movimiento;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovimientoEventPublisherAdapter implements MovimientoEventPublisherPort {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public MovimientoEventPublisherAdapter(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publishMovimientoCreated(Movimiento movimiento) {
        MovimientoEvento evento = new MovimientoEvento(movimiento.getId(),
                movimiento.getFecha(),
                "Movimiento registrado",
                movimiento.getSaldo());

        rabbitTemplate.convertAndSend("movimientosExchange", "movimientos.rk", evento);
    }
}
