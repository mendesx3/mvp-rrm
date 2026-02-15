package com.mvp.infrastructure.persistence;

import com.mvp.core.domain.PaymentMethod;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "sales")
public class SaleJpaEntity {
    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "contact_id", nullable = false)
    private ContactJpaEntity contact;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleItemJpaEntity> items = new ArrayList<>();

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal totalValue;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod paymentMethod;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    protected SaleJpaEntity() {}

    public SaleJpaEntity(UUID id, ContactJpaEntity contact, BigDecimal totalValue, PaymentMethod paymentMethod, LocalDateTime createdAt) {
        this.id = id;
        this.contact = contact;
        this.totalValue = totalValue;
        this.paymentMethod = paymentMethod;
        this.createdAt = createdAt;
    }

    public void addItem(SaleItemJpaEntity item) {
        item.setSale(this);
        this.items.add(item);
    }

    public UUID getId() { return id; }
    public ContactJpaEntity getContact() { return contact; }
    public List<SaleItemJpaEntity> getItems() { return items; }
    public BigDecimal getTotalValue() { return totalValue; }
    public PaymentMethod getPaymentMethod() { return paymentMethod; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
