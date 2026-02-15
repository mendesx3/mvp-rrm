package com.mvp.core.domain;

import com.mvp.core.domain.exception.InsufficientStockException;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class Product {
    private final UUID id;
    private final String name;
    private final String description;
    private final BigDecimal costPrice;
    private final BigDecimal salePrice;
    private Integer stockQuantity;

    public Product(UUID id,
                   String name,
                   String description,
                   BigDecimal costPrice,
                   BigDecimal salePrice,
                   Integer stockQuantity) {
        this.id = Objects.requireNonNull(id, "id is required");
        this.name = Objects.requireNonNull(name, "name is required");
        this.description = Objects.requireNonNull(description, "description is required");
        this.costPrice = Objects.requireNonNull(costPrice, "costPrice is required");
        this.salePrice = Objects.requireNonNull(salePrice, "salePrice is required");
        this.stockQuantity = Objects.requireNonNull(stockQuantity, "stockQuantity is required");
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public BigDecimal getCostPrice() { return costPrice; }
    public BigDecimal getSalePrice() { return salePrice; }
    public Integer getStockQuantity() { return stockQuantity; }

    public void decreaseStock(int quantity) {
        if (quantity > stockQuantity) {
            throw new InsufficientStockException(
                    "Produto [" + name + "] fora de estoque (Disponível: " + stockQuantity + ", Solicitado: " + quantity + ")"
            );
        }
        stockQuantity -= quantity;
    }
}
