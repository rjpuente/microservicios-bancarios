package com.rdevelop.tech_test.infrastructure.adapters.outbound;

import com.rdevelop.tech_test.core.domain.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CuentaJpaRepository extends JpaRepository<Cuenta, Long> {
    @Query("SELECT c FROM Cuenta c " +
            "WHERE c.cliente.clienteId = :clienteId")
    List<Cuenta> findByClienteId(@Param("clienteId") Long clienteId);

    @Query("SELECT c FROM Cuenta c " +
    "WHERE c.numeroCuenta = :numeroCuenta")
    Optional<Cuenta> findByNumeroCuenta(@Param("numeroCuenta") String numeroCuenta);;
}
