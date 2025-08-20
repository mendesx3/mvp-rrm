package com.mvp.core.ports;

import com.mvp.core.domain.Cart;

import java.util.Optional;
import java.util.UUID;

public interface CartRepository {
    Optional<Cart> findById(UUID id);
    Cart save(Cart cart);
}
