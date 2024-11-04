package com.rdevelop.tech_test.service;

import com.rdevelop.tech_test.entity.Cliente;
import com.rdevelop.tech_test.entity.Cuenta;
import com.rdevelop.tech_test.repository.ClienteRepository;
import com.rdevelop.tech_test.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaService {

    private final CuentaRepository cuentaRepository;
    private final ClienteRepository clienteRepository;

    @Autowired
    public CuentaService(CuentaRepository cuentaRepository, ClienteRepository clienteRepository) {
        this.cuentaRepository = cuentaRepository;
        this.clienteRepository = clienteRepository;
    }

    public List<Cuenta> findAllCuenta() {
        return cuentaRepository.findAll();
    }

    public Optional<Cuenta> findCuentaById(Long id) {
        return cuentaRepository.findById(id);
    }

    public Cuenta createCuenta(Cuenta cuenta) {
        Cliente cliente = clienteRepository.findById(cuenta.getCliente().getClienteId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
        cuenta.setCliente(cliente);
        return cuentaRepository.save(cuenta);
    }

    public Cuenta updateCuenta(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    public void deleteCuenta(Long id) {
        cuentaRepository.deleteById(id);
    }

}
