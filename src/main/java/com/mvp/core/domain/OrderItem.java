package com.mvp.core.domain;

import java.util.UUID;

public record OrderItem(UUID productId, int quantity, Money price) {}
