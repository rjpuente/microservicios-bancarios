package com.example.cuentaservice.infrastructure.adapters.outbound;

import com.example.cuentaservice.core.domain.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface MovimientoJpaRepository extends JpaRepository<Movimiento, Long> {
    @Query("SELECT m FROM Movimiento m " +
    "WHERE m.cuenta.numeroCuenta = :numeroCuenta")
    List<Movimiento> findByNumeroCuenta(@Param("numeroCuenta") String numeroCuenta);

    @Query("SELECT m FROM Movimiento m " +
            "WHERE m.cuenta.id = :clienteId AND m.fecha BETWEEN :fechaInicio AND :fechaFin")
    List<Movimiento> findByClienteIdAndFechaBetween(@Param("clienteId") Long clienteId,
                                                    @Param("fechaInicio") LocalDateTime fechaInicio,
                                                    @Param("fechaFin") LocalDateTime fechaFin);
}
