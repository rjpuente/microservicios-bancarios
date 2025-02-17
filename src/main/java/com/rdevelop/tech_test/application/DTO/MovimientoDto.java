package com.rdevelop.tech_test.application.DTO;

import java.time.LocalDateTime;

public class MovimientoDto {
    private LocalDateTime fecha;
    private String tipoMovimiento; // o enum TipoMovimiento
    private double valor;
    private double saldo;

    public MovimientoDto() {
    }

    public MovimientoDto(LocalDateTime fecha,
                         String tipoMovimiento,
                         double valor,
                         double saldo) {
        this.fecha = fecha;
        this.tipoMovimiento = tipoMovimiento;
        this.valor = valor;
        this.saldo = saldo;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
