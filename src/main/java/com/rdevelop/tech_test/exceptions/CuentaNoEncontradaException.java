package com.rdevelop.tech_test.exceptions;

public class CuentaNoEncontradaException extends RuntimeException {

    public CuentaNoEncontradaException(String message) {
        super(message);
    }
}