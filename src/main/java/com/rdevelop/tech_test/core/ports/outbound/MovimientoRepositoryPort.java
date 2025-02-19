package com.rdevelop.tech_test.core.ports.outbound;

import com.rdevelop.tech_test.core.domain.Movimiento;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MovimientoRepositoryPort {
    Movimiento save(Movimiento movimiento);

    Optional<Movimiento> findById(Long id);

    List<Movimiento> findByNumeroCuenta(String numeroCuenta);

    List<Movimiento> findByNumeroCuentaAndFechaBetween(Long numeroCuenta, LocalDateTime fechaInicio, LocalDateTime fechaFin);
}
