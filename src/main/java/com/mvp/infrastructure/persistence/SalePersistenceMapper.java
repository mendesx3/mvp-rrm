package com.mvp.infrastructure.persistence;

import com.mvp.core.domain.Sale;
import com.mvp.core.domain.SaleItem;

import java.util.Map;
import java.util.UUID;

public final class SalePersistenceMapper {
    private SalePersistenceMapper() {}

    public static SaleJpaEntity toEntity(Sale sale,
                                         ContactJpaEntity contact,
                                         Map<UUID, ProductJpaEntity> productsById) {
        SaleJpaEntity saleEntity = new SaleJpaEntity(
                sale.getId(),
                contact,
                sale.getTotalValue(),
                sale.getPaymentMethod(),
                sale.getCreatedAt()
        );

        for (SaleItem item : sale.getItems()) {
            ProductJpaEntity product = productsById.get(item.getProduct().getId());
            if (product == null) {
                throw new IllegalArgumentException("Produto não encontrado para item de venda");
            }
            SaleItemJpaEntity itemEntity = new SaleItemJpaEntity(
                    UUID.randomUUID(),
                    product,
                    item.getQuantity(),
                    item.getUnitPrice(),
                    item.getTotalItemValue()
            );
            saleEntity.addItem(itemEntity);
        }

        return saleEntity;
    }

    public static Sale toDomain(SaleJpaEntity entity) {
        return new Sale(
                entity.getId(),
                ContactPersistenceMapper.toDomain(entity.getContact()),
                entity.getItems().stream().map(item -> new SaleItem(
                        ProductPersistenceMapper.toDomain(item.getProduct()),
                        item.getQuantity(),
                        item.getUnitPrice()
                )).toList(),
                entity.getTotalValue(),
                entity.getPaymentMethod(),
                entity.getCreatedAt()
        );
    }
}
