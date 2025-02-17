package com.rdevelop.tech_test.application;

import com.rdevelop.tech_test.application.services.MovimientoServiceImpl;
import com.rdevelop.tech_test.core.domain.Cuenta;
import com.rdevelop.tech_test.core.domain.Movimiento;
import com.rdevelop.tech_test.core.domain.TipoMovimiento;
import com.rdevelop.tech_test.core.ports.outbound.CuentaRepositoryPort;
import com.rdevelop.tech_test.core.ports.outbound.MovimientoRepositoryPort;
import com.rdevelop.tech_test.exceptions.SaldoInsuficienteException;
import com.rdevelop.tech_test.infrastructure.adapters.outbound.RabbitMQProducer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class MovimientoServiceImplTest {

    private MovimientoRepositoryPort movimientoRepositoryPort;
    private CuentaRepositoryPort cuentaRepositoryPort;
    private MovimientoServiceImpl movimientoService;
    private RabbitMQProducer rabbitMQProducer;

    @BeforeEach
    void setUp() {
        movimientoRepositoryPort = mock(MovimientoRepositoryPort.class);
        cuentaRepositoryPort = mock(CuentaRepositoryPort.class);
        rabbitMQProducer = mock(RabbitMQProducer.class);
        movimientoService = new MovimientoServiceImpl(movimientoRepositoryPort, cuentaRepositoryPort, rabbitMQProducer);
    }

    @Test
    void registrarMovimientoDepositoExitoso() {
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta("12345");
        cuenta.setSaldoInicial(10000.0);

        Movimiento movimiento = new Movimiento();
        movimiento.setCuenta(cuenta);
        movimiento.setTipo(TipoMovimiento.DEPOSITO);
        movimiento.setValor(200.0);

        when(cuentaRepositoryPort.findByNumeroCuenta("12345")).thenReturn(Optional.of(cuenta));

        when(cuentaRepositoryPort.save(any(Cuenta.class))).thenAnswer(i -> i.getArgument(0));

        when(movimientoRepositoryPort.save(any())).thenAnswer(i -> {
            Movimiento mov = i.getArgument(0);
            mov.setId(1L);
            return mov;
        });


        Movimiento result = movimientoService.registrarMovimiento(movimiento);


        Assertions.assertNotNull(result.getId(), "Debe tener un ID");
        Assertions.assertEquals(10200.0, result.getSaldo(), 0.01, "Saldo tras el deposito debe ser 102000");
        Assertions.assertEquals(10200.0, cuenta.getSaldoInicial(), 0.01);

        verify(movimientoRepositoryPort).save(any(Movimiento.class));
        verify(cuentaRepositoryPort).save(any(Cuenta.class));
    }

    @Test
    void registrarMovimientoRetiroSaldoInsuficiente() {

        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta("12345");
        cuenta.setSaldoInicial(200.0);

        Movimiento mov = new Movimiento();
        mov.setTipo(TipoMovimiento.RETIRO);
        mov.setValor(300.0);
        mov.setCuenta(cuenta);

        when(cuentaRepositoryPort.findByNumeroCuenta("12345"))
                .thenReturn(Optional.of(cuenta));

        Assertions.assertThrows(SaldoInsuficienteException.class,
                () -> movimientoService.registrarMovimiento(mov),
                "Debe lanzar SaldoInsuficienteException si saldo resulta negativo");
    }

    @Test
    void registrarMovimientoRetiroExitoso() {
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta("12345");
        cuenta.setSaldoInicial(1000.0);

        Movimiento mov = new Movimiento();
        mov.setTipo(TipoMovimiento.RETIRO);
        mov.setValor(300.0);
        mov.setCuenta(cuenta);

        when(cuentaRepositoryPort.findByNumeroCuenta("12345"))
                .thenReturn(Optional.of(cuenta));

        when(cuentaRepositoryPort.save(any(Cuenta.class)))
                .thenAnswer(invoc -> invoc.getArgument(0));

        when(movimientoRepositoryPort.save(any(Movimiento.class)))
                .thenAnswer(invoc -> {
                    Movimiento m = invoc.getArgument(0);
                    m.setId(2L);
                    return m;
                });

        Movimiento resultado = movimientoService.registrarMovimiento(mov);

        Assertions.assertNotNull(resultado.getId());
        Assertions.assertEquals(700.0, resultado.getSaldo(), 0.01);
        Assertions.assertEquals(700.0, cuenta.getSaldoInicial(), 0.01);

        verify(movimientoRepositoryPort).save(any(Movimiento.class));
        verify(cuentaRepositoryPort).save(any(Cuenta.class));
    }
}
