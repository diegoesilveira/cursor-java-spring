package com.example.hexagonalproductapi.adapter.out.persistence.repository;

import com.example.hexagonalproductapi.adapter.out.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório JPA para operações de persistência de produtos.
 */
@Repository
public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {
    
    /**
     * Verifica se existe um produto com o SKU especificado.
     * 
     * @param sku SKU a ser verificado
     * @return true se existe, false caso contrário
     */
    boolean existsBySku(String sku);
    
    /**
     * Busca um produto pelo SKU.
     * 
     * @param sku SKU do produto
     * @return O produto encontrado ou null
     */
    ProductEntity findBySku(String sku);
} 