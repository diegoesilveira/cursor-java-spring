package com.example.hexagonalproductapi.application.domain.exception;

/**
 * Exceção lançada quando há tentativa de cadastrar um produto com um SKU que já existe no sistema.
 */
public class DuplicateSKUException extends BusinessException {

    private static final String ERROR_CODE = "PRODUCT-001";

    public DuplicateSKUException(String sku) {
        super("Produto com SKU '" + sku + "' já existe no sistema.", ERROR_CODE);
    }
} 