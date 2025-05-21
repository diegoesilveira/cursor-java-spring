package com.example.hexagonalproductapi.adapter.out.persistence.mapper;

import com.example.hexagonalproductapi.adapter.out.persistence.entity.ProductEntity;
import com.example.hexagonalproductapi.application.domain.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper para conversão entre entidades JPA e objetos de domínio.
 */
@Component
public class ProductPersistenceMapper {

    /**
     * Converte uma entidade JPA para um objeto de domínio.
     * 
     * @param entity Entidade JPA
     * @return Objeto de domínio
     */
    public Product toDomain(ProductEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return Product.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .quantity(entity.getQuantity())
                .sku(entity.getSku())
                .build();
    }

    /**
     * Converte um objeto de domínio para uma entidade JPA.
     * 
     * @param domain Objeto de domínio
     * @return Entidade JPA
     */
    public ProductEntity toEntity(Product domain) {
        if (domain == null) {
            return null;
        }
        
        return ProductEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .description(domain.getDescription())
                .price(domain.getPrice())
                .quantity(domain.getQuantity())
                .sku(domain.getSku())
                .build();
    }
    
    /**
     * Converte uma lista de entidades JPA para uma lista de objetos de domínio.
     * 
     * @param entities Lista de entidades JPA
     * @return Lista de objetos de domínio
     */
    public List<Product> toDomainList(List<ProductEntity> entities) {
        return entities.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
} 