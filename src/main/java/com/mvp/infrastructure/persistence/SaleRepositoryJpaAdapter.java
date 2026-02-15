package com.mvp.infrastructure.persistence;

import com.mvp.core.domain.Sale;
import com.mvp.core.ports.SaleRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class SaleRepositoryJpaAdapter implements SaleRepository {
    private final SpringDataSaleRepository saleRepository;
    private final SpringDataContactRepository contactRepository;
    private final SpringDataProductRepository productRepository;

    public SaleRepositoryJpaAdapter(SpringDataSaleRepository saleRepository,
                                    SpringDataContactRepository contactRepository,
                                    SpringDataProductRepository productRepository) {
        this.saleRepository = saleRepository;
        this.contactRepository = contactRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Sale save(Sale sale) {
        ContactJpaEntity contact = contactRepository.findById(sale.getContact().getId())
                .orElseThrow(() -> new IllegalArgumentException("Contato não encontrado para venda"));

        Map<UUID, ProductJpaEntity> productsById = productRepository.findAllById(
                        sale.getItems().stream().map(i -> i.getProduct().getId()).toList())
                .stream()
                .collect(Collectors.toMap(ProductJpaEntity::getId, p -> p));

        SaleJpaEntity saved = saleRepository.save(SalePersistenceMapper.toEntity(sale, contact, productsById));
        return SalePersistenceMapper.toDomain(saved);
    }

    @Override
    public List<Sale> findByDate(LocalDate date) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.plusDays(1).atStartOfDay();
        return saleRepository.findByCreatedAtBetween(start, end)
                .stream()
                .map(SalePersistenceMapper::toDomain)
                .toList();
    }
}
