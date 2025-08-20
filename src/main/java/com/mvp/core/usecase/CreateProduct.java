package com.mvp.core.usecase;

import com.mvp.core.domain.Money;
import com.mvp.core.domain.Product;
import com.mvp.core.ports.ProductRepository;

import java.math.BigDecimal;
import java.util.UUID;

public class CreateProduct {
    private final ProductRepository repository;

    public CreateProduct(ProductRepository repository) {
        this.repository = repository;
    }

    public Product execute(String name, String description, BigDecimal price, UUID categoryId, int stock) {
        Product product = new Product(UUID.randomUUID(), name, description, new Money(price), categoryId, stock);
        return repository.save(product);
    }
}
