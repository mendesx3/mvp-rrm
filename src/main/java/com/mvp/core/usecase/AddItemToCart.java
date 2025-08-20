package com.mvp.core.usecase;

import com.mvp.core.domain.Cart;
import com.mvp.core.domain.CartItem;
import com.mvp.core.domain.Product;
import com.mvp.core.ports.CartRepository;
import com.mvp.core.ports.ProductRepository;

import java.util.UUID;

public class AddItemToCart {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public AddItemToCart(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public Cart execute(UUID cartId, UUID productId, int quantity) {
        Cart cart = cartRepository.findById(cartId).orElse(new Cart(cartId));
        Product product = productRepository.findById(productId).orElseThrow();
        cart.addItem(new CartItem(productId, quantity, product.getPrice()));
        return cartRepository.save(cart);
    }
}
