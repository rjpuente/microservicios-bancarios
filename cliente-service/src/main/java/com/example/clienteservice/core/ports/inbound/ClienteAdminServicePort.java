package com.example.clienteservice.core.ports.inbound;


import com.example.clienteservice.core.domain.Cliente;

import java.util.List;

public interface ClienteAdminServicePort {
    List<Cliente> getAllClientes();
    void deleteCliente(Long id);
}
