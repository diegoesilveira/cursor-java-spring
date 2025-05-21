package com.example.hexagonalproductapi.adapter.out.persistence;

import com.example.hexagonalproductapi.adapter.out.persistence.mapper.ProductPersistenceMapper;
import com.example.hexagonalproductapi.adapter.out.persistence.repository.ProductJpaRepository;
import com.example.hexagonalproductapi.application.domain.exception.DuplicateSKUException;
import com.example.hexagonalproductapi.application.domain.exception.ProductNotFoundException;
import com.example.hexagonalproductapi.application.domain.model.Product;
import com.example.hexagonalproductapi.application.port.out.ProductPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Adaptador de persistência para produtos.
 * Implementa a porta de saída (ProductPersistencePort) e utiliza o repositório JPA.
 */
@Service
@RequiredArgsConstructor
public class ProductPersistenceAdapter implements ProductPersistencePort {

    private final ProductJpaRepository productJpaRepository;
    private final ProductPersistenceMapper productMapper;

    @Override
    @Transactional
    public Product saveProduct(Product product) {
        // Verifica se já existe um produto com o mesmo SKU
        if (product.getId() == null && productJpaRepository.existsBySku(product.getSku())) {
            throw new DuplicateSKUException(product.getSku());
        }
        
        var entity = productMapper.toEntity(product);
        var savedEntity = productJpaRepository.save(entity);
        return productMapper.toDomain(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        var entities = productJpaRepository.findAll();
        return productMapper.toDomainList(entities);
    }

    @Override
    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        return productJpaRepository.findById(id)
                .map(productMapper::toDomain)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    @Transactional
    public void deleteProductById(Long id) {
        if (!productJpaRepository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }
        productJpaRepository.deleteById(id);
    }
} 