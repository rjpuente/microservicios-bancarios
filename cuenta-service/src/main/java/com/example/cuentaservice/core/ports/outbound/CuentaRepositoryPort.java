package com.example.cuentaservice.core.ports.outbound;

import com.example.cuentaservice.core.domain.Cuenta;

import java.util.List;
import java.util.Optional;

public interface CuentaRepositoryPort {
    Cuenta save(Cuenta cuenta);

    Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);

    Cuenta findById(Long id);

    List<Cuenta> findCuentasByUsuario(Long idUsuario);

    void delete(Long numeroCuenta);
}
