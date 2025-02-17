package com.rdevelop.tech_test.infrastructure.adapters.outbound;

import com.rdevelop.tech_test.core.domain.Cuenta;
import com.rdevelop.tech_test.core.ports.outbound.CuentaRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CuentaRepositoryAdapter implements CuentaRepositoryPort {
    private final CuentaJpaRepository cuentaJpaRepository;

    @Autowired
    public CuentaRepositoryAdapter(final CuentaJpaRepository cuentaJpaRepository) {
        this.cuentaJpaRepository = cuentaJpaRepository;
    }

    @Override
    public Cuenta save(Cuenta cuenta) {
        return cuentaJpaRepository.save(cuenta); // Mapeo a entidad JPA si difiere
    }

    @Override
    public Optional<Cuenta> findByNumeroCuenta(Long numeroCuenta) {
        return cuentaJpaRepository.findById(numeroCuenta); // idem, mapeo si hace falta
    }

    @Override
    public void delete(Long numeroCuenta) {
        cuentaJpaRepository.deleteById(numeroCuenta);
    }
}
