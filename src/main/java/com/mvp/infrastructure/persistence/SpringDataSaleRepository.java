package com.mvp.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface SpringDataSaleRepository extends JpaRepository<SaleJpaEntity, UUID> {
    List<SaleJpaEntity> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}
