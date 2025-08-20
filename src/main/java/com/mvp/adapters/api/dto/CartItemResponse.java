package com.mvp.adapters.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.UUID;

@Schema(description = "Item contido no carrinho")
public record CartItemResponse(
        @Schema(description = "Identificador do produto") UUID productId,
        @Schema(description = "Quantidade do produto") int quantity,
        @Schema(description = "Preço unitário do produto") BigDecimal price
) {}
