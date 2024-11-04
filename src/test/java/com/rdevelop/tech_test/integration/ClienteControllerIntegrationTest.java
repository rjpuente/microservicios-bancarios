package com.rdevelop.tech_test.integration;

import com.rdevelop.tech_test.entity.Cliente;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClienteControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCrearYObtenerCliente() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setNombre("Marianela Montalvo");
        cliente.setGenero("Femenino");
        cliente.setEdad(30);
        cliente.setIdentificacion("9876543210");
        cliente.setDireccion("Amazonas y NNUU");
        cliente.setTelefono("097548965");
        cliente.setContrasena("5678");
        cliente.setEstado(true);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Cliente> entity = new HttpEntity<>(cliente, headers);

        // Crear Cliente
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/clientes"),
                HttpMethod.POST, entity, String.class);

        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(response.getBody());

        // Obtener Cliente
        Cliente[] clientes = restTemplate.getForObject(createURLWithPort("/clientes"), Cliente[].class);
        assertNotNull(clientes);
        assertEquals(1, clientes.length);
        assertEquals("Marianela Montalvo", clientes[0].getNombre());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}