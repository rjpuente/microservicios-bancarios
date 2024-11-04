package com.rdevelop.tech_test.controller;

import com.rdevelop.tech_test.entity.Cliente;
import com.rdevelop.tech_test.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClientService clienteService;

    @Autowired
    public ClienteController(ClientService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> obtenerTodosLosClientes() {
        return clienteService.findAllClients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Long id) {
        return clienteService.findByClientId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        return clienteService.createClient(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        if (!clienteService.findByClientId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        cliente.setId(id);
        return ResponseEntity.ok(clienteService.updateClient(cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        if (!clienteService.findByClientId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        clienteService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}