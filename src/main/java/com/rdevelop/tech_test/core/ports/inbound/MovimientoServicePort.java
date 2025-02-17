package com.rdevelop.tech_test.core.ports.inbound;

import com.rdevelop.tech_test.core.domain.Movimiento;

import java.time.LocalDateTime;
import java.util.List;

public interface MovimientoServicePort {
    Movimiento registrarMovimiento(Movimiento movimiento);

    List<Movimiento> obtenerMovimientosPorCuenta(Long numeroCuenta);

    List<Movimiento> obtenerMovimientosPorFecha(Long numeroCuenta, LocalDateTime fechaInicio, LocalDateTime fechaFin);
}
