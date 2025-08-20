package com.mvp.adapters.api.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record CartItemResponse(UUID productId, int quantity, BigDecimal price) {}
