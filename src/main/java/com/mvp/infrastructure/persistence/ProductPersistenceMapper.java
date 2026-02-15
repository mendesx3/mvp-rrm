package com.mvp.infrastructure.persistence;

import com.mvp.core.domain.Product;

public final class ProductPersistenceMapper {
    private ProductPersistenceMapper() {
    }

    public static ProductJpaEntity toEntity(Product product) {
        return new ProductJpaEntity(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getCostPrice(),
                product.getSalePrice(),
                product.getStockQuantity()
        );
    }

    public static Product toDomain(ProductJpaEntity entity) {
        return new Product(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getCostPrice(),
                entity.getSalePrice(),
                entity.getStockQuantity()
        );
    }
}
