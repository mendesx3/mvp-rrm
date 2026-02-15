package com.mvp.infrastructure.persistence;

import com.mvp.core.domain.FinancialTransaction;
import com.mvp.core.domain.TransactionStatus;
import com.mvp.core.domain.TransactionType;
import com.mvp.core.ports.FinancialRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class FinancialRepositoryJpaAdapter implements FinancialRepository {
    private final SpringDataFinancialRepository financialRepository;
    private final SpringDataContactRepository contactRepository;

    public FinancialRepositoryJpaAdapter(SpringDataFinancialRepository financialRepository,
                                         SpringDataContactRepository contactRepository) {
        this.financialRepository = financialRepository;
        this.contactRepository = contactRepository;
    }

    @Override
    public FinancialTransaction save(FinancialTransaction transaction) {
        ContactJpaEntity contact = contactRepository.findById(transaction.getContact().getId())
                .orElseThrow(() -> new IllegalArgumentException("Contato não encontrado para lançamento financeiro"));

        FinancialJpaEntity entity = FinancialPersistenceMapper.toEntity(transaction, contact);
        FinancialJpaEntity saved = financialRepository.save(entity);
        return FinancialPersistenceMapper.toDomain(saved);
    }

    @Override
    public List<FinancialTransaction> findByPeriod(LocalDate startDate, LocalDate endDate) {
        return financialRepository.findByDueDateBetween(startDate, endDate)
                .stream()
                .map(FinancialPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public List<FinancialTransaction> findByStatus(TransactionStatus status) {
        return financialRepository.findByStatus(status)
                .stream()
                .map(FinancialPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public List<FinancialTransaction> findByDueDateAndStatusAndType(LocalDate dueDate, TransactionStatus status, TransactionType type) {
        return financialRepository.findByDueDateAndStatusAndType(dueDate, status, type)
                .stream()
                .map(FinancialPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public List<FinancialTransaction> findByPaymentDateAndStatusAndType(LocalDate paymentDate, TransactionStatus status, TransactionType type) {
        return financialRepository.findByPaymentDateAndStatusAndType(paymentDate, status, type)
                .stream()
                .map(FinancialPersistenceMapper::toDomain)
                .toList();
    }
}
