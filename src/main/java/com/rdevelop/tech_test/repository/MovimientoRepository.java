package com.rdevelop.tech_test.repository;

import com.rdevelop.tech_test.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    @Query("SELECT m FROM Movimiento m WHERE m.cuenta.cliente.id = :clienteId AND m.fecha BETWEEN :fechaInicio AND :fechaFin")
    List<Movimiento> findMovimientosByClienteAndFechaBetween(@Param("clienteId") Long clienteId, @Param("fechaInicio") LocalDateTime fechaInicio, @Param("fechaFin") LocalDateTime fechaFin);
}
