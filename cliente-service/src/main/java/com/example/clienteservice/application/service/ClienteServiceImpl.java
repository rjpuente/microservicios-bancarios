package com.example.clienteservice.application.service;

import com.example.clienteservice.core.domain.Cliente;
import com.example.clienteservice.core.ports.inbound.ClienteServicePort;
import com.example.clienteservice.exceptions.BusinessValidationException;
import com.example.clienteservice.exceptions.EntityNotFoundException;
import com.example.clienteservice.infraestructure.adapters.outbound.ClienteRepository;
import com.example.clienteservice.infraestructure.adapters.outbound.RabbitMQProducer;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteServicePort {
    private final ClienteRepository clienteRepository;
    private final RabbitMQProducer rabbitMQProducer;

    public ClienteServiceImpl(ClienteRepository repository, RabbitMQProducer rabbitMQProducer) {
        this.clienteRepository = repository;
        this.rabbitMQProducer = rabbitMQProducer;
    }

    @Override
    public Cliente createCliente(Cliente cliente) {
        validateCliente(cliente);
        Cliente savedCliente = clienteRepository.save(cliente);

        rabbitMQProducer.sendClienteEvent("Cliente creado: " + savedCliente.getId());

        return savedCliente;
    }

    @Override
    public Cliente getClienteById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente con ID " + id + " no encontrado."));
    }

    private void validateCliente(Cliente cliente) {
        if (clienteRepository.findAll().stream()
                .anyMatch(c -> c.getIdentificacion().equals(cliente.getIdentificacion()))) {
            throw new BusinessValidationException("Ya existe un cliente con ese ID.");
        }
    }

}
