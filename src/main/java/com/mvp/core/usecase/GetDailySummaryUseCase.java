package com.mvp.core.usecase;

import com.mvp.core.domain.FinancialTransaction;
import com.mvp.core.domain.Sale;
import com.mvp.core.domain.TransactionStatus;
import com.mvp.core.domain.TransactionType;
import com.mvp.core.ports.FinancialRepository;
import com.mvp.core.ports.SaleRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class GetDailySummaryUseCase {
    private final SaleRepository saleRepository;
    private final FinancialRepository financialRepository;

    public GetDailySummaryUseCase(SaleRepository saleRepository, FinancialRepository financialRepository) {
        this.saleRepository = saleRepository;
        this.financialRepository = financialRepository;
    }

    public DailySummaryResult execute(LocalDate date) {
        List<Sale> sales = saleRepository.findByDate(date);
        BigDecimal totalSales = sales.stream()
                .map(Sale::getTotalValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalToReceive = sum(financialRepository.findByDueDateAndStatusAndType(date, TransactionStatus.PENDENTE, TransactionType.ENTRADA));
        BigDecimal totalToPay = sum(financialRepository.findByDueDateAndStatusAndType(date, TransactionStatus.PENDENTE, TransactionType.SAIDA));

        BigDecimal paidIn = sum(financialRepository.findByPaymentDateAndStatusAndType(date, TransactionStatus.PAGO, TransactionType.ENTRADA));
        BigDecimal paidOut = sum(financialRepository.findByPaymentDateAndStatusAndType(date, TransactionStatus.PAGO, TransactionType.SAIDA));

        BigDecimal netCashFlow = paidIn.subtract(paidOut);

        return new DailySummaryResult(totalSales, totalToReceive, totalToPay, netCashFlow);
    }

    private BigDecimal sum(List<FinancialTransaction> transactions) {
        return transactions.stream()
                .map(FinancialTransaction::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public record DailySummaryResult(BigDecimal totalSales,
                                     BigDecimal totalToReceive,
                                     BigDecimal totalToPay,
                                     BigDecimal netCashFlow) {
    }
}
