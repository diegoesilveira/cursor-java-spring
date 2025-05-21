package com.example.hexagonalproductapi.application.domain.service;

import com.example.hexagonalproductapi.application.domain.model.Product;
import com.example.hexagonalproductapi.application.port.in.ProductServicePort;
import com.example.hexagonalproductapi.application.port.out.ProductPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementação do serviço de domínio para produtos.
 * Implementa a porta de entrada (ProductServicePort) e utiliza a porta de saída (ProductPersistencePort).
 */
@Service
@RequiredArgsConstructor
public class ProductService implements ProductServicePort {

    private final ProductPersistencePort productPersistencePort;

    @Override
    public Product saveProduct(Product product) {
        return productPersistencePort.saveProduct(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productPersistencePort.getAllProducts();
    }

    @Override
    public Product getProductById(Long id) {
        return productPersistencePort.getProductById(id);
    }

    @Override
    public void deleteProductById(Long id) {
        productPersistencePort.deleteProductById(id);
    }
} 