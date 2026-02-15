package com.mvp.adapters.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Schema(description = "Item para processamento de venda")
public record ProcessSaleItemRequest(
        @NotNull(message = "Produto do item é obrigatório")
        @Schema(description = "Identificador do produto", example = "58c183d3-b2d8-4f8a-93d5-df93dd59b89f") UUID productId,

        @NotNull(message = "Quantidade do item é obrigatória")
        @Min(value = 1, message = "Quantidade do item deve ser no mínimo 1")
        @Schema(description = "Quantidade vendida", example = "2") Integer quantity
) {
}
