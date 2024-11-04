package com.rdevelop.tech_test.repository;

import com.rdevelop.tech_test.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
