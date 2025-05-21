package com.example.hexagonalproductapi.adapter.in.web.mapper;

import com.example.hexagonalproductapi.adapter.in.web.dto.ProductRequestDTO;
import com.example.hexagonalproductapi.adapter.in.web.dto.ProductResponseDTO;
import com.example.hexagonalproductapi.application.domain.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductDtoMapper {

    public Product toDomain(ProductRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        
        return Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .sku(dto.getSku())
                .build();
    }
    
    public ProductResponseDTO toResponseDTO(Product domain) {
        if (domain == null) {
            return null;
        }
        
        return ProductResponseDTO.builder()
                .id(domain.getId())
                .name(domain.getName())
                .description(domain.getDescription())
                .price(domain.getPrice())
                .quantity(domain.getQuantity())
                .sku(domain.getSku())
                .build();
    }
    
    public List<ProductResponseDTO> toResponseDTOList(List<Product> products) {
        return products.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
} 