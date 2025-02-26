package com.example.cuentaservice.infrastructure.adapters.outbound;

import com.example.cuentaservice.core.domain.Cuenta;
import com.example.cuentaservice.core.ports.outbound.CuentaRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
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
        return cuentaJpaRepository.save(cuenta);
    }

    @Override
    public Optional<Cuenta> findByNumeroCuenta(String numeroCuenta) {
        return Optional.ofNullable(cuentaJpaRepository.findByNumeroCuenta(numeroCuenta)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada")));
    }

    @Override
    public Cuenta findById(Long id) {
        return cuentaJpaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Cuenta> findCuentasByUsuario(Long idUsuario) {
        return cuentaJpaRepository.findByClienteId(idUsuario);
    }

    @Override
    public void delete(Long numeroCuenta) {
        cuentaJpaRepository.deleteById(numeroCuenta);
    }
}
