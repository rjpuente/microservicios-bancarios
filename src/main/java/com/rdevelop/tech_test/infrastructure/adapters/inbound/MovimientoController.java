package com.rdevelop.tech_test.infrastructure.adapters.inbound;

import com.rdevelop.tech_test.core.domain.Movimiento;
import com.rdevelop.tech_test.core.ports.inbound.CuentaServicePort;
import com.rdevelop.tech_test.core.ports.inbound.MovimientoServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {
    private final MovimientoServicePort movimientoServicePort;
    private final CuentaServicePort cuentaServicePort;

    @Autowired
    public MovimientoController(MovimientoServicePort movimientoServicePort, CuentaServicePort cuentaServicePort) {
        this.movimientoServicePort = movimientoServicePort;
        this.cuentaServicePort = cuentaServicePort;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Movimiento registrarMovimiento(@RequestBody Movimiento movimiento) {

        return movimientoServicePort.registrarMovimiento(movimiento);
    }
}
