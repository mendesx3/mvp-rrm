package com.mvp.adapters.api.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderResponse(UUID id, BigDecimal total) {}
