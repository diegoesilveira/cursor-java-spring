package com.example.hexagonalproductapi.application.domain.exception;

/**
 * Classe base para todas as exceções de negócio da aplicação.
 * Facilita a identificação e o tratamento de exceções relacionadas a regras de negócio.
 */
public abstract class BusinessException extends RuntimeException {
    
    /**
     * Código de erro específico do negócio
     */
    private final String errorCode;
    
    public BusinessException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
    
    public String getErrorCode() {
        return errorCode;
    }
} 