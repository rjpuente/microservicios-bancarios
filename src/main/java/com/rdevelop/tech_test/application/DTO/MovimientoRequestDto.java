package com.rdevelop.tech_test.application.DTO;

import com.rdevelop.tech_test.core.domain.Cuenta;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class MovimientoRequestDto {
    @NotNull
    private Long cuentaId;

    @NotNull
    private String tipo;

    @NotNull(message = "El valor es obligatorio")
    @Min(value = 1, message = "El valor debe ser al menos 1")
    private Double valor;

    /*
     * Getter's y Setter's
     */

    public Long getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(Long cuentaId) {
        this.cuentaId = cuentaId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
