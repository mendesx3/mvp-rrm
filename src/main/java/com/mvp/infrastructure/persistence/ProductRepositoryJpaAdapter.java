package com.mvp.infrastructure.persistence;

import com.mvp.core.domain.Product;
import com.mvp.core.ports.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ProductRepositoryJpaAdapter implements ProductRepository {
    private final SpringDataProductRepository repository;

    public ProductRepositoryJpaAdapter(SpringDataProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product save(Product product) {
        ProductJpaEntity entity = ProductPersistenceMapper.toEntity(product);
        ProductJpaEntity saved = repository.save(entity);
        return ProductPersistenceMapper.toDomain(saved);
    }

    @Override
    public List<Product> search(String query) {
        return repository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query, query)
                .stream()
                .map(ProductPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return repository.findById(id).map(ProductPersistenceMapper::toDomain);
    }
}
