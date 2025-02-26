package com.example.cuentaservice.application.DTO;

import java.time.LocalDateTime;

public class MovimientoEvento {
    private Long movimientoId;
    private LocalDateTime fecha;
    private String descripcion;
    private double saldo;

    public MovimientoEvento(Long movimientoId, LocalDateTime fecha, String descripcion, double saldo) {
        this.movimientoId = movimientoId;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.saldo = saldo;
    }

    public Long getMovimientoId() {
        return movimientoId;
    }

    public void setMovimientoId(Long movimientoId) {
        this.movimientoId = movimientoId;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
