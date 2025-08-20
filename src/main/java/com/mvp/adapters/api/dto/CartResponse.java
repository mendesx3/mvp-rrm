package com.mvp.adapters.api.dto;

import java.util.List;
import java.util.UUID;

public record CartResponse(UUID id, List<CartItemResponse> items) {}
