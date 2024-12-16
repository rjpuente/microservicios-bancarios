package com.rdevelop.tech_test.application;

import com.rdevelop.tech_test.core.domain.Cliente;
import com.rdevelop.tech_test.core.ports.ClienteAdminServicePort;
import com.rdevelop.tech_test.exceptions.EntityNotFoundException;
import com.rdevelop.tech_test.infrastructure.adapters.outbound.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteAdminService implements ClienteAdminServicePort {
    private final ClienteRepository clienteRepository;

    public ClienteAdminService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public void deleteCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new EntityNotFoundException("Cliente con ID " + id + " no encontrado para eliminar.");
        }
        clienteRepository.deleteById(id);
    }
}
