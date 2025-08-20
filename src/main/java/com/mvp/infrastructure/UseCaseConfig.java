package com.mvp.infrastructure;

import com.mvp.core.ports.*;
import com.mvp.core.usecase.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public CreateProduct createProduct(ProductRepository productRepository) {
        return new CreateProduct(productRepository);
    }

    @Bean
    public SearchProducts searchProducts(ProductRepository productRepository) {
        return new SearchProducts(productRepository);
    }

    @Bean
    public CreateCart createCart(CartRepository cartRepository) {
        return new CreateCart(cartRepository);
    }

    @Bean
    public GetCart getCart(CartRepository cartRepository) {
        return new GetCart(cartRepository);
    }

    @Bean
    public AddItemToCart addItemToCart(CartRepository cartRepository, ProductRepository productRepository) {
        return new AddItemToCart(cartRepository, productRepository);
    }

    @Bean
    public CheckoutCart checkoutCart(CartRepository cartRepository, OrderRepository orderRepository, InventoryGateway inventoryGateway) {
        return new CheckoutCart(cartRepository, orderRepository, inventoryGateway);
    }
}
