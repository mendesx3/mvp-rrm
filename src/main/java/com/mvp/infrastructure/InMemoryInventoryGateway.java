package com.mvp.infrastructure;

import com.mvp.core.ports.InventoryGateway;
import com.mvp.core.ports.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class InMemoryInventoryGateway implements InventoryGateway {
    private final ProductRepository productRepository;

    public InMemoryInventoryGateway(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public boolean checkAndDecrease(UUID productId, int quantity) {
        return productRepository.findById(productId)
                .map(p -> {
                    if (p.getStockQuantity() < quantity) return false;
                    p.decreaseStock(quantity);
                    productRepository.save(p);
                    return true;
                }).orElse(false);
    }
}
