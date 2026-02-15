package com.mvp.infrastructure.persistence;

import com.mvp.core.domain.FinancialTransaction;

public final class FinancialPersistenceMapper {
    private FinancialPersistenceMapper() {
    }

    public static FinancialJpaEntity toEntity(FinancialTransaction transaction, ContactJpaEntity contact) {
        return new FinancialJpaEntity(
                transaction.getId(),
                transaction.getDescription(),
                transaction.getValue(),
                transaction.getDueDate(),
                transaction.getPaymentDate(),
                transaction.getType(),
                transaction.getStatus(),
                contact,
                transaction.getSaleId()
        );
    }

    public static FinancialTransaction toDomain(FinancialJpaEntity entity) {
        return new FinancialTransaction(
                entity.getId(),
                entity.getDescription(),
                entity.getValue(),
                entity.getDueDate(),
                entity.getPaymentDate(),
                entity.getType(),
                entity.getStatus(),
                ContactPersistenceMapper.toDomain(entity.getContact()),
                entity.getSaleId()
        );
    }
}
