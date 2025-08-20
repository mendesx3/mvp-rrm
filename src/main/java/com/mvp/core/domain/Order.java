package com.mvp.core.domain;

import java.util.List;
import java.util.UUID;

public class Order {
    private final UUID id;
    private final List<OrderItem> items;
    private final Money total;

    public Order(UUID id, List<OrderItem> items, Money total) {
        this.id = id;
        this.items = items;
        this.total = total;
    }

    public UUID getId() { return id; }
    public List<OrderItem> getItems() { return items; }
    public Money getTotal() { return total; }
}
