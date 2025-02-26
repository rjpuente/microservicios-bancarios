package com.example.cuentaservice.infrastructure.adapters.inbound;

import com.example.cuentaservice.application.DTO.CuentaReporteDto;
import com.example.cuentaservice.application.DTO.MovimientoDto;
import com.example.cuentaservice.application.DTO.ReporteResponse;
import com.example.cuentaservice.core.domain.Cuenta;
import com.example.cuentaservice.core.domain.Movimiento;
import com.example.cuentaservice.core.domain.TipoMovimiento;
import com.example.cuentaservice.core.ports.inbound.CuentaServicePort;
import com.example.cuentaservice.core.ports.inbound.MovimientoServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReporteController {
    private final MovimientoServicePort movimientoServicePort;
    private final CuentaServicePort cuentaServicePort;

    @Autowired
    public ReporteController(MovimientoServicePort movimientoServicePort, CuentaServicePort cuentaServicePort) {
        this.movimientoServicePort = movimientoServicePort;
        this.cuentaServicePort = cuentaServicePort;
    }

    @GetMapping
    public ResponseEntity<ReporteResponse> obtenerReporte(@RequestParam Long clienteId, @RequestParam String fechaInicial, @RequestParam String fechaFinal) {
        LocalDateTime inicio = LocalDateTime.parse(fechaInicial);
        LocalDateTime fin = LocalDateTime.parse(fechaFinal);

        List<Cuenta> listaCuentasCliente = cuentaServicePort.obtenerCuentasPorCliente(clienteId);

        List<CuentaReporteDto> cuentasReporte = new ArrayList<>();
        listaCuentasCliente.forEach(cuenta -> {
            List<Movimiento> movimientos = movimientoServicePort.obtenerMovimientosPorFecha(cuenta.getId(), inicio, fin);

            double saldoInicial = (!movimientos.isEmpty()) ? movimientos.get(0).getSaldo() : 0;

            double saldoFinal = (!movimientos.isEmpty()) ? movimientos.get(movimientos.size() - 1).getSaldo() : cuenta.getSaldoInicial();

            List<MovimientoDto> listaMovimientosDto = movimientos.stream()
                    .map(m -> new MovimientoDto(
                            m.getFecha(),
                            m.getTipo().name(),
                            (m.getTipo().equals(TipoMovimiento.RETIRO) ? -m.getValor() : m.getValor()),
                            m.getSaldo()
                    )).toList();

            CuentaReporteDto cuentaReporteDto = new CuentaReporteDto(
                    cuenta.getNumeroCuenta(),
                    cuenta.getTipo().name(),
                    saldoInicial,
                    saldoFinal,
                    listaMovimientosDto
            );

            cuentasReporte.add(cuentaReporteDto);

        });

        ReporteResponse reporteResponse = new ReporteResponse(
                clienteId,
                inicio,
                fin,
                cuentasReporte
        );

        return ResponseEntity.ok(reporteResponse);

    }
}
