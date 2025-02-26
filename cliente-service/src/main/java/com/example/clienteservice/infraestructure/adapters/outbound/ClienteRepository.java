package com.example.clienteservice.infraestructure.adapters.outbound;

import com.example.clienteservice.core.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
