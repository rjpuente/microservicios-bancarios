package com.rdevelop.tech_test.controller;

import com.rdevelop.tech_test.entity.Movimiento;
import com.rdevelop.tech_test.service.MovimientoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class ReporteController {

    private final MovimientoService movimientoService;

    public ReporteController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @GetMapping("/reportes")
    public List<Movimiento> obtenerReporte(
            @RequestParam Long clienteId,
            @RequestParam String fechaInicio,
            @RequestParam String fechaFin) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime inicio = LocalDateTime.parse(fechaInicio, formatter);
        LocalDateTime fin = LocalDateTime.parse(fechaFin, formatter);
        return movimientoService.obtenerMovimientosPorClienteYRangoDeFechas(clienteId, inicio, fin);
    }
}