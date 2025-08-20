package com.mvp.core.ports;

import com.mvp.core.domain.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {
    Product save(Product product);
    List<Product> search(String query);
    Optional<Product> findById(UUID id);
}
