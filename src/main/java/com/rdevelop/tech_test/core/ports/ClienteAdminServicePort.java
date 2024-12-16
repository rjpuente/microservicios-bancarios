package com.rdevelop.tech_test.core.ports;

import com.rdevelop.tech_test.core.domain.Cliente;

import java.util.List;

public interface ClienteAdminServicePort {
    List<Cliente> getAllClientes();
    void deleteCliente(Long id);
}
