package com.mvp.core.usecase;

import com.mvp.core.domain.*;
import com.mvp.core.ports.CartRepository;
import com.mvp.core.ports.InventoryGateway;
import com.mvp.core.ports.OrderRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CheckoutCart {
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final InventoryGateway inventoryGateway;

    public CheckoutCart(CartRepository cartRepository, OrderRepository orderRepository, InventoryGateway inventoryGateway) {
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.inventoryGateway = inventoryGateway;
    }

    public Order execute(UUID cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow();
        List<OrderItem> items = new ArrayList<>();
        Money total = new Money(BigDecimal.ZERO);
        for (CartItem ci : cart.getItems()) {
            if(!inventoryGateway.checkAndDecrease(ci.productId(), ci.quantity())) {
                throw new IllegalStateException("Out of stock");
            }
            items.add(new OrderItem(ci.productId(), ci.quantity(), ci.price()));
            total = total.add(ci.price().multiply(ci.quantity()));
        }
        Order order = new Order(UUID.randomUUID(), items, total);
        orderRepository.save(order);
        cart.getItems().clear();
        cartRepository.save(cart);
        return order;
    }
}
