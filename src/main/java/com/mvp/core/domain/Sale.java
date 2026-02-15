package com.mvp.core.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Sale {
    private final UUID id;
    private final Contact contact;
    private final List<SaleItem> items;
    private final BigDecimal totalValue;
    private final PaymentMethod paymentMethod;
    private final LocalDateTime createdAt;

    public Sale(UUID id,
                Contact contact,
                List<SaleItem> items,
                BigDecimal totalValue,
                PaymentMethod paymentMethod,
                LocalDateTime createdAt) {
        this.id = Objects.requireNonNull(id, "id is required");
        this.contact = Objects.requireNonNull(contact, "contact is required");
        this.items = List.copyOf(Objects.requireNonNull(items, "items are required"));
        this.totalValue = Objects.requireNonNull(totalValue, "totalValue is required");
        this.paymentMethod = Objects.requireNonNull(paymentMethod, "paymentMethod is required");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt is required");
    }

    public UUID getId() { return id; }
    public Contact getContact() { return contact; }
    public List<SaleItem> getItems() { return items; }
    public BigDecimal getTotalValue() { return totalValue; }
    public PaymentMethod getPaymentMethod() { return paymentMethod; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
