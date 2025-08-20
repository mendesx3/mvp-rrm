package com.mvp.adapters.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.UUID;

@Schema(description = "Representa um carrinho de compras")
public record CartResponse(
        @Schema(description = "Identificador do carrinho") UUID id,
        @Schema(description = "Itens presentes no carrinho") List<CartItemResponse> items
) {}
