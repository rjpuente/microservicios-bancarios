package com.rdevelop.tech_test.application;

import com.rdevelop.tech_test.core.domain.Cliente;
import com.rdevelop.tech_test.core.ports.ClienteServicePort;
import com.rdevelop.tech_test.exceptions.BusinessValidationException;
import com.rdevelop.tech_test.exceptions.EntityNotFoundException;
import com.rdevelop.tech_test.infrastructure.adapters.inbound.RabbitMQConsumer;
import com.rdevelop.tech_test.infrastructure.adapters.outbound.ClienteRepository;
import com.rdevelop.tech_test.infrastructure.adapters.outbound.RabbitMQProducer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService implements ClienteServicePort {
    private final ClienteRepository clienteRepository;
    private final RabbitMQProducer rabbitMQProducer;

    public ClienteService(ClienteRepository repository, RabbitMQProducer rabbitMQProducer) {
        this.clienteRepository = repository;
        this.rabbitMQProducer = rabbitMQProducer;
    }

    @Override
    public Cliente createCliente(Cliente cliente) {
        validateCliente(cliente);
        Cliente savedCliente = clienteRepository.save(cliente);

        rabbitMQProducer.sendMessage("Cliente creado: " + savedCliente.getClienteId());

        return savedCliente;
    }

    @Override
    public Cliente getClienteById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente con ID " + id + " no encontrado."));
    }

    private void validateCliente(Cliente cliente) {
        if (cliente.getClienteId() == null) {
            throw new BusinessValidationException("El ID del cliente es obligatorio.");
        }
        if (clienteRepository.findAll().stream()
                .anyMatch(c -> c.getClienteId().equals(cliente.getClienteId()))) {
            throw new BusinessValidationException("Ya existe un cliente con ese ID.");
        }
    }

}
