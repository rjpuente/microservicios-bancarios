package com.example.clienteservice.core.ports.inbound;


import com.example.clienteservice.core.domain.Cliente;

public interface ClienteServicePort {
    Cliente createCliente(Cliente cliente);

    Cliente getClienteById(Long id);
}
