package com.rdevelop.tech_test.infrastructure.adapters.outbound;

import com.rdevelop.tech_test.core.domain.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaJpaRepository extends JpaRepository<Cuenta, Long> {
}
