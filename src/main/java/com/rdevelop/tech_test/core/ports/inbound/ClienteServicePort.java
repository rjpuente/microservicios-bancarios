package com.rdevelop.tech_test.core.ports.inbound;

import com.rdevelop.tech_test.core.domain.Cliente;

public interface ClienteServicePort {
    Cliente createCliente(Cliente cliente);

    Cliente getClienteById(Long id);
}
