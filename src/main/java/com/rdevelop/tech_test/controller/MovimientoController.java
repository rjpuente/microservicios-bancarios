package com.rdevelop.tech_test.controller;

import com.rdevelop.tech_test.entity.Movimiento;
import com.rdevelop.tech_test.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    private final MovimientoService movimientoService;

    @Autowired
    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @GetMapping
    public List<Movimiento> obtenerTodosLosMovimientos() {
        return movimientoService.findAllMovimientos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movimiento> obtenerMovimientoPorId(@PathVariable Long id) {
        return movimientoService.findMovimientoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Movimiento> registrarMovimiento(@RequestBody Movimiento movimiento) {
        try {
            Movimiento nuevoMovimiento = movimientoService.registrarMovimiento(movimiento);
            return ResponseEntity.ok(nuevoMovimiento);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}