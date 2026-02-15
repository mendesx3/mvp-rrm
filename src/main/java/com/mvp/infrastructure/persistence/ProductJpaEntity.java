package com.mvp.infrastructure.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "products")
public class ProductJpaEntity {
    @Id
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal costPrice;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal salePrice;

    @Column(nullable = false)
    private Integer stockQuantity;

    protected ProductJpaEntity() {
    }

    public ProductJpaEntity(UUID id, String name, String description, BigDecimal costPrice, BigDecimal salePrice, Integer stockQuantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.costPrice = costPrice;
        this.salePrice = salePrice;
        this.stockQuantity = stockQuantity;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public BigDecimal getCostPrice() { return costPrice; }
    public BigDecimal getSalePrice() { return salePrice; }
    public Integer getStockQuantity() { return stockQuantity; }
}
