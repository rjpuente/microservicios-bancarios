package com.rdevelop.tech_test.application.mapper;

import com.rdevelop.tech_test.application.DTO.MovimientoRequestDto;
import com.rdevelop.tech_test.core.domain.Cuenta;
import com.rdevelop.tech_test.core.domain.Movimiento;
import com.rdevelop.tech_test.core.domain.TipoMovimiento;

public class MovimientoMapper {
    public static Movimiento toMovimientoEntity(MovimientoRequestDto movimientoRequestDto, Cuenta cuenta) {
        Movimiento movimiento = new Movimiento();
        movimiento.setCuenta(cuenta);
        movimiento.setValor(movimientoRequestDto.getValor());
        movimiento.setTipo(TipoMovimiento.valueOf(movimientoRequestDto.getTipo()));

        return movimiento;
    }
}
