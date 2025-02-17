package com.rdevelop.tech_test.application.services;

import com.rdevelop.tech_test.core.domain.Cuenta;
import com.rdevelop.tech_test.core.ports.inbound.CuentaServicePort;
import com.rdevelop.tech_test.core.ports.outbound.CuentaRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaServiceImpl implements CuentaServicePort {

    private final CuentaRepositoryPort cuentaRepositoryPort;

    @Autowired
    public CuentaServiceImpl(CuentaRepositoryPort cuentaRepositoryPort) {
        this.cuentaRepositoryPort = cuentaRepositoryPort;
    }

    @Override
    public Cuenta crearCuenta(Cuenta cuenta) {
        return cuentaRepositoryPort.save(cuenta);
    }

    @Override
    public Optional<Cuenta> obtenerCuentaPorNumero(String numeroCuenta) {
        return Optional.ofNullable(cuentaRepositoryPort.findByNumeroCuenta(numeroCuenta)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada")));
    }


    @Override
    public List<Cuenta> obtenerCuentasPorCliente(Long idCliente) {
        return cuentaRepositoryPort.findCuentasByUsuario(idCliente);
    }

    @Override
    public Cuenta actualizarCuenta(Cuenta cuenta) {
        return cuentaRepositoryPort.save(cuenta);
    }

    @Override
    public void eliminarCuenta(Long numeroCuenta) {
        cuentaRepositoryPort.delete(numeroCuenta);
    }
}
