package com.mvp.core.usecase;

import com.mvp.core.domain.Cart;
import com.mvp.core.ports.CartRepository;

import java.util.UUID;

public class GetCart {
    private final CartRepository cartRepository;

    public GetCart(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart execute(UUID id) {
        return cartRepository.findById(id).orElseThrow();
    }
}
