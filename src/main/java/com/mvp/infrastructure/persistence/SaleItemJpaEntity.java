package com.mvp.infrastructure.persistence;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "sale_items")
public class SaleItemJpaEntity {
    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sale_id", nullable = false)
    private SaleJpaEntity sale;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductJpaEntity product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal unitPrice;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal totalItemValue;

    protected SaleItemJpaEntity() {}

    public SaleItemJpaEntity(UUID id, ProductJpaEntity product, Integer quantity, BigDecimal unitPrice, BigDecimal totalItemValue) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalItemValue = totalItemValue;
    }

    public void setSale(SaleJpaEntity sale) { this.sale = sale; }

    public UUID getId() { return id; }
    public SaleJpaEntity getSale() { return sale; }
    public ProductJpaEntity getProduct() { return product; }
    public Integer getQuantity() { return quantity; }
    public BigDecimal getUnitPrice() { return unitPrice; }
    public BigDecimal getTotalItemValue() { return totalItemValue; }
}
