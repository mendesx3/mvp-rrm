package com.mvp.core.usecase;

import com.mvp.core.domain.*;
import com.mvp.core.domain.exception.InsufficientStockException;
import com.mvp.core.domain.exception.ResourceNotFoundException;
import com.mvp.core.ports.ContactRepository;
import com.mvp.core.ports.FinancialRepository;
import com.mvp.core.ports.ProductRepository;
import com.mvp.core.ports.SaleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ProcessSaleUseCase {
    private static final Logger log = LoggerFactory.getLogger(ProcessSaleUseCase.class);

    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;
    private final ContactRepository contactRepository;
    private final FinancialRepository financialRepository;

    public ProcessSaleUseCase(SaleRepository saleRepository,
                              ProductRepository productRepository,
                              ContactRepository contactRepository,
                              FinancialRepository financialRepository) {
        this.saleRepository = saleRepository;
        this.productRepository = productRepository;
        this.contactRepository = contactRepository;
        this.financialRepository = financialRepository;
    }

    public Sale execute(UUID contactId, PaymentMethod paymentMethod, List<SaleItemInput> saleItemInputs) {
        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

        log.info("Processando venda para o cliente: {}", contact.getName());

        List<SaleItem> saleItems = saleItemInputs.stream()
                .map(this::buildSaleItem)
                .toList();

        Sale sale = new Sale(
                UUID.randomUUID(),
                contact,
                saleItems,
                saleItems.stream().map(SaleItem::getTotalItemValue).reduce(BigDecimal.ZERO, BigDecimal::add),
                paymentMethod,
                LocalDateTime.now()
        );

        Sale savedSale = saleRepository.save(sale);

        for (SaleItem item : saleItems) {
            Product product = item.getProduct();
            product.decreaseStock(item.getQuantity());
            productRepository.save(product);
        }

        FinancialTransaction transaction = new FinancialTransaction(
                UUID.randomUUID(),
                "Venda " + savedSale.getId(),
                savedSale.getTotalValue(),
                paymentMethod == PaymentMethod.PRAZO ? LocalDate.now().plusDays(30) : LocalDate.now(),
                null,
                TransactionType.ENTRADA,
                TransactionStatus.PENDENTE,
                contact,
                savedSale.getId()
        );

        if (paymentMethod == PaymentMethod.DINHEIRO || paymentMethod == PaymentMethod.PIX) {
            transaction.pay(LocalDate.now());
        }

        financialRepository.save(transaction);
        log.info("Venda {} processada com sucesso", savedSale.getId());

        return savedSale;
    }

    private SaleItem buildSaleItem(SaleItemInput input) {
        Product product = productRepository.findById(input.productId())
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));
        if (product.getStockQuantity() < input.quantity()) {
            throw new InsufficientStockException(
                    "Produto [" + product.getName() + "] fora de estoque (Disponível: "
                            + product.getStockQuantity() + ", Solicitado: " + input.quantity() + ")"
            );
        }
        return new SaleItem(product, input.quantity(), product.getSalePrice());
    }

    public record SaleItemInput(UUID productId, Integer quantity) {
    }
}
