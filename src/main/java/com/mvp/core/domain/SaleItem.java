package com.mvp.core.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class SaleItem {
    private final Product product;
    private final Integer quantity;
    private final BigDecimal unitPrice;
    private final BigDecimal totalItemValue;

    public SaleItem(Product product, Integer quantity, BigDecimal unitPrice) {
        this.product = Objects.requireNonNull(product, "product is required");
        this.quantity = Objects.requireNonNull(quantity, "quantity is required");
        this.unitPrice = Objects.requireNonNull(unitPrice, "unitPrice is required");
        this.totalItemValue = unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    public Product getProduct() { return product; }
    public Integer getQuantity() { return quantity; }
    public BigDecimal getUnitPrice() { return unitPrice; }
    public BigDecimal getTotalItemValue() { return totalItemValue; }
}
