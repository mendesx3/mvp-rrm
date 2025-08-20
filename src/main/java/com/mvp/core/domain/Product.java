package com.mvp.core.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Product {
    private final UUID id;
    private final String name;
    private final String description;
    private final Money price;
    private final UUID categoryId;
    private final List<String> images = new ArrayList<>();
    private int stock;

    public Product(UUID id, String name, String description, Money price, UUID categoryId, int stock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
        this.stock = stock;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public Money getPrice() { return price; }
    public UUID getCategoryId() { return categoryId; }
    public List<String> getImages() { return images; }
    public int getStock() { return stock; }

    public void decreaseStock(int quantity) {
        if(quantity > stock) throw new IllegalArgumentException("Insufficient stock");
        stock -= quantity;
    }
}
