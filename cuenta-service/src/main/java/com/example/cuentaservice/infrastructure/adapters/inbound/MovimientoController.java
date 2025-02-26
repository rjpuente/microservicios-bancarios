package com.example.cuentaservice.infrastructure.adapters.inbound;

import com.example.cuentaservice.application.DTO.MovimientoRequestDto;
import com.example.cuentaservice.application.mapper.MovimientoMapper;
import com.example.cuentaservice.core.domain.Cuenta;
import com.example.cuentaservice.core.domain.Movimiento;
import com.example.cuentaservice.core.ports.inbound.CuentaServicePort;
import com.example.cuentaservice.core.ports.inbound.MovimientoServicePort;
import com.example.cuentaservice.exceptions.EntityNotFoundException;
import jakarta.validation.Valid;
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
    public Movimiento registrarMovimiento(@Valid @RequestBody MovimientoRequestDto movimientoRequestDto) {
        Cuenta cuenta = cuentaServicePort.obtenerCuentaPorId(movimientoRequestDto.getCuentaId());
        if (cuenta == null) {
            throw new EntityNotFoundException("Cuenta no encontrada");
        }

        Movimiento movimiento = MovimientoMapper.toMovimientoEntity(movimientoRequestDto, cuenta);
        return movimientoServicePort.registrarMovimiento(movimiento);
    }
}
