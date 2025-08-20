package com.mvp.adapters.api.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateProductRequest(String name, String description, BigDecimal price, UUID categoryId, int stock) {}
