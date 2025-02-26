package com.example.clienteservice.infraestructure.adapters.inbound;

import com.example.clienteservice.core.domain.Cliente;
import com.example.clienteservice.core.ports.inbound.ClienteAdminServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clientes/admin")
public class ClienteAdminController {
    private final ClienteAdminServicePort clienteService;

    @Autowired
    public ClienteAdminController(ClienteAdminServicePort clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes() {
        return ResponseEntity.ok(clienteService.getAllClientes());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }
}
