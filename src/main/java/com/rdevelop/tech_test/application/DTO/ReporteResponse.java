package com.rdevelop.tech_test.application.DTO;

import java.time.LocalDateTime;
import java.util.List;

public class ReporteResponse {
    private Long clienteId;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private List<CuentaReporteDto> cuentas; // detalle de cada cuenta

    public ReporteResponse() {
    }

    public ReporteResponse(Long clienteId,
                           LocalDateTime fechaInicio,
                           LocalDateTime fechaFin,
                           List<CuentaReporteDto> cuentas) {
        this.clienteId = clienteId;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.cuentas = cuentas;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<CuentaReporteDto> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<CuentaReporteDto> cuentas) {
        this.cuentas = cuentas;
    }
}
