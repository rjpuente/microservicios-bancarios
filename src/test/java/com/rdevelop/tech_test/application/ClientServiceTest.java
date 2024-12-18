package com.rdevelop.tech_test.application;

import com.rdevelop.tech_test.core.domain.Cliente;
import com.rdevelop.tech_test.exceptions.BusinessValidationException;
import com.rdevelop.tech_test.exceptions.EntityNotFoundException;
import com.rdevelop.tech_test.infrastructure.adapters.outbound.ClienteRepository;
import com.rdevelop.tech_test.infrastructure.adapters.outbound.RabbitMQProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ClientServiceTest {
    private ClienteService clienteService;
    private ClienteRepository clienteRepository;
    private RabbitMQProducer rabbitMQProducer;

    @BeforeEach
    void setUp() {
        clienteRepository = mock(ClienteRepository.class);
        rabbitMQProducer = mock(RabbitMQProducer.class);
        clienteService = new ClienteService(clienteRepository, rabbitMQProducer);
    }

    @Test
    void createClienteShouldSaveClienteAndSendMessageWhenValidCliente() {
        Cliente cliente = new Cliente();
        cliente.setClienteId(123l);
        when(clienteRepository.findAll()).thenReturn(Collections.emptyList());
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente savedCliente = clienteService.createCliente(cliente);

        assertNotNull(savedCliente);
        verify(clienteRepository).save(cliente);
        verify(rabbitMQProducer).sendMessage("Cliente creado: " + cliente.getClienteId());
    }

    @Test
    void createClienteShouldThrowBusinessValidationExceptionWhenClienteIdIsNull() {
        Cliente cliente = new Cliente();
        BusinessValidationException exception = assertThrows(BusinessValidationException.class,
                () -> clienteService.createCliente(cliente));
        assertEquals("El ID del cliente es obligatorio.", exception.getMessage());
    }

    @Test
    void createClienteShouldThrowBusinessValidationExceptionWhenClienteIdAlreadyExists() {
        Cliente cliente = new Cliente();
        cliente.setClienteId(123l);
        when(clienteRepository.findAll()).thenReturn(Collections.singletonList(cliente));

        BusinessValidationException exception = assertThrows(BusinessValidationException.class,
                () -> clienteService.createCliente(cliente));
        assertEquals("Ya existe un cliente con ese ID.", exception.getMessage());
    }

    @Test
    void getClienteByIdShouldReturnClienteWhenClienteExists() {
        Cliente cliente = new Cliente();
        cliente.setClienteId(123l);
        when(clienteRepository.findById(123l)).thenReturn(Optional.of(cliente));

        Cliente foundCliente = clienteService.getClienteById(123l);

        assertNotNull(foundCliente);
        assertEquals(123l, foundCliente.getClienteId());
        verify(clienteRepository).findById(123l);
    }

    @Test
    void getClienteById_ShouldThrowEntityNotFoundException_WhenClienteDoesNotExist() {
        when(clienteRepository.findById(123l)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> clienteService.getClienteById(1L));
        assertEquals("Cliente con ID 1 no encontrado.", exception.getMessage());
    }
}
