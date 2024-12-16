package com.rdevelop.tech_test.core.domain;

import jakarta.persistence.Entity;

@Entity
public class Cliente extends Persona {
    private Long clienteId;
    private String contrasena;
    private Boolean estado;

    /*
     * Getter's and Setter's
     */
    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
