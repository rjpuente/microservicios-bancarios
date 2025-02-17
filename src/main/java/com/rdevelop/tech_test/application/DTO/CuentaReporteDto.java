package com.rdevelop.tech_test.application.DTO;

import java.util.List;

public class CuentaReporteDto {
    private String numeroCuenta;
    private String tipoCuenta;      // o enum TipoCuenta
    private double saldoInicial;
    private double saldoFinal;
    private List<MovimientoDto> movimientos;

    public CuentaReporteDto() {
    }

    public CuentaReporteDto(String numeroCuenta,
                            String tipoCuenta,
                            double saldoInicial,
                            double saldoFinal,
                            List<MovimientoDto> movimientos) {
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldoInicial = saldoInicial;
        this.saldoFinal = saldoFinal;
        this.movimientos = movimientos;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public double getSaldoFinal() {
        return saldoFinal;
    }

    public void setSaldoFinal(double saldoFinal) {
        this.saldoFinal = saldoFinal;
    }

    public List<MovimientoDto> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<MovimientoDto> movimientos) {
        this.movimientos = movimientos;
    }
}
