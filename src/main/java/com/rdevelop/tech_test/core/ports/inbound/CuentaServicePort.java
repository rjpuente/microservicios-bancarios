package com.rdevelop.tech_test.core.ports.inbound;

import com.rdevelop.tech_test.core.domain.Cliente;
import com.rdevelop.tech_test.core.domain.Cuenta;

import java.util.List;
import java.util.Optional;

public interface CuentaServicePort {
    Cuenta crearCuenta(Cuenta cuenta);

    Optional<Cuenta> obtenerCuentaPorNumero(String numeroCuenta);

    List<Cuenta> obtenerCuentasPorCliente(Long idCliente);

    Cuenta actualizarCuenta(Cuenta cuenta);

    void eliminarCuenta(Long numeroCuenta);
}
