package com.example.clienteservice.application;

import com.example.clienteservice.application.service.ClienteServiceImpl;
import com.example.clienteservice.core.domain.Cliente;
import com.example.clienteservice.exceptions.BusinessValidationException;
import com.example.clienteservice.exceptions.EntityNotFoundException;
import com.example.clienteservice.infraestructure.adapters.outbound.ClienteRepository;
import com.example.clienteservice.infraestructure.adapters.outbound.RabbitMQProducer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ClientServiceTest {
    private ClienteServiceImpl clienteServiceImpl;
    private ClienteRepository clienteRepository;
    private RabbitMQProducer rabbitMQProducer;

    @BeforeEach
    void setUp() {
        clienteRepository = mock(ClienteRepository.class);
        rabbitMQProducer = mock(RabbitMQProducer.class);
        clienteServiceImpl = new ClienteServiceImpl(clienteRepository, rabbitMQProducer);
    }

    @Test
    void createClienteShouldSaveClienteAndSendMessageWhenValidCliente() {
        Cliente cliente = new Cliente();
        when(clienteRepository.findAll()).thenReturn(Collections.emptyList());
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente savedCliente = clienteServiceImpl.createCliente(cliente);

        assertNotNull(savedCliente);
        verify(clienteRepository).save(cliente);
        verify(rabbitMQProducer).sendClienteEvent("Cliente creado: " + cliente.getId());
    }

    @Test
    void createClienteShouldThrowBusinessValidationExceptionWhenClienteIdIsNull() {
        Cliente cliente = new Cliente();
        BusinessValidationException exception = assertThrows(BusinessValidationException.class,
                () -> clienteServiceImpl.createCliente(cliente));
        Assertions.assertEquals("El ID del cliente es obligatorio.", exception.getMessage());
    }

    @Test
    void createClienteShouldThrowBusinessValidationExceptionWhenClienteIdAlreadyExists() {
        Cliente cliente = new Cliente();
        when(clienteRepository.findAll()).thenReturn(Collections.singletonList(cliente));

        BusinessValidationException exception = assertThrows(BusinessValidationException.class,
                () -> clienteServiceImpl.createCliente(cliente));
        Assertions.assertEquals("Ya existe un cliente con ese ID.", exception.getMessage());
    }

    @Test
    void getClienteByIdShouldReturnClienteWhenClienteExists() {
        Cliente cliente = new Cliente();
        when(clienteRepository.findById(123l)).thenReturn(Optional.of(cliente));

        Cliente foundCliente = clienteServiceImpl.getClienteById(123l);

        assertNotNull(foundCliente);
        assertEquals(123l, foundCliente.getId());
        verify(clienteRepository).findById(123l);
    }

    @Test
    void getClienteById_ShouldThrowEntityNotFoundException_WhenClienteDoesNotExist() {
        when(clienteRepository.findById(123l)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> clienteServiceImpl.getClienteById(1L));
        Assertions.assertEquals("Cliente con ID 1 no encontrado.", exception.getMessage());
    }
}
