package com.mvp.core.usecase;

import com.mvp.core.domain.Contact;
import com.mvp.core.domain.FinancialTransaction;
import com.mvp.core.domain.TransactionStatus;
import com.mvp.core.domain.TransactionType;
import com.mvp.core.domain.exception.BusinessException;
import com.mvp.core.domain.exception.ResourceNotFoundException;
import com.mvp.core.ports.ContactRepository;
import com.mvp.core.ports.FinancialRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class CreateFinancialEntryUseCase {
    private static final Logger log = LoggerFactory.getLogger(CreateFinancialEntryUseCase.class);

    private final FinancialRepository financialRepository;
    private final ContactRepository contactRepository;

    public CreateFinancialEntryUseCase(FinancialRepository financialRepository, ContactRepository contactRepository) {
        this.financialRepository = financialRepository;
        this.contactRepository = contactRepository;
    }

    public FinancialTransaction execute(String description,
                                        BigDecimal value,
                                        LocalDate dueDate,
                                        TransactionType type,
                                        UUID contactId) {
        if (type == TransactionType.SAIDA && dueDate == null) {
            throw new BusinessException("Data de vencimento é obrigatória para lançamentos de saída");
        }

        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado"));

        log.info("Criando lançamento financeiro para contato: {}", contact.getName());

        FinancialTransaction transaction = new FinancialTransaction(
                UUID.randomUUID(),
                description,
                value,
                dueDate,
                null,
                type,
                TransactionStatus.PENDENTE,
                contact,
                null
        );

        FinancialTransaction saved = financialRepository.save(transaction);
        log.info("Lançamento financeiro {} criado com sucesso", saved.getId());
        return saved;
    }
}
