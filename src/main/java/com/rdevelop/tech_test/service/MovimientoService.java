package com.rdevelop.tech_test.service;

import com.rdevelop.tech_test.entity.Cuenta;
import com.rdevelop.tech_test.entity.Movimiento;
import com.rdevelop.tech_test.events.MovimientoEvento;
import com.rdevelop.tech_test.exceptions.CuentaNoEncontradaException;
import com.rdevelop.tech_test.exceptions.SaldoInsuficienteException;
import com.rdevelop.tech_test.repository.CuentaRepository;
import com.rdevelop.tech_test.repository.MovimientoRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public MovimientoService(MovimientoRepository movimientoRepository,
                             CuentaRepository cuentaRepository,
                             RabbitTemplate rabbitTemplate) {
        this.movimientoRepository = movimientoRepository;
        this.cuentaRepository = cuentaRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public List<Movimiento> findAllMovimientos() {
        return movimientoRepository.findAll();
    }

    public Optional<Movimiento> findMovimientoById(Long id) {
        return movimientoRepository.findById(id);
    }

    public Movimiento registrarMovimiento(Movimiento movimiento) {
        Long cuentaId = movimiento.getCuenta().getNumeroCuenta();

        Cuenta cuenta = cuentaRepository.findById(cuentaId)
                .orElseThrow(() -> new CuentaNoEncontradaException("No se encontró la cuenta: " + cuentaId));

        validarSaldo(cuenta, movimiento);

        actualizarSaldoCuenta(cuenta, movimiento.getValor());

        movimiento.setSaldo(cuenta.getSaldoInicial());
        Movimiento savedMovimiento = movimientoRepository.save(movimiento);

        // Enviar un mensaje a RabbitMQ
        // Enviar evento a RabbitMQ
        MovimientoEvento evento = new MovimientoEvento(savedMovimiento.getId(), "Movimiento registrado");
        rabbitTemplate.convertAndSend("movimientosQueue", evento);

        return savedMovimiento;
    }

    private void validarSaldo(Cuenta cuenta, Movimiento movimiento) {
        if ("Retiro".equalsIgnoreCase(movimiento.getTipoMovimiento()) && cuenta.getSaldoInicial() < movimiento.getValor()) {
            throw new SaldoInsuficienteException("Saldo no disponible para la cuenta: " + cuenta.getNumeroCuenta());
        }
    }

    private void actualizarSaldoCuenta(Cuenta cuenta, double valorMovimiento) {
        cuenta.setSaldoInicial(cuenta.getSaldoInicial() + valorMovimiento);
        cuentaRepository.save(cuenta);
    }


    public Movimiento updateMovimiento(Movimiento movimiento) {
        return movimientoRepository.save(movimiento);
    }

    public void deleteMovimiento(Movimiento movimiento) {
        movimientoRepository.delete(movimiento);
    }

    public List<Movimiento> obtenerMovimientosPorClienteYRangoDeFechas(Long clienteId, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return movimientoRepository.findMovimientosByClienteAndFechaBetween(clienteId, fechaInicio, fechaFin);
    }

}
