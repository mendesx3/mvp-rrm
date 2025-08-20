package com.mvp.infrastructure;

import com.mvp.core.domain.Cart;
import com.mvp.core.ports.CartRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryCartRepository implements CartRepository {
    private final Map<UUID, Cart> store = new ConcurrentHashMap<>();

    @Override
    public Optional<Cart> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Cart save(Cart cart) {
        store.put(cart.getId(), cart);
        return cart;
    }
}
