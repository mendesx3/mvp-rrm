package com.mvp.infrastructure;

import com.mvp.core.domain.Product;
import com.mvp.core.ports.ProductRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryProductRepository implements ProductRepository {
    private final Map<UUID, Product> store = new ConcurrentHashMap<>();

    @Override
    public Product save(Product product) {
        store.put(product.getId(), product);
        return product;
    }

    @Override
    public List<Product> search(String query) {
        return store.values().stream()
                .filter(p -> p.getName().toLowerCase().contains(query.toLowerCase())
                        || p.getDescription().toLowerCase().contains(query.toLowerCase()))
                .toList();
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }
}
