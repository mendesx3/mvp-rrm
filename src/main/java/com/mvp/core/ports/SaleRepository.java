package com.mvp.core.ports;

import com.mvp.core.domain.Sale;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository {
    Sale save(Sale sale);
    List<Sale> findByDate(LocalDate date);
}
