package com.rdevelop.tech_test.ServiceTest;

import com.rdevelop.tech_test.entity.Cliente;
import com.rdevelop.tech_test.repository.ClienteRepository;
import com.rdevelop.tech_test.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ClienteServiceTest {

    @InjectMocks
    private ClientService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCrearCliente() {
        Cliente cliente = new Cliente();
        cliente.setNombre("Rodrigo Puente");

        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente result = clienteService.createClient(cliente);

        assertNotNull(result);
        assertEquals("Rodrigo Puente", result.getNombre());
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    public void testObtenerClientePorId() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("Rodrigo Puente");

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        Optional<Cliente> result = clienteService.findByClientId(1L);

        assertNotNull(result);
        assertEquals("Rodrigo Puente", result.get().getNombre());
    }
}