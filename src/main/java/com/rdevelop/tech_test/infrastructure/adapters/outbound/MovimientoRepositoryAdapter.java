package com.rdevelop.tech_test.infrastructure.adapters.outbound;

import com.rdevelop.tech_test.core.domain.Movimiento;
import com.rdevelop.tech_test.core.ports.outbound.MovimientoRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class MovimientoRepositoryAdapter implements MovimientoRepositoryPort {

    private final MovimientoJpaRepository movimientoJpaRepository;

    @Autowired
    public MovimientoRepositoryAdapter(MovimientoJpaRepository movimientoJpaRepository) {
        this.movimientoJpaRepository = movimientoJpaRepository;
    }

    @Override
    public Movimiento save(Movimiento movimiento) {
        return movimientoJpaRepository.save(movimiento);
    }

    @Override
    public Optional<Movimiento> findById(Long id) {
        return movimientoJpaRepository.findById(id);
    }

    @Override
    public List<Movimiento> findByNumeroCuenta(Long numeroCuenta) {
        return movimientoJpaRepository.findByNumeroCuenta(numeroCuenta);
    }

    @Override
    public List<Movimiento> findByNumeroCuentaAndFechaBetween(Long numeroCuenta, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return movimientoJpaRepository.findByClienteIdAndFechaBetween(numeroCuenta, fechaInicio, fechaFin);
    }
}