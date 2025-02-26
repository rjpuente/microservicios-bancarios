package com.example.clienteservice.infraestructure.adapters.inbound;

import com.example.clienteservice.core.domain.Cliente;
import com.example.clienteservice.core.ports.inbound.ClienteServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        Cliente clienteNuevo = clienteService.createCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteNuevo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getCliente(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.getClienteById(id));
    }


}
