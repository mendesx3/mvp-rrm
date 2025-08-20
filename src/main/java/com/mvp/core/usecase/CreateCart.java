package com.mvp.core.usecase;

import com.mvp.core.domain.Cart;
import com.mvp.core.ports.CartRepository;

import java.util.UUID;

public class CreateCart {
    private final CartRepository cartRepository;

    public CreateCart(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart execute() {
        Cart cart = new Cart(UUID.randomUUID());
        return cartRepository.save(cart);
    }
}
