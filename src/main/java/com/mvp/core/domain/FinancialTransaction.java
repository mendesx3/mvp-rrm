package com.mvp.core.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class FinancialTransaction {
    private final UUID id;
    private final String description;
    private final BigDecimal value;
    private final LocalDate dueDate;
    private LocalDate paymentDate;
    private final TransactionType type;
    private TransactionStatus status;
    private final Contact contact;
    private final UUID saleId;

    public FinancialTransaction(UUID id,
                                String description,
                                BigDecimal value,
                                LocalDate dueDate,
                                LocalDate paymentDate,
                                TransactionType type,
                                TransactionStatus status,
                                Contact contact,
                                UUID saleId) {
        this.id = Objects.requireNonNull(id, "id is required");
        this.description = Objects.requireNonNull(description, "description is required");
        this.value = Objects.requireNonNull(value, "value is required");
        this.dueDate = dueDate;
        this.paymentDate = paymentDate;
        this.type = Objects.requireNonNull(type, "type is required");
        this.status = Objects.requireNonNull(status, "status is required");
        this.contact = Objects.requireNonNull(contact, "contact is required");
        this.saleId = saleId;
    }

    public UUID getId() { return id; }
    public String getDescription() { return description; }
    public BigDecimal getValue() { return value; }
    public LocalDate getDueDate() { return dueDate; }
    public LocalDate getPaymentDate() { return paymentDate; }
    public TransactionType getType() { return type; }
    public TransactionStatus getStatus() { return status; }
    public Contact getContact() { return contact; }
    public UUID getSaleId() { return saleId; }

    public void pay(LocalDate date) {
        this.status = TransactionStatus.PAGO;
        this.paymentDate = Objects.requireNonNull(date, "payment date is required");
    }
}
