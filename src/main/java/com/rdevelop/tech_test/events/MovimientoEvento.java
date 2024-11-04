package com.rdevelop.tech_test.events;

public class MovimientoEvento {
    private Long movimientoId;
    private String descripcion;

    public MovimientoEvento(Long movimientoId, String descripcion) {
        this.movimientoId = movimientoId;
        this.descripcion = descripcion;
    }

    /*
     * Getter's and Setter's
     */
    public Long getMovimientoId() {
        return movimientoId;
    }

    public void setMovimientoId(Long movimientoId) {
        this.movimientoId = movimientoId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
