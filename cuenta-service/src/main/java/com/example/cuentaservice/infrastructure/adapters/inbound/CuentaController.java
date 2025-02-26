package com.example.cuentaservice.infrastructure.adapters.inbound;

import com.example.cuentaservice.core.domain.Cuenta;
import com.example.cuentaservice.core.ports.inbound.CuentaServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {
    private final CuentaServicePort cuentaServicePort;

    @Autowired
    public CuentaController(CuentaServicePort cuentaServicePort) {
        this.cuentaServicePort = cuentaServicePort;
    }

    @PostMapping
    public ResponseEntity<Cuenta> crearCuenta(@RequestBody Cuenta cuenta) {
        Cuenta cuentaNueva = cuentaServicePort.crearCuenta(cuenta);
        return ResponseEntity.status(HttpStatus.CREATED).body(cuentaNueva);
    }
}
