package com.mvp.core.ports;

import com.mvp.core.domain.FinancialTransaction;
import com.mvp.core.domain.TransactionStatus;
import com.mvp.core.domain.TransactionType;

import java.time.LocalDate;
import java.util.List;

public interface FinancialRepository {
    FinancialTransaction save(FinancialTransaction transaction);
    List<FinancialTransaction> findByPeriod(LocalDate startDate, LocalDate endDate);
    List<FinancialTransaction> findByStatus(TransactionStatus status);
    List<FinancialTransaction> findByDueDateAndStatusAndType(LocalDate dueDate, TransactionStatus status, TransactionType type);
    List<FinancialTransaction> findByPaymentDateAndStatusAndType(LocalDate paymentDate, TransactionStatus status, TransactionType type);
}
