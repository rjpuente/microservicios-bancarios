package com.rdevelop.tech_test.core.ports;

import com.rdevelop.tech_test.core.domain.Cliente;

import java.util.List;

public interface ClienteServicePort {
    Cliente createCliente(Cliente cliente);

    Cliente getClienteById(Long id);
}
