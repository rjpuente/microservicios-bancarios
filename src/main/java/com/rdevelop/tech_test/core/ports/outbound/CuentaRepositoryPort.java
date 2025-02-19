package com.rdevelop.tech_test.core.ports.outbound;

import com.rdevelop.tech_test.core.domain.Cuenta;

import java.util.List;
import java.util.Optional;

public interface CuentaRepositoryPort {
    Cuenta save(Cuenta cuenta);

    Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);

    Cuenta findById(Long id);

    List<Cuenta> findCuentasByUsuario(Long idUsuario);

    void delete(Long numeroCuenta);
}
