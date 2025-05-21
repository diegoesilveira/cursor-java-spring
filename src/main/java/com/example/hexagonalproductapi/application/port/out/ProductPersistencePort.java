package com.example.hexagonalproductapi.application.port.out;

import com.example.hexagonalproductapi.application.domain.model.Product;

import java.util.List;

/**
 * Porta de saída para persistência de produtos.
 * Define as operações necessárias para persistir e recuperar produtos.
 */
public interface ProductPersistencePort {
    
    /**
     * Salva um produto no repositório de dados.
     * 
     * @param product O produto a ser salvo
     * @return O produto salvo com seu ID gerado
     */
    Product saveProduct(Product product);
    
    /**
     * Obtém todos os produtos do repositório de dados.
     * 
     * @return Lista de produtos
     */
    List<Product> getAllProducts();
    
    /**
     * Busca um produto pelo seu ID no repositório de dados.
     * 
     * @param id ID do produto
     * @return O produto encontrado
     * @throws com.example.hexagonalproductapi.application.domain.exception.ProductNotFoundException quando o produto não for encontrado
     */
    Product getProductById(Long id);
    
    /**
     * Remove um produto do repositório de dados pelo seu ID.
     * 
     * @param id ID do produto a ser removido
     * @throws com.example.hexagonalproductapi.application.domain.exception.ProductNotFoundException quando o produto não for encontrado
     */
    void deleteProductById(Long id);
} 