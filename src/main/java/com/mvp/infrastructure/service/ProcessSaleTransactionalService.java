package com.mvp.infrastructure.service;

import com.mvp.core.domain.PaymentMethod;
import com.mvp.core.domain.Sale;
import com.mvp.core.usecase.ProcessSaleUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ProcessSaleTransactionalService {
    private final ProcessSaleUseCase processSaleUseCase;

    public ProcessSaleTransactionalService(ProcessSaleUseCase processSaleUseCase) {
        this.processSaleUseCase = processSaleUseCase;
    }

    @Transactional
    public Sale execute(UUID contactId, PaymentMethod paymentMethod, List<ProcessSaleUseCase.SaleItemInput> items) {
        return processSaleUseCase.execute(contactId, paymentMethod, items);
    }
}
