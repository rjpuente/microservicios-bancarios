package com.example.cuentaservice.application.mapper;

import com.example.cuentaservice.application.DTO.MovimientoRequestDto;
import com.example.cuentaservice.core.domain.Cuenta;
import com.example.cuentaservice.core.domain.Movimiento;
import com.example.cuentaservice.core.domain.TipoMovimiento;

public class MovimientoMapper {
    public static Movimiento toMovimientoEntity(MovimientoRequestDto movimientoRequestDto, Cuenta cuenta) {
        Movimiento movimiento = new Movimiento();
        movimiento.setCuenta(cuenta);
        movimiento.setValor(movimientoRequestDto.getValor());
        movimiento.setTipo(TipoMovimiento.valueOf(movimientoRequestDto.getTipo()));

        return movimiento;
    }
}
