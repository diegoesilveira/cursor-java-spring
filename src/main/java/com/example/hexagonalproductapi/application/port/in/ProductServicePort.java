package com.example.hexagonalproductapi.application.port.in;

import com.example.hexagonalproductapi.application.domain.model.Product;

import java.util.List;

/**
 * Porta de entrada para o serviço de produtos.
 * Define as operações disponíveis para manipulação de produtos.
 */
public interface ProductServicePort {
    
    /**
     * Salva um produto no sistema.
     * 
     * @param product O produto a ser salvo
     * @return O produto salvo com seu ID gerado
     */
    Product saveProduct(Product product);
    
    /**
     * Obtém todos os produtos cadastrados no sistema.
     * 
     * @return Lista de produtos
     */
    List<Product> getAllProducts();
    
    /**
     * Busca um produto pelo seu ID.
     * 
     * @param id ID do produto
     * @return O produto encontrado
     * @throws com.example.hexagonalproductapi.application.domain.exception.ProductNotFoundException quando o produto não for encontrado
     */
    Product getProductById(Long id);
    
    /**
     * Remove um produto do sistema pelo seu ID.
     * 
     * @param id ID do produto a ser removido
     * @throws com.example.hexagonalproductapi.application.domain.exception.ProductNotFoundException quando o produto não for encontrado
     */
    void deleteProductById(Long id);
} 