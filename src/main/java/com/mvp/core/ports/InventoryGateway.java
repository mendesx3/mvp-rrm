package com.mvp.core.ports;

import java.util.UUID;

public interface InventoryGateway {
    boolean checkAndDecrease(UUID productId, int quantity);
}
