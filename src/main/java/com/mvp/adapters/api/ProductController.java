package com.mvp.adapters.api;

import com.mvp.adapters.api.dto.*;
import com.mvp.core.domain.Product;
import com.mvp.core.usecase.CreateProduct;
import com.mvp.core.usecase.SearchProducts;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final CreateProduct createProduct;
    private final SearchProducts searchProducts;

    public ProductController(CreateProduct createProduct, SearchProducts searchProducts) {
        this.createProduct = createProduct;
        this.searchProducts = searchProducts;
    }

    @PostMapping
    public ProductResponse create(@RequestBody CreateProductRequest request) {
        Product product = createProduct.execute(request.name(), request.description(), request.price(), request.categoryId(), request.stock());
        return toResponse(product);
    }

    @GetMapping
    public List<ProductResponse> search(@RequestParam("q") String query) {
        return searchProducts.execute(query).stream().map(this::toResponse).collect(Collectors.toList());
    }

    private ProductResponse toResponse(Product p) {
        return new ProductResponse(p.getId(), p.getName(), p.getDescription(), p.getPrice().amount(), p.getCategoryId(), p.getStock());
    }
}
