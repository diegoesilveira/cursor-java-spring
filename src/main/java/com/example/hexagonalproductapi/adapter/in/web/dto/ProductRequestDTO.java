package com.example.hexagonalproductapi.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dados para criação ou atualização de um produto")
public class ProductRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    @Schema(description = "Nome do produto", example = "Smartphone XYZ", required = true)
    private String name;
    
    @Size(max = 1000, message = "Descrição não pode exceder 1000 caracteres")
    @Schema(description = "Descrição detalhada do produto", example = "Smartphone com 8GB RAM, 128GB armazenamento")
    private String description;
    
    @NotNull(message = "Preço é obrigatório")
    @Positive(message = "Preço deve ser maior que zero")
    @Schema(description = "Preço do produto", example = "1299.99", required = true)
    private BigDecimal price;
    
    @Schema(description = "Quantidade em estoque", example = "50")
    private Integer quantity;
    
    @NotBlank(message = "SKU é obrigatório")
    @Size(min = 3, max = 50, message = "SKU deve ter entre 3 e 50 caracteres")
    @Schema(description = "Código SKU único do produto", example = "PROD-12345", required = true)
    private String sku;
} 