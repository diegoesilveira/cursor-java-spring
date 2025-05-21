package com.example.hexagonalproductapi.adapter.in.web.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa a resposta padronizada para erros de validação.
 */
public class ValidationErrorResponse {
    
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String path;
    private List<ValidationError> fieldErrors;
    
    public ValidationErrorResponse(int status, String error, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.path = path;
        this.fieldErrors = new ArrayList<>();
    }
    
    public void addFieldError(String field, String message) {
        fieldErrors.add(new ValidationError(field, message));
    }
    
    // Getters
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public int getStatus() {
        return status;
    }
    
    public String getError() {
        return error;
    }
    
    public String getPath() {
        return path;
    }
    
    public List<ValidationError> getFieldErrors() {
        return fieldErrors;
    }
    
    /**
     * Classe interna que representa um erro de validação em um campo específico.
     */
    public static class ValidationError {
        private String field;
        private String message;
        
        public ValidationError(String field, String message) {
            this.field = field;
            this.message = message;
        }
        
        public String getField() {
            return field;
        }
        
        public String getMessage() {
            return message;
        }
    }
} 