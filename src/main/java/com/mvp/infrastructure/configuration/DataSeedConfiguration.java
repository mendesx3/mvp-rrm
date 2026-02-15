package com.mvp.infrastructure.configuration;

import com.mvp.core.domain.ContactType;
import com.mvp.core.domain.TransactionStatus;
import com.mvp.core.domain.TransactionType;
import com.mvp.infrastructure.persistence.CategoryJpaEntity;
import com.mvp.infrastructure.persistence.ContactJpaEntity;
import com.mvp.infrastructure.persistence.FinancialJpaEntity;
import com.mvp.infrastructure.persistence.ProductJpaEntity;
import com.mvp.infrastructure.persistence.SpringDataCategoryRepository;
import com.mvp.infrastructure.persistence.SpringDataContactRepository;
import com.mvp.infrastructure.persistence.SpringDataFinancialRepository;
import com.mvp.infrastructure.persistence.SpringDataProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Configuration
public class DataSeedConfiguration {
    private static final Logger log = LoggerFactory.getLogger(DataSeedConfiguration.class);

    @Bean
    public CommandLineRunner dataSeedRunner(SpringDataCategoryRepository categoryRepository,
                                            SpringDataContactRepository contactRepository,
                                            SpringDataProductRepository productRepository,
                                            SpringDataFinancialRepository financialRepository) {
        return args -> {
            boolean bancoVazio = categoryRepository.count() == 0
                    && contactRepository.count() == 0
                    && productRepository.count() == 0
                    && financialRepository.count() == 0;

            if (!bancoVazio) {
                log.info("Seed inicial ignorado: banco já possui dados");
                return;
            }

            List<CategoryJpaEntity> categories = List.of(
                    new CategoryJpaEntity(UUID.randomUUID(), "Vestuário"),
                    new CategoryJpaEntity(UUID.randomUUID(), "Calçados"),
                    new CategoryJpaEntity(UUID.randomUUID(), "Acessórios")
            );
            categoryRepository.saveAll(categories);

            ContactJpaEntity consumidorPadrao = contactRepository.save(new ContactJpaEntity(
                    UUID.randomUUID(),
                    "Consumidor Padrão",
                    "00000000000",
                    "consumidor@padrao.com",
                    ContactType.CLIENTE
            ));

            ContactJpaEntity fornecedorCentral = contactRepository.save(new ContactJpaEntity(
                    UUID.randomUUID(),
                    "Fornecedor Central",
                    "11111111000199",
                    "fornecedor@central.com",
                    ContactType.FORNECEDOR
            ));

            productRepository.saveAll(List.of(
                    new ProductJpaEntity(UUID.randomUUID(), "Camiseta", "Camiseta básica algodão", new BigDecimal("25.00"), new BigDecimal("49.90"), 80),
                    new ProductJpaEntity(UUID.randomUUID(), "Calça", "Calça jeans tradicional", new BigDecimal("60.00"), new BigDecimal("129.90"), 45),
                    new ProductJpaEntity(UUID.randomUUID(), "Tênis", "Tênis casual unissex", new BigDecimal("120.00"), new BigDecimal("249.90"), 30)
            ));

            financialRepository.save(new FinancialJpaEntity(
                    UUID.randomUUID(),
                    "Aluguel",
                    new BigDecimal("3500.00"),
                    LocalDate.now().plusDays(5),
                    null,
                    TransactionType.SAIDA,
                    TransactionStatus.PENDENTE,
                    fornecedorCentral,
                    null
            ));

            log.info("Seed inicial carregado com sucesso (cliente padrão: {}, fornecedor: {})",
                    consumidorPadrao.getName(), fornecedorCentral.getName());
        };
    }
}
