package com.rdevelop.tech_test.infrastructure.adapters.outbound;

import com.rdevelop.tech_test.core.domain.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface MovimientoJpaRepository extends JpaRepository<Movimiento, Long> {
    List<Movimiento> findByNumeroCuenta(Long numeroCuenta);

    @Query("SELECT m FROM Movimiento m " +
            "WHERE m.cuenta.id = :clienteId AND m.fecha BETWEEN :fechaInicio AND :fechaFin")
    List<Movimiento> findByClienteIdAndFechaBetween(@Param("clienteId") Long clienteId,
                                                    @Param("fechaInicio") LocalDateTime fechaInicio,
                                                    @Param("fechaFin") LocalDateTime fechaFin);
}
