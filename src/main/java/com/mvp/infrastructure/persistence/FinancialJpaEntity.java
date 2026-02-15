package com.mvp.infrastructure.persistence;

import com.mvp.core.domain.TransactionStatus;
import com.mvp.core.domain.TransactionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "financial_transactions")
public class FinancialJpaEntity {
    @Id
    private UUID id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal value;

    private LocalDate dueDate;

    private LocalDate paymentDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionStatus status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "contact_id", nullable = false)
    private ContactJpaEntity contact;

    @Column(name = "sale_id")
    private UUID saleId;

    protected FinancialJpaEntity() {
    }

    public FinancialJpaEntity(UUID id,
                              String description,
                              BigDecimal value,
                              LocalDate dueDate,
                              LocalDate paymentDate,
                              TransactionType type,
                              TransactionStatus status,
                              ContactJpaEntity contact,
                              UUID saleId) {
        this.id = id;
        this.description = description;
        this.value = value;
        this.dueDate = dueDate;
        this.paymentDate = paymentDate;
        this.type = type;
        this.status = status;
        this.contact = contact;
        this.saleId = saleId;
    }

    public UUID getId() { return id; }
    public String getDescription() { return description; }
    public BigDecimal getValue() { return value; }
    public LocalDate getDueDate() { return dueDate; }
    public LocalDate getPaymentDate() { return paymentDate; }
    public TransactionType getType() { return type; }
    public TransactionStatus getStatus() { return status; }
    public ContactJpaEntity getContact() { return contact; }
    public UUID getSaleId() { return saleId; }
}
