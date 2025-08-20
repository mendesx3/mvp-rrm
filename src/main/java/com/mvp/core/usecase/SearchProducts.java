package com.mvp.core.usecase;

import com.mvp.core.domain.Product;
import com.mvp.core.ports.ProductRepository;

import java.util.List;

public class SearchProducts {
    private final ProductRepository repository;

    public SearchProducts(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> execute(String query) {
        return repository.search(query);
    }
}
