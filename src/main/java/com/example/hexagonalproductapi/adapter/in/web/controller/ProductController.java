package com.example.hexagonalproductapi.adapter.in.web.controller;

import com.example.hexagonalproductapi.adapter.in.web.dto.ProductRequestDTO;
import com.example.hexagonalproductapi.adapter.in.web.dto.ProductResponseDTO;
import com.example.hexagonalproductapi.adapter.in.web.mapper.ProductDtoMapper;
import com.example.hexagonalproductapi.application.port.in.ProductServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Tag(name = "Produtos", description = "API para gerenciamento de produtos")
public class ProductController {

    private final ProductServicePort productServicePort;
    private final ProductDtoMapper productDtoMapper;

    @PostMapping
    @Operation(summary = "Criar um produto", description = "Cria um novo produto no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto criado com sucesso", 
                    content = @Content(mediaType = "application/json", 
                    schema = @Schema(implementation = ProductResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ProductRequestDTO requestDTO) {
        var product = productDtoMapper.toDomain(requestDTO);
        var savedProduct = productServicePort.saveProduct(product);
        return new ResponseEntity<>(productDtoMapper.toResponseDTO(savedProduct), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Listar todos os produtos", description = "Obtém uma lista de todos os produtos cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        var products = productServicePort.getAllProducts();
        return ResponseEntity.ok(productDtoMapper.toResponseDTOList(products));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar um produto por ID", description = "Busca um produto específico pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public ResponseEntity<ProductResponseDTO> getProductById(
            @Parameter(description = "ID do produto a ser buscado") 
            @PathVariable Long id) {
        var product = productServicePort.getProductById(id);
        return ResponseEntity.ok(productDtoMapper.toResponseDTO(product));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um produto", description = "Atualiza os dados de um produto existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    public ResponseEntity<ProductResponseDTO> updateProduct(
            @Parameter(description = "ID do produto a ser atualizado") 
            @PathVariable Long id,
            @Valid @RequestBody ProductRequestDTO requestDTO) {
        
        // Verificamos se o produto existe, o que lançará uma exceção se não encontrar
        productServicePort.getProductById(id);
        
        var product = productDtoMapper.toDomain(requestDTO);
        product.setId(id);
        var updatedProduct = productServicePort.saveProduct(product);
        return ResponseEntity.ok(productDtoMapper.toResponseDTO(updatedProduct));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um produto", description = "Remove um produto do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produto excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public ResponseEntity<Void> deleteProduct(
            @Parameter(description = "ID do produto a ser excluído") 
            @PathVariable Long id) {
        
        productServicePort.deleteProductById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
} 