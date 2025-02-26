package com.example.cuentaservice.core.ports.inbound;

import com.example.cuentaservice.core.domain.Cuenta;

import java.util.List;
import java.util.Optional;

public interface CuentaServicePort {
    Cuenta crearCuenta(Cuenta cuenta);

    Optional<Cuenta> obtenerCuentaPorNumero(String numeroCuenta);

    List<Cuenta> obtenerCuentasPorCliente(Long idCliente);

    Cuenta obtenerCuentaPorId(Long idCuenta);

    Cuenta actualizarCuenta(Cuenta cuenta);

    void eliminarCuenta(Long numeroCuenta);
}
