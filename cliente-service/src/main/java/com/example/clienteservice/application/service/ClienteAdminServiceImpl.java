package com.example.clienteservice.application.service;

import com.example.clienteservice.core.domain.Cliente;
import com.example.clienteservice.core.ports.inbound.ClienteAdminServicePort;
import com.example.clienteservice.exceptions.EntityNotFoundException;
import com.example.clienteservice.infraestructure.adapters.outbound.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteAdminServiceImpl implements ClienteAdminServicePort {
    private final ClienteRepository clienteRepository;

    public ClienteAdminServiceImpl(ClienteRepository clienteRepository) {
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
