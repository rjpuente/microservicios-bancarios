package com.rdevelop.tech_test.infrastructure.adapters.inbound;

import com.rdevelop.tech_test.application.ClienteAdminService;
import com.rdevelop.tech_test.core.domain.Cliente;
import com.rdevelop.tech_test.core.ports.ClienteAdminServicePort;
import com.rdevelop.tech_test.core.ports.ClienteServicePort;
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
