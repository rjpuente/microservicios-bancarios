package com.rdevelop.tech_test.repository;

import com.rdevelop.tech_test.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
}
