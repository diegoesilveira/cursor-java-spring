package com.example.hexagonalproductapi.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dados de resposta de um produto")
public class ProductResponseDTO {

    @Schema(description = "ID único do produto", example = "1")
    private Long id;
    
    @Schema(description = "Nome do produto", example = "Smartphone XYZ")
    private String name;
    
    @Schema(description = "Descrição detalhada do produto", example = "Smartphone com 8GB RAM, 128GB armazenamento")
    private String description;
    
    @Schema(description = "Preço do produto", example = "1299.99")
    private BigDecimal price;
    
    @Schema(description = "Quantidade em estoque", example = "50")
    private Integer quantity;
    
    @Schema(description = "Código SKU único do produto", example = "PROD-12345")
    private String sku;
} 