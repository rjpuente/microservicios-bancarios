package com.rdevelop.tech_test.application.services;

import com.rdevelop.tech_test.core.domain.Cuenta;
import com.rdevelop.tech_test.core.domain.Movimiento;
import com.rdevelop.tech_test.core.domain.TipoMovimiento;
import com.rdevelop.tech_test.core.ports.inbound.MovimientoServicePort;
import com.rdevelop.tech_test.core.ports.outbound.CuentaRepositoryPort;
import com.rdevelop.tech_test.core.ports.outbound.MovimientoRepositoryPort;
import com.rdevelop.tech_test.exceptions.SaldoInsuficienteException;
import com.rdevelop.tech_test.infrastructure.adapters.outbound.RabbitMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovimientoServiceImpl implements MovimientoServicePort {

    private final MovimientoRepositoryPort movimientoRepositoryPort;
    private final CuentaRepositoryPort cuentaRepositoryPort;
    private final RabbitMQProducer rabbitMQProducer;

    @Autowired
    public MovimientoServiceImpl(MovimientoRepositoryPort movimientoRepositoryPort, CuentaRepositoryPort cuentaRepositoryPort,
                                 RabbitMQProducer rabbitMQProducer) {
        this.movimientoRepositoryPort = movimientoRepositoryPort;
        this.cuentaRepositoryPort = cuentaRepositoryPort;
        this.rabbitMQProducer = rabbitMQProducer;
    }

    @Override
    public Movimiento registrarMovimiento(Movimiento movimiento) {

        double saldoActual = movimiento.getCuenta().getSaldoInicial();
        double saldo = saldoActual;

        if (movimiento.getTipo().equals(TipoMovimiento.DEPOSITO)) {
            saldo += movimiento.getValor();
        } else {
            saldo -= movimiento.getValor();
            if (saldo <= 0) {
                throw new SaldoInsuficienteException("Saldo insuficiente");
            }
        }

        movimiento.getCuenta().setSaldoInicial(saldo);
        cuentaRepositoryPort.save(movimiento.getCuenta());

        movimiento.setSaldo(saldo);
        movimiento.setFecha(LocalDateTime.now());
        Movimiento movimientoActual = movimientoRepositoryPort.save(movimiento);

        rabbitMQProducer.sendMovimientoEvent(movimiento.getId());

        return movimientoActual;
    }

    @Override
    public List<Movimiento> obtenerMovimientosPorCuenta(String numeroCuenta) {
        return movimientoRepositoryPort.findByNumeroCuenta(numeroCuenta);
    }

    @Override
    public List<Movimiento> obtenerMovimientosPorFecha(Long numeroCuenta, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return movimientoRepositoryPort.findByNumeroCuentaAndFechaBetween(numeroCuenta, fechaInicio, fechaFin);
    }
}
