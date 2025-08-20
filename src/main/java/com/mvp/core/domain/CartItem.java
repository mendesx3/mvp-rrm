package com.mvp.core.domain;

import java.util.UUID;

public record CartItem(UUID productId, int quantity, Money price) {}
