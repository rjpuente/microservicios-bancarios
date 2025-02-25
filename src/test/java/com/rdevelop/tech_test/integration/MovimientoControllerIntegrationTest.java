package com.rdevelop.tech_test.integration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.rdevelop.tech_test.core.domain.Cliente;
import com.rdevelop.tech_test.core.domain.Cuenta;
import com.rdevelop.tech_test.core.domain.Movimiento;
import com.rdevelop.tech_test.exceptions.ErrorResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "spring.profiles.active=test")
public class MovimientoControllerIntegrationTest extends IntegrationTestBase {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void registrarMovimientoDepositoExitoso() {
        Cuenta cuenta = crearCuenta("999ABC", 100.00);

        Map<String, Object> params = new HashMap<>();
        params.put("cuentaId", cuenta.getId());
        params.put("tipo", "DEPOSITO");
        params.put("valor", 200.0);

        ResponseEntity<Movimiento> response = restTemplate.postForEntity("/movimientos", params, Movimiento.class);


        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Movimiento movimiento = response.getBody();
        Assertions.assertNotNull(movimiento.getId());
        Assertions.assertEquals(300.00, movimiento.getSaldo(), 0.01, "El saldo luego del deposito, debe ser de 300");

    }


    @Test
    void registrarMovimientoDepositoCamposInsuficientes() {
        Cuenta cuenta = crearCuenta("999ABC", 100.00);

        Map<String, Object> params = new HashMap<>();
        params.put("cuentaId", cuenta.getId());
        params.put("tipo", "DEPOSITO");
        params.put("valor", 0.0);

        ResponseEntity<ErrorResponse> response = restTemplate.postForEntity("/movimientos", params, ErrorResponse.class);


        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ErrorResponse error = response.getBody();
        Assertions.assertNotNull(error);
        assertTrue(error.getMessage().contains("El valor debe ser al menos 1"));

    }

    @Test
    void registrarMovimientoRetidoSaldoInsuficiente() {
        Cuenta cuenta = crearCuenta("RET123", 500.00);

        Map<String, Object> params = new HashMap<>();
        params.put("cuentaId", cuenta.getId());
        params.put("tipo", "RETIRO");
        params.put("valor", 800.00);

        ResponseEntity<ErrorResponse> response = restTemplate.postForEntity("/movimientos", params, ErrorResponse.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ErrorResponse error = response.getBody();
        assertTrue(error.getMessage().contains("Saldo insuficiente"));
    }

    private Cuenta crearCuenta(String numeroCuenta, double saldoInicial) {
        Cliente cliente = crearCliente();

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("numeroCuenta", numeroCuenta);
        params.put("saldoInicial", saldoInicial);
        params.put("estado", true);
        params.put("tipo", "AHORRO");
        params.put("cliente", cliente);

        ResponseEntity<Cuenta> responseEntity = restTemplate.postForEntity("/cuentas", params, Cuenta.class);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        return responseEntity.getBody();
    }

    private Cliente crearCliente() {
        Map<String, Object> clienteMap = new HashMap<>();
        clienteMap.put("nombre", "Juan Perez");
        clienteMap.put("genero", "Masculino");
        clienteMap.put("edad", 30);
        clienteMap.put("identificacion", "1234567890");
        clienteMap.put("direccion", "Calle 1");
        clienteMap.put("telefono", "0987654321");

        clienteMap.put("contrasena", "1234");
        clienteMap.put("estado", true);

        ResponseEntity<Cliente> responseEntity = restTemplate.postForEntity("/clientes", clienteMap, Cliente.class);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        return responseEntity.getBody();
    }

}
