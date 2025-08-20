package com.mvp.adapters.api.dto;

import java.util.UUID;

public record AddItemRequest(UUID productId, int quantity) {}
