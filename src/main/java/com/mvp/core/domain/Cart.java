package com.mvp.core.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Cart {
    private final UUID id;
    private final List<CartItem> items = new ArrayList<>();

    public Cart(UUID id) {
        this.id = id;
    }

    public UUID getId() { return id; }
    public List<CartItem> getItems() { return items; }

    public void addItem(CartItem item) {
        items.add(item);
    }
}
