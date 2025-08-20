package com.mvp.adapters.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;

@Schema(description = "Requisição para adicionar um item ao carrinho")
public record AddItemRequest(
        @Schema(description = "Identificador do produto") UUID productId,
        @Schema(description = "Quantidade desejada") int quantity
) {}
