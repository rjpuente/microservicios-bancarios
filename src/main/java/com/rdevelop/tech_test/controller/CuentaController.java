package com.rdevelop.tech_test.controller;

import com.rdevelop.tech_test.entity.Cuenta;
import com.rdevelop.tech_test.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    private final CuentaService cuentaService;

    @Autowired
    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping
    public List<Cuenta> obtenerTodasLasCuentas() {
        return cuentaService.findAllCuenta();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> obtenerCuentaPorId(@PathVariable Long id) {
        return cuentaService.findCuentaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Cuenta crearCuenta(@RequestBody Cuenta cuenta) {
        return cuentaService.createCuenta(cuenta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cuenta> actualizarCuenta(@PathVariable Long id, @RequestBody Cuenta cuenta) {
        if (!cuentaService.findCuentaById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        cuenta.setNumeroCuenta(id);
        return ResponseEntity.ok(cuentaService.updateCuenta(cuenta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCuenta(@PathVariable Long id) {
        if (!cuentaService.findCuentaById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        cuentaService.deleteCuenta(id);
        return ResponseEntity.noContent().build();
    }
}