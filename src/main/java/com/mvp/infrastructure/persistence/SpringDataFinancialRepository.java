package com.mvp.infrastructure.persistence;

import com.mvp.core.domain.TransactionStatus;
import com.mvp.core.domain.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface SpringDataFinancialRepository extends JpaRepository<FinancialJpaEntity, UUID> {
    List<FinancialJpaEntity> findByDueDateBetween(LocalDate startDate, LocalDate endDate);
    List<FinancialJpaEntity> findByStatus(TransactionStatus status);
    List<FinancialJpaEntity> findByDueDateAndStatusAndType(LocalDate dueDate, TransactionStatus status, TransactionType type);
    List<FinancialJpaEntity> findByPaymentDateAndStatusAndType(LocalDate paymentDate, TransactionStatus status, TransactionType type);
}
