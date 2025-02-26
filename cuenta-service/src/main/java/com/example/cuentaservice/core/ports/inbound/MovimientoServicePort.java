package com.example.cuentaservice.core.ports.inbound;

import com.example.cuentaservice.core.domain.Movimiento;

import java.time.LocalDateTime;
import java.util.List;

public interface MovimientoServicePort {
    Movimiento registrarMovimiento(Movimiento movimiento);

    List<Movimiento> obtenerMovimientosPorCuenta(String numeroCuenta);

    List<Movimiento> obtenerMovimientosPorFecha(Long numeroCuenta, LocalDateTime fechaInicio, LocalDateTime fechaFin);
}
