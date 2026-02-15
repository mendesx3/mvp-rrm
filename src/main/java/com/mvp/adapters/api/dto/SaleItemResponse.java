package com.mvp.adapters.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.UUID;

@Schema(description = "Item da venda")
public record SaleItemResponse(
        @Schema(description = "Identificador do produto") UUID productId,
        @Schema(description = "Quantidade") Integer quantity,
        @Schema(description = "Preço unitário") BigDecimal unitPrice,
        @Schema(description = "Valor total do item") BigDecimal totalItemValue
) {
}
