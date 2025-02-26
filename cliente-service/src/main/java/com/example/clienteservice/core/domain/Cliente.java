package com.example.clienteservice.core.domain;

import jakarta.persistence.Entity;

@Entity
public class Cliente extends Persona {
    private String contrasena;
    private Boolean estado;

    /*
     * Getter's and Setter's
     */
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
