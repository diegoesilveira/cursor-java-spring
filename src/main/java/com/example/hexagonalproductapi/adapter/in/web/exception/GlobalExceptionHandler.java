package com.example.hexagonalproductapi.adapter.in.web.exception;

import com.example.hexagonalproductapi.application.domain.exception.BusinessException;
import com.example.hexagonalproductapi.application.domain.exception.DuplicateSKUException;
import com.example.hexagonalproductapi.application.domain.exception.ProductNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

/**
 * Gerenciador global de exceções da aplicação.
 * Centraliza o tratamento de erros e padroniza as respostas de erro da API.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Classe que representa a estrutura de resposta para erros.
     */
    public static class ApiError {
        private LocalDateTime timestamp;
        private int status;
        private String error;
        private String message;
        private String path;
        private String errorCode;

        public ApiError(int status, String error, String message, String path) {
            this.timestamp = LocalDateTime.now();
            this.status = status;
            this.error = error;
            this.message = message;
            this.path = path;
        }
        
        public ApiError(int status, String error, String message, String path, String errorCode) {
            this(status, error, message, path);
            this.errorCode = errorCode;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public int getStatus() {
            return status;
        }

        public String getError() {
            return error;
        }

        public String getMessage() {
            return message;
        }

        public String getPath() {
            return path;
        }
        
        public String getErrorCode() {
            return errorCode;
        }
    }

    /**
     * Trata exceções de validação de entrada (Bean Validation).
     * @param ex A exceção lançada quando a validação falha
     * @param request A requisição atual
     * @return ResponseEntity com detalhes dos erros de validação
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ValidationErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        ValidationErrorResponse errorResponse = new ValidationErrorResponse(
                HttpStatus.BAD_REQUEST.value(), 
                "Validation Error", 
                request.getRequestURI()
        );
        
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errorResponse.addFieldError(fieldName, errorMessage);
        });
        
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Trata exceções de regras de negócio.
     * @param ex A exceção de negócio
     * @param request A requisição atual
     * @return ResponseEntity com detalhes do erro
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiError> handleBusinessException(BusinessException ex, HttpServletRequest request) {
        HttpStatus status = determineStatusCode(ex);
        
        ApiError apiError = new ApiError(
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI(),
                ex.getErrorCode()
        );
        
        return new ResponseEntity<>(apiError, status);
    }
    
    /**
     * Determina o código de status HTTP apropriado com base no tipo de exceção de negócio.
     */
    private HttpStatus determineStatusCode(BusinessException ex) {
        if (ex instanceof ProductNotFoundException) {
            return HttpStatus.NOT_FOUND;
        } else if (ex instanceof DuplicateSKUException) {
            return HttpStatus.CONFLICT;
        }
        return HttpStatus.BAD_REQUEST;
    }

    /**
     * Trata exceções de recurso não encontrado.
     * @param ex A exceção lançada quando um recurso não é encontrado
     * @param request A requisição atual
     * @return ResponseEntity com detalhes do erro
     */
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiError> handleNoSuchElementException(NoSuchElementException ex, HttpServletRequest request) {
        ApiError apiError = new ApiError(
                HttpStatus.NOT_FOUND.value(),
                "Resource Not Found",
                ex.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    /**
     * Trata exceções genéricas não capturadas por outros handlers.
     * @param ex A exceção genérica
     * @param request A requisição atual
     * @return ResponseEntity com detalhes do erro
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiError> handleAllUncaughtException(Exception ex, HttpServletRequest request) {
        ApiError apiError = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                "An unexpected error occurred: " + ex.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
} 