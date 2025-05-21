package com.example.hexagonalproductapi.application.domain.exception;

/**
 * Exceção lançada quando um produto solicitado não é encontrado.
 */
public class ProductNotFoundException extends BusinessException {

    private static final String ERROR_CODE = "PRODUCT-002";

    public ProductNotFoundException(Long id) {
        super("Produto com ID " + id + " não foi encontrado.", ERROR_CODE);
    }
    
    public ProductNotFoundException(String sku) {
        super("Produto com SKU '" + sku + "' não foi encontrado.", ERROR_CODE);
    }
} 