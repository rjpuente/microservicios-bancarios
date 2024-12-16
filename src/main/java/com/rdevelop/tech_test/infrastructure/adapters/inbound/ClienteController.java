package com.rdevelop.tech_test.infrastructure.adapters.inbound;

import com.rdevelop.tech_test.core.domain.Cliente;
import com.rdevelop.tech_test.core.ports.ClienteServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clientes")
public class ClienteController {
    private final ClienteServicePort clienteService;

    @Autowired
    public ClienteController(ClienteServicePort clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.createCliente(cliente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getCliente(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.getClienteById(id));
    }


}
